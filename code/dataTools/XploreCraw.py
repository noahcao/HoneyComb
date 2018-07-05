from selenium import webdriver
import progressbar
import  csv
import time
from bs4 import BeautifulSoup as bs
import re

papers_met = {} # 记录了所有已经查询到了的paper的title
url_header = 'https://ieeexplore.ieee.org' # 在ieee xplore上进行搜索时的url的开头


# 初始化需要查询的topic的列表
def init_topics(topic_list):
    topics = []
    for topic in topic_list:
        topic_item = {}
        topic_item['keyword'] = topic
        topic_item['page_count'] = 1
        topic_item['total'] = topic_item['page_count'] * 100
        topics.append(topic_item)
    return topics

# 抓取source分步
def craw_pages(topics):
    driver = webdriver.Chrome()
    soups = []
    for topic in topics:
        print('Topic: %s', topic['keyword'])

    page_num = topic['page_count']
    pbar = progressbar.ProgressBar(page_num)
    pbar.start()

    for i in range(page_num):
        # 使用给定的topic中的keyword在xplore中进行搜索，默认每页显示100个搜索结果
        # Parameter:
        #   queryText='keyword'                 # 指定了在xplore中进行搜索使用的查询关键词
        #   sortType=desc_p_Citation_Count      # 将返回的结果按照其被其他论文引用的次数进行降序排列
        #   rowsPerPage                         # 每一页显示的查询结果的数量，默认为100
        #   pageNUmber                          # 需要获取的当前的查询页面的index
        driver.get('http://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText='+ topic['keyword'] +'&sortType=desc_p_Citation_Count&rowsPerPage=100&pageNumber='+str(i+1))

        time.sleep(5)
        for j in range(5):
            # 当每页显示100个搜索结果的时候，需要页面下拉5次才可以完整的得到显示，对于每一次显示，给浏览器一秒的响应时间
            driver.execute_script("window.scrollTo(0, " + str(100000) + ");")
            driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
            time.sleep(1)
        source = driver.page_source
        soup =  bs(source, "html.parser")
        soups.append(soup)

        pbar.update(i+1)

    pbar.finish() 
    driver.close()
    return soups

def innerHTML(element):
    return element.decode_contents(formatter='html')

def parseCitationPage(citation_url):
    # 解析一个论文对应的引用了它的ieee出版的论文的介绍页面，需要说明的是，这个页面中可能包含了大量的引用了这个论文的论文信息，
    # 但是默认情况下，我们只获得排名前三十的论文信息
    # 传入的citation_url是一个论文的被引用介绍页面，比如https://ieeexplore.ieee.org/document/6796673/citations?tabFilter=papers
    # 传出的是一个list，list中的每个element是一个字典，字典内包含了对应论文的title, author, publisher

    driver = webdriver.PhantomJS(executable_path=r".\\phantomjs\\bin\\phantomjs.exe")
    driver.get(citation_url)
    time.sleep(3)

    # 在页面中，只有点击了“Citation”按钮，才会展示引用了该论文的论文信息（首次展示至多30篇论文信息）
    try:
        element = driver.find_element_by_xpath("(//button[@type='button'])[3]")
    except:
        print("Error")
        driver.close()         
        return []
    else:               
        driver.execute_script("arguments[0].click();", element)
        #driver.find_element_by_xpath("(//button[@type='button'])[3]").click()

        source = driver.page_source

        soup = bs(source, "html.parser")

        # 获取页面上展示“Citation By Paper”的div位置，获取其中的内容
        citations_div = soup.select('.stats-citations-publisher-ieee')
        if len(citations_div) > 0:
            citations_div = citations_div[0]
        else:
            driver.close()
            return []
        citations = citations_div.select('.description')

        citation_list = []

        for citation in citations:
            '''
            meta = {}
            citation = str(citation)
            begin = citation.find('Text">')
            end = citation.find('</div>')
            citation = citation[begin+6:end]
            authors = citation[:citation.find("\"") - 2]
            authors = authors.replace(' ', '')
            authors = authors.split(',')
            meta["authors"] = authors # 获取论文的作者信息，作者可能不止一个人
            '''
            citation = str(citation)
            begin = citation.find('Text">')
            end = citation.find('</div>')
            citation = citation[begin+6:end]
            pattern = re.compile('"(.*)"')
            title = pattern.findall(citation)   # 获取论文的标题字符
            title = title[0]
            citation_list.append(title)

        driver.close()
        return citation_list

def parse_paper_body(r):
    # 解析一个页面中的关于论文查询结果的div，返回对应的metadata，具体来说，返回的是一个list
    # 包括了对应论文的title, author_list, year, cited和citation
    title = r.select('h2 a.ng-binding')
    year = r.select('span[ng-if="::record.publicationYear"]')
    author = r.select('span[ng-bind-html="::author.preferredName"]')
    cited = r.select('span[ng-if="::record.citationCount"]')

    title, n = re.subn("\\<.*?\\>", '', innerHTML(title[0]))
    year, n = re.subn("\\<.*?\\>", '', innerHTML(year[0]))
    year, n = re.subn('\s*?', '', year) # 去掉无用的空格
    year, n = re.subn('Year:', '', year) # 去电'Year:'字段

    if not author:
        author = u'No author'  # 可能出现没有作者的情况
    else:
        author_list = ""
        for oneauthor in author:
            oneauthor, n = author, n = re.subn("\\<.*?\\>", '', innerHTML(oneauthor)) # 去掉内容里HTML标签的部分
            author_list += oneauthor + ", "
        author_list = author_list[0: int(len(author_list)/2)]
    
    title = title.replace("[::", '')
    title = title.replace("::]", '')
    print(title)
    if not title in papers_met:
        # 如果这篇论文之前还没有被查询到过，就把他添加到查询字典中，否则，就直接开始下一篇论文的解析
        papers_met[title] = 1
    else:
        return [], []

    if not cited:
        # 一篇文章还没有被其他的文献引用
        cited = u'0'
        cited_url = ""
    else:
        content = str(cited[0])
        begin = int(content.find('/document'))
        end = int(content.find('=papers')) + 7
        cited_url = content[begin: end]
        cited_url = url_header + cited_url
        cited, n = re.subn("<.*?>", '', innerHTML(cited[0]))
        cited = cited.replace('Papers (', '')   # 去掉Papers(字段
        cited = cited.replace(')', '')          # 去掉）
        cited = cited.replace('\t', '')         # 去掉\t
        cited = cited.replace('\n', '')         # 去掉\n

    if cited_url != "":
        citation_list = parseCitationPage(cited_url)
    else:
        citation_list = []

    return [title, author_list, year, cited], citation_list


def analyse_soups(soups):
    # 对抓取道德网页的页面进行解析，这个页面是一个通过关键词查询得到的结果页面，所以内部可能会有多篇的论文信息
    years = []
    titles = []
    authors = []    
    publishers = []
    abstracts = []
    citeds = []

    base = 0
    for topic in topics:
        with open(topic['keyword']+'.csv', 'a', encoding='utf-8', newline='') as f:
            header = ['title', 'authors', 'year', 'citation']
            writer = csv.writer(f, delimiter=',')
            writer.writerow(header)
        for soup in soups[base:base + topic['page_count']]:
            result = soup.select('div.List-results-items')
            for r in result:
                '''
                title = r.select('h2 a.ng-binding')
                year = r.select('span[ng-if="::record.publicationYear"]')
                author = r.select('span[ng-bind-html="::author.preferredName"]')
                cited = r.select('span[ng-if="::record.citationCount"]')

                title, n = re.subn("\\<.*?\\>", '', innerHTML(title[0]))
                year, n = re.subn("\\<.*?\\>", '', innerHTML(year[0]))
                year, n = re.subn('\s*?', '', year) # 去掉无用的空格
                year, n = re.subn('Year:', '', year) # 去电'Year:'字段

                if not author:
                    author = u'No author'  # 可能出现没有作者的情况
                else:
                    author_list = ""
                    for oneauthor in author:
                        oneauthor, n = author, n = re.subn("\\<.*?\\>", '', innerHTML(oneauthor)) # 去掉内容里HTML标签的部分
                        author_list += oneauthor + ", "
                    author_list = author_list[0: int(len(author_list)/2)]
                
                title = title.replace("[::", '')
                title = title.replace("::]", '')
                print(title)
                if not title in papers_met:
                    # 如果这篇论文之前还没有被查询到过，就把他添加到查询字典中，否则，就直接开始下一篇论文的解析
                    papers_met[title] = 1
                else:
                    continue

                if not cited:
                    cited = u'0'
                    cited_url = ""
                else:
                    content = str(cited[0])
                    begin = int(content.find('/document'))
                    end = int(content.find('=papers')) + 7
                    cited_url = content[begin: end]
                    cited_url = url_header + cited_url
                    cited, n = re.subn("<.*?>", '', innerHTML(cited[0]))
                    cited = cited.replace('Papers (', '')   # 去掉Papers(字段
                    cited = cited.replace(')', '')          # 去掉）
                    cited = cited.replace('\t', '')         # 去掉\t
                    cited = cited.replace('\n', '')         # 去掉\n
                '''

                metadata, citation_list = parse_paper_body(r)
                if len(metadata) == 0:
                    continue

                # 把它们存在csv中
                f = open(topic['keyword']+'.csv', 'a', encoding='utf-8', newline='')
                writer = csv.writer(f, delimiter=',')
                #citation_list = parseCitationPage(cited_url)                 
                #row = [title, author_list, year, cited] + citation_list
                row = metadata + citation_lists
                writer.writerow(row)
    base += topic['page_count']

def analyse_onesoup(soup, filename):
    # 解析查询单篇文章得到的详情页面
    result = soup.select('div.List-results-items')
    for r in result:
        '''
        title = r.select('h2 a.ng-binding')
        year = r.select('span[ng-if="::record.publicationYear"]')
        author = r.select('span[ng-bind-html="::author.preferredName"]')
        cited = r.select('span[ng-if="::record.citationCount"]')

        title, n = re.subn("\\<.*?\\>", '', innerHTML(title[0]))
        year, n = re.subn("\\<.*?\\>", '', innerHTML(year[0]))
        year, n = re.subn('\s*?', '', year) # 去掉无用的空格
        year, n = re.subn('Year:', '', year) # 去电'Year:'字段

        if not author:
            author = u'No author'  # 可能出现没有作者的情况
        else:
            author_list = ""
            for oneauthor in author:
                oneauthor, n = author, n = re.subn("\\<.*?\\>", '', innerHTML(oneauthor)) # 去掉内容里HTML标签的部分
                author_list += oneauthor + ", "
            author_list = author_list[0: int(len(author_list)/2)]
        
        title = title.replace("[::", '')
        title = title.replace("::]", '')
        print(title)
        if not title in papers_met:
            # 如果这篇论文之前还没有被查询到过，就把他添加到查询字典中，否则，就直接开始下一篇论文的解析
            papers_met[title] = 1
        else:
            continue

        if not cited:
            # 一篇文章还没有被其他的文献引用
            cited = u'0'
            cited_url = ""
        else:
            content = str(cited[0])
            begin = int(content.find('/document'))
            end = int(content.find('=papers')) + 7
            cited_url = content[begin: end]
            cited_url = url_header + cited_url
            cited, n = re.subn("<.*?>", '', innerHTML(cited[0]))
            cited = cited.replace('Papers (', '')   # 去掉Papers(字段
            cited = cited.replace(')', '')          # 去掉）
            cited = cited.replace('\t', '')         # 去掉\t
            cited = cited.replace('\n', '')         # 去掉\n

        # 把它们存在csv里
        file = open(filename, 'a', encoding='utf-8', newline='')
        writer = csv.writer(file, delimiter=',')
        if cited_url != "":
            citation_list = parseCitationPage(cited_url)
        else:
            citation_list = [] 
        '''

        metadata, citation_list = parse_paper_body(r)
        # row = [title, author_list, year, cited] + citation_list
        if len(metadata) == 0:
            continue
        row = metadata + citation_list

        # 把结果写在对应的文件中
        file = open(filename, 'a', encoding='utf-8', newline='')
        writer = csv.writer(file, delimiter=',')
        writer.writerow(row)

        for paper in citation_list:
            # 对于引用列表中的文献，遍历他们进行查找，进行递归的搜索
            searchOnePaper(paper)
            

def searchOnePaper(paper_title, filename=None):
    # 通过一个paper的标题，在ieee xplore上查找他的相关信息
    # 特别说明：
    #   虽然默认的爬虫入口是通过关键词在ieee xplore得到的结果列表开始，但是理论上也可以通过单篇论文的查询网页开始，
    #   只要这个论文在ieee xplore上的被引用次数不是0，就可以了
    if paper_title in papers_met:
        return

    paper_title = paper_title.replace(' ', '%20')
    search_url_header = "https://ieeexplore.ieee.org/search/searchresult.jsp?newsearch=true&queryText="
    search_url = search_url_header + paper_title

    driver = webdriver.PhantomJS(executable_path=r".\\phantomjs\\bin\\phantomjs.exe")
    driver.get(search_url)

    source = driver.page_source
    soup = bs(source, 'html.parser')

    while str(soup).find("Getting results...") != -1:
        source = driver.page_source
        soup = bs(source, 'html.parser')

    if filename == None:
        filename = 'papermetadata.csv'
    driver.close()
    analyse_onesoup(soup, filename)


if __name__ == '__main__':
    #topic_list = ['machine%20learning']
    #topics = init_topics(topic_list)
    #soups = craw_pages(topics)
    #analyse_soups(soups)
    searchOnePaper("Fat-trees: Universal networks for hardware-efficient supercomputing", "papers.csv")
    #searchOnePaper("Grounding spatial relations for human-robot interaction")
    #parseCitationPage("https://ieeexplore.ieee.org/document/6796673/citations?tabFilter=papers")


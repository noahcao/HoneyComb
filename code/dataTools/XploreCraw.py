from selenium import webdriver
import progressbar
import  csv
import time
from bs4 import BeautifulSoup as bs
import re

# 初始化需要查询的topic的列表
def init_topics(topic_list):
    topics = []
    for topic in topic_list:
        topic_item = {}
        topic_item['keyword'] = topic
        topic_item['page_count'] = 2
        topic_item['total'] = topic_item['page_count'] * 100
        topics.append(topic_item)
    return topics

# 抓取source分步
def craw_pages(topics):
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
            time.sleep(2)
        source = driver.page_source
        soup =  bs(source, "html.parser")
        soups.append(soup)

        pbar.update(i+1)

    pbar.finish() 
    return soups

def innerHTML(element):
    return element.decode_contents(formatter='html')

def analyse_soups(soups):
    years = []
    titles = []
    authors = []    
    publishers = []
    abstracts = []
    citeds = []

    base = 0
    for topic in topics:
        with open(topic['keyword']+'.csv', 'a', encoding='utf-8', newline='') as f:
            header = ['title', 'first-author', 'year', 'citation']
            writer = csv.writer(f, delimiter=',')
            writer.writerow(header)
        for soup in soups[base:base + topic['page_count']]:
            result = soup.select('div.List-results-items')
            for r in result:
                #print("------------------------------")
                title = r.select('h2 a.ng-binding')
                #print(title)
                year = r.select('span[ng-if="::record.publicationYear"]')
                #print(year)
                author = r.select('span[ng-bind-html="::author.preferredName"]')
                #print(author)
                cited = r.select('span[ng-if="::record.citationCount"]')
                #print(cited)
                #print("----------------------------------")

                title, n = re.subn("\\<.*?\\>", '', innerHTML(title[0]))
                year, n = re.subn("\\<.*?\\>", '', innerHTML(year[0]))
                year, n = re.subn('\s*?', '', year) # 去掉无用的空格
                year, n = re.subn('Year:', '', year) # 去电'Year:'字段

                if not author:
                    author = u'No author'  # 可能出现没有作者的情况
                else:
                    author, n = re.subn("\\<.*?\\>", '', innerHTML(author[0]))  # 去掉内容里HTML标签的部分
                if not cited:
                    cited = u'0'
                else:
                    cited, n = re.subn("<.*?>", '', innerHTML(cited[0]))
                    cited = cited.replace('Papers (', '')   # 去掉Papers(字段
                    cited = cited.replace(')', '')          # 去掉）
                    cited = cited.replace('\t', '')         # 去掉\t
                    cited = cited.replace('\n', '')         # 去掉\n

                # 把它们存在csv里
                with open(topic['keyword']+'.csv', 'a', encoding='utf-8', newline='') as f:
                    writer = csv.writer(f, delimiter=',')
                    row = [title, author, year, cited]
                    writer.writerow(row)
    base += topic['page_count']


if __name__ == '__main__':
    driver = webdriver.Chrome()
    topic_list = ['deep%20learning']
    topics = init_topics(topic_list)
    soups = craw_pages(topics)
    analyse_soups(soups)


# -*- coding: utf-8 -*-
import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import time


# 执行函数
def work(browser,url):

    browser.get(url)
    ele_nums = []
    time.sleep(10)
    js = 'window.scrollTo(0, document.body.scrollHeight);'
    browser.execute_script(js)
    time.sleep(5)
    browser.execute_script(js)

    try:
        for link in browser.find_elements_by_xpath("//*[@data-artnum]"):
            if isContainClass(link.get_attribute('className'),'icon-pdf'):
                ele_num = link.get_attribute('data-artnum')
                ele_nums.append(ele_num)

        return ele_nums

    except:
        print("failure")


#用于判断某元素是否具有某class
def isContainClass(allClass,targetClass):
    #解析allClass,判断是否包含targetClass
    classArr = allClass.split(' ')
    result = False
    for str in classArr:
        if str == targetClass:
            result = True
            break
    return result


def getHtml(url):
    # Mozilla/5.0 (Windows NT 10.0; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0
    headers = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0'}
    try:
        response = requests.get(url,timeout=40,headers=headers)
        response.raise_for_status()

        response.encoding = response.apparent_encoding

        return response.text
    except:
        import traceback
        traceback.print_exc()

def getSoup(html):
    soup = BeautifulSoup(html,'html.parser')
    print(soup.body.find_all('a',attrs={'class':r'icon-pdf'}))

def downloadPaper(url):
    try:
        soup = BeautifulSoup(getHtml(url), 'html.parser')
        result = soup.body.find_all('iframe')

        downloadUrl = result[-1].attrs['src'].split('?')[0]

        headers = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:57.0) Gecko/20100101 Firefox/57.0'}
        response = requests.get(downloadUrl, timeout=80, headers=headers)

        fname = downloadUrl[-12:]
        print(fname)

        with open(fname,'ab+') as f:
            print('start download file ',fname)
            f.write(response.content)
    except:
        import traceback
        with open('errorLog','ab+') as f:
            traceback.print_exc(file=f)

if __name__ == '__main__':

    url = 'http://ieeexplore.ieee.org/search/searchresult.jsp'+\
          '?queryText=SLAM&ranges=2014_2018_Year&sortType=desc_p_Citation_Count'
    baseUrl = 'http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber='
    maxPageNumber = 3
    browser = webdriver.Chrome()

    if maxPageNumber >= 1:
        eleNums = work(browser,url)
        eleNums = list(set(eleNums))
        for eleNum in eleNums:
            newUrl = baseUrl+str(eleNum)
            downloadPaper(newUrl)

    else:
        for i in range(2,maxPageNumber+1):
            url = url+'&pageNumber='+str(i)
            eleNums = work(browser,url)
            eleNums = list(set(eleNums))
            for eleNum in eleNums:
                newUrl = baseUrl + str(eleNum)
                downloadPaper(newUrl)
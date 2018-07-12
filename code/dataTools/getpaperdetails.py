#-*- coding:utf-8 -*-
from XploreCraw import *
import csv
from selenium import webdriver
import threading
import warnings
import progressbar
import time

# 因为webdriver的版本问题，可能产生warning，为了方便，予以忽略
warnings.filterwarnings("ignore")
bar = progressbar.ProgressBar(10) 
bar.start()
current = 0

# paper.csv共有160030行 

class myThread(threading.Thread):
    # 一个自建的线程类，用于多线程的爬取数据，其中tID是这个线程的编号，keyword是进行爬虫的起始关键词
    # 可以是一个paper的title，
    def __init__(self, tID, paperlist, filename):
        threading.Thread.__init__(self)
        self.tID = tID
        self.filename = filename
        self.paperlist = paperlist

    def run(self):
        # 默认的执行函数，从一篇paper开始，进行论文数据的查找
        driver = webdriver.PhantomJS()
        for title in self.paperlist:
            try:
                searchOnePaper(title, driver=driver, filename=self.filename)
                global current
                current += 1
                bar.update(current)
            except:
                continue
        driver.close()

def search(threads, begin_idx, number, paper_list):
    # 开始逐篇论文的搜索信息，填充内容
    piece = int(number / threads)
    thread_list = []
    bar.maxval = number
    for i in range(threads):
        if i != threads - 1:
            begin = begin_idx + i * piece
            end = begin_idx + (i+1) * piece
        else:
            begin = begin_idx + i * piece
            end = begin_idx + number
        filename = "%d_%d.csv" % (begin, end)
        papers = paper_list[begin:end]
        thread = myThread(i, papers, filename)
        thread_list.append(thread)
    for thread in thread_list:
        thread.start()
    for thread in thread_list:
        thread.join()


if __name__ == '__main__':
    filename = './paper.csv'
    f = open(filename, 'r')
    reader = csv.reader(f)
    total_paper_list = [row[1] for row in reader]
    begin = 9000
    for i in range(10):
        begin = i*1000 + begin
        search(4, begin, 1000, total_paper_list)
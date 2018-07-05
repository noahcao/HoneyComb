from selenium import webdriver
import progressbar
import  csv
import time
from bs4 import BeautifulSoup as bs
import re

url = "https://ieeexplore.ieee.org/document/6796673/citations?tabFilter=papers"

driver = webdriver.Firefox()

driver.get(url)
time.sleep(3)

driver.find_element_by_xpath("(//button[@type='button'])[3]").click()

source = driver.page_source

soup = bs(source, "html.parser")

# result = soup.select('#citations-section-container')

citations_div = soup.select('.stats-citations-publisher-ieee')
citations_div = citations_div[0]

citations = citations_div.select('.description')

file = open('html.txt', 'w', encoding='utf-8')

file.write(str(citations))

print(citations)

for citation in citations:
    citation = str(citation)
    begin = citation.find('Text">')
    end = citation.find('</div>')
    print('------------')
    print(citation[begin+6:end])

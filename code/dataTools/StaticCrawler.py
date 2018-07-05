import urllib3

query = 'graph'
url = 'http://scholar.google.com/scholar?hl=en&q=' + query + '&btnG=&as_sdt=1%2C5&as_sdtp='

url2 = 'https://scholar.google.com/scholar?hl=zh-CN&as_sdt=0%2C5&q=deep+learning&btnG='

header = {'Host': 'scholar.google.com',
'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; rv:26.0) Gecko/20100101 Firefox/26.0',
'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8',
'Accept-Encoding': 'gzip, deflate',
'Connection': 'keep-alive'}

http = urllib3.PoolManager()

ret = http.request('GET', url)

print(ret.status)
print(ret.data)
#-*- coding:utf-8 -*-

# 用来将爬取的论文数据插入到数据库中所需要的脚本文件，最后会生成一个对应相应操作的sql文件
# 以支持之后的复用和在不同机器上的使用

import csv
import os
import pymysql

def connectdb(database, username, password, url='localhost'):
    print("尝试连接数据库(" + database + ')...')
    db = pymysql.connect(url, username, password, database, charset='utf8')
    print('数据库(' + database + ')已连接')
    return db

# 建立和数据库的连接并且申请一个操作数据库需要的游标
db = connectdb('honeycomb', 'root', 'caojinkun123')
cursor = db.cursor()

def get_paperid(title):
    # 对应给定了title的paper，返回他在paper这个数据表中的ID
    sql = "SELECT id FROM paper WHERE title = \'%s\'" % (pymysql.escape_string(str(title)))
    cursor.execute(sql)
    id = cursor.fetchone()
    id = id[0]
    return id

def check_indatabase(item, table, key):
    # 用于检查一个元素是否已经在数据库中了
    # item是这个元素的一个superkey， table指定了在哪一个表中尝试查找这个数据
    # key是对应的查询的superkey的名字
    sql = ""
    sql = "SELECT * from %s WHERE %s = \"%s\"" % (table, key, pymysql.escape_string(str(item))) # pymysql.escape_string用来处理item的字符串中有单引号或者双引号的情况
    res = cursor.execute(sql)
    if int(res) == 0:
        return False
    else:
        return True

def check_reference_indb(title, citation):
    # 检查一堆引用关系是否已经存在于当前的数据库中了
    paper_id = get_paperid(title)
    citation_id = get_paperid(citation)
    sql = "SELECT * FROM paper_paper_reference WHERE paper_id = %d and citation_id = %d" % (int(paper_id), int(citation_id))
    res = cursor.execute(sql)
    if res == 0:
        return False
    else:
        return True

def insert_paper(title, author_list, year, cited, citation_list):
    # 把一篇论文的数据插入到数据库中，其中title和year直接插入到paper表中
    # citation_list和author_list插入到author_paper和paper_paper_reference
    # 这两个联系集生成的table中，同时，在author这个table中尝试插入对应的author
    # Note: 在尝试调用这个函数对一篇paper的信息进行插入之前，应该已经确认其当前不在数据库中
    sql = "INSERT INTO paper (title, year, cited) VALUES (\'%s\', %d, %d)" % (pymysql.escape_string(str(title)), int(year), int(cited))
    cursor.execute(sql)
    db.commit()
    id = get_paperid(title)
    for author in author_list:
        if not check_indatabase(author, 'author', 'name'):
            # 这个作者还没有被记录在了author这个表中
            sql_insertauthor = "INSERT INTO author (name) VALUES (\'%s\')" % pymysql.escape_string(author)
            cursor.execute(sql_insertauthor)
            db.commit()
        # 获取表中的author对应的id，和paper的id一同对author_reference表进行填充
        getauthorid = "SELECT id FROM author WHERE name = \'%s\'" % pymysql.escape_string(author)
        cursor.execute(getauthorid)
        authorid = cursor.fetchone()
        authorid = authorid[0]
        sql_insertref = "INSERT INTO author_paper (author_id, paper_id) VALUES (%d, %d) " % (int(authorid), int(id))
        cursor.execute(sql_insertref)
        db.commit()

def insert_reference(title, citation, citation_not_recorded=False):
    # 用来插入一对论文的引用关系，当citation_not_recorded为true时，
    # 说明citation对应的文档还没有被记录在paper这个数据表中，还需要进行额外的操作进行添加
    if citation_not_recorded:
        # 首先把引用论文citation添加到paper这个table中，否则其找不到对应的ID
        sql = "INSERT INTO paper (title) VALUES (\'%s\')" % (pymysql.escape_string(citation))
        #print("add the new citation: %s" % citation)
        cursor.execute(sql)
        db.commit()
    if check_reference_indb(title, citation):
        # 想要插入的元组已经存在于数据库之中了
        return
    #print(citation_not_recorded)
    id_paper = get_paperid(title)
    id_citation = get_paperid(citation)
    sql = "INSERT INTO paper_paper_reference (paper_id, citation_id) VALUES (%d, %d)" % (int(id_paper), int(id_citation))
    #print(sql)
    cursor.execute(sql)
    db.commit()

def insert_all(data_path):
    # 用于向某个函数中插入所有path下文件的函数，理想情况下，csv文件的第一行应该
    # 说明了每一列的名称，而这个名称应该是和对应的数据库中的列名相同的
    data_files = os.listdir(data_path)
    idx = 0
    for filename in data_files:
        try:
            filename = data_path + filename
            csvfile = open(filename, 'r', encoding='utf-8')
            reader = csv.reader(csvfile)
            for item in reader:
                if len(item) < 3:
                    continue
                title = item[0]
                authors = item[1]
                year = item[2]
                cited = item[3]
                citation_list = item[4: len(item)]
                authors = authors.split(',')
                # 对于作者名字中的空格进行删除，同时排除掉一些实际为空的作者名元素
                real_authors = []

                for i in range(len(authors)):
                    authorname = authors[i].replace(' ', '')
                    if len(authorname) > 1:
                        real_authors.append(authorname)

                if check_indatabase(title, 'paper', 'title'):
                    # 这篇论文之前已经被插入到数据库中了
                    continue
                else:
                    insert_paper(title, real_authors, year, cited, citation_list)
                    db.commit()

                for citation in citation_list:
                    if check_indatabase(citation, 'paper', 'title'):
                        # 如果这篇citation对应的论文也在现在的paper表中的话，插入这个引用关系
                        insert_reference(title, citation)
                    else:
                        # 这个citation对应的论文没有在paper的话，不但添加这个引用关系，而且将
                        # citation对应的论文添加到paper这个数据表中
                        insert_reference(title, citation, True)
        except:
            continue

if __name__ == '__main__':
    data_path = './data/'    # data where the csv files placed
    insert_all(data_path)
    sql = "SELECT * from paper WHERE id = 11"
    in_database = check_indatabase(1, 'paper', 'id')
    print(in_database)
    db.commit()



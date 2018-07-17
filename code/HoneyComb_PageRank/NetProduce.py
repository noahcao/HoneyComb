# -*- coding: utf-8 -*-
# 依据PageRank算法构建论文网络图
# 网络图元素中包括论文、作者、相关代码

# 需要对pagerank算法做一定调整，考虑无箭头指向关系的处理
# 第一种考虑：在论文与作者间增加双向箭头
# 共同撰写作者间增加双向箭头
# 存在问题：作者比重可能会过于大？

from pygraph.classes.digraph import digraph
import pymysql

class NetNode:
    __doc__ = '''代表网络图中的节点'''

    def __init__(self):
        self.type = 0 # 0表示unknown 1表示论文 2表示作者 3表示代码
        self.name = ""
    
    def setType(self,newType):
        self.type = newType

    def setName(self,newName):
        self.name = newName
    def getName(self):
        return self.name
    def getType(self):
        return self.type

class HoneyCombIterator:
    __doc__ = '''计算迭代中构建网络图的PR值'''

    def __init__(self, dg):
        self.damping_factor = 0.85 # 阻尼系数，表示PageRank中用户停留在动迁界面的概率
        self.max_iterations = 100 # 最大迭代次数
        self.min_delta = 0.00001 # 确定迭代是否结束的参数,即ϵ
        self.graph = dg

    def construct(self):
        #先将图中没有出链的节点改为对所有节点都有出链
        try:
            print("start construct!")
            for node in self.graph.nodes():
                if len(self.graph.neighbors(node)) == 0:
                    print("ops!no neighbor!")
                    print(node)
                    for neighbor_node in self.graph.nodes():
                        digraph.add_edge(self.graph,(node,neighbor_node))
                
            print("edge append completed!")
        
        except Exception as e:
            raise e

        nodes = self.graph.nodes()
        graph_size = len(nodes)

        if graph_size == 0:
            return {}
        page_rank = dict.fromkeys(nodes, 1.0 / graph_size) #给每个节点赋予初始PR值
        damping_value = (1.0 - self.damping_factor) / graph_size #公式中(1-α)/N部分

        flag = False
        for i in range(self.max_iterations):
            change = 0 #每次迭代PR值变化
            for node in nodes:
                newPR  = 0 #节点每次迭代后得到的新PR值
                for incident_node in self.graph.incidents(node): #遍历所有“入射”节点
                    newPR += self.damping_factor * (page_rank[incident_node] / len(self.graph.neighbors(incident_node)))
                newPR += damping_value
                change += abs(newPR - page_rank[node]) #节点PR值绝对值变化
                page_rank[node] = newPR

            print("This is N0.%d iteration" %(i+1))
            print(page_rank)

            if change < self.min_delta:
                flag = True
                print("finished in %d iteration!" %(i+1))
                break
        
        if not(flag):
            print("finished out of 100 iterations!")
        return page_rank

if __name__ == '__main__':
    #此处连接数据库
    #将所有论文和作者作为节点插入
    #将所有的论文与作者关系以及论文引用关系作为边加入
    #不同作者共写一篇论文（待解决）

    dg = digraph()

    db =pymysql.connect(host="localhost",user="root",password="5121039",db="honeycomb",port=3306)

    cur = db.cursor()

    paper_sql = "select * from paper"
    try:
        cur.execute(paper_sql)
        results = cur.fetchall()

        for row in results:
            # node = NetNode()
            # node.setName(row[0])
            # node.setType(1)
            dg.add_node("p"+str(row[0]))
    except Exception as e:
        raise e

    cur.close()

    print ("paper finised!")

    cur = db.cursor()

    author_sql = "select * from author"
    try:
        cur.execute(author_sql)
        results = cur.fetchall()

        for row in results:
            # node = NetNode()
            # node.setName(row[0])
            # node.setType(2)
            dg.add_node("a"+str(row[0]))
    except Exception as e:
        raise e

    print ("author finished!")
    allnode = dg.nodes()
    firstnode = allnode[0]
    print (firstnode)

    cur.close()
    cur = db.cursor()

    count = 0
    paper_reference_sql = "select * from paper_paper_reference"
    try:
        cur.execute(paper_reference_sql)
        results = cur.fetchall()

        for row in results:
            outNode = "p" + str(row[1])
            inNode = "p" + str(row[0]) 
            # outNode = NetNode()
            # outNode.setName(row[1])
            # outNode.setType(1)
            # inNode = NetNode()
            # inNode.setName(row[0])
            # inNode.setType(1)
            # if(outNode in allnode):
            #     print("1")
            #     if(inNode in allnode):
            #         print("2")
            #         dg.add_edge((outNode,inNode))
            #         count += 1
            #         print(count)
            # else
            # flag1 = 0
            # flag2 = 0

            # if(outNode in allnode):
            #     flag1 == 1
            #     print("in!")

            # for singlenode in allnode:
            #     if(inNode.getName() == singlenode.getName() and inNode.getType() == singlenode.getType()) :
            #         flag2 = 1
            #         break

            if(dg.has_node(outNode) and dg.has_node(inNode)):
                dg.add_edge((outNode,inNode))
                count += 1
            else:
                print ("bad edge")
    except Exception as e:
        raise e

    print (count)

    cur.close()
    cur = db.cursor()
    count = 0

    author_paper_sql = "select * from author_paper"
    try:
        cur.execute(author_paper_sql)
        results = cur.fetchall()
        for row in results:
            paperNode = "p" + str(row[1])
            authorNode = "a" + str(row[0])
            # paperNode = NetNode()
            # paperNode.setName(row[1])
            # paperNode.setType(1)
            # authorNode = NetNode()
            # authorNode.setName(row[0])
            # authorNode.setType(2)
            if(dg.has_node(paperNode) and dg.has_node(authorNode)):
                dg.add_edge((paperNode,authorNode))
                count += 1
            else:
                print("bad edge again")

    except Exception as e:
        raise e
    cur.close()
    print (count) 
    db.close()

    honey_comb =HoneyCombIterator(dg)
    honey_comb_net = honey_comb.construct()

    
    print("finished!")

    # a = NetNode()
    # b = NetNode()
    # c = NetNode()
    # d = NetNode()
    # e = NetNode()

    # a.setName("A")
    # a.setType(1)
    # b.setName("B")
    # b.setType(1)
    # c.setName("C")
    # c.setType(1)
    # d.setName("D")
    # d.setType(1)
    # e.setName("E")
    # e.setType(1)

    # dg.add_nodes([a,b,c,d,e])

    # dg.add_edge((a,b))
    # dg.add_edge((a,c))
    # dg.add_edge((a,d))
    # dg.add_edge((b,d))
    # dg.add_edge((c,e))
    # dg.add_edge((d,e))
    # dg.add_edge((b,e))
    # dg.add_edge((e,a))

    # honey_comb = HoneyCombIterator(dg)
    # honey_comb_net = honey_comb.construct()

    # print("The final page rank is\n",honey_comb_net )


        
        

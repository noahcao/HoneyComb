# -*- coding: utf-8 -*-
# 依据PageRank算法构建论文网络图
# 网络图元素中包括论文、作者、相关代码

#

from pygraph.classes.digraph import digraph
import json
import sys


class NetNode:
    __doc__ = '''代表网络图中的节点'''

    def __init__(self):
        self.type = 0  # 0表示unknown 1表示论文 2表示作者 3表示代码
        self.name = ""

    def setType(self, newType):
        self.type = newType

    def setName(self, newName):
        self.name = newName

    def getName(self):
        return self.name

    def getType(self):
        return self.type


class HoneyCombIterator:
    __doc__ = '''计算迭代中构建网络图的PR值'''

    def __init__(self, dg):
        self.damping_factor = 0.85  # 阻尼系数，表示PageRank中用户停留在动迁界面的概率
        self.max_iterations = 100  # 最大迭代次数
        self.min_delta = 0.00001  # 确定迭代是否结束的参数,即ϵ
        self.graph = dg

    def construct(self):
        # 先将图中没有出链的节点改为对所有节点都有出链
        try:
            # print("start construct!")
            for node in self.graph.nodes():
                if len(self.graph.neighbors(node)) == 0:
                    # print("ops!no neighbor!")
                    print(node)
                    for neighbor_node in self.graph.nodes():
                        digraph.add_edge(self.graph, (node, neighbor_node))

            # print("edge append completed!")

        except Exception as e:
            raise e

        nodes = self.graph.nodes()
        graph_size = len(nodes)

        if graph_size == 0:
            return {}
        page_rank = dict.fromkeys(nodes, 1.0 / graph_size)  # 给每个节点赋予初始PR值
        damping_value = (1.0 - self.damping_factor) / \
            graph_size  # 公式中(1-α)/N部分

        flag = False
        for i in range(self.max_iterations):
            change = 0  # 每次迭代PR值变化
            for node in nodes:
                newPR = 0  # 节点每次迭代后得到的新PR值
                for incident_node in self.graph.incidents(node):  # 遍历所有“入射”节点
                    newPR += self.damping_factor * \
                        (page_rank[incident_node] /
                         len(self.graph.neighbors(incident_node)))
                newPR += damping_value
                change += abs(newPR - page_rank[node])  # 节点PR值绝对值变化
                page_rank[node] = newPR

            # print("This is N0.%d iteration" % (i+1))
            # print(page_rank)

            if change < self.min_delta:
                flag = True
                # print("finished in %d iteration!" % (i+1))
                break

        # if not(flag):
            # print("finished out of 100 iterations!")
        return page_rank


if __name__ == '__main__':

    dg = digraph()

    paper_json = sys.argv[1]
    author_json = sys.argv[2]

    # 下列数据测试可行
    # paper_json= '{"papers": [{"id": "1", "authors": ["1","3"], "reference": ["2"] },{"id": "2", "authors": ["2"],"reference": []}]}'
    # author_json= '{"authors":["1","2","3"]}'

    papers = json.loads(paper_json)
    authors = json.loads(author_json)

    paper_list = papers['papers']
    author_list = authors['authors']

    #添加节点
    for paper in paper_list:    
        dg.add_node('p'+paper['id'])

    for author_id in author_list:
        dg.add_node('a'+author_id)

    #添加边
    for paper in paper_list:
        for author in paper['authors']:
            dg.add_edge(('p'+paper['id'],'a'+author))
        for paper_refer in paper['reference']:
            dg.add_edge(('p'+paper['id'],'p'+paper_refer))
        
    paper_pr = {'paper':[]}
    author_pr = {'author':[]}

    honey_comb = HoneyCombIterator(dg)
    honey_comb_net = honey_comb.construct()

    for net_node in honey_comb_net:
        if(net_node[0] == 'p'):
            newpaper = {}
            newpaper['paperid'] = int(net_node[1:len(net_node)])
            newpaper['pagerank'] = honey_comb_net[net_node]
            # newpaper[net_node[1:len(net_node)]] = honey_comb_net[net_node]
            paper_pr['paper'].append(newpaper)
        else:
            newauthor = {}
            newauthor['authorid'] = int(net_node[1:len(net_node)])
            newauthor['pagerank'] = honey_comb_net[net_node]
            # newauthor[net_node[1:len(net_node)]] = honey_comb_net[net_node]
            author_pr['author'].append(newauthor)


    print(paper_pr)
    print(author_pr)

# -*- coding: utf-8 -*-
# 依据PageRank算法构建论文网络图
# 网络图元素中包括论文、作者、相关代码

from pygraph.classes.digraph import digraph

class NetNode:
    __doc__ = '''代表网络图中的节点'''

    def __init__(self):
        self.type = 0 # 0表示unknown 1表示论文 2表示作者 3表示代码
        self.name = ""
    
    def setType(self,newType):
        self.type = newType

    def setName(self,newName):
        self.name = newName

class HoneyCombIterator:
    __doc__ = '''计算迭代中构建网络图的PR值'''

    def __init__(self, dg):
        self.damping_factor = 0.85 # 阻尼系数，表示PageRank中用户停留在动迁界面的概率
        self.max_iterations = 100 # 最大迭代次数
        self.min_delta = 0.00001 # 确定迭代是否结束的参数,即ϵ
        self.graph = dg

    def construct(self):
        #先将图中没有出链的节点改为对所有节点都有出链
        for node in self.graph.nodes():
            if len(self.graph.neighbors(node)) == 0:
                for neighbor_node in self.graph.nodes():
                    digraph.add_edge(self.graph,(node,neighbor_node))

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
    dg = digraph()

    a = NetNode()
    b = NetNode()
    c = NetNode()
    d = NetNode()
    e = NetNode()

    a.setName("A")
    a.setType(1)
    b.setName("B")
    b.setType(1)
    c.setName("C")
    c.setType(1)
    d.setName("D")
    d.setType(1)
    e.setName("E")
    e.setType(1)

    dg.add_nodes([a,b,c,d,e])

    dg.add_edge((a,b))
    dg.add_edge((a,c))
    dg.add_edge((a,d))
    dg.add_edge((b,d))
    dg.add_edge((c,e))
    dg.add_edge((d,e))
    dg.add_edge((b,e))
    dg.add_edge((e,a))

    honey_comb = HoneyCombIterator(dg)
    honey_comb_net = honey_comb.construct()

    print("The final page rank is\n",honey_comb_net )


        
        

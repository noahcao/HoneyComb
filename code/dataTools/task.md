# HoneyComb数据获取工具说明

为了支持honeycomb的项目需要，对于互联网上的论文数据的整理是一个关键的工作。为了收到论文的metadata的数据，我尝试了如下的多种途径，对于不同的论文数据库或者接口进行数据的获取，相关的简介和内容说明如下:

* GoogleScholar: 经过反复的尝试和资料查阅，googlescholar因为其强大的bot检测和反爬虫技术，让我的尝试都以失败告终了，而且其不开放对外的数据api，所以现阶段并没有办法从google scholar获取数据；
* CrossRef: [Crossref](https://www.crossref.org/)是一个对外开放的学术论文数据的api获取接口，与其可以一同使用的还有基于R或者Python的代码库[havanero](https://github.com/sckott/habanero)，但是经过反复尝试，发现这套工具无法获得一个论文对应的引用论文的列表，而只能获得被引用的数量，如果需要获得引用其的论文列表，还是需要其他的手段进行组合工作；
* Web of Science：一个汤森路透提供的论文数据库，但是需要特定机构的访问权限，在得到了其提供的关于谋篇论文的metadata后，发现其对应的引用论文数据不是公开的，需要进一步处理才可能获取，这也是下一步的工作内容。
* IEEE Xplore：IEEE Xplore对外提供了获取数据的api接口，但是这个接口的申请需要一个organization的背景和声明api被使用的application的详情，截止7.5早上九点，我于3号提交的申请仍旧在等待被处理的队列中。因此我现在转向使用爬虫对xplore进行获取，第一步尝试是利用requests + urllib的python技术栈，但是发现其论文详情等页面都是由js动态生成的，所以上述的静态抓取没有成功。不过现阶段，利用selenium + beautifulsoup + re的工具链，我已经实现了对其部分页面的抓取和解析，获取了一定数量的论文数据，已经得到的数据包括论文的title,author,year,citation等，并且已经可以抓取引用其的论文展示页面，下一步会尝试对其进行解析，进而获取引用一个论文的完整列表。
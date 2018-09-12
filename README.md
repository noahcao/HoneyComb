# HoneyComb

A web (and optionally mobile application) project to build a reliable visualized network system for the reference dependencies among public academic papers. you can visite it online through [HoneyComb: a powerful academic community](combsite.site).

## Major Features

Not same as some existing products aiming to researchers, such as [Medenley](https://www.mendeley.com/), [Google Scholar](https://scholar.google.com.hk) , though with some overlap on functions, **HoneyComb** concentrate to provide multi-functional service including:

* Visualization of academic data, paper and author information is supported now.
* Retrieval of academic data based on a built-in database.
* Community for users to share anything, but only thoserelated to valuable academic stuff are encouraged.
* Customized private collection and definition of academic networks.

### Visualization of academic data

A network can be built with a selected center node. Nodes in such a network can be either a academic paper or an author of a paper (may also be a insitution, a conference/journal press or any other meaningful roles for academic literature sharing). All nodes are rendered with a **weight**, proportional to their size, which is calculated through many factors, such as the citation or publishment year of a paper or h-index of a researcher.

Based on such a visualized academic networks, further dynamic expanding or filtering are supported. And abstract introduction is also provided. We look forward to do anxillary work through such visual module during academic retrieval and collection of researchers, especally those newbees who feel difficult to find top importance academic terms among a big volume of database.

### Academic information retrieval

A search engine is provided in our site and excepted to be the entrance of a use cycle. Such a module is much similar with Google Scholar, but, to be honest, much weaker. But we wish it can be integrated perfectly with other modules in our project. For instance, if a **seach -> star -> network it** workflow is supported, we believe we thus provide a useful feature for users.

### Community

In community, users can share anything, but it's not the [Reddit](www.reddit.com) or [Baidu Tieba](https://tieba.baidu.com/index.html?traceid=), such stuff are encouraged to turn out here:

* code / experiment to reproduce some works in academic literature;
* report of issues for a paper, an experiment result, former topic ...
* sharing of experience; find some new friends with similar interest; feed back of our site...

Community consist of many panels, each of which is contructed under a **'panel - post - comment'** hierarchy, which is inspried by **issue** block of [github](www.github.com). Panel with a title defines the theme, and main discussion is in posts. Comments can be attached to both posts or other comments.

### Customized space

Customized space support the **history**, **trend**, and, the most special, **self-defined combs**. Users can build a network containing any information with free connections. Self-defined comb is designed mainly for the requirement that many researchers want to trace their reference or research footprint in a self-controling way. Different from totally text-format organization like that in Mendeley or traditional file systems, such module bring a more powerful experience of information collection and relationship definition. 

### TODO

Many features are to be developped or to be optimized:

* Add more abundant community functions, including but not limited to **Share**, **Star**, **In-line media content support**, **Vote and Veto**.
* Augement the volume of paper data in local dataset. Till now, all concluded paper metadata is crawled from [IEEE Xplore](https://ieeexplore.ieee.org/), we look forward to an extension on that.
* Combine the **community**, **data retrieval** and **private space** modules, for example, the nodes in users' self-defined networks can be simply paper among search results or a stared panel in community.
* Private in-time chatting system between users (this feature raise some disputes, and may be not further a candidate in the future).
* Enhance security and protection on sensitive information.
* **ANY OTHER GOOD IDEAS!**

## Contributors

**HoneyComb** is originally developped by [Jinkun Cao](https://github.com/noahcao), [Clive2312](https://github.com/Clive2312), [Mkk](https://github.com/WhirlFourEye) and [XPandora](https://github.com/XPandora). 

Feel absolutely free for any contribution or suggestion to this project. And contribution can be done through either fork workflow or simply to new branches. Online version of **HoneyComb** is always the latest stable version under ther *master* branch.

## License

This project is partly outsourced, excepted for the built-in dataset and user information. Considering the capacity of us four original developers who still are undergraduates, to bring a better and truly useful HoneyComb, all content in this project can be free distributed for non-commercial use.
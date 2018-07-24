<template>
  <div>
    <svg width='1000' height='600'></svg>
    <div class='tooltip'>
      <div v-if='unselected' class='tip'>
        <p>Detailed Information</p>
        <p>Here!</p>
        <span class='glyphicon glyphicon-list-alt'></span>
      </div>
      <div v-else-if='ispaper'>
        <p class='title'>{{paper.title}}</p>
        <p class='year'>year: {{paper.year}}</p>
      </div>
      <div v-else>
        <p class='title'>{{author.name}}</p>
      </div>
      <div v-if='selected'>
        <hr class='tooltip-hr'></hr>
        <div v-if='ispaper'>
          <p class='abstract'>{{paper.abstract}}</p>
        </div>
        <div v-else>
          <p class='authorinfo'>Publications:</p>
          <ul>
            <li v-for='item in author.publication'>
              {{item.title}}
            </li>
          </ul>
          <p class='authorinfo'>Co-workers:</p>
          <ul>
            <li v-for='item in author.co_author'>
              {{item.name}}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import * as d3 from 'd3'
// var data = {
//   nodes: [
//     { id: 'mk', level: 1, type: 'paper', title: 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels', year: '2007', abstract: 'Studies the performance advantage offered by the wideband multipath RAKE structure receiver in a cellular radio direct sequence code division multiple access system (CDMA). The base to mobile link is modeled as a Nakagami fading frequency selective channel. The performance of a RAKE structure receiver employing coherent reception with maximal ratio combining in a frequency selective channel is analyzed and compared with the flat fading case. The degradation in the performance of the receiver as a result of imperfect channel estimation is also studied.', pagerank: 0.1 },
//     { id: 'mk1', level: 2, type: 'paper', title: 'sth', year: '2017', abstract: 'this is abstract', pagerank: 0.2 },
//     { id: 'mk2', level: 3, type: 'paper', title: 'sth', year: '1917', abstract: 'this is abstract', pagerank: 0.12 },
//     { id: 'mk3', level: 3, type: 'paper', title: 'sth', year: '2003', abstract: 'this is abstract', pagerank: 0.02 },
//     { id: 'zjh', level: 2, type: 'paper', title: 'sth', year: '2017', abstract: 'this is abstract', pagerank: 0.05 },
//     { id: 'zjh1', level: 3, type: 'paper', title: 'sth', year: '1983', abstract: 'this is abstract', pagerank: 0.11 },
//     { id: 'zjh2', level: 3, type: 'paper', title: 'sth', year: '2007', abstract: 'this is abstract', pagerank: 0.01 },
//     { id: 'zjh3', level: 2, type: 'paper', title: 'sth', year: '2001', abstract: 'this is abstract', pagerank: 0.22 },
//     { id: 'cjk', level: 3, type: 'author', name: 'I. M. Salama', publication: [{ title: 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels' }, { title: 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels' }], co_author: [{ name: 'author1' }, { name: 'author2' }], pagerank: 0.01 },
//     { id: 'cjk1', level: 4, type: 'author', name: 'author!', publication: [{ title: 'publication1' }, { title: 'publication2' }], co_author: [{ name: 'author1' }, { name: 'author2' }], pagerank: 0.16 }
//   ],
//   links: [
//     { source: 'mk', target: 'mk1', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'mk1', target: 'mk2', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'mk3', target: 'mk1', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'zjh', target: 'mk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'zjh', target: 'zjh1', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'zjh', target: 'zjh2', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'zjh3', target: 'mk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
//     { source: 'zjh3', target: 'cjk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'author' },
//     { source: 'cjk1', target: 'cjk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'author', targettype: 'author' }
//   ]
// }

var data = {
  nodes: [],
  links: []
}

export default {
  name: 'd3-force',
  data () {
    return {
      // nodes: data.nodes,
      // links: data.links,
      modeldata: {},
      nodes: [],
      links: [],
      unselected: true,
      selected: false,
      ispaper: true,
      totalPR: 1,
      selectLevel: 4,
      chooseall: true,
      paper: {
        title: 'title',
        year: '2018',
        abstract: 'paper abstract'
      },
      author: {
        name: 'name',
        publication: ['paper1', 'paper2'],
        co_author: ['co_author1', 'co_author2']
      }
    }
  },
  methods: {
    print_id: function (id) {
      console.log(id)
    }
  },
  created () {
    this.$http.post('/graphdata', { id: 37819, hierarchyLimit: 4 })
      .then((res) => {
        if (res.data.paper !== null) {
          this.modeldata = res.data
        } else {
          alert('error!')
        }
      })

    var papersList = this.modeldata.paper
    var authorList = this.modeldata.author

    for (var i = 0; i < papersList.length; i++) {
      var temppaper = papersList[i]
      var papernode = {}
      papernode.id = 'p' + temppaper.paperid
      papernode.level = temppaper.level
      papernode.type = 'paper'
      papernode.pagerank = temppaper.pagerank
      papernode.abstract = ''
      papernode.year = ''
      data.nodes.push(papernode)

      var tempPapertoAuthor = temppaper.authors
      var tempPaperRefer = temppaper.reference

      for (var j = 0; j < tempPapertoAuthor.length; j++) {
        var paperlink = {}
        paperlink.source = 'p' + temppaper.paperid
        paperlink.target = 'a' + tempPapertoAuthor[j]
        paperlink.sourcetype = 'paper'
        paperlink.targettype = 'author'
        data.links.push(paperlink)
      }

      for (var j = 0; j < tempPaperRefer.length; j++) {
        var paperlink = {}
        paperlink.source = 'p' + temppaper.paperid
        paperlink.target = 'a' + tempPaperRefer[j]
        paperlink.sourcetype = 'paper'
        paperlink.targettype = 'author'
        data.links.push(paperlink)
      }
    }

    for (var i = 0; i < authorList.length; i++) {
      var tempauthor = authorList[i]
      var authornode = {}
      authornode.id = 'a' + tempauthor.authorid
      authornode.type = 'author'
      authornode.level = tempauthor.level
      authornode.pagerank = tempauthor.pagerank
      data.nodes.push(authornode)
    }
  },
  mounted () {
    let that = this
    var zoom = d3
      .zoom()
      .scaleExtent([-5, 2])
      .on('zoom', zoomed)

    var color = d3.scaleOrdinal(d3.schemeCategory10)

    var svg = d3.select('svg'),
      width = +svg.attr('width'),
      height = +svg.attr('height')

    svg.call(zoom)

    function zoomed () {
      svg.selectAll('g').attr('transform', d3.event.transform)
    }
    // 自定义引力
    var repelForce = d3
      .forceManyBody()
      .strength(-500)
      .distanceMax(250)
    // 自定义斥力

    var simulation = d3
      .forceSimulation()
      .force(
        'link',
        d3.forceLink().id(function (d) {
          return d.id
        })
      )
      .force('center', d3.forceCenter(width / 2, height / 2))
      // .force('attractForce',attractForce)
      .force('repelForce', repelForce)

    simulation.nodes(data.nodes).on('tick', ticked)

    simulation.force('link').links(data.links)

    var svgLink = svg
      .append('g')
      .attr('class', 'links')
      .selectAll('line')
      .data(data.links)
      .enter()
      .append('line')
      .attr('stroke-width', function (d) {
        return Math.sqrt(d.value)
      })

    var svgNode = svg
      .append('g')
      .attr('class', 'nodes')
      .selectAll('circle')
      .data(data.nodes)
      .enter()
      .append('circle')
      .attr('r', function (d, i) {
        return Math.sqrt(d.pagerank / that.totalPR * 2000)
      })
      .attr('fill', function (d, i) {
        if (d.type === 'author') {
          return '#2c3e50'
        }
        else {
          return color((d.year / 5) % 10)
        }
      })
      .call(
        d3
          .drag()
          .on('start', dragstarted)
          .on('drag', dragged)
          .on('end', dragended)
      )
      .on('click', function (d) {
        that.print_id(d.id)
      })
      .on('mouseover', function (d, i) {
        if (d.type === 'paper') {
          that.paper.title = d.title
          that.paper.year = d.year
          that.paper.abstract = d.abstract
          that.unselected = false
          that.selected = true
          that.ispaper = true
        }
        else {
          that.author.name = d.name
          that.author.publication = d.publication
          that.author.co_author = d.co_author
          that.unselected = false
          that.selected = true
          that.ispaper = false
        }
      })
      .on('mouseout', function (d) {
        // tooltip.style('display', 'none')
      })

    /* var text = svg
      .append('g')
      .attr('class','texts')
      .selectAll('text')
      .data(data.nodes)
      .enter()
      .append('text')
      .attr('font-family', 'sans-serif')
      .attr('font-size', '11px')
      .attr('fill', 'black')
      .attr('fill-opacity', '0.0') */

    function ticked () {
      svgLink
        .attr('x1', function (d) {
          return d.source.x
        })
        .attr('y1', function (d) {
          return d.source.y
        })
        .attr('x2', function (d) {
          return d.target.x
        })
        .attr('y2', function (d) {
          return d.target.y
        })

      svgNode
        .attr('cx', function (d) {
          return d.x
        })
        .attr('cy', function (d) {
          return d.y
        })

      /* text
        .text(function(d) {
          return d.id;
        })
        .attr('x', function(d, i) {
          return d.x;
        })
        .attr('y', function(d) {
          return d.y;
        })
        .attr('dx', '-3.5')
        .attr('dy', '20'); */
    }
    function dragstarted (d) {
      // tooltip.style('display','none')
      if (!d3.event.active) simulation.alphaTarget(0.3).restart()
      d.fx = d.x
      d.fy = d.y
    }

    function dragged (d) {
      d.fx = d3.event.x
      d.fy = d3.event.y
      // console.log('dragged')
    }

    function dragended (d) {
      // console.log('dragended')
      if (!d3.event.active) simulation.alphaTarget(0)
      d.fx = null
      d.fy = null
    }

    this.bus.$on('SelectPaper', function (message) {
      that.chooseall = false
      var newnodes = []
      var newlinks = []

      for (var i = 0; i < data.nodes.length; i++) {
        if (data.nodes[i].type === 'paper') {
          newnodes.push(data.nodes[i])
        }
      }

      for (var i = 0; i < data.links.length; i++) {
        if (
          data.links[i].source.type !== 'paper' ||
          data.links[i].target.type !== 'paper'
        ) {
          continue
        }
        else {
          newlinks.push(data.links[i])
        }
      }

      svgNode = svgNode.data(newnodes)
      svgLink = svgLink.data(newlinks)
      svgNode.exit().remove()
      svgLink.exit().remove()
    })

    this.bus.$on('SelectAll', function (message) {
      that.chooseall = true
      svgLink = svgLink.data(data.links, d => {
        return d.source.id + '-' + d.target.id
      })

      svgLink = svgLink
        .enter()
        .append('line')
        .attr('stroke-width', function (d) {
          return Math.sqrt(d.value)
        })
        .merge(svgLink)

      svgNode = svgNode
        .data(data.nodes, d => {
          return d.id
        })
        .enter()
        .append('circle')
        .attr('r', function (d, i) {
          return Math.sqrt(d.pagerank / that.totalPR * 2000)
        })
        .attr('fill', function (d, i) {
          if (d.type === 'author') {
            return '#2c3e50'
          }
          else {
            return color((d.year / 5) % 10)
          }
        })
        .merge(svgNode)
        .call(
          d3
            .drag()
            .on('start', dragstarted)
            .on('drag', dragged)
            .on('end', dragended)
        )
        .on('click', function (d) {
          that.print_id(d.id)
        })
        .on('mouseover', function (d, i) {
          if (d.type === 'paper') {
            that.paper.title = d.title
            that.paper.year = d.year
            that.paper.abstract = d.abstract
            that.unselected = false
            that.selected = true
            that.ispaper = true
          }
          else {
            that.author.name = d.name
            that.author.publication = d.publication
            that.author.co_author = d.co_author
            that.unselected = false
            that.selected = true
            that.ispaper = false
          }
        })
        .on('mouseout', function (d) {
          // tooltip.style('display', 'none')
        })
      simulation.nodes(data.nodes)
      simulation.force('link').links(data.links)
      simulation.alpha(1).restart()
    })

    this.bus.$on('SelectTwoLevel', function (message) {
      if (that.selectLevel > 2) {
        that.selectLevel = 2
        var newnodes = []
        var newlinks = []
        if (this.chooseall === true) {
          for (var i = 0; i < data.nodes.length; i++) {
            if (data.nodes[i].level <= 2) {
              newnodes.push(data.nodes[i])
            }
          }
          for (var i = 0; i < data.links.length; i++) {
            if (data.links[i].source.level <= 2 && data.links[i].target.level <= 2) {
              newlinks.push(data.links[i])
            }
          }
        }
        else {
          for (var i = 0; i < data.nodes.length; i++) {
            if (data.nodes[i].level <= 2 && data.nodes[i].type === 'paper') {
              newnodes.push(data.nodes[i])
            }
          }
          for (var i = 0; i < data.links.length; i++) {
            if (
              data.links[i].source.type === 'paper' &&
              data.links[i].target.type === 'paper' &&
              data.links[i].source.level <= 2 &&
              data.links[i].target.level <= 2
            ) {
              newlinks.push(data.links[i])
            }
          }
        }
        svgNode = svgNode.data(newnodes)
        svgLink = svgLink.data(newlinks)
        svgNode.exit().remove()
        svgLink.exit().remove()
      }
    })

    this.bus.$on('SelectThreeLevel', function (message) {
      if (that.selectLevel === 3) {
        return
      }
      var newnodes = []
      var newlinks = []
      if (this.chooseall === true) {
        for (var i = 0; i < data.nodes.length; i++) {
          if (data.nodes[i].level <= 3) {
            newnodes.push(data.nodes[i])
          }
        }
        for (var i = 0; i < data.links.length; i++) {
          if (data.links[i].source.level <= 3 && data.links[i].target.level <= 3) {
            newlinks.push(data.links[i])
          }
        }
      }
      else {
        for (var i = 0; i < data.nodes.length; i++) {
          if (data.nodes[i].level <= 3 && data.nodes[i].type === 'paper') {
            newnodes.push(data.nodes[i])
          }
        }
        for (var i = 0; i < data.links.length; i++) {
          if (
            data.links[i].source.type === 'paper' &&
            data.links[i].target.type === 'paper' &&
            data.links[i].source.level <= 3 &&
            data.links[i].target.level <= 3
          ) {
            newlinks.push(data.links[i])
          }
        }
      }
      if (that.selectLevel > 3) {
        svgNode = svgNode.data(newnodes)
        svgLink = svgLink.data(newlinks)
        svgNode.exit().remove()
        svgLink.exit().remove()
      }
      else {
        svgLink = svgLink.data(newlinks, d => {
          return d.source.id + '-' + d.target.id
        })

        svgLink = svgLink
          .enter()
          .append('line')
          .attr('stroke-width', function (d) {
            return Math.sqrt(d.value)
          })
          .merge(svgLink)

        svgNode = svgNode
          .data(newnodes, d => {
            return d.id
          })
          .enter()
          .append('circle')
          .attr('r', function (d, i) {
            return Math.sqrt(d.pagerank / that.totalPR * 2000)
          })
          .attr('fill', function (d, i) {
            if (d.type === 'author') {
              return '#2c3e50'
            }
            else {
              return color((d.year / 5) % 10)
            }
          })
          .merge(svgNode)
          .call(
            d3
              .drag()
              .on('start', dragstarted)
              .on('drag', dragged)
              .on('end', dragended)
          )
          .on('click', function (d) {
            that.print_id(d.id)
          })
          .on('mouseover', function (d, i) {
            if (d.type === 'paper') {
              that.paper.title = d.title
              that.paper.year = d.year
              that.paper.abstract = d.abstract
              that.unselected = false
              that.selected = true
              that.ispaper = true
            }
            else {
              that.author.name = d.name
              that.author.publication = d.publication
              that.author.co_author = d.co_author
              that.unselected = false
              that.selected = true
              that.ispaper = false
            }
          })
          .on('mouseout', function (d) {
            // tooltip.style('display', 'none')
          })
        simulation.nodes(newnodes)
        simulation.force('link').links(newlinks)
        simulation.alpha(1).restart()
      }
      that.selectLevel = 3
    })

    this.bus.$on('SelectFourLevel', function (message) {
      if (that.selectLevel < 4) {
        that.selectLevel = 4
        var newnodes = []
        var newlinks = []
        if (this.chooseall === true) {
          for (var i = 0; i < data.nodes.length; i++) {
            if (data.nodes[i].level <= 4) {
              newnodes.push(data.nodes[i])
            }
          }
          for (var i = 0; i < data.links.length; i++) {
            if (data.links[i].source.level <= 4 && data.links[i].target.level <= 4) {
              newlinks.push(data.links[i])
            }
          }
        }
        else {
          for (var i = 0; i < data.nodes.length; i++) {
            if (data.nodes[i].level <= 4 && data.nodes[i].type === 'paper') {
              newnodes.push(data.nodes[i])
            }
          }
          for (var i = 0; i < data.links.length; i++) {
            if (
              data.links[i].source.type === 'paper' &&
              data.links[i].target.type === 'paper' &&
              data.links[i].source.level <= 4 &&
              data.links[i].target.level <= 4
            ) {
              newlinks.push(data.links[i])
            }
          }
        }
        svgLink = svgLink.data(newlinks, d => {
          return d.source.id + '-' + d.target.id
        })

        svgLink = svgLink
          .enter()
          .append('line')
          .attr('stroke-width', function (d) {
            return Math.sqrt(d.value)
          })
          .merge(svgLink)

        svgNode = svgNode
          .data(newnodes, d => {
            return d.id
          })
          .enter()
          .append('circle')
          .attr('r', function (d, i) {
            return Math.sqrt(d.pagerank / that.totalPR * 2000)
          })
          .attr('fill', function (d, i) {
            if (d.type === 'author') {
              return '#2c3e50'
            }
            else {
              return color((d.year / 5) % 10)
            }
          })
          .merge(svgNode)
          .call(
            d3
              .drag()
              .on('start', dragstarted)
              .on('drag', dragged)
              .on('end', dragended)
          )
          .on('click', function (d) {
            that.print_id(d.id)
          })
          .on('mouseover', function (d, i) {
            if (d.type === 'paper') {
              that.paper.title = d.title
              that.paper.year = d.year
              that.paper.abstract = d.abstract
              that.unselected = false
              that.selected = true
              that.ispaper = true
            }
            else {
              that.author.name = d.name
              that.author.publication = d.publication
              that.author.co_author = d.co_author
              that.unselected = false
              that.selected = true
              that.ispaper = false
            }
          })
          .on('mouseout', function (d) {
            // tooltip.style('display', 'none')
          })
        simulation.nodes(newnodes)
        simulation.force('link').links(newlinks)
        simulation.alpha(1).restart()
      }
    })
  }
}
</script>

<style>
.links line {
  stroke: #999;
  stroke-opacity: 0.6;
}

.nodes circle {
  stroke: #fff;
  stroke-width: 1.5px;
  cursor: pointer;
}

.text {
  fill-opacity: 0;
}

.tooltip {
  position: absolute;
  top: 0px;
  right: 0px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  border-left: 1.5px solid #ddd;
  background: #eee;
  opacity: 1;
  color: #000;
  padding-left: 15px;
  padding-right: 10px;
  padding-top: 50px;
  padding-bottom: 20px;
  width: 300px;
  height: 630px;
  font-size: 15px;
  z-index: 120;
}

.tooltip p {
  font-size: 15px;
  text-align: center;
  padding: 0;
  margin: 0;
}

hr.tooltip-hr {
  color: #ddd;
  padding: 3px 0 0 0;
  margin: 3px 0 3px 0;
}

.tooltip .title {
  text-align: left;
  padding-left: 10px;
  margin-bottom: 10px;
  font-size: 20px;
  line-height: 24px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.tooltip .tip {
  text-align: center;
  margin-top: 50px;
}

.tooltip .tip p {
  font-size: 20px;
}

.tooltip .tip span {
  margin-top: 20px;
  font-size: 40px;
}
.tooltip .year {
  text-align: left;
  padding-left: 20px;
}

.tooltip .name {
  font-weight: bold;
}

.tooltip .abstract {
  padding-left: 5px;
  text-indent: 1em;
  text-align: left;
  position: relative;
  line-height: 20px;
  max-height: 400px;
  overflow: hidden;
}

.tooltip .authorinfo {
  text-align: left;
  padding-left: 15px;
}

.tooltip .abstract::after {
  position: absolute;
  bottom: 0;
  right: 0;
  padding-left: 40px;
  background: -webkit-linear-gradient(left, transparent, #eee 55%);
  background: -o-linear-gradient(right, transparent, #eee 55%);
  background: -moz-linear-gradient(right, transparent, #eee 55%);
  background: linear-gradient(to right, transparent, #eee 55%);
}
</style>

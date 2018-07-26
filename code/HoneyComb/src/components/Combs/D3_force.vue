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
          <!-- <p class='authorinfo'>Publications:</p>
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
          </ul> -->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import * as d3 from 'd3'
var data1 = {
  nodes: [
    { id: 'mk', level: 1, type: 'paper', title: 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels', year: '2007', abstract: 'Studies the performance advantage offered by the wideband multipath RAKE structure receiver in a cellular radio direct sequence code division multiple access system (CDMA). The base to mobile link is modeled as a Nakagami fading frequency selective channel. The performance of a RAKE structure receiver employing coherent reception with maximal ratio combining in a frequency selective channel is analyzed and compared with the flat fading case. The degradation in the performance of the receiver as a result of imperfect channel estimation is also studied.', pagerank: 0.1 },
    { id: 'mk1', level: 2, type: 'paper', title: 'sth', year: '2017', abstract: 'this is abstract', pagerank: 0.2 },
    { id: 'mk2', level: 3, type: 'paper', title: 'sth', year: '1917', abstract: 'this is abstract', pagerank: 0.12 },
    { id: 'mk3', level: 3, type: 'paper', title: 'sth', year: '2003', abstract: 'this is abstract', pagerank: 0.02 },
    { id: 'zjh', level: 2, type: 'paper', title: 'sth', year: '2017', abstract: 'this is abstract', pagerank: 0.05 },
    { id: 'zjh1', level: 3, type: 'paper', title: 'sth', year: '1983', abstract: 'this is abstract', pagerank: 0.11 },
    { id: 'zjh2', level: 3, type: 'paper', title: 'sth', year: '2007', abstract: 'this is abstract', pagerank: 0.01 },
    { id: 'zjh3', level: 2, type: 'paper', title: 'sth', year: '2001', abstract: 'this is abstract', pagerank: 0.22 },
    { id: 'cjk', level: 3, type: 'author', name: 'I. M. Salama', publication: [{ title: 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels' }, { title: 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels' }], co_author: [{ name: 'author1' }, { name: 'author2' }], pagerank: 0.01 },
    { id: 'cjk1', level: 4, type: 'author', name: 'author!', publication: [{ title: 'publication1' }, { title: 'publication2' }], co_author: [{ name: 'author1' }, { name: 'author2' }], pagerank: 0.16 }
  ],
  links: [
    { source: 'mk', target: 'mk1', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'mk1', target: 'mk2', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'mk3', target: 'mk1', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'zjh', target: 'mk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'zjh', target: 'zjh1', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'zjh', target: 'zjh2', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'zjh3', target: 'mk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'paper' },
    { source: 'zjh3', target: 'cjk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'paper', targettype: 'author' },
    { source: 'cjk1', target: 'cjk', value: 1, sourcelevel: 1, targetlevel: 1, sourcetype: 'author', targettype: 'author' }
  ]
}
var initnode = []
var initlink = []

var data = {}
export default {
  name: 'd3-force',
  data () {
    return {
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
    },
    deepFunc: function (argc) {
      var newObj = {}
      for (var property in argc) {
        if (typeof argc[property] == 'object') newObj[property] = this.deepFunc(argc[property])
        else newObj[property] = argc[property]
      }
      return newObj
    }
  },
  mounted () {
    let that = this
    initnode.splice(0, initnode.length)
    initlink.splice(0, initlink.length)
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

    var svgLink = svg
      .append('g')
      .attr('class', 'links')
      .selectAll('line')

    var svgNode = svg
      .append('g')
      .attr('class', 'nodes')
      .selectAll('circle')

    this.$http.post('/graphdata', { id: 37821, hierarchyLimit: 4 })
      .then((res) => {
        console.log(res)
        if (res.data.paper !== null) {
          this.modeldata = res.data
          var papersList = this.modeldata.paper
          var authorList = this.modeldata.author
          console.log(papersList)
          console.log(authorList)

          for (var i = 0; i < papersList.length; i++) {
            var temppaper = papersList[i]
            var papernode = {}
            papernode.id = 'p' + temppaper.paperid
            papernode.type = 'paper'
            papernode.level = temppaper.level
            papernode.pagerank = temppaper.pagerank
            papernode.title = 'CDMA RAKE receiver for cellular mobile radio in Nakagami fading frequency selective channels'
            papernode.abstract = 'Studies the performance advantage offered by the wideband multipath RAKE structure receiver in a cellular radio direct sequence code division multiple access system (CDMA). The base to mobile link is modeled as a Nakagami fading frequency selective channel. The performance of a RAKE structure receiver employing coherent reception with maximal ratio combining in a frequency selective channel is analyzed and compared with the flat fading case. The degradation in the performance of the receiver as a result of imperfect channel estimation is also studied.'
            papernode.year = '2007'
            initnode.push(Object.assign({}, papernode))

            var tempPapertoAuthor = temppaper.authors
            var tempPaperRefer = temppaper.reference
            if (papernode.level < 4) {
              for (var j = 0; j < tempPapertoAuthor.length; j++) {
                var paperlink = {}
                paperlink.source = 'p' + temppaper.paperid
                paperlink.target = 'a' + tempPapertoAuthor[j]
                paperlink.sourcetype = 'paper'
                paperlink.targettype = 'author'
                initlink.push(Object.assign({}, paperlink))
              }

              for (var j = 0; j < tempPaperRefer.length; j++) {
                var paperlink = {}
                paperlink.source = 'p' + temppaper.paperid
                paperlink.target = 'p' + tempPaperRefer[j]
                paperlink.sourcetype = 'paper'
                paperlink.targettype = 'paper'
                initlink.push(Object.assign({}, paperlink))
              }
            }

          }

          for (var i = 0; i < authorList.length; i++) {
            var tempauthor = authorList[i]
            var authornode = {}
            authornode.id = 'a' + tempauthor.authorid
            authornode.type = 'author'
            authornode.level = tempauthor.level
            authornode.pagerank = tempauthor.pagerank
            initnode.push(Object.assign({}, authornode))
          }

          console.log(initlink)
          console.log(initnode)
          // console.log(initlink)

          simulation.nodes(initnode).on('tick', ticked)

          simulation.force('link').links(initlink)

          svgLink = svgLink
            .data(initlink)
            .enter()
            .append('line')
            .attr('stroke-width', function (d) {
              return Math.sqrt(d.value)
            })

          svgNode = svgNode
            .data(initnode)
            .enter()
            .append('circle')
            .attr('r', function (d, i) {
              return Math.sqrt(d.pagerank / that.totalPR * 2500)
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
                var paperid = d.id
                paperid = paperid.slice(1)
                that.$http.post('/getpaper', { id: paperid })
                  .then((res) => {
                    if (res.data.id !== null) {
                      console.log(res.data)
                      that.paper.title = res.data.title
                      that.paper.year = res.data.year
                      that.paper.abstract = res.data._abstract
                      that.unselected = false
                      that.selected = true
                      that.ispaper = true
                    } else {
                      alert('paperid error')
                    }
                  })
              }
              else {
                var authorid = d.id
                authorid = authorid.slice(1)
                that.$http.post('/getauthor', { id: authorid })
                  .then((res) => {
                    if (res.data.id !== null) {
                      console.log(res.data)
                      that.author.name = res.data.name
                      that.unselected = false
                      that.selected = true
                      that.ispaper = false
                    } else {
                      alert('authorid error')
                    }
                  })
              }
            })
            .on('mouseout', function (d) {
              // tooltip.style('display', 'none')
            })
        } else {
          alert('error!')
        }
      })

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
    }
    function dragstarted (d) {
      if (!d3.event.active) simulation.alphaTarget(0.3).restart()
      d.fx = d.x
      d.fy = d.y
    }

    function dragged (d) {
      d.fx = d3.event.x
      d.fy = d3.event.y
    }

    function dragended (d) {
      if (!d3.event.active) simulation.alphaTarget(0)
      d.fx = null
      d.fy = null
    }

    this.bus.$on('SelectPaper', function (message) {
      that.chooseall = false
      var newnodes = []
      var newlinks = []

      for (var i = 0; i < initnode.length; i++) {
        if (initnode[i].type === 'paper') {
          newnodes.push(initnode[i])
        }
      }

      for (var i = 0; i < initlink.length; i++) {
        console.log(initlink[i].source)
        if (
          initlink[i].source.type !== 'paper' ||
          initlink[i].target.type !== 'paper'
        ) {
          continue
        }
        else {
          newlinks.push(initlink[i])
        }
      }

      svgNode = svgNode.data(newnodes)
      svgLink = svgLink.data(newlinks)
      svgNode.exit().remove()
      svgLink.exit().remove()
    })

    this.bus.$on('SelectAll', function (message) {
      that.chooseall = true
      svgLink = svgLink.data(initlink, d => {
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
        .data(initnode, d => {
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
            var paperid = d.id
            paperid = paperid.slice(1)
            that.$http.post('/getpaper', { id: paperid })
              .then((res) => {
                if (res.data.id !== null) {
                  console.log(res.data)
                  that.paper.title = res.data.title
                  that.paper.year = res.data.year
                  that.paper.abstract = res.data._abstract
                  that.unselected = false
                  that.selected = true
                  that.ispaper = true
                } else {
                  alert('paperid error')
                }
              })
          }
          else {
            var authorid = d.id
            authorid = authorid.slice(1)
            that.$http.post('/getauthor', { id: authorid })
              .then((res) => {
                if (res.data.id !== null) {
                  console.log(res.data)
                  that.author.name = d.name
                  that.unselected = false
                  that.selected = true
                  that.ispaper = false
                } else {
                  alert('authorid error')
                }
              })
          }
        })
        .on('mouseout', function (d) {
          // tooltip.style('display', 'none')
        })
      simulation.nodes(initnode)
      simulation.force('link').links(initlink)
      simulation.alpha(1).restart()
    })

    this.bus.$on('SelectTwoLevel', function (message) {
      if (that.selectLevel > 2) {
        that.selectLevel = 2
        var newnodes = []
        var newlinks = []
        if (this.chooseall === true) {
          for (var i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 2) {
              newnodes.push(initnode[i])
            }
          }
          for (var i = 0; i < initlink.length; i++) {
            if (initlink[i].source.level <= 2 && initlink[i].target.level <= 2) {
              newlinks.push(initlink[i])
            }
          }
        }
        else {
          for (var i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 2 && initnode[i].type === 'paper') {
              newnodes.push(initnode[i])
            }
          }
          for (var i = 0; i < initlink.length; i++) {
            if (
              initlink[i].source.type === 'paper' &&
              initlink[i].target.type === 'paper' &&
              initlink[i].source.level <= 2 &&
              initlink[i].target.level <= 2
            ) {
              newlinks.push(initlink[i])
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
      console.log('select three!')
      if (that.selectLevel === 3) {
        return
      }
      var newnodes = []
      var newlinks = []

      if (that.chooseall === true) {
        for (var i = 0; i < initnode.length; i++) {
          if (initnode[i].level <= 3) {
            newnodes.push(initnode[i])
          }
        }
        console.log(initnode)
        console.log(newnodes)
        for (var i = 0; i < initlink.length; i++) {
          if (initlink[i].source.level <= 3 && initlink[i].target.level <= 3) {
            newlinks.push(initlink[i])
          }
        }
      }
      else {
        for (var i = 0; i < initnode.length; i++) {
          if (initnode[i].level <= 3 && initnode[i].type === 'paper') {
            newnodes.push(initnode[i])
          }
        }
        for (var i = 0; i < initlink.length; i++) {
          if (
            initlink[i].source.type === 'paper' &&
            initlink[i].target.type === 'paper' &&
            initlink[i].source.level <= 3 &&
            initlink[i].target.level <= 3
          ) {
            newlinks.push(initlink[i])
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
        // console.log(newnodes)
        // console.log(newlinks)
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
              var paperid = d.id
              paperid = paperid.slice(1)
              that.$http.post('/getpaper', { id: paperid })
                .then((res) => {
                  if (res.data.id !== null) {
                    console.log(res.data)
                    that.paper.title = res.data.title
                    that.paper.year = res.data.year
                    that.paper.abstract = res.data._abstract
                    that.unselected = false
                    that.selected = true
                    that.ispaper = false
                  } else {
                    alert('paperid error')
                  }
                })
            }
            else {
              var authorid = d.id
              authorid = authorid.slice(1)
              that.$http.post('/getauthor', { id: authorid })
                .then((res) => {
                  if (res.data.id !== null) {
                    console.log(res.data)
                    that.author.name = d.name
                    that.unselected = false
                    that.selected = true
                    that.ispaper = true
                  } else {
                    alert('authorid error')
                  }
                })
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
      if (that.selectLevel <= 4) {
        that.selectLevel = 4
        var newnodes = []
        var newlinks = []
        if (that.chooseall === true) {
          // console.log(chooseall)
          for (var i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 4) {
              newnodes.push(initnode[i])
            }
          }
          for (var i = 0; i < initlink.length; i++) {
            if (initlink[i].source.level <= 4 && initlink[i].target.level <= 4) {
              newlinks.push(initlink[i])
            }
          }
        }
        else {
          for (var i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 4 && initnode[i].type === 'paper') {
              newnodes.push(initnode[i])
            }
          }
          for (var i = 0; i < initlink.length; i++) {
            if (
              initlink[i].source.type === 'paper' &&
              initlink[i].target.type === 'paper' &&
              initlink[i].source.level <= 4 &&
              initlink[i].target.level <= 4
            ) {
              newlinks.push(initlink[i])
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
              var paperid = d.id
              paperid = paperid.slice(1)
              that.$http.post('/getpaper', { id: paperid })
                .then((res) => {
                  if (res.data.id !== null) {
                    console.log(res.data)
                    that.paper.title = res.data.title
                    that.paper.year = res.data.year
                    that.paper.abstract = res.data._abstract
                    that.unselected = false
                    that.selected = true
                    that.ispaper = true
                  } else {
                    alert('paperid error')
                  }
                })
            }
            else {
              var authorid = d.id
              authorid = authorid.slice(1)
              that.$http.post('/getauthor', { id: authorid })
                .then((res) => {
                  if (res.data.id !== null) {
                    console.log(res.data)
                    that.author.name = d.name
                    that.unselected = false
                    that.selected = true
                    that.ispaper = false
                  } else {
                    alert('authorid error')
                  }
                })
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
  max-height: 450px;
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

<template>
  <div>
    <svg width='900' height='668'></svg>
    <div class='tooltip'>
      <div v-if='unselected' class='tip'>
        <p>Detailed Information</p>
        <p>Here!</p>
        <span class='glyphicon glyphicon-list-alt'></span>
      </div>
      <div v-else-if='ispaper'>
        <div class="paper-area">
          <p class='title'>{{paper.title}}</p>
          <p class='year'>year: {{paper.year}}</p>
          <p class='abstract'>{{paper.abstract}}</p>
          <div>
            <h4 id="comment-title">Comment:</h4>
            <div class="comment-area" v-for="paperComment in paper.paperComments">
              <p class="comment-content">
                {{paperComment.content}}
              </p>
              <p class="comment-line">
                <span>{{paperComment.userName}}</span>
                <span>{{paperComment.time}}</span>
              </p>
            </div>
          </div>
          <div class="click-icons">
            <a v-if="this.data.id===null">
              <span class="glyphicon glyphicon-star-empty paper-icons2" @click="loginWarnning"></span>
            </a>
            <a v-else-if="checkStar(paper.id)">
              <span class="glyphicon glyphicon-star paper-icons2" @click="paperUnstar(paper.id)"></span>
            </a>
            <a v-else>
              <span class="glyphicon glyphicon-star-empty paper-icons2" @click="paperStar(paper.id)"></span>
            </a>
            <a>
              <span id="comment-btn" class="glyphicon glyphicon-comment paper-icons" data-toggle="modal" data-target="#paperComment_modal" type="button"></span>
            </a>
            <a v-if="paper.url!==null" :href="paper.url" target="_blank">
              <span class="glyphicon glyphicon-send paper-icons"></span>
            </a>
            <a v-else>
              <span class="glyphicon glyphicon-send paper-icons"></span>
            </a>
          </div>
        </div>
      </div>
      <div v-else>
        <p class='title'>{{author.name}}</p>
      </div>
    </div>
    <div class="modal fade" id="paperComment_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="exampleModalLabel">Comment</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <textarea type="text" class="form-control" rows="8" id="paperCommentBox" placeholder="Enter here">
                </textarea>
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="closeBtn" class="btn btn-primary" data-dismiss="modal" @click="closeComment">Close</button>
            <button type="button" class="btn btn-primary" @click="addComment">Submit</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import $ from 'jquery'
import * as d3 from 'd3'
var initnode = []
var initlink = []
var svgNode
var svgLink
var simulation

export default {
  name: 'd3-force',
  props: ['paperId'],
  data () {
    return {
      id: this.data.id,
      modeldata: {},
      nodes: [],
      links: [],
      unselected: true,
      selected: false,
      ispaper: true,
      totalPR: 1,
      selectLevel: 4,
      chooseall: true,
      starList: [],
      nodeClick: false,
      paper: {
        id: 0,
        title: 'title',
        year: '2018',
        abstract: 'paper abstract',
        url: null,
        paperComments: []
      },
      author: {
        name: 'name',
        publication: ['paper1', 'paper2'],
        co_author: ['co_author1', 'co_author2']
      }
    }
  },
  methods: {
    getColor: function (year) {
      if (year === null) {
        return 'brown'
      }
      if (year > 2005) {
        return 'dodgerblue'
      } else if (year > 1990) {
        return 'coral'
      } else if (year > 1975) {
        return 'darkgreen'
      } else {
        return 'blue'
      }
    },
    loginWarnning: function () {
      alert('You need to login first')
    },
    getCommentArray: function (paperId) {
      this.$http.post('/getpapercomment', { paperId: paperId })
        .then((res) => {
          this.paper.paperComments = res.data.paperComments
        })
    },
    closeComment: function () {
      $('#paperCommentBox').val('')
    },
    addComment: function () {
      if (this.id === null) {
        this.loginWarnning()
        return
      }

      var content = $('#paperCommentBox').val()

      if (content === '') {
        return
      }
      console.log(content)

      this.$http.post('/addpapercomment', { userId: this.id, paperId: this.paper.id, content: content })
        .then((res) => {
          var newComment = {}
          console.log(res)
          newComment.commentId = res.data.commentId
          newComment.userId = res.data.userId
          newComment.paperId = res.data.paperId
          newComment.content = res.data.content
          newComment.userName = res.data.userName
          newComment.time = res.data.time
          this.paper.paperComments.push(newComment)
          $('#closeBtn').click()
        })
    },
    paperStar: function (d) {
      var newStar = {}
      newStar.id = d
      this.starList.push(newStar)
      this.$http.post('/addstar', { userId: this.id, paperId: d })
        .then((res) => {

        })
    },
    paperUnstar: function (d) {
      var index = null
      for (var i = 0; i < this.starList.length; i++) {
        if (d === this.starList[i].id) {
          index = i
          break
        }
      }

      this.starList.splice(index, 1)
      this.$http.post('/deletestar', { userId: this.id, paperId: d })
        .then((res) => {

        })
    },
    checkStar: function (d) {
      console.log(d)
      console.log(this.starList)
      var paperid = d
      for (var i = 0; i < this.starList.length; i++) {
        if (paperid === this.starList[i].id) {
          return true
        }
      }
      return false
    },
    print_id: function (id) {
      console.log(id)
    },
    dataAnalyse: function (papersList, authorList, hierarchyLimit) {
      for (var i = 0; i < papersList.length; i++) {
        var temppaper = papersList[i]
        var papernode = {}
        papernode.id = 'p' + temppaper.paperid
        papernode.type = 'paper'
        papernode.level = temppaper.level
        papernode.pagerank = temppaper.pagerank
        papernode.title = ''
        papernode.abstract = ''
        papernode.year = temppaper.year
        initnode.push(Object.assign({}, papernode))

        var tempPapertoAuthor = temppaper.authors
        var tempPaperRefer = temppaper.reference
        var tempPaperCite = temppaper.cite
        if (papernode.level < hierarchyLimit) {
          for (var j = 0; j < tempPapertoAuthor.length; j++) {
            var paperlink = {}
            paperlink.source = 'p' + temppaper.paperid
            paperlink.target = 'a' + tempPapertoAuthor[j]
            paperlink.sourcetype = 'paper'
            paperlink.targettype = 'author'
            initlink.push(Object.assign({}, paperlink))
          }

          for (j = 0; j < tempPaperRefer.length; j++) {
            paperlink = {}
            paperlink.source = 'p' + temppaper.paperid
            paperlink.target = 'p' + tempPaperRefer[j]
            paperlink.sourcetype = 'paper'
            paperlink.targettype = 'paper'
            initlink.push(Object.assign({}, paperlink))
          }

          for (j = 0; j < tempPaperCite.length; j++) {
            paperlink = {}
            paperlink.source = 'p' + temppaper.paperid
            paperlink.target = 'p' + tempPaperCite[j]
            paperlink.sourcetype = 'paper'
            paperlink.targettype = 'paper'
            initlink.push(Object.assign({}, paperlink))
          }
        }
      }

      for (i = 0; i < authorList.length; i++) {
        var tempauthor = authorList[i]
        var authornode = {}
        authornode.id = 'a' + tempauthor.authorid
        authornode.type = 'author'
        authornode.level = tempauthor.level
        authornode.pagerank = tempauthor.pagerank
        initnode.push(Object.assign({}, authornode))
      }
    },
    netConstruct: function () {
      let that = this
      simulation.nodes(initnode).on('tick', this.ticked)
      simulation.force('link').links(initlink)

      svgLink = svgLink.data([])
      svgNode = svgNode.data([])

      svgNode.exit().remove()
      svgLink.exit().remove()

      svgLink = svgLink.data(initlink)
      svgNode = svgNode.data(initnode)

      svgLink = svgLink
        .enter()
        .append('line')
        .attr('stroke-width', function (d) {
          return Math.sqrt(d.value)
        })

      svgNode = svgNode
        .enter()
        .append('circle')
        .attr('r', function (d, i) {
          if (d.level === 1) {
            return 25
          }
          return Math.sqrt(d.pagerank / that.totalPR * 2500) * 2
        })
        .attr('fill', function (d, i) {
          if (d.type === 'author') {
            return '#2c3e50'
          } else {
            if (d.level === 1) {
              return 'red'
            }
            return that.getColor(d.year)
          }
        })
        .call(
          d3
            .drag()
            .on('start', this.dragstarted)
            .on('drag', this.dragged)
            .on('end', this.dragended)
        )
        .on('click', function (d) {
          if (d.type === 'paper' && d.level !== 1) {
            that.getNewNode(d)
          }
        })
        .on('mouseover', function (d, i) {
          console.log(d.id + ' ' + d.level)
          if (d.type === 'paper') {
            var paperid = d.id
            paperid = paperid.slice(1)
            that.$http.post('/getpaper', { id: paperid })
              .then((res) => {
                if (res.data.id !== null) {
                  that.paper.id = res.data.id
                  that.paper.title = res.data.title
                  that.paper.year = res.data.year
                  that.paper.abstract = res.data._abstract
                  that.paper.url = res.data.url
                  that.unselected = false
                  that.selected = true
                  that.ispaper = true
                } else {
                  alert('paperid error')
                }
              })
            that.getCommentArray(paperid)
          } else {
            var authorid = d.id
            authorid = authorid.slice(1)
            that.$http.post('/getauthor', { id: authorid })
              .then((res) => {
                if (res.data.id !== null) {
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
      simulation.alpha(1).restart()
    },
    getNewNode: function (cliNode) {
      if (this.nodeClick) {
        return
      }
      this.nodeClick = true
      initnode.splice(0, initnode.length)
      initlink.splice(0, initlink.length)

      var paperid = cliNode.id
      paperid = paperid.slice(1)
      this.$http.post('/graphdata', { id: paperid, hierarchyLimit: 4 })
        .then((res) => {
          if (res.data.paper !== null) {
            console.log(res.data.paper)
            this.modeldata = res.data
            var papersList = this.modeldata.paper
            var authorList = this.modeldata.author

            this.dataAnalyse(papersList, authorList, 4)
            this.netConstruct()
            this.nodeClick = false
          } else {
            alert('error!')
          }
        })
        .catch((error) => {
          console.log(error)
          this.$http.post('/graphdata', { id: paperid, hierarchyLimit: 3 })
            .then((res) => {
              if (res.data.paper !== null) {
                this.modeldata = res.data
                var papersList = this.modeldata.paper
                var authorList = this.modeldata.author

                this.dataAnalyse(papersList, authorList, 3)
                this.netConstruct()
                this.nodeClick = false
              } else {
                alert('error!')
              }
            })
        })
    },
    ticked: function () {
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
    },
    dragstarted: function (d) {
      if (!d3.event.active) simulation.alphaTarget(0.3).restart()
      d.fx = d.x
      d.fy = d.y
    },

    dragged: function (d) {
      d.fx = d3.event.x
      d.fy = d3.event.y
    },

    dragended: function (d) {
      if (!d3.event.active) simulation.alphaTarget(0)
      d.fx = null
      d.fy = null
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

    var svg = d3.select('svg')

    var width = svg.attr('width')
    var height = svg.attr('height')

    svg.call(zoom)

    function zoomed () {
      svg.selectAll('g').attr('transform', d3.event.transform)
    }
    // 自定义引力
    var repelForce = d3
      .forceManyBody()
      .strength(-800)
      .distanceMax(200)
    // 自定义斥力

    simulation = d3
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

    svgLink = svg
      .append('g')
      .attr('class', 'links')
      .selectAll('line')

    svgNode = svg
      .append('g')
      .attr('class', 'nodes')
      .selectAll('circle')

    if (this.id !== null) {
      this.$http.post('/getstar', { userId: this.id })
        .then((res) => {
          this.starList = res.data.stars
        })
    }

    console.log(this.paperId)
    this.$http.post('/graphdata', { id: this.paperId, hierarchyLimit: 4 })
      .then((res) => {
        if (res.data.paper !== null) {
          this.modeldata = res.data
          var papersList = this.modeldata.paper
          var authorList = this.modeldata.author

          this.dataAnalyse(papersList, authorList, 4)
          this.netConstruct()
        } else {
          alert('error!')
        }
      })
      .catch((error) => {
        console.log(error)
        this.$http.post('/graphdata', { id: 37821, hierarchyLimit: 3 })
          .then((res) => {
            if (res.data.paper !== null) {
              this.modeldata = res.data
              var papersList = this.modeldata.paper
              var authorList = this.modeldata.author

              this.dataAnalyse(papersList, authorList, 3)
              this.netConstruct()
            } else {
              alert('error!')
            }
          })
      })

    this.bus.$on('SelectPaper', function (message) {
      that.chooseall = false
      var newnodes = []
      var newlinks = []

      for (var i = 0; i < initnode.length; i++) {
        if (initnode[i].type === 'paper' && initnode[i].level <= that.selectLevel) {
          newnodes.push(initnode[i])
        }
      }

      for (i = 0; i < initlink.length; i++) {
        console.log(initlink[i].source)
        if (
          initlink[i].source.type !== 'paper' || initlink[i].source.level > that.selectLevel ||
          initlink[i].target.type !== 'paper' || initlink[i].target.level > that.selectLevel
        ) {
          continue
        } else {
          newlinks.push(initlink[i])
        }
      }

      svgNode = svgNode.data(newnodes)
      svgLink = svgLink.data(newlinks)
      svgNode.exit().remove()
      svgLink.exit().remove()

      simulation.alpha(1).restart()
    })

    this.bus.$on('SelectAll', function (message) {
      that.chooseall = true
      that.selectLevel = 4
      svgLink = svgLink.data(initlink, d => {
        return d.source.id + '-' + d.target.id
      })

      svgNode = svgNode.data(initnode, d => {
        return d.id
      })
      svgLink.exit().remove()
      svgNode.exit().remove()

      svgLink = svgLink
        .enter()
        .append('line')
        .attr('stroke-width', function (d) {
          return Math.sqrt(d.value)
        })
        .merge(svgLink)

      svgNode = svgNode
        .enter()
        .append('circle')
        .attr('r', function (d, i) {
          if (d.level === 1) {
            return 25
          }
          return Math.sqrt(d.pagerank / that.totalPR * 2500) * 2
        })
        .attr('fill', function (d, i) {
          if (d.type === 'author') {
            return '#2c3e50'
          } else {
            if (d.level === 1) {
              return 'red'
            }
            return that.getColor(d.year)
          }
        })
        .merge(svgNode)
        .call(
          d3
            .drag()
            .on('start', that.dragstarted)
            .on('drag', that.dragged)
            .on('end', that.dragended)
        )
        .on('click', function (d) {
          if (d.type === 'paper' && d.level !== 1) {
            that.getNewNode(d)
          }
        })
        .on('mouseover', function (d, i) {
          console.log(d.id + ' ' + d.level)
          if (d.type === 'paper') {
            var paperid = d.id
            paperid = paperid.slice(1)
            that.$http.post('/getpaper', { id: paperid })
              .then((res) => {
                if (res.data.id !== null) {
                  that.paper.id = res.data.id
                  that.paper.title = res.data.title
                  that.paper.year = res.data.year
                  that.paper.abstract = res.data._abstract
                  that.paper.url = res.data.url
                  that.unselected = false
                  that.selected = true
                  that.ispaper = true
                } else {
                  alert('paperid error')
                }
              })
            that.getCommentArray(paperid)
          } else {
            var authorid = d.id
            authorid = authorid.slice(1)
            that.$http.post('/getauthor', { id: authorid })
              .then((res) => {
                if (res.data.id !== null) {
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
          for (i = 0; i < initlink.length; i++) {
            if (initlink[i].source.level <= 2 && initlink[i].target.level <= 2) {
              newlinks.push(initlink[i])
            }
          }
        } else {
          for (i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 2 && initnode[i].type === 'paper') {
              newnodes.push(initnode[i])
            }
          }
          for (i = 0; i < initlink.length; i++) {
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

        simulation.alpha(1).restart()
      }
    })

    this.bus.$on('SelectThreeLevel', function (message) {
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
        for (i = 0; i < initlink.length; i++) {
          if (initlink[i].source.level <= 3 && initlink[i].target.level <= 3) {
            newlinks.push(initlink[i])
          }
        }
      } else {
        for (i = 0; i < initnode.length; i++) {
          if (initnode[i].level <= 3 && initnode[i].type === 'paper') {
            newnodes.push(initnode[i])
          }
        }
        for (i = 0; i < initlink.length; i++) {
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

        simulation.alpha(1).restart()
      } else {
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
            if (d.level === 1) {
              return 25
            }
            return Math.sqrt(d.pagerank / that.totalPR * 2500) * 2
          })
          .attr('fill', function (d, i) {
            if (d.type === 'author') {
              return '#2c3e50'
            } else {
              if (d.level === 1) {
                return 'red'
              }
              return that.getColor(d.year)
            }
          })
          .merge(svgNode)
          .call(
            d3
              .drag()
              .on('start', that.dragstarted)
              .on('drag', that.dragged)
              .on('end', that.dragended)
          )
          .on('click', function (d) {
            if (d.type === 'paper' && d.level !== 1) {
              that.getNewNode(d)
            }
          })
          .on('mouseover', function (d, i) {
            console.log(d.id + ' ' + d.level)
            if (d.type === 'paper') {
              var paperid = d.id
              paperid = paperid.slice(1)
              that.$http.post('/getpaper', { id: paperid })
                .then((res) => {
                  if (res.data.id !== null) {
                    that.paper.id = res.data.id
                    that.paper.title = res.data.title
                    that.paper.year = res.data.year
                    that.paper.abstract = res.data._abstract
                    that.paper.url = res.data.url
                    that.unselected = false
                    that.selected = true
                    that.ispaper = true
                  } else {
                    alert('paperid error')
                  }
                })
              that.getCommentArray(paperid)
            } else {
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
          for (var i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 4) {
              newnodes.push(initnode[i])
            }
          }
          for (i = 0; i < initlink.length; i++) {
            if (initlink[i].source.level <= 4 && initlink[i].target.level <= 4) {
              newlinks.push(initlink[i])
            }
          }
        } else {
          for (i = 0; i < initnode.length; i++) {
            if (initnode[i].level <= 4 && initnode[i].type === 'paper') {
              newnodes.push(initnode[i])
            }
          }
          for (i = 0; i < initlink.length; i++) {
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

        simulation.nodes(newnodes)
        simulation.force('link').links(newlinks)

        svgLink = svgLink.data(newlinks, d => {
          return d.source.id + '-' + d.target.id
        })
        svgNode = svgNode.data(newnodes, d => {
          return d.id
        })

        svgLink.exit().remove()
        svgNode.exit().remove()

        svgLink = svgLink
          .enter()
          .append('line')
          .attr('stroke-width', function (d) {
            return Math.sqrt(d.value)
          })
          .merge(svgLink)

        svgNode = svgNode
          .enter()
          .append('circle')
          .attr('r', function (d, i) {
            if (d.level === 1) {
              return 25
            }
            return Math.sqrt(d.pagerank / that.totalPR * 2500) * 2
          })
          .attr('fill', function (d, i) {
            if (d.type === 'author') {
              return '#2c3e50'
            } else {
              if (d.level === 1) {
                return 'red'
              }
              return that.getColor(d.year)
            }
          })
          .merge(svgNode)
          .call(
            d3
              .drag()
              .on('start', that.dragstarted)
              .on('drag', that.dragged)
              .on('end', that.dragended)
          )
          .on('click', function (d) {
            if (d.type === 'paper' && d.level !== 1) {
              that.getNewNode(d)
            }
          })
          .on('mouseover', function (d, i) {
            console.log(d.id + ' ' + d.level)
            if (d.type === 'paper') {
              var paperid = d.id
              paperid = paperid.slice(1)
              that.$http.post('/getpaper', { id: paperid })
                .then((res) => {
                  if (res.data.id !== null) {
                    that.paper.id = res.data.id
                    that.paper.title = res.data.title
                    that.paper.year = res.data.year
                    that.paper.abstract = res.data._abstract
                    that.paper.url = res.data.url
                    that.unselected = false
                    that.selected = true
                    that.ispaper = true
                  } else {
                    alert('paperid error')
                  }
                })
              that.getCommentArray(paperid)
            } else {
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
        simulation.alpha(1).restart()
      }
    })
  }
}
</script>

<style>
.tooltip {
  display: none;
}

@media (min-width: 1000px) {
  .tooltip {
    display: block;
  }
}
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
  padding-top: 25px;
  padding-bottom: 20px;
  width: 28%;
  bottom: 0px;
  font-size: 15px;
  z-index: 120;
}

/* .tooltip p {
  font-size: 15px;
  text-align: center;
  padding: 0;
  margin: 0;
} */

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

.click-icons {
  width: 95%;
  position: absolute;
  bottom: 15px;
  text-align: center;
}
.paper-icons {
  font-size: 25px;
  margin-right: 30px;
}
.paper-icons2 {
  font-size: 28px;
  margin-right: 30px;
}
.click-icons a {
  color: #464644;
}
.click-icons a:hover {
  color: #000;
}

.paper-area {
  height: 610px;
  overflow: auto;
}

#comment-title {
  margin-top: 30px;
  margin-left: 10px;
  margin-right: 10px;
  padding-bottom: 10px;
  border-bottom: 1px rgba(36, 41, 46, 0.5) solid !important;
}

.comment-line {
  margin-right: 15px;
  float: right;
}

.comment-content {
  text-indent: 1em;
  margin-left: 3px;
  margin-right: 3px;
  margin-top: 10px;
  margin-bottom: 5px;
}

.comment-area {
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  padding-bottom: 20px;
  border-bottom: 1px rgba(36, 41, 46, 0.5) solid !important;
}

.btn-primary {
  transition: all 0.3s ease;
  background: rgba(36, 41, 46, 0.9);
  border-color: rgba(36, 41, 46, 0);
  margin-top: 5px;
  color: white;
}
.btn-primary:active:focus {
  color: #333;
  background-color: #ffffff;
  border-color: rgba(36, 41, 46, 0);
}
.btn-primary:hover {
  color: #333;
  background-color: rgba(36, 41, 46, 0.2);
  border-color: rgba(36, 41, 46, 0);
}
</style>

<template>
  <div>
    <div class="op-area">
      <strong>Node:</strong>
      <a>
        <span id="add-icon" class="glyphicon glyphicon-plus-sign" data-toggle="modal" data-target="#addNode_modal" type="button"></span>
      </a>
      <a>
        <span id="minus-icon" class="glyphicon glyphicon-minus-sign" data-toggle="modal" data-target="#deleteNode_modal" type="button"></span>
      </a>
      <strong>Link:</strong>
      <a>
        <span id="add-icon" class="glyphicon glyphicon-plus-sign" data-toggle="modal" data-target="#addLink_modal" type="button"></span>
      </a>
      <a>
        <span id="minus-icon" class="glyphicon glyphicon-minus-sign" data-toggle="modal" data-target="#deleteLink_modal" type="button"></span>
      </a>
    </div>
    <div class="modal fade" id="addNode_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="exampleModalLabel">Add Net-node</h4>
          </div>
          <div class="modal-body">
            <form>
              <!-- <div class="form-group">
                <label for="recipient-name" class="control-label">Node Type:</label>
                <label class="radio-inline">
                  <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="option1" checked> paper
                </label>
                <label class="radio-inline">
                  <input type="radio" name="optionsRadiosinline" id="optionsRadios4" value="option2"> author
                </label>
              </div> -->
              <div class="form-group">
                <label for="recipient-name" class="control-label">Node name:</label>
                <input type="text" class="form-control" id="name1" placeholder="Paper title/Author name">
              </div>
              <div class="form-group">
                <label for="recipient-name" class="control-label">Node radius:</label>
                <input type="text" class="form-control" id="radius1" placeholder="Node radius here">
              </div>
              <div class="form-group">
                <label for="recipient-name" class="control-label">Node color:</label>
                <input type="radio" class="radioColor" name="radioColor" value="black" checked>Black
                <input type="radio" class="radioColor" name="radioColor" value="red">Red
                <input type="radio" class="radioColor" name="radioColor" value="blue">Blue
                <input type="radio" class="radioColor" name="radioColor" value="green">Green
                <input type="radio" class="radioColor" name="radioColor" value="yellow">Yellow
                <input type="radio" class="radioColor" name="radioColor" value="purple">Purple
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="close1" class="btn btn-primary" data-dismiss="modal" @click="closeNode">Close</button>
            <button type="button" class="btn btn-primary" @click="addNode">Submit</button>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="deleteNode_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="exampleModalLabel">Delete Net-node</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="recipient-name" class="control-label">Node name:</label>
                <input type="text" class="form-control" id="name2" placeholder="Paper title/Author name">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="close2" class="btn btn-primary" data-dismiss="modal" @click="closeNode">Close</button>
            <button type="button" class="btn btn-primary" @click="deleteNode">Submit</button>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="addLink_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="exampleModalLabel">Add Net-link</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="recipient-name" class="control-label">Start Node Name:</label>
                <input type="text" class="form-control" id="startNode1" placeholder="Paper title/Author name">
              </div>
              <div class="form-group">
                <label for="recipient-name" class="control-label">End Node name:</label>
                <input type="text" class="form-control" id="endNode1" placeholder="Paper title/Author name">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="close3" class="btn btn-primary" data-dismiss="modal" @click="closeLink">Close</button>
            <button type="button" class="btn btn-primary" @click="addLink">Submit</button>
          </div>
        </div>
      </div>
    </div>
    <div class="modal fade" id="deleteLink_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <h4 class="modal-title" id="exampleModalLabel">Delete Net-link</h4>
          </div>
          <div class="modal-body">
            <form>
              <div class="form-group">
                <label for="recipient-name" class="control-label">Start Node Name:</label>
                <input type="text" class="form-control" id="startNode2" placeholder="Paper title/Author name">
              </div>
              <div class="form-group">
                <label for="recipient-name" class="control-label">End Node name:</label>
                <input type="text" class="form-control" id="endNode2" placeholder="Paper title/Author name">
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="close4" class="btn btn-primary" data-dismiss="modal" @click="closeLink">Close</button>
            <button type="button" class="btn btn-primary" @click="deleteLink">Submit</button>
          </div>
        </div>
      </div>
    </div>
    <svg class="net-background" width="740" height="400">
    </svg>
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
var tooltip

export default {
  name: 'Net',
  data () {
    return {
      id: this.data.id
    }
  },
  methods: {
    reconstruct: function () {
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
        .attr('stroke', function (d) {
          return '#999'
        })

      svgNode = svgNode
        .enter()
        .append('circle')
        .attr('r', function (d) {
          return d.radius
        })
        .attr('fill', function (d, i) {
          return d.Nodecolor
        })
        .call(
          d3
            .drag()
            .on('start', this.dragstarted)
            .on('drag', this.dragged)
            .on('end', this.dragended)
        )
        .on('mouseover', function (d, i) {

        })

      simulation.alpha(1).restart()
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
    },
    closeNode: function () {
      $('#name1').val('')
      $('#name2').val('')
      $('#radius1').val('')
      $('#name1').parent().removeClass('has-error')
      $('#name2').parent().removeClass('has-error')
      $('#radius1').parent().removeClass('has-error')
      $('#name1-help').remove()
      $('#radius1-help').remove()
      $('#name2-help').remove()
    },
    addNode: function () {
      // var color = d3.scaleOrdinal(d3.schemeCategory10)
      var Nodename = $('#name1').val()
      var radius = $('#radius1').val()
      var colorName = document.getElementsByName('radioColor')
      var errorFlag = false

      if (Nodename === '') {
        $('#name1').parent().addClass('has-error')
        $('#name1-help').remove()
        $('#name1').after('<span id="name1-help" class="help-block">Node name can not be empty</span>')
        errorFlag = true
      }

      if (radius === '') {
        $('#radius1').parent().addClass('has-error')
        $('#radius1-help').remove()
        $('#radius1').after('<span id="radius1-help" class="help-block">Node radius can not be empty</span>')
        errorFlag = true
      }

      if (errorFlag) {
        return
      }

      var Nodecolor = 'black'
      for (var i = 0; i < colorName.length; i++) {
        if (colorName[i].checked) {
          Nodecolor = colorName[i].value
        }
      }

      var node = {}
      node.id = Nodename
      node.radius = radius
      node.Nodecolor = Nodecolor
      initnode.push(node)

      this.reconstruct()
      $('#close1').click()

      // this.$http.post('/addPersonalNode', { userid: this.id, nodeid: Nodename, radius: radius, nodecolor: Nodecolor })
      //   .then((res) => {
      //     // to check
      //   })
    },
    deleteNode: function () {
      var Nodename = $('#name2').val()
      var errorFlag = false
      var everApear = false
      var index = null

      for (var i = 0; i < initnode.length; i++) {
        if (Nodename === initnode[i].id) {
          everApear = true
          index = i
          break
        }
      }

      if (!everApear) {
        Nodename = ''
      }
      if (Nodename === '') {
        $('#name2').parent().addClass('has-error')
        $('#name2-help').remove()
        $('#name2').after('<span id="name2-help" class="help-block">Node name can not be found in net</span>')
        errorFlag = true
      }

      if (errorFlag) {
        return
      }

      initnode.splice(index, 1)
      var hasApear = false
      var count = 0
      var linkIndex = null

      for (var j = 0; j < initlink.length; j++) {
        if (Nodename === initlink[j].startNode || Nodename === initlink[j].endNode) {
          count++
          if (!hasApear) {
            hasApear = true
            linkIndex = j
          }
        }
      }

      initlink.splice(linkIndex, count)
      this.reconstruct()
      $('#close2').click()

      // this.$http.post('/deletePersonalNode', { userid: this.id, nodeid: Nodename })
      //   .then((res) => {
      //     // to check
      //   })
    },

    addLink: function (d) {
      var startNode = $('#startNode1').val()
      var endNode = $('#endNode1').val()
      var errorFlag = false
      var errorStartNode = true
      var errorEndNode = true

      for (var i = 0; i < initnode.length; i++) {
        if (startNode === initnode[i].id) {
          errorStartNode = false
        }
        if (endNode !== startNode && endNode === initnode[i].id) {
          errorEndNode = false
        }
      }

      if (errorStartNode) {
        startNode = ''
      }

      if (errorEndNode) {
        endNode = ''
      }

      if (startNode === '') {
        $('#startNode1').parent().addClass('has-error')
        $('#startNode1-help').remove()
        $('#startNode1').after('<span id="startNode1-help" class="help-block">Node name can not be found</span>')
        errorFlag = true
      }

      if (endNode === '') {
        $('#endNode1').parent().addClass('has-error')
        $('#endNode1-help').remove()
        $('#endNode1').after('<span id="endNode1-help" class="help-block">Node name can not be found</span>')
        errorFlag = true
      }

      if (errorFlag) {
        return
      }

      var link = {}
      link.source = startNode
      link.target = endNode
      link.value = 2
      initlink.push(link)

      this.reconstruct()
      $('#close3').click()

      // this.$http.post('/addPersonalLink', { userid: this.id, source: startNode, target: endNode, value: 2 })
      //   .then((res) => {
      //     // do sth
      //   })
    },

    deleteLink: function (d) {
      var startNode = $('#startNode2').val()
      var endNode = $('#endNode2').val()
      var errorFlag = false
      var everApear = false
      var index = null

      for (var i = 0; i < initlink.length; i++) {
        if (startNode === initlink[i].source.id && endNode === initlink[i].target.id) {
          index = i
          everApear = true
          break
        }

        if (endNode === initlink[i].source.id && startNode === initlink[i].target.id) {
          index = i
          everApear = true
          break
        }
      }

      if (!everApear) {
        startNode = ''
        endNode = ''
      }

      if (startNode === '') {
        $('#startNode2').parent().addClass('has-error')
        $('#startNode2-help').remove()
        $('#startNode2').after('<span id="startNode2-help" class="help-block">Link can not be found</span>')
        errorFlag = true
      }

      if (endNode === '') {
        $('#endNode2').parent().addClass('has-error')
        $('#endNode2-help').remove()
        $('#endNode2').after('<span id="endNode2-help" class="help-block">Link can not be found</span>')
        errorFlag = true
      }

      if (errorFlag) {
        return
      }

      initlink.splice(index, 1)

      this.reconstruct()
      $('close4').click()

      // this.$http.post('/deletePersonalLink', { userid: this.id, source: startNode, target: endNode })
      //   .then((res) => {
      //     // to check
      //   })
    },

    closeLink: function () {
      $('#startNode1').val('')
      $('#endNode1').val('')
      $('#startNode2').val('')
      $('#endNode2').val('')
      $('#startNode1').parent().removeClass('has-error')
      $('#endNode1').parent().removeClass('has-error')
      $('#startNode2').parent().removeClass('has-error')
      $('#endNode2').parent().removeClass('has-error')
      $('#startNode1-help').remove()
      $('#endNode1-help').remove()
      $('#startNode2-help').remove()
      $('#endNode2-help').remove()
    }
  },
  mounted () {
    let that = this
    initnode.splice(0, initnode.length)
    initlink.splice(0, initlink.length)
    var zoom = d3
      .zoom()
      .scaleExtent([-2, 2])
      .on('zoom', zoomed)

    // var color = d3.scaleOrdinal(d3.schemeCategory10)

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

    simulation.nodes(initnode).on('tick', this.ticked)
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
        return d.radius
      })
      .attr('fill', function (d, i) {
        return '#2c3e50'
      })
      .call(
        d3
          .drag()
          .on('start', this.dragstarted)
          .on('drag', this.dragged)
          .on('end', this.dragended)
      )
      .on('mouseover', function (d, i) {
      })

    // 获取个人网络图
    // this.$http.post('/personalgraphdata', { id: this.id })
    //   .then((res) => {
    //     initnode = res.data.nodearray
    //     initlink = res.data.linkarray

    //     simulation.nodes(initnode).on('tick', this.ticked)
    //     simulation.force('link').links(initlink)

    //     svgLink = svgLink
    //       .data(initlink)
    //       .enter()
    //       .append('line')
    //       .attr('stroke-width', function (d) {
    //         return Math.sqrt(d.value)
    //       })
    //       .attr('stroke', function (d) {
    //         return '#999'
    //       })

    //     svgNode = svgNode
    //       .data(initnode)
    //       .enter()
    //       .append('circle')
    //       .attr('r', function (d, i) {
    //         return d.radius
    //       })
    //       .attr('fill', function (d, i) {
    //         return d.nodecolor
    //       })
    //       .call(
    //         d3
    //           .drag()
    //           .on('start', this.dragstarted)
    //           .on('drag', this.dragged)
    //           .on('end', this.dragended)
    //       )
    //       .on('mouseover', function (d, i) {

    //       })
    //   })
  }
}
</script>

<style scoped>
a {
  color: #333;
}
.net-background {
  margin-top: 10px;
  background-color: rgba(255, 255, 255, 0.4);
}
#add-icon {
  margin-left: 5px;
  margin-top: 10px;
  margin-right: 8px;
  font-size: 20px;
}
#minus-icon {
  margin-right: 10px;
  font-size: 20px;
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
.btn:active,
.btn:focus {
  z-index: 2;
  box-shadow: none;
  outline: none;
}

.control-label {
  margin-right: 5px;
}
.op-area {
  padding-left: 10px;
}
.radioColor {
  margin-left: 3px;
  margin-right: 5px;
}
</style>


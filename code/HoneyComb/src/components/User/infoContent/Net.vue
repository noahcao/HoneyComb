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
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" id="close" class="btn btn-primary" data-dismiss="modal" @click="closeNode">Close</button>
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
            <button type="button" id="close" class="btn btn-primary" data-dismiss="modal" @click="closeNode">Close</button>
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
            <button type="button" id="close" class="btn btn-primary" data-dismiss="modal" @click="closeLink">Close</button>
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
            <button type="button" id="close" class="btn btn-primary" data-dismiss="modal" @click="closeLink">Close</button>
            <button type="button" class="btn btn-primary" @click="deleteLink">Submit</button>
          </div>
        </div>
      </div>
    </div>
    <svg class="net-background">
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

export default {
  name: 'Net',
  props: ['userid'],
  data () {
    return {
      id: this.data.id
    }
  },
  watch: {
    'userid': {
      handler: function (val, oldVal) {
        // this.$http.post('/getTrends', { id: this.id })
        //   .then((res) => {

        //   })
      },
      deep: true
    }
  },
  methods: {
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
      $('radius1').val('')
      $('#name1').parent().removeClass('has-error')
      $('#name2').parent().removeClass('has-error')
      $('radius1').parent().removeClass('has-error')
    },

    addNode: function () {
      var Nodename = $('#name1').val()
      var radius = $('#radius1').val()
      var errorFlag = false

      if (Nodename === '') {
        $('#name1').parent().addClass('has-error')
        $('#name1-help').remove()
        $('#name11').after('<span id="name-help" class="help-block">Node name can not be empty</span>')
        errorFlag = true
      }

      if (radius === '') {
        $('#radius1').parent().addClass('has-error')
        $('#radius-help').remove()
        $('#radius').after('<span id="radius-help" class="help-block">Node radius can not be empty</span>')
        errorFlag = true
      }

      if (errorFlag) {
        return
      }

      


    },

    deleteNode: function () {

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


    // this.$http.post('/personalgraphdata', { id: this.id })
    //   .then((res) => {
    //     if (res.data.paper !== null) {

    //       simulation.nodes(initnode).on('tick', this.ticked)
    //       simulation.force('link').links(initlink)

    //       svgLink = svgLink
    //         .data(initlink)
    //         .enter()
    //         .append('line')
    //         .attr('stroke-width', function (d) {
    //           return Math.sqrt(d.value)
    //         })

    //       svgNode = svgNode
    //         .data(initnode)
    //         .enter()
    //         .append('circle')
    //         .attr('r', function (d, i) {
    //           return Math.sqrt(d.pagerank / that.totalPR * 2500)
    //         })
    //         .attr('fill', function (d, i) {
    //           if (d.type === 'author') {
    //             return '#2c3e50'
    //           }
    //           else {
    //             return color((d.year / 5) % 10)
    //           }
    //         })
    //         .call(
    //           d3
    //             .drag()
    //             .on('start', this.dragstarted)
    //             .on('drag', this.dragged)
    //             .on('end', this.dragended)
    //         )
    //         .on('click', function (d) {
    //           if (d.type === 'paper') {
    //             that.getNewNode(d)
    //           }
    //         })
    //         .on('mouseover', function (d, i) {
    //           console.log(d.id + ' ' + d.level)
    //           if (d.type === 'paper') {
    //             var paperid = d.id
    //             paperid = paperid.slice(1)
    //             that.$http.post('/getpaper', { id: paperid })
    //               .then((res) => {
    //                 if (res.data.id !== null) {
    //                   that.paper.title = res.data.title
    //                   that.paper.year = res.data.year
    //                   that.paper.abstract = res.data._abstract
    //                   that.unselected = false
    //                   that.selected = true
    //                   that.ispaper = true
    //                 } else {
    //                   alert('paperid error')
    //                 }
    //               })
    //           }
    //           else {
    //             var authorid = d.id
    //             authorid = authorid.slice(1)
    //             that.$http.post('/getauthor', { id: authorid })
    //               .then((res) => {
    //                 if (res.data.id !== null) {
    //                   that.author.name = res.data.name
    //                   that.unselected = false
    //                   that.selected = true
    //                   that.ispaper = false
    //                 } else {
    //                   alert('authorid error')
    //                 }
    //               })
    //           }
    //         })
    //     } else {
    //       alert('error!')
    //     }
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
  width: 100%;
  height: 400px;
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
</style>


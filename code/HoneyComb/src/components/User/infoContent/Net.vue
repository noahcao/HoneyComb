<template>
  <div>
    <svg width='800' height='600'></svg>
  </div>
</template>

<script>
import * as d3 from 'd3'
var initnode = []
var initlink = []
var svgNode
var svgLink
var simulation

export default {
  name: 'Net',
  data () {
    return {
      id: this.data.id
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

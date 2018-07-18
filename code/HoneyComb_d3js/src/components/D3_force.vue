<template>
  <div>
    <svg width='1000' height='600'></svg>
  </div>
</template>
<script>
import * as d3 from 'd3';
var data = {
  nodes: [
    { id: 'mk', group: 1 },
    { id: 'mk1', group: 1 },
    { id: 'mk2', group: 1 },
    { id: 'mk3', group: 1 },
    { id: 'zjh', group: 2 },
    { id: 'zjh1', group: 2 },
    { id: 'zjh2', group: 2 },
    { id: 'zjh3', group: 2 },
    { id: 'cjk', group: 3 },
    { id: 'cjk1', group: 3 }
  ],
  links: [
    { source: 'mk', target: 'mk1', value: 1 },
    { source: 'mk1', target: 'mk2', value: 1 },
    { source: 'mk3', target: 'mk1', value: 1 },
    { source: 'zjh', target: 'mk', value: 1 },
    { source: 'zjh', target: 'zjh1', value: 1 },
    { source: 'mk3', target: 'zjh2', value: 1 },
    { source: 'zjh3', target: 'zjh1', value: 1 },
    { source: 'zjh3', target: 'cjk', value: 1 },
    { source: 'cjk1', target: 'cjk', value: 1 }
  ]
}

export default {
  name: 'D3Force',
  data () {
    return {
      nodes: data.nodes,
      links: data.links
    }
  },
  methods: {
    print_id: function (id) {
      console.log(id)
    }
  },
  created () {
    this.bus.$on('SelectAuthor', function (message) {
      //waiting for finished!
    })
  },
  mounted () {
    let this_ = this
    console.log('mounted!')
    var img_w = 10
    var img_h = 10
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

    var attractForce = d3
      .forceManyBody()
      .strength(200)
      .distanceMax(400)
      .distanceMin(60)
    //自定义引力
    var repelForce = d3
      .forceManyBody()
      .strength(-500)
      .distanceMax(250)
    //自定义斥力

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

    var svg_link = svg
      .append('g')
      .attr('class', 'links')
      .selectAll('line')
      .data(data.links)
      .enter()
      .append('line')
      .attr('stroke-width', function (d) {
        return Math.sqrt(d.value)
      })

    // var tooltip = d3.select('body')
    //     .append('div')
    //     .attr('class', 'tooltip')
    //     .style('display', 'none')

    var svg_node = svg
      .append('g')
      .attr('class', 'nodes')
      .selectAll('circle')
      .data(data.nodes)
      .enter()
      .append('circle')
      .attr('r', 10)
      .attr('fill', function (d, i) {
        return color(d.group)
        /*var defs = svg.append('defs').attr('id', 'imgdefs')

        var catpattern = defs.append('pattern')
                            .attr('id','catpattern' + i)
                            .attr('height', 1)
                            .attr('width', 1)

        catpattern.append('image')
                .attr('x',-(img_w - 20))
                .attr('y', (img_h - 20))
                .attr('width', img_w*2)
                .attr('height', img_h*2)
                .attr('xlink:href', d.image)

        return 'url(#catpattern' + i + ')'; */
      })
      .call(
        d3
          .drag()
          .on('start', dragstarted)
          .on('drag', dragged)
          .on('end', dragended)
      )
      .on('click', function (d) {
        this_.print_id(d.id)
      })
      .on('mouseover', function (d, i) {
        // tooltip.html('<p>' + d.id + '</p>')
        //     .transition()
        //     .style('left', (d.x + 80) + 'px')
        //     .style('top', (d.y + 20) + 'px')
        //     .style('display', 'block')
      })
      .on('mouseout', function (d) {
        // tooltip.style('display', 'none')
      })

    /*var text = svg
      .append('g')
      .attr('class','texts')
      .selectAll('text')
      .data(data.nodes)
      .enter()
      .append('text')
      .attr('font-family', 'sans-serif')
      .attr('font-size', '11px')
      .attr('fill', 'black')
      .attr('fill-opacity', '0.0')*/

    function ticked () {
      svg_link
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
        });

      svg_node
        .attr('cx', function (d) {
          return d.x
        })
        .attr('cy', function (d) {
          return d.y
        });

      /*text
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
        .attr('dy', '20');*/
    }
    function dragstarted (d) {
      //tooltip.style('display','none')
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
      console.log(svg_node);
      var newnodes = []
      var newlinks = []

      for (var i = 0; i < data.nodes.length; i++) {
        if (data.nodes[i].group != 3) {
          newnodes.push(data.nodes[i])
        }
      }

      for (var i = 0; i < data.links.length; i++) {
        if (
          data.links[i].source.id == 'cjk' ||
          data.links[i].target.id == 'cjk' ||
          data.links[i].source.id == 'cjk1' ||
          data.links[i].target.id == 'cjk1'
        ) {
          continue
        } else newlinks.push(data.links[i])
      }

      svg_node = svg_node.data(newnodes)
      svg_link = svg_link.data(newlinks)
      svg_node.exit().remove()
      svg_link.exit().remove()
    });

    this.bus.$on('SelectAll', function (message) {
      svg_link = svg_link.data(data.links, d => {
        return d.source.id + '-' + d.target.id
      });

      svg_link = svg_link
        .enter()
        .append('line')
        .attr('stroke-width', function (d) {
          return Math.sqrt(d.value)
        })
        .merge(svg_link)

      svg_node = svg_node
        .data(data.nodes, d => {
          return d.id
        })
        .enter()
        .append('circle')
        .attr('r', 10)
        .attr('fill', function (d, i) {
          return color(d.group)
        })
        .merge(svg_node)
        .call(
          d3
            .drag()
            .on('start', dragstarted)
            .on('drag', dragged)
            .on('end', dragended)
        );

      simulation.nodes(data.nodes)
      simulation.force('link').links(data.links)
      simulation.alpha(1).restart()
    });
  }
};
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
  top: 100px;
  left: 100px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  border: 2px solid #ddd;
  background: #fff;
  opacity: 1;
  color: #000;
  padding: 10px;
  width: 300px;
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
  padding: 3px 0 0 0;
  margin: 3px 0 3px 0;
}

.tooltip .title {
  font-size: 20px;
  line-height: 24px;
}

.tooltip .name {
  font-weight: bold;
}
</style>

<template>

  <body>
  </body>
</template>

<script>
/* eslint-disable */
import * as d3 from '../../assets/js/d3.v3.min.js'

export default {
  name: 'comb',
  mounted () {
    var width = 600;
    var height = 600;
    var img_w = 50;
    var img_h = 50;

    var svg = d3.select("body").append("svg")
      .attr("width", width)
      .attr("height", height);

    d3.json("../../assets/relation.json", function (error, root) {

      if (error) {
        return console.log(error);
      }
      console.log(root);

      var force = d3.layout.force()
        .nodes(root.nodes)
        .links(root.edges)
        .size([width, height])
        .linkDistance(200)
        .charge(-1500)
        .start();


      var edges_line = svg.selectAll("line")
        .data(root.edges)
        .enter()
        .append("line")
        .style("stroke", function (d) {
          if (d.relation == "引用")
            return "#ccc"
          else
            return "#000"
        })
        .style("stroke-width", 2.0)
        .style("stroke-dasharray", function (d) {
          if (d.relation == "撰写")
            return "20,10,5,5,10";
          else if (d.relation == "其他")
            return "5,5";
        });

      var edges_text = svg.selectAll(".linetext")
        .data(root.edges)
        .enter()
        .append("text")
        .attr("class", "linetext")
        .text(function (d) {
          return d.relation;
        });

      var tooltip = d3.select("body")
        .append("div")
        .attr("class", "tooltip")
        .style("opacity", 0.0)


      var drag = force.drag()
        .on("dragstart", function (d, i) {

          root.nodes.forEach(function (node) {
            if (node != d)
              node.fixed = false;
          });

          d.fixed = true;    //拖拽开始后设定被拖拽对象为固定
          tooltip.style("opacity", 0.0)
          //label_text_2.text("拖拽状态：开始");
        })
        .on("dragend", function (d, i) {
          //label_text_2.text("拖拽状态：结束");
        })
        .on("drag", function (d, i) {
          //label_text_2.text("拖拽状态：进行中");
        });

      var nodes_img = svg.selectAll("image")
        .data(root.nodes)
        .enter()
        .append("image")
        .attr("width", img_w)
        .attr("height", img_h)
        .attr("xlink:href", function (d) {
          return d.image;
        })
        .on("mouseover", function (d, i) {

          //显示连接线上的文字
          edges_text.style("fill-opacity", function (edge) {
            if (edge.source === d || edge.target === d) {
              return 1.0;
            }
          });

          //加粗关联线
          edges_line.style("stroke-width", function (edge) {
            if (edge.source === d || edge.target === d)
              return 3.0;
            else
              return 2.0;
          });

          //图片放大
          nodes_img.transition()
            .attr("width", function (node) {
              if (node == d)
                return img_w * 1.1;
              else
                return img_w;
            })
            .attr("height", function (node) {
              if (node == d)
                return img_h * 1.1;
              else
                return img_h;
            })

          tooltip.html("<p>" + d.name + "</p>")
            .transition()
            .style("left", (d.x + 30) + "px")
            .style("top", (d.y + 30) + "px")
            .style("opacity", 1.0)

        })
        .on("mouseout", function (d, i) {
          //隐去连接线上的文字
          edges_text.style("fill-opacity", function (edge) {
            if (edge.source === d || edge.target === d) {
              return 0.0;
            }
          });

          //连接线粗细变正常
          edges_line.style("stroke-width", function (edge) {
            return 2.0;
          });

          //图片大小变正常
          nodes_img.attr("width", img_w)
          nodes_img.attr("height", img_h)

          tooltip.style("opacity", 0.0)
        })
        .on("click", function (d, i) {
          //跳转至论文界面
        })
        .call(drag);

      var text_dx = -20;
      var text_dy = 20;

      var nodes_text = svg.selectAll(".nodetext")
        .data(root.nodes)
        .enter()
        .append("text")
        .attr("class", "nodetext")
        .attr("dx", text_dx)
        .attr("dy", text_dy)
        .text(function (d) {
          return d.name;
        });

      //力学图运动开始时
      force.on("start", function () {
        //label_text_1.text("运动状态：开始");
      });

      //力学图运动结束时
      force.on("end", function () {
        //label_text_1.text("运动状态：结束");
      });

      force.on("tick", function () {

        //修改标签文字
        //label_text_1.text("运动状态：进行中");

        //限制结点的边界
        root.nodes.forEach(function (d, i) {
          d.x = d.x - img_w / 2 < 0 ? img_w / 2 : d.x;
          d.x = d.x + img_w / 2 > width ? width - img_w / 2 : d.x;
          d.y = d.y - img_h / 2 < 0 ? img_h / 2 : d.y;
          d.y = d.y + img_h / 2 + text_dy > height ? height - img_h / 2 - text_dy : d.y;
        });

        //更新连接线的位置
        edges_line.attr("x1", function (d) { return d.source.x; });
        edges_line.attr("y1", function (d) { return d.source.y; });
        edges_line.attr("x2", function (d) { return d.target.x; });
        edges_line.attr("y2", function (d) { return d.target.y; });

        //更新连接线上文字的位置
        edges_text.attr("x", function (d) { return (d.source.x + d.target.x) / 2; });
        edges_text.attr("y", function (d) { return (d.source.y + d.target.y) / 2; });


        //更新结点图片和文字
        nodes_img.attr("x", function (d) { return d.x - img_w / 2; });
        nodes_img.attr("y", function (d) { return d.y - img_h / 2; });

        nodes_text.attr("x", function (d) { return d.x });
        nodes_text.attr("y", function (d) { return d.y + img_w / 2; });
      });
    });

  }
}
</script>

<style scoped>
.labeltext {
  font-size: 16px;
  font-family: SimHei, "Microsoft YaHei";
  fill: #000000;
}

.nodetext {
  font-size: 12px;
  font-family: SimHei, "Microsoft YaHei";
  fill: #000000;
  fill-opacity: 0;
}

.linetext {
  font-size: 12px;
  font-family: SimHei, "Microsoft YaHei";
  fill: #000000;
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

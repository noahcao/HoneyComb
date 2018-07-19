<template>
  <div>
    <nav-bar></nav-bar>
    <div class="comb-container">
      <div class="col-xs-12 col-md-2 tool-bar"></div>

      <div class="col-xs-12 col-md-8 comb">
        <!-- <d3-force></d3-force> -->
      </div>

      <div class="col-xs-12 col-md-2 abstract-bar">
        <div class="abs-title">
          Title-xxxxxxx-xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        </div>
        <div class="abs-author">
          Jinkun Cao, Tianyi Xie, Ke Ma, Jinhao Zhu
        </div>
        <div class="abs-abs">
          Rich text editors are built to help people write text. Yet surprisingly, most rich text editors have no idea what text the user composed. These editors see their content through the same lens a web developer does: the DOM. This presents an impedance mismatch since the DOM is made up of Nodes organized in an unbalanced tree, whereas text is made up of lines, words and characters. There is no DOM API where characters is the unit of measure. With this limitation, most rich text editors cannot answer simple questions such as “What text is in this range?” or “Is the cursor on bolded text?” Trying to build rich editing experiences on top of such primitives is very difficult and frustrating.
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import NavBar from '../main/NavBar'
import Footer from '../main/Footer'
import d3Force from './D3_force'
import $ from 'jquery'
export default {
  name: 'outcomb',
  components: {
    NavBar,
    Footer,
    d3Force
  },
  mounted () {
    $('canvas').remove()
  }
}
</script>

<style scoped>
.tool-bar,
.abstract-bar,
.comb {
  height: 100%;
}

.tool-bar {
  background-color: black;
}
.abstract-bar {
  background-color: lavender;
}

.abs-title {
  word-break: break-all;
  white-space: normal;
  font-size: 24px;
}
.abs-author{
  margin-top: 10px;
  font-size: 20px
}
.abs-abs{
  margin-top: 10px;
  
}
.comb {
  background-color: dimgray;
}
.comb-container {
  width: 100%;
  position: absolute;
  top: 70px;
  bottom: 0px;
  left: 0px;
}
</style>

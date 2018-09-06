<template>
  <div>
    <nav-bar></nav-bar>
    <div class="comb-container">
      <div class="col-xs-12 col-md-2 tool-bar">
        <select-box></select-box>
      </div>

      <div class="col-xs-12 col-md-10 comb">
        <d3-force></d3-force>
      </div>
    </div>
  </div>
</template>

<script>

import NavBar from '../main/NavBar'
import Footer from '../main/Footer'
import d3Force from './D3_force'
import SelectBox from './SelectBox.vue'

export default {
  name: 'outcomb',
  components: {
    NavBar,
    Footer,
    d3Force,
    SelectBox
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
  background-color: #333;
}
.abstract-bar {
  background-color: white;
}

.abs-title {
  word-break: break-all;
  white-space: normal;
  font-size: 24px;
}
.abs-author {
  margin-top: 10px;
  font-size: 20px;
}
.abs-abs {
  margin-top: 10px;
}
.comb {
  background-color: #eee;
}
.comb-container {
  width: 100%;
  position: absolute;
  top: 70px;
  bottom: 0px;
  left: 0px;
}

</style>

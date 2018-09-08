<template>
  <div>
    <nav-bar></nav-bar>
    <div class="tips">
      <h1>Please use this function in PC!</h1>
    </div>
    <div class="comb-container">
      <div class="col-md-0 col-lg-2 tool-bar">
        <select-box></select-box>
      </div>

      <div class="col-md-12 col-lg-10 comb">
        <d3-force :paperId='paperId'></d3-force>
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
  },
  data () {
    return {
      paperId: this.$route.params["paperId"],
    }
  }
}
</script>

<style scoped>
.tool-bar,
.abstract-bar,
.comb {
  height: 100%;
}

.comb{
  display: none;
}

.tool-bar {
  display: none;
}

@media(min-width: 800px){
  .comb {
    display: block;
  }
  .tips{
    display: none;
  }
}

@media (min-width: 1200px) {
  .tool-bar{
    display: block;
  }
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

/* eslint-disable */
<template>
  <div id="community">
    <nav-bar style='margin-bottom:1px'></nav-bar>
    <div class='row' style='height:100%'>
      <div class="col-md-2" id='sidebar'>
        <sidebar></sidebar>
      </div>
      <div class="col-md-10" id='posterboard'>
        <posterboard></posterboard>
      </div>
    </div>
  </div>
</template>

<script>
  /* eslint-disable */
  import NavBar from '../main/NavBar'
  import editor from './editor'
  import sidebar from './sidebar'
  import posterboard from './posterboard'
  export default {
    name: 'outcomb',
    components: {
      NavBar,
      editor,
      sidebar,
      posterboard
    }
  }
</script>

<style scoped>
  #community{
    height: 750px;
  }
  #sidebar{
    background-color: black;
    height: 100%
  }
  #sidebar{
    background-color: aqua;
    height: 100%
  }
  #posterboard{
    background-color: bisque;
    height: 100%;
    padding-left: 0;
    padding-right: 0
  }
</style>

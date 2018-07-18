/* eslint-disable */
<template>
  <div id="community">
    <nav-bar></nav-bar>
    <editor></editor>
  </div>
</template>

<script>
  /* eslint-disable */
  import NavBar from '../main/NavBar'
  import editor from './editor'
  export default {
    name: 'outcomb',
    components: {
      NavBar,
      editor
    }
  }
</script>

<style scoped>
  #community{
    height: 500px;
  }
</style>

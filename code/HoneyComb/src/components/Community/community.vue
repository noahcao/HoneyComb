<template>
  <div>
    <nav-bar></nav-bar>
    <editor></editor>
  </div>
</template>

<script>
  import NavBar from '../main/NavBar'
  import editor from './index'
  export default {
    name: 'outcomb',
    components: {
      NavBar,
      editor
    }
  }
</script>

<style scoped>
</style>

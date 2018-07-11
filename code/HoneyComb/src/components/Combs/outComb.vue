<template>
  <div>
    <nav-bar></nav-bar>
    <d3-force></d3-force>
  </div>
</template>

<script>
import NavBar from '../main/NavBar'
import Footer from '../main/Footer'
import d3Force from './D3_force'
export default {
  name: 'outcomb',
  components: {
    NavBar,
    Footer,
    d3Force
  }
}
</script>

<style scoped>
</style>

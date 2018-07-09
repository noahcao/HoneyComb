<template>
  <div>
    <nav-bar></nav-bar>
    <comb></comb>
  </div>
</template>

<script>
import NavBar from '../main/NavBar'
import Footer from '../main/Footer'
import comb from './comb'
export default {
  name: 'outcomb',
  components: {
    NavBar,
    Footer,
    comb
  }
}
</script>

<style scoped>
</style>

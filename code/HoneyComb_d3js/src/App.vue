<template>
  <div id="app">
    <D3Force></D3Force>
  </div>
</template>

<script>
import D3Force from './components/D3_force.vue'
export default {
  name: 'app',
  components: { D3Force }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
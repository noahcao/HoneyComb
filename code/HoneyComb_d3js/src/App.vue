<template>
  <div id="app">
    <paper-net></paper-net>

  </div>
</template>

<script>
import PaperNet from './components/PaperNet.vue'
export default {
  name: "app",
  components: {
    PaperNet
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
<template>
    <nav-bar></nav-bar>
</template>

<script>
import a from '../assets/js/canvas-nest'
import navBar from '../components/main/NavBar'
export default {
  name: 'Main',
  components: {
    navBar
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>
<template>
  <div>
    <nav-bar></nav-bar>
    <main-body></main-body>
  </div>
</template>

<script>
// eslint-disable-next-line
import a from '../assets/js/canvas-nest'
import navBar from '../components/main/NavBar'
import mainBody from '../components/main/Body'
export default {
  name: 'Main',
  components: {
    navBar,
    mainBody
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

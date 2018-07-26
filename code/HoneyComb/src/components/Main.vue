<template>
  <div>
    <nav-bar></nav-bar>
    <main-body></main-body>
  </div>
</template>

<script>
import mainBody from '../components/main/Body'
import navBar from '../components/main/NavBar'
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

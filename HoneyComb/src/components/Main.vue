<template>
  <div>
    <nav-bar></nav-bar>
    <main-body></main-body>
  </div>
</template>

<script>
// eslint-disable-next-line
import mainBody from "../components/main/Body";export default {
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

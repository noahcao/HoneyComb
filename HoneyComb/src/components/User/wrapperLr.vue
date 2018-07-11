<template>
  <div>
    <Login v-if="status"></Login>
    <register v-if="!status"></register>
  </div>
</template>

<script>
import Login from './Login'
import Register from './Register'
export default {
  name: 'wrapper-lr',
  props: ['status'],
  components: {
    Login,
    Register
  },
  mounted () {
    console.log(this.status)
  }
}
</script>

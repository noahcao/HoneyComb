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
  components: {
    Login,
    Register
  },
  data () {
    return {
      status: false
    }
  },
  watch: {
    'data.LRList': {
      handler: function (val, oldval) {
        console.log('status change')
        this.status = val[0].status
      },
      deep: true
    }
  }
}
</script>

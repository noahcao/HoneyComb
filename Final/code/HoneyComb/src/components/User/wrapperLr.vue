<template>
  <div>
    <Login v-if="status"></Login>
    <register v-if="!status"></register>
    <h2 class="hide">{{status}}</h2>
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
      status: this.data.LRList[0].status
    }
  },
  watch: {
    'data.LRList': {
      handler: function (val, oldval) {
        this.status = val[0].status
        console.log('status change ' + this.status)
      },
      deep: true
    }
  }
}
</script>

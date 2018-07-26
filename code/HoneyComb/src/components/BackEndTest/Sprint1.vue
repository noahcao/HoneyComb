<template>
  <div></div>
</template>

<script>
export default {
  data () {
    return {
      name: 'testuser',
      pwd: '123',
      email: 'test@testuser.top'
    }
  },
  mounted () {
    console.time('register time: ')
    this.$http.post('/registerhandle', { name: this.name, pwd: this.pwd, email: this.email })
      .then((res) => {
        console.timeEnd('register time: ')
        if (res.data.id !== null) {
          console.log('success')
        } else {
          if (res.data.name === null) {
            console.log('username exsited')
          }
          if (res.data.email === null) {
            console.log('email exsited')
          }
        }
        this.loginTest()
      })
  },
  methods: {
    loginTest () {
      console.time('login time: ')
      this.$http.post('/loginhandle', { name: this.name, pwd: this.pwd })
        .then((res) => {
          console.timeEnd('login time: ')
          var id = null
          if (res.data.id !== null) {
            id = res.data.id
            console.log('success')
          } else {
            console.log('username or password wrong')
          }
          this.getuserTest(id)
        })
    },
    getuserTest (id) {
      console.time('getuser time: ')
      this.$http.post('/getuser', { id: id })
        .then((res) => {
          console.timeEnd('getuser time: ')
        })
    }
  }
}
</script>

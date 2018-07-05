<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <input type="file" id="pic">
    <img :src="icon">
    <button type="button" @click="submit"></button>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      icon: ''
    }
  },
  methods: {
    submit () {
      var pic = document.getElementById('pic').files[0]
      var r = new FileReader() // 本地预览
        r.readAsDataURL(pic) // Base64
        r.onload = () => {
          this.$http.post('/updateicon', { icon: r.result, id: 0 })
            .then((response) => {
              this.icon = response.data.icon
              console.log(response.data)
            })
        }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

<template>
  <div>
    <nav-bar></nav-bar>
    <div class='loader loader--audioWave' id="loading1"></div>
    <div v-for="paper in papers">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h3 class="panel-title">{{paper.title}}</h3>
      </div>
      <div class="panel-body">
        {{paper._abstract}}
      </div>
    </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import NavBar from '../main/NavBar'
export default {
  name: 'search',
  components: {
    NavBar
  },
  data () {
    return {
      content: this.$route.params.content,
      papers: null
    }
  },
  mounted () {
    this.$http.get('/searchpaper', { params: { key: this.content } })
      .then((res) => {
        this.papers = res.data.papers
        $('#loading1').remove()
      })
  }
}

</script>

<style scoped>
.loader {
  margin: 4em auto;
}
.loader--audioWave {
  width: 3em;
  height: 2em;
  background: linear-gradient(#24292e, #24292e) 0 50%,
    linear-gradient(#24292e, #24292e) 0.625em 50%,
    linear-gradient(#24292e, #24292e) 1.25em 50%,
    linear-gradient(#24292e, #24292e) 1.875em 50%,
    linear-gradient(#24292e, #24292e) 2.5em 50%;
  background-repeat: no-repeat;
  background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em,
    0.5em 0.25em;
  animation: audioWave 1.5s linear infinite;
}
@keyframes audioWave {
  25% {
    background: linear-gradient(#3498db, #3498db) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em,
      0.5em 0.25em;
  }
  37.5% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#3498db, #3498db) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em,
      0.5em 0.25em;
  }
  50% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#3498db, #3498db) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em,
      0.5em 0.25em;
  }
  62.5% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#3498db, #3498db) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em,
      0.5em 0.25em;
  }
  75% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#3498db, #3498db) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em,
      0.5em 2em;
  }
}
</style>

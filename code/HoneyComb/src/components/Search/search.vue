<template>
<div>
  <nav-bar></nav-bar>
  <div class='loader loader--audioWave'></div>
</div>
</template>

<script>
import NavBar from '../main/NavBar'
export default {
  name: 'search',
  components: {
    NavBar
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
  background: linear-gradient(#24292e, #24292e) 0 50%, linear-gradient(#24292e, #24292e) 0.625em 50%, linear-gradient(#24292e, #24292e) 1.25em 50%, linear-gradient(#24292e, #24292e) 1.875em 50%, linear-gradient(#24292e, #24292e) 2.5em 50%;
  background-repeat: no-repeat;
  background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em;
  animation: audioWave 1.5s linear infinite;
}
@keyframes audioWave {
  25% {
    background: linear-gradient(#3498db, #3498db) 0 50%, linear-gradient(#24292e, #24292e) 0.625em 50%, linear-gradient(#24292e, #24292e) 1.25em 50%, linear-gradient(#24292e, #24292e) 1.875em 50%, linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em;
  }
  37.5% {
    background: linear-gradient(#24292e, #24292e) 0 50%, linear-gradient(#3498db, #3498db) 0.625em 50%, linear-gradient(#24292e, #24292e) 1.25em 50%, linear-gradient(#24292e, #24292e) 1.875em 50%, linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em;
  }
  50% {
    background: linear-gradient(#24292e, #24292e) 0 50%, linear-gradient(#24292e, #24292e) 0.625em 50%, linear-gradient(#3498db, #3498db) 1.25em 50%, linear-gradient(#24292e, #24292e) 1.875em 50%, linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em;
  }
  62.5% {
    background: linear-gradient(#24292e, #24292e) 0 50%, linear-gradient(#24292e, #24292e) 0.625em 50%, linear-gradient(#24292e, #24292e) 1.25em 50%, linear-gradient(#3498db, #3498db) 1.875em 50%, linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em;
  }
  75% {
    background: linear-gradient(#24292e, #24292e) 0 50%, linear-gradient(#24292e, #24292e) 0.625em 50%, linear-gradient(#24292e, #24292e) 1.25em 50%, linear-gradient(#24292e, #24292e) 1.875em 50%, linear-gradient(#3498db, #3498db) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em;
  }
}
</style>

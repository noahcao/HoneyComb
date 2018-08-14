<template>
  <div>
    <div class="media" v-for="paper in collects">
      <!-- <div class="media-left">
        <a href="#">
          <img class="media-object img-rounded" src="../../../assets/logo.png" alt="...">
        </a>
      </div> -->
      <div class="media-body">
        <h4 class="media-heading">
          <strong>{{paper.title}}</strong>
        </h4>
        <p> year: {{paper.year}}</p>
        <p>{{paper.abstract}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Trends',
  data () {
    return {
      id: this.data.id,
      collects: [
        { title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' },
        { title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' },
        { title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' },
        { title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' }
      ]
    }
  },
  mounted () {
    // this.$http.post('/getcollects', { id: this.id })
    //   .then((res) => {

    //   })
  }
}
</script>

<style scoped>
.media-heading {
  margin-top: 10px;
}
.media-object {
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
  height: 100px;
  border: 1px solid rgba(200, 200, 200, 0.6);
  border-radius: 4px;
}
.media {
  margin-top: 10px;
  background-color: rgba(255, 255, 255, 0.5);
  border-radius: 4px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 5px;
  padding-bottom: 10px;
}

</style>

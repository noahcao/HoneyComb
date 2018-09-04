<template>
  <div>
    <div class="media" v-for="paper in collects">
      <div class="media-body">
        <div class="d-inline-block">
          <h4 class="media-heading">
            <strong>{{paper.title}}</strong>
          </h4>
        </div>
        <div class="float-right">
          <button type="button" class="btn btn-default btn-sm" @click="opCollect(paper.paperid)">
            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
            <strong>{{paper.text}}</strong>
          </button>
        </div>
        <div>
          <p> year: {{paper.year}}</p>
          <p>{{paper.abstract}}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Collects',
  data () {
    return {
      id: this.data.id,
      collects: [
        { paperid: '1', text: 'Remove', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' },
        { paperid: '2', text: 'Remove', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' },
        { paperid: '3', text: 'Remove', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' },
        { paperid: '4', text: 'Remove', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '', abstract: 'This Intel technology is for application developers who are seeking to protect select code and data from disclosure or modification. Intel® SGX makes such protections possible through the use of enclaves, which are protected areas of execution in memory.' }
      ]
    }
  },
  methods: {
    opCollect: function (d) {
      var isRemove = false
      var index = null

      for (var i = 0; i < this.collects.length; i++) {
        if (d === this.collects[i].paperid) {
          index = i
          if (this.collects[i].text === 'Remove') {
            isRemove = true
          }
          break
        }
      }

      if (isRemove) {
        this.collects[index].text = 'Collect'
        // this.$http.post('/deleteCollection', { userid: this.id, paperid:d })
        //   .then((res) => {
        //     // to check
        //   })
      } else {
        this.collects[index].text = 'Remove'
        // this.$http.post('/addCollection', { userid: this.id, paperid:d })
        //   .then((res) => {
        //     // to check
        //   })
      }
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

.d-inline-block {
  display: inline;
}

.float-right {
  float: right;
}
</style>

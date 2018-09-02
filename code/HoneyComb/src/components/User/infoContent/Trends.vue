<template>
  <div>
    <div class="trends">
      <div class="trends-clear">
        <a @click="toClearAll">
          Clear all trends
          <span class="glyphicon glyphicon-trash clear-icon"></span>
        </a>
      </div>
      <div class="trend-item" v-for="trend in collects">
        <span>{{trend.time}}</span>
        <span class="trend-title">
          <a @click="">{{trend.title}}</a>
        </span>
        <a @click="toClearTrend">
          <span class="glyphicon glyphicon-remove float-right" aria-hidden="true"></span>
        </a>
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
        { time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' },
        { time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' },
        { time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' },
        { time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' }
      ]
    }
  },
  mounted () {
    // this.$http.post('/getTrends', { id: this.id })
    //   .then((res) => {

    //   })
  },
  method: {
    toClearAll () {
      // this.$http.post('/deleteAllTrends', { id: this.id })
      //   .then((res) => {

      //   })
    },
    toClearTrend () {
      // this.$http.post('/deleteATrend', { id: this.id })
      //   .then((res) => {

      //   })
    }

  }
}
</script>

<style scoped>
.trends {
  margin-top: 10px;
  background-color: rgba(255, 255, 255, 0.4);
  border-radius: 4px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 5px;
  padding-bottom: 10px;
}
.trend-block {
  margin-top: 20px;
  border-bottom: 1px #ddd solid;
}
.trend-item {
  margin-top: 10px;
  margin-bottom: 10px;
}

.trends {
  border-left: 1px #ddd;
}

.trends a {
  color: black;
}

.trend-title {
  padding-left: 20px;
}

.float-right {
  float: right;
}
.trends-clear {
  margin-top: 5px;
  padding-bottom: 8px;
  border-bottom: 1px #ddd solid;
}

.clear-icon {
  margin-left: 20px;
  padding-top: 4px;
}
</style>

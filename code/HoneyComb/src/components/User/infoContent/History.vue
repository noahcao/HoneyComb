<template>
  <div>
    <div class="historys">
      <div class="historys-clear">
        <a @click="toClearAll">
          Clear all historys
          <span class="glyphicon glyphicon-trash clear-icon"></span>
        </a>
      </div>
      <div class="history-item" v-for="history in collects">
        <span>{{history.time}}</span>
        <span class="history-title">
          <a>{{history.title}}</a>
        </span>
        <a v-on:click="toClearhistory(history.historyid)">
          <span class="glyphicon glyphicon-remove float-right" aria-hidden="true"></span>
        </a>
      </div>
    </div>
  </div>

</template>
<script>
export default {
  name: 'History',
  data () {
    return {
      id: this.data.id,
      collects: [
        { historyid: 1, time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' },
        { historyid: 2, time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' },
        { historyid: 3, time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' },
        { historyid: 4, time: '2018-9-2-9:29', title: 'A fast and elitist multiobjective genetic algorithm: NSGA-II', year: 2002, url: '' }
      ]
    }
  },
  methods: {
    toClearAll: function () {
      this.collects.splice(0, this.collects.length)
      // this.$http.post('/deleteAllhistory', { userid: this.id })
      //   .then((res) => {

      //   })
    },
    toClearhistory: function (d) {
      var index = null
      for (var i = 0; i < this.collects.lenths; i++) {
        if (this.collects[i].historyid === d) {
          index = i
          break
        }
      }
      this.collects.splice(index, 1)
      // this.$http.post('/deleteAhistory', { userid: this.id, historyid: })
      //   .then((res) => {

      //   })
    }
  },
  mounted () {

    // this.$http.post('/gethistorys', { id: this.id })
    //   .then((res) => {
    //     this.collects = res.data
    //   })
  }
}
</script>

<style scoped>
.historys {
  margin-top: 10px;
  background-color: rgba(255, 255, 255, 0.4);
  border-radius: 4px;
  padding-left: 20px;
  padding-right: 20px;
  padding-top: 5px;
  padding-bottom: 10px;
}
.history-block {
  margin-top: 20px;
  border-bottom: 1px #ddd solid;
}
.history-item {
  margin-top: 10px;
  margin-bottom: 10px;
}

.historys {
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
}

.historys a {
  color: black;
}

.history-title {
  padding-left: 20px;
}

.float-right {
  float: right;
}
.historys-clear {
  margin-top: 5px;
  padding-bottom: 8px;
  border-bottom: 1px #ddd solid;
}

.clear-icon {
  margin-left: 20px;
  padding-top: 4px;
}
</style>

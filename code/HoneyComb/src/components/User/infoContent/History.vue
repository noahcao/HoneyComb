<template>
  <div>
    <div class="historys">
      <div class="historys-clear">
        <a>
          Your history
        </a>
      </div>
      <div class="history-item" v-for="history in historys">
        <span>{{history.time}}</span>
        <span class="history-title">
          <a v-if="history.paper.url !== null" :href="history.paper.url" target="_blank">{{history.paper.title}}</a>
          <a v-else>{{history.paper.title}}</a>
        </span>
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
      historys: []
    }
  },
  methods: {

  },
  mounted () {
    this.$http.post('/gethistory', { userId: this.id })
      .then((res) => {
        this.historys = res.data.history
        console.log(this.historys)
      })
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

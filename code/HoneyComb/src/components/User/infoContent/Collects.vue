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
          <button type="button" class="btn btn-default btn-sm" @click="opCollect(paper.id)">
            <span class="glyphicon glyphicon-star" aria-hidden="true"></span>
            <strong>{{paper.text}}</strong>
          </button>
        </div>
        <div>
          <p> year: {{paper.year}}</p>
          <p>{{paper._abstract}}</p>
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
      collects: []
    }
  },
  methods: {
    opCollect: function (d) {
      var isRemove = false
      var index = null

      for (var i = 0; i < this.collects.length; i++) {
        if (d === this.collects[i].id) {
          index = i
          if (this.collects[i].text === 'Remove') {
            isRemove = true
          }
          break
        }
      }

      console.log(this.collects)
      console.log(index)

      if (isRemove) {
        var newCollect = this.collects[index]
        newCollect.text = 'Collect'
        this.collects.splice(index, 1, newCollect)
        // this.collects[index].text = 'Collect'
        console.log(this.id)
        this.$http.post('/deletestar', { userId: this.id, paperId: d })
          .then((res) => {

          })
      } else {
        var newCollect = this.collects[index]
        newCollect.text = 'Remove'
        this.collects.splice(index, 1, newCollect)
        // this.collects[index].text = 'Remove'
        this.$http.post('/addstar', { userId: this.id, paperId: d })
          .then((res) => {

          })
      }
    }
  },
  mounted () {
    this.$http.post('/getstar', { userId: 1 })
      .then((res) => {
        this.collects = res.data.stars
        for (var i = 0; i < this.collects.length; i++) {
          this.collects[i].text = 'Remove'
        }
      })
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

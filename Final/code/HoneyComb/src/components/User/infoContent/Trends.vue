<template>
  <div>
    <div v-for="trend in trends" :key="trend.id">
      <div class="trend-block">
        <div v-if="trend.type==='panel'">
          <div>
            You added a new penal:
          </div>
          <div class="content">
            <a class="trend-title">
              <strong>{{trend.title}}</strong>
            </a>
          </div>
          <div>
            <p>{{trend.time}}</p>
          </div>
        </div>
        <div v-else-if="trend.type==='post'">
          <div>
            You posted
            <!-- <a class="trend-title">
              <strong>{{trend.title}}</strong>
            </a> -->
            :
          </div>
          <div class="content">
            {{trend.content}}
          </div>
          <div>
            <p>{{trend.time}}</p>
          </div>
        </div>
        <div v-else>
          <div>
            You commented on
            <strong>{{trend.target}}</strong>
            :
          </div>
          <div class="content">
            {{trend.content}}
          </div>
          <div>
            <p>{{trend.time}}</p>
          </div>
        </div>
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
      trends: [
        // { type: 'panel', title: 'A new panel', time: '2018-9-5-13:14' },
        // { type: 'post', title: 'A new panel', content: 'This is a post', poster: 'someone', time: '2018-9-4-12:13' },
        // { type: 'comment', content: 'This is a comment', commentator: '555', poster: 'someone', time: '2018-9-6-17:01' }
      ]
    }
  },
  methods: {
    timeSort: function () {
      this.trends.sort(function (a, b) {
        if (a.time < b.time) {
          return 1
        } else {
          return -1
        }
      })
    }

  },
  mounted () {
    this.$http.post('/userpanel', { userId: this.id })
      .then((res) => {
        var panelArray = res.data.panels
        for (var i = 0; i < panelArray.length; i++) {
          var panel = panelArray[i]
          panel.id = 'panel' + panel.id + i
          panel.type = 'panel'
          this.trends.push(panel)
        }
        this.timeSort()
      })

    this.$http.post('/userpost', { userId: this.id })
      .then((res) => {
        var postArray = res.data.posts
        for (var i = 0; i < postArray.length; i++) {
          var post = postArray[i]
          // console.log(post)
          post.id = 'post' + post.id + i
          post.content = post.content.replace(/<[/]p><p>/g, '\n')
          post.content = post.content.replace('</p>', '')
          post.content = post.content.replace('<p>', '')
          post.type = 'post'
          this.trends.push(post)
        }
        this.timeSort()
      })

    this.$http.post('./usercomment', { userId: this.id })
      .then((res) => {
        var commentArray = res.data.comments
        for (var i = 0; i < commentArray.length; i++) {
          var comment = commentArray[i]
          comment.id = 'comment' + comment.id + i
          comment.type = 'comment'
          this.trends.push(comment)
        }
        this.timeSort()
      })
  }
}
</script>

<style scoped>
.trend-block {
  width: 100%;
  padding-top: 16px;
  padding-bottom: 24px;
  border-bottom: 1px rgba(36, 41, 46, 0.5) solid !important;
  padding-left: 24px;
  padding-right: 48px;
}
.trend-title {
  color: black;
}

.trend-block p {
  float: right;
}

.content {
  margin-top: 5px;
  padding-left: 30px;
}
</style>

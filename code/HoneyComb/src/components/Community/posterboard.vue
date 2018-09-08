<template>
    <div id="posterboard1">
        <div class="col-xs-12 col-sm-6 col-md-3" v-for="panel in panels" :key="panel.id">
            <div class="posterwrapper">
                <div class="poster">
                    <div class="card card-body postercard">
                        <router-link :to="{ name: 'poster', params: { panelid: panel.id }}">
                            <h3 class="card-title dark-text">{{panel.title}}</h3>
                        </router-link>
                        <a href="#">{{panel.ownerName}}</a>
                        <p class="card-time">posted at: {{panel.time}}</p>
                        <hr class="my-4">
                        <p v-if="panel.posts.length > 0" class="card-text">{{panel.posts[0].content}}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
export default {
    data () {
        return {
            items: [1, 2, 3, 4, 5, 6, 7, 8],
            poster: {
                id: 1,
                title: "poster title",
                username: "cjk",
                posttime: "2018/7/23 16:03",
                abstract: "This is a poster self-defined for the test on the front-end project. The default display effect is designed to hide overflow text content and adopt the design style defined in Google's Material Design language."
            },
            panels: []
        }
    },
    created () {
        this.$http.post('/getlatestpanels')
            .then((res) => {
                console.log(res.data.panels)
                this.panels = res.data.panels;
                var i = 0
                for (i; i < this.panels.length; i++) {
                    if (this.panels[i].posts.length == 0) {
                        continue
                    }
                    var j = 0;
                    j = this.panels[i].posts[0].content
                    j = j.replace(/<[/]p><p>/g, "\n")
                    j = j.replace("</p>", "")
                    j = j.replace("<p>", "")
                    this.panels[i].posts[0].content = j
                }
            })
    },
    methods: {
        toPoster () {
            alert("jump to a poster page");
        }
    }
}
</script>

<style scoped>
@import "../../../static/css/mdb.css";
* {
  margin: 0px;
}

.card-title {
  color: black;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.card-text {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 8;
  -webkit-box-orient: vertical;
}
.poster {
  margin: 20px 5px 0 5px;
  padding: 2px 5px 2px 5px;
  height: 95%;
}
.postercard {
  height: 100%;
  background-color: white;
  padding: 10px;
}
.posterwrapper {
  margin: 0;
  height: 45vh;
}
.postercontent {
  word-wrap: break-word;
  word-break: break-all;
  display: -webkit-box;
  -webkit-line-clamp: 11;
  overflow: hidden;
  -webkit-line-break: auto;
  -webkit-box-orient: vertical;
}
.poster h3 {
  padding-left: 10px;
  padding-right: 10px;
  padding-bottom: 10px;
}
.poster a {
  padding-left: 10px;
}
.poster p {
  padding-left: 10px;
  padding-right: 10px;
  padding-top: 10px;
}
.card-time {
  padding: 10px;
}
</style>


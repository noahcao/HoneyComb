<template>
    <div id="posterpage">
        <nav-bar style='margin-bottom:1px'></nav-bar>
        <div id="panelboard">
            <div class="jumbotron" id="panel">
                <div class="row jumbotron" id="buttonbar">
                    <div class="col-xs-12 col-sm-9">
                        <h2 class="h2-reponsive mb-3" id="paneltitle">
                            <strong>{{paneltitle}}</strong>
                        </h2>
                    </div>
                    <div class="col-xs-12 col-sm-3" style="padding:0">
                        <button type="button" class="btn btn-outline-warning waves-effect self-btn">
                            <i class="fa fa-star pr-2" aria-hidden="true"> Star</i>
                        </button>
                        <button type="button" class="btn btn-outline-info waves-effect px-3 self-btn" @click="showeditor=!showeditor">
                            <i class="fa fa-pencil-square-o" aria-hidden="true"> Post</i>
                        </button>
                        <button type="button" class="btn btn-outline-secondary waves-effect px-3 self-btn">
                            <i class="fa fa-share-alt" aria-hidden="true"> Share</i>
                        </button>
                    </div>
                </div>
                <div v-for="poster in posters" :key="poster.id">
                    <div class="jumbotron poster">
                        <a href="#">{{poster.userName}}</a>posted at {{poster.time}}
                        <hr class="my-4">
                        <div v-html="poster.content" class='contentboard'>
                        </div>
                        <div v-for="comment in poster.comments" :key="comment.id" class="commentboard">
                            <div class="jumbotron jumbotron-fluid comment">
                                <a href="#">{{comment.userName}}</a> post at: {{comment.time}}
                                <hr class="my-4" style="margin:0">
                                <div class="comment-content" v-html="comment.content" style="padding-left:10px;padding-right:10px">
                                </div>
                            </div>
                        </div>
                        <div class="row" id="newcomment">
                            <div class="md-form input-group row" style="width:100%">
                                <div class="col-xs-12 col-sm-11">
                                    <input type="text" class="form-control" placeholder="Add a comment" aria-label="Add a comment" aria-describedby="basic-addon2" v-model="newComments[poster.id]">
                                </div>
                                <div class="col-xs-12 col-sm-1" style="float:right">
                                    <button class="btn btn-outline-elegant waves-effect" type="button" @click="addComment(panelinfo.id, poster.id)">
                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="editorboard" v-if="showeditor">
            <editor :panelId="panelid"></editor>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import NavBar from '../main/NavBar'
import editor from './editor'
export default {
    name: 'poster',
    components: {
        NavBar,
        editor
    },
    methods: {
        getusername (userid) {
            this.$http.post('/getuser', { id: userid })
                .then((res) => {
                    console.log(res.data);
                    console.log(res.data.name);
                    return res.data.name;
                })
        },
        addComment (panelid, postid) {
            if (this.data.id === null) {
                alert("Please login first!")
                return
            }
            console.log(this.data.id)
            console.log(panelid)
            this.$http.post('/addcomment', {                panelId: panelid, postId: postid, content: this.newComments[postid],
                userId: this.data.id            })
                .then((res) => {
                    alert("Successfult add a comment!");
                    this.$http.post('/getpanel', { id: this.panelid })
                        .then((res) => {
                            console.log(this.panelid);
                            console.log(res.data);
                            console.log(res.data.title);
                            this.panelinfo = res.data;
                            this.posters = res.data.posts;
                            this.paneltitle = res.data.title;
                            for (var i = 0; i < this.posters.length; i++) {
                                this.newComments.push[""];
                            }
                        })
                })
        },
        getUserName (userId) {
            this.$http.post('/getusername', { id: this.data.id })
                .then((res) => {
                    alert(res.data)
                })
        }
    },
    created () {
        this.$http.post('/getpanel', { id: this.panelid })
            .then((res) => {
                console.log(this.panelid);
                console.log(res.data);
                console.log(res.data.title);
                this.panelinfo = res.data;
                this.posters = res.data.posts;
                this.paneltitle = res.data.title;
                for (var i = 0; i < this.posters.length; i++) {
                    this.newComments.push[""];
                }
            })
    },
    data () {
        return {
            showeditor: false,
            panelid: this.$route.params.panelid,
            panelinfo: null,
            posters: null,
            paneltitle: null,
            newComments: [],
            tempPoster: null
        }
    }
}
</script>

<style scoped>
@import "../../../static/css/mdb";
.comment-content {
  word-wrap: break-word;
}

.poster_layout {
  border: solid;
  background-color: whitesmoke;
  margin: 0 2% 1% 2%;
  width: 96%;
  min-height: 90%;
}
#posterpage {
  min-height: 1000px;
}
#panelboard {
  height: 90%;
  padding-top: 2%;
  width: 90%;
  margin-left: 5%;
}
#panel {
  padding: 5px 5px 5px 5px;
}
#buttonbar {
  padding-top: 10px;
  padding-bottom: 10px;
  width: 100%;
  margin-left: 0%;
  margin-bottom: 1%;
}
#paneltitle {
  padding-top: 0px;
  font-size: 250%;
  margin: 0;
}
#rightwrapper {
  margin: 2% 0 0 0;
  padding: 0 2% 0 0;
}
#rightboard {
  background-color: aqua;
  height: 90%;
  padding-top: 5%;
}
.contentboard {
  padding: 1% 2% 2% 2%;
  margin-bottom: 2px;
}
.comment {
  width: 96%;
  margin: 10px 2% 0 2%;
  padding: 0;
}
.comment a {
  padding: 10px !important;
}
.comment div {
  padding-bottom: 10px;
}
.jumbotron p {
  padding: 5px 10px 5px 10px;
}
.poster h2 {
  padding: 5px 10px 5px 20px;
}
.poster a {
  padding: 5px 10px 0 25px;
}
.poster {
  padding-top: 5px;
  padding-bottom: 10px;
  margin-bottom: 10px;
}
.hr {
  padding: 0px;
  margin: 0px;
}
.self-btn {
  padding-left: 15px;
  padding-right: 15px;
  background-color: white !important;
}
#newcomment {
  margin: 0px 3% 0px 3%;
  width: 94%;
}
#editorboard {
  height: 500px;
  padding-bottom: 40px;
  margin-bottom: 50px;
}
@media (min-width: 768px) {
  .btn-outline-primary {
    padding: 8px 15px 8px 15px;
    margin-left: 10%;
  }
}
</style>


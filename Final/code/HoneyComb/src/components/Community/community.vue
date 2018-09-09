
<template>
  <section>
    <nav-bar></nav-bar>
    <div class="comBody">
      <!-- the side bar part-->
      <div class="col-md-2 col-sm-4" id='sidebar' v-if="showsidebar">
        <div class='mobile'>
          <!-- Checkbox to toggle the menu -->
          <div id='selectsection'>
            <form role='form' style='padding:15%;color:white'>
              <ul>
                <li>
                  <form class="form-inline md-form form-sm">
                    <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" aria-label="Search" v-model="content">
                    <i class="fa fa-search" @click="search()" aria-hidden="true"></i>
                  </form>
                </li>
                <li>
                  <input type="checkbox" @click="check(1)" class="form-check-input" id="check1"> Search in title
                </li>
                <li>
                  <input type="checkbox" @click="check(2)" class="form-check-input" id="check2"> Search in content
                </li>
                <li>
                  <input type="checkbox" @click="check(3)" class="form-check-input" id="check3"> Search in author
                </li>
                <li>
                  <button class="btn btn-primary1" @click="createNewPanel" @click.prevent="onSubmit">
                    <span class="fa plus" aria-hidden="true"></span>Create Panel</button>
                </li>
              </ul>
            </form>
          </div>
        </div>
      </div>

      <!-- the poster board part -->
      <div class="col-md-10 col-sm-8" id='posterboard' v-if="showPoster">
        <div id="posterboard1">
          <div class="col-xs-12 col-sm-6 col-md-3" v-for="panel in panels" :key="panel.id">
            <div class="posterwrapper">
              <div class="poster">
                <div class="card card-body postercard">
                  <router-link :to="{ name: 'poster', params: { panelid: panel.id }}">
                    <h3 class="card-title dark-text">{{panel.title}}</h3>
                  </router-link>
                  <a>{{panel.ownerName}}</a>
                  <p class="card-time">posted at: {{panel.time}}</p>
                  <hr class="my-4">
                  <p v-if="panel.posts.length > 0" class="card-text">{{panel.posts[0].content}}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- the new panel part -->
      <div class="col-md-10" id="newpanel" v-if="!showPoster">
        <div id="panelboard" style="padding-top:20px">
          <div id="functional">
            <!-- Title: <input style="margin:40px" v-model="newPanelTitle"> -->
            <!-- Material input -->
            <div class="col-sm-6 col-xs-12">
              <div class="md-form">
                <input type="text" id="form1" class="form-control control2" v-model="newPanelTitle">
                <label for="form1">Enter the title</label>
              </div>
            </div>
            <div class="col-sm-6 col-xs-5">
              <button class="btn btn-elegant" @click="createPanel" @click.prevent="onSubmit"> Post </button>
            </div>
          </div>
          <div id="panelEditor">
            <quill-editor class="quill" v-model="panelContent" v-if="!showMarkdown"></quill-editor>
          </div>
        </div>
      </div>

      <!-- Modal -->
      <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">

        <!-- Add .modal-dialog-centered to .modal-dialog to vertically center the modal -->
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              ...
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div>

    </div>
  </section>
</template>

<script>
/* eslint-disable */
import NavBar from '../main/NavBar'
import editor from './editor'
import posterboard from './posterboard'
import '../../../static/css/quill.core.css'
import '../../../static/css/quill.snow.css'
import '../../../static/css/quill.bubble.css'
import markdown from './markdown'
import { quillEditor } from 'vue-quill-editor'
export default {
  name: 'outcomb',
  components: {
    NavBar,
    markdown,
    quillEditor,
    posterboard
  },
  data () {
    return {
      showPoster: true,
      showsidebar: true,
      newPanelTitle: "",
      showMarkdown: false,
      anotherEditor: 'Markdown Mode',
      panelContent: "",
      panels: [],
      choice: 1,
      content: '',
      choices: ['title', 'content', 'user'],
      items: [1, 2, 3, 4, 5, 6, 7, 8],
      poster: {
        id: 1,
        title: "poster title",
        username: "cjk",
        posttime: "2018/7/23 16:03",
        abstract: "This is a poster self-defined for the test on the front-end project. The default display effect is designed to hide overflow text content and adopt the design style defined in Google's Material Design language."
      }
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
  mounted () {
    $('canvas').remove()
    $("#check1").prop("checked", true);
  },
  methods: {
    toPoster () {
      alert("Jump to a poster page!");
    },
    search () {
      if (this.content === '') {
        return
      }
      this.$http.post('/searchpanels', { key: this.content, type: this.choices[this.choice - 1] })
        .then((res) => {
          console.log(res.data.panels)
          this.panels = res.data.panels;
          console.log(this.content, this.choices[this.choice - 1])
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
    check (e) {
      if (e === 1) {
        $("#check1").prop("checked", true);
        $("#check2").prop("checked", false);
        $("#check3").prop("checked", false);
        this.choice = 1
      }
      else if (e === 2) {
        $("#check2").prop("checked", true);
        $("#check1").prop("checked", false);
        $("#check3").prop("checked", false);
        this.choice = 2
      }
      else if (e === 3) {
        $("#check3").prop("checked", true);
        $("#check1").prop("checked", false);
        $("#check2").prop("checked", false);
        this.choice = 3
      }
    },
    alterEditor () {
      if (this.showMarkdown) {
        this.showMarkdown = false;
        this.anotherEditor = 'MarkDown Mode';
        this.data.EditorContent = "";
        this.data.inMarkdown = true
      }
      else {
        this.showMarkdown = true;
        this.anotherEditor = 'RichText Mode';
        this.data.EditorContent = "";
        this.data.inMarkdown = true
      }
    },
    createNewPanel () {
      if (this.data.id > 0) {
        this.showsidebar = !this.showsidebar;
        this.showPoster = !this.showPoster;
      }
      else {
        alert("You must log in for creating a panel!");
      }
    },
    saveContent () {
      alert("存储内容进入数据库:" + this.data.EditorContent + "!")
    },
    createPanel () {
      if (this.newPanelTitle == "") {
        alert("Title can't be empty!");
        return;
      }
      else if (this.panelContent == "") {
        alert("Content of panel can't be empty!");
        return;
      }
      else {
        this.$http.post('/addpanel', { owner: this.data.id, title: this.newPanelTitle })
          .then((res) => {
            this.$http.post('/addpost', { panelId: res.data.id, userId: this.data.id, content: this.panelContent })
              .then((res) => {
                alert("Add a post at " + res.data.time + "!");
                this.panelContent = "";
                this.newPanelTitle = "";
                this.showPoster = true;
                this.showsidebar = true;
                window.location.reload()
              })
          })
      }
    }
  }
}
</script>

<style scoped>
@import "../../../static/css/mdb.css";

* {
  margin: 0px;
}

.fa-search {
  cursor: pointer;
}

.control2 {
  -webkit-box-shadow: 0 1px 0 0 rgba(18, 21, 23, 0.8);
  box-shadow: 0 1px 0 0 rgba(18, 21, 23, 0);
  border-bottom: 1.5px solid rgba(0, 0, 0, 0.8) !important;
  color: black !important;
}

.btn-primary1 {
  /* background-image: -webkit-linear-gradient(top,#337ab7 0,#265a88 100%); */
  background-image: none;
  /* background-image: -webkit-gradient(linear,left top,left bottom,from(#337ab7),to(#265a88)); */
  /* background-image: linear-gradient(to bottom,#337ab7 0,#265a88 100%); */
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff337ab7', endColorstr='#ff265a88', GradientType=0);
  filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
  background-repeat: repeat-x;
  border-color: #245580;
  background-color: rgba(158, 158, 158, 0.1);
}

.md-form input[type="text"][data-v-a19d5654],
.md-form input[type="password"][data-v-a19d5654],
.md-form input[type="email"][data-v-a19d5654],
.md-form input[type="url"][data-v-a19d5654],
.md-form input[type="time"][data-v-a19d5654],
.md-form input[type="date"][data-v-a19d5654],
.md-form input[type="datetime-local"][data-v-a19d5654],
.md-form input[type="tel"][data-v-a19d5654],
.md-form input[type="number"][data-v-a19d5654],
.md-form input[type="search-md"][data-v-a19d5654],
.md-form input[type="search"][data-v-a19d5654],
.md-form textarea.md-textarea[data-v-a19d5654] {
  -webkit-transition: border-color 0.15s ease-in-out,
    -webkit-box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out,
    -webkit-box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out,
    -webkit-box-shadow 0.15s ease-in-out;
  outline: none;
  -webkit-box-shadow: none;
  box-shadow: none;
  border: none;
  border-bottom: 1px solid rgba(255, 255, 255, 0.5);
  border-radius: 0;
  -webkit-box-sizing: content-box;
  box-sizing: content-box;
  background-color: transparent;
}

.md-form input[type="text"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="password"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="email"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="url"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="time"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="date"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="datetime-local"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="tel"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="number"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="search-md"][data-v-a19d5654]:focus:not([readonly]),
.md-form input[type="search"][data-v-a19d5654]:focus:not([readonly]),
.md-form textarea.md-textarea[data-v-a19d5654]:focus:not([readonly]) {
  -webkit-box-shadow: 0 1px 0 0 rgba(18, 21, 23, 0.8);
  box-shadow: 0 1px 0 0 rgba(18, 21, 23, 0);
  border-bottom: 1.5px solid rgba(255, 255, 255, 0.8);
}

.form-control {
  color: #f9f9f9;
}

#sidebar {
  background-color: black;
  height: 50%;
  z-index: -1;
}

.comBody {
  width: 100%;
  position: absolute;
  top: 70px;
  bottom: 0px;
  left: 0px;
  z-index: -1;
}
* {
  padding: 0;
}
h2 {
  color: #fff;
  font: 300 40px "Open Sans";
  width: 300px;
  padding: 30px;
  float: left;
}
.mobile {
  float: left;
  position: absolute;
  box-shadow: 0 0 25px 0 rgba(27, 27, 27, 0.5);
  overflow: hidden;
  width: 100%;
  height: 100%;
  background: #373535;
  padding: 5%;
  z-index: inherit;
}
#selectsection {
  height: 80%;
  width: 100%;
}
li {
  list-style-type: none;
  margin-bottom: 20px;
}
ul {
  list-style-type: none;
}
#newpanel {
  width: 100%;
  height: 100%;
  padding: 20px;
}
#panelEditor {
  padding: 0;
  height: 400px;
}
@media (min-width: 768px) {
  .btn-elegant {
    background-color: #2e2e2e !important;
    color: #fff !important;
    margin-top: 29px;
    margin-left: 15px;
  }
  #functional {
    margin-left: 5%;
    margin-right: 5%;
    width: 90%;
    height: 100px;
  }
  #sidebar {
    background-color: aqua;
    height: 100%;
    padding: 0;
  }
  #posterboard {
    background-color: whitesmoke;
    height: 100%;
    padding-left: 0;
    padding-right: 0;
    overflow: scroll;
  }
}

#posterboard {
  background-color: whitesmoke;
  height: 100%;
  padding-left: 0;
  padding-right: 0;
}

#functional {
  margin-left: 5%;
  margin-right: 5%;
  height: 120px;
}

.md-form label {
  font-size: 1.6rem;
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

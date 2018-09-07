/* eslint-disable */
<template>
  <div id="community">
    <nav-bar style='margin-bottom:1px'></nav-bar>
    <div class="comBody">

      <!-- the side bar part-->
      <div class="col-md-2" id='sidebar' v-if="showsidebar">
        <div class='mobile'>
          <!-- Checkbox to toggle the menu -->
          <div id='selectsection'>
            <form role='form' style='padding:20px;color:white'>
              <ul>
                <li>
                  <form class="form-inline md-form form-sm">
                    <input class="form-control form-control-sm mr-3 w-75" type="text" placeholder="Search" aria-label="Search">
                    <i class="fa fa-search" aria-hidden="true"></i>
                  </form>
                </li>
                <li>
                  <input type="checkbox" class="form-check-input"> Search in title
                </li>
                <li>
                  <input type="checkbox" class="form-check-input"> Search in content
                </li>
                <li>
                  <input type="checkbox" class="form-check-input"> Search in author
                </li>
                <li>
                  <button class="btn btn-primary1" @click="createNewPanel">
                    <span class="fa plus" aria-hidden="true"></span>Create Panel</button>
                </li>
              </ul>
            </form>
          </div>
        </div>
      </div>

      <!-- the poster board part -->
      <div class="col-md-10" id='posterboard' v-if="showPoster">
        <posterboard></posterboard>
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
              <button class="btn btn-elegant" @click="createPanel"> Post </button>
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
  </div>
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
      panels: []
    }
  },
  created () {
    this.$http.post('/getlatestpanels')
      .then((res) => {
        console.log(res.data.panels)
        this.panels = res.data.panels;
      })
  },
  methods: {
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
      alert("存储内容进入数据库:" + this.data.EditorContent)
    },
    createPanel () {
      if (this.newPanelTitle == "") {
        alert("title can't be empty!");
        return;
      }
      else if (this.panelContent == "") {
        alert("content of panel can't be empty!");
        return;
      }
      else {
        this.$http.post('/addpanel', { owner: this.data.id, title: this.newPanelTitle })
          .then((res) => {
            this.$http.post('/addpost', { panelId: res.data.id, userId: this.data.id, content: this.panelContent })
              .then((res) => {
                alert("add a post at " + res.data.time);
                this.panelContent = "";
                this.newPanelTitle = "";
                this.showPoster = true;
                this.showsidebar = true;
              })
          })
      }
    }
  }
}
</script>

<style scoped>
@import "../../../static/css/mdb.css";

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

#community {
  height: 750px;
}
#sidebar {
  background-color: black;
  height: 50%;
}

#posterboard {
  background-color: whitesmoke;
  height: 100%;
  padding-left: 0;
  padding-right: 0;
}
.comBody {
  width: 100%;
  position: absolute;
  top: 70px;
  bottom: 0px;
  left: 0px;
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
  padding-left: 10%;
}
#selectsection {
  margin-top: 10%;
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
}

#functional {
  margin-left: 5%;
  margin-right: 5%;
  height: 120px;
}

.md-form label{
    font-size: 1.6rem;
}

</style>

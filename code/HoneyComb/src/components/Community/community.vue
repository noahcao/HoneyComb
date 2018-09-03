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
                          <input placeholder="Search Poster" style='margin-bottom:10px'>
                      </li>
                      <li>
                          <input type="checkbox" class="form-check-input">
                          Material Design
                      </li>
                      <li>
                          <button @click="createNewPanel" style="color:black">Create Panel</button>
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
            Title: <input style="margin:40px" v-model="newPanelTitle">
            <button @click="createPanel"> Post </button>
          </div>
          <div id="panelEditor">
            <quill-editor class="quill" v-model="panelContent"
        v-if="!showMarkdown"></quill-editor>
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
  data (){
    return {
      showPoster: true,
      showsidebar: true,
      newPanelTitle: "",
      showMarkdown: false,
      anotherEditor: 'Markdown Mode',
      panelContent: ""
    }
  },
   methods:{
        alterEditor(){
            if(this.showMarkdown){
                this.showMarkdown = false;
                this.anotherEditor = 'MarkDown Mode';
                this.data.EditorContent = "";
                this.data.inMarkdown = true
            }
            else{
                this.showMarkdown = true;
                this.anotherEditor = 'RichText Mode';
                this.data.EditorContent = "";
                this.data.inMarkdown = true
            }
        },
        createNewPanel(){
          if(this.data.id > 0){
            this.showsidebar = !this.showsidebar;
            this.showPoster = !this.showPoster;
          }
          else{
            alert("You must log in for creating a panel!");
          }
        },
        saveContent(){
            alert("存储内容进入数据库:" + this.data.EditorContent)
        },
        createPanel(){
          if(this.newPanelTitle==""){
            alert("title can't be empty!");
            return;
          }
          else if(this.panelContent==""){
            alert("content of panel can't be empty!");
            return;
          }
          else{
            this.$http.post('/addpanel', {owner: this.data.id, title: this.newPanelTitle})
              .then((res) => {
                this.$http.post('/addpost', {panelId: res.data.id, userId: this.data.id, content: this.panelContent})
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
#community {
  height: 750px;
}
#sidebar {
  background-color: black;
  height: 100%;
}
#sidebar {
  background-color: aqua;
  height: 100%;
  padding: 0
}
#posterboard {
  background-color:whitesmoke;
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
  font: 300 40px 'Open Sans';
  width: 300px;
  padding: 30px;
  float: left;
}
.mobile {
  float: left;
  position: absolute;
  box-shadow: 0 0 25px 0 rgba(27, 27, 27, 0.5);
  overflow: hidden;
  width:100%;
  height:100%;
  background: #373535;
  padding-left: 10%
}
#selectsection{
    margin-top: 10%;
    height: 80%;
    width: 100%;
}
li{
    list-style-type: none;
    margin-bottom: 20px
}
ul{
    list-style-type:none;
}
#newpanel{
  width: 100%;
  height: 100%;
  padding: 20px
}
#panelEditor{
  padding: 0;
  height: 400px
}
#functional{
  margin-left: 10%;
  margin-right: 10%;
  width: 80%;
  height: 100px
}
</style>

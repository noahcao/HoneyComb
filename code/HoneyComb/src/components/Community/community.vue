/* eslint-disable */
<template>
  <div id="community">
    <nav-bar style='margin-bottom:1px'></nav-bar>
    <div class="comBody">

      <!-- the side bar part-->
      <div class="col-md-2" id='sidebar'>
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
                          <button @click="showPoster=!showPoster" style="color:black">Create Panel</button>
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
          <input style="margin:40px">
          <editor style="height:400px"></editor>
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
export default {
  name: 'outcomb',
  components: {
    NavBar,
    editor,
    posterboard
  },
  data (){
    return {
      showPoster: true
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
</style>

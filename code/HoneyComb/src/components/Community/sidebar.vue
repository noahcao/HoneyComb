<template>
  <div class='mobile'>
    <!-- Checkbox to toggle the menu -->
    <div id='selectsection'>
        <form role='form' style='padding:20px;color:white'>
            <ul>
                <li>
                    <input placeholder="Search Poster" style='margin-bottom:10px'>
                </li>
                <!--
                <li>
                    <select class="form-control">
                        <option>Default select</option>
                        <option>Default select</option>
                        <option>Default select</option>
                    </select>
                </li>
                -->
                <li>
                    <input type="checkbox" class="form-check-input">
                    Material Design
                </li>
                <li>
                    <button @click="data.showPosters=!data.showPosters" style="color:black">Create Panel</button>
                </li>
            </ul>
        </form>
    </div>
  </div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'sidebar',
  methods: {
  },

};
</script>

<style scoped>
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



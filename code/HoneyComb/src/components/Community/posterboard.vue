<template>
    <div id="posterboard">
        <div v-for="item in items" :key="item.id">
            <div class="col-xs-3 col-sm-3 posterwrapper">
                <div class="poster">
                    <div class="card card-body postercard">
                        <router-link :to="{ name: 'poster', params: { panelid: '5b5a827c3324a3e6507d4621' }}"><h3 class="card-title blue-text">{{poster.title}}</h3></router-link>
                        <a href="#">{{poster.username}}</a>posted at: {{poster.posttime}}
                        <hr class="my-4">
                        <p class="card-text">{{poster.abstract}}</p>
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
            items: [1,2,3,4,5,6,7,8],
            poster:{
                id: 1,
                title: "poster title",
                username: "cjk",
                posttime: "2018/7/23 16:03",
                abstract: "This is a poster self-defined for the test on the front-end project. The default display effect is designed to hide overflow text content and adopt the design style defined in Google's Material Design language."
            }
        }
    },
    mounted(){
        
    },
    methods:{
        toPoster(){
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
.poster {
  margin: 20px 5px 0 5px;
  padding: 2px 5px 2px 5px;
  height: 95%;
}
.postercard{
    height:100%;
    background-color: white;
    padding: 10px
}
.posterwrapper {
  margin: 0;
  height: 45vh;
  width: 25%;
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
.poster h3{
    padding:10px
}
.poster a{
    padding: 0 20px 0 20px;
}
.poster p{
    padding: 20px
}
</style>


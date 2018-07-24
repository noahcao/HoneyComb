<template>
    <div id="posterboard">
        <div v-for="item in items" :key="item.id">
            <div class="col-xs-3 col-sm-3 posterwrapper">
                <div class="poster">
                    <h3>Poster Title</h3>
                    <a href="#">cjk</a>
                    <a>16分钟前</a>
                    <p></p>
                    <span class="badge badge-primary">Default</span>
                    <span class="badge badge-pill teal">
                        <i class="fa fa-heart fa-2x" aria-hidden="true"></i>
                    </span>
                    <p class="postercontent">
                        Lorem ipsum dolor sit amet, consecteturfsdafsdsdadipisicing elit.dhsfjksdhfkjsdhfajkhdsjkfhdkjfsdafsdaf fdsffsdfsdafsdfsdfsdfssadflkasjldfjaklsdfhlkasjdfklajsdkfhalksjdfasddfsdafdsfdgsdagsdhdha fsdafsdafsdfdadsfdsfsdfasdfsdafdssadfsdafsdffdsafsdfsddasdasdfasdf dasdsasadasdsdasdasddasdsaasdasdasdasdasdasdasdasdasdasdasdasdaasdasdfasfasjkfajsdflkasjdkfhaksjfkhshdfkasjlkfjalksjdfklasjlkfjaskldjlahjfal dasdasdasdasdasdasdasdasdasdasdasdasdasasdasdsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            items: [1, 2, 3, 4, 5, 6, 7, 8]
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
  background-color: white;
  border: solid;
  margin: 20px 5px 20px 5px;
  padding: 2px 5px 2px 5px;
  height: 80%;
}
.posterwrapper {
  margin: 0;
  height: 45%;
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
</style>


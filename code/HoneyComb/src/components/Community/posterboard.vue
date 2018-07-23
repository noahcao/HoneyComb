<template>
    <div id="posterboard">
        <div v-for="item in items" :key="item.id" class="posterwrapper">
            <div class="col-xs-3 col-sm-3" style="height:100%">
                <div class="poster">
                    <h3>Poster Title</h3>
                    <a href="#">cjk</a>发布于<a>16分钟前</a>
                    <p></p>
                    <span class="badge badge-primary">Default</span>
                    <span class="badge badge-pill teal"><i class="fa fa-heart fa-2x" aria-hidden="true"></i></span>
                    <p class="postercontent">
                        Lorem ipsum dolor sit amet, consecteturfsdafsdsdadipisicing 
                        elit.dhsfjksdhfkjsdhfajkhdsjkfhdkjfsdafsdaf
                        fdsffsdfsdafsdfsdfsdfsdfsdafdsfdgsdagsdhdha
                        fsdafsdafsdfdadsfdsfsdfasdfsdafdssadfsdafsdffdsafsdfsd</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            items:[1,2,3,4,5,6,7,8]
        }
    }
}
</script>

<style scoped>
@import '../../../static/css/mdb.css';
.poster{
    background-color: white;
    border: solid;
    margin: 20px 5px 20px 5px;
    padding: 2px 5px 2px 5px
}
.posterwrapper{
    margin: 0;
    height:45%
}
.postercontent{
    word-wrap:break-word;
    text-overflow:ellipsis;
    overflow:hidden;height:100%
}
</style>


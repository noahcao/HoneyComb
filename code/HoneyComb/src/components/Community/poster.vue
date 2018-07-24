<template>
    <div id="posterpage">
        <nav-bar style='margin-bottom:1px'></nav-bar>
        <div class="col-xs-8 col-sm-8" id="panelboard">
            <div class="jumbotron" id="panel">
                <div class="row jumbotron" id="buttonbar">
                    <div class="col-xs-8 col-sm-8">
                        <h2 class="h2-reponsive mb-3 blue-text" id="paneltitle"><strong>Panel Topic</strong></h2>
                    </div>
                    <div class="col-xs-4 col-sm-4" style="padding:0">
                        <button type="button" class="btn btn-outline-warning waves-effect self-btn"><i class="fa fa-star pr-2" aria-hidden="true"> Star</i></button>
                        <button type="button" class="btn btn-outline-info waves-effect px-3 self-btn"><i class="fa fa-pencil-square-o" aria-hidden="true"> Post</i></button>
                        <button type="button" class="btn btn-outline-secondary waves-effect px-3 self-btn"><i class="fa fa-share-alt" aria-hidden="true"> Share</i></button>
                    </div>
                </div>
                <div v-for="poster in posters" :key="poster.id">
                    <div class="jumbotron poster">
                        <h2 class="h2-reponsive mb-3 blue-text">{{poster.title}}</h2>
                        <a href="#">{{poster.username}}</a>posted at {{poster.posttime}}
                        <hr class="my-4">
                        <div v-html="poster.content" class='contentboard'>
                        </div>
                        <div v-for="comment in poster.comments" :key="comment.id" class="commentboard">
                            <div class="jumbotron jumbotron-fluid comment">
                                <a href="#">{{comment.username}}</a> post at: {{comment.posttime}}
                                <hr class="my-4" style="margin:0">
                                <div v-html="comment.content" style="padding-left:10px;padding-right:10px">
                                </div>
                            </div>
                        </div>
                        <div class="row" id="newcomment">
                            <div class="md-form input-group row" style="width:100%">
                                <div class="col-xs-11 col-xm-11">
                                    <input type="text" class="form-control" placeholder="Add a comment" aria-label="Add a comment" aria-describedby="basic-addon2">
                                </div>
                                <div class="col-xs-1 col-xm-1" style="float:right">
                                <button class="btn btn-outline-primary waves-effect" type="button" style="padding:8px 15px 8px 15px;margin-left:10%;float:right"><i class="fa fa-pencil" aria-hidden="true"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-4 col-sm-4" id="rightwrapper">
            <div id="rightboard">
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import NavBar from '../main/NavBar'
export default {
    name: 'poster',
    components: {
        NavBar
    },
    methods:{
    },
    data(){
        return {
            posters:
            [
                {   
                    id: 1,
                    userid: 1,
                    username: 'cjk',
                    posttime:"2018/7/22 15:03",
                    vote: 10,
                    veto: 2,
                    title: "Poster Title",
                    content:'<span>test content</span><p class="lead">This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>',
                    comments:[
                        {   
                            id: 22,
                            userid: 2,
                            username: 'zjh',
                            posttime: "2018/7/23 16:10",
                            vote: 20,
                            veto: 21,
                            content: "Good Luck"
                        },
                        {   
                            id: 23,
                            userid: 4,
                            username: 'xty',
                            posttime: "2018/7/23 18:10",
                            vote: 11,
                            veto: 0,
                            content: "Best Wishes"
                        }
                    ]
                },
                {   
                    id: 2,
                    userid: 1,
                    username: 'cjk',
                    posttime:"2018/7/22 15:03",
                    vote: 10,
                    veto: 2,
                    title: "Test on the page of panel",
                    content:"test content",
                    comments:[
                        {   
                            id:11,
                            userid: 2,
                            username: 'zjh',
                            posttime: "2018/7/23 16:10",
                            vote: 20,
                            veto: 21,
                            content: "Good Luck"
                        },
                        {   
                            id:12,
                            userid: 4,
                            username: 'xty',
                            posttime: "2018/7/23 18:10",
                            vote: 11,
                            veto: 0,
                            content: "Best Wishes"
                        }
                    ]
                }
            ]
        }
    }
}
</script>

<style scoped>
@import "../../../static/css/mdb";
.poster_layout{
    border:solid;
    background-color: whitesmoke;
    margin: 0 2% 1% 2%;
    width: 96%;
    min-height: 90%
}
#posterpage{
    height: 800px
}
#panelboard{
    height: 90%;
    padding-top: 2%;
}
#panel{
    padding: 5px 5px 5px 5px;
}
#buttonbar{
    padding-top:10px;
    padding-bottom: 10px;
    width: 100%;
    margin-left:0%;
    margin-bottom: 1%;
}
#paneltitle{
    padding-top:0px;
    font-size: 350%;
    margin: 0
}
#rightwrapper{
    margin: 2% 0 0 0;
    padding: 0 2% 0 0 
}
#rightboard{
    background-color: aqua;
    height: 90%;
    padding-top: 5%;
}
.contentboard{
    padding: 1% 2% 2% 2%;
    margin-bottom: 2px
}
.comment{
    width: 96%;
    margin: 10px 2% 0 2%;
    padding: 0
}
.comment a{
    padding: 10px !important;
}
.comment div{
    padding-bottom: 10px
}
.jumbotron p{
    padding: 5px 10px 5px 10px
}
.poster h2{
    padding: 5px 10px 5px 20px
}
.poster a{
    padding: 5px 10px 0 25px
}
.poster{
    padding-top:  5px;
    padding-bottom: 10px;
    margin-bottom: 10px
}
.hr{
    padding: 0px;
    margin: 0px
}
.self-btn{
    padding-left: 15px;
    padding-right: 15px;
    background-color: white !important;
}
#newcomment{
    margin: 0px 3% 0px 3%;
    width: 94%
}
</style>



<template>
  <div>
    <nav-bar></nav-bar>
    <div class="waveFather" v-show="!show">
      <div class='loader loader--audioWave' id="loading1"></div>
    </div>

    <!-- after result get  -->
    <div v-show="show">
      <div class="col-xs-12 col-md-3">
        <div class="card">
          <div class="card-body">
            <form class="navbar-form">
              <div class="form-group">
                <input type="text" class="form-control2" placeholder="Search" v-model="content2">
                <span class="glyphicon glyphicon-search glyphicon1" @click="search()" aria-hidden="true" style="color: #24292e;float:left"></span>
              </div>

            </form>
            <h6 class="time-scale" @click="searchY(0,9998)">Any time</h6>
            <h6 class="time-scale" @click="searchY(2018,9998)">Since 2018</h6>
            <h6 class="time-scale" @click="searchY(2017,9998)">Since 2017</h6>
            <h6 class="time-scale" @click="searchY(2014,9998)">Since 2014</h6>
            <h6 class="time-scale" @click="reverse()">Custom range...</h6>
            <div class="gs_res_sb_yyr" v-show="showRange">
              <div class="gs_in_txtw gs_in_txtm"><input v-model="begin" type="text" class="gs_in_txt" name="as_ylo" value="" id="gs_as_ylo" size="4" maxlength="4" autocapitalize="off" pattern="[0-9]*"></div>
              —
              <div class="gs_in_txtw gs_in_txtm"><input v-model="end" type="text" class="gs_in_txt" name="as_yhi" value="" size="4" maxlength="4" autocapitalize="off" pattern="[0-9]*"></div>
              <div>
                <span class="glyphicon glyphicon-search search2" @click="search(begin,end)" aria-hidden="true" style="color: #24292e;"></span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-9">
        <div v-for="paper in papers">
          <div class="panel panel-default">
            <div class="panel-heading">
              <a :href="paper.url" target="_blank">
                <h3 class="panel-title">{{paper.title}}</h3>
              </a>
            </div>
            <hr class="my-4">
            <div class="panel-body">
              <div class="col-xs-12 col-md-6 author-group">
                <div class="body-author">Authors: </div>
                <div class="body-author" v-for="author in paper.authors">
                  <strong>
                    {{author.name}}
                  </strong>
                </div>
              </div>
              <div class="col-xs-12 col-md-2 body-cited">
                cited:
                <strong>{{paper.cited}}</strong>
              </div>
              <div class="col-xs-12 col-md-2 body-cited">
                Year:
                <strong>{{paper.year}}</strong>
              </div>
              <router-link class="col-xs-12 col-md-2 body-cited" :to="{ name: 'outcomb', params: { paperId: paper.id }}">
                more...
              </router-link>
            </div>
            <div class="panel-body">
              <div class="body-abstract">
                {{paper._abstract}}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-show="pageShow" class="col-xs-12 col-md-2">
        <span class="glyphicon glyphicon-upload" aria-hidden="true" @click="up()"></span>
        <!-- <div id="box" class="box"  >
          <div class="box-in"></div>
          
        </div> -->
      </div>
      <div v-show="pageShow" class="col-xs-12 col-md-10 bottom-nav">
        <ul class="pagination">
          <li id="prev" class="disabled">
            <a @click="prevPaging()">&lt;</a>
          </li>
          <li class="active">
            <a id="page1" @click="paging('page1')">{{page[0]}}</a>
          </li>
          <li>
            <a id="page2" @click="paging('page2')">{{page[1]}}</a>
          </li>
          <li>
            <a id="page3" @click="paging('page3')">{{page[2]}}</a>
          </li>
          <li>
            <a id="page4" @click="paging('page4')">{{page[3]}}</a>
          </li>
          <li>
            <a id="page5" @click="paging('page5')">{{page[4]}}</a>
          </li>
          <li>
            <a @click="nextPaging()">&gt;</a>
          </li>
        </ul>
      </div>
    </div>
    <!-- <nav>
      <ul class="pagination pg-dark">
        <li class="page-item">
          <a class="page-link" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
            <span class="sr-only">Previous</span>
          </a>
        </li>

        <li class="page-item active">
          <a class="page-link">1</a>
        </li>
        <li class="page-item">
          <a class="page-link">2</a>
        </li>
        <li class="page-item">
          <a class="page-link">3</a>
        </li>
        <li class="page-item">
          <a class="page-link">4</a>
        </li>
        <li class="page-item">
          <a class="page-link">5</a>
        </li>
        
        <li class="page-item">
          <a class="page-link" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
            <span class="sr-only">Next</span>
          </a>
        </li>
      </ul>
    </nav> -->
  </div>
</template>

<script>
import '../../../static/js/mdb'
import $ from 'jquery'
import NavBar from '../main/NavBar'
/* eslint-disable */
export default {
  name: 'search',
  components: {
    NavBar
  },
  methods: {
    reverse () {
      this.showRange = !this.showRange
    },
    searchY (start, end) {
      this.defaultStart = start
      this.defaultEnd = end + 1
      this.page = [1, 2, 3, 4, 5]
      $('#prev').addClass('disabled')
      this.paging('page1')
    },
    search () {
      this.content = this.content2
      this.page = [1, 2, 3, 4, 5]
      $('#prev').addClass('disabled')
      this.paging('page1')
    },
    prevPaging () {
      for (var i = 0; i < this.page.length; i++) {
        this.page[i] = this.page[i] - 5
      }
      var index = this.page[0]
      this.paging('page1')
      if (this.page[0] === 1) {
        $('#prev').addClass('disabled')
      }
    },
    nextPaging () {
      for (var i = 0; i < this.page.length; i++) {
        this.page[i] = this.page[i] + 5
      }
      var index = this.page[0]
      this.paging('page1')
      $('#prev').removeClass('disabled')
    },
    paging (e) {
      this.show = false
      var a = document.getElementById(e)
      var index = a.innerHTML
      console.log(index)
      this.$http.get('/searchpaper', { params: { key: this.content, start: (index - 1) * this.numPerPage, end: this.numPerPage * index, startYear: this.defaultStart, endYear: this.defaultEnd } })
        .then((res) => {
          this.papers = res.data.papers
          console.log(this.papers)
          this.show = true
          console.log('yutuityuityuity')
          console.log($('#' + e))
          $('#' + e).parent().siblings().removeClass('active')
          $('#' + e).parent().addClass('active')
          console.log(res.data.papers.length)
          if (index === '1') {
            if (res.data.papers.length < 10) {
              this.pageShow = false;
            }
          }
        })

    },
    up () {
      cancelAnimationFrame(this.timer);
      this.timer = requestAnimationFrame(function fn () {
        var oTop = document.body.scrollTop || document.documentElement.scrollTop;
        if (oTop > 0) {
          document.body.scrollTop = document.documentElement.scrollTop = oTop - 50;
          this.timer = requestAnimationFrame(fn);
        } else {
          cancelAnimationFrame(this.timer);
        }
      });
    }
  },
  data () {
    return {
      content: this.$route.params.content,
      content2: '',
      papers: null,
      show: false,
      page: [1, 2, 3, 4, 5],
      numPerPage: 10,
      activate: 0,
      defaultStart: 0,
      defaultEnd: 9999,
      timer: null,
      showRange: false,
      pageShow: true,
      begin: null,
      end: null
    }
  },
  mounted () {
    $('canvas').remove()
    this.$http.get('/searchpaper', { params: { key: this.content, start: 0, end: this.numPerPage, startYear: this.defaultStart, endYear: this.defaultEnd } })
      .then((res) => {
        this.papers = res.data.papers
        console.log(this.papers)
        this.show = true
        if (res.data.papers.length < 10) {
          this.pageShow = false;
        }
      })
  }
}

</script>

<style scoped>
@import "../../../static/css/mdb";

.search2 {
  display: inline;
}

.btn {
  margin-left: 15px;
  margin-top: 10px;
  display: block;
}

.gs_in_txtw {
  display: inline-block;
  vertical-align: middle;
}

.gs_res_sb_yyr {
  padding-left: 15px;
}

hr {
  margin-top: 0px;
  margin-bottom: 0px;
  border: 0;
  border-top: 1px solid #eee;
}

.time-scale {
  font-size: 10px;
}

.time-scale:hover {
  font-weight: bold !important;
  cursor: pointer;
}

.panel-body {
  padding-top: 5px;
  padding-bottom: 5px;
}
.card {
  margin-bottom: 5%;
  margin-left: 5%;
  margin-right: 5%;
  /* position: fixed;
  left: 100px;
  top: 90px; */
}

@media (min-width: 768px) {
  .panel-body {
    padding-top: 15px;
    padding-bottom: 15px;
  }
  .card {
    margin-bottom: 5%;
    margin-left: 20%;
    margin-right: 0%;
    /* position: fixed;
  left: 100px;
  top: 90px; */
  }
}

.body-cited {
  padding-left: 0;
  color: #333;
}
.card .card-body h6 {
  font-weight: 400;
  font-size: 1.4rem;
  padding-left: 15px;
}
.card-body {
  -ms-flex: 1 1 auto;
  flex: 1 1 auto;
  padding: 1.8rem;
}

.glyphicon1 {
  position: relative;
  top: 12px;
  display: inline-block;
  font-family: "Glyphicons Halflings";
  font-style: normal;
  font-weight: 200;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.form-control2 {
  display: inline-block;
  width: 85%;
  height: 34px;
  padding: 6px 12px;
  font-size: 14px;
  line-height: 1.42857143;
  color: #24292e;
  background-color: rgba(0, 0, 0, 0);
  background-image: none;
  border-top: 1px solid rgba(0, 0, 0, 0);
  border-right: 1px solid rgba(0, 0, 0, 0);
  border-left: 1px solid rgba(0, 0, 0, 0);
  border-bottom: 1px solid #24292e;
  border-radius: 0px;
  margin-left: 5px;
  box-shadow: none;
  -webkit-transition: border-color ease-in-out 0.15s,
    -webkit-box-shadow ease-in-out 0.15s;
  -o-transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
  transition: border-color ease-in-out 0.15s, box-shadow ease-in-out 0.15s;
  outline: none;
}
.pagination > .active > a,
.pagination > .active > a:focus,
.pagination > .active > a:hover,
.pagination > .active > span,
.pagination > .active > span:focus,
.pagination > .active > span:hover {
  z-index: 3;
  color: #24292e;
  cursor: default;
  background-color: rgba(0, 0, 0, 0.1);
  font-weight: bold;
  border-color: rgba(0, 0, 0, 0.15);
}

.pagination > li > a:hover,
.pagination > li > span:hover {
  position: relative;
  float: left;
  padding: 6px 12px;
  margin-left: 0px;
  line-height: 1.42857143;
  color: #24292e;
  text-decoration: none;
  background-color: rgba(0, 0, 0, 0.2);
  border: 2px solid rgba(0, 0, 0, 0);
}

.pagination > li > a,
.pagination > li > span {
  transition: all 0.5s;
  position: relative;
  float: left;
  padding: 6px 12px;
  margin-left: 0px;
  line-height: 1.42857143;
  color: #24292e;
  text-decoration: none;
  background-color: rgba(0, 0, 0, 0.1);
  border: 2px solid rgba(0, 0, 0, 0);
}
.pagination > .disabled > a,
.pagination > .disabled > a:focus,
.pagination > .disabled > a:hover,
.pagination > .disabled > span,
.pagination > .disabled > span:focus,
.pagination > .disabled > span:hover {
  color: rgba(0, 0, 0, 0.2);
  cursor: not-allowed;
  background-color: rgba(0, 0, 0, 0.02);
  border-color: rgba(0, 0, 0, 0.02);
}

.bottom-nav {
  text-align: center;
}
.pagination {
  display: inline-block;
}
.bar {
  margin-left: 25%;
}
.bar-c {
  font-size: 22px;
}
.waveFather {
  font-size: 18px;
}
.body-abstract {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.body-author {
  margin-right: 8px;
  float: left;
}
.author-group {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  padding-left: 0px;
}
.panel {
  margin-left: 5%;
  margin-right: 5%;
  margin-bottom: 20px;
  background-color: #fff;
  border: 0px solid transparent;
  border-radius: 0px;
  /* -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05); */
  box-shadow: darkgrey -3px 3px 6px 1px;
}

.panel-default > .panel-heading {
  background-image: linear-gradient(to bottom, #ffffff 0, #ffffff 100%);
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff5f5f5', endColorstr='#ffe8e8e8', GradientType=0);
  background-repeat: repeat-x;
  /* color: #333; */
}

.panel-title {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 18px;
  color: #24292e;
}

.panel-heading {
  border-top: 0px solid rgba(0, 0, 0, 0.5);
  border-right: 20px solid rgba(0, 0, 0, 0.5);
  border-bottom: 0px solid rgba(0, 0, 0, 0);
  border-left: 0px solid rgba(0, 0, 0, 0);
  border-top-left-radius: 0px;
  border-top-right-radius: 0px;
}

/* loading component */
.loader {
  margin: 4em auto;
}
.loader--audioWave {
  width: 3em;
  height: 2em;
  background: linear-gradient(#24292e, #24292e) 0 50%,
    linear-gradient(#24292e, #24292e) 0.625em 50%,
    linear-gradient(#24292e, #24292e) 1.25em 50%,
    linear-gradient(#24292e, #24292e) 1.875em 50%,
    linear-gradient(#24292e, #24292e) 2.5em 50%;
  background-repeat: no-repeat;
  background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em,
    1em 0.25em;
  animation: audioWave 1.5s linear infinite;
}
@keyframes audioWave {
  25% {
    background: linear-gradient(#ffc000, #ffc000) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em,
      0.5em 0.25em;
  }
  37.5% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#ffc000, #ffc000) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em, 0.5em 0.25em,
      0.5em 0.25em;
  }
  50% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#ffc000, #ffc000) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em, 0.5em 0.25em,
      0.5em 0.25em;
  }
  62.5% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#ffc000, #ffc000) 1.875em 50%,
      linear-gradient(#24292e, #24292e) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 2em,
      0.5em 0.25em;
  }
  75% {
    background: linear-gradient(#24292e, #24292e) 0 50%,
      linear-gradient(#24292e, #24292e) 0.625em 50%,
      linear-gradient(#24292e, #24292e) 1.25em 50%,
      linear-gradient(#24292e, #24292e) 1.875em 50%,
      linear-gradient(#ffc000, #ffc000) 2.5em 50%;
    background-repeat: no-repeat;
    background-size: 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em, 0.5em 0.25em,
      0.5em 2em;
  }
}

.box {
  /* position: fixed;
  right: 10px;
  bottom: 10px; */
  height: 30px;
  width: 50px;
  text-align: center;
  padding-top: 20px;
  background-color: lightblue;
  border-radius: 20%;
  overflow: hidden;
}
.box:hover:before {
  top: 50%;
}
.box:hover .box-in {
  visibility: hidden;
}
.box:before {
  position: absolute;
  top: -50%;
  left: 50%;
  transform: translate(-50%, -50%);
  content: "回到顶部";
  width: 40px;
  color: peru;
  font-weight: bold;
}
.box-in {
  visibility: visible;
  display: inline-block;
  height: 20px;
  width: 20px;
  border: 3px solid black;
  border-color: white transparent transparent white;
  transform: rotate(45deg);
}

.glyphicon-upload:hover,
.glyphicon-search:hover {
  cursor: pointer;
}

.glyphicon-upload {
  position: relative;
  top: 0px;
  font-size: 36px;
  margin-top: 20px;
  margin-left: 20%;
  display: inline-block;
  font-family: "Glyphicons Halflings";
  font-style: normal;
  font-weight: 600;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height: 100%;
}
</style>

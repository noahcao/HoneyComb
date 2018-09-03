
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
              <div class="form-group" >
                <input type="text" class="form-control2" placeholder="Search">
                <span class="glyphicon glyphicon-search" aria-hidden="true" style="color: #24292e"></span>
              </div>
              
            </form>
            <h6 class="time-scale">Any time</h6>
            <h6 class="time-scale">Since 2018</h6>
            <h6 class="time-scale">Since 2017</h6>
            <h6 class="time-scale">Since 2014</h6>
            <h6 class="time-scale">Custom range...</h6>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-9">
        <div v-for="paper in papers">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">{{paper.title}}</h3>
            </div>
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
              <a class="col-xs-12 col-md-2 body-cited" :href="paper.url" target="_blank">more..</a>
            </div>
            <div class="panel-body">
              <div class="body-abstract">
                {{paper._abstract}}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xs-0 col-md-2">
      </div>
      <div class="col-xs-12 col-md-10 bottom-nav">
        <ul class="pagination">
          <li class="disabled">
            <a>&lt;</a>
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
            <a @click="">&gt;</a>
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
    paging (e) {
      this.show = false
      var a = document.getElementById(e)
      var index = a.innerHTML
      console.log(index)
      this.$http.get('/searchpaper', { params: { key: this.content, start: (index - 1) * this.numPerPage, end: this.numPerPage * index } })
        .then((res) => {
          this.papers = res.data.papers
          console.log(this.papers)
          this.show = true
          console.log('yutuityuityuity')
          console.log($('#' + e))
          $('#' + e).parent().siblings().removeClass('active')
          $('#' + e).parent().addClass('active')

        })

    }
  },
  data () {
    return {
      content: this.$route.params.content,
      papers: null,
      show: false,
      page: [1, 2, 3, 4, 5],
      numPerPage: 10,
      activate: 0,
    }
  },
  mounted () {
    $('canvas').remove()
    this.$http.get('/searchpaper', { params: { key: this.content, start: 0, end: this.numPerPage } })
      .then((res) => {
        this.papers = res.data.papers
        console.log(this.papers)
        this.show = true

      })
  }
}

</script>

<style scoped>
@import "../../../static/css/mdb";

.time-scale{
  font-size: 10px;
}
.panel-body {
  padding-top: 5px;
  padding-bottom: 5px;
}
@media (min-width: 768px) {
  .panel-body {
    padding-top: 15px;
    padding-bottom: 15px;
  }
}
.card {
  margin-bottom: 5%;
  margin-left: 5%;
  margin-right: 5%;
}
.body-cited {
  padding-left: 0;
}
.card .card-body h6 {
  font-weight: 400;
  font-size: 1.4rem;
}
.card-body {
  -ms-flex: 1 1 auto;
  flex: 1 1 auto;
  padding: 1.8rem;
}
.form-control2 {
  display: block;
  width: 80%;
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
  color: inherit;
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
</style>

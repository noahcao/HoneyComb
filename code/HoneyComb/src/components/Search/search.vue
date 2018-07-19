
<template>
  <div>
    <nav-bar></nav-bar>
    <div class="waveFather" v-if="!show">
      <div class='loader loader--audioWave' id="loading1"></div>
    </div>

    <!-- after result get  -->
    <div v-else>
      <div class="col-xs-1 col-md-2">
        <div class="bar">
          <div >
            <a href="" class="bar-c" >Since 2018</a>
          </div>
          <div>
            <a href="" class="bar-c">Since 2017</a>
          </div>
          <div>
            <a href="" class="bar-c">Since 2016</a>
          </div>
          <div>
            <a href="" class="bar-c">...</a>
          </div>
        </div>
      </div>
      <div class="col-xs-1 col-md-10">
        <div v-for="paper in papers">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">{{paper.title}}</h3>
            </div>
            <div class="panel-body">
              <div class="col-xs-1 col-md-6 author-group">
                <div class="body-author">Authors: </div>
                <div class="body-author" v-for="author in paper.authors">
                  <strong>
                    {{author.name}}
                  </strong>
                </div>
              </div>
              <div class="col-xs-1 col-md-2 body-cited">
                cited:
                <strong>{{paper.cited}}</strong>
              </div>
              <div class="col-xs-1 col-md-2 body-cited">
                Year:
                <strong>{{paper.year}}</strong>
              </div>
              <a class="col-xs-1 col-md-2" :href="paper.url" target="_blank">more..</a>
            </div>
            <div class="panel-body">
              <div class="body-abstract">
                {{paper._abstract}}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import $ from 'jquery'
import NavBar from '../main/NavBar'
/* eslint-disable */
export default {
  name: 'search',
  components: {
    NavBar
  },
  data () {
    return {
      content: this.$route.params.content,
      papers: null,
      show: false
    }
  },
  mounted () {
    $('canvas').remove()
    this.$http.get('/searchpaper', { params: { key: this.content } })
      .then((res) => {
        this.papers = res.data.papers
        console.log(this.papers)
        this.show = true
      })
  }
}

</script>

<style scoped>
.bar {
  margin-left: 25%;
}
.bar-c{
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
}
.panel {
  margin-left: 5%;
  margin-right: 5%;
  margin-bottom: 20px;
  background-color: #fff;
  border: 0px solid transparent;
  border-radius: 4px;
  -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
}

.panel-default > .panel-heading {
  background-image: linear-gradient(to bottom, #dedede 0, #dedede 100%);
  filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#fff5f5f5', endColorstr='#ffe8e8e8', GradientType=0);
  background-repeat: repeat-x;
}
.panel-title {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 18px;
  color: inherit;
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

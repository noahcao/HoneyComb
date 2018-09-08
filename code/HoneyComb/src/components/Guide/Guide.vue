<template>
  <div>
    <nav-bar></nav-bar>
    <div class="container-fluid guide-top-wrap">
      <div class="top-image-container">
        <h1>Paper Journal</h1>
        <div class="journal-boxes">
          <div class="col-md-4 journal-box">
            <a href="https://www.springer.com/cn/" target="_blank">
              <img src="../../../static/pic/springer.jpg">
              <p>Springer is a leading global scientific, technical and medical portfolio</p>
            </a>
          </div>
          <div class="col-md-4 journal-box">
            <a href="https://ieeexplore.ieee.org/Xplore/home.jsp" target="_blank">
              <img src="../../../static/pic/ieee_xplore.png">
              <p>IEEE Xplore is a research database for discovery and access to journal articles</p>
            </a>
          </div>
          <div class="col-md-4 journal-box">
            <a href="https://arxiv.org/" target="_blank">
              <img src="../../../static/pic/arxiv.png">
              <p>123123</p>
            </a>
          </div>
        </div>
      </div>
    </div>

    <div class="container-box">
      <div class="guide-wrap">
        <h2>
          <strong>Search Paper</strong>
        </h2>
        <div class="container-wrap">
          <div class="container-inbox">
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step1.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step2.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step3.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step4.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step5.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step6.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/searchpaper_step7.png">
              </p>
            </div>

          </div>
        </div>
      </div>

      <div class="guide-wrap">
        <h2>
          <strong>Construct your comb</strong>
        </h2>
        <div class="container-wrap">
          <div class="container-inbox">
            <div class="guide-step">
              <h4>Step1. Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/combconstruct1.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/combconstruct2.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/combconstruct3.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/combconstruct4.png">
              </p>
            </div>
            <div class="guide-step">
              <h4>1.Enter the paper on the home page</h4>
              <p>
                <img class="step-pic" src="../../../static/pic/combconstruct5.png">
              </p>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import navBar from '../main/NavBar'

export default {
  name: 'Guide',
  components: {
    navBar
  },
  data () {
    return {

    }
  },
  mounted () {
    $('canvas').remove()
  }
}
</script>

<style scoped>
.guide-top-wrap {
  display: none;
}

.journal-boxes {
  margin-top: 30px;
}

.journal-box:hover {
  cursor: pointer;
}

.journal-box:hover p {
  opacity: 1;
}

.container-box {
  margin-top: 2%;
  margin-bottom: 5%;
  margin-left: 5%;
  margin-right: 5%;
  padding-top: 2%;
  padding-left: 5%;
  padding-right: 5%;
  padding-bottom: 5%;
  background-color: rgba(255, 255, 255, 0.6);
  /* box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12); */
}

.guide-wrap {
  margin-top: 30px;
  margin-bottom: 50px;
}

.container-wrap {
  margin-top: 20px;
  width: 100%;
  /* height: 50px; */
  white-space: nowrap;
  overflow: hidden;
  overflow-x: scroll; /* 1 */
  -webkit-backface-visibility: hidden;
  -webkit-perspective: 1000;
  -webkit-overflow-scrolling: touch; /* 2 */
  text-align: justify; /* 3 */
}

.container-inbox > div {
  display: inline-block;
  color: black;
  text-align: center;
}

.container-inbox {
  position: relative;
  padding-left: 0px;
  padding-right: 0px;
}

.guide-step {
  z-index: -1;
  background-color: #fff !important;
  border: 1px #e1e4e8 solid !important;
  width: 140%;
  height: auto;
  border-radius: 10px !important;
  margin-right: 10px;
  margin-bottom: 10px;
  margin-left: 10px;
}

@media (min-width: 992px) {
  .guide-top-wrap {
    margin-top: -22px;
    background-color: #2f363d;
    background-image: url("https://assets-cdn.github.com/images/modules/marketplace/bg-hero.svg");
    height: 400px;
    display: block;
  }
  .guide-step {
    background-color: #fff !important;
    border: 1px #e1e4e8 solid !important;
    width: 80%;
    height: auto;
    border-radius: 10px !important;
    margin-right: 10px;
    margin-bottom: 10px;
    margin-left: 10px;
  }
}
.guide-step img {
  display: block;
  width: 80%;
  height: auto;
  margin: auto;
}

.top-image-container {
  padding-top: 20px;
  width: 80%;
  margin: auto;
  transition: all 0.5s ease;
}

.top-image-container h1 {
  text-align: center;
  color: #fff;
}

.top-image-container img {
  display: block;
  width: 95%;
  height: auto;
  margin: auto;
  border-radius: 3px;
  opacity: 1;
}

.top-image-container p {
  padding-top: 20px;
  width: 80%;
  margin: auto;
  text-align: center;
  color: #fff;
  opacity: 0.7;
  transition: all 0.5s ease;
}
</style>

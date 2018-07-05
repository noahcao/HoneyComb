
<template>
  <section>
    <nav-bar></nav-bar>
    <div class="row">
      <div class="col-sm-0 col-md-1"></div>
      <div class="col-xs-12 col-sm-4 col-md-3">
        <div class="thumbnail">
          <img class="img-rounded avatar" src="../../assets/pic/dft-header.png" alt="70x70">
          <div class="caption">
            <h3>Lena Oxton</h3>
            <p v-if="editBio">{{bio}}</p>
            <p>
              <button class="btn btn-default" type="button" @click="edit_bio" v-if="editBio">
                Edit Bio
              </button>
              <textarea rows="5" v-else v-model="bio"></textarea>
              <button class="btn btn-default" v-if="!editBio" type="button">save</button>
              <button class="btn btn-default" v-if="!editBio" type="button" @click="edit_bio">cancel</button>
              <br/>
              <button class="btn btn-default" type="button">
                Edit Profile
              </button>
            </p>
          </div>
        </div>
      </div>
      <div class="col-sm-8 col-md-7">
        <div class="r">
          <ul class="nav nav-tabs">
            <li role="presentation" class="active">
              <a class="tabb" href="#net" data-toggle="tab">Nets</a>
            </li>
            <li role="presentation">
              <a class="tabb" href="#jmeter" data-toggle="tab">Collects</a>
            </li>
            <li role="presentation">
              <a class="tabb" href="#ejb" data-toggle="tab">Trends</a>
            </li>
          </ul>
          <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="net">
              <Net></Net>
            </div>
            <div class="tab-pane fade" id="ios">
              <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
            </div>
            <div class="tab-pane fade" id="jmeter">
              <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
            </div>
            <div class="tab-pane fade" id="ejb">
              <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
              </p>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-0 col-md-1"></div>
    </div>
  </section>
</template>

<script>
// import $ from 'jquery'
import navBar from '../main/NavBar'
import Net from './infoContent/Net'
export default {
  name: 'userInfo',
  components: {
    navBar,
    Net
  },
  data () {
    return {
      editBio: true,
      bio:
        'The former Overwatch agent known as Tracer is a time-jumping adventurer and an irrepressible force for good.'
    }
  },
  methods: {
    edit_bio () {
      this.editBio = !this.editBio
    }
  }
}
</script>

<style scoped>
textarea,
p {
  width: 235px;
  resize: none;
}
.row {
  margin-left: 0px;
  margin-right: 0px;
  margin-top: 4.5%;
}
.r {
  margin-left: 5%;
}
.tabb {
  color: rgba(36, 41, 46, 0.8);
  font-size: 20px;
  border-bottom-color: rgba(36, 41, 46, 0);
}
.nav-tabs {
  border-bottom-color: rgba(36, 41, 46, 0.5);
}
.nav-tabs > li > a {
  transition: 0.8s ease;
  transition-property: color;
  margin-right: 0px;
}
.nav-tabs > li.active > a,
.nav-tabs > li.active > a:hover {
  color: #000;
  cursor: default;

  background-color: rgba(36, 41, 46, 0);
  border-right: 1px solid rgba(36, 41, 46, 0);
  border-left: 1px solid rgba(36, 41, 46, 0);
  border-top: 1px solid rgba(36, 41, 46, 0);

  border-bottom: 2px solid rgba(36, 41, 46, 1);
}
.tabb {
  transition: all 0.3s ease;
  color: rgba(36, 41, 46, 0.5);
  font-size: 20px;
}
.tabb:hover {
  color: #24292e;
  font-size: 20px;
  border-bottom-color: rgba(36, 41, 46, 0);
}
.thumbnail {
  display: block;
  padding: 4px;
  margin-bottom: 20px;
  line-height: 1.42857143;
  background-color: rgba(255, 255, 255, 0);
  border: none;
  border-right: 2px solid rgba(36, 41, 46, 0.5);
  border-radius: 0px;
  border-right-style: inset;
  -webkit-transition: border 0.2s ease-in-out;
  -o-transition: border 0.2s ease-in-out;
  transition: border 0.2s ease-in-out;
  box-shadow: none;
}
.thumbnail > img {
  display: block;
  /* max-width: 100%; */
  height: 240px;
  margin-top: 20px;
  margin-left: 0px;
}
.avatar {
  border: 1px solid rgba(200, 200, 200, 0.6);
}
.btn-default {
  transition: all 0.3s ease;
  background: rgba(255, 255, 255, 0.5);
  border-color: rgba(36, 41, 46, 0);
  margin-top: 5px;
}
.btn-default:active:focus {
  color: #333;
  background-color: #d4d4d4;
  border-color: rgba(36, 41, 46, 0);
}
.btn-default:hover {
  color: #333;
  background-color: rgba(255, 255, 255, 0.9);
  border-color: rgba(36, 41, 46, 0);
}
.btn:active,
.btn:focus {
  z-index: 2;
  box-shadow: none;
  outline: none;
}
</style>

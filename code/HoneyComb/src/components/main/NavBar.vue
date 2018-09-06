<template>
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <router-link :to="{name:'Main'}" class="navbar-brand">HONEYCOMB</router-link>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <!-- <li >
            <a href="#" class="title">Honeycomb</a>
          </li> -->
          <li>
            <router-link :to="{name:'outcomb'}" class="tag1">Explore</router-link>
          </li>
          <li>
            <router-link :to="{name:'poster'}" class="tag">About us</router-link>
          </li>
          <li>
            <router-link :to="{name:'community'}" class="tag">Community</router-link>
          </li>
          <!-- <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <a href="#">Action</a>
              </li>
              <li>
                <a href="#">Another action</a>
              </li>
              <li>
                <a href="#">Something else here</a>
              </li>
              <li role="separator" class="divider"></li>
              <li>
                <a href="#">Separated link</a>
              </li>
              <li role="separator" class="divider"></li>
              <li>
                <a href="#">One more separated link</a>
              </li>
            </ul>
          </li> -->
        </ul>
        <!-- <form class="md-form">
          <fa icon="search" />
          <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search" aria-label="Search" />
        </form> -->
        <ul class="nav navbar-nav navbar-right">
          <li v-if="this.id === null">
            <a id="signIn" data-toggle="modal" data-target="#myModal" @click="changeStatus(1)">Sign in</a>
          </li>
          <li v-if="this.id === null">
            <a data-toggle="modal" data-target="#myModal" @click="changeStatus(0)" id="sign-up">Sign up</a>
          </li>
          <li class="dropdown" v-else>
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">{{name}}&nbsp;&nbsp;
              <span class="glyphicon glyphicon-menu-right"></span>
            </a>
            <ul class="dropdown-menu">
              <li>
                <router-link :to="{name:'userInfo'}">My Profile</router-link>
              </li>
              <li>
                <router-link :to="{name:'UserComb'}">My Comb</router-link>
              </li>
              <li role="separator" class="divider"></li>
              <li>
                <a href="#" @click="logOut">Sign out</a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
    <!-- 弹出层 modal -->
    <div class="modal right fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title" id="myModalLabel">{{ status[0].status ? 'Login' : 'Register'}}
              <span class="glyphicon glyphicon-menu-right pull-right" @click="closeToggle" data-dismiss="modal"></span>
            </h4>

          </div>
          <div class="modal-body">
            <wrapper-lr></wrapper-lr>
          </div>
        </div>
      </div>
    </div>
    <h2 class="hide">{{status}}</h2>
  </nav>
</template>

<script>
/* eslint-disable */
import $ from 'jquery'
import wrapperLr from '../user/wrapperLr'
export default {
  name: 'nav-bar',
  data () {
    var status = []
    for (var i of this.data.LRList) {
      status.push(i)
    }
    return {
      id: this.data.id,
      name: null,
      status: status
    }
  },
  components: {
    wrapperLr
  },
  mounted () {
    $('#myModal').on('hidden.bs.modal', '.modal', function () {
      $(this).removeData("bs.modal");
      $(".modal-body").children().remove();
    });
    this.loginJudge()
    // $('body').append('<style>' +
    //   '@keyframes rotatefresh {' +
    //   'from {' +
    //   'transform: rotate(0deg);' +
    //   '}' +
    //   'to {' +
    //   'transform: rotate(90deg);' +
    //   'transition: all 0.5s;' +
    //   '}' +
    //   '}' +
    //   '</style>')
    // $('body').append('<style>' +
    //   '@keyframes rotatefresh2 {' +
    //   'from {' +
    //   'transform: rotate(90deg);' +
    //   '}' +
    //   'to {' +
    //   'transform: rotate(0deg);' +
    //   'transition: all 0.5s;' +
    //   '}' +
    //   '}' +
    //   '</style>')
    // $('.dropdown-toggle').mouseover(function () {
    //   $('.dropdown-toggle .glyphicon').css({
    //     'animation': 'rotatefresh 0.5s',
    //     'transform': 'rotate(90deg)'
    //   })
    // })
    // $('.dropdown-toggle').mouseout(function () {
    //   $('.dropdown-toggle .glyphicon').css({
    //     'animation': 'rotatefresh2 0.5s',
    //     'transform': 'rotate(0deg)'
    //   })
    // })
    // $("#myModal").on("hidden.bs.modal", function () {
    //   $(this).removeData("bs.modal");
    // });
  },
  methods: {
    closeToggle () {
      console.log($('#signIn'))
      $('#signIn').click()
    },
    loginJudge () {
      this.$http.get('/loginstatus')
        .then((res) => {
          this.data.id = res.data.id
          this.id = this.data.id
          console.log('status', this.data.id)
          if (this.data.id !== null) {
            this.$http.get('/getusername', { params: { id: this.id } })
              .then((res) => {
                this.name = res.data.name
                console.log('status', res.data)
              })
          }
        })
    },
    logOut () {
      this.$http.get('/logout')
        .then((res) => {
          this.id = null;
          var url = window.location.href
          window.location.href = url
        })
    },
    changeStatus (e) {
      if (e) {
        this.data.LRList[0].status = true
      } else {
        this.data.LRList[0].status = false
      }
    }
  }
}
</script>
<style scoped>
.glyphicon-menu-right {
  cursor: pointer;
}
.navbar-inverse {
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  background: linear-gradient(#24292e, #24292e);
  height: 70px;
}

.modal-backdrop {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 20;
    background-color: #000;
}

.container-fluid {
  margin-top: 10px;
}
.navbar-inverse .navbar-header {
  height: 60px;
}

.navbar-inverse .navbar-nav > .open > a,
.navbar-inverse .navbar-nav > .open > a:focus,
.navbar-inverse .navbar-nav > .open > a:hover {
  color: #fff;
  background-color: #24292e;
}

.container-fluid > .navbar-collapse,
.container-fluid > .navbar-header,
.container > .navbar-collapse,
.container > .navbar-header {
  margin-right: -16px;
  margin-left: -16px;
  background-color: #24292e;
}

.navbar-inverse .navbar-brand {
  font-family: "Century Gothic", sans-serif;
  font-weight: 400;
  letter-spacing: 1px;
  font-size: 22px;
  color: #ffffff !important;
  background-color: transparent;
  margin-left: 15px;
}

@media (min-width: 768px) {
  
  .tag, tag1 {
    margin-left: 5px;
  }
  .navbar-inverse .navbar-brand {
    font-family: "Century Gothic", sans-serif;
    font-weight: 400;
    letter-spacing: 1px;
    font-size: 22px;
    color: #ffffff !important;
    background-color: transparent;
    margin-left: 15px;
  }
}

@media (min-width: 900px) {
  .tag1 {
    margin-left: 30px;
  }
  .tag {
    margin-left: 5px;
  }
  .navbar-inverse .navbar-brand {
    font-family: "Century Gothic", sans-serif;
    font-weight: 400;
    letter-spacing: 1px;
    font-size: 22px;
    color: #ffffff !important;
    background-color: transparent;
    margin-left: 80px;
  }
}
.navbar-right {
  margin-right: 50px;
}
.navbar-inverse .navbar-nav > li > a {
  transition: all 0.8s ease;
  display: inline-block;
  color: #9d9d9d;
  text-transform: uppercase;
  font-family: "Century Gothic", sans-serif;
  text-decoration: none;
  font-weight: normal;
  letter-spacing: 0.8px;
}
.navbar-inverse .navbar-nav > li > a:hover {
  color: #ffffff;
  font-weight: normal;
}
/* .navbar-brand {
    float: left;
    height: 50px;
    padding: 0px 15px;
    font-size: 18px;
    line-height: 20px;
    width: 120px;
}
.navbar-brand>img {
    display: block;
    max-width: 40%;
    height: auto;
} */
.navbar-inverse .navbar-nav > .active > a,
.navbar-inverse .navbar-nav > .open > a {
  background-image: linear-gradient(to bottom, #24292e 0, #24292e 100%);
  box-shadow: inset 0 3px 9px rgba(0, 0, 0, 0);
}
.dropdown-menu {
  border: 1px solid rgba(0, 0, 0, 0);
  border-radius: 0px;
}
/* .nav > li:hover .dropdown-menu {
  display: block;
} */

.dropdown-menu > li > a:focus,
.dropdown-menu > li > a:hover {
  background-image: linear-gradient(top, #dedede 0, #dedede 100%);
}
.dropdown-menu > li > a {
  line-height: 2;
}
.input-group-btn:last-child > .btn,
.input-group-btn:last-child > .btn-group {
  z-index: 2;
  margin-left: 0px;
}

.mybtn {
  position: fixed;
  right: 10px;
  bottom: 20px;
  display: block;
  width: 50px;
  height: 50px;
  border-radius: 50px;
  padding: 0px;
  text-align: center;
  line-height: 50px;
}

.modal.left .modal-dialog,
.modal.right .modal-dialog {
  position: fixed;
  margin: auto;
  width: 100vw;
  height: 100%;
  -webkit-transform: translate3d(0%, 0, 0);
  -ms-transform: translate3d(0%, 0, 0);
  -o-transform: translate3d(0%, 0, 0);
  transform: translate3d(0%, 0, 0);
}

@media (min-width: 768px) {
  .modal.left .modal-dialog,
  .modal.right .modal-dialog {
    position: fixed;
    margin: auto;
    width: 375px;
    height: 100%;
    -webkit-transform: translate3d(0%, 0, 0);
    -ms-transform: translate3d(0%, 0, 0);
    -o-transform: translate3d(0%, 0, 0);
    transform: translate3d(0%, 0, 0);
  }
}

.modal.left .modal-content,
.modal.right .modal-content {
  height: 100%;
  overflow-y: auto;
}

.modal.left .modal-body,
.modal.right .modal-body {
  padding: 15px 15px 80px;
}

/*Left*/
.modal.left.fade .modal-dialog {
  left: -100vw;
  -webkit-transition: opacity 0.3s linear, left 0.3s ease-out;
  -moz-transition: opacity 0.3s linear, left 0.3s ease-out;
  -o-transition: opacity 0.3s linear, left 0.3s ease-out;
  transition: opacity 0.3s linear, left 0.3s ease-out;
}

.modal.left.fade.in .modal-dialog {
  left: 0;
}

@media (min-width: 768px) {
  .modal.right.fade .modal-dialog {
    right: -320px;
  }
  .modal.left.fade .modal-dialog {
    left: -320px;
  }
}

/*Right*/
.modal.right.fade .modal-dialog {
  right: -100vw;
  -webkit-transition: opacity 0.3s linear, right 0.3s ease-out;
  -moz-transition: opacity 0.3s linear, right 0.3s ease-out;
  -o-transition: opacity 0.3s linear, right 0.3s ease-out;
  transition: opacity 0.3s linear, right 0.3s ease-out;
}

.modal.right.fade.in .modal-dialog {
  right: 0;
}

/* ----- MODAL STYLE ----- */
.modal-content {
  border-radius: 0;
  border: none;
}

.modal-header {
  color: white;
  border-bottom-color: #eeeeee;
  background-color: rgba(18, 21, 23, 1);
  padding: 20px;
  height: 72px;
  border-bottom: 0.5px solid rgba(200, 200, 200, 1);
}
.modal-content {
  background-color: rgba(200, 200, 200, 1);
}
.modal.right .modal-body {
  padding-top: 15px;
  padding-right: 0px;
  padding-bottom: 80px;
  padding-left: 0px;
}
.glyphicon-menu-right {
  margin-top: 2px;
}

/* @keyframes rotatefresh {
  from {
    transform: rotate(0deg);
  }
  to {
    transform: rotate(90deg);
    transition: all 0.5s;
  }
}
@keyframes rotatefresh2 {
  from {
    transform: rotate(90deg);
  }
  to {
    transform: rotate(0deg);
    transition: all 0.5s;
  }
} */
</style>



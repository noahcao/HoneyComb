<template>
  <section class="container">
    <form class="form-horizontal">
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <div class="md-form">
            <input type="text" id="inputMDEx1" class="form-control" v-model="username" autocomplete="off">
            <label for="inputMDEx1">User Name</label>
          </div>

        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <div class="md-form">
            <input id="inputMDEx2" class="form-control" type="password" v-model="userpwd" autocomplete="off">
            <label for="inputMDEx2">Password</label>
          </div>

        </div>
      </div>

      <!-- <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <label class="col-xs-6 control-label col-md-6">User Name</label>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <input type="text" class="form-control" placeholder="User" v-model="username">

        </div>
      </div> -->
      <!-- <div class="md-form">
        <input type="text" id="form1" class="form-control">
        <label for="form1">Example label</label>
      </div> -->
      <!-- <div class="form-group">
        <div class="col-xs-1 col-md-2"></div>
        <label class="col-xs-2 control-label col-md-2">Password</label>
      </div> -->
      <!-- <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <input type="password" class="form-control" placeholder="Password" v-model="userpwd">
        </div>
      </div> -->
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class=" col-xs-10 col-md-10">
          <label class="pull-left">
            <input type="checkbox">Remember Me
          </label>
          <a class="pull-right" @click="toRegister">
            No Account?
          </a>
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-offset-1 col-xs-10">
          <button type="button" @click="login" class="btn btn-elegant pull-left" id="login-button">Login</button>
          <!-- <router-link :to="{ name: 'Register' }">
          <button type="button" class="btn btn-default">注册</button>
        </router-link> -->
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import '../../../static/js/mdb'
import $ from 'jquery'
export default {
  name: 'login',
  data () {
    return {
      username: '',
      userpwd: ''
    }
  },
  methods: {
    toRegister () {
      this.data.LRList[0].status = false
    },
    login () {
      if (this.username !== '' && this.userpwd !== '') {
        this.$http.post('/loginhandle', { name: this.username, pwd: this.userpwd })
          .then((res) => {
            if (res.data.id !== null) {
              alert('Success!')
              this.data.id = res.data.id
              window.location.reload()
            } else {
              alert('Username or password error!')
            }
          })
      }
    },
    keyListener (e) {
      if (e.keyCode === 13) {
        $('#login-button').click()
      }
    }
  },
  mounted () {
    document.onkeydown = this.keyListener
  }
}
</script>

<style scoped>
@import "../../../static/css/mdb";

.md-form .form-control {
  margin: 0 0 0.5rem 0;
  border-radius: 0;
  padding: 0.9rem 0 0 0;
  background-image: none;
  background-color: transparent;
}
.md-form label {
  position: absolute;
  top: 1.8rem;
  left: 0.8rem;
  font-size: 18px;
  -webkit-transition: 0.2s ease-out;
  transition: 0.2s ease-out;
  cursor: text;
  color: rgba(18, 21, 23, 0.5);
  padding-bottom: 1px;
  font-weight: 400;
}
.inputMDEx1 {
  height: 38px;
  border-bottom: 1px solid #000;
}
.inputMDEx2 {
  height: 38px;
  border-bottom: 1px solid #000;
}
.form-horizontal {
  width: 100vw;
}
.container {
  width: 100vw;
  padding-left: 0px;
  padding-right: 0px;
}
.form-horizontal {
  margin-top: 5%;
}
.form-group {
  width: 100vw;
}
.form-horizontal .form-group {
  margin-right: 0px;
  margin-left: 0px;
}
@media (min-width: 768px) {
  .form-horizontal {
    width: 375px;
  }
  .container {
    width: 375px;
    padding-left: 0px;
    padding-right: 0px;
  }
  .form-horizontal {
    margin-top: 5%;
  }
  .form-group {
    width: 375px;
  }
}

.form-control {
  border: none;
  border-radius: 4px;
  height: 38px;
  margin-left: auto;
  margin-right: auto;
  color: #000;
}
input::-webkit-input-placeholder {
  /* WebKit browsers */
  color: rgba(255, 255, 255, 0.9);
}
input {
  transition: all 1s ease;
  background-color: rgba(36, 41, 46, 0.3);
  outline: none;
  box-shadow: none;
}
input:hover,
input:focus {
  background-color: rgba(36, 41, 46, 0.5);
  border: 1px solid grey;
  box-shadow: none;
}
.container {
  margin-top: 80px;
  text-align: center;
}
.btn-default {
  transition: all 0.3s ease;
  background: rgba(255, 255, 255, 0.5);
  border-color: rgba(36, 41, 46, 0);
  display: inline-block;
  height: 35px;
}
.input-group-btn > .btn:active,
.input-group-btn > .btn:focus {
  z-index: 2;
  box-shadow: none;
  outline: none;
}
.btn-default:active:focus {
  color: #333;
  background-color: #d4d4d4;
  border-color: rgba(36, 41, 46, 0);
  outline: none;
}
.btn-default:hover {
  color: #333;
  background-color: rgba(255, 255, 255, 0.9);
  border-color: rgba(36, 41, 46, 0);
}
.btn-default:focus {
  box-shadow: none;
  outline: none;
}
@media (min-width: 768px) {
  .form-horizontal .control-label {
    padding-top: 7px;
    margin-bottom: 0;
    text-align: left;
  }
}

input:-webkit-autofill {
  -webkit-box-shadow: 0 0 0px 1000px white inset;
  -webkit-text-fill-color: #333;
}
</style>

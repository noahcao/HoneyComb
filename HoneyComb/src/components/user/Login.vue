<template>
  <section class="container">
    <form class="form-horizontal">
      <div class="form-group">
        <div class="col-xs-1 col-md-2"></div>
        <label class="col-xs-2 control-label col-md-2">User Name</label>
        <div class="col-md-4 col-xs-6">
          <input type="text" class="form-control" placeholder="User" v-model="username">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-2"></div>
        <label class="col-xs-2 control-label col-md-2">Password</label>
        <div class="col-md-4 col-xs-6">
          <input type="password" class="form-control" placeholder="Password" v-model="userpwd">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-offset-4 col-xs-10 col-md-4">
          <label class="pull-left">
            <input type="checkbox">Remember Me
          </label>
          <router-link :to="{ name: 'Register' }" class="pull-right">
            No Account?
          </router-link>
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10 col-md-8">
          <button type="button" @click="login" class="btn btn-default" id="login-button">Login</button>
          <!-- <router-link :to="{ name: 'Register' }">
          <button type="button" class="btn btn-default">注册</button>
        </router-link> -->
        </div>
      </div>
    </form>
  </section>
</template>

<script>
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
    login () {
      if (this.username !== '' && this.userpwd !== '') {
        this.$http.post('/loginhandle', { name: this.username, pwd: this.userpwd })
          .then((res) => {
            if (res.data.id !== null) {
              alert('success')
            } else {
              alert('用户名或密码错误')
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
.form-horizontal {
  margin-top: 5%;
}
.form-group {
  text-align: center;
}
.form-control {
  border: none;
  border-radius: 4px;
  color: rgba(255, 255, 255, 0.8);
  height: 46px;
  margin-left: auto;
  margin-right: auto;
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
</style>

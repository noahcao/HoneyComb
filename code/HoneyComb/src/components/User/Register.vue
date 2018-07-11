<template>
  <section class="container">
    <form class="form-horizontal">
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <label class="col-xs-6 control-label col-md-6">User Name</label>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-xs-10 col-md-10">
          <input type="text" class="form-control" id="name" aria-describedby="name-help" placeholder="User">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <label class="col-md-6 control-label col-xs-6">Email</label>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <input type="email" class="form-control" id="email" aria-describedby="email-help" placeholder="Email">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <label class="col-xs-6 control-label col-md-6">Password</label>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-xs-10 col-md-10">
          <input type="password" class="form-control" id="password" aria-describedby="password-help" placeholder="Password">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <label class="col-xs-6 control-label col-md-6">Confirm Your Password</label>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-xs-10 col-md-10">
          <input type="password" class="form-control" id="repeat" placeholder="Password Again">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-offset-1 col-xs-6 col-md-4">
          <button type="button" @click="register" class="btn btn-default pull-left">
            <strong>Submit</strong>
          </button>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import $ from 'jquery'
export default {
  name: 'register',
  methods: {
    register () {
      $('.has-error').removeClass('has-error')
      $('.help-block').remove()
      var pwd = document.getElementById('password').value
      var repeat = document.getElementById('repeat').value
      var name = document.getElementById('name').value
      var email = document.getElementById('email').value
      var flag = true
      if (repeat !== pwd) alert('两次密码输入不一致')
      else {
        if (pwd !== '' && name !== '') {
          var alphabet = /[a-z]/i
          var number = /[0-9]/
          // eslint-disable-next-line
          var re = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
          if (!re.test(email)) {
            $('#email').parent().parent().addClass('has-error')
            $('#email').after('<span id="email-help" class="help-block">Email format error</span>')
            flag = false
          }
          if (!alphabet.test(pwd) || !number.test(pwd)) {
            $('#password').parent().parent().addClass('has-error')
            $('#password').after('<span id="password-help" class="help-block">Password must contains alphabet and number</span>')
            flag = false
            document.getElementById('repeat').value = ''
            document.getElementById('password').value = ''
          }
          if (!flag) return
          this.$http.post('/registerhandle', {name: name, email: email, pwd: pwd})
            .then((response) => {
              if (response.data.id === null) {
                $('#name').parent().parent().addClass('has-error')
                $('#name').after('<span id="name-help" class="help-block">User name existed</span>')
              } else {
                this.data.id = response.data.id
                window.location.reload()
              }
            })
        }
      }
    },
    keyListener (e) {
      if (e.keyCode === 13) {
        this.button.click()
      }
    }
  },
  mounted () {
    document.onkeydown = this.keyListener
    var buttons = document.getElementsByTagName('button')
    for (var button of buttons) {
      if (button.innerHTML === '提交') {
        this.button = button
        break
      }
    }
  }
}
</script>

<style scoped>
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
@media (min-width: 768px) {
  .form-horizontal .control-label {
    padding-top: 7px;
    margin-bottom: 0;
    text-align: left;
  }
}
.form-horizontal {
  width: 375px;
}
</style>

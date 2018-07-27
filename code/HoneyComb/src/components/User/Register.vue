<template>
  <section class="container">
    <form class="form-horizontal">
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <div class="md-form">
            <input id="inputMDEx1" class="form-control" type="text" autocomplete="off">
            <label for="inputMDEx1">User Name</label>
          </div>

        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <div class="md-form">
            <input id="inputMDEx2" class="form-control" type="email" autocomplete="off">
            <label for="inputMDEx2">Email</label>
          </div>

        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <div class="md-form">
            <input id="inputMDEx3" class="form-control" type="password" aria-describedby="password-help" autocomplete="off">
            <label for="inputMDEx3">Password</label>
          </div>

        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-1 col-md-1"></div>
        <div class="col-md-10 col-xs-10">
          <div class="md-form">
            <input id="inputMDEx4" class="form-control" type="password" aria-describedby="password-help" autocomplete="off">
            <label for="inputMDEx4">Confirm Your Password</label>
          </div>

        </div>
      </div>
      
      <div class="form-group">
        <div class="col-xs-offset-1 col-xs-6 col-md-4">
          <button type="button" @click="register" class="btn btn-elegant pull-left">
            <strong>Submit</strong>
          </button>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import '../../../static/js/mdb'
import $ from 'jquery'
export default {
  name: 'register',
  methods: {
    register () {
      $('.has-error').removeClass('has-error')
      $('.help-block').remove()
      var pwd = document.getElementById('inputMDEx3').value
      var repeat = document.getElementById('inputMDEx4').value
      var name = document.getElementById('inputMDEx1').value
      var email = document.getElementById('inputMDEx2').value
      var flag = true
      if (repeat !== pwd) alert('Password Inconsistency')
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
          this.$http.post('/registerhandle', { name: name, email: email, pwd: pwd })
            .then((response) => {
              if (response.data.id === null) {
                $('#name').parent().parent().addClass('has-error')
                $('#name').after('<span id="name-help" class="help-block">User name existed</span>')
              } else {
                alert('success')
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
.inputMDEx1, .inputMDEx2, .inputMDEx3, .inputMDEx4 {
  height: 38px;
  border-bottom: 1px solid #000;
}
.form-horizontal .form-group {
  margin-right: 0px;
  margin-left: 0px;
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
  margin-top: 30px;
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
.form-control {
  border: none;
  border-radius: 4px;
  height: 38px;
  margin-left: auto;
  margin-right: auto;
  color: #000;
}
</style>

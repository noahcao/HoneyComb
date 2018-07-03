<template>
  <section class="container">
    <head-bar headtext="用户注册"></head-bar>
    <form class="form-horizontal">
      <div class="form-group">
        <label class="col-xs-2 control-label col-md-2">用户名</label>
        <div class="col-xs-10 col-md-8">
          <input type="text" class="form-control" id="name" aria-describedby="name-help" placeholder="User">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label col-xs-2">手机号码</label>
        <div class="col-md-8 col-xs-10">
          <input type="tel" class="form-control" id="phone" aria-describedby="phone-help" placeholder="Phone">
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label col-xs-2">电子邮箱</label>
        <div class="col-md-8 col-xs-10">
          <input type="email" class="form-control" id="email" aria-describedby="email-help" placeholder="Email">
        </div>
      </div>
      <div class="form-group">
        <label class="col-xs-2 control-label col-md-2">密码</label>
        <div class="col-xs-10 col-md-8">
          <input type="password" class="form-control" id="password" aria-describedby="password-help" placeholder="Password">
        </div>
      </div>
      <div class="form-group">
        <label class="col-xs-2 control-label col-md-2">确认密码</label>
        <div class="col-xs-10 col-md-8">
          <input type="password" class="form-control" id="repeat" placeholder="Password Again">
        </div>
      </div>
      <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10 col-md-8">
          <button type="button" @click="register" class="btn btn-default">提交</button>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
import HeadBar from '@/components/components/head-bar'
import $ from 'jquery'
export default {
  name: 'register',
  components: {
    HeadBar
  },
  methods: {
    register () {
      $('.has-error').removeClass('has-error')
      $('.help-block').remove()
      var pwd = document.getElementById('password').value
      var repeat = document.getElementById('repeat').value
      var name = document.getElementById('name').value
      var email = document.getElementById('email').value
      var phone = document.getElementById('phone').value
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
            $('#email').after('<span id="email-help" class="help-block">邮箱格式错误</span>')
            flag = false
          }
          if (isNaN(Number(phone)) || phone.length !== 11) {
            $('#phone').parent().parent().addClass('has-error')
            $('#phone').after('<span id="phone-help" class="help-block">手机格式错误</span>')
            flag = false
          }
          if (!alphabet.test(pwd) || !number.test(pwd)) {
            $('#password').parent().parent().addClass('has-error')
            $('#password').after('<span id="password-help" class="help-block">密码必须包含数字和字母</span>')
            flag = false
            document.getElementById('repeat').value = ''
            document.getElementById('password').value = ''
          }
          for (var user of this.data.UserList) {
            if (user.name === name) {
              $('#name').parent().parent().addClass('has-error')
              $('#name').after('<span id="name-help" class="help-block">用户名已被注册</span>')
              flag = false
              break
            }
          }
          if (!flag) return
          var len = this.data.UserList.length
          this.data.UserList.push({
            id: len,
            name: name,
            pwd: pwd,
            phone: phone,
            buy: [],
            email: email,
            icon: ''
          })
          var url = window.location.href
          url = url.substring(0, url.length - 8)
          window.location.href = url + 'login'
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

</style>

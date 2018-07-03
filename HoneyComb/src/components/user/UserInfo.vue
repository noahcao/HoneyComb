<template>
  <form v-if="login" class="form-horizontal">
    <div class="form-group">
      <label class="col-md-2 control-label">头像</label>
      <img v-if="user.icon === ''" src="../../../static/default.svg" alt="140x140" class="img-circle" />
      <img v-else :src="user.icon" alt="140x140" class="img-circle" />
      <input type="file" class="inline hide" id="icon">
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label">用户名</label>
      <div class="col-md-10">
        <input type="text" class="form-control" id="name" aria-describedby="name-help" :value="user.name">
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label">新密码</label>
      <div class="col-md-10">
        <input type="password" class="form-control" id="password" aria-describedby="password-help" placeholder="留空则不更改">
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label">手机号码</label>
      <div class="col-md-10">
        <input type="tel" class="form-control" id="phone" aria-describedby="phone-help" :value="user.phone" @change="checkPhone">
      </div>
    </div>
    <div class="form-group">
      <label class="col-md-2 control-label">电子邮箱</label>
      <div class="col-md-10">
        <input type="email" class="form-control" id="email" aria-describedby="email-help" :value="user.email">
      </div>
    </div>
    <div class="form-group">
      <div class="col-md-offset-2 col-md-10">
        <a tabindex="0" data-trigger="focus" data-container="body" id="dialog" data-placement="bottom" data-content="保存成功">
          <button type="button" class="btn btn-default" @click="save">保存</button>
        </a>
        <button type="button" class="btn btn-danger inline" @click="quit">退出登录</button>
      </div>
    </div>
  </form>
</template>

<script>
import $ from 'jquery'

export default {
  name: 'user-info',
  data () {
    return {
      login: this.data.LoginId !== '',
      user: this.data.UserList[this.data.LoginId],
      button: ''
    }
  },
  methods: {
    quit () {
      this.data.LoginId = ''
      var url = window.location.href
      url = url.substring(0, url.length - 9)
      window.location.href = url + 'login'
    },
    checkPhone () {
      var phone = document.getElementById('phone').value
      document.getElementById('phone').value = parseInt(phone) > 0 ? parseInt(phone) : ''
    },
    save () {
      $('.has-error').removeClass('has-error')
      $('.help-block').remove()
      var pwd = document.getElementById('password').value
      var name = document.getElementById('name').value
      var phone = document.getElementById('phone').value
      var email = document.getElementById('email').value
      var icon = document.getElementById('icon').value
      var flag = true
      if (phone.length !== 11) {
        $('#dialog').popover('destroy')
        $('#phone').parent().parent().addClass('has-error')
        $('#phone').after('<span id="phone-help" class="help-block">手机格式错误</span>')
        flag = false
      }
      if (icon !== '') {
        this.data.UserList[this.data.LoginId].icon = icon
        this.user.icon = icon
      }
      // eslint-disable-next-line
      var re = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
      if (!re.test(email)) {
        $('#dialog').popover('destroy')
        $('#email').parent().parent().addClass('has-error')
        $('#email').after('<span id="email-help" class="help-block">邮箱格式错误</span>')
        flag = false
      }
      for (var user of this.data.UserList) {
        if (user.name === name) {
          $('#name').parent().parent().addClass('has-error')
          $('#name').after('<span id="name-help" class="help-block">用户名已被注册</span>')
          flag = false
          break
        }
      }
      if (pwd !== '') {
        var alphabet = /[a-z]/i
        var number = /[0-9]/
        if (!alphabet.test(pwd) || !number.test(pwd)) {
          $('#dialog').popover('destroy')
          $('#password').parent().parent().addClass('has-error')
          $('#password').after('<span id="password-help" class="help-block">密码必须包含数字和字母</span>')
          flag = false
        } else if (flag) this.data.UserList[this.data.LoginId].pwd = pwd
      }
      if (flag) {
        this.data.UserList[this.data.LoginId].name = name
        this.data.UserList[this.data.LoginId].phone = phone
        this.data.UserList[this.data.LoginId].email = email
        $('#dialog').popover('show')
      }
      // this.user.name = name
      // this.user.phone = phone
      // this.user.email = email
      document.getElementById('password').value = ''
    },
    keyListener (e) {
      if (e.keyCode === 13) {
        this.button.click()
      }
    }
  },
  mounted () {
    if (this.data.LoginId === '') {
      var url = window.location.href
      url = url.substring(0, url.length - 9)
      window.location.href = url + 'login'
    }
    document.onkeydown = this.keyListener
    var buttons = document.getElementsByTagName('button')
    for (var button of buttons) {
      if (button.innerHTML === '保存') {
        this.button = button
        break
      }
    }
  }
}
</script>

<style scoped>
.inline {
  float: right;
}
</style>

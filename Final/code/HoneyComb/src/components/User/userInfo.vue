<template>
  <section>
    <nav-bar></nav-bar>
    <div class="row">
      <div class="col-sm-0 col-md-1"></div>
      <div class="col-xs-12 col-sm-4 col-md-3">
        <div class="thumbnail">
          <img class="img-rounded avatar" :src="defaulti" alt="70x70" @click="upload_img" v-if="icon === null">
          <img class="img-rounded avatar" :src="icon" alt="70x70" @click="upload_img" v-else>
          <input type="file" accept="image/*" id="icon" class="hidden" v-on:change="change_img" />
          <div class="caption">
            <h3>{{name}}</h3>
            <p v-if="editBio">{{bio}}</p>
            <p>
              <button class="btn btn-default btn-full-width" type="button" @click="edit_bio" v-if="editBio">
                Edit Bio
              </button>
              <textarea rows="5" v-else v-model="bios"></textarea>
              <button class="btn btn-default" v-if="!editBio" type="button" @click="save_bio">save</button>
              <button class="btn btn-default" v-if="!editBio" type="button" @click="edit_bio">cancel</button>
              <br/>
              <button class="btn btn-default btn-full-width" data-toggle="modal" data-target="#profile_modal" type="button">
                Edit Profile
              </button>
            </p>
          </div>
        </div>
      </div>
      <div class="modal fade" id="profile_modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <h4 class="modal-title" id="exampleModalLabel">Edit Profile</h4>
            </div>
            <div class="modal-body">
              <form>
                <div class="form-group">
                  <label for="recipient-name" class="control-label">New Email:</label>
                  <input type="text" class="form-control" id="email1" placeholder="Empty to not changed">
                </div>
                <div class="form-group">
                  <label for="message-text" class="control-label">New Password:</label>
                  <input class="form-control" type="password" id="pwd1" placeholder="Empty to not changed" v-on:change="passsChange" v-model="message">
                </div>
                <div class="form-group" v-show="passs">
                  <label for="message-text" class="control-label">Confirm Password:</label>
                  <input class="form-control" type="password" id="pwd2">
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" id="close" class="btn btn-primary" data-dismiss="modal" @click="closeProfile">Close</button>
              <button type="button" class="btn btn-primary" @click="submitProfile">Submit</button>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-8 col-md-7 col-xs-12">
        <div class="r">
          <ul class="nav nav-tabs">
            <li role="presentation" class="active">
              <a class="tabb" href="#collects" data-toggle="tab">Collects</a>
            </li>
            <li role="presentation">
              <a class="tabb" href="#trends" data-toggle="tab">Trends</a>
            </li>
            <li role="presentation">
              <a class="tabb" href="#history" data-toggle="tab">History</a>
            </li>
          </ul>
          <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="collects">
              <collects></collects>
            </div>
            <div class="tab-pane fade" id="trends">
              <trends></trends>
            </div>
            <div class="tab-pane fade" id="history">
              <history></history>
            </div>
          </div>
        </div>
      </div>
      <div class="col-sm-0 col-md-1"></div>
    </div>
  </section>
</template>

<script>
import $ from 'jquery'
import navBar from '../main/NavBar'
import Net from './infoContent/Net'
import Collects from './infoContent/Collects'
import History from './infoContent/History'
import Trends from './infoContent/Trends'

export default {
  name: 'userInfo',
  components: {
    navBar,
    Net,
    Collects,
    Trends,
    History
  },
  data () {
    return {
      editBio: true,
      bio:
        'Oops, empty here!',
      id: this.data.id,
      name: null,
      icon: null,
      bios: 'Oops, empty here!',
      iconInput: null,
      defaulti: null,
      passs: false,
      message: ''
    }
  },
  mounted () {
    $('canvas').remove()
    this.loginJudge()
  },
  methods: {
    closeProfile () {
      console.log($('#email1').val())
      $('#email1').val('')
      console.log($('#email1').val())
      console.log($('#pwd1').val())
      $('#pwd1').val('')
      this.message = ''
      console.log($('#pwd1').val())
      $('#pwd2').val('')
      this.passs = false
      $('#email1').parent().removeClass('has-error')
      $('#pwd1').parent().removeClass('has-error')
      $('#pwd2').parent().removeClass('has-error')
      $('#password-help').remove()
      $('#email-help').remove()
    },
    submitProfile () {
      var email = $('#email1').val()
      var pwd1 = $('#pwd1').val()
      var pwd2 = $('#pwd2').val()
      var alphabet = /[a-z]/i
      var number = /[0-9]/
      var flag = true
      console.log(email)
      console.log(pwd1)
      console.log(pwd2)
      if (pwd1 !== '' && pwd1 !== pwd2) {
        console.log('pwd error')
        $('#pwd1').parent().addClass('has-error')
        $('#pwd2').parent().addClass('has-error')
        $('#password-help').remove()
        $('#pwd2').after('<span id="password-help" class="help-block">Password Inconsistency</span>')
        flag = false
        $('#pwd1').val('')
        this.message = ''
        $('#pwd2').val('')
      }
      // eslint-disable-next-line
      var re = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
      if (email !== '' && !re.test(email)) {
        console.log('email error')
        $('#email1').parent().addClass('has-error')
        $('#email-help').remove()
        $('#email1').after('<span id="email-help" class="help-block">Email format error</span>')
        flag = false
        return
      }
      if (pwd1 !== '' && (!alphabet.test(pwd1) || !number.test(pwd1))) {
        console.log('pwd error')
        $('#pwd1').parent().addClass('has-error')
        $('#pwd2').parent().addClass('has-error')
        $('#password-help').remove()
        $('#pwd2').after('<span id="password-help" class="help-block">Password Inconsistency</span>')
        flag = false
        $('#pwd1').val('')
        this.message = ''
        $('#pwd2').val('')
      }
      if (!flag) return
      console.log('hi there')
      if (email !== '' && pwd1 !== '') {
        this.$http.get('/updateemail', { params: { id: this.id, email: email } })
          .then((res) => {
            this.$http.get('/updatepwd', { params: { id: this.id, pwd: pwd1 } })
              .then((res) => {
                alert('Success!')
                $('#close').click()
              })
          })
      } else if (email === '' && pwd1 !== '') {
        this.$http.get('/updatepwd', { params: { id: this.id, pwd: pwd1 } })
          .then((res) => {
            alert('Success!')
            $('#close').click()
          })
      } else if (email !== '' && pwd1 === '') {
        this.$http.get('/updateemail', { params: { id: this.id, email: email } })
          .then((res) => {
            alert('Success!')
            $('#close').click()
          })
      }
      $('#close').click()
    },
    passsChange () {
      if (this.message === '') {
        console.log('disable')
        this.passs = false
      } else {
        console.log('enable')
        this.passs = true
      }
    },
    save_bio () {
      this.$http.get('/updatebio', { params: { id: this.id, bio: this.bios } })
        .then((res) => {
          this.bio = this.bios
          this.editBio = !this.editBio
        })
    },
    loginJudge () {
      this.$http.get('/loginstatus')
        .then((res) => {
          if (res.data.id !== null) {
            this.data.id = res.data.id
            this.id = res.data.id
            this.getUserInfo()
          }
        })
    },
    edit_bio () {
      this.editBio = !this.editBio
      this.bios = this.bio
      console.log(this.iconInput)
    },
    upload_img () {
      $('#icon').click()
    },
    change_img () {
      var icon = document.getElementById('icon').files[0]
      var imgSize = icon.size
      if (imgSize > 300 * 1024) {
        alert('File is too big!')
        $('#icon').val('')
        return
      }
      if (icon) {
        var r = new FileReader() // 本地预览
        r.readAsDataURL(icon) // Base64
        r.onload = () => {
          this.$http.post('/updateicon', { icon: r.result, id: this.id })
            .then((res) => {
              this.icon = res.data.icon
            })
        }
      }
    },
    getUserInfo () {
      this.$http.get('/getuser', { params: { id: this.id } })
        .then((res) => {
          this.name = res.data.name
          if (res.data.bio != null) {
            this.bio = res.data.bio
            this.bios = res.data.bio
          }
          this.icon = res.data.icon
          this.defaulti = '../../../static/pic/dft-header.png'
          console.log('getuser', res.data)
        })
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

textarea{
  margin-top: 5px;
}

.row {
  margin-left: 0px;
  margin-right: 0px;
  margin-top: 4.5%;
}
.r {
  margin-left: 5%;
  margin-right: 5%;
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

    -webkit-transition: border 0.2s ease-in-out;
    -o-transition: border 0.2s ease-in-out;
    transition: border 0.2s ease-in-out;
    box-shadow: none;
    padding-left: 12%
  }
@media (min-width: 768px) {
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
}
.thumbnail > img {
  display: block;
  /* max-width: 100%; */
  height: 240px;
  width: 240px;
  margin-top: 20px;
  margin-left: 9px;
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
.btn-primary {
  transition: all 0.3s ease;
  background: rgba(36, 41, 46, 0.9);
  border-color: rgba(36, 41, 46, 0);
  margin-top: 5px;
  color: white;
}
.btn-primary:active:focus {
  color: #333;
  background-color: #ffffff;
  border-color: rgba(36, 41, 46, 0);
}
.btn-primary:hover {
  color: #333;
  background-color: rgba(36, 41, 46, 0.2);
  border-color: rgba(36, 41, 46, 0);
}
.btn:active,
.btn:focus {
  z-index: 2;
  box-shadow: none;
  outline: none;
}
@media (min-width: 768px) {
  .modal-dialog {
    width: 600px;
    margin: 50px auto;
    margin-top: 50px;
    margin-right: auto;
    margin-bottom: 50px;
    margin-left: auto;
  }
}

.btn-full-width {
  width: 100%;
}
</style>

import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Login from '../components/user/Login'
import Register from '../components/user/Register'
import userInfo from '../components/user/userInfo'
import sideBar from '../components/Combs/sideBar'
// import outcomb from '../components/Combs/outComb'
// import navTest from '../components/main/navTest'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/register',
      name: 'Register',
      component: Register
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/userInfo',
      name: 'userInfo',
      component: userInfo
    },
    {
      path: '/sideBar',
      name: 'sideBar',
      component: sideBar
    }
    // {
    //   path: '/comb',
    //   name: 'outcomb',
    //   component: outcomb
    // }
  ]
})

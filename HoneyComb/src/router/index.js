import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Login from '../components/user/Login'
import Register from '../components/user/Register'

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
    }
  ]
})

import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import userInfo from '../components/user/userInfo'
import outcomb from '../components/Combs/outComb'
import search from '../components/Search/search'
import community from '../components/Community/community'
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
      path: '/userInfo',
      name: 'userInfo',
      component: userInfo
    },
    {
      path: '/comb',
      name: 'outcomb',
      component: outcomb

    },
    {
      path: '/search?content=:content',
      name: 'search',
      component: search

    },
    {
      path: '/community',
      name: 'community',
      component: community
    }
  ]
})

import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import userInfo from '../components/user/userInfo'
import outcomb from '../components/Combs/outComb'
import BackEndTest from '../components/BackEndTest/Sprint1'
import search from '../components/Search/search'
import community from '../components/Community/community'
import poster from '../components/Community/poster'
import UserComb from '../components/User/UserComb'
import Guide from '../components/Guide/Guide'
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
      path: '/backendtest',
      name: 'BackEndTest',
      component: BackEndTest
    },
    {
      path: '/search',
      name: 'search',
      component: search

    },
    {
      path: '/community',
      name: 'community',
      component: community
    },
    {
      path: '/poster/:panelid',
      name: 'poster',
      component: poster
    },
    {
      path: '/UserComb',
      name: 'UserComb',
      component: UserComb
    },
    {
      path: '/Guide',
      name: 'Guide',
      component: Guide
    }
  ]
})

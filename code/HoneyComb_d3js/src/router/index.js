import Vue from 'vue'
import Router from 'vue-router'
import PaperNet from '@/components/PaperNet.vue'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'PaperNet',
      component: PaperNet
    }
  ]
})

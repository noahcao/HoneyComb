import Vue from 'vue'
import Router from 'vue-router'
import D3Force from '@/components/D3_force.vue'

Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/',
      name: 'D3Force',
      component: D3Force
    }
  ]
})

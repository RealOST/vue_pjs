import Vue from 'vue'
import VueRouter from 'vue-router'
//解决重复点击导航项，或者重新向报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

import Home from '@/components/Home.vue'
import BrandList from '@/components/brand/BrandList.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: () => import('../components/Login.vue') },
  {
    path: '/home/:uname',
    name: 'Home',
    component: Home,
    children: [
      { path: '/index', name: "首页", component: () => import(`@/components/Index.vue`) },
      { path: '/member', component: () => import(`@/components/member/MemberList.vue`) },
      { path: '/baseconfig', component: () => import(`@/components/config/BaseConfig.vue`) },
      { path: '/user', component: () => import(`../components/system/UserList.vue`) },
      { path: '/role', component: () => import(`../components/system/RoleList.vue`) },
      { path: '/book', component: () => import(`../components/book/BookList.vue`) },
      { path: '/brand', component: BrandList }
    ]
  }
]

const router = new VueRouter({
  routes
})

// 挂载路由导航守卫,to表示将要访问的路径，from表示从哪里来，next是下一个要做的操作
// router.beforeEach((to, from, next) => {
//   if (to.path === '/login') return next()
//   // 获取token
//   const tokenStr = window.sessionStorage.getItem('token')
//   if (!tokenStr) return next('/login')
//   next()
// })


export default router

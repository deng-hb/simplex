import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    }, {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
    }, {
      path: '/sys/resource',
      name: '系统资源',
      component: () => import('./views/sys/resource.vue')
    }, {
      path: '/sys/role',
      name: '系统角色',
      component: () => import('./views/sys/role.vue')
    }, {
      path: '/sys/user',
      name: '系统用户',
      component: () => import('./views/sys/user.vue')
    }, {
      path: '/sys/accessLog',
      name: '访问日志',
      component: () => import('./views/sys/accessLog.vue')
    }
  ]
});

router.beforeEach((to, from, next) => {
  HeyUI.$LoadingBar.start();
  if (to.meta && to.meta.title) {
    document.title = to.meta.title + ' - 管理应用';
  } else {
    document.title = '管理系统';
  }
  next();
});

router.afterEach(() => {
  HeyUI.$LoadingBar.success();
});

export default router;

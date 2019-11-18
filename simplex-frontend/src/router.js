import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home.vue'

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      meta: {
        title: 'Home'
      },
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
      name: 'resource',
      meta: {
        title: '系统资源'
      },
      component: () => import('./views/sys/resource.vue')
    }, {
      path: '/sys/role',
      name: 'role',
      meta: {
        title: '系统角色'
      },
      component: () => import('./views/sys/role.vue')
    }, {
      path: '/sys/user',
      name: 'user',
      meta: {
        title: '系统用户'
      },
      component: () => import('./views/sys/user.vue')
    }, {
      path: '/sys/access-log',
      name: 'access-log',
      meta: {
        title: '访问日志'
      },
      component: () => import('./views/sys/access-log.vue')
    }
  ]
});

router.beforeEach((to, from, next) => {
  HeyUI.$LoadingBar.start();
  if (to.meta) {
    document.title = to.meta.title + ' - SimpleX';
  } else {
    document.title = 'SimpleX';
  }
  next();
});

router.afterEach(() => {
  HeyUI.$LoadingBar.success();
});

export default router;

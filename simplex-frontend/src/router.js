import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/home.vue'

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
      component: () => import('./views/about.vue')
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
    }, {
      path: '/demo/demo1/demo1-1',
      name: 'demo1-1',
      meta: {
        title: 'Demo1-1'
      },
      component: () => import('./views/demo/demo1/demo1-1.vue')
    }, {
      path: '/demo/demo1/demo1-2',
      name: 'demo1-2',
      meta: {
        title: 'Demo1-2'
      },
      component: () => import('./views/demo/demo1/demo1-2.vue')
    }, {
      path: '/demo/demo1/demo1-3',
      name: 'demo1-3',
      meta: {
        title: 'Demo1-3'
      },
      component: () => import('./views/demo/demo1/demo1-3.vue')
    }, {
      path: '/demo/demo2',
      name: 'demo2',
      meta: {
        title: 'Demo2'
      },
      component: () => import('./views/demo/demo2.vue')
    }, {
      path: '/demo/demo3',
      name: 'demo3',
      meta: {
        title: 'Demo3'
      },
      component: () => import('./views/demo/demo3.vue')
    }, {
      path: '/demo/demo3',
      name: 'demo3',
      meta: {
        title: 'Demo3'
      },
      component: () => import('./views/demo/demo3.vue')
    }, {
      path: '/demo/demo4',
      name: 'demo4',
      meta: {
        title: 'Demo4'
      },
      component: () => import('./views/demo/demo4.vue')
    }, {
      path: '/demo/demo5',
      name: 'demo5',
      meta: {
        title: 'Demo5'
      },
      component: () => import('./views/demo/demo5.vue')
    }, {
      path: '/demo/demo6',
      name: 'demo6',
      meta: {
        title: 'Demo6'
      },
      component: () => import('./views/demo/demo6.vue')
    }, {
      path: '/demo/demo7',
      name: 'demo7',
      meta: {
        title: 'Demo7'
      },
      component: () => import('./views/demo/demo7.vue')
    }, {
      path: '/demo/demo8',
      name: 'demo8',
      meta: {
        title: 'Demo8'
      },
      component: () => import('./views/demo/demo8.vue')
    }, {
      path: '/demo/demo9',
      name: 'demo9',
      meta: {
        title: 'Demo9'
      },
      component: () => import('./views/demo/demo9.vue')
    }, {
      path: '/demo/demo10',
      name: 'demo10',
      meta: {
        title: 'Demo10'
      },
      component: () => import('./views/demo/demo10.vue')
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

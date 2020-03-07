import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      avatar: 'https://gw.alipayobjects.com/zos/rmsportal/cnrhVkzwxjPwAaCfPbdc.png',
      name: '张三'
    },
    cachePage: [],
    signed: null != window.localStorage.getItem('token'),
    siderCollapsed: false,
  },
  mutations: {
    setCachePage(state, args) {
      state.cachePage = args;
    },
    setSigned(state, args) {
      state.signed = args;
    },
    updateSiderCollapse(state, isShow) {
      state.siderCollapsed = isShow;
    }
  },
  actions: {

  }
})

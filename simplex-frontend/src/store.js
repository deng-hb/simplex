import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    cachePage: [],
    signed: null != window.localStorage.getItem('token'),
  },
  mutations: {
    setCachePage(state, args) {
      state.cachePage = args;
    },
    setSigned(state, args) {
      state.signed = args;
    },
  },
  actions: {

  }
})

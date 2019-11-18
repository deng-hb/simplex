import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    cachePage: []
  },
  mutations: {
    setCachePage(state, args) {
      state.cachePage = args;
    },
  },
  actions: {

  }
})

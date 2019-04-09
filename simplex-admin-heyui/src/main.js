import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import HeyUI from 'heyui'

require("heyui/themes/index.css")

Vue.use(HeyUI);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

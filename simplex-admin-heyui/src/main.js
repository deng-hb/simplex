import Vue from 'vue'
import HeyUI from 'heyui'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import { hasApi } from '@/hasApi'

require("heyui/themes/index.css")

Vue.use(HeyUI)
Vue.use(hasApi)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

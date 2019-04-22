import Vue from 'vue'
import HeyUI from 'heyui'
import App from '@/App.vue'
import router from '@/router'
import store from '@/store'
import HasApi from '@/api/hasApi'

require("heyui/themes/index.css")

Vue.use(HeyUI)
Vue.use(HasApi)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

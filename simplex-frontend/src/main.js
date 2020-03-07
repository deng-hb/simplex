import vue from 'vue'
import heyui from 'heyui'
import router from '@/router'
import store from '@/store'
import app from '@/app'
import hasApi from '@/api/hasApi'

require('./css/app.less');

vue.use(heyui)
vue.use(hasApi)

vue.config.productionTip = false

new vue({
  router,
  store,
  render: h => h(app)
}).$mount('#app')

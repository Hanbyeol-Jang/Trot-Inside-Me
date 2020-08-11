import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
//도연
import VueSimpleAlert from "vue-simple-alert"
import VueCookies from 'vue-cookies'
import VueSession from 'vue-session'
import InfiniteLoading from 'vue-infinite-loading';
<<<<<<< HEAD
import VueConfirmDialog from 'vue-confirm-dialog'
 
Vue.use(VueConfirmDialog)
Vue.component('vue-confirm-dialog', VueConfirmDialog.default)
=======

import vueCalendar from 'vue2-simple-calendar'
import "./assets/style/vue2-simple-calendar.css"

>>>>>>> soyeong
//도연
var sessionOptions = {
  persist: true
}
Vue.use(VueSimpleAlert)
Vue.use(VueCookies)
Vue.use(VueSession, sessionOptions)
Vue.use(InfiniteLoading, { /* options */ })
Vue.use(vueCalendar)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')

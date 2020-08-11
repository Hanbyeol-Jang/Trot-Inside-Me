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
import VueConfirmDialog from 'vue-confirm-dialog'

Vue.use(VueConfirmDialog)
Vue.component('vue-confirm-dialog', VueConfirmDialog.default)

import vueCalendar from 'vue2-simple-calendar'
import "./assets/style/vue2-simple-calendar.css"

//도연
var sessionOptions = {
  persist: true
}
const config  = {
  locale: 'kr',
  languages: {
    kr: {
      showMore: '더보기',
      dayNameShort: ['일', '월', '화', '수', '목', '금', '토'],
      dayNameLong: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
      monthNameShort: ['1월', '2월', '3월.', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
      monthNameLong: ['1월', '2월', '3월.', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
    }
  }
}

Vue.use(VueSimpleAlert)
Vue.use(VueCookies)
Vue.use(VueSession, sessionOptions)
Vue.use(InfiniteLoading, { /* options */ })
Vue.use(vueCalendar, config)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')

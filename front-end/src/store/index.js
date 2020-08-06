import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'
import router from '@/router'
import axios from 'axios'

import SERVER from '@/api/drf'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
    singers: [],
    contentsCount: 0,
  },
  getters: {
    isLoggedIn: state => !!state.authToken,
    config: state => ({ headers: { token: `${state.authToken}` } }),
    singersLength: state => state.singers.length
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_SINGERS(state, singers) {
      state.singers = singers
    },
    SET_CONTENTS_CNT(state, cnt) {
      state.contentsCount = cnt
    }
  },
  actions: {
    login({ commit }, loginData) {
      console.log(loginData)
      axios.post(SERVER.URL + SERVER.ROUTES.login, loginData)
        .then(res => {
          commit('SET_TOKEN', res.data)
          router.push({ name: 'Home' })
        })
        .catch(err => console.log(err.response.data))
    },
    fetchSingers({ getters, commit }) {
      if (getters.singersLength < 1) {
        axios.get(SERVER.URL + SERVER.ROUTES.singerList)
            .then(res => { commit('SET_SINGERS', res.data) })
            .catch(err => { console.error(err) })
      }
    },
    getUser({ getters }) {
      axios.get(SERVER.URL + "/test", getters.config)
            .then(res => {
                console.log(res)
            })
        .catch((err)=>{
            console.error(err)
        })    
    },
    getContentsCount({ commit }, info) {
      const options = { params: { b_type: info.mediaType, s_idx: info.singerId }}
      axios.get(SERVER.URL + '/board/count', options)
        .then(res => { commit('SET_CONTENTS_CNT', res.data) })
        .catch(err => { console.error(err) })
    }
  },
  modules: {
  }
})

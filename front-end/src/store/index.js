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
    authAdmin: cookies.get('auth-admin'),
    singers: [],
    singer: {},
    programs: [],
    contentsCount: 0,
    user: {},
    isFollow: 0,
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
    SET_USER(state, userInfo) {
      state.user = userInfo
    },
    SET_SINGERS(state, singers) {
      state.singers = singers
    },
    SET_SINGER(state, singer) {
      state.singer = singer
    },
    SET_PROGRAMS(state, programs) {
      state.programs = programs
    },
    SET_CONTENTS_CNT(state, cnt) {
      state.contentsCount = cnt
    },
    SET_FOLLOW(state, isFollow) {
      state.isFollow = isFollow
    },
  },
  actions: {
    // Auth
    login({ commit }, loginData) {
      axios.post(SERVER.URL + SERVER.ROUTES.login, loginData)
        .then(res => {
          console.log('Admin Login SUCCESS')
          commit('SET_TOKEN', res.data) 
          router.push({ name: 'AdminView' })
        })
        .catch(err => console.log(err))
    },
    logout({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.logout, getters.config)
        .then(() => {
          console.log('Admin Logout SUCCESS')
          commit('SET_TOKEN', null)
          cookies.remove('auth-token')
          router.push({ name: 'Home' })
        })
        .catch(err => console.log(err))
    },
    kakaoLogin({ dispatch, commit }, accessToken) {
      const axiosConfig = { headers:{ access_token : accessToken } }
      axios.post(SERVER.URL + SERVER.ROUTES.kakaoLogin, null, axiosConfig)
        .then((res)=>{
          console.log('Kakao Login SUCCESS')
            commit('SET_TOKEN', res.data)
            dispatch('getUser')
            router.push({ name: 'Home' })
        })
        .catch((err)=>{ console.log(err) })
    },
    kakaoLogout({ commit }) {
      window.location.href = SERVER.ROUTES.kakaoLogout;
      console.log('Kakao Logout SUCCESS')
      commit('SET_TOKEN', null)
      cookies.remove('auth-token')
      router.push({ name: 'Home' })
    },
    kakaoOff({ getters }) { 
      axios.post(SERVER.URL + SERVER.ROUTES.kakaoOff, null, getters.config)
        .then((res) => {  
            console.log('Kakao OFF', res) 
            router.push({ name: 'Home' })
          })
        .catch((err)=>{ console.error(err) }) 
    },
    getUser({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.getUserInfo, getters.config)
        .then(res => {  commit('SET_USER', res.data) })
        .catch((err)=>{ console.error(err) })
    },

    // Feed Data
    getContentsCount({ commit }, info) {
      const options = { params: { b_type: info.mediaType, s_idx: info.singerId }}
      axios.get(SERVER.URL + '/board/count', options)
        .then(res => { commit('SET_CONTENTS_CNT', res.data) })
        .catch(err => { console.error(err) })
    },

    // Singer Data
    fetchSingers({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.singerList)
        .then(res => { commit('SET_SINGERS', res.data) })
        .catch(err => { console.error(err) })
    },
    postSinger(context, singerData) {
      axios.post(SERVER.URL + SERVER.ROUTES.singerCreate, singerData)
        .then(() => {
          console.log('Singer POST SUCCESS')
          router.push({ name: 'SingerManageView' })
        })
        .catch(err => console.log(err))
    },
    deleteSinger(context, singerId) { 
      axios.delete(SERVER.URL + SERVER.ROUTES.singerDelete + singerId)
        .then(() => {
          console.log('Singer DELETE SUCCESS')
          location.reload(true)
        })
        .catch(err => console.log(err))
    },
    getSingerDetail({ getters, commit }, singerId) {
      axios.get(SERVER.URL + SERVER.ROUTES.singerDetail + singerId, getters.config)
        .then((res) => {
          commit('SET_SINGER', res.data)
        })
        .catch(err => console.log(err))
    },
    // Follow Singer
    follow({ state, commit }, info) {
      console.log('follow')
      const options = {
        headers:{ token: state.authToken },
        params: { isfollow: info.f_flag }
      }
      axios.get(SERVER.URL + SERVER.ROUTES.follow + info.s_idx, options)
      .then(res => { 
        commit('SET_FOLLOW', res.data) 
        console.log(res.data)
      })
      .catch(err => { console.error(err) })
    },
    // Program Data
    fetchPrograms({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.programList)
        .then(res => { commit('SET_PROGRAMS', res.data) })
        .catch(err => { console.error(err) })
    },
    postProgram(context, singerData) {
      axios.post(SERVER.URL + SERVER.ROUTES.programCreate, singerData)
        .then(() => {
          console.log('Singer POST SUCCESS')
          router.push({ name: 'ProgramManageView' })
        })
        .catch(err => console.log(err))
    },
    deleteProgram(context, programId) { 
      axios.delete(SERVER.URL + SERVER.ROUTES.programDelete + programId)
        .then(() => {
          console.log('Singer DELETE SUCCESS')
          location.reload(true)
        })
        .catch(err => console.log(err))
    },

    scrollToTop() {
      scroll(0, 0)
    },
  },
  modules: {
  }
})

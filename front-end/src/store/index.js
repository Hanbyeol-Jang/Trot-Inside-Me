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
    isAdmin: 0,
    user: {},
    userDetail: {},
    singers: [],
    singer: {},
    programs: [],
    followBtn: false,
    followCnt: 0,
    userFollowing: [],
  },
  getters: {
    isLoggedIn: state => !!state.authToken,
    config: state => ({ headers: { token: `${state.authToken}` } }),
    singersLength: state => state.singers.length,
  },
  mutations: {
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_USER(state, userInfo) {
      state.user = userInfo
    },
    SET_ADMIN(state, isAdmin) {
      state.isAdmin = isAdmin
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
    SET_FOLLOW(state, isFollow) {
      state.followBtn = isFollow.f_flag
      state.followCnt = isFollow.f_cnt
    },
    followAdd(state) {
      state.followBtn = 1
      state.followCnt++
    },
    followBack(state) {
      state.followBtn = 0
      state.followCnt--
    },
    SET_FOLLOWLIST(state, followList) {
      state.userFollowing = followList
    },
  },
  actions: {
    // Auth
    login({ dispatch, commit }, loginData) {
      axios.post(SERVER.URL + SERVER.ROUTES.login, loginData)
        .then(res => {
          commit('SET_TOKEN', res.data)
          dispatch('getUser')
          router.push({ name: 'AdminView' })
        })
        .catch(err => console.log(err))
    },
    logout({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.logout, getters.config)
        .then(() => {
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
            commit('SET_TOKEN', res.data)
            dispatch('getUser')
            router.push({ name: 'Home' })
        })
        .catch((err)=>{ console.log(err) })
    },
    kakaoLogout({ commit }) {
      window.location.href = SERVER.ROUTES.kakaoLogout
      commit('SET_TOKEN', null)
      cookies.remove('auth-token')
      router.push({ name: 'Home' })
    },
    kakaoOff({ getters }) { 
      axios.post(SERVER.URL + SERVER.ROUTES.kakaoOff, null, getters.config)
        .then(() => { 
            router.push({ name: 'Home' })
          })
        .catch((err)=>{ console.error(err) }) 
    },
    getUser({ getters, commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.getUserInfo, getters.config)
        .then(res => { 
          commit('SET_USER', res.data)
        })
        .catch((err)=>{ console.error(err) })
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
          router.push({ name: 'SingerManageView' })
        })
        .catch(err => console.log(err))
    },
    deleteSinger(context, singerId) { 
      axios.delete(SERVER.URL + SERVER.ROUTES.singerDelete + singerId)
        .then(() => {
          location.reload(true)
        })
        .catch(err => console.log(err))
    },
    getSingerDetail({ getters, commit }, singerId) {
      axios.get(SERVER.URL + SERVER.ROUTES.singerDetail + singerId, getters.config)
        .then((res) => {
          commit('SET_SINGER', res.data)
          commit('SET_FOLLOW', { f_flag: res.data.f_flag, f_cnt: res.data.f_cnt })
        })
        .catch(err => console.log(err))
    },
    // Follow Singer
    follow({ state, commit }, info) {
      const options = {
        headers:{ token: state.authToken },
        params: { isfollow: info.f_flag }
      }
      axios.get(SERVER.URL + SERVER.ROUTES.follow + info.s_idx, options)
      .then(res => { 
        commit('SET_FOLLOW', { f_flag: res.data, f_cnt: state.followCnt })
        if (res.data) { commit('followAdd') } 
        else { commit('followBack') }
      })
      .catch(err => { console.error(err) })
    },
    getFollowList({ commit }, userId) {
      const options = { params:{ u_email : userId } }
      axios.get(SERVER.URL + SERVER.ROUTES.followSingersList, options)
      .then(res => { commit('SET_FOLLOWLIST', res.data) })
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
          router.push({ name: 'ProgramManageView' })
        })
        .catch(err => console.log(err))
    },
    deleteProgram(context, programId) { 
      axios.delete(SERVER.URL + SERVER.ROUTES.programDelete + programId)
        .then(() => {
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

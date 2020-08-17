import Vue from 'vue'
import Vuex from 'vuex'

import cookies from 'vue-cookies'
import router from '@/router'
import axios from 'axios'
import Swal from 'sweetalert2'

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
    checkvote:null,


    singerSchedule: [],
    indexedSchedule: [],
    CalendarScehdule: {},
    events: [],
    singerVote:0,
  },
  getters: { 
    isLoggedIn: state => !!state.authToken,
    config: state => ({ headers: { token: `${state.authToken}` } }),
    singersLength: state => state.singers.length,
  },
  mutations: {
    SET_SINGERVOTE(state,singerVote){
      state.singerVote = singerVote
    },
    SET_VOTE(state,checkvote){
      state.checkvote = checkvote
    },
    SET_TOKEN(state, token) {
      state.authToken = token
      cookies.set('auth-token', token)
    },
    SET_USER(state, userInfo) {
      state.user = userInfo
    },
    SET_USER_DETAIL(state, userInfo) {
      state.userDetail = userInfo
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
    SET_SINGER_SCHEDULE(state, schedule) {
      state.singerSchedule = schedule
    },
    SET_INDEX_SCHEDULE(state, schedule) {
      state.indexedSchedule = schedule
    },
    SET_EVENTS(state, events) {
      state.events = events
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
          Swal.fire({
            position: 'center',
            icon: 'success',
            title: '로그인 되었습니다!',
            showConfirmButton: false,
            timer: 1500
          })
          router.push({ name: 'Home' })
        })
        .catch((err)=>{ console.log(err) })
    },
    kakaoLogout({ commit }) {
      window.location.href = SERVER.ROUTES.kakaoLogout

      commit('SET_TOKEN', null)
      cookies.remove('auth-token')
      router.push({ name: 'Home' })
      // 로그아웃 확인 버튼
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
    getUserDetail({ commit }, userId) {
      axios.get(SERVER.URL + SERVER.ROUTES.getUserInfo + `/${userId}`)
        .then(res => {
          commit('SET_USER_DETAIL', res.data.userInfo)
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
      const config ={
        headers: { 'Content-Type': 'multipart/form-data' }
      }
      axios.post(SERVER.URL + SERVER.ROUTES.singerCreate, singerData, config)
        .then(() => {
          router.push({ name: 'SingerManageView' })
          Swal.fire({
            position: 'center',
            icon: 'success',
            text: '가수 등록 완료!',
            showConfirmButton: false,
            timer: 1500
          })
        })
        .catch(err => console.log(err))
    },
    deleteSinger(context, singerId) { 
      axios.delete(SERVER.URL + SERVER.ROUTES.singerDelete + singerId)
        .then(() => {
          Swal.fire({
            position: 'center',
            icon: 'success',
            text: '가수 삭제 완료!',
            showConfirmButton: false,
            timer: 500
          })
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
    getSingerSchedule({ commit, dispatch }, singerId) {
      axios.get(SERVER.URL + SERVER.ROUTES.singerScheduleList + singerId)
        .then((res) => {
          commit('SET_SINGER_SCHEDULE', res.data)
          dispatch('indexingSchedule', res.data)
        })
        .catch(err => console.log(err))
    },
    indexingSchedule({ commit }, scheduleList) {
      let result = new Object()
      let resultCnt = new Object()
      let events = []
      let today = new Date()
      let year = today.getFullYear()
      let month = today.getMonth() + 1

      if (month < 10) {
        month = "0" + month.toString()
      }
      for (let i = 0; i < scheduleList.length; i++) {
        if (scheduleList[i].bc_date) {
          let key = `${year}-${month}-${scheduleList[i].bc_date.slice(0,2)}`
          if (key in result) {
            result[key].push(scheduleList[i])
            resultCnt[key]++
          } else {
            result[key] = [scheduleList[i]]
            resultCnt[key] = 1
          }
        }
      }
      commit('SET_INDEX_SCHEDULE', result)
      for (const [key, value] of Object.entries(resultCnt)) {
        let context = {
          start : key,
          title : value,
        }
        events.push(context)
      }
      commit('SET_EVENTS', events)
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
      axios.get(SERVER.URL + SERVER.ROUTES.followSingersList + userId)
      .then(res => { commit('SET_FOLLOWLIST', res.data) })
      .catch(err => { console.error(err) })
    },

    // Program Data
    fetchPrograms({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.programList)
        .then(res => { commit('SET_PROGRAMS', res.data) })
        .catch(err => { console.error(err) })
    },
    postProgram(context, programData) {
      const config ={
        headers: { 'Content-Type': 'multipart/form-data' }
      }
      axios.post(SERVER.URL + SERVER.ROUTES.programCreate, programData, config)
        .then(() => {
          router.push({ name: 'ProgramManageView' })
          Swal.fire({
            position: 'center',
            icon: 'success',
            text: '프로그램 등록 완료!',
            showConfirmButton: false,
            timer: 1500
          })
        })
        .catch(err => console.log(err))
    },
    deleteProgram(context, programId) { 
      axios.delete(SERVER.URL + SERVER.ROUTES.programDelete + programId)
        .then(() => {
          location.reload(true)
          Swal.fire({
            position: 'center',
            icon: 'success',
            text: '프로그램 삭제 완료!',
            showConfirmButton: false,
            timer: 500
          })
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

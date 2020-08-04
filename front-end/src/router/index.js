import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/main/Home.vue'
import ArticleDetailView from '@/views/main/ArticleDetailView'
import VideoDetailView from '@/views/main/VideoDetailView'
import TvtableDetailView from '@/views/main/TvtableDetailView'
import SingerSearchView from '@/views/main/SingerSearchView'
import PageNotFound from '@/views/PageNotFound.vue'
import ErrorView from '@/views/ErrorView.vue'

import Login from '@/views/accounts/Login.vue'
import AdminLogin from '@/views/accounts/admin/AdminLogin.vue'
import Signup from '@/views/accounts/Signup.vue'
import CommunityView from '@/views/community/CommunityView.vue'
import VoteView from '@/views/vote/VoteView.vue'
import SingerDetailView from '@/views/main/SingerDetailView.vue'
import UserDetailView from '@/views/accounts/UserDetailView.vue'
import SignupCompleteView from '@/views/accounts/SignupCompleteView.vue'
import UserSettingView from '@/views/accounts/UserSettingView.vue'
import VideoListView from '@/views/main/VideoListView.vue'
import ArticleListView from '@/views/main/ArticleListView.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    // path: '/article/:articleId',
    path: '/article',
    name: 'ArticleDetailView',
    component: ArticleDetailView
  },
  {
    path: '/video/:videoId',
    name: 'VideoDetailView',
    component: VideoDetailView
  },
  {
    path: '/accounts/admin/login',
    name: 'AdminLogin',
    component: AdminLogin
  },
  {
    path: '/accounts/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/accounts/signup',
    name: 'Signup',
    component: Signup
  },
  {
    path: '/community',
    name: 'CommunityView',
    component: CommunityView
  },
  {
    path: '/vote',
    name: 'VoteView',
    component: VoteView
  },
  {
    path: '/tvtable',
    name: 'TvtableDetailView',
    component: TvtableDetailView
  },
  {
    path: '/singer/search',
    name: 'SingerSearchView',
    component: SingerSearchView
  },
  {
    path: '/singer/:singerId',
    name: 'SingerDetailView',
    component: SingerDetailView
  },
  {
    path: '/accounts/:userId',
    name: 'UserDetailView',
    component: UserDetailView
  },
  {
    path: '/accounts/signup/complete',
    name: 'SignupCompleteView',
    component: SignupCompleteView
  },
  {
    path: '/accounts/:userId/settings',
    name: 'UserSettingView',
    component: UserSettingView
  },
  {
    path: '/singer/:singerId/videos',
    name: 'VideoListView',
    component: VideoListView
  },
  {
    path: '/singer/:singerId/articles',
    name: 'ArticleListView',
    component: ArticleListView
  },
  {
    path : '*',
    name : 'PageNotFound',
    component : PageNotFound
  },
  {
    path : '/errorpage',
    name : 'ErrorView',
    component : ErrorView
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

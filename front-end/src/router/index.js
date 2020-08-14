import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/main/Home.vue'
import ArticleDetailView from '@/views/main/ArticleDetailView'
import VideoDetailView from '@/views/main/VideoDetailView'
import TvtableDetailView from '@/views/main/TvtableDetailView'
import SingerSearchView from '@/views/main/SingerSearchView'
import PageNotFound from '@/views/PageNotFound.vue'
import ErrorView from '@/views/ErrorView.vue'
import CommunityIndexView from '@/views/community/CommunityIndexView.vue'
import CommunityDetailView from '@/views/community/CommunityDetailView.vue'
import CommunityCreateView from '@/views/community/CommunityCreateView.vue'
import CommunityUpdateView from '@/views/community/CommunityUpdateView.vue'

import VoteCreateView from '@/views/vote/VoteCreateView.vue'
import VoteLocalSelectView from '@/views/vote/VoteLocalSelectView.vue'
import VoteOldrankView from '@/views/vote/VoteOldrankView.vue'
import VoteResultView from '@/views/vote/VoteResultView.vue'
import VoteView from '@/views/vote/VoteView.vue'


import Login from '@/views/accounts/Login.vue'
import Signup from '@/views/accounts/Signup.vue'
import SingerDetailView from '@/views/main/SingerDetailView.vue'
import UserDetailView from '@/views/accounts/UserDetailView.vue'
import SignupCompleteView from '@/views/accounts/SignupCompleteView.vue'
import UserSettingView from '@/views/accounts/UserSettingView.vue'
import VideoListView from '@/views/main/VideoListView.vue'
import ArticleListView from '@/views/main/ArticleListView.vue'
import SingerScheduleView from '@/views/main/SingerScheduleView.vue'

import AdminLogin from '@/views/accounts/admin/AdminLogin.vue'
import AdminView from '@/views/accounts/admin/AdminView.vue'
import SingerManageView from '@/views/accounts/admin/SingerManageView.vue'
import SingerCreateView from '@/views/accounts/admin/SingerCreateView.vue'
import ProgramManageView from '@/views/accounts/admin/ProgramManageView.vue'
import ProgramCreateView from '@/views/accounts/admin/ProgramCreateView.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    // path: '/article/:articleId',
    path: '/article/:articleId',
    name: 'ArticleDetailView',
    component: ArticleDetailView
  },
  {
    path: '/video/:videoId',
    name: 'VideoDetailView',
    component: VideoDetailView
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
    name: 'CommunityIndexView',
    component: CommunityIndexView
  },
  {
    path: '/community/:communityId',
    name: 'CommunityDetailView',
    component: CommunityDetailView
  },
  {
    path: '/create/community',
    name: 'CommunityCreateView',
    component: CommunityCreateView
  },
  {
    path: '/update/:communityId',
    name: 'CommunityUpdateView',
    component: CommunityUpdateView
  },
  {
    path: '/vote',
    name: 'VoteView',
    component: VoteView
  },
  {
    path: '/vote/local',
    name: 'VoteLocalSelectView',
    component: VoteLocalSelectView
  },
  {
    path: '/vote/old',
    name: 'VoteOldrankView',
    component: VoteOldrankView
  },
  {
    path: '/vote/result',
    name: 'VoteResultView',
    component: VoteResultView
  },
  {
    path: '/vote/create',
    name: 'VoteCreateView',
    component: VoteCreateView
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
    path: '/schedule/:singerId',
    name: 'SingerScheduleView',
    component: SingerScheduleView
  },

  // Admin Page
  {
    path: '/admin',
    name: 'AdminLogin',
    component: AdminLogin
  },
  {
    path: '/admin/main',
    name: 'AdminView',
    component: AdminView
  },
  {
    path: '/admin/singer',
    name: 'SingerManageView',
    component: SingerManageView
  },
  {
    path: '/admin/singer/create',
    name: 'SingerCreateView',
    component: SingerCreateView
  },
  {
    path: '/admin/program',
    name: 'ProgramManageView',
    component: ProgramManageView
  },
  {
    path: '/admin/program/create',
    name: 'ProgramCreateView',
    component: ProgramCreateView
  },

  // Error Page
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
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return {x: 0, y: 0}
    }
  }
})

router.beforeEach((to, from, next) => {
  // const publicPages = ['Login', 'Signup', 'Home', 'AdminLogin'] // Login 안해도 됨
  const loggedInPages = ['UserDetailView', 'UserSettingView', 
    'AdminView', 'SingerManageView', 'SingerCreateView', 'ProgramCreateView', 'ProgramManageView',]
  // const adminPages = ['AdminView']
  const authPages = ['Login', 'Signup', 'AdminLogin'] // Login 되어있으면 안됨

  // const authRequired = !publicPages.includes(to.name) // Login 해야함
  const authRequired = loggedInPages.includes(to.name) // Login 해야함
  const unAuthRequired = authPages.includes(to.name) // Login 해서는 안됨
  const isLoggedIn = !!Vue.$cookies.isKey('auth-token')

  if (unAuthRequired && isLoggedIn) {
    next('/')
  }
  if (authRequired && !isLoggedIn) {
    next({name: 'Login'})
  } else {
    next()
  }
  // authRequired && !isLoggedIn ? next({name: 'Login'}) : next()
})

export default router

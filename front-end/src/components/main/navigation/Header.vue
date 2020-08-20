<template>
  <div>
    <v-app-bar
      fixed
      absolute
      color="white"
      elevate-on-scroll 
      height="50px"
      scroll-target="#scrolling-techniques-7"
      class="header"
    >
      <v-btn v-if="navBool" icon @click="goInfo"><i class="fas fa-info-circle fa-lg"></i></v-btn>
      <v-btn v-if="!navBool" icon @click="goBack"><i class="fas fa-chevron-left fa-lg"></i></v-btn>
      <v-spacer></v-spacer>
      <v-toolbar-title class="d-flex align-center">
        <img v-if="routeName === 'Home'" src="@/assets/image/trot_logo.png" alt=""
          width="90px" class="main-logo">
        <span v-if="routeName === 'SingerSearchView'" class="">가수 검색</span>
        <span v-if="routeName === 'VoteView'" class="">투표</span>
        <span v-if="routeName === 'CommunityIndexView'" class="">수다방</span>
        <span v-if="routeName === 'TvtableDetailView'" class="">편성표 {{ todayDate }}</span>
        <span v-if="routeName === 'VideoListView'" class="">영상 보기</span>
        <span v-if="routeName === 'ArticleListView'" class="">기사 보기</span>
        <span v-if="routeName === 'AdminView'" class="">관리자 페이지</span>
        <span v-if="routeName === 'UserDetailView'" class="">프로필</span>
        <span v-if="routeName === 'SingerManageView'" class="">가수 관리</span>
        <span v-if="routeName === 'SingerCreateView'" class="">가수 등록</span>
        <span v-if="routeName === 'ProgramManageView'" class="">프로그램 관리</span>
        <span v-if="routeName === 'ProgramCreateView'" class="">프로그램 등록</span>
        <span v-if="routeName === 'SingerScheduleView'" class="">가수 스케줄</span>
        <span v-if="routeName === 'CommunityCreateView'" class="">게시글 작성</span>
        <span v-if="routeName === 'CommunityUpdateView'" class="">게시글 수정</span>
        <span v-if="routeName === 'UserLikeVideoView'" class="">찜한 영상</span>
        <span v-if="routeName === 'UserLikeArticleView'" class="">찜한 기사</span>
        <span v-if="routeName === 'SingerDetailView'">가수 정보</span>
        <span v-if="routeName === 'SingerVoteView'">투표 결과</span>
        <span v-if="routeName === 'VideoDetailView'">영상 상세 보기</span>
        <span v-if="routeName === 'ArticleDetailView'">기사 상세 보기</span>
        <span v-if="routeName === 'About'">서비스 소개</span>
        <span v-if="routeName === 'CommunityDetailView'">상세 보기</span>
        <span v-if="routeName === 'UserSettingView'">설정</span>
        <span v-if="routeName === 'UserLikeSingerView'">
          <span v-if="this.$route.params.userId === user.u_email">내 가수 보기</span>
          <span v-else>찜한 가수</span>
        </span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn v-if="navUser" icon @click="goUserDetail"><i class="fas fa-user fa-lg"></i></v-btn>
      <!-- <v-btn v-if="navHome" icon @click="goHome"><i class="fas fa-home fa-lg"></i></v-btn> -->
      <v-btn v-if="navSetting" icon @click="goSettings"><i class="fas fa-cog fa-lg"></i></v-btn>

      <!-- <v-btn v-if="navSetting" icon @click="goSettings"><i class="fas fa-cog fa-lg"></i></v-btn> -->
      <v-btn v-if="navNone" icon></v-btn>
    </v-app-bar>
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
var today = new Date()

export default {
    name: 'Header',
    data() {
      return {
        userId: this.$route.params.userId,
        date: today.getDate(),
        month: today.getMonth() + 1,
        year: today.getFullYear(),
        todayDate: '',
        isAdmin: false,
      }
    },
    computed: {
      ...mapState(['user']),
      ...mapGetters(['isLoggedIn']),
      routeName() {
        return this.$route.name
      },
      navBool() {
        if (this.$route.name === 'Home' 
            || this.$route.name === 'ErrorView' 
            || this.$route.name === 'PageNotFound'){
          return true
        } else {
          return false
        }
      },
      navBack() {
        if (this.$route.name === 'UserDetailView'){
          return true
        } else {
          return false
        }
      },
      navUser() {
        if ( this.$route.name === 'UserDetailView' 
          || this.$route.name === 'VoteCreateView'
          || this.$route.name === 'VoteLocalSelectView'
          || this.$route.name === 'UserSettingView'
          || this.$route.name === 'About'
          || this.$route.name === 'Login'
          ){
          return false
        } else {
          return true
        }
      },
      navSetting() {
        if (this.$route.name === 'UserDetailView' 
            && this.$route.params.userId === this.user.u_email){
              if(this.user.u_isAdmin === 1) {
                return false
              }
          return true
        } else {
          return false
        }
      },
      navNone() {
        if (this.$route.name === 'UserSettingView'){
          return true
        } else {
          return false
        }
      },
    },
    methods: {
      goBack() {
          history.back()
      },
      goInfo() {
        if (this.$route.name !== 'About') {
          this.$router.push({ name: 'About' }).catch(()=>{})
        }
      },
      goHome() {
        if (this.$route.name !== 'Home') {
          this.$router.push({ name: 'Home' }).catch(()=>{})
        }
      },
      goSettings() {
        if (this.$route.name !== 'UserSettingView') {
          this.$router.push({ name: 'UserSettingView' }).catch(()=>{})
        }
      },
      goUserDetail() {
        if(this.isLoggedIn && this.$route.name !== 'UserDetailView') {
            this.$router.push({ name: 'UserDetailView', params: { userId: this.user.u_email } }).catch(()=>{})
        } else {
          this.$router.push({ name: 'Login' })
        }
      },
    },
    created() {
      if (this.date < 10) {
            this.date = '0' + this.date
      }
      if (this.month < 10) {
          this.month = '0' + this.month
      }
      this.todayDate = this.month +'월 ' + this.date + '일'
    }
  }
</script>

<style scoped>

.close-btn{
  padding-top: 20px;
  padding-left: 15px; 
  padding-right: 15px;
}

.go-back:hover, .go-home:hover {
  cursor: pointer;
  color: #D500F9;
}

#nav {
  padding: 0;
  height: 50px;
  background-color: white;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

.main-logo {
  bottom: 0;
}
</style>
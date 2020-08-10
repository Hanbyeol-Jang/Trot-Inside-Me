<template>
  <div>
    <v-app-bar
      fixed
      absolute
      color="white"
      elevate-on-scroll 
      height="50px"
      scroll-target="#scrolling-techniques-7"
    >
      <v-btn v-if="navBool" icon><i class="fas fa-bell fa-lg"></i></v-btn>
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
        <span v-if="routeName === 'UserDetailView'" class="">내 정보</span>
        <span v-if="routeName === 'SingerManageView'" class="">가수 관리</span>
        <span v-if="routeName === 'SingerCreateView'" class="">가수 등록</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn v-if="navBool" icon @click="goUserDetail"><i class="fas fa-user fa-lg"></i></v-btn>
      <v-btn v-else icon @click="goHome"><i class="fas fa-home fa-lg"></i></v-btn>
      <v-btn v-if="navSetting" icon @click="goSettings"><i class="fas fa-cog fa-lg"></i></v-btn>
    </v-app-bar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
var today = new Date()

export default {
    name: 'Header',
    data() {
      return {
        date: today.getDate(),
        month: today.getMonth() + 1,
        year: today.getFullYear(),
        todayDate: '',
        isAdmin: false,
      }
    },
    methods: {
      ...mapActions(['getUser']),
      goBack() {
          history.back()
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
        if(this.isLoggedIn) {
          if (this.user.u_isAdmin === 1) {
            if (this.$route.name !== 'AdminView') {
              this.$router.push({ name: 'AdminView' }).catch(()=>{})
            }
          } else {
            if (this.$route.name !== 'UserDetailView') {
              this.$router.push({ name: 'UserDetailView', params: { userId: 1 } }).catch(()=>{})
            }
          }
        } else {
          if (this.$route.name !== 'Login') {
            this.$router.push({ name: 'Login' }).catch(()=>{})
          }
        }
      },
    },
    computed: {
      ...mapState(['user']),
      ...mapGetters(['isLoggedIn']),
      routeName() {
        return this.$route.name
      },
      navBool() {
        if (this.$route.name === 'Home'
          || this.$route.name === 'CommunityIndexView' || this.$route.name === 'VoteView'
          || this.$route.name === 'SingerSearchView'){
          return true
        } else {
          return false
        }
      },
      navSetting() {
         if (this.$route.name === 'UserDetailView'){
          return true
        } else {
          return false
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
      this.getUser()
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
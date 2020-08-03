<template>
  <div>
    <v-navigation-drawer
      app
      right
      v-model="drawer"
      dark
      src="../../assets/image/rainbow_background.jpg"
      >
      <div class="my-2 ml-3">
        <v-btn text @click="closeDrawer"><i class="fas fa-times mr-2"></i>닫기</v-btn>
      </div>
      <v-list>
        <v-list-item
          v-for="([icon, text, route], i) in items"
          :key="i"
          link
          @click="$router.push({ name: route }).catch(err => {})"
        >
            <v-list-item-icon>
              <v-icon>{{ icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ text }}</v-list-item-title>
            </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      app
      color="white"
      height="50px"
    >
      <v-btn v-if="!navBool" icon @click="goBack"><i class="fas fa-chevron-left fa-lg"></i></v-btn>
      <v-spacer></v-spacer>
      <v-app-bar-nav-icon v-if="navBool" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-btn v-else icon @click="goHome"><i class="fas fa-home fa-lg"></i></v-btn>
      <v-btn v-if="navSetting" icon @click="goSettings"><i class="fas fa-cog fa-lg"></i></v-btn>
      

      <template v-if="navBool" v-slot:extension>
        <v-tabs
          background-color="pink lighten-5"
          centered
          color="basil"
          icons-and-text
          height="60px"
        >
        <v-tabs-slider></v-tabs-slider>
        <v-tab href="#tab-1" @click="goHome">
          홈
          <i class="fas fa-home fa-lg"></i>
        </v-tab>
        <v-tab href="#tab-2" @click="goCommunity">
          수다방
          <i class="fas fa-comment fa-lg"></i>
        </v-tab>
        <v-tab href="#tab-3" @click="goVote">
          투표
          <i class="fas fa-vote-yea fa-lg"></i>
        </v-tab>
        <v-tab href="#tab-4" @click="goSearch">
          검색
          <i class="fas fa-search fa-lg"></i>
        </v-tab>
      </v-tabs>
      </template>
    </v-app-bar>
    
  </div>
</template>

<script>
export default {
    name: 'Header',
    data() {
        return {
            page: window.location.pathname,
            drawer : false,
            userId : '/accounts/1',
            items: [
              ['mdi-account', '로그인', 'Login'],
              ['mdi-account-plus', '회원가입', 'Signup'],
              ['mdi-account-circle', '나의 페이지'],
            ],
        }
    },
    methods: {
        goBack() {
            history.back()
        },
        goHome() {
          if (this.$route.name !== 'Home') {
            this.$router.push({ name: 'Home' }).catch(()=>{})
          }
        },
        goCommunity() {
          if (this.$route.name !== 'CommunityView') {
            this.$router.push({ name: 'CommunityView' }).catch(()=>{})
          }
        },
        goVote() {
          if (this.$route.name !== 'VoteView') {
            this.$router.push({ name: 'VoteView' }).catch(()=>{})
          }
        },
        goSettings() {
          if (this.$route.name !== 'UserSettingView') {
            this.$router.push({ name: 'UserSettingView' }).catch(()=>{})
          }
        },
        goSearch() {
          if (this.$route.name !== 'SingerSearchView') {
            this.$router.push({ name: 'SingerSearchView' }).catch(()=>{})
          }
        },
        goLogin() {
          if (this.$route.name !== 'Login') {
            this.$router.push({ name: 'Login' }).catch(()=>{})
          }
        },
        goSignup() {
          if (this.$route.name !== 'Signup') {
            this.$router.push({ name: 'Signup' }).catch(()=>{})
          }
        },
        addItem(item) {
          const removed = this.items.splice(0, 1)
          this.items.push(
            ...this.more.splice(this.more.indexOf(item), 1)
          )
          this.more.push(...removed)
          this.$nextTick(() => { this.currentItem = 'tab-' + item })
        },
        closeDrawer() {
          this.drawer = false
        },
    },
    components: {

    },
    computed: {
      navBool() {
        if (this.$route.name === 'Home'
          || this.$route.name === 'CommunityView' || this.$route.name === 'VoteView'
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
}
</script>

<style scoped>
@import '../../assets/style/burger-button.css';

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

</style>
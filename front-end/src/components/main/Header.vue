<template>
  <div>
    <v-app-bar
      fixed
      absolute
      color="white"
      elevate-on-scroll 
      height="50px"
    >
      <v-btn v-if="navBool" icon><i class="fas fa-bell fa-lg"></i></v-btn>
      <v-btn v-if="!navBool" icon @click="goBack"><i class="fas fa-chevron-left fa-lg"></i></v-btn>
      <v-spacer></v-spacer>
      <v-toolbar-title
        class="d-flex align-center"
        >
        <img v-if="routeName === 'Home'" src="@/assets/image/trot_logo.png" alt=""
          width="90px" class="main-logo">
        <span v-if="routeName === 'SingerSearchView'" class="">가수 검색</span>
        <span v-if="routeName === 'VoteView'" class="">투표</span>
        <span v-if="routeName === 'CommunityView'" class="">수다방</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn v-if="navBool" icon><i class="fas fa-user fa-lg"></i></v-btn>
      <v-btn v-else icon @click="goHome"><i class="fas fa-home fa-lg"></i></v-btn>
      <v-btn v-if="navSetting" icon @click="goSettings"><i class="fas fa-cog fa-lg"></i></v-btn>
    </v-app-bar>
  </div>
</template>

<script>
export default {
    name: 'Header',
    data() {
        return {

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
        goSettings() {
          if (this.$route.name !== 'UserSettingView') {
            this.$router.push({ name: 'UserSettingView' }).catch(()=>{})
          }
        },
    },
    components: {

    },
    computed: {
      routeName() {
        return this.$route.name
      },
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
<template>
  <v-app id="app">
    <Header/>
    <v-main 
      id="scrolling-techniques-7"
      class="mt-4 mb-8">
      <v-container
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
          <router-view></router-view>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
    <BottomNav/>
    <vue-confirm-dialog class="my-class"></vue-confirm-dialog>
  </v-app>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'

import Header from './components/main/navigation/Header'
import BottomNav from './components/main/navigation/BottomNav'

export default {
  name: 'App',
  components: {
    Header,
    BottomNav,
  },
  data: () => ({
  }),
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn']),
    // bottomNav() {
    //   let routeName = this.$route.name
    //   if (routeName === 'Home' || routeName === 'SingerSearchView'
    //     || routeName === 'VoteView' || routeName === 'CommunityIndexView') {
    //       return true
    //     } else {
    //       return false
    //     }
    // },
  },
  methods: {
    ...mapActions(['getUser']),
  },
  created() {
    if (this.isLoggedIn) {
      this.getUser()
    }
  }
};
</script>

<style>
#app {
  font-family: 'Jua', sans-serif !important;
  -webkit-font-smoothing: antialiased !important;
  -moz-osx-font-smoothing: grayscale !important;
  /* text-align: center; */
  color: #2c3e50;
}
.my-class .vc-text {
  font-size: 200%;
  margin: 0;
}
.my-class .vc-container {
  width: 300px;
  background-color: whitesmoke;
}
.my-class .vc-btn {
  color: whitesmoke;
  background-color: #e91e63;
  font-size: 150%;
}

.my-class .left {
  color: #e91e63;
  background-color: whitesmoke;
  font-size: 150%;
}
</style>
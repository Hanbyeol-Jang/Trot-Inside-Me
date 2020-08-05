<template>
  <v-app id="app">
    <Header/>
    <v-main 
      id="scrolling-techniques-7"
      class="mt-4">
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
    <BottomNav v-if="bottomNav"/>
  </v-app>
</template>

<script>
import Header from './components/main/Header'
import BottomNav from './components/main/BottomNav'

export default {
  name: 'App',

  components: {
    Header,
    BottomNav,
  },
  data: () => ({
    //
  }),
  computed: {
    bottomNav() {
      let routeName = this.$route.name
      if (routeName === 'Home' || routeName === 'SingerSearchView'
        || routeName === 'VoteView' || routeName === 'CommunityView') {
          return true
        } else {
          return false
        }
    }
  },
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
</style>
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
    <vue-confirm-dialog class="my-dialog"></vue-confirm-dialog>
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
    color: #2c3e50;
  }
  .my-dialog .vc-text {
    font-size: 200%;
    margin: 0;
  }
  .my-dialog .vc-container {
    width: 300px;
    background-color: whitesmoke;
  }
  .my-dialog .vc-btn {
    color: whitesmoke;
    background-color: #e91e63;
    font-size: 150%;
  }

  .my-dialog .left {
    color: #e91e63;
    background-color: whitesmoke;
    font-size: 150%;
  }
  .swal2-popup {
    font-family: 'Jua', sans-serif !important;
  }
  .swal2-content{
    font-size: 200% !important;
    margin: 0;
  }
  .swal2-styled.swal2-confirm{
    background-color: #e91e63 !important;
  }
  .fade-enter-active, .fade-leave-active {
      transition: opacity .9sss;
    }
  .fade-enter, .fade-leave-to {
    opacity: 0;
  }
</style>
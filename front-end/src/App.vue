<template>
  <v-app id="app">
    <Header/>
    <v-main class="mt-4">
      <v-container
        v-if="containerBool"
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
      <div v-else><router-view></router-view></div>
    </v-main>
  </v-app>
</template>

<script>
import Header from './components/main/Header'
export default {
  name: 'App',

  components: {
    Header,
  },
  data: () => ({
    //
  }),
  computed: {
      containerBool() {
        if (this.$route.name === 'Login' || this.$route.name === 'Signup'){
          return false
        } else {
          return true
        }
      },
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
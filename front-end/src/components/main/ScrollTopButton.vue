<template>
  <v-btn
    v-show="!hidden"
    color="pink"
    class="button-bottom"
    @click="scrollToTop"
    v-on:scroll.passive="handleScroll"
    dark
    fab
  >
    <v-icon>mdi-chevron-up</v-icon>
  </v-btn>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'ScrollTopButton',
  data() {
    return {
      hidden: false,
    }
  },
  methods: {
    ...mapActions(['scrollToTop']),
    handleScroll: function(e) {
      if ((e.target.scrollHeight-300) <= e.target.scrollTop) {
        alert("Moving!");
      }
    } 
  },
  }
</script>

<style scoped>
.button-bottom {
    position: fixed;
    right: 5vw;
    bottom: 10vh;
}
</style>
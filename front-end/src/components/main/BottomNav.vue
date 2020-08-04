<template>
  <v-bottom-navigation
    :value="activeBtn"
    background-color="pink"
    color="white"
    icons-and-text
    height="60px"
    fixed
    grow
    dark
  >
    <v-btn @click="goHome">
      <span class="bottom-nav">홈</span>
      <i class="fas fa-home fa-lg"></i>
    </v-btn>

    <v-btn @click="goSearch">
      <span class="bottom-nav">가수 검색</span>
      <i class="fas fa-search fa-lg"></i>
    </v-btn>

    <v-btn @click="goVote">
      <span class="bottom-nav">투표</span>
      <i class="fas fa-vote-yea fa-lg"></i>
    </v-btn>

    <v-btn @click="goCommunity">
      <span class="bottom-nav">수다방</span>
      <i class="fas fa-comment fa-lg"></i>
    </v-btn>
  </v-bottom-navigation>
</template>

<script>
  export default {
    data () {
      return {
        activeBtn: 0,
      }
    },
    methods: {
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
        goSearch() {
          if (this.$route.name !== 'SingerSearchView') {
            this.$router.push({ name: 'SingerSearchView' }).catch(()=>{})
          }
        },
        changeTab() {
          if (this.routeName === 'Home') {
            this.activeBtn = 0
          } else if (this.routeName === 'SingerSearchView' ) {
            this.activeBtn = 1
          } else if (this.routeName === 'VoteView' ) {
            this.activeBtn = 2
          } else if (this.routeName === 'CommunityView' ) {
            this.activeBtn = 3
          }
        }
    },
    computed: {
      routeName() {
        return this.$route.name
      }
    },
    mounted() {
      this.changeTab()
    }
  }
</script>

<style scoped>

.bottom-nav {
  font-size: 17px !important;
}

</style>
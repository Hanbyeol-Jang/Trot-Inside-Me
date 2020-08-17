<template>
  <div class="mt-6">
    <v-row>
      <v-col 
        cols="12"
        class="d-flex justify-space-around">
        <v-avatar
          class="ma-3 elevation-4"
          size="120"
          >
          <v-img 
            v-if="userDetail.u_profileImg"
            :src="userDetail.u_profileImg">
          </v-img>
          <v-img
            v-else
            src="@/assets/image/user_default.png">
          </v-img>
        </v-avatar>
        <div class="d-flex align-center">
          <div class="d-flex flex-column">
            <div>{{ userDetail.u_name }}</div>
            <v-btn 
              v-if="user.u_email === userDetail.u_email"
              text
              @click="userLogout(user.u_isAdmin)">
              <i class="fas fa-sign-out-alt mr-1"></i>로그아웃
            </v-btn>
          </div>
        </div>
      </v-col>
    </v-row>
    <v-container>
      <v-row dense>
        <v-col 
          v-for="menu in menuItems"
          :key="menu.id"
          cols="12">
          <v-card
            color="#FCE4EC"
            raised
            @click="goMenuDetail(menu.id)"
          >
            <v-card-title><h4><i :class="menu.icon" class="mr-2"></i>{{ menu.title }}</h4></v-card-title>
          </v-card>
        </v-col>
        <v-col
          v-if="user.u_isAdmin === 1"
          cols="12">
          <v-card
            color="#FCE4EC"
            raised
            @click="goMenuDetail(4)"
          >
            <v-card-title><h4><i class="fas fa-cog mr-2"></i>관리자 페이지</h4></v-card-title>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
export default {
  name: 'UserDetailView',
  data() {
    return {
      userId: this.$route.params.userId,
      menuItems: [
        { id: 1, title: '찜한 가수', icon: 'fas fa-music'},
        { id: 2, title: '찜한 영상', icon: 'far fa-play-circle'},
        { id: 3, title: '찜한 기사', icon: 'fas fa-newspaper'},
      ],
    }
  },
  computed: {
    ...mapState(['user', 'userDetail']),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    ...mapActions(['getUserDetail', 'logout', 'kakaoLogout']),
    goMenuDetail(id) {
      if (id === 1) {
        this.$router.push({ name: 'UserLikeSingerView' })
      } else if (id === 2) {
        this.$router.push({ name: 'UserLikeVideoView' })
      } else if (id === 3) {
        this.$router.push({ name: 'UserLikeArticleView' })
      } else if (id === 4) {
        this.$router.push({ name: 'AdminView' })
      }
    },
    userLogout(isAdmin) {
      if(isAdmin) {
        this.logout()
      } else {
        this.kakaoLogout()
      }
    },
  },
  created() {
    this.getUserDetail(this.userId)
  },
}
</script>

<style>

</style>
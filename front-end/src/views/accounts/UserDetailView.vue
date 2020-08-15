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
            v-if="user.u_profileImg"
            :src="user.u_profileImg">
          </v-img>
          <v-img
            v-else
            src="@/assets/image/user_default.png">
          </v-img>
        </v-avatar>
        <div class="d-flex align-center">
          <div class="d-flex flex-column">
            <div>{{ user.u_name }}</div>
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
      menuItems: [
        { id: 1, title: '내 가수 보기', icon: 'fas fa-music'},
        { id: 2, title: '찜한 영상', icon: 'fas fa-table'},
        { id: 3, title: '찜한 동영상', icon: 'fas fa-info-circle'},
      ],
    }
  },
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    ...mapActions(['getUser']),
    goMenuDetail(id) {
      if (id === 1) {
        console.log('내 가수 보기 해야함! 아직 안함!')
      } else if (id === 2) {
        this.$router.push({ name: 'UserLikeVideoView' })
      } else if (id === 3) {
        this.$router.push({ name: 'UserLikeVideoView' })
      }
    }
  },
  created() {
    if (this.isLoggedIn === 1) {
      this.getUser()
    }
  },
}
</script>

<style>

</style>
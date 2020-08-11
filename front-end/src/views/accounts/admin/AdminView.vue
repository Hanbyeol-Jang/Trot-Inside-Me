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
            src="@/assets/image/admin_default.png">
              <template v-slot:placeholder>
                <v-row
                  class="fill-height ma-0"
                  align="center"
                  justify="center"
                >
                  <v-progress-circular indeterminate color="grey lighten-5"></v-progress-circular>
                </v-row>
              </template>
            </v-img>
        </v-avatar>
        <div class="d-flex align-center">
          <div class="d-flex flex-column">
            <div>{{ user.u_name }}</div>
            <div><i class="fas fa-at mr-1"></i>{{ user.u_email }}</div>
            <v-btn text @click="logout"><i class="fas fa-sign-out-alt mr-1"></i>로그아웃</v-btn>
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
import { mapState, mapActions } from 'vuex'

export default {
  name: 'AdminView',
  data() {
    return {
      menuItems: [
        { id: 1, title: '가수 관리하기', icon: 'fas fa-music'},
        { id: 2, title: '편성표 관리하기', icon: 'fas fa-table'},
        { id: 3, title: 'About 내 안의 트롯', icon: 'fas fa-info-circle'},
      ],
    }
  },
  computed: {
    ...mapState(['user']),
  },
  methods: {
    ...mapActions(['logout', 'getUser']),
    goMenuDetail(id) {
      if (id === 1) {
        this.$router.push({ name: 'SingerManageView' })
      } else if (id === 2) {
        this.$router.push({ name: 'ProgramManageView' })
      }
    }
  },
  created() {
    this.getUser()
  }
}
</script>

<style>

</style>
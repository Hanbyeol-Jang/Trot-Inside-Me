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
            <div><i class="fas fa-at mr-1"></i></div>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
export default {
  name: 'UserDetailView',
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    ...mapActions(['getUser']),
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
<template>
  <div>
      <v-list
      >
        <v-list-item-group v-model="item" color="primary">
          <v-list-item>
            <v-list-item-content @click="kakaoLogout">
              <v-list-item-title class="d-flex justify-space-between">
                로그아웃
                <i class="fas fa-sign-out-alt mr-2"></i>
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content @click="kakaoOff">
              <v-list-item-title class="d-flex justify-space-between">
                서비스 탈퇴
                <i class="fas fa-user-times mr-2"></i>
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'UserSettingView',
    data() {
      return {
        item: 5,
      items: [
        {
          title: '로그아웃',
        },
        {
          title: '탈퇴하기',
        },
      ],
      }
    },
    methods: {
      ...mapActions(['kakaoLogout', 'kakaoOff']),
    },
}
</script>

<style>

</style>
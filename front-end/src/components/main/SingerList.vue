<template>
  <div class="container">
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <v-container fluid class="p-0">
            <v-row v-if="singers.length">
              <v-col
                v-for="singer in singers"
                :key="singer.s_idx"
                cols="4"
              >
              <div class="cursor_test d-flex justify-center">
                  <router-link :to="{ name: 'SingerDetailView', params: { singerId: singer.s_idx }}">
                    <v-avatar size="85"
                      class="text-center elevation-3">
                      <img
                        v-if="singer.s_img"
                        :src="'/images/' + singer.s_img" 
                        alt="Singer Default"
                      >
                      <img
                        v-else
                        src="@/assets/image/user_default.png"
                      >
                    </v-avatar>
                  </router-link>
                
              </div>
              <h3 class="text-center mt-3">{{singer.s_name}}</h3>
              </v-col>
            </v-row>
            <div class="mt-10" v-if="this.$route.name === 'UserLikeSingerView'">
              <div v-if="!singers.length">
                <div class="text-center">
                  <img src="@/assets/image/trot_logo.png" alt="My Trot" width="200px">
                </div>
                <h2 class="text-center mt-4">
                  찜한 가수가 없습니다!
                </h2> 
              </div>
            </div>
            <div class="mt-10" v-else>
              <div v-if="!singers.length">
                <div class="text-center">
                <img src="@/assets/image/trot_logo.png" alt="My Trot" width="200px">
                </div>
                <h2 class="text-center mt-4">
                  찾는 가수가 없습니다!
                </h2>
              </div>
            </div>
          </v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
    name:"SingerList",
    props:{
      singers: Array,
    },
    methods:{

    },
}
</script>

<style>

</style>
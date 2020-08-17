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
                        :src="singerImg"
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
            <p class="text-center" v-if="!singers.length">찾는 가수 없음</p>
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
    computed: {
      singerImg() {
        return '/img/' + this.singers.s_img
      }
    },
    methods:{

    },
}
</script>

<style>

</style>
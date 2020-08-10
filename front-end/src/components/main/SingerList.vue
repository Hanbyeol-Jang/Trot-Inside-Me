<template>
  <div class="container">
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <v-container fluid class="p-0">
            <v-row v-if="singers.length">
              <v-col
                v-for="singer in singers"
                :key="singer.s_name"
                cols="4"
              >
              <div class="cursor_test">
                  <router-link :to="{ name: 'SingerDetailView', params: { singerId: singer.s_idx }}">
                    <v-avatar size="80">
                      <img
                        :src="singer.s_img"
                        alt="John"
                      height="100%"
                      width="100%"
                      >
                    </v-avatar>
                  </router-link>
                <h4 class="text-center">{{singer.s_name}}</h4>
              </div>
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
        singers:Array,
    },
    methods:{

    },
}
</script>

<style>

</style>
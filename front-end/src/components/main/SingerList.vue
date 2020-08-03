<template>
  <div class="container">
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <v-container fluid class="p-0">
            <v-row v-if="Singers.length">
              <v-col
                v-for="singer in Singers"
                :key="singer.s_name"
                cols="4"
              >
              <div class="cursor_test">
                  <router-link :to="{ name: 'SingerDetailView', params: { singerId: singer.s_name, singerImg: singer.s_img }}">
                <v-avatar size="100">
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
            <p class="text-center" v-if="!Singers.length">찾는 가수 없음</p>
          </v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
    name:"SingerList",
    props:{
        Singers:Array,
    },
    methods:{
      goSingerDetail(singer_id){
        this.$router.push({ name: 'SingerDetailView', params: { singerId:singer_id }})
      },
    },
}
</script>

<style>

</style>
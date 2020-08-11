<template>
  <div class="container">
    <v-row>
      <v-col cols="12" sm="6" offset-sm="3">
          <v-container fluid>
            <v-row v-if="Singers.length">
              <v-col
                v-for="singer in Singers"
                :key="singer.name"
                cols="4"
              >
              <div class="mb-3 cursor_test">
                  <!-- :src="`https://randomuser.me/api/portraits/men/${i + 20}.jpg`" -->
                <img
                  :src="singer.img"
                  alt="lorem"
                  class="image rounded-circle"
                  height="100%"
                  width="100%"
                >
                <h4 class="text-center">{{singer.name}}</h4>
              </div>
              </v-col>
            </v-row>
            <p class="text-center" v-if="!Singers.length">관심 가수를 등록 해 주세요.</p>
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
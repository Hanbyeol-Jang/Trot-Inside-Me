<template>
  <div class="vote">
    <div><i class="fas fa-crown mr-2"></i>이번주 순위<i class="fas fa-crown ml-2"></i></div>
    <v-container fluid class="p-0">
      <v-row v-if="singers.length">
        <v-col
          v-for="singer in singers"
          :key="singer.rankk"
          cols="4"
        >
        <div class="d-flex justify-center">
          <v-avatar size="85"
            class="text-center elevation-3">
            <img
              :src="singer.s_img"
            >
          </v-avatar>
        </div>
        <h3 class="text-center mt-1">{{singer.rankk}}</h3>
        <h3 class="text-center">{{singer.s_name}}</h3>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name: 'VoteThisWeek',
    data() {
      return {
        singers: [],
      }
    },
    computed: {
    },
    methods: {
      getThisWeekVote() {
        axios.get(SERVER.URL + SERVER.ROUTES.thisWeekTopThree)
          .then((res) => {
            this.singers = res.data
          })
          .catch((err)=>{ console.error(err) }) 
      }
    },
    created() {
      this.getThisWeekVote()
    }
}
</script>

<style scoped>
.vote {
  color:black;
}
</style>
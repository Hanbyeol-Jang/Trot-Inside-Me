<template>
  <div class="mt-5">
    <div class="text-center">
      <v-avatar
        class="ma-3 elevation-4"
        size="150px">
        <img
          v-if="singer.s_img"
          :src="singer.s_img"
          alt="Singer Default"
        >
        <img
          v-else
          src="@/assets/image/user_default.png"
        >
      </v-avatar>
      <h2 class="mt-4">{{ singer.s_name }}</h2>
    </div>
    <div class="text-center mt-3">
      <v-btn rounded color="pink" dark><h3><i class="fas fa-plus mr-2"></i>내 가수 추가하기</h3></v-btn>
    </div>
    <v-container class="mt-4 text-center">
      <v-row no-gutters>
        <template v-for="menu in menus">
          <v-col :key="menu.id">
            <v-card
              class="pa-2"
              outlined
              tile
              color="white"
              @click="goMenuDetail(menu.id)"
            >
              <VideoIcon v-if="menu.id === 1" />
              <MagazineIcon v-if="menu.id === 2" />
              <CalendarIcon v-if="menu.id === 3" />
              <div class="menu-title">{{ menu.title }}</div>
            </v-card>
          </v-col>
          <v-responsive
            v-if="menu.id === 2"
            :key="`width-${menu.id}`"
            width="100%"
          ></v-responsive>
        </template>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

import VideoIcon from '@/assets/icon/video-icon.svg'
import MagazineIcon from '@/assets/icon/magazine-icon.svg'
import CalendarIcon from '@/assets/icon/calendar-icon.svg'

export default {
  name: 'SingerDetailView',
  data() {
    return {
      s_idx: this.$route.params.singerId,
      singer: {},
      menus: [
        { id: 1, title: '영상 보기'},
        { id: 2, title: '기사 보기'},
        { id: 3, title: '스케줄 보기'},
        { id: 4, title: ''},
      ],
    }
  },
  components: {
    VideoIcon,
    MagazineIcon,
    CalendarIcon,
  },
  methods: {
    getSingerDetail() {
      axios.get(SERVER.URL +`/singer/${this.s_idx}`, { params: { s_idx: this.s_idx } })
            .then(res =>{ this.singer = res.data })
            .catch(err=>{ console.error(err) })
    },
    goMenuDetail(id) {
      if (id === 1) {
        this.$router.push({ name: 'VideoListView', params: { singerId: this.s_idx }})
      } else if (id === 2) {
        this.$router.push({ name: 'ArticleListView', params: { singerId: this.s_idx }})
      } else if (id === 3) {
        this.$router.push({ name: 'SingerScheduleView', params: { singerId: this.s_idx }})
      } 
    }
  },
  created() {
    this.getSingerDetail()
  }
}
</script>

<style scoped>
.menu-title{
  font-size: 25px;
}
</style>
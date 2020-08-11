<template>
  <div class="mt-5">
    <div class="text-center">
      <v-avatar
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
            <v-card-title><h2><i :class="menu.icon" class="mr-2"></i>{{ menu.title }}</h2></v-card-title>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
  name: 'SingerDetailView',
  data() {
    return {
      s_idx: this.$route.params.singerId,
      singer: {},
      menuItems: [ 
        { id: 1, title: '영상 보기', icon: 'fas fa-play-circle'},
        { id: 2, title: '기사 보기', icon: 'fas fa-newspaper'},
        { id: 3, title: '스케줄 보기', icon: 'far fa-calendar-alt'},
      ],
    }
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

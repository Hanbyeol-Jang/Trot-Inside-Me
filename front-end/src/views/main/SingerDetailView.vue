<template>
  <div class="mt-5">
    <div class="text-center">
      <v-avatar
        class="ma-3 elevation-4"
        size="120px">
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
      <div class="mt-4 d-flex justify-center">
        <h2 class="mr-2">{{ singer.s_name }} </h2>
        <v-icon
          color="pink">
          mdi-heart
        </v-icon>
        <div class="subheading ma-2 like-count">{{ followCnt }} 명</div>
        </div>
    </div>
    <v-container>
          <div v-if="isLoggedIn" class="text-center">
            <v-btn v-if="!followBtn" rounded color="pink" dark @click="followSinger(followBtn)">
              <h3><i class="fas fa-plus mr-2"></i>내 가수 추가하기</h3>
            </v-btn>
            <v-btn v-else rounded outlined color="pink" dark @click="followSinger(followBtn)">
              <h3><i class="fas fa-times mr-2"></i>내 가수 취소하기</h3>
            </v-btn>
          </div>
          <div v-else class="text-center" @click="showMsg()">
            <v-btn rounded color="pink" dark>
              <h3><i class="fas fa-plus mr-2"></i>내 가수 추가하기</h3>
            </v-btn>
        </div>
    </v-container>
    <v-container class="mt-2 text-center">
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
              <VoteIcon v-if="menu.id === 4" />
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
import { mapState, mapGetters, mapActions } from 'vuex'
import axios from 'axios'
import SERVER from '@/api/drf'
import VideoIcon from '@/assets/icon/video-icon.svg'
import MagazineIcon from '@/assets/icon/magazine-icon.svg'
import CalendarIcon from '@/assets/icon/calendar-icon.svg'
import VoteIcon from '@/assets/icon/vote-icon.svg'

export default {
  name: 'SingerDetailView',
  data() {
    return {
      s_idx: this.$route.params.singerId,
      menus: [
        { id: 1, title: '영상 보기'},
        { id: 2, title: '기사 보기'},
        { id: 3, title: '스케줄 보기'},
        { id: 4, title: '투표결과 보기'},
      ],
    }
  },
  components: { 
    VideoIcon,
    MagazineIcon,
    CalendarIcon,
    VoteIcon,
  },
  computed: {
    ...mapState(['singer', 'followBtn', 'followCnt']),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    ...mapActions(['getSingerDetail', 'follow']),
    followSinger(isFollow) {
      if (isFollow) {
        this.follow({ s_idx: this.singer.s_idx, f_flag: 1 })
      } else {
        this.follow({ s_idx: this.singer.s_idx, f_flag: 0 })
      }
    },
    goMenuDetail(id) {
      if (id === 1) {
        this.$router.push({ name: 'VideoListView', params: { singerId: this.s_idx }})
      } else if (id === 2) {
        this.$router.push({ name: 'ArticleListView', params: { singerId: this.s_idx }})
      } else if (id === 3) {
        this.$router.push({ name: 'SingerScheduleView', params: { singerId: this.s_idx }})
      } else if (id === 4) {
            axios.get(`${SERVER.URL}/voteOverview/${this.s_idx}`)
            .then((res)=>{
            this.$store.commit('SET_SINGERVOTE',res.data);
            this.$router.push({ name: 'SingerVoteView', params: { singerId: this.s_idx }})
            })
            .catch((err)=>{
                console.error(err)
            }) 
      }     
      },
    showMsg() {
      this.$confirm({
        message: "로그인 해주세요.",
        button: {
          yes: '로그인 하기',
          no: '돌아가기',
        },
        callback: confirm => {
          if (confirm) { this.$router.push({ name: 'Login' }) }
        }
      })
    }
  },
  created() {
    this.getSingerDetail(this.s_idx)
  },
}
</script>

<style scoped>
.menu-title {
  font-size: 25px;
}
.like-count {
  display: inline-block;
}
</style>
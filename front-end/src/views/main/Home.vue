<template>
  <div class="home">
    <div class="text-center mt-4">
      <v-carousel
        height="200px"
        hide-delimiters
        cycle
        >
        <v-carousel-item>
          <VoteThisWeek />
        </v-carousel-item>
        <v-carousel-item>
          <VoteLastWeek />
        </v-carousel-item>
        <CarouselItem 
          v-for="(item,i) in items"
          :key="i"
          :imgSrc="item.src"/>
      </v-carousel>
      <v-container class="mt-4">
        <v-row no-gutters>
          <template v-for="menu in menus">
            <v-col :key="menu.id">
              <v-card
                class="pa-2"
                outlined
                tile
                color="white"
                @click="goMenu(menu.id)"
              >
                <VideoIcon v-if="menu.id === 1" />
                <MagazineIcon v-if="menu.id === 2" />
                <TelevisionIcon v-if="menu.id === 3" />
                <MicrophoneIcon v-if="menu.id === 4" />
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
  </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
// <div>Icons made by <a href="https://www.flaticon.com/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
import VideoIcon from '@/assets/icon/video-icon.svg'
import MagazineIcon from '@/assets/icon/magazine-icon.svg'
import TelevisionIcon from '@/assets/icon/television-icon.svg'
import MicrophoneIcon from '@/assets/icon/microphone-icon.svg'
import CarouselItem from '@/components/main/CarouselItem'
import VoteThisWeek from '@/components/vote/VoteThisWeek'
import VoteLastWeek from '@/components/vote/VoteLastWeek'

export default {
  name: 'Home',
  components: {
    VideoIcon,
    MagazineIcon,
    TelevisionIcon,
    MicrophoneIcon,
    CarouselItem,
    VoteThisWeek,
    VoteLastWeek,
  },
  data() {
    return {
      menus: [
        { id: 1, title: '영상 보기'},
        { id: 2, title: '기사 보기'},
        { id: 3, title: '편성표 보기'},
        { id: 4, title: '내가수 보기'},
      ],
      items: [
          {
            src: 'https://www.newsinside.kr/news/photo/202001/1057643_742781_1913.jpg',
          },
          {
            src: 'https://cwcontent.asiae.co.kr/asiaresize/215/2020031311291420272_1584066554.jpg',
          },
          {
            src: 'https://t1.daumcdn.net/cfile/tistory/993095345F05621F27',
          },
        ],
    }
  },
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn']),
  },
  methods: {
    goMenu(m_idx) {
      if (m_idx === 1) {
        this.$router.push({ name: 'VideoListView', params: { singerId: 0 }})
      } else if (m_idx === 2) {
        this.$router.push({ name: 'ArticleListView', params: { singerId: 0 }})
      } else if (m_idx === 3) {
        this.$router.push({ name: 'TvtableDetailView' })
      } else if (m_idx === 4) {
        if (this.isLoggedIn) {
          this.$router.push({ name: 'UserLikeSingerView', params: { userId: this.user.u_email }})
        } else {
          this.$confirm({
          message: "로그인 해주세요!",
          button: {
              yes: '로그인 하기',
              no: '돌아가기',
            },
            callback: confirm => {
              if (confirm) {
                this.$router.push({ name: 'Login' })
              }
            }
          })
        }
      }
    },
  },
}
</script>

<style scoped>
  .menu-title{
    font-size: 25px;
  }
</style>
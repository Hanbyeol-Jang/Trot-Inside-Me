<template>
  <div class="home">
    <div class="text-center">
      <v-avatar
        size="150px">
        <img
          :src="singer.singerImage"
          alt="John"
        >
      </v-avatar>
      <h3 class="mt-4">{{ singer.singerName }}</h3>
    </div>
    <div class="text-center mt-3">
      <v-btn rounded color="pink" dark>내 가수 추가하기</v-btn>
    </div>
    <div class="mt-5 mb-6">
      <v-tabs
        color="pink"
        class="d-flex justify-center">
        <v-tab @click="showVideo"><h2><i class="fas fa-video mr-2"></i>동영상</h2></v-tab>
        <v-tab @click="showArticle"><h2><i class="fas fa-newspaper mr-2"></i>뉴스 기사</h2></v-tab>
      </v-tabs>
    </div>
    <div class="mt-4">
      <VideoFeed v-if="videoTab" :singer="singer"/>
      <ArticleFeed v-if="articleTab" :singer="singer"/>
    </div>
  </div>
</template>

<script>
import VideoFeed from '../../components/main/singer/VideoFeed'
import ArticleFeed from '../../components/main/singer/ArticleFeed'

export default {
  name: 'SingerDetailView',
  components: {
    VideoFeed,
    ArticleFeed,
  },
  data() {
    return {
      videoTab: true,
      articleTab: false,
      singer: {
        singerId: this.$route.params.singerId,
        singerName: this.$route.params.singerId,
        singerImage: this.$route.params.singerImg,
        singerFollowers: '',
      },
      videos: [],
      page: 1,
      videosNum: null,
    }
  },
  methods: {
    showVideo() {
      this.videoTab = true,
      this.articleTab = false
    },
    showArticle() {
      this.videoTab = false,
      this.articleTab = true
    },
    // fetchSingerVideoData() {
    //   const options = { params: {_page: this.page++}}
    //   axios.get(`${SERVER.URL}/search/naver/${this.singerName}`, options)
    //     .then(res => {
    //       // this.videos = res.data
    //       this.videosNum = res.data.length
    //       console.log(res.data.length)
    //       setTimeout(() => {
    //         this.videos.push(...res.data)
    //       }, 1000);
    //     })
    //     .catch(err => console.log(err));
    // },
    // infiniteHandler($state){
    //   if (parseInt(this.videosNum / 5) >= this.page){
    //     const options = {params: {_page: this.page++}}
    //     axios.get(`${SERVER.URL}/select/naver/${this.singerName}`, options)
    //       .then(res => {
    //         setTimeout(() => {
    //           this.videos.push(...res.data)
    //           $state.loaded()
    //         }, 1000);
    //       })
    //       .catch(err => {
    //         console.log(err.response.data)
    //       })
    //   } else{
    //     $state.complete()
    //   }
    // },

  },
  created() {
    // this.fetchSingerVideoData()
  },
}
</script>

<template>
  <v-container>
    <div v-for="video in videos" :key="video.b_idx" class="mb-5">
      <VideoFeedItem :video="video"/>
    </div>
    <div class="mt-10" v-if="isWaiting">
      <div class="d-flex justify-center">
        <circle8></circle8>
      </div>
      <h2 class="text-center mt-4">
        잠시만 기다려 주세요!
      </h2> 
    </div>
    <div class="mt-10" v-else>
      <div v-if="!isData">
        <div class="text-center">
          <img src="@/assets/image/trot_logo.png" alt="My Trot" width="200px">
        </div>
        <h2 class="text-center mt-4">
          찜한 영상이 없습니다!
        </h2>
      </div>
    </div>
    <transition name="fade">
      <ScrollTopButton v-if="scrolled"/>
    </transition> 
    <infinite-loading 
      v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots">
    </infinite-loading>
  </v-container>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import VideoFeedItem from '@/components/main/VideoFeedItem'
import ScrollTopButton from '@/components/main/ScrollTopButton'

export default {
    name: 'LikeVideoFeed',
    data() {
      return {
        videos: [],
        page: 1,
        videoCnt: 0,
        scrolled: false,
        isData: false,
        isWaiting :true,
      }
    },
    components: {
      VideoFeedItem,
      InfiniteLoading,
      Circle8,
      ScrollTopButton,
    },
    props: {
      userId: Number,
    },
    methods: {
      fetchVideoData() {
        const options = { params: { page: this.page++ } }
        axios.get(SERVER.URL + SERVER.ROUTES.followVideoList + this.userId, options)
          .then((res) => {
            if (res.data.length) {
              this.videoCnt = res.data[0].b_cnt
              res.data.forEach(item => {
                const parser = new DOMParser()
                const doc = parser.parseFromString(item.b_title, 'text/html')
                item.b_title = doc.body.innerText
                if (item.b_title.length > 35) {
                  item.b_title = item.b_title.slice(0, 35) + '...'
                }
              })
              setTimeout(() => { 
                this.videos.push(...res.data)
                this.isWaiting = false 
                this.isData = true }, 500)
            } else {
              this.isWaiting = false
            }
          })
            .catch(err => {
              if(err.message==="Request failed with status code 404"){
                this.$router.push({name:"PageNotFound"})
              }})      },
      // Pagination
      infiniteHandler($state){
        if (parseInt(this.videoCnt / 5) + 1 >= this.page){
          const options = { params: { page: this.page++ } }
          axios.get(SERVER.URL + SERVER.ROUTES.followVideoList + this.userId, options)
            .then(res => {
              setTimeout(() => {
                this.videos.push(...res.data)
                res.data.forEach(item => {
                  const parser = new DOMParser()
                  const doc = parser.parseFromString(item.b_title, 'text/html')
                  item.b_title = doc.body.innerText
                  if (item.b_title.length > 35) {
                    item.b_title = item.b_title.slice(0, 35) + '...'
                  }
                })
                $state.loaded()
              }, 500);
            })
            .catch(err => {
              if(err.message==="Request failed with status code 404"){
                this.$router.push({name:"PageNotFound"})
              }})
        } else{
          $state.complete()
        }
      },
      detectWindowScrollY() {
        this.scrolled = window.scrollY > 0
      }
    },
    created() {
      this.fetchVideoData()
    },
    mounted() {
      window.addEventListener('scroll', this.detectWindowScrollY)
      },
    beforeDestory() {
      window.removeEventListener('scroll', this.detectWindowScrollY)
    }
}
</script>

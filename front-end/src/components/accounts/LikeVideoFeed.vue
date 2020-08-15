<template>
  <v-container>
    <div v-for="video in videos" :key="video.b_idx"  class="mb-5">
      <VideoFeedItem :video="video"/>
    </div>
    <div class="mt-10" v-if="!videos.length">
      <div class="d-flex justify-center">
        <circle8></circle8>
      </div>
      <h2 class="text-center mt-4">
        잠시만 기다려 주세요!
      </h2> 
    </div>
    <ScrollTopButton /> 
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
            this.videoCnt = res.data[0].b_cnt
            setTimeout(() => { this.videos.push(...res.data) }, 500) 
          })
          .catch(err => console.log(err))
      },
      // Pagination
      infiniteHandler($state){
        if (parseInt(this.videoCnt / 5) + 1 >= this.page){
          const options = { params: { page: this.page++ } }
          axios.get(SERVER.URL + SERVER.ROUTES.followVideoList + this.userId, options)
            .then(res => {
              setTimeout(() => {
                this.videos.push(...res.data)
                $state.loaded()
              }, 500);
            })
            .catch(err => console.log(err))
        } else{
          $state.complete()
        }
      },
    },
    created() {
      this.fetchVideoData()
    },
}
</script>

<template>
  <v-container>
    <div v-for="video in videos" :key="videos.indexOf(video)"  class="mb-5">
      <VideoFeedItem :video="video" :videoId="videos.indexOf(video)"/>
    </div>
    <p class="mt-2" v-if="!videos.length">결과가 없습니다.</p>
    <!-- <infinite-loading v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading> -->

  </v-container>
</template>

<script>
import axios from 'axios'
// import InfiniteLoading from 'vue-infinite-loading'

import SERVER from '@/api/drf'
import VideoFeedItem from './VideoFeedItem'

export default {
    name: 'VideoFeed',
    components: {
      VideoFeedItem,
      // InfiniteLoading,
    },
    data() {
      return {
        videos: [],
        page: 1,
        videosNum: null,
      }
    },
    props: {
      singer: Object,
    },
    methods: {
      fetchSingerVideoData() {
      const options = { params: {_page: this.page++}}
      axios.get(`${SERVER.URL}/select/naver/${this.singer.singerName}`, options)
        .then(res => {
          this.videosNum = res.data.length
          console.log(res.data.length)
          this.videos = res.data.slice(0,30)
          // setTimeout(() => {
          //   this.videos.push(...res.data)
          // }, 1000);
        })
        .catch(err => console.log(err));
      },
      // infiniteHandler($state){
      //   if (parseInt(this.videosNum / 5) >= this.page){
      //     const options = {params: {_page: this.page++}}
      //     axios.get(`${SERVER.URL}/select/naver/${this.singer.singerName}`, options)
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
      this.fetchSingerVideoData()
    },
}
</script>

<style>

</style>

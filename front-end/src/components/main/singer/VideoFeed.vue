<template>
  <v-container>
    <div v-for="video in videos" :key="video.b_idx"  class="mb-5">
      <VideoFeedItem :video="video"/>
    </div>
    <p class="mt-2" v-if="!videos.length">결과가 없습니다.</p>
    <infinite-loading v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>

  </v-container>
</template>

<script>
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'

import SERVER from '@/api/drf'
import VideoFeedItem from './VideoFeedItem'

export default {
    name: 'VideoFeed',
    components: {
      VideoFeedItem,
      InfiniteLoading,
    },
    data() {
      return {
        videos: [],
        page: 1,
        videosNum: 80,
      }
    },
    props: {
      singerId: Number,
    },
    methods: {
      fetchSingerVideoData() {
        const options = { params: { page: this.page++ }}
        axios.get(SERVER.URL + `/singer/${this.singerId}/videos`, options)
          .then(res => {
            // this.videosNum = res.data.length
            setTimeout(() => {
              this.videos.push(...res.data)
            }, 1000);
          })
          .catch(err => console.log(err))
      },
      infiniteHandler($state){
        if (parseInt(this.videosNum / 5) + 1 >= this.page){
          const options = {params: { page: this.page++ }}
          axios.get(SERVER.URL + `/singer/${this.singerId}/videos`, options)
            .then(res => {
              setTimeout(() => {
                this.videos.push(...res.data)
                $state.loaded()
              }, 1000);
            })
            .catch(err => console.log(err))
        } else{
          $state.complete()
        }
      },
    },
    created() {
      this.fetchSingerVideoData()
    },
}
</script>

<style>

</style>

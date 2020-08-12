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
    <infinite-loading v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'

import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import SERVER from '@/api/drf'
import VideoFeedItem from './VideoFeedItem'
import ScrollTopButton from './ScrollTopButton'

export default {
    name: 'VideoFeed',
    data() {
      return {
        videos: [],
        page: 1,
        pageAll: 1,
        mediaType: 1,
      }
    },
    components: {
      VideoFeedItem,
      InfiniteLoading,
      Circle8,
      ScrollTopButton,
    },
    props: {
      singerId: Number,
    },
    computed: {
      ...mapState(['contentsCount']),
      routeSingerId() {
        return parseInt(this.$route.params.singerId)
      }
    },
    methods: {
      ...mapActions(['getContentsCount']),
      fetchVideoData() {
        if (this.routeSingerId) {
          const options = { params: { page: this.page++ }}
          axios.get(SERVER.URL + `/singer/${this.singerId}/videos`, options)
            .then(res => {
              setTimeout(() => {
                this.videos.push(...res.data)
              }, 1000);
            })
            .catch(err => console.log(err))
        } else {
          console.log('all')
          const options = { params: { page: this.page++ }}
          axios.get(SERVER.URL + `/${this.mediaType}/good`, options)
            .then(res => {
              setTimeout(() => {
                this.videos.push(...res.data)
              }, 500);
            })
            .catch(err => console.log(err))
        }
      },
      infiniteHandler($state){
        if (this.routeSingerId) {
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
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
        } else {
          console.log('all')
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
            const options = {params: { page: this.page++ }}
            axios.get(SERVER.URL + `/${this.mediaType}/good`, options)
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
        }
      },
    },
    created() {
      const info = { mediaType: 1, singerId: this.$route.params.singerId }
      this.getContentsCount(info)
      this.fetchVideoData()
    },
}
</script>

<style scoped>

</style>

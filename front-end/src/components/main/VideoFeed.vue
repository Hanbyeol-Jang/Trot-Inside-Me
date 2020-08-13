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
<<<<<<< HEAD
      </h2> 
    </div>
    <ScrollTopButton /> 
    <infinite-loading 
      v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots">
    </infinite-loading>
=======
      </h2>
    </div>
    <infinite-loading v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>

>>>>>>> BE-Develop
  </v-container>
</template>

<script>
<<<<<<< HEAD
import { mapState } from 'vuex'

import axios from 'axios'
import SERVER from '@/api/drf'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import VideoFeedItem from './VideoFeedItem'
import ScrollTopButton from './ScrollTopButton'
=======
import { mapState, mapActions } from 'vuex'

import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import SERVER from '@/api/drf'
import VideoFeedItem from './VideoFeedItem'
>>>>>>> BE-Develop

export default {
    name: 'VideoFeed',
    data() {
      return {
        videos: [],
        page: 1,
<<<<<<< HEAD
        mediaType: 1,
        articleCnt: 0,
=======
        pageAll: 1,
        mediaType: 1,
>>>>>>> BE-Develop
      }
    },
    components: {
      VideoFeedItem,
      InfiniteLoading,
      Circle8,
<<<<<<< HEAD
      ScrollTopButton,
=======
>>>>>>> BE-Develop
    },
    props: {
      singerId: Number,
    },
    computed: {
<<<<<<< HEAD
      ...mapState(['authToken']),
=======
      ...mapState(['contentsCount']),
>>>>>>> BE-Develop
      routeSingerId() {
        return parseInt(this.$route.params.singerId)
      }
    },
    methods: {
<<<<<<< HEAD
      fetchVideoData() {
        if (this.routeSingerId) {
          // singer
          const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
          }
          axios.get(SERVER.URL + SERVER.ROUTES.singerVideoList + this.singerId, options)
            .then((res) => {
              this.videoCnt = res.data[0].b_cnt
              setTimeout(() => { this.videos.push(...res.data) }, 500) 
            })
            .catch(err => console.log(err))
        } else {
          // all
          const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
          }
          axios.get(SERVER.URL + SERVER.ROUTES.mainList + this.mediaType, options)
            .then((res) => {
              this.videoCnt = res.data[0].b_cnt
              setTimeout(() => { this.videos.push(...res.data) }, 500) 
=======
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
>>>>>>> BE-Develop
            })
            .catch(err => console.log(err))
        }
      },
<<<<<<< HEAD
      // Pagination
      infiniteHandler($state){
        if (this.routeSingerId) {
          // singer
          if (parseInt(this.videoCnt / 5) + 1 >= this.page){
            const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
            }
            axios.get(SERVER.URL + SERVER.ROUTES.singerVideoList + this.singerId, options)
=======
      infiniteHandler($state){
        if (this.routeSingerId) {
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
            const options = {params: { page: this.page++ }}
            axios.get(SERVER.URL + `/singer/${this.singerId}/videos`, options)
>>>>>>> BE-Develop
              .then(res => {
                setTimeout(() => {
                  this.videos.push(...res.data)
                  $state.loaded()
<<<<<<< HEAD
                }, 500);
=======
                }, 1000);
>>>>>>> BE-Develop
              })
              .catch(err => console.log(err))
          } else{
            $state.complete()
          }
        } else {
<<<<<<< HEAD
          // all
          if (parseInt(this.videoCnt / 5) + 1 >= this.page){
            const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
            }
            axios.get(SERVER.URL + SERVER.ROUTES.mainList + this.mediaType, options)
=======
          console.log('all')
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
            const options = {params: { page: this.page++ }}
            axios.get(SERVER.URL + `/${this.mediaType}/good`, options)
>>>>>>> BE-Develop
              .then(res => {
                setTimeout(() => {
                  this.videos.push(...res.data)
                  $state.loaded()
<<<<<<< HEAD
                }, 500);
=======
                }, 1000);
>>>>>>> BE-Develop
              })
              .catch(err => console.log(err))
          } else{
            $state.complete()
          }
        }
      },
    },
    created() {
<<<<<<< HEAD
=======
      const info = { mediaType: 1, singerId: this.$route.params.singerId }
      this.getContentsCount(info)
>>>>>>> BE-Develop
      this.fetchVideoData()
    },
}
</script>
<<<<<<< HEAD
=======

<style scoped>

</style>
>>>>>>> BE-Develop

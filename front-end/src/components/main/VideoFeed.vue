<template>
  <div>
    <VideoFeedSearch v-show="!singerId" @search-video="fetchVideoData2" @video="fetchVideoData"/>
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
      <transition name="fade">
        <ScrollTopButton v-if="scrolled"/>
      </transition> 
      <infinite-loading 
        v-if="videos.length" @infinite="infiniteHandler" spinner="waveDots">
      </infinite-loading>
    </v-container>
  </div>
</template>

<script>
import VideoFeedSearch from '@/components/main/VideoFeedSearch'

import { mapState } from 'vuex'

import axios from 'axios'
import SERVER from '@/api/drf'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import VideoFeedItem from './VideoFeedItem'
import ScrollTopButton from './ScrollTopButton'

export default {
    name: 'VideoFeed',
    data() {
      return {
        videos: [],
        page: 1,
        mediaType: 1,
        videoCnt: 0,
        searchFlag:false,
        keyword:'',
        scrolled: false,
      }
    },
    components: {
      VideoFeedItem,
      InfiniteLoading,
      Circle8,
      ScrollTopButton,
      VideoFeedSearch
    },
    props: {
      singerId: Number,
    },
    computed: {
      ...mapState(['authToken']),
      routeSingerId() {
        return parseInt(this.$route.params.singerId)
      }
    },
    methods: {
      fetchVideoData() {
        this.searchFlag=false
        this.videos=[]
        this.page = 1
        if (this.routeSingerId) {
          // singer
          const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
          }
          console.log(this.page)
          axios.get(SERVER.URL + SERVER.ROUTES.singerVideoList + this.singerId, options)
            .then((res) => {
              this.videoCnt = res.data[0].b_cnt
              res.data.forEach(item => {
                const parser = new DOMParser()
                const doc = parser.parseFromString(item.b_title, 'text/html')
                item.b_title = doc.body.innerText
                if (item.b_title.length > 40) {
                  item.b_title = item.b_title.slice(0, 40) + '...'
                }
              })
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
              res.data.forEach(item => {
                const parser = new DOMParser()
                const doc = parser.parseFromString(item.b_title, 'text/html')
                item.b_title = doc.body.innerText
                if (item.b_title.length > 35) {
                  item.b_title = item.b_title.slice(0, 35) + '...'
                }
              })
              setTimeout(() => { this.videos.push(...res.data) }, 500) 
            })
            .catch(err => console.log(err))
        }
      },

      fetchVideoData2(keyword) {
        this.page = 1
        this.searchFlag=true
        this.videos=[]
        this.keyword=keyword
        const options = {
            // headers:{ token: this.authToken },
            params: { word:keyword,page: this.page++ }
        }
        axios.get(SERVER.URL + "/board/videolist/search", options)
          .then((res) => {
            this.videoCnt = res.data[0].b_cnt
            setTimeout(() => { this.videos.push(...res.data) }, 500) 
          })
          .catch(err => console.log(err))
      },
      // Pagination
      infiniteHandler($state){
        if(!this.searchFlag){
          if (this.routeSingerId) {
            // singer
            if (parseInt(this.videoCnt / 5) + 1 >= this.page){
              const options = {
                headers:{ token: this.authToken },
                params: { page: this.page++ }
              }
              axios.get(SERVER.URL + SERVER.ROUTES.singerVideoList + this.singerId, options)
                .then(res => {
                  res.data.forEach(item => {
                    const parser = new DOMParser()
                    const doc = parser.parseFromString(item.b_title, 'text/html')
                    item.b_title = doc.body.innerText
                    if (item.b_title.length > 40) {
                      item.b_title = item.b_title.slice(0, 40) + '...'
                    }
                  })
                  setTimeout(() => {
                    this.videos.push(...res.data)
                    $state.loaded()
                  }, 500);
                })
                .catch(err => console.log(err))
            } else{
              $state.complete()
            }
          } else {
            // all
            if (parseInt(this.videoCnt / 5) + 1 >= this.page){
              const options = {
                headers:{ token: this.authToken },
                params: { page: this.page++ }
              }
              axios.get(SERVER.URL + SERVER.ROUTES.mainList + this.mediaType, options)
                .then(res => {
                  res.data.forEach(item => {
                    const parser = new DOMParser()
                    const doc = parser.parseFromString(item.b_title, 'text/html')
                    item.b_title = doc.body.innerText
                    if (item.b_title.length > 40) {
                      item.b_title = item.b_title.slice(0, 40) + '...'
                    }
                  })
                  setTimeout(() => {
                    this.videos.push(...res.data)
                    $state.loaded()
                  }, 500);
                })
                .catch(err => console.log(err))
            } else{
              $state.complete()
            }
          }
        }else{
            if (parseInt(this.videoCnt / 5) + 1 >= this.page){
              const options = {
                // headers:{ token: this.authToken },
                params: { word:this.keyword,page: this.page++ }
              }
              axios.get(SERVER.URL + "/board/videolist/search", options)
                .then(res => {
                  res.data.forEach(item => {
                    const parser = new DOMParser()
                    const doc = parser.parseFromString(item.b_title, 'text/html')
                    item.b_title = doc.body.innerText
                    if (item.b_title.length > 40) {
                      item.b_title = item.b_title.slice(0, 40) + '...'
                    }
                  })
                  setTimeout(() => {
                    this.videos.push(...res.data)
                    $state.loaded()
                  }, 500);
                })
                .catch(err => console.log(err))
            } else{
              $state.complete()
            }
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

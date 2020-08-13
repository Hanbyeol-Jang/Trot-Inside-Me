<template>
  <v-container> 
        <v-row dense>
            <v-col cols="12">
              <div v-for="article in articles" :key="article.b_idx">
                  <ArticleFeedItem :article="article"/>
              </div>
              <div class="mt-10" v-if="!articles.length">
                <div class="d-flex justify-center">
                  <circle8></circle8>
                </div>
                <h2 class="text-center mt-4">
                  잠시만 기다려 주세요!
                </h2>
              </div>
            </v-col>
<<<<<<< HEAD
            <ScrollTopButton />
=======
>>>>>>> BE-Develop
            <v-col cols="12">
              <infinite-loading v-if="articles.length" 
                @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
            </v-col>
      </v-row>
  </v-container>
</template>

<script>
<<<<<<< HEAD
import { mapState } from 'vuex'
=======
import { mapState, mapActions } from 'vuex'
>>>>>>> BE-Develop

import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import SERVER from '@/api/drf'
import ArticleFeedItem from './ArticleFeedItem'
<<<<<<< HEAD
import ScrollTopButton from './ScrollTopButton'
=======
>>>>>>> BE-Develop

export default {
    name: 'ArticleFeed',
    data() {
        return {
            articles: [],
            page: 1,
<<<<<<< HEAD
            mediaType: 2,
            videoCnt: 0,
=======
            pageAll: 1,
            mediaType: 2,
>>>>>>> BE-Develop
        }
    },
    components: {
        ArticleFeedItem,
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
      fetchArticleData() {
        if (this.routeSingerId) {
          // singer
          const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
          }
          axios.get(SERVER.URL + SERVER.ROUTES.singerArticleList + this.singerId, options)
            .then((res) => {
              this.articleCnt = res.data[0].b_cnt
              setTimeout(() => { this.articles.push(...res.data) }, 500) 
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
              this.articleCnt = res.data[0].b_cnt
              setTimeout(() => { this.articles.push(...res.data) }, 500) 
            })
            .catch(err => console.log(err))
        }
      }, 
      infiniteHandler($state){
        if (this.routeSingerId) {
          // singer
          if (parseInt(this.articleCnt / 5) + 1 >= this.page){
            const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
            }
            axios.get(SERVER.URL + SERVER.ROUTES.singerArticleList + this.singerId, options)
=======
      ...mapActions(['getContentsCount']),
      fetchArticleData() {
        if (this.routeSingerId) {
          const options = { params: { page: this.page++ }}
          axios.get(SERVER.URL + `/singer/${this.singerId}/articles`, options)
            .then(res => {
              setTimeout(() => {
                this.articles.push(...res.data)
              }, 1000);
            })
            .catch(err => console.log(err))
        } else {
          console.log(this.routeSingerId)
          const options = { params: { page: this.page++ }}
          axios.get(SERVER.URL + `/${this.mediaType}/good`, options)
            .then(res => {
              setTimeout(() => {
                this.articles.push(...res.data)
              }, 500);
            })
            .catch(err => console.log(err))
        }
      },
      infiniteHandler($state){
        if (this.routeSingerId) {
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
            const options = {params: { page: this.page++ }}
            axios.get(SERVER.URL + `/singer/${this.singerId}/articles`, options)
>>>>>>> BE-Develop
              .then(res => {
                setTimeout(() => {
                  this.articles.push(...res.data)
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
          if (parseInt(this.articleCnt / 5) + 1 >= this.page){
            const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
            }
            axios.get(SERVER.URL + SERVER.ROUTES.mainList + this.mediaType, options)
=======
          console.log('all', this.routeSingerId)
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
            const options = {params: { page: this.page++ }}
            axios.get(SERVER.URL + `/${this.mediaType}/good`, options)
>>>>>>> BE-Develop
              .then(res => {
                setTimeout(() => {
                  this.articles.push(...res.data)
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
        const info = { mediaType: 2, singerId: this.$route.params.singerId }
        this.getContentsCount(info)
        console.log('check', this.routeSingerId)
>>>>>>> BE-Develop
        this.fetchArticleData()
    },

}
</script>

<style>

</style>
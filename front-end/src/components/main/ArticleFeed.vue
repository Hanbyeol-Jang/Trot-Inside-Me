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
            <ScrollTopButton />
            <v-col cols="12">
              <infinite-loading v-if="articles.length" 
                @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
            </v-col>
      </v-row>
  </v-container>
</template>

<script>
import { mapState } from 'vuex'

import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import SERVER from '@/api/drf'
import ArticleFeedItem from './ArticleFeedItem'
import ScrollTopButton from './ScrollTopButton'

export default {
    name: 'ArticleFeed',
    data() {
        return {
            articles: [],
            page: 1,
            mediaType: 2,
            videoCnt: 0,
        }
    },
    components: {
        ArticleFeedItem,
        InfiniteLoading,
        Circle8,
        ScrollTopButton,
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
              .then(res => {
                setTimeout(() => {
                  this.articles.push(...res.data)
                  $state.loaded()
                }, 500);
              })
              .catch(err => console.log(err))
          } else{
            $state.complete()
          }
        } else {
          // all
          if (parseInt(this.articleCnt / 5) + 1 >= this.page){
            const options = {
              headers:{ token: this.authToken },
              params: { page: this.page++ }
            }
            axios.get(SERVER.URL + SERVER.ROUTES.mainList + this.mediaType, options)
              .then(res => {
                setTimeout(() => {
                  this.articles.push(...res.data)
                  $state.loaded()
                }, 500);
              })
              .catch(err => console.log(err))
          } else{
            $state.complete()
          }
        }
      },
    },
    created() {
        this.fetchArticleData()
    },

}
</script>

<style>

</style>
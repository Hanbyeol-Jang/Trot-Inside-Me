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
import { mapState, mapActions } from 'vuex'

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
            pageAll: 1,
            mediaType: 2,
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
      ...mapState(['contentsCount']),
      routeSingerId() {
        return parseInt(this.$route.params.singerId)
      }
    },
    methods: {
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
              .then(res => {
                setTimeout(() => {
                  this.articles.push(...res.data)
                  $state.loaded()
                }, 1000);
              })
              .catch(err => console.log(err))
          } else{
            $state.complete()
          }
        } else {
          console.log('all', this.routeSingerId)
          if (parseInt(this.contentsCount / 5) + 1 >= this.page){
            const options = {params: { page: this.page++ }}
            axios.get(SERVER.URL + `/${this.mediaType}/good`, options)
              .then(res => {
                setTimeout(() => {
                  this.articles.push(...res.data)
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
        const info = { mediaType: 2, singerId: this.$route.params.singerId }
        this.getContentsCount(info)
        console.log('check', this.routeSingerId)
        this.fetchArticleData()
    },

}
</script>

<style>

</style>
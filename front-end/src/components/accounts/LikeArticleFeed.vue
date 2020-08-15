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
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading'
import { Circle8 } from 'vue-loading-spinner'

import SERVER from '@/api/drf'
import ArticleFeedItem from '@/components/main/ArticleFeedItem'
import ScrollTopButton from '@/components/main/ScrollTopButton'

export default {
  name: 'LikeArticleFeed',
  data() {
    return {
      articles: [],
      page: 1,
      articleCnt: 0,
    }
  },
  components: {
      ArticleFeedItem,
      InfiniteLoading,
      Circle8,
      ScrollTopButton,
  },
  props: {
    userId: Number,
  },
  methods: {
    fetchArticleData() {
      const options = { params: { page: this.page++ } }
      axios.get(SERVER.URL + SERVER.ROUTES.followArticleList + this.userId, options)
        .then((res) => {
          this.articleCnt = res.data[0].b_cnt
          setTimeout(() => { this.articles.push(...res.data) }, 500) 
        })
        .catch(err => console.log(err))
    },
    // Pagination
    infiniteHandler($state){
      if (parseInt(this.articleCnt / 5) + 1 >= this.page){
        const options = { params: { page: this.page++ } }
        axios.get(SERVER.URL + SERVER.ROUTES.followArticleList + this.userId, options)
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
    },
  },
  created() {
    this.fetchArticleData()
  },
}
</script>

<style>

</style>
<template>
  <v-container> 
    <v-row dense>
      <v-col cols="12">
        <div v-for="article in articles" :key="article.b_idx">
            <ArticleFeedItem :article="article"/>
        </div>
        <div class="mt-10" v-if="isWaiting">
          <div class="d-flex justify-center">
            <circle8></circle8>
          </div>
          <h2 class="text-center mt-4">
            잠시만 기다려 주세요!
          </h2> 
        </div>
        <div class="mt-10" v-else>
          <div v-if="!isData">
            <div class="text-center">
            <img src="@/assets/image/trot_logo.png" alt="My Trot" width="200px">
            </div>
            <h2 class="text-center mt-4">
              찜한 기사가 없습니다!
            </h2>
          </div>
        </div>
      </v-col>
      <transition name="fade">
        <ScrollTopButton v-if="scrolled"/>
      </transition> 
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
      scrolled: false,
      isData: false,
      isWaiting :true,
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
          if (res.data.length) {
            this.articleCnt = res.data[0].b_cnt
            setTimeout(() => { 
              this.articles.push(...res.data)
              this.isWaiting = false 
              this.isData = true }, 500) 
          } else {
            this.isWaiting = false
          }
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
    detectWindowScrollY() {
      this.scrolled = window.scrollY > 0
    }
  },
  created() {
    this.fetchArticleData()
  },
  mounted() {
    window.addEventListener('scroll', this.detectWindowScrollY)
    },
  beforeDestory() {
    window.removeEventListener('scroll', this.detectWindowScrollY)
  }
}
</script>

<style>

</style>
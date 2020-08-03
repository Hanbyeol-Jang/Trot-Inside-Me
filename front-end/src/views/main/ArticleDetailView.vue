<template>
  <v-card
    class="mx-auto my-10"
    max-width="700"
  >
    <v-container fluid>
      <v-row dense>
          <v-card>
            <ArticleDetailItem :article="article"/>
            <v-card-actions class="d-flex justify-space-around">

              <div @click="showLikeChange()">
                <v-btn icon>
                  <v-icon v-show="!showLike">mdi-heart</v-icon>
                  <v-icon v-show="showLike" color="red">mdi-heart</v-icon>
                  {{likeCnt}}
                </v-btn>
              </div>

              <div @click="showBookmarkChange()">
                <v-btn icon>
                  <v-icon v-show="!showBookmark">mdi-bookmark</v-icon>
                  <v-icon v-show="showBookmark" color="yellow">mdi-bookmark</v-icon>
                </v-btn>
              </div>

              <div  @click="showCommentsChange()">
                <v-btn icon>
                  <v-icon v-show="!showComments">mdi-message-text</v-icon>
                  <v-icon v-show="showComments" large color="blue darken-2">mdi-message-text</v-icon>
                </v-btn>
              </div>

            </v-card-actions>
            <CommentList v-show="showComments" :id="id"/>
          </v-card>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import axios from 'axios'
import ArticleDetailItem from '@/components/main/ArticleDetailItem.vue'
import CommentList from '@/components/main/CommentList.vue'
import SERVER from '@/api/drf'

export default {
  name: 'ArticleDetailView',
  components: {
    ArticleDetailItem,
    CommentList,
  },
  data(){
    return {
      article: [],
      bookmarkCnt:'',
      likeCnt:'',
      showLike: false,
      showComments: false,
      showBookmark: false,
      // id : this.$router.params.articleId
      id:1,
    }
  },
  methods: {
    checkBookmarklike(){
      const axiosConfig = {
          headers:{
          Authorization : `Token ${this.$cookies.get('auth-token')}`
          },
      }
      axios.get(SERVER.URL + '/',this.article.id,axiosConfig)
      .then((response)=>{
        this.showBookmark = response.data.data
        this.showLike = response.data.data
      })
      .catch((err) => {console.log(err.response.data)})
    },
    getArticle(){
      //////////////////////////////////////////////////////////////////////////////////////
      axios.get(SERVER.URL + `/${this.id}/`)
      ///////////////////////////////////////////////////////////////////////////////////////
        .then((response) => {
            this.article = response.data.data
            this.bookmarkCnt = response.data.data
            this.likeCnt = response.data.data
            this.checkBookmarklike()
        })
        .catch((err) => {console.log(err.response.data)})
    },
    showCommentsChange(){
      this.showComments = !this.showComments
    },
  },
    showBookmarkChange(){
      const axiosConfig = {
          headers:{
          Authorization : `Token ${this.$cookies.get('auth-token')}`
          },
      }
      axios.post(SERVER.URL+'/',this.article.id,axiosConfig)
      .then((response)=>{
        this.showBookmark = response.data.data
        this.bookmarkCnt = response.data.data
      })
      .catch((err) => {console.log(err.response.data)})
    },
    showLikeChange(){
      const axiosConfig = {
          headers:{
          Authorization : `Token ${this.$cookies.get('auth-token')}`
          },
      }
      axios.post(SERVER.URL+'/',this.article.id,axiosConfig)
      .then((response)=>{
        this.showLike = response.data.data
        this.likeCnt = response.data.data
      })
      .catch((err) => {console.log(err.response.data)})
    },
    created(){      // this.getArticle()
    },
    
}
</script>

<style>

</style>
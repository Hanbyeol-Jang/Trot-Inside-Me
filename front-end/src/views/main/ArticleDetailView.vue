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
      likeCnt:'',
      showLike: false,
      showComments: false,
      id : this.$route.params.articleId,
      axiosConfig : {
          headers:{
          token : `${this.$cookies.get('auth-token')}`
          },
      }
    }
  },
  methods: {
    // checkBookmarklike(){
    //   const axiosConfig = {
    //       headers:{
    //       Authorization : `Token ${this.$cookies.get('auth-token')}`
    //       },
    //   }
    //   axios.get(SERVER.URL + '/',this.article.id,axiosConfig)
    //   .then((response)=>{
    //     this.showBookmark = response.data.data
    //     this.showLike = response.data.data
    //   })
    //   .catch((err) => {console.log(err.response.data)})
    // },
    showLikeChange(){
      axios.post(SERVER.URL + `/${this.id}/`,this.axiosConfig)
      .then((response)=>{
        this.showLike = response.data.data
        this.likeCnt = response.data.data
      })
      .catch((err) => {console.log(err.response.data)})
    },
    getArticle(){
      axios.get(SERVER.URL +`/singer/articles/${this.$route.params.articleId}`)
        .then((response) => {
            console.log(response.data)
            this.article = response.data
        })
        .catch((err) => {console.log(err.response.data)})
    },
    showCommentsChange(){
      this.showComments = !this.showComments
    },
  },
    created(){      
      this.getArticle()
    },
    
}
</script>

<style>

</style>
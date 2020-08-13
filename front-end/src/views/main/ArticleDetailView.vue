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

              <div class="d-flex" @click="showLikeChange()">
                <v-btn icon>
                  <v-icon v-show="!showLike">mdi-heart</v-icon>
                  <v-icon v-show="showLike" color="red">mdi-heart</v-icon>
                </v-btn>
                <div class="my-2 mx-2">
                    {{likeCnt}}
                </div>
              </div>

              <div  class="d-flex" @click="showCommentsChange()">
                <v-btn icon>
                  <v-icon v-show="!showComments">mdi-message-text</v-icon>
                  <v-icon v-show="showComments" large color="blue darken-2">mdi-message-text</v-icon>
                </v-btn>
              </div>
                <div class="my-2 mx-2">
                    {{commentCnt}}
                </div>
            </v-card-actions>
            <MainCommentList v-show="showComments" @add-comment="addcomment" @delete-comment="deleteComment" :id="id"/>
          </v-card>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import axios from 'axios'
import ArticleDetailItem from '@/components/main/ArticleDetailItem.vue'
import MainCommentList from '@/components/main/comment/MainCommentList.vue'
import SERVER from '@/api/drf'

export default {
  name: 'ArticleDetailView',
  components: {
    ArticleDetailItem,
    MainCommentList,
  },
  data(){
    return {
      article: [],
      likeCnt:'',
      showLike: '',
      commentCnt:'',
      showComments: false,
      id : '',
      type:'',
    }
  },
  methods: {
    checkId(){
      this.id = this.$route.params.articleId
    },


    getArticle(){
      const axiosConfig = {
          headers:{
          token: `${this.$cookies.get('auth-token')}`
          },
      }
      axios.get(SERVER.URL +`/board/detail/2/${this.id}`,axiosConfig)
        .then((response) => {
          console.log(response)
          this.article = response.data
          this.type = response.data.b_type
          this.likeCnt = response.data.good_cnt
          this.commentCnt = response.data.br_cnt
          this.showLike = response.data.good_flag
          
        })
        .catch((err) => {console.log(err.response.data)})
    },


    showLikeChange(){
        if(this.showLike){
            this.showLike = 0
            this.likeCnt -= 1
        }else{
            this.showLike = 1
            this.likeCnt += 1
        }
      const axiosConfig = {
          headers:{
          token: `${this.$cookies.get('auth-token')}`
          },
          params: {isgood:this.showLike}
      }
      axios.get(SERVER.URL+`/board/good/${this.type}/${this.id}`,axiosConfig)
      .then((response)=>{
        console.log(response)
        // if(this.showLike){
        //     this.showLike = 0
        //     this.likeCnt -= 1
        // }else{
        //     this.showLike = 1
        //     this.likeCnt += 1
        // }
      })
      .catch((err) => {console.log(err.response.data)})
    },


    showCommentsChange(){
      this.showComments = !this.showComments
    },

    addcomment(){
        this.commentCnt += 1
    },


    deleteComment(){
        this.commentCnt -= 1
    }

  },
    created(){
      this.checkId()      
      this.getArticle()
    },
    
}
</script>

<style>

</style>
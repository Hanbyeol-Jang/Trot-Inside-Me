<template>
 <v-card-text>
   <hr>
        <v-col cols="10" class="row text-center mx-auto">
          <v-text-field
            label="댓글을 작성해주세요."
            single-line
            v-model="commentData.content" 
            @keypress.enter="commentCreate"
          ></v-text-field>
          <v-btn icon class="d-flex align-self-center">
              <i class="far fa-paper-plane fa-lg my-auto" @click="commentCreate"></i>
          </v-btn>
        </v-col>
    <div v-for="comment in comments" :key="comment.id">
      <CommentListItem class="mb-3" @comment-delete="commentDelete" :comment="comment"/>
      <hr>
    </div>
      <br>
      <p class="text-center" v-if="!comments.length">No results :( </p>
      <infinite-loading v-if="comments.length" @infinite="infiniteHandler"></infinite-loading>
</v-card-text>
</template>

<script>
import axios from 'axios'
import CommentListItem from '@/components/main/CommentListItem'
import InfiniteLoading from 'vue-infinite-loading'
import SERVER from '@/api/drf'

export default {
  name: 'CommentList',
  components: {
    CommentListItem,
    InfiniteLoading,
  },
  data(){
    return {
      comments: [],
      commentsNum: '',
      commentData: {
        content: '',
      },
      page: 1,
    }
  },
  methods: {
    getComments(){
        const axiosConfig2 = {
          headers:{
            token: `${this.$cookies.get('auth-token')}`,
            },
          params: {co_idx:this.$route.params.communityId, page: this.page}
        }
        axios.get(SERVER.URL+`/community/detail/1`,axiosConfig2)
        .then((response)=>{
            console.log(response)
            this.comments = response.data
            this.commentsNum = response.data.length
            console.log(this.comments)
            console.log(this.commentsNum)
        })
        .catch((err)=>{
            console.error(err)
        })
    },


    infiniteHandler($state) {
      const axiosConfig2 = {
        headers:{
          token: `${this.$cookies.get('auth-token')}`,
          },
        params: {co_idx:this.$route.params.communityId, page: this.page+1}
      }
      if (parseInt(this.commentsNum / 5) >= this.page){
        axios.get(SERVER.URL +`/community/list`, axiosConfig2)
          .then(res => {
            setTimeout(() => {
              this.page += 1
              this.comments.push(...res.data)
              $state.loaded()
            }, 1000);
          })
          .catch(err => console.log(err))
      } else{
        $state.complete()     
      }
    },


    commentCreate(){
      console.log(123)
      console.log(this.commentData.content)
      if (!this.commentData.content){
        this.$alert('내용을 작성해주세요')
      } else{
        if (!this.$cookies.isKey('auth-token')){
          this.$alert('로그인이 필요합니다')
          this.commentData.content = null
        } else{
            const axiosConfig2 = {
              headers:{
                token: `${this.$cookies.get('auth-token')}`,
                },
            }
            const dto = {
                "co_idx": this.$route.params.communityId,
                "cr_content": this.commentData.content,
            }
-          axios.post(SERVER.URL + `/community/reply/add/${this.$route.params.communityId}`,dto,axiosConfig2)
           .then((res) => {
             console.log(res)
              this.commentData.content = null
              this.comments.push(res.data.data)
            })
            .catch((err) => { console.log(err.response.data) })
        }
      }
    },


    commentDelete(commentId){
      this.comments = this.comments.filter(function (comment){
        return comment.id !== commentId
      })
    },


  },
  created(){
    this.getComments()
  }
}
</script>

<style>

</style>
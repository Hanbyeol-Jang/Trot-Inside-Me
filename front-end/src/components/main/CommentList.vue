<template>
 <v-card-text>
   <hr>
    <div v-for="comment in comments" :key="comment.id">
      <CommentListItem class="mb-3" @comment-delete="commentDelete" :comment="comment"/>
      <hr>
    </div>
      <br>
      <p class="text-center" v-if="!comments.length">No results </p>
      <infinite-loading v-if="comments.length" @infinite="infiniteHandler"></infinite-loading>
        <v-col cols="10" class="row text-center mx-auto">
          <v-text-field
            label="Write a comment"
            single-line
            v-model="commentData.content" 
            @keypress.enter="commentCreate"
          ></v-text-field>
          <v-btn icon class="d-flex align-self-center">
              <i class="far fa-paper-plane fa-lg my-auto" @click="commentCreate"></i>
          </v-btn>
        </v-col>
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
  props:{
    id:Number
  },
  data(){
    return {
      comments: [],
      commentsNum: null,
      commentData: {
        content: null,
      },
      page: 1,
    }
  },
  methods: {
    getComments(){
    const options = {params: {_page: this.page}}
    //////////////////////////////////////////////////////////////////////
    axios.get(SERVER.URL + `/${this.id}/`,options)
    /////////////////////////////////////////////////////////////////////
      .then((response) => {
        this.comments = response.data.data
        this.commentsNum = response.data.comments_num
      })
      .catch((err) => { console.log(err.response.data) })
    },
    infiniteHandler($state) {
      const options = {params: {_page: this.page+1}}
      ////////////////////////////////////////////////////////////////
      axios.get(SERVER.URL + `/${this.id}/`,options)
      ////////////////////////////////////////////////////////////////
      .then(( response ) => {
        if ((response.data.comments_num/5) >= this.page) {
          setTimeout(()=>{
            this.page += 1;
            this.comments.push(...response.data.data);
            $state.loaded();
          }, 1000);
        } else {
          $state.complete();
        }
      })
      .catch((err) => {
        console.log(err.response.data)
        $state.complete();
      })
    },
    showCommentsChange(){
      this.showComments = !this.showComments
    },
    commentCreate(){
      if (!this.commentData.content){
        alert('내용을 작성해주세요')
      } else{
        /////////////////////////////////////////
        if (!this.$cookies.isKey('auth-token')){
        ////////////////////////////////////////
          alert('로그인이 필요합니다')
          this.commentData.content = null
        } else{
          //////////////////////////////////////////////////////
          axios.post(SERVER.URL + `/${this.id}/`, this.commentData, { headers: { Authorization: `Token ${this.$cookies.get("auth-token")}` }})
          /////////////////////////////////////////////////////
           .then((res) => {
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
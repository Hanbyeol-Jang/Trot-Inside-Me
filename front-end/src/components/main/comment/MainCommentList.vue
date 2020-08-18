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
      <MainCommentListItem class="mb-3" @delete-comment2="commentDelete" :comment="comment" :currentUser="currentUser"/>
    </div>
      <br>
      <!-- <p class="text-center" v-if="!comments.length">No results :( </p> -->
      <infinite-loading  @infinite="infiniteHandler"></infinite-loading>
      <ScrollTopButton />
</v-card-text>
</template>

<script>
import axios from 'axios'
import MainCommentListItem from '@/components/main/comment/MainCommentListItem'
import InfiniteLoading from 'vue-infinite-loading'
import SERVER from '@/api/drf'
import ScrollTopButton from '@/components/main/ScrollTopButton'


export default {
  name: 'MainCommentList',
  components: {
    MainCommentListItem,
    InfiniteLoading,
    ScrollTopButton,
  },
  props:{
    commentCnt:Number,
    id:Number,
    type:Number
  },
  data(){
    return {
      currentUser:{},
      comments: [],
      commentData: {
        content: '',
      },
      page: 1,
    }
  },
  methods: {

    checkAuth(){
      if (this.$cookies.get('auth-token')){
        const axiosConfig ={
            headers:{
                token : `${this.$cookies.get('auth-token')}`
            },
        }
        axios.get(SERVER.URL+`/user/getUserInfo`,axiosConfig)
        .then((reaponse)=>{
            this.currentUser = reaponse.data
        })
        .catch((err)=>{
            console.error(err)
        })
      }
    },

    getComments(){
        const axiosConfig2 = {
          headers:{
            token: `${this.$cookies.get('auth-token')}`,
            },
          params: {page:this.page}
        }
        axios.get(SERVER.URL+`/board/replylist/${this.type}/${this.id}`,axiosConfig2)
        .then((response)=>{
            this.comments = response.data
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
        params: {page: this.page+1}
      } 
      if (parseInt(this.commentCnt / 5)+1 >= this.page){
        axios.get(SERVER.URL +`/board/replylist/${this.type}/${this.id}`, axiosConfig2)
          .then(res => {
            setTimeout(() => {
              this.page+=1
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
        const json = {
            b_type:this.type,
            b_idx : this.id ,
            r_content : this.commentData.content
        }
        const axiosConfig2 = {
            headers:{
                token : `${this.$cookies.get('auth-token')}`
            },
            params: {page: this.page}
        }
      if (!this.commentData.content){
        this.$alert('내용을 작성해주세요')
      } else{
        if (!this.$cookies.isKey('auth-token')){
            this.$confirm(
                {
                message: `로그인 해주세요.`,
                button: {
                    yes: '로그인 하기',
                    no: '돌아가기',
                },
                callback: confirm => {
                    if (confirm) {
                      this.$router.push({ name: 'Login'})
                    }
                }})
            this.commentData.content = ''
        } else{
-          axios.post(SERVER.URL + `/board/replyadd`,json,axiosConfig2)
           .then((res) => {
              this.$emit('add-comment')
              this.commentData.content = ''
              this.comments = []
              this.comments.push(...res.data)
            })
            .catch((err) => { console.log(err.response.data) })
        }
      }
    },


    commentDelete(idx){
      const axiosConfig2 = {
        headers:{
          token: `${this.$cookies.get('auth-token')}`,
          },
        params: {page: this.page}
      }
        axios.delete(SERVER.URL+`/board/replydelete/${this.type}/${this.id}/${idx}`,axiosConfig2)
        .then((response)=>{
            this.$emit('delete-comment')
            this.comments = []
            this.comments.push(...response.data)
            this.$alert('삭제 완료')
        })
        .catch((err)=>{
            console.log(err)
        })
     },
  },
  
  created(){
    this.getComments()
    this.checkAuth()
  }
}
</script>

<style>

</style>
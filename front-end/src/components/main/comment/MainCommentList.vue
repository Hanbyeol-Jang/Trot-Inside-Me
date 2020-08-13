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
      <MainCommentListItem class="mb-3" @delete-comment2="commentDelete" :comment="comment"/>
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
    communityIdx:Number
  },
  data(){
    return {
      comments: [],
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
        axios.get(SERVER.URL+`/community/replylist/${this.$route.params.communityId}`,axiosConfig2)
        .then((response)=>{
            console.log(response)
            this.comments = response.data
        })
        .catch((err)=>{
            console.error(err)
        })
    },


    infiniteHandler($state) {
      console.log(this.page)
      const axiosConfig2 = {
        headers:{
          token: `${this.$cookies.get('auth-token')}`,
          },
        params: {co_idx:this.$route.params.communityId, page: this.page+1}
      } 
      if (parseInt(this.commentCnt / 5)+1 >= this.page){
        axios.get(SERVER.URL +`/community/detail/replylist/${this.$route.params.communityId}`, axiosConfig2)
          .then(res => {
            console.log(res.data)
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
            co_idx : this.$route.params.communityId ,
            cr_content : this.commentData.content
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
          this.$alert('로그인이 필요합니다')
          this.commentData.content = ''
        } else{
-          axios.post(SERVER.URL + `/community/replyadd`,json,axiosConfig2)
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
        axios.delete(SERVER.URL+`/community/replydelete/${this.$route.params.communityId}/${idx}`,axiosConfig2)
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
  }
}
</script>

<style>

</style>
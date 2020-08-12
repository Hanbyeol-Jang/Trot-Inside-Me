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
      <CommentListItem class="mb-3" @delete-comment2="commentDelete" :comment="comment"/>
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
      console.log(this.communityIdx)
        const axiosConfig2 = {
          headers:{
            token: `${this.$cookies.get('auth-token')}`,
            },
          params: {co_idx:this.$route.params.communityId, page: this.page}
        }
        axios.get(SERVER.URL+`/community/detail/reply/${this.$route.params.communityId}`,axiosConfig2)
        .then((response)=>{
            this.comments = response.data
        })
        .catch((err)=>{
            console.error(err)
        })
    },


    infiniteHandler($state) {
      console.log(this.commentCnt)
      const axiosConfig2 = {
        headers:{
          token: `${this.$cookies.get('auth-token')}`,
          },
        params: {co_idx:this.$route.params.communityId, page: this.page+1}
      } 
      if (parseInt(this.commentCnt / 5)+1 >= this.page){
        console.log(axiosConfig2)
        axios.get(SERVER.URL +`/community/detail/reply/${this.$route.params.communityId}`, axiosConfig2)
          .then(res => {
            setTimeout(() => {
              this.page+=1
              console.log(res)
              console.log(this.page)
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
        this.$emit('add-comment')
        this.page = 1
        const json = {
            co_idx : this.$route.params.communityId ,
            cr_content : this.commentData.content
        }
        const axiosConfig2 = {
            headers:{
                token : `${this.$cookies.get('auth-token')}`
            }
        }
      if (!this.commentData.content){
        this.$alert('내용을 작성해주세요')
      } else{
        if (!this.$cookies.isKey('auth-token')){
          this.$alert('로그인이 필요합니다')
          this.commentData.content = null
        } else{
-          axios.post(SERVER.URL + `/community/reply/add`,json,axiosConfig2)
           .then((res) => {
             console.log(res)
             this.page = 1
              this.commentData.content = ''
              this.comments = res.data
            })
            .catch((err) => { console.log(err.response.data) })
        }
      }
    },


    commentDelete(){
      this.$emit('delete-comment')
    },


  },
  created(){
    this.getComments()
  }
}
</script>

<style>

</style>
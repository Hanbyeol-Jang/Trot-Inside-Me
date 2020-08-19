<template >
<div>
    <br>
  <v-card
    hover 
    max-width="700"
    class="mx-auto"
    >
    <v-list-item class="d-flex">
      <v-list-item-avatar>      
        <img v-if="userImg" :src="userImg" alt="User">
        <img v-else src="@/assets/image/user_default.png" alt="User">
      </v-list-item-avatar>
      <v-list-item-content class="d-flex justify-space-between">
        <div class="d-flex">
            <v-list-item-title >{{communityUser}}</v-list-item-title>
            <div class="mr-1" v-show="deleteuser">
                <v-btn depressed color="error" @click="deleteArticle">삭제</v-btn>
            </div>
            <div v-show="edituser">
                <v-btn depressed color="primary" @click="editArticle">수정</v-btn>
            </div>
        </div>
      </v-list-item-content>
    </v-list-item>
        <v-card-text>
            {{communityDate.slice(0,10)}}
        </v-card-text>
    <v-img v-if="communityImg" :src="communImg" height=100%></v-img>
    <v-card-text>
        <h1>
            {{communityContent}}
        </h1>
    </v-card-text>
    <hr>
    <v-card-actions class="d-flex justify-space-around">

        <div class="d-flex" @click="showLikeChange()">
        <v-btn icon>
            <v-icon v-show="!showLike">mdi-thumb-up</v-icon>
            <v-icon v-show="showLike" color="red">mdi-thumb-up</v-icon>
        </v-btn>
            <div class="my-2 mx-2">
                {{likeCnt}}
            </div>
        </div>
        <div>
        <v-btn icon>
            <v-icon large color="blue darken-2">mdi-message-text</v-icon>
            <div class="my-2 mx-2">
                {{commentCnt}}
            </div>
        </v-btn>
        </div>
    </v-card-actions>
    <CommentList :commentCnt="commentCnt" :communityIdx="communityIdx" @add-comment="addcomment" @delete-comment="deleteComment"/>
  </v-card>
    <br>
    <br>
    <transition name="fade">
      <ScrollTopButton v-if="scrolled"/>
    </transition>
    </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

import ScrollTopButton from '@/components/main/ScrollTopButton'
import CommentList from '@/components/community/CommentList.vue'

export default {
    name:"CommunityDetailView",
    components:{
        CommentList,
        ScrollTopButton,
    },
    data(){
        return{
            userImg:'',
            communityImg:'',
            communityDate:'',
            communityContent:'',
            communityIdx:0,
            likeCnt:'',
            commentCnt:0,
            showLike: '',
            currentUser:'',
            communityUser:'',
            edituser:false,
            deleteuser:false,
            axiosConfig :{
                headers:{
                    token : `${this.$cookies.get('auth-token')}`
                },
            },
            scrolled: false,
        }
    },
    computed: {
        communImg() {
            return '/images/' + this.communityImg
        }
    },
    methods: {

        checkLogin(){
                if (!(this.$cookies.get('auth-token'))){
                    this.$alert(" 로그인을 해주세요")
                    this.$router.push({name:'Home'})                
                }
            },

        getuser(){
            axios.get(SERVER.URL+`/user/getUserInfo`,this.axiosConfig)
            .then((reaponse)=>{
                if(Number(reaponse.data.u_isAdmin)){
                    this.currentUser = reaponse.data.u_name
                    this.deleteuser = true
                    if (this.communityUser === this.currentUser){
                        this.edituser = true
                    }else{
                        this.edituser = false
                    }
                }else{
                    this.currentUser = reaponse.data.u_name
                    if (this.communityUser === this.currentUser){
                        this.edituser = true
                    }else{
                        this.edituser = false
                    }
                }
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        getCommunity(){
            axios.get(SERVER.URL+`/community/detail/${this.$route.params.communityId}`,this.axiosConfig)
            .then((reaponse)=>{
                this.communityDate=reaponse.data.co_date,
                this.communityContent=reaponse.data.co_content,
                this.likeCnt=reaponse.data.good_cnt,
                this.commentCnt=reaponse.data.cr_cnt,
                this.showLike=reaponse.data.good_flag,
                this.communityUser=reaponse.data.co_name,
                this.communityIdx=reaponse.data.co_idx
                this.userImg = reaponse.data.co_profileImg
                this.communityImg = reaponse.data.co_img
                this.getuser()
            })
            .catch((err)=>{
                console.error(err)
            })
        },


        showLikeChange(){
            const axiosConfig2 = {
              headers:{
                token: `${this.$cookies.get('auth-token')}`,
                },
              params: {co_idx:this.communityIdx, isgood:this.showLike}
            }
            axios.get(SERVER.URL+`/community/good/${this.communityIdx}`,axiosConfig2)
            .then(()=>{
                if(this.showLike){
                    this.showLike = 0
                    this.likeCnt -= 1
                }else{
                    this.showLike = 1
                    this.likeCnt += 1
                }
            })
            .catch((err) => {console.log(err)})
            },


        deleteArticle(){
            this.$confirm(
                {
                message: `삭제하시겠습니까?`,
                button: {
                    yes: '삭제하기',
                    no: '아니요',
                },
                callback: confirm => {
                    if (confirm) {
                        const axiosConfig2 = {
                        headers:{
                            token: `${this.$cookies.get('auth-token')}`,
                            },
                        }
                        axios.delete(SERVER.URL+`/community/detaildelete/${this.communityIdx}`,axiosConfig2)
                        .then(()=>{
                            this.$alert('삭제 완료')
                            this.$router.push({name:'CommunityIndexView'})                
                        })
                        .catch((err)=>{
                            console.log(err)
                        })                  
                    }
                }
                }
            )
        },


        editArticle(){
            this.$router.push({ name: 'CommunityUpdateView', params: { communityId: this.communityIdx, page:this.$route.params.page }})
        },

        addcomment(){
            this.commentCnt += 1
        },


        deleteComment(){
            this.commentCnt -= 1
        },
        detectWindowScrollY() {
        this.scrolled = window.scrollY > 0
      }
    },
    created(){
        this.checkLogin(),
        this.getCommunity()
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
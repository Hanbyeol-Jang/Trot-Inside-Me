<template>
  <v-card
    hover 
    max-width="700"
    class="mx-auto"
  >
    <v-list-item class="d-flex">
      <v-list-item-avatar>      
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John">
        <!-- <img :src="userimg" alt="userimg"> -->
      </v-list-item-avatar>
      <v-list-item-content class="d-flex justify-space-between">
        <div class="d-flex">
            <!-- <v-list-item-title class="mr-1">username</v-list-item-title> -->
            <v-list-item-title>{{community.co_name}}</v-list-item-title>
            <div class="mr-1" v-show="edituser">
                <v-btn depressed color="error">삭제</v-btn>
            </div>
            <div v-show="deleteuser">
                <v-btn depressed color="primary">수정</v-btn>
            </div>
        </div>
        <v-card-text>
        {{community.co_date}}
        </v-card-text>
      </v-list-item-content>
    </v-list-item>

    <!-- <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" height="194"></v-img> -->
    <v-img :src="communityimg" height=100%></v-img>

    <v-card-text>
      {{community.co_content}}
    </v-card-text>
    <hr>
    <v-card-actions class="d-flex justify-space-around">

        <div @click="showLikeChange()">
        <v-btn icon>
            <v-icon v-show="!showLike">mdi-thumb-up</v-icon>
            <v-icon v-show="showLike" color="red">mdi-thumb-up</v-icon>
            {{likeCnt}}
        </v-btn>
        </div>
        <div  @click="showCommentsChange()">
        <v-btn icon>
            <v-icon large color="blue darken-2">mdi-message-text</v-icon>
            {{commentCnt}}
        </v-btn>
        </div>
    </v-card-actions>
    <CommentList v-show="showComments" :id="id"/>
  </v-card>
</template>

<script>
import CommentList from '@/components/main/CommentList.vue'
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"CommunityDetailView",
    components:{
        CommentList,
    },
    data(){
        return{
            likeCnt:'',
            commentCnt:'',
            showLike: false,
            currentUser:'',
            communityUser:this.community.user,
            edituser:false,
            deleteuser:false,
            axiosConfig : {
                headers:{
                Authorization : `Token ${this.$cookies.get('auth-token')}`
                },
            }
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
            axios.get(SERVER.URL`/user/`,this.axiosConfig)
            .then((reaponse)=>{
                this.currentUser = reaponse.data.username
                if (this.communityUser === this.currentUser){
                    this.edituser = true
                    this.deleteuser =true
                }else{
                    this.edituser = false
                    this.deleteuser = false
                }
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        getCommunity(){
            console.log(123456)
            console.log(this.$route.params.communityId)
            console.log(this.$route.params.page)
            const axiosConfig2 = {
              headers:{
                token: `${this.$cookies.get('auth-token')}`,
                },
              params: {co_idx:this.$route.params.communityId, page: this.$route.params.page}
            }
            axios.get(SERVER.URL+`/community/detail/${this.$route.params.communityId}`,axiosConfig2)
            .then((reaponse)=>{
                console.log(reaponse)
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        checkAdmin(){
            axios.get(SERVER.URL`/user/`,this.axiosConfig)
            .then((reaponse)=>{
                if(reaponse.data.u_isAdmin){
                    this.deleteuser = true
                }
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        showLikeChange(){
        axios.post(SERVER.URL+'/',`/${this.id}/`,this.axiosConfig)
        .then((response)=>{
            this.showLike = response.data.data
            this.likeCnt = response.data.data
        })
        .catch((err) => {console.log(err)})
        },

        showCommentsChange(){
        this.showComments = !this.showComments
        },
    },
    computed:{
        userimg(){
            return this.community.userimg
        },
        communityimg(){
            return this.community.co_img
        },
    },
    created(){
        this.getCommunity()
        this.checkLogin()
        this.getuser()
    }
}
</script>

<style>

</style>
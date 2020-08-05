<template>
  <v-card
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
            <v-list-item-title class="mr-1">username</v-list-item-title>
            <!-- <v-list-item-title>{{community.username}}</v-list-item-title> -->
            <div class="mr-1" v-show="edituser">
                <v-btn depressed color="error">삭제</v-btn>
            </div>
            <div v-show="deleteuser">
                <v-btn depressed color="primary">수정</v-btn>
            </div>
        </div>
      </v-list-item-content>
    </v-list-item>

    <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" height="194"></v-img>
    <!-- <v-img :src="communityimg" height=100%></v-img> -->

    <v-card-text>
      Visit ten places on our planet that are undergoing the biggest changes today.
      <!-- {{community.description}} -->
    </v-card-text>
    <hr>
    <v-card-actions class="d-flex justify-space-around">
        <div>
            <v-icon v-show="!showLike">mdi-thumb-up</v-icon>
            <v-icon v-show="showLike" color="red">mdi-thumb-up</v-icon>
            {{likeCnt}}
        </div>
        <div>
            <v-icon v-show="!showComments">mdi-message-text</v-icon>
            <v-icon v-show="showComments" large color="blue darken-2">mdi-message-text</v-icon>
            {{commentCnt}}
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
    name:"CommunityDetail",
    components:{
        CommentList,
    },
    props:{
        community:Object,
    },
    data(){
        return{
            likeCnt:'',
            commentCnt:'',
            showLike: false,
            showComments: false,
            id:1,
            // likeCnt:this.community.likecnt,
            // commentCnt:this.community.commentcnt,
            // showLike: this.community.flag,
            // showComments: false,
            // id:this.community.id,
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
    },
    computed:{
        userimg(){
            return this.community.userimg
        },
        communityimg(){
            return this.community.communityimg
        },
    },
    created(){
        this.getuser(),
        this.checkAdmin()
    }
}
</script>

<style>

</style>
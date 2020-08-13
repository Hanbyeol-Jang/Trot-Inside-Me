<<<<<<< HEAD
<template >
<div v-show="deleteFlag">
  <v-card
    hover 
    max-width="700"
    class="mx-auto"
    >
=======
<template>
  <v-card
    max-width="700"
    class="mx-auto"
  >
>>>>>>> BE-Develop
    <v-list-item class="d-flex">
      <v-list-item-avatar>      
        <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John">
        <!-- <img :src="userimg" alt="userimg"> -->
      </v-list-item-avatar>
      <v-list-item-content class="d-flex justify-space-between">
        <div class="d-flex">
<<<<<<< HEAD
            <v-list-item-title @click="goCommunity()" >{{community.co_name}}</v-list-item-title>
            <div class="mr-1" v-show="deleteuser">
                <v-btn depressed color="error" @click="deleteArticle">삭제</v-btn>
            </div>
            <div v-show="edituser">
=======
            <v-list-item-title class="mr-1">username</v-list-item-title>
            <!-- <v-list-item-title>{{community.username}}</v-list-item-title> -->
            <div class="mr-1" v-show="edituser">
                <v-btn depressed color="error" @click="deleteArticle">삭제</v-btn>
            </div>
            <div v-show="deleteuser">
>>>>>>> BE-Develop
                <v-btn depressed color="primary" @click="editArticle">수정</v-btn>
            </div>
        </div>
      </v-list-item-content>
    </v-list-item>
<<<<<<< HEAD
        <v-card-text>
            {{community.co_date.slice(0,10)}}
        </v-card-text>
    <v-img :src="communityimg" height=100% @click="goCommunity()"></v-img>
    <v-card-text @click="goCommunity()">
      {{community.co_content}}
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
        <div class="d-flex" @click="goCommunity()">
        <v-btn icon>
            <v-icon>mdi-message-text</v-icon>
        </v-btn>
            <div class="my-2 mx-2">
                {{commentCnt}}
            </div>
        </div>
    </v-card-actions>
  </v-card>
    <br>
    <hr>  
    <br>
</div>
</template>
<script>
// import CommentList from '@/components/main/CommentList.vue'
import axios from 'axios'
import SERVER from '@/api/drf'
export default {
    name:"CommunityDetailItem",
    components:{
        // CommentList,
    },
    props:{
        community:Object,
        page:Number,
    },
    data(){
        return{
            deleteFlag:true,
            likeCnt:this.community.good_cnt,
            commentCnt:this.community.cr_cnt,
            showLike: this.community.good_flag,
            currentUser:'',
            communityUser:this.community.co_name,
            edituser:false,
            deleteuser:false,
            axiosConfig :{
                headers:{
                    token : `${this.$cookies.get('auth-token')}`
=======

    <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg" height="194"></v-img>
    <!-- <v-img :src="communityimg" height=100%></v-img> -->

    <v-card-text>
      Visit ten places on our planet that are undergoing the biggest changes today.
      <!-- {{community.description}} -->
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
            <v-icon v-show="!showComments">mdi-message-text</v-icon>
            <v-icon v-show="showComments" large color="blue darken-2">mdi-message-text</v-icon>
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
    name:"CommunityDetailItem",
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
>>>>>>> BE-Develop
                },
            }
        }
    },
    methods: {
<<<<<<< HEAD
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
=======
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
>>>>>>> BE-Develop
                }
            })
            .catch((err)=>{
                console.error(err)
            })
        },

<<<<<<< HEAD

        showLikeChange(){
            const axiosConfig2 = {
              headers:{
                token: `${this.$cookies.get('auth-token')}`,
                },
              params: {co_idx:this.community.co_idx, isgood:this.showLike}
            }
            axios.get(SERVER.URL+`/community/good/${this.community.co_idx}`,axiosConfig2)
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


        goCommunity(){
            this.$router.push({ name: 'CommunityDetailView', params: { communityId: this.community.co_idx, page:this.page }})
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
                        const idx = this.community.co_idx
                        this.$emit('community-delete',idx)                    
                    }
                }
                }
            )},


        editArticle(){
            this.$router.push({ name: 'CommunityUpdateView', params: { communityId: this.community.co_idx, page:this.page }})
=======
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

        deleteArticle(event){
            event.preventDefault()
            axios.delete(`${SERVER.URL}//`+this.$route.params.communityId+'/',this.axiosConfig)
            .then((response)=>{
                console.log(response)
                this.$alert('삭제 완료')
            })
            .catch((err)=>{
                console.log(err)
            })
        },

        editArticle(){
            this.$router.push({ name: 'CommunityUpdateView', params: { communityId: this.community.id }})
>>>>>>> BE-Develop
        },
    },
    computed:{
        userimg(){
            return this.community.userimg
        },
        communityimg(){
<<<<<<< HEAD
            return `${SERVER.URL}/${this.community.co_img}`
        },
    },
    created(){
        this.checkLogin(),
        this.getuser()
    },
}
</script>
<style>
</style>
=======
            return this.community.communityimg
        },
    },
    created(){
        this.getuser(),
        this.checkAdmin()
    },
}
</script>

<style>

</style>
>>>>>>> BE-Develop

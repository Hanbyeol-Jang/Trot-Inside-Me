<template>
  <div>
    <h1 class="d-flex">커뮤니티 수정</h1>
  <v-card
    class="mx-auto mt-5"
    max-width="500"
  >
    <v-card-text>
      <v-textarea solo label="여기를 눌러 새로운 소식을 남겨보세요." height="400" v-model="content"></v-textarea>
      <v-img :src="show_image" width="100%" height="100%" v-show="!flag"></v-img>
      <v-img :src="change_image" width="100%" height="100%" v-show="flag"></v-img>
      <v-file-input show-size counter multiple label="사진을 등록 할 수 있습니다." type="file" id="file" ref="file" @change="communityImage()"></v-file-input>
    </v-card-text>
    <v-card-actions class="d-flex flex-row-reverse">
        <v-btn text color="deep-purple accent-4" @click="updateCommunity"><v-icon class="mr-2">mdi-pencil</v-icon>글 수정하기</v-btn>
    </v-card-actions>
  </v-card>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"CommunityUpdateView",
    data(){
        return{
            flag:false,
            show_image:'',
            change_image:'',
            image:"",
            content:"",
            currentuser:"",
            user:"",
            axiosConfig:{
              headers:{
                Authorization : `Token ${this.$cookies.get('auth-token')}`
              }
            },
        }
    },
    methods:{
        checklogin(){
            if (!(this.$cookies.get('auth-token'))){
                this.$alert(" 로그인을 해주세요")
                this.$router.push({name:'Home'})                
            }
        },

        getuser(){
            axios.get(`${SERVER.URL}/rest-auth/user/`,this.axiosConfig)
                .then((reaponse)=>{
                this.currentuser = reaponse.data.username
                if (this.user !== this.currentuser){
                    this.$alert("잘 못 된 접근입니다.");
                    this.$router.push({name:'Home'})
                }  
                })
                .catch((err)=>{
                console.error(err)
                })
        },

        getcommunity(){ 
            axios.get(`${SERVER.URL}/community/articles/`+this.$route.params.communityId+'/')
            .then((reaponse)=>{
                this.show_image = SERVER.URL+reaponse.data.data.image
                this.content = reaponse.data.data.content
                this.user = reaponse.data.data.user.username
                this.getuser()
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        updateCommunity(event){
            event.preventDefault()
            const data = new FormData()
            data.append('content',this.content)
            if (this.$refs.file.$refs.input.files[0]!==undefined){
              data.append('image',this.image)
            }
            axios.post(`${SERVER.URL}/community/`,data,this.axiosConfig)
            .then(()=>{
                this.$router.push({ name: 'CommunityIndexView'})
            })
            .catch((err)=>{
                console.log(err)
            })
        },

        communityImage(){
          this.image = this.$refs.file.$refs.input.files[0]
          this.change_image = URL.createObjectURL(this.image)
          this.flag = true
        },
    },
    created(){
        this.checklogin()
        this.getcommunity()
        this.getuser()
    },
}
</script>

<style>

</style>
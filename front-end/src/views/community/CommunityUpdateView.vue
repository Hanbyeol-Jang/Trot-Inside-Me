<template>
  <div class="my-5">
    <h1 class="d-flex">커뮤니티 수정</h1>
  <v-card
    class="mx-auto mt-5"
    max-width="500"
  >
    <v-card-actions class="d-flex flex-row-reverse">
        <v-btn  outlined color="deep-purple accent-4" @click="updateCommunity"><v-icon class="mr-2">mdi-pencil</v-icon>글 수정하기</v-btn>
        <div class="mr-1">
            <v-btn depressed  color="error" @click="deleteArticle">삭제</v-btn>
        </div>
    </v-card-actions>    
    <v-card-text>
      <v-textarea solo label="여기를 눌러 새로운 소식을 남겨보세요." height="300" v-model="content"></v-textarea>
      <v-img :src="show_image" width="100%" height="100%" v-show="!flag"></v-img>
      <v-img :src="change_image" width="100%" height="100%" v-show="flag"></v-img>
      <v-file-input show-size counter multiple label="사진을 등록 할 수 있습니다." type="file" id="file" ref="file" @change="communityImage()"></v-file-input>
    </v-card-text>
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
                token : `${this.$cookies.get('auth-token')}`
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
            axios.get(`${SERVER.URL}/user/getUserInfo`,this.axiosConfig)
                .then((reaponse)=>{
                this.currentuser = reaponse.data.u_name
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
            axios.get(SERVER.URL+`/community/detail/${this.$route.params.communityId}`,this.axiosConfig)
            .then((reaponse)=>{
                this.content=reaponse.data.co_content,
                this.user=reaponse.data.co_name,
                this.show_image=reaponse.data.co_img
                this.getuser()
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        updateCommunity(){
            const axiosConfig2={
              headers:{
                token : `${this.$cookies.get('auth-token')}`,
                'Content-Type': 'multipart/form-data'
              }
            }
            const data = new FormData()
            data.append('co_idx',this.$route.params.communityId)
            data.append('co_content',this.content)
            if (this.$refs.file.$refs.input.files[0]!==undefined){
              data.append('co_img',this.image)
            }
            // const data = {
            //   'co_idx':this.$route.params.communityId,
            //   'co_content' : this.content,
            //   'co_img' : this.image
            // }
            axios.put(`${SERVER.URL}/community/update`,data,axiosConfig2)
            .then(()=>{
                this.$router.push({ name: 'CommunityIndexView'})
            })
            .catch((err)=>{
                console.log(err)
            })
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

        communityImage(){
          this.image = this.$refs.file.$refs.input.files[0]
          this.change_image = URL.createObjectURL(this.image)
          this.flag = true
        },

    },
    created(){
        this.checklogin()
        this.getcommunity()
    },
}
</script>

<style>

</style>
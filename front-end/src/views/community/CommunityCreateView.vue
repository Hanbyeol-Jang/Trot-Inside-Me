<template>
  <div class="my-5">
  <v-card
    class="mx-auto mt-5"
    max-width="500"
  >
    <v-card-actions class="d-flex flex-row-reverse">
        <v-btn text color="deep-purple accent-4" @click="createCommunity"><v-icon class="mr-2">mdi-pencil</v-icon>글 작성하기</v-btn>
    </v-card-actions>
    <v-card-text>
      <v-textarea solo label="여기를 눌러 새로운 소식을 남겨보세요." height="300" v-model="content"></v-textarea>
      <v-img :src="change_image" width="100%" height="100%" v-show="flag"></v-img>
      <!-- <v-file-input show-size counter multiple label="사진을 등록 할 수 있습니다." type="file" id="file" ref="file" @change="communityImage()"></v-file-input> -->
      <input type="file" label="사진을 등록 할 수 있습니다." id="file" ref="file" accept="image/*" @change="communityImage()"/>
    </v-card-text>
  </v-card>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"CommunityCreateView",
    data(){
        return{
            flag:false,
            change_image:'',
            image:null,
            content:"",
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
            this.$router.push({name:'Login'})                
            }
        },

        createCommunity(){
          if(!this.content.trim() && !this.image){
            this.$alert("입력 된 값이 없습니다.")
            this.content = ''
          }else{
            const axiosConfig2={
              headers:{
                token : `${this.$cookies.get('auth-token')}`,
                'Content-Type': 'multipart/form-data'
              }
            }
            let dto = new FormData()
            if(this.content){
              dto.append('co_content',this.content)
            }
            if(this.image){
              dto.append('co_img',this.image)
            }
            axios.post(`${SERVER.URL}/community/add`,dto,axiosConfig2)
            .then(()=>{
                this.$router.push({ name: 'CommunityIndexView'})
            })
            .catch((err)=>{
                console.log(err)
            })
          }
        },

        communityImage(){
          this.image = this.$refs.file.files[0]
          if(this.image){
            var fileName = document.getElementById("file").value;
            var idxDot = fileName.lastIndexOf(".") + 1;
            var extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
            if (extFile=="jpg" || extFile=="jpeg" || extFile=="png"){
                //TO DO
            }else{
                this.$alert("jpg/jpeg,png 이미지만 가능해요!");
            }  
            this.change_image = URL.createObjectURL(this.image)
            this.flag = true
          }else{
            this.change_image = null
            this.flag =false
          }
        },
    },
    created(){
        this.checklogin()
    },
}
</script>

<style>
.inputfile {
	width: 0.1px;
	height: 0.1px;
	opacity: 0;
	overflow: hidden;
	position: absolute;
	z-index: -1;
}
</style>

<template>
  <div class="container">
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <v-container fluid class="p-0">
            <v-row v-if="singers.length">
              <v-col
                v-for="singer in singers"
                :key="singer.s_name"
                cols="4"
              >
              <div class="cursor_test" @click="inputsinger(singer)">
                    <v-avatar size="80">
                      <img
                        :src="singer.s_img"
                        alt="John"
                      height="100%"
                      width="100%"
                      >
                    </v-avatar>
                <h4 class="text-center">{{singer.s_name}}</h4>
              </div>
              </v-col>
            </v-row>
            <p class="text-center" v-if="!singers.length">서비스 준비 중 입니다.</p>
          </v-container>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"VoteSingerList",
    props:{
        singers:Array,
    },
    data(){
        return{
          selectSinger:"",
          singerId:"",
          userEmail:""
        }
    },
    methods:{
        checklogin(){
            if (!(this.$cookies.get('auth-token'))){
                this.$alert(" 로그인을 해주세요")
                this.$router.push({name:'Home'})                
            }
        },

        checklocal(){
          if(this.$route.params.local===undefined){
            this.$alert("거주지역을 다시 선택해주세요.")
            this.$router.push({ name: 'VoteLocalSelectView'})
          }
        },

        getuser(){
            const axiosConfig ={
                headers:{
                    token : `${this.$cookies.get('auth-token')}`
                },
            }
            axios.get(SERVER.URL+`/admin/userNow`,axiosConfig)
            .then((reaponse)=>{
              console.log(reaponse)
              if(reaponse.data.u_hasVote===1){
                this.$alert("이미 투표를 하셨습니다.")
                this.$router.push({ name: 'VoteView'})
              }else{
                this.userEmail = reaponse.data.u_email
              }
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        checkDialog(singer){
            this.$confirm(
              {
                message: `"${singer}"`,
                button: {
                  yes: '투표 하기',
                  no: '다시 선택하기',
                },
                callback: confirm => {
                  if (confirm) {
                    const voteDto = {
                        "s_idx": this.singerId,
                        "u_email": this.userEmail,
                        "v_area": this.$route.params.local
                    }
                    axios.post(SERVER.URL+`/vote`,voteDto)
                    .then((res)=>{
                      console.log(res)
                    })
                    .catch((err) => {console.log(err)})                
                    }
                }
              }
            )
        },

        inputsinger(singer){
          this.selectSinger = singer.s_name
          this.singerId = singer.s_idx
          this.checkDialog(singer.s_name)
        },
      },
      created(){
        this.checklogin()
        this.checklocal()
        this.getuser()
      }
}
</script>

<style>

</style>
<template>
<div>
  <v-timeline-item
          color="pink"
          small
          class="cursor_test"
          v-for="singerProgram in singerPrograms"
          :key="singerProgram.bc_idx"
        >
          <v-row class="pt-1">
            <v-col cols="3" class="px-2">
              <strong>{{singerProgram.bc_time}}</strong>
            </v-col>
            <v-col class="pa-0">
              <strong><h2>{{singerProgram.bc_title}}</h2></strong>
              <div>{{singerProgram.bc_company}}</div>
            </v-col>
            <v-col cols="3" class="mr-0 px-0 text-center">
              <v-btn color="error" fab small dark @click="kakaogo(singerProgram.bc_idx)">
                <v-icon>mdi-alarm</v-icon>
              </v-btn>            
            </v-col>
          </v-row>
        </v-timeline-item>
</div>
        
</template>

<script>
import { mapGetters } from 'vuex'
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"SingerScheduleList",
    props:{
      singerPrograms: Array,
    },
    data(){
        return{
            color:"",
            axiosConfig:{
              headers:{
                token : `${this.$cookies.get('auth-token')}`
              }
            },
        }
    },
    computed: {
      ...mapGetters(['isLoggedIn']),
    },
    methods:{
      kakaogo(idx){
        if (this.isLoggedIn) {
          axios.get(SERVER.URL+`/board/tvmsg/${idx}`,this.axiosConfig)
            .then(()=>{
              this.$alert("카카오 메세지를 확인 해 주세요!")
            })
            .catch((err)=>{
                console.error(err)
            })
        } else {
          this.$confirm({
          message: "로그인 해주세요!",
          button: {
              yes: '로그인 하기',
              no: '돌아가기',
            },
            callback: confirm => {
              if (confirm) {
                this.$router.push({ name: 'Login' })
              }
            }
          })
        }
      },
    },
}
</script>

<style>
.cursor_test {
  cursor: pointer;
  }
</style>
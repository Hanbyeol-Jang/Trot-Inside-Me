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
            <v-col cols="3" class="mr-0 px-0">
              <v-btn color="error" fab small dark @click="kakaogo">
                <v-icon>mdi-alarm</v-icon>
              </v-btn>            
            </v-col>
          </v-row>
        </v-timeline-item>
</div>
        
</template>

<script>
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
    methods:{
        kakaogo(){
            axios.get(SERVER.URL+`/board/tvmsg/${this.singerProgram.bc_idx}`,this.axiosConfig)
            .then(()=>{
              this.$alert("카카오 메세지를 확인 해 주세요!")
            })
            .catch((err)=>{
                console.error(err)
            })
        },
    },
}
</script>

<style>
.cursor_test {
  cursor: pointer;
  }
</style>
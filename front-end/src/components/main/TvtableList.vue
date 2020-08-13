<template>
        <v-timeline-item
          :color="color"
          small
          class="cursor_test"
        >
          <v-row class="pt-1">
            <v-col cols="3" class="px-2">
              <strong>{{tvprogram.bc_time}}</strong>
            </v-col>
            <v-col class="pa-0">
              <strong><h2>{{tvprogram.bc_title}}</h2></strong>
              <div>{{tvprogram.bc_company}}</div>
            </v-col>
            <v-col cols="3" class="mr-0 px-0">
              <v-btn color="error" fab small dark @click="kakaogo">
                <v-icon>mdi-alarm</v-icon>
              </v-btn>            
            </v-col>
          </v-row>
        </v-timeline-item>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"TvtableList",
    props:{
        tvprogram: Object,
        tvprogramid: Number
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
            axios.get(SERVER.URL+`/board/tvmsg/${this.tvprogram.bc_idx}`,this.axiosConfig)
            .then((reaponse)=>{
              console.log(reaponse)
              this.$alert("카카오 메세지를 확인 해 주세요!")
            })
            .catch((err)=>{
                console.error(err)
            })
        },

        checkColor(){
            if(this.tvprogramid % 2 === 0){
                this.color = "teal lighten-3"
            }else{
                this.color="pink"
            }
        },
    },
    created(){
        this.checkColor()
    }
}
</script>

<style>
.cursor_test {
  cursor: pointer;
  }
</style>
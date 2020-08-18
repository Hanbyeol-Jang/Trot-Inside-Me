<template>
<div>
    <v-card
      color="#EEEEEE"
      class="my-4"
      raised
      >
      <div class="text-center">
        <v-card-title v-if="fastTimeProgram">현재 보실 수 있는 프로그램은 <br />
            <span class="highlight-program mx-3" color="pink">{{ fastTimeProgram.bc_title }}</span> 입니다.
        </v-card-title>
        <v-card-title v-else>
            현재 방영중인 프로그램이 없습니다.
        </v-card-title>
      </div>
    </v-card>
    <TvtableSearch @search-programs="searchPrograms"/>
    <v-tabs
        color="pink"
        class="d-flex justify-center">
        <v-tab @click="getTvtable"><h4>시간 순으로 보기</h4></v-tab>
        <v-tab @click="getTvtableProgram"><h4>프로그램 별 보기</h4></v-tab>
    </v-tabs>
    <v-timeline
      align-top
      dense
      v-for="tvprogram in Tvprograms" :key="tvprogram.bc_idx"
    >
    <TvtableList :tvprogram="tvprogram" :tvprogramid="Tvprograms.indexOf(tvprogram)"/>
    </v-timeline>
    <ScrollTopButton /> 
</div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'
import _ from 'lodash'
import TvtableSearch from '@/components/main/TvtableSearch.vue'
import TvtableList from '@/components/main/TvtableList.vue'
import ScrollTopButton from '@/components/main/ScrollTopButton'

export default {
    name: "TvtableDetailView",
    components:{
        TvtableList,
        TvtableSearch,
        ScrollTopButton,
    },
    data(){
        return{
            Tvprograms: [],
            now: "",
            fastTimeProgram: {},
        }
    },
    methods:{
        getTime(){
            var date = new Date()
            if(date.getHours() === 0){
                this.now = "00"
            }else if(date.getHours()<10 && date.getHours()>0){
                this.now = "0"+date.getHours()+':'+date.getMinutes()
            }
            else{
                this.now = date.getHours()+':'+date.getMinutes()
            }
        },
        getTvtable(){
            axios.get(`${SERVER.URL}/board/schedule/todayList`)
            .then((response)=>{
              this.Tvprograms = []
              const programs = _.sortBy(response.data,'bc_time')
              for (var i in programs){
                  if (programs[i].bc_time >= this.now){
                      if (programs[i].bc_title ==="신청곡을 불러드립니다 - 사랑의 콜센타"){
                          programs[i].bc_title = "사랑의 콜센타"
                      }
                      this.Tvprograms.push(programs[i])
                  }
              }
              this.fastTimeProgram = this.Tvprograms[0]
            })
            .catch((err)=>{
                console.error(err)
            }) 
        },
        getTvtableProgram(){
            axios.get(`${SERVER.URL}/board/schedule/todayList`)
            .then((response)=>{
                this.Tvprograms = []
                const programs = _.sortBy(response.data,'bc_title','bc_time')
                for (var i in programs){
                    if (programs[i].bc_time >= this.now){
                        if (programs[i].bc_title ==="신청곡을 불러드립니다 - 사랑의 콜센타"){
                            programs[i].bc_title = "사랑의 콜센타"
                        }
                        this.Tvprograms.push(programs[i])
                    }
                }
            })
            .catch((err)=>{
                console.error(err)
            }) 
        },
        searchPrograms(keyword) {
           axios.get(`${SERVER.URL}/board/schedule/todayList`)
                .then(response => {
                    const resultPrograms = response.data.filter(data => data.bc_title.includes(keyword))
                    this.Tvprograms = resultPrograms
                })
            .catch((err)=>{
                console.error(err)
            })
            },
    },
    created(){
        this.getTvtable(),
        this.getTime()
    }
}
</script>


<style scoped>
.highlight-program {
    font-size: 25px !important;
    color: #E91E63;
}
</style>
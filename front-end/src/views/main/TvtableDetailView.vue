<template>
<div>
    <h1>오늘의 트로트 티비 편성표</h1>
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
        v-for="tvprogram in Tvprograms" :key="Tvprograms.indexOf(tvprogram)"
      >
        <TvtableList :tvprogram="tvprogram" :tvprogramid="Tvprograms.indexOf(tvprogram)"/>
      </v-timeline>
</div>
</template>


<script>
import axios from 'axios'
import SERVER from '@/api/drf'
import _ from 'lodash'
import TvtableSearch from '@/components/main/TvtableSearch.vue'
import TvtableList from '@/components/main/TvtableList.vue'

export default {
    name: "TvtableDetailView",
    components:{
        TvtableList,
        TvtableSearch
    },
    data(){
        return{
            Tvprograms:[],
            now: ""
        }
    },
    methods:{
        getTime(){
            var date = new Date()
            if(date.getHours() === 0){
                this.now = "00"
            }else{
                this.now = date.getHours()+':'+date.getMinutes()
            }
        },
        getTvtable(){
            axios.get(`${SERVER.URL}/schedule/todayList`)
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
            })
            .catch((err)=>{
                console.error(err)
            }) 
        },
        getTvtableProgram(){
            axios.get(`${SERVER.URL}/schedule/todayList`)
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
           axios.get(`${SERVER.URL}/schedule/todayList`)
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


<style>

</style>
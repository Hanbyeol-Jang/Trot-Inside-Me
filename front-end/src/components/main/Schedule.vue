<template>
  <div>
    <div class="d-flex justify-space-between mb-3">
          <span><h3>편성표 {{ todayDate }}</h3></span>
          <span @click="goTVDetail">더보기<i class="fas fa-plus ml-1"></i></span>

    </div>
    <v-card
      class="mx-auto"
      shaped
    >
        <v-timeline
          align-top
          dense
          v-for="tvprogram in kprograms" :key="kprograms.indexOf(tvprogram)"
        >
        <TvtableList :tvprogram="tvprogram" :tvprogramid="kprograms.indexOf(tvprogram)"/>
        </v-timeline>
    </v-card>
  </div>
</template>

<script>
import TvtableList from '@/components/main/TvtableList.vue'
import axios from 'axios'
import SERVER from '@/api/drf'
import _ from 'lodash'

var today = new Date()

export default {
    name: 'Schedule',
    components:{
        TvtableList,
    },
    data() {
      return {
        reverse: true,
        date: today.getDate(),
        month: today.getMonth() + 1,
        year: today.getFullYear(),
        todayDate: '',
        kprograms:[],
        Tvprograms:[],
        now: ""
      }
    },
    methods: {
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
              this.Tvprograms = response.data
              this.kprograms = []
              const programs = _.sortBy(response.data,'bc_time')
              for (var i in programs){
                  if (programs[i].bc_time >= this.now){
                      if (programs[i].bc_title ==="신청곡을 불러드립니다 - 사랑의 콜센타"){
                          programs[i].bc_title = "사랑의 콜센타"
                      }
                      this.kprograms.push(programs[i])
                      if (this.kprograms.length===5){
                        break
                      }
                  }
              }
          })
          .catch((err)=>{
              console.error(err)
          }) 
      },
      goTVDetail() {
        this.$router.push({ name: 'TvtableDetailView' })
      },
    },
    created() {
      this.getTime(),
      this.getTvtable()
      if (this.date < 10) {
        this.date = '0' + this.date
      }

      if (this.month < 10) {
        this.month = '0' + this.month
      }

      this.todayDate = this.month +'월 ' + this.date + '일'
    }
}
</script>

<style>

</style>
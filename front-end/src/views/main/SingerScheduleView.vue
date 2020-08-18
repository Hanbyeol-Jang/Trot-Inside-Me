<template>
  <div>
    <v-card
      class="mx-auto mt-5"
      v-if="singerSchedule.length" >
      <vue-calendar
        class="ma-1"
        :show-limit="3"
        :events="events"
        @day-clicked="dayClicked"
        @event-clicked="eventClicked"
      ></vue-calendar>
    </v-card>
    <div v-if="singerSchedule.length" >
      <v-timeline
        align-top
        dense
        v-for="(singerPrograms, key) in indexedSchedule" :key="key"
        :id="key"
        >
        <div>{{ key }}</div>
        <SingerScheduleList :singerPrograms="singerPrograms"/>
      </v-timeline>
    </div>
    <div v-else class="mt-5">아직 등록된 스케줄이 없습니다 :(</div>
    <ScrollTopButton />   
  </div>
</template> 
 
<script>
import { mapState, mapActions } from 'vuex'

import SingerScheduleList from '@/components/main/SingerScheduleList'
import ScrollTopButton from '@/components/main/ScrollTopButton'

export default {
  name: 'SingerScheduleView',
  data() {
    return {
      s_idx: this.$route.params.singerId,
    }
  },
  components:{
    SingerScheduleList,
    ScrollTopButton,
  },
  computed: {
    ...mapState(['singerSchedule', 'indexedSchedule', 'events']),
  },
  methods: {
    ...mapActions(['getSingerSchedule']),
    dayClicked(day) {
      this.year = day.date.getFullYear()
      this.month = day.date.getMonth() + 1
      this.date = day.date.getDate()

      let month = this.changeToString(this.month)
      let date = this.changeToString(this.date)
      let dateId = this.year + '-' + month + '-' + date
      let element = document.getElementById(dateId)
      if (element) {
        element.scrollIntoView()
      }
      
    },
    eventClicked(event) {
      let today = new Date()
      let year = today.getFullYear()

      let month = event.start.slice(5, 7)
      let date = event.start.slice(8, 10)
      let dateId = year + '-' + month + '-' + date
      let element = document.getElementById(dateId)
      if (element) {
        element.scrollIntoView()
      }
    },
    changeToString(number){
      var str = ''
      if (number < 10) {
        str = '0' + number.toString()
      } else {
        str = number.toString()
      }
      return str
    },

  },
  created() {
    this.getSingerSchedule(this.s_idx)
    this.$calendar.eventBus.$on("show-all", events => this.showAll(events));
    this.$calendar.eventBus.$on("day-clicked", day => this.dayClicked(day));
  }
}
</script>

<style scoped>
h1,
h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
<template>
  <div>
    <v-card
      class="mx-auto mt-5">
      <vue-calendar
        class="ma-1"
        :show-limit="3"
        :events="events"

        @show-all="showAll"
        @day-clicked="dayClicked"
        @event-clicked="eventClicked"
        @month-changed="monthChanged"
      ></vue-calendar>
    </v-card>
    <div v-if="singerSchedule.length" >
      <v-timeline
        align-top
        dense
        v-for="(singerPrograms, key) in indexedSchedule" :key="key"
        >
        <div>{{ key }}</div>
        <SingerScheduleList :singerPrograms="singerPrograms"/>
      </v-timeline>
    </div>
    <div v-else>아직 등록된 스케줄이 없습니다 :(</div>
  </div>
</template> 
 
<script>
import { mapState, mapActions } from 'vuex'

import SingerScheduleList from '@/components/main/SingerScheduleList'

export default {
  name: 'SingerScheduleView',
  data() {
    return {
      s_idx: this.$route.params.singerId,
    }
  },
  components:{
    SingerScheduleList,
  },
  computed: {
    ...mapState(['singerSchedule', 'indexedSchedule', 'events']),
  },
  methods: {
    ...mapActions(['getSingerSchedule']),
    showAll(events) {
      // Do something...
      console.log(events)
    },
    dayClicked(day) {
      // Do something...
      console.log(day)
    },
    eventClicked(event) {
      // Do something...
      console.log(event)
    },
    monthChanged(start, end) {
      // Do something...
      console.log(start, end)
    }
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
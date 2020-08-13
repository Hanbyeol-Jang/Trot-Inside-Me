<template>
<<<<<<< HEAD
    <v-card
    class="mx-auto mt-5"
    >
      <vue-calendar
        class="ma-1"
        :show-limit="3"
        @show-all="showAll"
        @day-clicked="dayClicked"
        @event-clicked="eventClicked"
        @month-changed="monthChanged"
      ></vue-calendar>
    </v-card> 
</template>
 
<script>
  export default {
    name: 'SingerScheduleView',
    methods: {
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
=======
  <div>

  </div>
</template>

<script>
export default {
    name: 'SingerScheduleView',
}
</script>

<style>

>>>>>>> BE-Develop
</style>
<template>
  <v-card>
    <v-container class="singerVoteResult mt-4">
        <div class="text-center mt-4">
          <h4>
            이번주 투표 현황 및 순위
          </h4>
        </div>
        <hr>
        <div class="mt-5">{{singerVote.thisWeekTotal}} 중 {{singerVote.thisWeek}} 득표</div>
      <div class="d-flex">
        <div>
          <ejs-accumulationchart id="container1">
            <e-accumulation-series-collection>
                <e-accumulation-series :dataSource='seriesData1' xName='x' yName='y' innerRadius='65%' > </e-accumulation-series>
            </e-accumulation-series-collection>
          </ejs-accumulationchart>
        </div>
        <div class="d-flex">
            {{singerVote.thisWeekRank}} 등
        </div>
      </div>
      <br>
      <hr>
      <hr>
    <div class="text-center mt-4"> 
          <h4>
            지난주 투표 결과 및 순위
          </h4>
    </div>
    <hr>
    <div class="mt-5">{{singerVote.lastWeekTotal}} 중 {{singerVote.lastWeek}} 득표</div>
      <div class="d-flex">
        <div>
          <ejs-accumulationchart id="container2">
            <e-accumulation-series-collection>
                <e-accumulation-series :dataSource='seriesData2' xName='x' yName='y' innerRadius='65%' > </e-accumulation-series>
            </e-accumulation-series-collection>
          </ejs-accumulationchart>
        </div>
        <div class="d-flex">
            {{singerVote.lastWeekRank}} 등
        </div>
      </div>
      <br>
      <hr>
      <hr>
    </v-container>
  </v-card>
</template>

<script>
import {PieSeries} from "@syncfusion/ej2-vue-charts";
import { mapState } from 'vuex'
export default {
    name:"SingerVoteResult",
    computed:{
      ...mapState(['singerVote']),
        seriesData1(){
            return [{ x: '1', y: 0 }, { x: '2', y: 0 },{ x: '3', y: (10-this.singerVote.thisWeekRatio) },{ x: '4', y: this.singerVote.thisWeekRatio }]
            },
        seriesData2(){
            return [{ x: '1', y: 0 }, { x: '2', y: 0 },{ x: '3', y: (10-this.singerVote.lastWeekRatio) },{ x: '4', y: this.singerVote.lastWeekRatio }]
            },
    },
    provide: {
    accumulationchart: [PieSeries]
    }
}
</script>

<style>
.singerVoteResult{
  font-size: 200%;
}

</style>
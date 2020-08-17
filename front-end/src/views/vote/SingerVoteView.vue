<template>
  <div class="mt-5">
    <div class="text-center">
      <h2 class="mt-4">{{ singer.s_name }}</h2>
    </div>
    <v-container class="mt-4">
      <!-- <v-container class="mt-4"> -->
      <div class="d-flex">
        <div>
          <ejs-accumulationchart id="container1">
            <e-accumulation-series-collection>
                <e-accumulation-series :dataSource='seriesData1' xName='x' yName='y' innerRadius='65%' > </e-accumulation-series>
            </e-accumulation-series-collection>
          </ejs-accumulationchart>
        </div>
        <div class="d-flex align-center">
          sdsdsdsd
        </div>
      </div>
      <!-- </v-container> -->
      <div class="d-flex">
        <div>
          <ejs-accumulationchart id="container2">
            <e-accumulation-series-collection>
                <e-accumulation-series :dataSource='seriesData2' xName='x' yName='y' innerRadius='65%' > </e-accumulation-series>
            </e-accumulation-series-collection>
          </ejs-accumulationchart>
        </div>
        <div class="d-flex align-center">
          sdsdsdsd
        </div>
      </div>
    </v-container>
  </div>
</template>

<script>
import {PieSeries} from "@syncfusion/ej2-vue-charts";
import axios from 'axios'
import SERVER from '@/api/drf'
import { mapState, mapActions } from 'vuex'

export default {
    name:"SingerVoteView",
    data(){
        return{
            singerData:{},
            seriesData1: [
           { x: '1', y: 0 }, { x: '2', y: 0 },{ x: '3', y: 1 },{ x: '4', y: 9 },

            ],
            seriesData2: [
           { x: '1', y: 0 }, { x: '2', y: 0 },{ x: '3', y: 3 },{ x: '4', y: 9 },
            ]
        }
    },
    provide: {
      accumulationchart: [PieSeries]
    },
    computed: {
        ...mapState(['singer']),
    },
    methods:{
    ...mapActions(['getSingerDetail']),
        getdata(){
            axios.get(`${SERVER.URL}/voteOverview/${this.$route.params.singerId}`)
            .then((res)=>{
                console.log(res.data)
            })
            .catch((err)=>{
                console.error(err)
            }) 
        },
    },
    created(){
        this.getdata()
    }
}
</script>

<style>
 #container1 {
     height: 200px;
     width: 200px;
 }
 #container2 {
    height: 200px;
    width: 200px;
 }
</style>
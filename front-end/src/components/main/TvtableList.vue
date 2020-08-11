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
              <v-btn color="error" fab small dark>
                <v-icon>mdi-alarm</v-icon>
              </v-btn>            
            </v-col>
          </v-row>
        </v-timeline-item>
</template>

<script>
export default {
    name:"TvtableList",
    props:{
        tvprogram: Object,
        tvprogramid: Number
    },
    data(){
        return{
            color:""
        }
    },
    methods:{
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
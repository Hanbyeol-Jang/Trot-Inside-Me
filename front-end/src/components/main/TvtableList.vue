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
            <v-col>
              <strong>{{tvprogram.bc_title}}</strong>
              <div class="caption">{{tvprogram.bc_company}}</div>
            </v-col>
          </v-row>
        </v-timeline-item>
</template>

<script>
export default {
    name:"TvtableList",
    props:{
        tvprogram:Object,
        tvprogramid:Number
    },
    data(){
        return{
            color:""
        }
    },
    methods:{
        checkColor(){
            if(this.tvprogramid%2===0){
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
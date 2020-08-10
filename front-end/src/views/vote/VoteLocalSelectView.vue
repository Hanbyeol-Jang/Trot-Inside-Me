<template>
    <div class="container">
        <div>
            <h1>거주지역을 선택해주세요.</h1>
        </div>
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <v-container fluid class="p-0">
            <v-row>
              <v-col
                v-for="local in locals" :key="local.id"
                cols="4"
              >
              <div class="cursor_test">
                    <v-btn>{{local}}</v-btn>
              </div>
              </v-col>
            </v-row>
            <v-row >
              <v-col
                v-for="local1 in local2" :key="local1.id"
                cols="4"
              >
              <div class="cursor_test">
                    <v-btn>{{local1}}</v-btn>
              </div>
              </v-col>
            </v-row>
          </v-container>
      </v-col>
    </v-row>
    </div>
</template>

<script>
export default {
    name:"VoteLocalSelectView",
    data(){
        return{
            locals:["서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시"],
            local2:["경기도","강원도","충청도","전라도","경상도","제주도"]
        }
    },
}
</script>

<style>

</style>
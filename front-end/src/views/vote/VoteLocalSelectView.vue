<template>
    <div class="container">
        <div>
            <h1>거주지역을 선택해주세요.</h1>
        </div>
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <div class="d-flex justify-center">
          <h3>{{selectLocal}}</h3>
          </div>
          <v-container fluid class="p-0">
            <v-row >
              <v-col
                v-for="local1 in local2" :key="local1.id"
                cols="4"
                class="pa-0 d-flex justify-center"
              >
              <div class="cursor_test my-5">
                    <v-btn @click="inputCategory(local1)">
                      {{local1}}
                    </v-btn>
              </div>
              </v-col>
            </v-row>
            <v-row >
              <v-col
                v-for="local in locals" :key="local.id"
                cols="6"
                class="pa-0 d-flex justify-center"
              >
              <div class="cursor_test my-5">
                    <v-btn @click="inputCategory(local)">
                      {{local}}
                    </v-btn>
              </div>
              </v-col>
            </v-row>
          </v-container>
      </v-col>
    </v-row>
    <vue-confirm-dialog class="my-class"></vue-confirm-dialog>
    </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name:"VoteLocalSelectView",
    data(){
        return{
            locals:["서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","세종특별자치시"],
            local2:["경기도","강원도","충청남도","충청북도","전라남도","전라북도","경상남도","경상북도","제주도"],
            selectLocal:[],
        }
    },
    methods:{
      checkDialog(local){
        console.log(123456)
          this.$confirm(
            {
              message: `${local} 선택하셨습니다.`,
              button: {
                yes: 'Yes',
                no: '다시 선택하기',
              },
              callback: confirm => {
                if (confirm) {
                  axios.get(SERVER.URL, { headers: { Authorization: `Token ${this.$cookies.get("auth-token")}` }})
                    .then(() => {
                    })
                    .catch((err) => { console.log(err.response.data) })
                }
              }
            }
          )
      },

      inputCategory(local){
        this.selectLocal = local
        this.checkDialog(local)
      },
    },
}
</script>

<style>
</style>
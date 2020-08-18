<template>
    <div class="container">
        <div class="d-flex justify-center">
            <h1>거주지역을 선택해주세요.</h1>
        </div>
    <v-row>
      <v-col cols="12" sm="24" offset-sm="3" class="mx-0 my-0 p-0">
          <div class="d-flex justify-center">
          </div>
          <v-container fluid class="p-0">
            <v-row >
              <v-col
                v-for="local1 in local2" :key="local1.id"
                cols="4"
                class="pa-0 d-flex justify-center"
              >
              <div class="cursor_test my-5">
                    <v-btn @click="inputLocal(local1)">
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
                    <v-btn @click="inputLocal(local)">
                      {{local}}
                    </v-btn>
              </div>
              </v-col>
            </v-row>
          </v-container>
      </v-col>
    </v-row>
    </div>
</template>

<script>
import { mapState, mapGetters } from 'vuex'
// import axios from 'axios'
// import SERVER from '@/api/drf'

export default {
    name:"VoteLocalSelectView",
    data(){
        return{
            locals:["서울특별시","인천광역시","대전광역시","광주광역시","대구광역시","울산광역시","부산광역시","세종특별자치시"],
            local2:["경기도","강원도","충청남도","충청북도","전라남도","전라북도","경상남도","경상북도","제주도"],
            selectLocal:"",
        }
    },
    computed: {
      ...mapState(['user','checkvote']),
      ...mapGetters(['isLoggedIn']),
    },
    methods:{
      checkAuth(){
        if(this.checkvote === null){
          this.$alert("잘못된 접근입니다.")
          this.$router.push({ name: 'Home' })
        }
      },


      checkDialog(local){
        this.$confirm(
          {
            message: `"${local}"`,
            button: {
              yes: '선택 완료',
              no: '다시 선택하기',
            },
            callback: confirm => {
              if (confirm) {
                this.$router.push({ name: 'VoteCreateView', params: { local:`${this.selectLocal}`}})
              }
            }
          }
        )
      },

      inputLocal(local){
        this.selectLocal = local
        this.checkDialog(local)
      },
    },
    created(){
      this.checkAuth()
    }
}
</script>

<style>

</style>
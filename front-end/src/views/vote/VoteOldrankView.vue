<template>
  <div align="center mt-8">

      <div class="d-flex justify-space-between px-4">
        <v-btn rounded 
                    
          color="blue" 
          dark
          class="mt-4" 
          @click="goOld">
            <div class="mx-2"><i class="fas fa-vote-yea mr-2"></i>이번주 결과보기</div>
        </v-btn>
        <v-btn rounded 
                    
          color="pink" 
          dark
          class="mt-4" 
          @click="goVote">
            <div class="mx-2"><i class="fas fa-vote-yea mr-2"></i>투표하기</div>
        </v-btn>
      </div>
    <embed class="my-4 mx-1"                    
      src="https://public.tableau.com/views/trot_15973170885490/1?:language=ko&:display_count=y&:origin=viz_share_link:showVizHome=no&:embed=true"
      width="100%" height="1400px">
    <!-- <VoteMoving /> -->
    <ScrollTopButton /> 
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import axios from 'axios'
import SERVER from '@/api/drf'
import Swal from 'sweetalert2'
// import VoteMoving from '@/components/vote/VoteMoving'
import ScrollTopButton from '@/components/main/ScrollTopButton'
// import Axios from 'axios'


export default {
  name: 'VoteOldrankView',
  computed: {
    ...mapState(['user']),
    ...mapState(['checkvote']),
    ...mapGetters(['isLoggedIn', 'config']),
  },
  components: {
    // VoteMoving,
      ScrollTopButton,

  },
  methods:{
    ...mapActions(['getUser']),
    goVote(){
      if (this.isLoggedIn) {
        axios.get(`${SERVER.URL}/voteCheck/${this.user.u_email}`)
        .then((res)=>{
          if (res.data.hasVote === false) {
            this.$store.commit('SET_VOTE',false);
            this.$router.push({ name: 'VoteLocalSelectView' })
          } else {
            this.$store.commit('SET_VOTE',true);
            this.$confirm({
              message: "이미 이번주 투표 완료하셨습니다.",
              button: {
                no: '닫기',
              }
            })
          }
                })
        .catch((err)=>{
            console.log(err)
        })
      } else {
        this.$confirm({
          message: "로그인 해주세요!",
          button: {
              yes: '로그인 하기',
              no: '돌아가기',
            },
            callback: confirm => {
              if (confirm) {
                this.$router.push({ name: 'Login' })
              }
            }
        })
      }
    },


    goOld(){
      this.$router.push({ name: 'VoteView' })
    },


    loaddata(){
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: '데이터를 불러오는 중입니다!',
        showConfirmButton: false,
        timer: 2000
      })
    }
  },
  created(){
    this.loaddata()
    if (this.isLoggedIn) {
      this.getUser()
      }
  }
  }
</script>

<style>

</style>
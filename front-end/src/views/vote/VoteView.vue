<template>
  <div align="center mt-8">

      <div class="d-flex justify-space-between px-4">
        <img src="@/assets/image/trot_logo.png" alt=""
                    
            width="120px" class="mt-4">
        <v-btn rounded 
                    
          color="pink" 
          dark
          class="mt-4" 
          @click="goVote">
            <div class="mx-4"><i class="fas fa-vote-yea mr-2"></i>투표하기</div>
        </v-btn>
      </div>
    <embed class="my-4 mx-1"                    
      src="https://public.tableau.com/views/trot/1?:language=ko&:display_count=y&publish=yes&:origin=viz_share_link:showVizHome=no&:embed=true"
      width="100%" height="1400px">
    <!-- <VoteMoving /> -->
    <ScrollTopButton /> 
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
// import axios from 'axios'
// import SERVER from '@/api/drf'
// import VoteMoving from '@/components/vote/VoteMoving'
import ScrollTopButton from '@/components/main/ScrollTopButton'


export default {
  name: 'VoteView',
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn', 'config']),
  },
  components: {
    // VoteMoving,
      ScrollTopButton,

  },
  methods:{
    ...mapActions(['getUser']),
    // getuser() {
    //   axios.get(SERVER.URL + SERVER.ROUTES.getUserInfo, this.config)
    //     .then((reaponse)=>{
    //       if(reaponse.data.u_hasVote===0){
    //         this.$router.push({ name: 'VoteLocalSelectView'})
    //       }
    //     })
    //     .catch((err)=>{ console.error(err) })
    // },
    goVote(){
      if (this.isLoggedIn) {
        if (this.user.u_hasVote === 0) {
          this.$router.push({ name: 'VoteLocalSelectView' })
        } else {
          this.$confirm({
            message: "이미 투표하셨습니다!",
            button: {
              no: '닫기',
            }
          })
        }
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
      
    }
  },
  created(){
    if (this.isLoggedIn) {
      this.getUser()
      }
  }
  }
</script>

<style>

</style>
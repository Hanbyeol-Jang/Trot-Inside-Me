<template>
  <div align="center mt-8">
    <v-btn
        color="pink"
        class="mt-4"
        dark
        @click="goVote"
    >
    투표하기
    </v-btn>
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
          message: "로그인이 해주세요!",
          button: {
              yes: '로그인 하기',
              no: '닫기',
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
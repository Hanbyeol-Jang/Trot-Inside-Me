<template>
  <div align="center mt-5">
    <v-btn
        color="pink"
        class="button-bottom"
        dark
        @click="goVote"
    >
    투표하기
    </v-btn>
    <iframe
      src="https://public.tableau.com/views/1_15971485028810/1_1?:language=ko&:display_count=y&publish=yes&:origin=viz_share_link:showVizHome=no&:embed=true"
      width="100%" height="700px">
    </iframe>
    <!-- <VoteMoving /> -->
    <ScrollTopButton /> 
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import axios from 'axios'
import SERVER from '@/api/drf'
// import VoteMoving from '@/components/vote/VoteMoving'
import ScrollTopButton from '@/components/main/ScrollTopButton'


export default {
  name: 'VoteView',
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn']),
  },
  components: {
    // VoteMoving,
      ScrollTopButton,

  },
  methods:{
    ...mapActions(['getUser']),
    getuser() {
      const axiosConfig = {
        headers:{
            token : `${this.$cookies.get('auth-token')}`
        },
      }
      axios.get(SERVER.URL+`/admin/userNow`,axiosConfig)
        .then((reaponse)=>{
          if(reaponse.data.u_hasVote===0){
            this.$router.push({ name: 'VoteLocalSelectView'})
          }
        })
        .catch((err)=>{ console.error(err) })
      },
          goVote(){
            this.$router.push({ name: 'VoteLocalSelectView' })
        }
  },
  created(){
    if (this.isLoggedIn) {
      this.getuser()
      }
  }
  }
</script>

<style>

</style>
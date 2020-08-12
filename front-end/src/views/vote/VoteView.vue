<template>
  <div align="center mt-5">
    <iframe
      src="https://public.tableau.com/views/1_15971485028810/1_1?:language=ko&:display_count=y&publish=yes&:origin=viz_share_link:showVizHome=no&:embed=true"
      width="100%" height="700px">
    </iframe>
    <ScrollTopButton />
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import axios from 'axios'
import SERVER from '@/api/drf'
import ScrollTopButton from '@/components/main/ScrollTopButton'

export default {
  name: 'VoteView',
  computed: {
    ...mapState(['user']),
    ...mapGetters(['isLoggedIn']),
  },
  components: {
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
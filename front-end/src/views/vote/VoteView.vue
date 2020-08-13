<template>
  <div align="center mt-5">
    <iframe class="mt-4 mx-1"                    
      src="https://public.tableau.com/views/trot/1?:language=ko&:display_count=y&publish=yes&:origin=viz_share_link:showVizHome=no&:embed=true"
      width="100%" height="1400px">
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
    ...mapGetters(['isLoggedIn', 'config']),
  },
  components: {
    ScrollTopButton,
  },
  methods:{
    ...mapActions(['getUser']),
    getuser() {
      axios.get(SERVER.URL + SERVER.ROUTES.getUserInfo, this.config)
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
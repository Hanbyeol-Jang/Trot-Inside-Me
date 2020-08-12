<template>
  <div align="center mt-5">
    <iframe
      src="https://public.tableau.com/views/1_15971485028810/1_1?:language=ko&:display_count=y&publish=yes&:origin=viz_share_link:showVizHome=no&:embed=true"
      width="100%" height="700px">
    </iframe>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
    name: 'VoteView',
    methods:{
      getuser(){
          const axiosConfig ={
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
          .catch((err)=>{
              console.error(err)
          })
      },
    },
    created(){
      this.getuser()
    }
}
</script>

<style>

</style>
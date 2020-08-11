<template>
  <div></div>
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
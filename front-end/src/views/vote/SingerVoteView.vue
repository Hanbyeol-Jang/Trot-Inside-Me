<template>
  <div class="mt-5">
    <div class="text-center">
      <h1 class="mt-4">{{ singer.s_name }}</h1>
    </div>
    <SingerVoteResult/>
  </div>
</template>

<script>
import SingerVoteResult from '@/components/vote/SingerVoteResult.vue'
import { mapState, mapActions } from 'vuex'

export default {
    name:"SingerVoteView",
    components:{
      SingerVoteResult,
    },
    data(){
        return{
            singerName:'',
        }
    },
    computed: {
        ...mapState(['singer','singerVote']),
    },
    methods:{
    ...mapActions(['getSingerDetail']),
      checkerr(){
        if(this.singerVote === 0){
          this.$alert("잘못된 접근입니다.")
          this.$router.push({ name: 'Home' })
        }
      }
    },
    created(){
      this.checkerr()
    }
}
</script>

<style>
 #container1 {
     height: 200px;
     width: 200px;
 }
 #container2 {
    height: 200px;
    width: 200px;
 }
</style>
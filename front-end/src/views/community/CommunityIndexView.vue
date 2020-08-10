<template>
  <div>
    <div class="my-5 d-flex justify-sm-space-between">
      <h1>커뮤니티</h1>            
      <v-btn x-large color="primary" @click="createCommunity"><v-icon class="mr-2">mdi-pencil</v-icon>게시글 작성</v-btn>
    </div>
    <br>
      <v-tabs
          color="pink"
          class="d-flex justify-center">
          <v-tab @click="getLikeCommunity" ><h4>인기 순으로 보기</h4></v-tab>
          <v-tab @click="getRecentCommunity" ><h4>최신 순으로 보기</h4></v-tab>
      </v-tabs>
      <br>
      <div class="mt-5">
          <div v-for="community in communities" :key="community.co_idx">
            <router-link :to="{ name: 'CommunityDetailView', params: { communityId: community.co_idx }}">
              <CommunityDetailItem :community="community"/>
            </router-link>  
          </div>
          <div class="my-5 text-center">
            <p class="mt-2" v-if="!communities.length">No results :(</p>
            <infinite-loading v-if="communities.length" @infinite="infiniteHandler" spinner="waveDots"></infinite-loading>
          </div>
      </div>      
  </div>
</template>

<script>
import axios from 'axios'
import CommunityDetailItem from '@/components/community/CommunityDetailItem.vue'
import InfiniteLoading from 'vue-infinite-loading'
import SERVER from '@/api/drf'

export default {
    name: 'CommunityIndexView',
    components: {
      CommunityDetailItem,
      InfiniteLoading,
    },  
    data(){
      return{
        communities:[],
        page: 1,
        communityNum:null,
      }
    },
    methods:{
      checkLogin(){
        if (!(this.$session.get('jwt'))){
          this.$alert(" 로그인을 해주세요")
          this.$router.push({ name: 'Home'})
        }
      },

      getLikeCommunity(){
        this.page = 1
        const options = {params: {no: 1, page: this.page}}
        axios.get(SERVER.URL + `/community/list`, options)
          .then((response) => {
            console.log(response)
            this.communityNum = response.data.data
            this.communities.push(...response.data.data)
          })
          .catch((err) => {console.log(err)})
      },

      getRecentCommunity(){
        this.page = 1
        const options = {params: {no: 2, page: this.page}}
        axios.get(SERVER.URL + `/community/list`, options)
          .then((response) => {
            console.log(response)
            this.communityNum = response.data.data
            this.communities.push(...response.data.data)
          })
          .catch((err) => {console.log(err)})
      },

      createCommunity(){
        this.$router.push({ name: 'CommunityCreateView' })
      },

      infiniteHandler($state){
        if (parseInt(this.communityNum / 5) + 1 >= this.page){
          const options = {params: {_page: this.page+1}}
          axios.get(SERVER.URL + `/community/`, options)
            .then((response) => {
              setTimeout(() => {
                this.page += 1
                this.communities.push(...response.data.data)
                $state.loaded()
              }, 1000);
            })
            .catch((err) => {
              console.log(err)
            })
        } else{
          $state.complete()
        }
      },
    },

    created(){
      this.checkLogin()
      this.getLikeCommunity()
    },
}
</script>

<style>

</style>
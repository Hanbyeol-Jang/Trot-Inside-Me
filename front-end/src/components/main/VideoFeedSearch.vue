<template>
  <div>
    <div class="d-flex flex-row">
        <v-col cols="10" class="row text-center mx-auto">
          <v-text-field
            label="검색어를 입력해주세요."
            single-line
            v-model="keyword" 
           @keypress.enter="searchProgram"
          ></v-text-field>
          <v-btn icon class="d-flex align-self-center">
              <i class="fas fa-search fa-lg my-auto" @click="searchProgram"></i>
          </v-btn>
        </v-col>
    </div>
      <div v-show="searchFlag" class="d-flex flex-row-reverse px-1 mb-2">
        <v-btn rounded
          v-show="searchFlag" 
          color="pink" 
          dark 
          @click="showVideo">
          <div class="mx-2"><i class="fas fa-video mr-2"></i>전체보기</div>   
        </v-btn>
      </div>
  </div>
</template>

<script>
export default {
    name:"VideoFeedSearch",
    data(){
        return{
            searchFlag:false,
            keyword:'',
        }
    },
    methods:{
        searchProgram(){
          if (!this.keyword.trim()){
              this.$alert('검색어를 입력해주세요!')
          } else {
              this.$emit('search-video', this.keyword.trim())
              this.keyword=''
              this.searchFlag=true
          }
        },
        showVideo(){
            this.$emit('video')
            this.searchFlag=false
        },
    },

}
</script>

<style>

</style>
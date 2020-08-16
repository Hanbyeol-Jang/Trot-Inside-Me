<template>
  <div class="d-flex flex-row">
        <v-col cols="10" class="row text-center mx-auto">
          <v-text-field
            label="가수 이름 검색"
            single-line
            v-model="keyword" 
           @keypress.enter="searchSinger"
          ></v-text-field>
          <v-btn icon class="d-flex align-self-center">
              <i class="fas fa-search fa-lg my-auto" @click="searchSinger"></i>
          </v-btn>
        </v-col>
    <!-- <input class="form-control" v-model="keyword" @keypress.enter="searchSinger" type="Search" placeholder="Search Club name" aria-label="Search"> -->
    <!-- <button class="btn btn-info" type="button" @click="searchSinger"><i class="fas fa-search"></i></button> -->
  </div>
</template>

<script>
export default {
    name:"SingerSearch",
    data: function () {
        return {
            keyword: '',
        }
    },
    methods: {
        searchSinger: function () {
          if (!this.keyword.trim()){
              this.$alert('가수 이름을 입력해주세요!')
          } else {
              this.$emit('search-singers', this.keyword.trim())
              this.keyword=''
          }
        }
    },
}
</script>

<style>

</style>
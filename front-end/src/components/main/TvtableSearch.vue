<template>
  <div class="d-flex flex-row">
        <v-col cols="10" class="row text-center mx-auto">
          <v-text-field
            label="티비 방송 검색"
            single-line
            v-model="keyword" 
           @keypress.enter="searchProgram"
          ></v-text-field>
          <v-btn icon class="d-flex align-self-center">
              <i class="fas fa-search fa-lg my-auto" @click="searchProgram"></i>
          </v-btn>
        </v-col>
    <!-- <input class="form-control" v-model="keyword" @keypress.enter="searchProgram" type="Search" placeholder="Search Club name" aria-label="Search"> -->
    <!-- <button class="btn btn-info" type="button" @click="searchProgram"><i class="fas fa-search"></i></button> -->
  </div>
</template>

<script>
export default {
    name:"TvtableSearch",
    data: function () {
        return {
            keyword: '',
        }
    },
    methods: {
        searchProgram: function () {
        if (!this.keyword.trim()){
            this.$alert('프로그램 이름을 입력해주세요!')
        } else {
            this.$emit('search-programs', this.keyword.trim())
            this.keyword=''
        }
        }
    },
}
</script>

<style>

</style>
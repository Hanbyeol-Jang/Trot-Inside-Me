<template>
<div class="container">
    <SingerSearch @search-singers="searchSingers"/>
    <div class="d-flex justify-center">
        <v-btn v-show="searchFlag" @click="showAll" color="pink" dark><h4>전체 가수 보기</h4></v-btn>
    </div>
    <SingerList v-if="singersData.length === 0" :singers="singers"/>
    <SingerList v-else :singers="singersData"/>
</div>
</template>

<script> 
import { mapState, mapActions } from 'vuex'

import SingerList from '@/components/main/SingerList.vue'
import SingerSearch from '@/components/main/SingerSearch.vue'

export default {
    name: "SingerSearchView",
    components:{
        SingerList,
        SingerSearch
    },
    data(){
        return{
            singersData: [],
            searchFlag:false,
        }
    },
    computed: {
        ...mapState(['singers']),
    },
    methods:{
        ...mapActions(['fetchSingers']),
        searchSingers(keyword) {
            const resultSingers = this.singers.filter(data => data.s_name.includes(keyword))
            this.singersData = resultSingers
            this.searchFlag = true
        },
        showAll() {
            this.singersData = this.singers
            this.searchFlag =false
        }
    },
    created(){
        this.fetchSingers()
        this.singersData = this.singers
    }
}
</script>

<style>

</style>
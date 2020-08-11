<template>
<div class="container">
        <div class="d-flex justify-center">
            <h1>응원가수를 선택해주세요.</h1>
        </div>
    <SingerSearch @search-singers="searchSingers"/>
    <div class="d-flex justify-center">
        <v-btn @click="showAll" color="pink" dark><h4>전체 가수 보기</h4></v-btn>
    </div>
    <VoteSingerList v-if="singersData.length === 0" :singers="singers"/>
    <VoteSingerList v-else :singers="singersData"/>
</div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

import VoteSingerList from '@/components/vote/VoteSingerList.vue'
import SingerSearch from '@/components/main/SingerSearch.vue'

export default {
    name: "VoteCreateView",
    components:{
        VoteSingerList,
        SingerSearch
    },
    data(){
        return{
            singersData: [],
        }
    },
    computed: {
        ...mapState(['singers'])
    },
    methods:{
        ...mapActions(['fetchSingers']),
        searchSingers(keyword) {
            const resultSingers = this.singers.filter(data => data.s_name.includes(keyword))
            this.singersData = resultSingers
        },
        showAll() {
            this.singersData = this.singers
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
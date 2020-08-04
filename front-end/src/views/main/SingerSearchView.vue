<template>
<div class="container">
    <SingerSearch @search-singers="searchSingers"/>
    <div class="d-flex justify-center">
        <v-btn @click="fetchSingers" color="pink" dark><h4>전체 가수 보기</h4></v-btn>
    </div>
    <SingerList :singers="singers"/>
</div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

import axios from 'axios'
import SERVER from '@/api/drf'
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
            Singers:[],
        }
    },
    computed: {
        ...mapState(['singers'])
    },
    methods:{
        ...mapActions(['fetchSingers']),
        searchSingers(keyword) {
            axios.get(`${SERVER.URL}/search/singerlist`)
                .then(response => {
                    console.log(response)
                const resultSingers = response.data.filter(data => data.s_name.includes(keyword))
                this.Singers = resultSingers
                })
            .catch((err)=>{
                console.error(err)
            })             
        },
    },
    created(){
        this.fetchSingers()
    }
}
</script>

<style>

</style>
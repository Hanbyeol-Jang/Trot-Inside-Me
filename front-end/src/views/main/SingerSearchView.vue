<template>
<div class="container">
    <SingerSearch @search-singers="searchSingers"/>
    <div class="d-flex justify-center">
        <v-btn @click="getSinger" color="pink"><h4>전체 가수 보기</h4></v-btn>
    </div>
    <SingerList :Singers="Singers"/>
</div>
</template>

<script>
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
    methods:{
        getSinger(){
            axios.get(`${SERVER.URL}/search/singerlist`)
            .then((response)=>{
                console.log(response)
                this.Singers = response.data
            })
            .catch((err)=>{
                console.error(err)
            }) 
        },
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
        this.getSinger()
    }
}
</script>

<style>

</style>
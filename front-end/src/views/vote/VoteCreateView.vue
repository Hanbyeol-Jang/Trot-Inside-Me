<template>
<div class="container">
    <SingerList :singers="singers"/>
</div>
</template>

<script>
import { mapState } from 'vuex'
import SingerList from '@/components/main/SingerList.vue'

export default {
    name: "VoteCreateView",
    components:{
        SingerList,
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
    },
    created(){
        this.fetchSingers()
    }
}
</script>

<style>

</style>
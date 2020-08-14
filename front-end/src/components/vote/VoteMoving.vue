<template>
    <v-btn
        color="pink"
        class="button-bottom"
        dark
        @click="goVote"
    >
    투표하기
    </v-btn>
</template>

<script>
export default {
    name:"VoteMoving",
    methods: {
        goVote(){
            this.$router.push({ name: 'VoteLocalSelectView' })
        }
        },
    }
</script>

<style scoped>
.button-bottom {
    position: fixed;
    right: 5vw;
    bottom: 15vh;
}

</style>
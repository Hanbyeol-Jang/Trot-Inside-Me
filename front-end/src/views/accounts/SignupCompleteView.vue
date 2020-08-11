<template>
  <div>
    <h1>회원가입이<br/>
        완료되었습니다. <br/>
        로그인 해주세요 :)
    </h1>
    <div class="my-2 ml-3">
        <v-btn text color="pink" @click="goLogin">
            <h2>
               로그인 하러가기 <i class="fas fa-chevron-right"></i>
            </h2>
        </v-btn>
    </div>
  </div>
</template>

<script>
export default {
    name: 'SignupCompleteView',
    data() {
        return {
            email: this.$route.params.name
        }
    },
    methods: {
        goLogin() {
            this.$router.push({ name: 'Login' })
            scroll(0, 0)
        },
    },

}
</script>

<style>

</style>
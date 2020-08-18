<template>
    <div class="container">
        <div class="card card0">
            <div class="card card1">
                    <div class="mt-3 mb-2">
                            <div class="text-center">
                                <img src="../../assets/image/rainbow_trot.png" alt=""
                                    width="200px">
                            </div>
                            <h4 class="msg-info pl-0 text-center">로그인해주세요 :)</h4>
                            <div class="row justify-center px-3">
                            <KakaoLogin
                                :api-key="apiKey"
                                :on-success=onSuccess
                                :on-failure=onFailure
                            />
                            </div>
                        </div>
                </div>
        </div>
    </div>
</template>

<script>
import { mapActions } from 'vuex'
import KakaoLogin from 'vue-kakao-login'

export default {
    name: 'Login',
    components: {
        KakaoLogin
    },
    data() {
        return {
            email: '',
            password: '',
            apiKey: '3b520fb7e5e907ebebfde93be5b8a1aa'
        }
    },
    methods: {
        ...mapActions(['kakaoLogin']),
        onSuccess(data){
            this.kakaoLogin(data.access_token)
        },
        onFailure(){
        },
    },
    created() {
        scroll(0, 0)
    },
}
</script>

<style scoped>
body {
    color: #000;
    overflow-x: hidden;
    height: 100%;
    background-image: linear-gradient(to right, #D500F9, #FFD54F);
    background-repeat: no-repeat
}

.msg-info {
    padding-left: 15px;
    margin-bottom: 15px
}
</style>
<template>
    <div class="container px-4 py-5 mx-auto">
        <div class="card card0">
            <div class="d-flex flex-lg-row flex-column-reverse">
                <div class="card card1">
                    <div class="row justify-center my-auto">
                        <div class="col-md-8 col-10 mt-3 mb-2">
                            <div class="text-center">
                                <img src="../../assets/image/rainbow_trot.png" alt=""
                                    width="200px">
                            </div>
                            <h4 class="msg-info pl-0 text-center">로그인해주세요 :)</h4>
                            <div class="row justify-center px-3">
                            <KakaoLogin
                            api-key="3b520fb7e5e907ebebfde93be5b8a1aa"
                            :on-success=onSuccess
                            :on-failure=onFailure
                            />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- <button class="api-btn" onclick="kakaoLogout()">로그아웃</button> -->
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
            token:'',
        }
    },
    methods: {
        ...mapActions(['kakaoLogin']),
        onSuccess(data){
            console.log("kakao sucess")
            this.token = data.access_token
            this.kakaoLogin(data.access_token)
        },
        onFailure(data){
            console.log("kakao failure", data)
        },
    },
    computed: {

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
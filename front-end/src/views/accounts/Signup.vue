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
                            <h4 class="msg-info pl-0 text-center">회원가입 해주세요 :)</h4>
                            <form>
                                <v-text-field
                                    label="닉네임"
                                    placeholder="닉네임 입력"
                                    type="text"
                                    v-model="name"
                                    :error-messages="nameErrors"
                                    @input="$v.name.$touch()"
                                    @blur="$v.name.$touch()"
                                    required
                                    filled
                                    rounded
                                    dense
                                    background-color="#F3E5F5"
                                    height="50px"                          
                                ></v-text-field>

                                <v-text-field
                                    label="이메일"
                                    placeholder="이메일 입력"
                                    type="email"
                                    v-model="email"
                                    :error-messages="emailErrors"
                                    @input="$v.email.$touch()"
                                    @blur="$v.email.$touch()"
                                    required
                                    filled
                                    rounded
                                    dense
                                    background-color="#F3E5F5"
                                    height="50px"                          
                                ></v-text-field>

                                <v-text-field
                                    label="비밀번호"
                                    placeholder="비밀번호 입력"
                                    type="password"
                                    v-model="password"
                                    :error-messages="passwordErrors"
                                    @input="$v.password.$touch()"
                                    @blur="$v.password.$touch()"
                                    filled
                                    rounded
                                    dense
                                    background-color="#F3E5F5"
                                    required
                                    height="50px"
                                ></v-text-field>
                                <v-text-field
                                    label="비밀번호 확인"
                                    placeholder="비밀번호 다시 입력"
                                    type="password"
                                    v-model="repeatPassword"
                                    :error-messages="repeatPasswordErrors"
                                    @input="$v.repeatPassword.$touch()"
                                    @blur="$v.repeatPassword.$touch()"
                                    filled
                                    rounded
                                    dense
                                    background-color="#F3E5F5"
                                    required
                                    height="50px"
                                ></v-text-field>
                                <div class="row justify-center px-3"><v-btn height="45px" block class="btn-color" @click="submit">회원가입</v-btn></div>
                            </form>
                        </div>
                    </div>
                    <div class="bottom text-center mb-5">
                        <div href="#" class="sm-text mx-auto mb-3">회원이신가요?</div>
                        <button class="btn btn-white" @click="goLogin">로그인</button>
                    </div>
                </div>
                <div class="card card2 d-none d-lg-block">
                    <div class="mt-5 mx-md-5 px-md-5 right">
                        <h3 class="text-white">환영합니다!</h3> 
                        <small class="text-white">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</small>
                    </div>
                </div>
            </div>
        </div>
    </div>
  
</template>

<script>
import { validationMixin } from 'vuelidate'
import { required, maxLength, email, minLength, sameAs } from 'vuelidate/lib/validators'


export default {
    mixins: [validationMixin],
    validations: {
        name: { required, maxLength: maxLength(10) },
        email: { required, email },
        password: {
            required,
            minLength: minLength(6)
        },
        repeatPassword: {
            sameAsPassword: sameAs('password')
        }
    },
    name: 'Signup',
    data() {
        return {
            name: '',
            email: '',
            password: '',
            repeatPassword: '',
        }
    },
    methods: {
        goLogin() {
            this.$router.push({ name: 'Login' })
            scroll(0, 0)
        },
        submit() {
            this.$v.$touch()
            if(this.$v.$invalid){
                alert('모든 값을 입력해 주세요!')
            } else {
                this.$router.push({ name: 'SignupCompleteView', params: { name: this.name } })
            }
        },
        clear() {
            this.$v.$reset()
            this.email = ''
            this.password = ''
        },
    },
    computed: {
        nameErrors() {
            const errors = []
            if (!this.$v.name.$dirty) return errors
            !this.$v.name.maxLength && errors.push('닉네임은 최대 10자만 가능합니다.')
            !this.$v.name.required && errors.push('이름을 입력해 주세요.')
            return errors
        },
        emailErrors() {
            const errors = []
            if (!this.$v.email.$dirty) return errors
            !this.$v.email.email && errors.push('이메일 형식을 바르게 입력헤주세요.')
            !this.$v.email.required && errors.push('이메일을 입력해주세요.')
            return errors
        },
        passwordErrors() {
            const errors = []
            if (!this.$v.password.$dirty) return errors
            !this.$v.password.minLength && errors.push(`비밀번호는 최소 ${this.$v.password.$params.minLength.min}자리 이상 입력해야 합니다.`)
            !this.$v.password.required && errors.push('비밀번호를 입력해주세요.')
            return errors
        },
        repeatPasswordErrors() {
            const errors = []
            if (!this.$v.password.$dirty) return errors
            !this.$v.repeatPassword.sameAsPassword && errors.push('비밀번호가 같지 않습니다.')
            return errors
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

input,
textarea {
    background-color: #F3E5F5;
    border-radius: 50px !important;
    padding: 12px 15px 12px 15px !important;
    width: 100%;
    box-sizing: border-box;
    border: none !important;
    border: 1px solid #F3E5F5 !important;
    font-size: 16px !important;
    color: #000 !important;
    font-weight: 400
}

input:focus,
textarea:focus {
    -moz-box-shadow: none !important;
    -webkit-box-shadow: none !important;
    box-shadow: none !important;
    border: 1px solid #D500F9 !important;
    outline-width: 0;
    font-weight: 400
}

button:focus {
    -moz-box-shadow: none !important;
    -webkit-box-shadow: none !important;
    box-shadow: none !important;
    outline-width: 0
}

.card {
    border-radius: 0;
    border: none
}

.card1 {
    width: 50%;
    padding: 40px 30px 10px 30px
}

.card2 {
    width: 50%;
    background-image: linear-gradient(to right, #FFD54F, #D500F9)
}

#logo {
    width: 70px;
    height: 60px
}

.heading {
    margin-bottom: 30px !important
}

::placeholder {
    color: #000 !important;
    opacity: 1
}

:-ms-input-placeholder {
    color: #000 !important
}

::-ms-input-placeholder {
    color: #000 !important
}

.form-control-label {
    font-size: 12px;
    margin-left: 15px
}

.msg-info {
    padding-left: 15px;
    margin-bottom: 15px
}

.btn-color {
    border-radius: 50px;
    color: #fff;
    background-image: linear-gradient(to right, #FFD54F, #D500F9);
    padding: 15px;
    cursor: pointer;
    border: none !important;
    margin-top: 20px
}

.btn-color:hover {
    color: #fff;
    background-image: linear-gradient(to right, #D500F9, #FFD54F)
}

.btn-kakao {
    border-radius: 50px;
    color: black;
    background-color: #FFD54F;
    padding: 15px;
    cursor: pointer;
    border: none !important;
    margin-top: 20px
}

.btn-kakao:hover {
    color: black;
    background-image: linear-gradient(to right, #D500F9, #FFD54F)
}


.btn-white {
    border-radius: 50px;
    color: #D500F9;
    background-color: #fff;
    padding: 8px 40px;
    cursor: pointer;
    border: 2px solid #D500F9 !important
}

.btn-white:hover {
    color: #fff;
    background-image: linear-gradient(to right, #FFD54F, #D500F9)
}
a {
    color: #000
}

a:hover {
    color: #000
}

.bottom {
    width: 100%;
    margin-top: 30px !important
}

.sm-text {
    font-size: 15px
}

@media screen and (max-width: 992px) {
    .card1 {
        width: 100%;
        padding: 10px 10px 10px 10px
    }

    .card2 {
        width: 100%
    }

    .right {
        margin-top: 100px !important;
        margin-bottom: 100px !important
    }
}

@media screen and (max-width: 768px) {
    .container {
        padding: 10px !important
    }

    .card2 {
        padding: 50px
    }

    .right {
        margin-top: 50px !important;
        margin-bottom: 50px !important
    }
}

</style>
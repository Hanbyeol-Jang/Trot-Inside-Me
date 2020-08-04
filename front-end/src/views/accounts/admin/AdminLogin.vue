<template>
  <div class="container">
    <div class="mt-3 mb-2 mx-4">
        <div class="text-center">
            <img src="@/assets/image/rainbow_trot.png" alt=""
                width="200px">
        </div>
        <h4 class="msg-info pl-0 text-center">관리자 로그인</h4>
        <form>
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
          <div class="row justify-center px-3"><v-btn height="45px" block class="btn-color" @click="submit">로그인</v-btn></div>
        </form>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { validationMixin } from 'vuelidate'
import { required, email, minLength } from 'vuelidate/lib/validators'

export default {
    mixins: [validationMixin],
    validations: {
        email: { required, email },
        password: {
            required,
            minLength: minLength(5)
        },
    },
    name: 'AdminLogin',
    data() {
        return {
          email: '',
          password: '',
        }
    },
    methods: {
      ...mapActions(['login']),
      submit() {
          this.$v.$touch()
          if(this.$v.$invalid){
              alert('모든 값을 입력해 주세요!')
          } else {
            let loginData = {
              u_email: this.email,
              u_pw: this.password
            }
            this.login(loginData)
          }
      },
      clear() {
          this.$v.$reset()
          this.email = ''
          this.password = ''
      },
    },
    computed: {
        emailErrors() {
            const errors = []
            if (!this.$v.email.$dirty) return errors
            !this.$v.email.email && errors.push('이메일 형식을 바르게 입력해주세요.')
            !this.$v.email.required && errors.push('이메일을 입력해주세요.')
            return errors
        },
        passwordErrors() {
            const errors = []
            if (!this.$v.password.$dirty) return errors
            !this.$v.password.minLength && errors.push(`비밀번호는 최소 ${this.$v.password.$params.minLength.min}자 이상 입력해야 합니다.`)
            !this.$v.password.required && errors.push('비밀번호를 입력해주세요.')
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
</style>
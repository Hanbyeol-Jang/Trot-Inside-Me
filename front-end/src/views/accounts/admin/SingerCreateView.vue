<template>
  <div class="my-5">
    <v-card
      class="mx-auto mt-5"
      max-width="500"
    >
      <v-card-actions class="d-flex justify-space-between px-4">
        <img src="@/assets/image/trot_logo.png" alt=""
            width="120px" class="mt-2">
        <v-btn rounded 
          color="pink" 
          dark
          class="mt-2" 
          @click="submit">
            <div class="mx-2"><i class="fas fa-plus mr-2"></i>가수 등록</div>
        </v-btn>
      </v-card-actions>
      <v-card-text>
        <form>
          <h3 class="mb-3">가수 이름</h3>
          <v-text-field
            solo
            v-model="name"
            :error-messages="nameErrors"
            :counter="5"
            label="가수 이름을 입력해주세요."
            required
            @input="$v.name.$touch()"
            @blur="$v.name.$touch()"
          ></v-text-field>
          <h3>팬카페</h3>
          <v-radio-group v-model="radios" row>
            <v-radio label="있음" color="pink" value="yes"></v-radio>
            <v-radio label="없음" color="pink" value="no"></v-radio>
          </v-radio-group>
          <v-textarea 
            v-show="radios === 'yes'"
            solo
            v-model="cafeUrl"
            :error-messages="cafeUrlErrors"
            label="가수 팬카페 URL을 입력해 주세요." 
            required
            @input="$v.cafeUrl.$touch()"
            @blur="$v.cafeUrl.$touch()"
            height="80" 
          ></v-textarea>
        </form>
        <h3>가수 이미지</h3>
        <v-img :src="change_image" width="100%" height="100%" v-show="flag"></v-img>
        <v-file-input show-size counter multiple label="가수 이미지를 등록하세요." 
          type="file" id="file" ref="file" @change="singerImage()"></v-file-input>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex'
import { validationMixin } from 'vuelidate'
import { required, maxLength } from 'vuelidate/lib/validators'

import Swal from 'sweetalert2'

export default {
    name: 'SingerCreateView',
    mixins: [validationMixin],
    validations: {
      name: { required, maxLength: maxLength(5) },
      cafeUrl: { required },
    },
    data() {
      return {
        flag: false,
        change_image: '',
        image: null,
        name: '',
        cafeUrl: '',
        radios: 'yes',
      }
    },
    computed: {
      ...mapState(['user']),
      ...mapGetters(['isLoggedIn']),
      nameErrors () {
        const errors = []
        if (!this.$v.name.$dirty) return errors
        !this.$v.name.maxLength && errors.push('최대 5글자 입력이 가능합니다.')
        !this.$v.name.required && errors.push('필수 항목입니다.')
        return errors
      },
      cafeUrlErrors () {
        const errors = []
        if (!this.$v.cafeUrl.$dirty) return errors
        !this.$v.cafeUrl.required && errors.push('필수 항목입니다.')
        return errors
      },
    },
    methods: {
      ...mapActions(['postSinger']),
      submit() {
        if (this.radios === 'no') {
          this.cafeUrl = '미등록'
        }
        this.$v.$touch()
        if(this.$v.$invalid){
            Swal.fire({
              icon: 'error',
              text: '모든 값을 입력해주세요.',
            })
            this.cafeUrl = ''
        } else {
          if (this.radios === 'no') {
            this.cafeUrl = null
          }
          let singerData = new FormData()
          singerData.append('name', this.name)
          if (this.image) {
            singerData.append('img', this.image)
          }
          if (this.cafeUrl) {
            singerData.append('url', this.cafeUrl)
          }
          this.postSinger(singerData)
        }
      },
      singerImage(){
        this.image = this.$refs.file.$refs.input.files[0]
        this.change_image = URL.createObjectURL(this.image)
        this.flag = true
      },
    },
    created() {
      if (this.isLoggedIn && !this.user.u_isAdmin) {
        this.$alert("잘못된 접근입니다.")
        this.$router.push({ name: 'Home' })
      }
    },
}
</script>

<style>

</style>
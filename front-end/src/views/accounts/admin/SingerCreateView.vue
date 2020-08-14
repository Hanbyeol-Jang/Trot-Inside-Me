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
          <v-text-field
            solo
            v-model="name"
            :error-messages="nameErrors"
            :counter="5"
            label="가수 이름"
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
            label="가수 팬카페 URL" 
            required
            @input="$v.cafeUrl.$touch()"
            @blur="$v.cafeUrl.$touch()"
            height="80" 
          ></v-textarea>

          <v-textarea 
            solo 
            v-model="ImgUrl"
            :error-messages="ImgUrlErrors"
            label="가수 이미지 URL"
            required
            @input="$v.ImgUrl.$touch()"
            @blur="$v.ImgUrl.$touch()"
            height="80" 
          ></v-textarea>
        </form>
        <v-img width="100%" height="100%"></v-img>
        <v-file-input 
          show-size 
          counter 
          multiple 
          label="가수 이미지를 등록하세요." 
          type="file" id="file" ref="file"></v-file-input>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { validationMixin } from 'vuelidate'
import { required, maxLength } from 'vuelidate/lib/validators'

export default {
    name: 'SingerCreateView',
    mixins: [validationMixin],
    validations: {
      name: { required, maxLength: maxLength(5) },
      cafeUrl: { required },
      ImgUrl: { required },
    },
    data() {
      return {
        name: '',
        cafeUrl: '',
        ImgUrl: '',
        radios: 'yes',
      }
    },
    computed: {
      nameErrors () {
        const errors = []
        if (!this.$v.name.$dirty) return errors
        !this.$v.name.maxLength && errors.push('최대 5글자 입력이 가능합니다.')
        !this.$v.name.required && errors.push('가수 이름을 입력해 주세요.')
        return errors
      },
      cafeUrlErrors () {
        const errors = []
        if (!this.$v.cafeUrl.$dirty) return errors
        !this.$v.cafeUrl.required && errors.push('URL을 입력해 주세요.')
        return errors
      },
      ImgUrlErrors () {
        const errors = []
        if (!this.$v.ImgUrl.$dirty) return errors
        !this.$v.ImgUrl.required && errors.push('URL을 입력해 주세요.')
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
            alert('모든 값을 입력해 주세요!')
        } else {
          if (this.radios === 'no') {
            this.cafeUrl = ''
          }
          let singerData = {
              s_cafeUrl: this.cafeUrl,
              s_img: this.ImgUrl,
              s_name: this.name,
            }
          this.postSinger(singerData)
        }
      }
    },
}
</script>

<style>

</style>
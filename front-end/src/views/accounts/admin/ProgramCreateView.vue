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
          <div class="mx-2"><i class="fas fa-plus mr-2"></i>프로그램 등록</div>
      </v-btn>
    </v-card-actions>
    <v-card-text>
      <form>
        <h3 class="mb-3">프로그램명</h3>
        <v-text-field
          solo
          v-model="name"
          :error-messages="nameErrors"
          :counter="20"
          label="프로그램명을 입력해주세요."
          required
          @input="$v.name.$touch()"
          @blur="$v.name.$touch()"
        ></v-text-field>
        <h3 class="mb-3">편성표 URL</h3>
        <v-textarea 
          solo
          v-model="scheduleUrl"
          :error-messages="scheduleUrlErrors"
          label="편성표 URL을 입력해주세요." 
          required
          @input="$v.scheduleUrl.$touch()"
          @blur="$v.scheduleUrl.$touch()"
          height="80" 
        ></v-textarea>
      </form>
      <h3>프로그램 이미지</h3>
      <v-img :src="change_image" width="100%" height="100%" v-show="flag"></v-img>
      <v-file-input show-size counter multiple label="가수 이미지를 등록하세요." 
        type="file" id="file" ref="file" @change="programImage()"></v-file-input>
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
    name: 'ProgramCreateView',
    mixins: [validationMixin],
    validations: {
      name: { required, maxLength: maxLength(20) },
      scheduleUrl: { required },
    },
    data() {
      return {
        flag: false,
        change_image: '',
        image: null,
        name: '',
        scheduleUrl: '',
      }
    },
    computed: {
      ...mapState(['user']),
      ...mapGetters(['isLoggedIn']),
      nameErrors () {
        const errors = []
        if (!this.$v.name.$dirty) return errors
        !this.$v.name.maxLength && errors.push('최대 20글자 입력이 가능합니다.')
        !this.$v.name.required && errors.push('프로그램을 입력해 주세요.')
        return errors
      },
      scheduleUrlErrors () {
        const errors = []
        if (!this.$v.scheduleUrl.$dirty) return errors
        !this.$v.scheduleUrl.required && errors.push('URL을 입력해 주세요.')
        return errors
      }, 
    },
    methods: {
      ...mapActions(['postProgram']),
      submit() {
        this.$v.$touch()
        if(this.$v.$invalid){
            Swal.fire({
              icon: 'error',
              text: '모든 값을 입력해주세요.',
            })
        } else {
          let programData = new FormData()
          programData.append('name', this.name)
          if (this.image) {
            programData.append('img', this.image)
          }
          if (this.scheduleUrl) {
            programData.append('url', this.scheduleUrl)
          }
          this.postProgram(programData)
        }
      },
      programImage(){
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

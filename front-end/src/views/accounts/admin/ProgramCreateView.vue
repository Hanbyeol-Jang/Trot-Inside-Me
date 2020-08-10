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
        <v-text-field
          solo
          v-model="name"
          :error-messages="nameErrors"
          :counter="20"
          label="프로그램 명"
          required
          @input="$v.name.$touch()"
          @blur="$v.name.$touch()"
        ></v-text-field>
        <v-textarea 
          solo
          v-model="scheduleUrl"
          :error-messages="scheduleUrlErrors"
          label="편성표 URL" 
          required
          @input="$v.scheduleUrl.$touch()"
          @blur="$v.scheduleUrl.$touch()"
          height="80" 
        ></v-textarea>
      </form>
    </v-card-text>
  </v-card>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import { validationMixin } from 'vuelidate'
import { required, maxLength } from 'vuelidate/lib/validators'

export default {
    name: 'ProgramCreateView',
    mixins: [validationMixin],
    validations: {
      name: { required, maxLength: maxLength(20) },
      scheduleUrl: { required },
    },
    data() {
      return {
        name: '',
        scheduleUrl: '',
      }
    },
    computed: {
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
            alert('모든 값을 입력해 주세요!')
        } else {
          let programData = {
              a_broadName: this.name,
              a_broadUrl: this.scheduleUrl,
            }
          this.postProgram(programData)
        }
      }
    },
}
</script>

<style>

</style>

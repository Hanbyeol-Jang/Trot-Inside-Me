<template>
  <div>
    <div class="row d-flex align-center">
      <div class="col-2">
        <v-avatar color="indigo"><v-icon dark>mdi-account-circle</v-icon></v-avatar>
        <!-- <b-avatar variant="secondary" :src="profileImage" size="3.5rem"></b-avatar> -->
      </div>
      <div class="col-10 pl-2">
        <div class="d-flex flex-wrap justify-content-between">
          <div class="d-flex align-center">
            <h3 class="font-weight-bold">{{ comment.cr_name }}</h3>
            <!-- <pre class="text-secondary ml-3">{{ updateTime }}</pre> -->
          </div>
          <div v-if="isAuth" class="ml-auto">
            <v-btn depressed outlined color="error" @click="deleteComment">삭제</v-btn>
          </div>
        </div>
      </div>
    </div>
        <div class="ml-10 pl-10">
          <h1 class=""> {{ comment.cr_content }}</h1>
        </div>
    <br>
    <hr>
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

export default {
  name: 'CommentListItem',
  props: {
    comment: Object,
  },
  data(){
    return {
      isAuth: false,
      axiosConfig :{
          headers:{
              token : `${this.$cookies.get('auth-token')}`
          },
      }
    }
  },
  computed: {
    profileImage(){
      return SERVER.URL + this.comment.cr_profileImg
    },
    updateTime(){
      return this.comment.updated_at.slice(0,10)
    },
  },

  methods: {
    deleteComment(){
      const idx = this.comment.cr_idx
      this.$confirm(
        {
          message: `삭제하시겠습니까?`,
          button: {
            yes: '삭제하기',
            no: '아니요',
          },
          callback: confirm => {
            if (confirm) {
                  this.$emit('delete-comment2',idx)
            }
          }
        }
      )
    },

    checkAuth(){
      axios.get(SERVER.URL+`/user/getUserInfo`,this.axiosConfig)
      .then((reaponse)=>{
          const currentUser = reaponse.data.u_name
          if(Number(reaponse.data.u_isAdmin)){
              this.isAuth = true
          }else{
              if (this.comment.cr_name === currentUser){
                  this.isAuth = true
              }
          }
      })
      .catch((err)=>{
          console.error(err)
      })
    }
  },
  mounted(){
    this.checkAuth()
  },
}
</script>

<style>

</style>
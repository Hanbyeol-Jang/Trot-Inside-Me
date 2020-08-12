<template>
  <div v-show="!isdelete" class="row">
    <div class="col-2">
      <v-avatar color="indigo"><v-icon dark>mdi-account-circle</v-icon></v-avatar>
      <!-- <b-avatar variant="secondary" :src="profileImage" size="3.5rem"></b-avatar> -->
    </div>
    <div class="col-10 pl-2">
      <div class="d-flex flex-wrap justify-content-between">
        <div class="d-flex">
          <h6 class="font-weight-bold">{{ comment.cr_name }}</h6>
          <!-- <pre class="text-secondary ml-3">{{ updateTime }}</pre> -->
        </div>
        <div v-if="isAuth" class="ml-auto">
          <button class="btn btn-link" @click="deleteComment">삭제</button>
        </div>
      </div>
      <span class=""> {{ comment.cr_content }}</span>
    </div>
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
      isdelete:false,
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
      return SERVER.URL + this.comment.user.profile_image
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
                  this.isdelete = true
                  this.$emit('delete-comment2',idx)
            }
          }
        }
      )
    },

    checkAuth(){
      axios.get(SERVER.URL+`/admin/userNow`,this.axiosConfig)
      .then((reaponse)=>{
          const currentUser = reaponse.data.u_name
          if(Number(reaponse.data.u_isAdmin)){
              this.isAuth = true
          }else{
              if (this.communityUser === currentUser){
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
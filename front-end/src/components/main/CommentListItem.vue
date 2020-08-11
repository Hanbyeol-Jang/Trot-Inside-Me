<template>
  <div class="row">
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
      isAuth: null,
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
      this.$confirm(
        {
          message: `삭제하시겠습니까?`,
          button: {
            yes: 'Yes',
            no: 'No',
          },
          callback: confirm => {
            if (confirm) {
              axios.delete(SERVER.URL + `/community/comments/${this.comment.id}/`, { headers: { Authorization: `Token ${this.$cookies.get("auth-token")}` }})
                .then(() => {
                  this.$emit('comment-delete', this.comment.id)
                })
                .catch((err) => { console.log(err.response.data) })
            }
          }
        }
      )
    },
    checkAuth(){
      if (this.$cookies.get("auth-token")){
        axios.get(SERVER.URL + '/accounts/', { headers: { Authorization: `Token ${this.$cookies.get("auth-token")}` }})
          .then((response) => {
            if (response.data.data.id === this.comment.user.id){
              this.isAuth = true
            } else{
              this.isAuth = false
            }
              })
          .catch((err) => { console.log(err.response.data) })
        }
    }
  },
  mounted(){
    // this.checkAuth()
  },
}
</script>

<style>

</style>
<template>
  <div>
    <div class="row d-flex align-center">
      <div class="col-2">
        <v-avatar @click="goUserDetail(comment.cr_email)">
          <img v-if="profileImage" :src="profileImage" alt="User">
          <img v-else src="@/assets/image/user_default.png" alt="User">
        </v-avatar>
      </div>
      <div class="col-10 pl-2">
        <div class="d-flex flex-wrap justify-content-between">
          <div class="d-flex align-center">
            <h3 class="font-weight-bold" @click="goUserDetail(comment.cr_email)">{{ comment.cr_name }}</h3>
            <pre class="text-secondary ml-3">{{ updateTime }}</pre>
          </div>
          <div v-if="currentUser.u_isAdmin === 1" class="ml-auto">
            <v-btn depressed outlined color="error" @click="deleteComment">삭제</v-btn>
          </div>
          <div  v-else-if="comment.cr_name === currentUser.u_name" class="ml-auto">
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
export default {
  name: 'CommentListItem',
  props: {
    comment: Object,
    currentUser: Object,
  },
  computed: {
    profileImage(){
      return this.comment.cr_profileImg
    },
    updateTime(){
      return this.comment.cr_date.slice(0,10)
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
    goUserDetail(userId) {
      this.$router.push({ name: 'UserDetailView', params: { userId: userId }})
    },
  },
}
</script>

<style>

</style>
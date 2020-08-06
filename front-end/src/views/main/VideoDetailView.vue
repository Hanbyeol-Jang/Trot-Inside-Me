<template>
  <v-card
    class="mx-auto my-10"
    max-width="700"
  >
    <v-container fluid>
      <v-row dense>
          <v-card>
          <VideoDetailItem :video="video"/>
            <v-card-actions class="d-flex justify-space-around">

              <div @click="showLikeChange()">
                <v-btn icon>
                  <v-icon v-show="!showLike">mdi-heart</v-icon>
                  <v-icon v-show="showLike" color="red">mdi-heart</v-icon>
                  {{likeCnt}}
                </v-btn>
              </div>
              <div  @click="showCommentsChange()">
                <v-btn icon>
                  <v-icon v-show="!showComments">mdi-message-text</v-icon>
                  <v-icon v-show="showComments" large color="blue darken-2">mdi-message-text</v-icon>
                  {{commentCnt}}
                </v-btn>
              </div>

            </v-card-actions>
            <CommentList v-show="showComments" :id="id"/>
          </v-card>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
import axios from 'axios'
import VideoDetailItem from '@/components/main/VideoDetailItem.vue'
import CommentList from '@/components/main/CommentList.vue'
import SERVER from '@/api/drf'

export default {
  name: 'VideoDetailView',
  components: {
    VideoDetailItem,
    CommentList,
  },
  data(){
    return {
      video: [],
      likeCnt:'',
      commentCnt:'',
      showLike: false,
      showComments: false,
      email:"",
      id:this.$route.params.videoId,
      type:''
    }
  },
  methods: {
    getuser(){
      const axiosConfig = {
          headers:{
          token: `${this.$cookies.get('auth-token')}`
          },
      }
      axios.get(SERVER.URL+"/admin/test",axiosConfig)
      .then((response)=>{
        this.email = response.data.u_email
      })
      .catch((err) => {console.log(err)})
    },

    getLikeCnt(){
      axios.get(SERVER.URL+`/board/good/${this.type}/${this.$route.params.videoId}`)
      .then((response)=>{
        console.log(response)
        this.likeCnt = response.data.like_count

      })
      .catch((err) => {console.log(err)})
    },

    showLikeChange(){
      axios.post(SERVER.URL+'/',`/${this.id}/`,this.axiosConfig)
      .then((response)=>{
        this.showLike = response.data.data
      })
      .catch((err) => {console.log(err.response.data)})
    },

    getVideo(){
      axios.get(SERVER.URL +`/singer/videos/${this.$route.params.videoId}`)
        .then((response) => {
          console.log(response)
          this.video = response.data
          this.type = response.data.b_type
          this.getLikeCnt()
          
        })
        .catch((err) => {console.log(err.response.data)})
    },

    showCommentsChange(){
      this.showComments = !this.showComments
    },
  },
  created(){
    this.getuser()
    this.getVideo()
  },
}
</script>

<style>

</style>
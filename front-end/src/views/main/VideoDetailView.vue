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
      // id : this.$router.params.videoId,
      id:1,
      axiosConfig : {
          headers:{
          Authorization : `Token ${this.$cookies.get('auth-token')}`
          },
      }
    }
  },
  methods: {
    showLikeChange(){
      axios.post(SERVER.URL+'/',`/${this.id}/`,this.axiosConfig)
      .then((response)=>{
        this.showLike = response.data.data
        this.likeCnt = response.data.data
      })
      .catch((err) => {console.log(err.response.data)})
    },
    getVideo(){
      axios.get(SERVER.URL + `/${this.id}/`,this.axiosConfig)
        .then((response) => {
            this.video = response.data.data
            this.likeCnt = response.data.data
            this.commentCnt = response.data.data
            this.showLike = response.data.data
        })
        .catch((err) => {console.log(err.response.data)})
    },
    showCommentsChange(){
      this.showComments = !this.showComments
    },
  },
  created(){
    // this.getVideo()
  },
}
</script>

<style>

</style>
<template>
  <div class="home">
    <div class="text-center">
      <img src="../../assets/image/rainbow_trot.png" alt=""
        width="250px">
    </div>
    <Schedule />
    <div class="mt-5">
        <h3>피드</h3>
      </div>
    <div class="text-center mt-5">
      <v-btn rounded color="pink darken-2 mr-3" dark @click="showVideo">
        <i class="fas fa-video mr-2"></i> 동영상
      </v-btn>
      <v-btn rounded color="pink darken-2 ml-3" dark @click="showArticle">
        <i class="fas fa-newspaper mr-2"></i>뉴스기사
      </v-btn>
    </div>
    <div class="mt-4">
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Schedule from '../../components/main/Schedule'

export default {
  name: 'Home',
  components: {
    Schedule,
  },
  data() {
    return {
      videoTab: true,
      articleTab: false,
    }
  },
  methods: {
    showVideo() {
      this.videoTab = true,
      this.articleTab = false
    },
    showArticle() {
      this.videoTab = false,
      this.articleTab = true
    },
  },
}
</script>

<template>
  <v-card
      hover 
      @click="goDetail"
  >
    <img v-if="videoThumbnail" :src="videoThumbnail" alt="video thumbnail" 
        width="100%"/>
    <img v-else src="@/assets/image/content_default.png" alt="video thumbnail default"
        width="100%"/>
    <v-card-title>
    {{ videoTitle }}
    </v-card-title>

    <v-row
      align="center"
      justify="end"
      class="mx-4 pb-3 d-flex justify-space-between"
    >
      <div>{{ videoDate }}</div>
      <div>
        <v-icon>mdi-heart</v-icon>
        <span class="subheading mx-2">{{ videoLikes }}</span>
        <v-icon>mdi-message-text</v-icon>
        <span class="subheading mx-2">{{ videoReply }}</span>      
      </div>
    </v-row>
  </v-card>
</template>

<script>
export default {
    name: 'VideoFeedItem',
    props: {
        video: Object,
    },
    computed: {
        videoTitle() {
            return this.video.b_title
        },
        videoThumbnail() {
            return this.video.b_thumbnail
        },
        videoLikes() {
            return this.video.good_cnt
        },
        videoReply() {
            return this.video.br_cnt
        },
        videoDate() {
            return this.video.b_date
        },
        videoId() {
            return this.video.b_idx
        }
    },
    methods: {
        goDetail() {
            this.$router.push({ name: 'VideoDetailView', params: { videoId: this.videoId } })
        },
    }
}
</script>

<style>

</style>
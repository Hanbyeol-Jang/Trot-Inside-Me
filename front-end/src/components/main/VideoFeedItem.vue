<template>
<<<<<<< HEAD
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
    class="mr-5 pb-3"
    >
    {{ videoDate }}
    <v-icon class="mr-1">mdi-heart</v-icon>
    <span class="subheading mr-2">{{ videoLikes }}</span>
    <span class="mr-1">·</span>
    <i class="fas fa-comment fa-lg mr-1"></i>
    <span class="subheading mr-2">{{ videoReply }}</span>
    </v-row>
  </v-card>
=======
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
        class="mr-5 pb-3"
        >
        {{ videoDate }}
        <v-icon class="mr-1">mdi-heart</v-icon>
        <span class="subheading mr-2">{{ videoLikes }}</span>
        <span class="mr-1">·</span>
        <i class="fas fa-comment fa-lg mr-1"></i>
        <span class="subheading mr-2">{{ videoReply }}</span>
        </v-row>
    </v-card>
>>>>>>> BE-Develop
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
<<<<<<< HEAD
            return this.video.good_cnt
        },
        videoReply() {
            return this.video.br_cnt
=======
            return this.video.b_good
        },
        videoReply() {
            return this.video.b_reply
>>>>>>> BE-Develop
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
<template>
    <v-card
        hover 
        @click="goDetail"
    >
        <iframe :src="videoThumbnail"
            frameborder='no' scrolling='no' marginwidth='0' marginheight='0' 
            WIDTH=100% allow='autoplay' allowfullscreen></iframe>

        <v-card-title>
        {{ videoTitle }}
        </v-card-title>

        <v-row
        align="center"
        justify="end"
        class="mr-5 pb-3"
        >
        <v-icon class="mr-1">mdi-heart</v-icon>
        <span class="subheading mr-2">256</span>
        <span class="mr-1">·</span>
        <i class="fas fa-comment fa-lg mr-1"></i>
        <span class="subheading mr-2">256</span>
        <span class="mr-1">·</span>
        <v-icon class="mr-1">mdi-share-variant</v-icon>
        <span class="subheading">45</span>
        </v-row>
    </v-card>
</template>

<script>
export default {
    name: 'VideoFeedItem',
    props: {
        video: Object,
        videoId: Number,
    },
    computed: {
        videoTitle() {
            return this.video.v_title
        },
        videoThumbnail() {
            return this.video.v_url
        },
    },
    methods: {
        goDetail() {
            this.$router.push({ name: 'VideoDetailView', params: { videoId: this.videoId, video: this.video } })
        },
    }
}
</script>

<style>

</style>
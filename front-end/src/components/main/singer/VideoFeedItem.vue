<template>
    <v-card
        hover 
        @click="goDetail"
    >
        <img :src="videoThumbnail" alt="video thumbnail" 
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
        <span class="subheading mr-2">{{ videoReplysss }}</span>
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
            return this.video.b_good
        },
        videoReply() {
            return this.video.b_reply
        },
        videoDate() {
            return this.video.b_date
        }
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
<template>
  <div>
    <VideoFeed :singerId="singerId"/>
  </div>
</template>

<script>
import VideoFeed from '@/components/main/VideoFeed'

export default {
    name: "VideoListView",
    data() {
      return {
      }
    },
    components: {
      VideoFeed,
    },
    computed: {
      singerId() {
        return parseInt(this.$route.params.singerId)
      }
    },
}
</script>

<style>

</style>
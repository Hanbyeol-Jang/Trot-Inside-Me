<template>
  <div>
    <VideoFeed :singerId="singerId"/>
  </div>
</template>

<script>
import VideoFeed from '@/components/main/singer/VideoFeed'

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
        return this.$route.params.singerId
      }
    }
}
</script>

<style>

</style>
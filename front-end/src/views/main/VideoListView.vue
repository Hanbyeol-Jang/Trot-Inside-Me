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
<<<<<<< HEAD
        return parseInt(this.$route.params.singerId)
=======
        return this.$route.params.singerId
>>>>>>> BE-Develop
      }
    },
}
</script>

<style>

</style>
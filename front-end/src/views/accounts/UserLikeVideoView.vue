<template>
  <div>
    <LikeVideoFeed :userId="userId" />
  </div>
</template>

<script>
import LikeVideoFeed from '@/components/accounts/LikeVideoFeed'

export default {
    name: 'UserLikeVideoView',
    data() {
      return {
        userId: parseInt(this.$route.params.userId),
      }
    },
    components: {
      LikeVideoFeed,
    },
}
</script>

<style>

</style>
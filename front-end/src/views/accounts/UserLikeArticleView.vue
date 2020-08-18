<template>
  <div>
    <LikeArticleFeed :userId="userId" />
  </div>
</template>

<script>
import LikeArticleFeed from '@/components/accounts/LikeArticleFeed'

export default {
    name: 'UserLikeArticleView',
    data() {
      return {
        userId: parseInt(this.$route.params.userId),
      }
    },
    components: {
      LikeArticleFeed,
    },
}
</script>

<style>

</style>
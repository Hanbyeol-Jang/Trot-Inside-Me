<template>
  <div>
    <ArticleFeed :singerId="singerId"/>
  </div>
</template>

<script>
import ArticleFeed from '@/components/main/ArticleFeed'

export default {
    name: "ArticleListView",
    data() {
      return {
      }
    },
    components: {
      ArticleFeed,
    },
    computed: {
      singerId() {
<<<<<<< HEAD
        return parseInt(this.$route.params.singerId)
=======
        return this.$route.params.singerId
>>>>>>> BE-Develop
      }
    }
}
</script>

<style>

</style>
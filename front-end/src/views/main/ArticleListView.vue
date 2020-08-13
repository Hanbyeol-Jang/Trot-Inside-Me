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
        return parseInt(this.$route.params.singerId)
      }
    }
}
</script>

<style>

</style>
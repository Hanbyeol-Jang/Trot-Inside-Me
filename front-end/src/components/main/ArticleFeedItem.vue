<template>
    <v-card
      color="#EEEEEE"
      v-if="article.b_title"
      class="my-4"
      @click="goArticleoDetail"
      shaped
      >
      <v-card-title class="">{{ article.b_title }}</v-card-title>
      <v-card-subtitle>{{ article.b_company }}</v-card-subtitle>
      <v-row
        align="center"
        justify="end"
        class="mx-4 pb-3 d-flex justify-space-between"
      >
        <div>{{ article.b_date }}</div>
        <div>
          <v-icon v-if="article.good_flag" color="pink">mdi-heart</v-icon>
          <v-icon v-else>mdi-heart</v-icon>
          <span class="subheading mx-2">{{ article.good_cnt }}</span>
          <v-icon>mdi-message-text</v-icon>
          <span class="subheading mx-2">{{ article.br_cnt }}</span>      
        </div>
      </v-row>
    </v-card>
</template>

<script>
export default {
    name: 'ArticleFeedItem',
    props: {
        article: Object,
    },
    computed: {
        articleId() {
            return this.article.b_idx
        }
    },
    methods: {
        goArticleoDetail() {
            this.$router.push({ name: 'ArticleDetailView', params: { articleId: this.articleId }})
        }
    }
}
</script>

<style>

</style>
<template>
    <v-card
        color="#385F73"
        dark
        v-if="article.b_title"
        class="mb-3"
        @click="goArticleoDetail"
        >
        <v-card-title class="">{{ article.b_title }}</v-card-title>
        <v-card-subtitle>{{ article.b_company }}</v-card-subtitle>
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
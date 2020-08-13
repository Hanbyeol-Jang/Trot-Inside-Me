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
        class="mr-5 pb-3"
        >
        {{ article.b_date }}
        <v-icon class="mr-1">mdi-heart</v-icon>
        <span class="subheading mr-2">{{ article.good_cnt }}</span>
        <span class="mr-1">·</span>
        <i class="fas fa-comment fa-lg mr-1"></i>
        <span class="subheading mr-2">{{ article.br_cnt }}</span>
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
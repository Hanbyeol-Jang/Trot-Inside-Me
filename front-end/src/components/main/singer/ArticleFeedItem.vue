<template>
    <v-card
        color="#385F73"
        dark
        v-if="articleTitle"
        class="mb-3"
        >
        <v-card-title class="">{{ articleTitle }}</v-card-title>

        <v-card-subtitle>{{ articleUrl }}</v-card-subtitle>
<!-- 
        <v-card-actions>
        <v-btn text>Listen Now</v-btn>
        </v-card-actions> -->
    </v-card>
  
</template>

<script>
export default {
    name: 'ArticleFeedItem',
    props: {
        article: Object,
    },
    computed: {
        articleTitle() {
            return this.article.a_title
        },
        articleUrl() {
            return this.article.a_url
        },
    },

}
</script>

<style>

</style>
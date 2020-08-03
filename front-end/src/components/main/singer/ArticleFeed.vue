<template>
  <v-container> 
        <v-row dense>
            <v-col cols="12">
                <div v-for="article in articles" :key="articles.indexOf(article)">
                    <ArticleFeedItem :article="article"/>
                </div>
            </v-col>
      </v-row>
  </v-container>
</template>

<script>
import axios from 'axios'
import SERVER from '@/api/drf'

import ArticleFeedItem from './ArticleFeedItem'

export default {
    name: 'ArticleFeed',
    data() {
        return {
            items: [
                {
                color: '#1F7087',
                src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
                title: 'Supermodel',
                artist: 'Foster the People',
                },
                {
                color: '#952175',
                src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
                title: 'Halcyon Days',
                artist: 'Ellie Goulding',
                }
            ],
            articles: [],

        }
    },
    components: {
        ArticleFeedItem,
    },
    props: {
        singer: Object,
    },
    methods: {
        fetchArticleData() {
            axios.get(`${SERVER.URL}/board/ArticleAllList`)
        .then(res => {
          this.articles = res.data
        })
        .catch(err => console.log(err))
        },
    },
    created() {
      this.fetchArticleData()
    },

}
</script>

<style>

</style>
<template>
      <v-card
          color="#EEEEEE"
          v-if="article.b_title"
          class="my-4"
          shaped
          >
          <v-card-title class="">{{ article.b_title }}</v-card-title>
          <v-card-subtitle>{{ article.b_company }}</v-card-subtitle>
          <a :href="article.b_url">기사 보러가기</a>
          <v-row
          align="center"
          justify="end"
          class="mr-5 pb-3"
          >
          {{ article.b_date }}
          </v-row>
          <img :src="article.b_thumbnail" alt="">
      </v-card>
</template>

<script>
export default {
    name:"ArticleDetailItem",
    components:{
    },
    props:{
        article:Object,
    },
    methods:{
      goarticle(){
        
      },
    }
}
</script>

<style>

</style>
<template>
    <div class="pa-2">
      <h2 class="px-2">{{ article.b_title }}</h2>
      <v-card
        color="white"
        outlined
        >
        <div class="d-flex flex-no-wrap justify-space-between">
          <div>
            <v-card-title>{{ article.b_company }}</v-card-title>
            <v-card-subtitle>{{ article.b_date }}</v-card-subtitle>
            <v-btn rounded color="pink" dark class="ml-3" target="_blank" :href="article.b_url">
                <h3><i class="fas fa-newspaper mr-2"></i>기사 보기</h3></v-btn>
          </div>
          <v-avatar
            class="ma-3"
            size="125"
            tile
          >
            <v-img :src="article.b_thumbnail"></v-img>
          </v-avatar>
        </div>
      </v-card>
    </div>
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
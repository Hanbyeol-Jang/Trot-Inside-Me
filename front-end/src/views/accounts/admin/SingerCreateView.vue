<template>
  <div class="my-5">
  <v-card
    class="mx-auto mt-5"
    max-width="500"
  >
    <v-card-actions class="d-flex flex-row-reverse">
      <v-btn rounded 
        color="pink" 
        dark
        class="mt-2" 
        @click="goSingerCreate">
          <div class="mx-2"><i class="fas fa-plus mr-2"></i>가수 등록</div>
      </v-btn>
    </v-card-actions>
    <v-card-text>
      <v-textarea 
        solo 
        label="여기를 눌러 새로운 소식을 남겨보세요." 
        height="300" v-model="content"></v-textarea>
      <v-img :src="change_image" width="100%" height="100%" v-show="flag"></v-img>
      <v-file-input 
        show-size 
        counter 
        multiple 
        label="사진을 등록 할 수 있습니다." 
        type="file" id="file" ref="file" @change="communityImage()"></v-file-input>
    </v-card-text>
  </v-card>
  </div>
</template>

<script>
export default {
    name: 'SingerCreateView',
}
</script>

<style>

</style>
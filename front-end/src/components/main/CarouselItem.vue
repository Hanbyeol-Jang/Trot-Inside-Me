<template>
  <v-carousel-item
    :src="imgSrc"
  >
  </v-carousel-item>
</template>

<script>
export default {
  name: 'CarouselItem',
  props: {
    imgSrc: String,
  }
}
</script>

<style>

</style>
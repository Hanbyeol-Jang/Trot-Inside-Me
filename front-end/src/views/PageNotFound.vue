<template>
  <div class="container mt-5">
    <img src="@/assets/image/404.png" alt=""  class="text-center col-lg">
    <div class="text-center">
      <p><router-link to="/" class="btn--text">여기</router-link>를 눌러주세요</p>
    </div>
    <!-- <router-link to="/" class="btn--text">
    <div class="text-center">Home</div>
    </router-link> -->
  </div>
</template>

<script>
export default {
    name:"PageNotFound",
}
</script>

<style>

</style>
<template>
  <div class="container mt-5">
    <img src="@/assets/image/404.png" alt=""  class="text-center col-lg">
    <div class="text-center">
      <p class="btn--text2"><router-link to="/" class="btn--text">여기</router-link>를 눌러주세요</p>
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
.btn--text{
  font-size: 150%;
}
.btn--text2{
  font-size: 150%;
}
</style>
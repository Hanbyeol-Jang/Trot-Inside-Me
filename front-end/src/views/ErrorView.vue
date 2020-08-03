<template>
  <div>
  <div class="container mt-5">
    <img src="@/assets/image/500.png" alt="" class="text-center col-lg">
    <router-link to="/" class="btn--text">
    <div class="text-center" @click="goback">Back</div>
    </router-link>
  </div>
  </div>
</template>

<script>
export default {
    name:"ErrorView",
    methods:{
        goback(){
            history.back()
        }
    },
}
</script>

<style>

</style>
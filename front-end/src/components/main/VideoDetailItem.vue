<template>
  <div>
    <iframe :src="video.b_url" frameborder='no' scrolling='no' marginwidth='0' marginheight='0' WIDTH=100% HEIGHT=300 allow='autoplay' allowfullscreen></iframe>

    <hr>
    <div class="mx-3 mt-3">
        <h3 class="font-weight-bold">{{ video.b_title }}</h3>
        <h5>{{video.b_date}}</h5>
        <hr>
        <p>{{ video.b_content }}</p>
        <br>
        <hr>
    </div>
  </div>
</template>

<script>
export default {
    name:"VideoDetailItem",
    components:{
    },
    props:{
        video:Object,
    },
}
</script>

<style>

</style>
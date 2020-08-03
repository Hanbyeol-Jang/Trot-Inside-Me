<template>
  <div>
    <!-- <h3 class="font-weight-bold">{{ article.title }}</h3> -->
            <h3 class="font-weight-bold">임영웅-뿐이고</h3>
    <hr>
    <!-- <img width="100%" :src="article.url" alt=""> -->
    <iframe src='https://tv.naver.com/embed/14982924?autoPlay=true' frameborder='no' scrolling='no' marginwidth='0' marginheight='0' WIDTH=100% HEIGHT=400 allow='autoplay' allowfullscreen></iframe>
    <hr>
    <div class="m-3">
        <!-- <h4>{{ article.descripton }}</h4> -->
        <p>벤의 최고 매력이자 장점이 진짜 가성과 진성의 갭이 그 어떤누구보다 적다는게 아주 독보적인거 같다..
다른 거의 대부분의 가수들은 진성과 가성의 갭을 다른느낌으로 표현하는게 일반적인데 벤은 자세히 듣지 않으면 이게 진성인지 가성인지 구분이 안될정도로 차이가 안느껴 진다고나 할까</p>
        <hr>
    </div>
    
  </div>
</template>

<script>
export default {
    name:"ArticleDetailItem",
    components:{
    },
    props:{
        // article,
    },
}
</script>

<style>

</style>
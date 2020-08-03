<template>
  <div class="text-center">
     <v-avatar
      size="100px">
      <img
        src="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSrPi2eq64wocYIj9FMtpIUqStLMKG1_iwt7jAmpZOo1blAxD0asvOHJp6Z_SKfPIE8_XK4WieQapBuqV13lwAdnHHFC0DLhmryZW8a&usqp=CAU&ec=45682162"
        alt="John"
      >
    </v-avatar>
    <h3 class="mt-4">김싸피</h3>
    <div class="mt-5 mb-6">
      <v-tabs class="d-flex justify-center">
        <v-tab>나의 가수</v-tab>
        <v-tab>저장된 동영상</v-tab>
        <v-tab>저장된 기사</v-tab>
      </v-tabs>
    </div>
  </div>
</template>

<script>
export default {
    name: 'UserDetailView',
}
</script>

<style>

</style>
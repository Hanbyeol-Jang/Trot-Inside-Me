<template>
  <div class="mt-6">
    <v-row>
      <v-col 
        cols="12"
        class="d-flex justify-space-around">
        <v-avatar
          class="ma-3 elevation-4"
          size="120"
          >
          <v-img 
            src="@/assets/image/user_default.png">
          </v-img>
        </v-avatar>
        <div class="d-flex align-center">
          <div class="d-flex flex-column">
            <div></div>
            <div><i class="fas fa-at mr-1"></i></div>
          </div>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script> 
export default {
    name: 'UserDetailView',
}
</script>

<style>

</style>
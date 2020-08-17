<template>
  <div>
    <SingerList :singers="userFollowing" />
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'

import SingerList from '@/components/main/SingerList.vue'

export default {
  name: 'UserLikeSingerView',
  data() {
    return {
      userId: this.$route.params.userId,
    }
  },
  components: {
    SingerList,
  },
  computed: {
    ...mapState(['user', 'userFollowing']),
  },
  methods: {
    ...mapActions(['getFollowList']),
  },
  created() {
    this.getFollowList(this.userId)
  }
}
</script>

<style>

</style> 
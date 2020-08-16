<template>
  <v-container>
    <div class="my-3 text-right">
      <v-btn rounded color="pink" dark @click="goSingerCreate">
        <i class="fas fa-plus mr-2"></i>가수 등록
      </v-btn>
    </div>
    <v-simple-table 
      fixed-header>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">이미지</th>
            <th class="text-left">이름</th>
            <th class="text-left">팬 카페</th>
            <th class="text-left">삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="singer in singers" :key="singer.s_idx">
            <td>
              <v-avatar
                class="my-3 elevation-2 singer-detail"
                @click="goSingerDetail(singer.s_idx)"
                size="60">
                <img
                  v-if="singer.s_img"
                  :src="singer.s_img"
                  alt="Singer Default"
                >
                <img
                  v-else
                  src="@/assets/image/default_img.png">
              </v-avatar>
            </td>
            <td>{{ singer.s_name }}</td>
            <td v-if="singer.s_cafeUrl" class="px-1">
              <v-btn depressed small
                :href="singer.s_cafeUrl">
                팬카페
                <v-icon
                  color="pink"
                  right
                >
                  mdi-open-in-new
                </v-icon>
              </v-btn>
            </td>
            <td v-else>미등록</td>
            <td class="text-center px-1">
              <v-btn icon @click="deleteSinger(singer.s_idx)"><i class="fas fa-trash-alt"></i></v-btn>
            </td>
          </tr> 
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'

export default {
  data () {
    return {
      name: 'SingerManageView',
      data() {
        return {
        }
      },
    }
  },
  computed: {
    ...mapState(['singers']),
  },
  methods: {
    ...mapActions(['fetchSingers', 'deleteSinger']),
    goSingerCreate() {
      this.$router.push({ name: 'SingerCreateView' })
    },
    goSingerDetail(singerId) {
      this.$router.push({ name: 'SingerDetailView', params: { singerId: singerId }})
      console.log(singerId)
    },
  },
  created() {
    this.fetchSingers()
  }
}
</script>

<style scoped>
.singer-detail:hover {
  cursor: pointer;
}
</style>
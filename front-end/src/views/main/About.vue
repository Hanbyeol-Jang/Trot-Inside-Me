<template>
  <div class="my-5">
      <div class="text-center">
        <img src="@/assets/image/trot_logo.png" alt="My Trot" width="200px">
      </div>
  <v-container>
    <v-simple-table 
      class="my-5"
      fixed-header>
      <template v-slot:default>
        <tbody>
          <tr>
            <td>
              <v-avatar
                class="my-3 elevation-2 singer-detail"
                size="60">
                <img
                  src="@/assets/image/홈.png"
                  alt=""
                >
              </v-avatar>
            </td>
            <td>
              <p class="ma-0 service">인기 영상/기사를 볼 수 있습니다.</p>
              <p class="ma-0">방송편성표 탭을 통해 관심 프로그램 카카오 알림을 받아보세요.</p>
            </td>
          </tr>
          <tr>
            <td>
              <v-avatar
                class="my-3 elevation-2 singer-detail"
                size="60">
                <img
                  src="@/assets/image/가수검색.png"
                  alt=""
                >
              </v-avatar>
            </td>
            <td>
              <p class="ma-0 service">가수 별 컨텐츠를 즐겨보세요.</p>
            </td>
          </tr>
          <tr>
            <td>
              <v-avatar
                class="my-3 elevation-2 singer-detail"
                size="60">
                <img
                  src="@/assets/image/투표.png"
                  alt=""
                >
              </v-avatar>
            </td>
            <td>
              <p class="ma-0">가수별 지역별 투표를 해보세요.</p>
            </td>
          </tr>
          <tr>
            <td>
              <v-avatar
                class="my-3 elevation-2 singer-detail"
                size="60">
                <img
                  src="@/assets/image/수다방.png"
                  alt=""
                >
              </v-avatar>
            </td>
            <td>
              <p class="ma-0">유저들과 정보를 공유하세요.</p>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
  </div>
</template>

<script>
export default {
    name: 'About',
}
</script>

<style>
.service{
  font-size: 100%;
}
</style>
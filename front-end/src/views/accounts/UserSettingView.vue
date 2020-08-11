<template>
  <div>
      <v-list
        :disabled="disabled"
        :dense="dense"
        :two-line="twoLine"
        :three-line="threeLine"
        :shaped="shaped"
        :flat="flat"
        :subheader="subheader"
        :sub-group="subGroup"
        :nav="nav"
        :avatar="avatar"
        :rounded="rounded"
      >
        <v-list-item-group v-model="item" color="primary">
          <v-list-item
            v-for="(item, i) in items"
            :key="i"
            :inactive="inactive"
          >
            <v-list-item-avatar v-if="avatar">
              <v-img :src="item.avatar"></v-img>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title v-html="item.title"></v-list-item-title>
              <v-list-item-subtitle v-if="twoLine || threeLine" v-html="item.subtitle"></v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
      </v-list>
  </div>
</template>

<script>
export default {
    name: 'UserSettingView',
    data() {
      return {
        item: 5,
      items: [
        {
          title: '상태 메세지 수정',
        },
        {
          title: '닉네임 수정',
        },
        {
          title: '이메일 수정',
        },
        {
          title: '공지사항',
        },
        {
          title: '탈퇴하기',
        },
      ],
      disabled: false,
      dense: false,
      twoLine: false,
      threeLine: false,
      shaped: false,
      flat: false,
      subheader: false,
      inactive: false,
      subGroup: false,
      nav: false,
      avatar: false,
      rounded: false,
      }
    },
}
</script>

<style>

</style>
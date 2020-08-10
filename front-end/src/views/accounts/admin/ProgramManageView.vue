<template>
  <v-container>
    <div class="my-3 text-right">
      <v-btn rounded color="pink" dark @click="goProgramCreate">
        <i class="fas fa-plus mr-2"></i>프로그램 등록
      </v-btn>
    </div>
    <v-simple-table 
      fixed-header>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">이름</th>
            <th class="text-left">URL</th>
            <th class="text-center">삭제</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="program in programs" :key="program.a_idx">
            <td>{{ program.a_broadName }}</td>
            <td v-if="program.a_broadUrl"><a :href="program.a_broadUrl">URL<i class="fas fa-external-link-alt ml-1"></i></a></td>
            <td v-else>미등록</td>
            <td class="text-center">
              <v-btn icon @click="deleteProgram(program.a_idx)"><i class="fas fa-trash-alt"></i></v-btn>
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
      name: 'ProgramManageView',
      data() {
        return {
        }
      },
    }
  },
  computed: {
        ...mapState(['programs']),
      },
  methods: {
    ...mapActions(['fetchPrograms', 'deleteSProgram']),
    goProgramCreate() {
      this.$router.push({ name: 'ProgramCreateView' })
    }
  },
  created() {
    this.fetchPrograms()
  }
}
</script>

<style>

</style>

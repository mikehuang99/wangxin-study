<template>
  <div class="tit_box">
    <b @click="moreCourse(headinfo.after)"><nuxt-link to="/courseAllList" class="more">{{headinfo.before}}<img width="10" src="img/icon_more.jpg" class="icon_more"></nuxt-link></b>
    <h1 class="tit_contain no_con_tp"><img :src=headinfo.imgurl width="15" class="icon_time" alt=""><span>{{headinfo.after}}</span></h1>
  </div>
</template>
<script>
import {mapState, mapMutations} from 'vuex'
export default {
  props: ['headinfo'],
  computed: {
    ...mapState([
      'courseId', 'coursename', 'courseSort'
    ])
  },
  methods: {
    commitParams (value) {

    },
    moreCourse (value) {
      switch (value) {

        default:
          this.commitParams('')
          break
      }
    }
  }
}
</script>
<style lang="sass">
  .tit_box
    margin-top: 1rem
    overflow: hidden
    padding: 12px 0 12px 0
    line-height: 20px
    .icon_time
      margin-top: -0.1rem
    .more
      color: #999
      float: right
      .icon_more
        margin-left: 3.5px
        vertical-align: middle
      .icon_more
        margin-top: -2px
    .tit_contain
      font-size: 1rem
      font-weight: bold
      line-height: 2rem
      color: #484848
      padding: 0 0 0 4px
      span
        margin-left: 0.5rem
</style>

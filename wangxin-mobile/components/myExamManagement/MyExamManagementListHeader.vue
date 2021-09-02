<template>
  <header id="header" class="header">
    <nuxt-link to="/" class="left_back"></nuxt-link>
    <div class="header_search">
      <div class="drop_down_list">
        <span data-role="select_txt" class="select_txt">标题</span>
      </div>
      <div class="header_search_area">
        <input v-model.trim="title" v-on:keyup.13="submit()" placeholder="搜索考试" class="header_search_txt">
        <div class="reset_all_condition" @click="reload()">
          <span v-if="!reloadFlag" style="font-weight: bold;color: #2828FF;"><img src="/wxmobile/img/refresh_green.png"/></span>
          <span v-if="reloadFlag" style="font-weight: bold;color: red;"><img src="/wxmobile/img/refresh_red.png"/></span>
        </div>
      </div>
    </div>
  </header>
</template>
<script>
export default {
  data () {
    return {
        title: '',
        reloadFlag: false
    }
  },
  mounted () {
    this.title = this.$route.query.title
  },
    watch: {
        $route(){
            this.title= this.$route.query.title
        },
        title() {
            this.$parent.reloadOrderList(this.title);
        }
    },
  methods: {
      reload(){
          this.reloadFlag = true;
          this.title = '';
          this.$parent.reloadOrderList(this.title);
          setTimeout(() => {
              this.reloadFlag = false;
          }, 500)
      },
    submit () {
      this.$router.push({path: '/my/myExamManagement/myExamManagementIndex', query: {title: this.title}})
    }
  }
}
</script>
<style lang="sass" scoped>
  header
    background: #fff
    border-bottom: 1px solid #b2b2b2
    .left_back
      background: url('/wxmobile/img/icon_back_red.png')no-repeat
      background-size: 10px 16px
      width: 10px
      height: 16px
      position: absolute
      left: 0
      top: 15px
      display: block
      margin: 0 0 0 20px
    .header_search
      margin-left: 43px
      margin-top: 2px
      margin-right: 10px
      padding-bottom: 2px
      .drop_down_list
        width: 48px
        height: 30px
        background: url(/wxmobile/img/header_drop_down.png) no-repeat 36px center #eee
        background-size: 8px 5px
        line-height: 30px
        font-size: 1.2rem
        color: #474747
        text-align: left
        float:left
        position: relative
        padding: 0 0 0 8px
        border-top-left-radius: 10px
        border-bottom-left-radius: 10px
      .header_search_area
        margin-top: -30px
        margin-left: 48px
        position: relative
        .header_search_txt
          width: 72%
          height: 30px
          margin-right: 28px
          border-top-right-radius: 10px
          border-bottom-right-radius: 10px
          font-size: 1.2rem
          color:#474747
          display: inline-block
          padding: 0 0 0 25px
          background: #f5f5f5 url("/wxmobile/img/search_page.png") no-repeat 5px 9px
          background-size: 14px 13px
          border-left: 1px solid #ccc
        .reset_all_condition
          display: inline-block
          float: right
          margin-top: 5px
          margin-right: 0px
</style>

<template>
  <div class="bottom_labels">
    <p class="top_line"></p>
    <div class="labels_list">
      <ul>
        <li class="label_one">
          <nuxt-link to="/"></nuxt-link>
          <span>首页</span>
        </li>
        <li class="label_two">
          <nuxt-link to="/courseAllList"></nuxt-link>
          <span>课程列表</span>
        </li>
        <li class="label_three">
          <nuxt-link to="/my/myDot/myDotIndex"></nuxt-link>
          <span>点券记录</span>
        </li>
        <li class="label_four">
          <nuxt-link to="/my/myInfo"></nuxt-link>
          <span>我的</span>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
    }
  }
}
</script>

<style lang="sass">
@import '~static/css/style.sass'
.bottom_labels
  position: fixed
  left: 0
  bottom: 0
  width: 100%
  .top_line
    height: 10px
    background: #eee
    border-bottom: 1px solid #ddd
  ul
    height: 60px
    background: #fff
    li
      width: 25%
      float: left
      position: relative
      a
        display: inline-block
        height: 50px
        width: 100%
        -webkit-tap-highlight-color:transparent
      span
        display: inline-block
        width: 100%
        text-align: center
        position: absolute
        bottom: 0
        left: 0
        &.on
          color: #f15757
    .label_one
      a
        background: url('/wxmobile/img/icon_tab_home.png')no-repeat center 10px
        background-size: 19px 20px
        &.nuxt-link-exact-active
          background: url('/wxmobile/img/icon_tab_home_on.png')no-repeat center 10px
          background-size: 19px 20px
    .label_two
      a
        background: url('/wxmobile/img/icon_tab_class.png')no-repeat center 10px
        background-size: 19px 20px
        &.nuxt-link-exact-active
            background: url('/wxmobile/img/icon_tab_class_on.png')no-repeat center 10px
            background-size: 19px 20px
    .label_three
      position: relative
      .count_cart
        position: absolute
        right: 20px
        top: 10px
        display: inline-block
        width: 15px
        height: 15px
        background: $theme_color
        color: #fff
        border-radius: 50%
        text-align: center
        line-height: 15px
      a
        background: url('/wxmobile/img/icon_cart.png')no-repeat center 10px
        background-size: 19px 20px
        &.nuxt-link-exact-active
          background: url('/wxmobile/img/icon_on_cart.png')no-repeat center 10px
          background-size: 19px 20px
    .label_four
      a
        background: url('/wxmobile/img/icon_tab_my.png')no-repeat center 10px
        background-size: 19px 20px
        &.nuxt-link-exact-active
          background: url('/wxmobile/img/icon_tab_my_on.png')no-repeat center 10px
          background-size: 19px 20px
</style>

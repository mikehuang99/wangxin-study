<template>
  <header class="header" :class="[this.backgroundColor ? 'fff' : '' ]">
    <a href="javascript:" @click="go()" class="header_back_icon_white"></a>
    <span style="font-size: 18px;font-weight: bold;color: white">我的信息</span>
  </header>
</template>
<script>
export default {
  props: ['name', 'className', 'backgroundColor', 'back'],
    data(){
      return{

      }
    },
  methods: {
    go () {
      this.$router.go(-1)
    }
  }
}
</script>
<style lang="sass" scoped>
@import '~static/css/style.sass'
header
	width: 100%
	height: 44px
	text-align: center
	padding-top: 5px
	position: fixed
	left: 0
	top: 0
	z-index: 19881121
	span
  // color: #fff
  font-size: 1.6rem
  line-height: 38px
</style>

<template>
  <header class="header" style="background-color:#FF5151">
    <a href="javascript:" @click="go()" class="header_back_icon_white"></a>
    <span :class="[this.headerNameClass ? 'titlegray' : 'title']">{{this.headerName}}</span>
  </header>
</template>
<script>
export default {
  props: ['headerName', 'headerNameClass'],
  methods: {
    go () {
      this.$router.go(-1)
    }
  }
}
</script>
<style lang="sass" scoped>
header
	width: 100%
	height: 44px
	text-align: center
	padding-top: 5px
	position: fixed
	left: 0
	top: 0
	z-index: 200000
	span
  font-size: 1.6rem
  line-height: 38px
  color: white
</style>

<template>
  <header class="header" :class="[this.backgroundColor ? 'fff' : '' ]">
    <a href="javascript:" @click="go()" class="header_back_icon_white"></a>
    <span style="font-size: 18px;font-weight: bold;color: white">我的优惠券</span>
    <div style="width: 30%;height: 90%;position: absolute;right: 10px;
    color: #ffffff;font-size: 14px;top: 12px;text-decoration: underline" @click="goCollectCoupon">去领取优惠券</div>
  </header>
</template>
<script>
export default {
  props: ['name', 'className', 'backgroundColor', 'back'],
    data(){
      return{
          div1Flag: false,
          div2Flag: false
      }
    },
  methods: {
    go () {
      this.$router.go(-1)
    },
      goCollectCoupon(){
          this.$router.push({path: '/my/collectCoupon/collectCouponIndex', query: {}});
      },
    div1 () {
        console.log("div1====")
        if(this.div1Flag==false) {
            this.div1Flag = true;
            this.div2Flag = false;
        }else{
            this.div1Flag = false;
        }
    },
    div2() {
        console.log("div2====")
        if(this.div2Flag==false) {
            this.div2Flag = true;
            this.div1Flag = false;
        }else{
            this.div2Flag = false;
        }
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

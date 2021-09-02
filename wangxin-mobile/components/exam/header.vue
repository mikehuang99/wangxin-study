<template>
  <header class="header" :class="[this.backgroundColor ? 'fff' : '' ]">
    <a href="javascript:" @click="go()" class="header_back_icon_white"></a>
    <!--<span :class="[false ? 'titgray' : 'tit']">设置</span>-->
    <div style="width: 20%;height: 90%;position: absolute;right: 10px;
    color: #ffffff;font-size: 18px;top: 10px" @click="goIndex"><img src="/wxmobile/img/icon_tab_home_on_24X24.png"/></div>
    <!--
    <div style="width: 40%;height: 90%;position: absolute;;right: 5px;border:1px dashed #000">
      <img style="width: 50%;height: 90%;position: absolute;right: 0%" src="/wxmobile/img/wxImg/shu48X48.png" @click="div2">
      <img style="width: 50%;height: 90%;position: absolute;right: 50%" src="/wxmobile/img/wxImg/set48X48.png" @click="div1">
    </div>
    <div v-show="this.div1Flag" style="width: 40%;height: 10%;position: fixed;top: 44px;right: 0px;border:0px dashed #000">
      <img style="width: 50%;height: 90%;position: absolute;right: 50%" src="/wxmobile/img/wxImg/set48X48.png">
    </div>
    <div v-show="this.div2Flag" style="width: 40%;height: 10%;position: fixed;top: 44px;right: 0px;border:0px dashed #000">
      <img style="width: 50%;height: 90%;position: absolute;right: 0%" src="/wxmobile/img/wxImg/shu48X48.png">
    </div>
    -->
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
      goIndex(){
          this.$router.push({path: '/', query: {}});
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

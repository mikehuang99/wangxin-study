<template>
   <!-- 功能按钮 -->
  <div class="course_detail_bottom">
      <div class="course_detail_bottom_btn">
        <a @click="go()" class="bottom_btn" :class="{disabled: false}">返回课程列表</a>
        <!--
        <a class="bottom_btn" :class="{disabled: true}" v-if="continueBuy==false&&longTerm==false&&courseDetail.isPutaway==1&&isLogin==true" >立即购买</a>
        <a class="bottom_btn" :class="{disabled: true}" v-if="continueBuy==true&&longTerm==false&&courseDetail.isPutaway==1&&isLogin==true">续期</a>
        -->
        <!--
        <a class="bottom_btn" :class="{disabled: true}" v-if="isPay&&courseInfo.isPutaway==1&&isLogin==true">进入学习</a>
        -->
        <!--
        <a class="bottom_btn" :class="{disabled: true}" v-if="isLogin==false">立即登录</a>-->
      </div>
  </div>
</template>
<script>
export default {
    props: ['courseDetail'],
    data () {
    return {
        longTerm: false,
        continueBuy: false,
        isPay: false,
        isLogin: true
    }
  },
  mounted () {
  },
  computed: {

  },
  methods: {
      go () {
          //this.$router.go(-1)
          this.$router.push({path: '/courseAllList', query: {}})

      }
  },
  components: {
  },
  watch: {
        courseDetail: function (value) {
            if(value.isPay==1){
                if(value.validTerm=='9999-12-31 23:59'){
                    this.longTerm = true;
                }
                this.continueBuy = true;
                this.isPay = true;
            }
            console.log("this.longTerm:"+this.longTerm);
            console.log("this.continueBuy:"+this.continueBuy);
            console.log("this.isPay:"+this.isPay);

        }
   }
}
</script>

<style lang="sass" scoped>
.course_detail_bottom
	width: 100%
	position: fixed
	left: 0
	bottom: 0
	z-index: 19881121
	.course_detail_bottom_btn
		height: 50px
    width: 100%
  a
    display: inline-block
    //width: 33.333%
    width: 100%
    height: 100%
    line-height: 50px
    text-align: center
    color: #fff
    font-size: 15px
    float: left
  .bottom_btn
    background: red
    &.disabled
      background: #bbb
</style>

<template>
  <div class="fix_bottom_contain">
      <div class="bottom_btn">
        <a class="count_d" :class="{after: true}">
          <span @click="goQuestion('last')" style="float:left;margin-left:40px;color: #333333;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;">
            <img src="/wxmobile/img/icon_return.png">
          </span>
          <!--
          <span>&nbsp;</span>
          <span style="color: #333333;">总数{{totalquestionscount}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span style="content: '\2714';color: #008100;">√</span>
          <span style="color: #333333">{{rightCount}}</span>
          <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span style="content: '\2714';color: red;">X</span>
          <span style="color: #333333">{{errorCount}}</span>
          <span>&nbsp;</span>
          -->
          <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
          <span @click="goQuestion('next')" style="float:right;margin-right:40px;color: #333333;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;">
            <img src="/wxmobile/img/icon_go.png">
          </span>
        </a>
      </div>
  </div>
</template>
<script>
import {getStore,removeStore,setStore} from '~/store/common.js'
import { mapState } from 'vuex'
export default {
  data () {
    return {
      layerMSg: '',
      isCart: false,
      disabledCart: false,
      disabledYu: false,
      disabledAdd: false,
      users: ''
    }
  },
  mounted () {
  },
  computed: {
    ...mapState([
      'courseDetail'
    ])
  },
  props: ['addCart','rightCount','errorCount','totalquestionscount'],
  methods: {
      goQuestion(lastOrNext){
          this.$parent.goQuestion(lastOrNext);
      },
      go () {
          this.$router.go(-1)
      },
    islogin (value) {
      if (!getStore('user')) {
        this.$router.push({path: '/login'}) // 没有登录就去登录把  少年
      } else {
        if (this.disabledAdd) { // 是否禁用啊  如果是 你就别再跳转了 好吧
          return
        } else {
          if (value === 1) {
            let id = JSON.parse(getStore('courseDetail')).courseId // 路由带走当前的id
            this.$router.push({path: '/submitOrder/index', query: {id: id}}) // 跳转提交订单
          } else {
            this.$router.push({path: '/subscribe/index'}) // 跳转预约试听
          }
        }
      }
    }
  },
  components: {
  }
}
</script>

<style lang="sass" scoped>
@import '~static/css/style.sass'
.fix_bottom_contain
	width: 100%
	position: fixed
	left: 0
	bottom: 0
	z-index: 19881121
	.bottom_btn
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
  .count_d
    background: red
    &.before
      background: #00AA00
    &.after
      background: white
</style>

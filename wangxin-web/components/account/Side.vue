<template>
  <header class="side_box">
    <div class="menu_panel" v-if="teacher">
      <h3 class="title" style="background-color: deepskyblue">★教师平台</h3>
      <ul class="menus">
        <li :class="{on: type == 'jsxx'}"><router-link :to="{name: 'account-teacher'}">教师信息</router-link></li>
        <li :class="{on: type == 'kcgl'}"><router-link :to="{name: 'account-teacher-course'}">课程管理</router-link></li>
        <li :class="{on: type == 'stgl'}"><router-link :to="{name: 'examp'}">题库管理</router-link></li>
        <li :class="{on: type == 'sjgl'}"><router-link :to="{name: 'examp-paper'}">试卷管理</router-link></li>
        <li :class="{on: type == 'ksgl'}"><router-link :to="{name: 'examp-examManagement'}">考试管理</router-link></li>
        <li :class="{on: type == 'teacherUserStudyLog'}"><router-link :to="{name: 'teacherUserStudyLog'}">学习记录</router-link></li>
      </ul>
    </div>
    <div class="menu_panel">
      <h3 class="title" style="background-color: deepskyblue">★个人资料</h3>
      <ul class="menus">
        <li :class="{on: type == 'grxx'}"><router-link :to="{name: 'account'}">个人信息</router-link></li>
        <li :class="{on: type == 'xgmm'}"><router-link :to="{name: 'account-reset'}">修改密码</router-link></li>
        <li :class="{on: type == 'wddd'}"><router-link :to="{name: 'account-order'}">我的订单</router-link></li>
        <li :class="{on: type == 'mycourse'}"><router-link :to="{name: 'account-mycourse'}">我的课程</router-link></li>
        <li :class="{on: type == 'myyhq'}"><router-link :to="{name: 'account-coupon-my'}">我的优惠券</router-link></li>
        <li :class="{on: type == 'cyhq'}"><router-link :to="{name: 'account-coupon-collect'}">领取优惠券</router-link></li>
        <li :class="{on: type == 'studycard'}"><router-link :to="{name: 'account-studycard'}">学习卡</router-link></li>
        <li :class="{on: type == 'dot'}"><router-link :to="{name: 'account-dot'}">点券</router-link></li>
        <li :class="{on: type == 'point'}"><router-link :to="{name: 'account-point'}">积分</router-link></li>
        <li :class="{on: type == 'myexammanagement'}"><router-link :to="{name: 'account-myexammanagement'}">我的考试</router-link></li>
        <li :class="{on: type == 'userStudyLog'}"><router-link :to="{name: 'account-userStudyLog'}">学习记录</router-link></li>

        <!--
        <li :class="{on: type == 'xxjl'}"><router-link :to="{name: 'account-study'}">学习记录</router-link></li>
        -->
      </ul>
    </div>
  </header>
</template>
<script>
import {getUserInfo} from '~/api/user.js'
import VueCookie from 'vue-cookie'

export default {
  props: {
    type: {
      type: String,
      default: 'grxx'
    }
  },
  data () {
    return {
      teacher: false,
      isLive: false,
      isLibrary: false,
      isCourse: false,
      isResource: false,
      webInfo: this.$store.state.webInfo,
      tokenInfo: '',
      userInfo: []
    }
  },
  methods: {
    close () {
      this.$emit('close')
    }
  },
  mounted () {
      this.tokenInfo = VueCookie.get('wangxinToken');
      this.userInfo = JSON.parse(VueCookie.get("wangxinUserInfo"));
    if (this.tokenInfo && this.userInfo && this.userInfo.token == this.tokenInfo) {
      if (this.userInfo.userType === 2 || this.userInfo.userType === 4) {
        this.teacher = true;
        console.log('teacher+++++' + this.teacher)
      }
    } else {
      getUserInfo().then(res => {
        // console.log(res)
        let result = res.data
        if (result.code === 200) {
          if (result.data.userType === 2 || result.data.userType === 4) {
            this.teacher = true;
          }
        }
      })
    }
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .side_box{
    border-radius: 6px 6px 0 0;
    float: left;
    width: 170px;
  }
  .menu_panel{
    background-color: #fff;
    margin-bottom: 20px;
    border-radius: 6px 6px 0 0;
    .act_icon {
      width: 16px;
      margin-right: 2px;
    }
    .title{
      font-size: 14px;
      color: #333;
      line-height: 50px;
      padding: 0 35px;
      border-bottom: 1px solid rgb(242, 242, 242);
      i{
        padding-right: 6px;
        font-size: 16px;
        position: relative;
        left: -5px;
      }
    }
  }
  .menus{
    li{
      line-height: 40px;
      // padding-left: 35px;
      text-align: left;
      margin-left: 30px;
      :before{content:"☉"; color:blue;}
      //★
      &:hover,&.on{
        a{
          color: white;
          background-color: cornflowerblue;
        }
      }
    }
    a{
      color: #333;
      font-size: 14px;
    }
  }
</style>

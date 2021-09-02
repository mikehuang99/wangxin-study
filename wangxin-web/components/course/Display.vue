<!-- 课程信息展示 -->
<template>
  <div class="">
    <y-header :active="'kczx'"></y-header>
    <div class="detail_content">
      <div class="detail_body">
        <ul class="detail_header clearfix">
          <li><nuxt-link :to="{name: 'index'}">首页</nuxt-link><span>></span></li>
          <li><nuxt-link :to="{name: 'list'}">课程中心</nuxt-link><span>></span></li>
          <li>{{courseInfo.courseName}}</li>
        </ul>
        <div class="clearfix">
          <div class="video_box">
            <div class="detail_view" id="player" ref="videobox" :style="'background-image:url('+courseInfo.courseLogo+')'"></div>
            <!-- <span class="iconfont close_video" @click="stopVideo">关闭</span> -->
          </div>
          <div class="view_info">
            <p>{{courseInfo.courseName}}</p>
            <div class="view_price">
              <div v-if="courseInfo.courseOriginal==courseInfo.courseDiscount">
                <span v-if="!courseInfo.isFree">￥{{courseInfo.courseOriginal ? courseInfo.courseOriginal.toFixed(2) : '0.00'}}元{{courseInfo.costTimeType | costTimeTypeChange}}</span>
                <span v-else>免费</span>
              </div>
              <div v-if="courseInfo.courseOriginal!=courseInfo.courseDiscount">
                价格:<span v-if="!courseInfo.isFree" style="text-decoration: line-through;">￥{{courseInfo.courseOriginal ? courseInfo.courseOriginal.toFixed(2) : '0.00'}}元{{courseInfo.costTimeType | costTimeTypeChange}}</span>
                <span v-if="!courseInfo.isFree">优惠价:</span><span v-if="!courseInfo.isFree">￥{{courseInfo.courseDiscount ? courseInfo.courseDiscount.toFixed(2) : '0.00'}}元{{courseInfo.costTimeType | costTimeTypeChange}}</span>
                <span v-else>免费</span>
              </div>
            </div>
            <div class="view_teacher">
              <span class="text_b">教师:</span>{{courseInfo.teacher.teacherName}}
            </div>
            <!-- <div class="view_teacher mgt20" v-else>
              <span class="text_b">有效期:</span>永久
            </div> -->
            <!--分享代码开始-->
            <div class="bdsharebuttonbox" style="margin-top: 30px;"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a></div>
            <script>
                with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
            </script>
            <!--分享代码结束-->
            <div class="view_teacher mgt20">
              <span class="text_b">购买人数:</span>{{courseInfo.buyCount}} 人
            </div>
            <br/>
            <div v-if="courseInfo.isPutaway==0||courseInfo.validValue==0">
              <span class="text_b" style="font-size: 20px;border: 2px solid red;color: red">已经下架</span>
            </div>
            <div class="foot_box">
              <div class="study_num">
                <span>☼</span>{{courseInfo.studyCount}} 人已学习
              </div>
              <div style="margin-left: 0">
              <button style="margin-left: 0px;width: 140px;height: 38px;color: #fff;background: blue;border: none; &:hover { text-decoration: none;color: #fff;cursor: pointer;}" id="buyBtn" v-if="continueBuy==false&&longTerm==false&&courseInfo.isPutaway==1&&isLogin==true" @click="buyCourse">立即购买</button>
              <button style="margin-left: 0px;width: 140px;height: 38px;color: #fff;background: blue;border: none; &:hover { text-decoration: none;color: #fff;cursor: pointer;}" id="buyBtn2" v-if="continueBuy==true&&longTerm==false&&courseInfo.isPutaway==1&&isLogin==true" @click="buyCourse">续期</button>
              <button style="margin-left: 10px;width: 140px;height: 38px;color: #fff;background: blue;border: none; &:hover { text-decoration: none;color: #fff;cursor: pointer;}"  id="buyBtnContinue"  v-if="isPay&&courseInfo.isPutaway==1&&isLogin==true" @click="goStudy">进入学习</button>
              <button style="margin-left: 10px;width: 140px;height: 38px;color: #fff;background: blue;border: none; &:hover { text-decoration: none;color: #fff;cursor: pointer;}"  id="toLogin_d"  v-if="isLogin==false" @click="toLoginD">立刻登录</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <d-pay :isCourse="true" @hidefun="showPay = false" :data="payData" v-if="showPay"></d-pay>
  </div>
</template>
<script>
import YHeader from '../common/Header'
import DPay from '~/components/PayModal'
import VueCookie from 'vue-cookie'
export default {
  components: {
    DPay,
    YHeader
  },
  props: {
    courseInfo: {
      type: Object,
      default: null
    },
      isPay: {
          type: Boolean,
          default: false
      },
      continueBuy: {
          type: Boolean,
          default: false
      },
      longTerm: {
          type: Boolean,
          default: false
      }
  },
  data () {
    return {
      payData: null,
      showPay: false,
      isCollect: '',
      activityText: '',
      isLogin: false
    }
  },
    filters:{
        costTimeTypeChange(value){
            //计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
            if(value===0){
                return "";
            }else if(value===1){
                return "/天";
            }else if(value===2){
                return "/周";
            }else if(value===3){
                return "/月";
            }else if(value===4){
                return "/年";
            }else{
                return "";
            }
        }
    },
  methods: {
      toLoginD(){
          this.$router.push({name: 'tologin'})
      },
      goStudy(){
          this.$emit('goStudy')
      },
    stopVideo () {
      console.log("video")
    },
    // 购买视频
    buyCourse (event) {
      console.log('buy')
      if (!this.isLogin) {
        this.$msgBox({
          content: '请登录后再购买',
          isShowCancelBtn: false
        }).then(() => {
          this.$store.dispatch('REDIRECT_LOGIN');
        })
        return
      }

      this.showPay = true;
      this.payData = {course: this.courseInfo, payType: 'course'};
    }
  },
  mounted () {
      if(VueCookie.get("wangxinToken")==''||VueCookie.get("wangxinToken")==null){
          this.isLogin = false;
      }else{
          this.isLogin = true;
      }
  }

}

</script>

<style lang="scss" rel="stylesheet/scss">
  .detail_content {
    background: lightskyblue;
    .detail_body {
      width: 1200px;
      margin: 0 auto;
      height: 373px;
    }
  }
  .detail_header {
    li {
      float: left;
      line-height: 57px;
      margin: 0 10px;
      color: black;
      font-size: 14px;
      span {
        margin-left: 20px;
      }
      a {
        color: black;
        &:hover {
          text-decoration: none;
        }
      }
    }
  }
  .view_info {
    float: right;
    width: 570px;
    height: 295px;
    position: relative;
    p {
      font-size: 18px;
      color: black;
      font-weight: bold;
      word-break: break-all;
    }
    .foot_box {
      position: absolute;
      bottom: 0px;
      width: 100%;
      height: 36px;
    }
    .buy_btn {
      display: block;
      width: 140px;
      height: 38px;
      color: #fff;
      background: blue;
      border: none;
      border-radius: 0px;
      line-height: 38px;
      text-align: center;
      font-size: 16px;
      // position: absolute;
      // bottom: 0px;
      &:hover {
        text-decoration: none;
        color: #fff;
        cursor: pointer;
      }
    }
    .study_num {
      float: right;
      height: 36px;
      line-height: 36px;
      color: #999;
      font-size: 14px;
    }
    .huabei {
      color: #fff;
      font-size: 13px;
      position: absolute;
      bottom: 0;
      padding-left: 25px;
      img {
        width: 17px;
        height: 17px;
        position: absolute;
        left: 0;
      }
    }
  }
  .view_price {
    border-bottom: 1px solid rgb(102, 102, 102);
    padding-bottom: 10px;
    color: rgb(102, 102, 102);
    font-size: 14px;
    margin: 20px 0;
    span {
      font-size: 24px;
      color: #D51423;
      font-weight: bold;
      margin-left: 20px;
    }
    .font_16 {
      font-size: 16px;
      font-weight: 400;
    }
    .set_vip {
      color: #0099FF;
      margin-left: 6px;
    }
    .favo {
      font-size: 10px;
      display: inline-block;
      height: 14px;
      line-height: 14px;
      background: #D51423;
      color: #333;
      padding: 0 8px;
      border-radius: 0 7px 7px 0;
      margin-left: 7px;
    }
    .favo_right {
      background: none;
      border: 1px solid #D51423;
      height: 13px;
      color: #D51423;
      margin-left: -5px;
      border-radius: 7px 0 0 7px;
    }
    .small_favo {
      font-size: 10px;
      border: 1px solid #D51423;
      height: 13px;
      line-height: 14px;
      color: #D51423;
      padding: 0 5px;
    }
    .big_favo {
      color: #999;
      font-size: 14px;
      font-weight: 400;
      margin-left: 3px;
    }
  }
  .detail_view {
    float: left;
    width: 600px;
    height: 295px;
    background: rgb(102, 102, 102);
    background-size: 100%;
    &.float_win {
      position: fixed;
      right: 10px;
      bottom: 30px;
      z-index: 9999;
    }
  }
  .view_teacher {
    color: rgb(102, 102, 102);
    font-size: 14px;
    .text_b {
      margin-right: 20px;
      font-weight: bold;
    }
  }
</style>

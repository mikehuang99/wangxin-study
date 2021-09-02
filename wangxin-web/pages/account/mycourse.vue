<template>
  <div>
    <y-header></y-header>
    <div class="container account_cont clearfix">
      <y-side :type="'mycourse'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: num == -1}" @click="clicktab(-1)">我的课程</a>
          <span @click="printStudyRecord()" class="fr solid_btn">打印学习证明</span>
        </ul>
        <div class="notdata" v-if="notdata">
          ★暂时没有数据
        </div>
        <div class="person_info" v-if="!notdata">
          <div class="order_content" v-for="(item, index) in pageObj.list" :key="index">
            <div class="order_title clearfix">
            </div>
            <div class="order_body clearfix">
              <div style="float: left">
                <input type="checkbox" :value="item.id" v-model="pda.ids">
              </div>
              <div class="body_left clearfix">
                <div class="img_box fl" v-if="item.courseLogo">
                  <img :src="item.courseLogo" alt="">
              </div>
              <p class="fl">
                  {{item.courseName}}
                </p>
              </div>
              <ul class="body_right clearfix">
                <li class="money">学习进度：{{item.totalStudyProgress}}%</li>
                <li class="money">有效期至<br/>{{item.validTerm}}<br/>或者课程下架为止</li>
                <li>
                  <router-link :to="{name: 'view-courseDetail', query: {courseId: item.courseId}}" class="go_btn">进入学习</router-link>
                </li>
              </ul>
            </div>
          </div>
          <d-page v-if="pageObj.totalPage > 1 && !notdata" :page="pageObj" @btnClick="getPage"></d-page>
        </div>
      </div>
    </div>
    <d-paymodal class="" :data="payData" :orderNo="orderNo" @hidefun="showPay = false" v-if="showPay"></d-paymodal>
    <y-footer></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YFooter from '~/components/common/Footer'
import YSide from '~/components/account/Side'
import DPaymodal from '~/components/account/PayModal'
import { courseUserList} from '~/api/account/course.js'
import { saveStudyProof} from '~/api/account/user.js'

import DPage from '~/components/Page'
import {myHttp} from '~/utils/myhttp.js'
export default {
  data () {
    return {
      num: -1,
      showPay: false,
      payData: null,
      notdata: true,
      orderNo: '',
      pda: {
          ids:[]
      },
      obj: {
        orderStatus: 0,
        pageCurrent: 1,
        pageSize: 20
      },
      pageObj: {
        pageCurrent: '',
        pageSize: '',
        totalCount: '',
        totalPage: ''
      }
    }
  },
  methods: {
    printStudyRecord() {
       this.saveStudyProof();
    },
      saveStudyProof(){
        if(this.pda.ids.length==0){
            this.$msgBox({
                content: '必须勾选课程才能打印学习证明',
                isShowCancelBtn: false
            }).then(() => {
            }).catch(() => {
            })
            return;
        }
          saveStudyProof({"courseUserIds":this.pda.ids}).then(res => {
              let result = res.data
              if (result.code === 200) {
                  let routeUrl = this.$router.resolve({name: 'account-studyProof', query: {id: result.data.id}});
                  window.open(routeUrl.href, '_blank');
              } else {
                  if (result.code >= 300 && result.code < 400) {
                      if(result.code == 304){
                          this.$msgBox({
                              content: '该账号被异地登录',
                              isShowCancelBtn: false
                          }).then(() => {
                              this.$store.dispatch('REDIRECT_LOGIN', result.code)
                          }).catch(() => {
                              this.$store.dispatch('REDIRECT_LOGIN', result.code)
                          })
                      }
                      this.$msgBox({
                          content: '登录超时、异常或者被异地登录，请重新登录',
                          isShowCancelBtn: false
                      }).then(() => {
                          this.$store.dispatch('REDIRECT_LOGIN', result.code)
                      }).catch(() => {
                          this.$store.dispatch('REDIRECT_LOGIN', result.code)
                      })
                  } else {
                      this.$msgBox({
                          content: result.msg,
                          isShowCancelBtn: false
                      }).catch(() => {})
                  }
              }
          }).catch(() => {
          })
      },
    pay (orderNo) {
      //this.payData = item;
      this.showPay = true;
      this.orderNo = orderNo;
    },
    clicktab (int) {
      this.num = int;
      if(int==-1){
          this.obj.payStatus = '';
      }else{
          this.obj.payStatus = int;
      }
      this.obj.pageCurrent = 1;
      this.getOrderList();
    },
    getPage: function (int) {
      this.obj.pageCurrent = int;
      this.getOrderList();
    },
      getOrderList() {
          courseUserList(this.obj).then(res => {
              res = res.data;
              if (res.code === 200) {
                  //this.studyCardList = res.data.list || [];
                  this.pageObj = res.data;
                  if (res.data.list.length > 0) {
                      this.notdata = false;
                  } else {
                      this.notdata = true;
                  }
              } else {
                  if (res.code >= 300 && res.code < 400) {
                      this.$msgBox({
                          content: res.msg,
                          isShowCancelBtn: false
                      }).then(() => {
                          this.$store.dispatch('REDIRECT_LOGIN', result.code)
                      }).catch(() => {
                          this.$store.dispatch('REDIRECT_LOGIN', result.code)
                      })
                  } else {
                      this.$msgBox({
                          content: res.msg,
                          isShowCancelBtn: false
                      }).catch(() => {})
                  }
              }
          }).catch(() => {
          })
      }},
  mounted () {
    this.obj = {
      orderStatus: 0,
      pageCurrent: 1,
      pageSize: 10
    }
    this.getOrderList();
  },
  components: {
    YHeader,
    YFooter,
    YSide,
    DPage,
    DPaymodal
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
@import '~/assets/css/account.scss';
  .person_info {
    padding: 25px;
    min-height: 400px;
  }
  .order_content {
    border-bottom: 2px solid rgba(245, 245, 245, 1);
    margin-top: 20px;
    position: relative;
    &:first-child {
      margin-top: 0;
    }
  }
  .order_title {
    line-height: 34px;
    padding-left: 20px;
    background: rgba(245, 245, 245, 1);
    font-size: 14px;
    color: #999;
    .order_type {
      margin-right: 10px;
      color: #0099FF;
    }
    .time {
      margin-left: 40px;
    }
  }
  .order_body {
    margin: 15px 0;
  }
  .body_left {
    padding: 0 20px;
    float: left;
    // position: relative;
    .img_box {
      width: 122px;
      height: 62px;
      display: inline-block;
    }
    img {
      width: 122px;
      height: 62px;
    }
    p {
      // position: absolute;
      width: 200px;
      white-space: inherit;
      margin-left: 10px;
      // left: 160px;
      font-size: 14px;
      color: #999;
    }
  }
  .body_right {
    float: right;
    li {
      float: left;
      height: 59px;
      width: 180px;
      text-align: center;
      border-left: 2px solid rgba(245, 245, 245, 1);
      font-size: 14px;
      color: blue;
      &.alipay {
        color: #0099FF;
      }
      &.money {
        color: #D51423;
      }
      .cancel {
        display: block;
        text-align: center;
        margin-top: 5px;
        color: #999;
        &:hover {
          color: #D51423;
        }
      }
      .go_btn {
        display: block;
        width: 76px;
        height: 30px;
        margin-left: 10px;
        line-height: 30px;
        background: #fff;
        border-radius: 0px;
        font-size: 14px;
        cursor: pointer;
        color: #999;
        border: 1px solid #999;
        &.go_pay {
          border-color: orange;
          color: orange;
        }
        &:hover {
          color: blue;
          border-color: blue;
        }
      }
    }
  }
  .notdata{
    text-align: center;
    padding: 60px 0;
    font-size: 30px;
    color: #999;
    min-height: 400px;
    i{
      font-size: 30px;
      padding-right: 12px;
    }
  }
  .page {
     margin-bottom: 20px;
     margin-top: 10px;
  }
</style>

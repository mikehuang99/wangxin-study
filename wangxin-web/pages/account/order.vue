<template>
  <div>
    <y-header></y-header>
    <div class="container account_cont clearfix">
      <y-side :type="'wddd'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: num == -1}" @click="clicktab(-1)">全部订单</a>
          <a class="tab" :class="{on: num == 0}" @click="clicktab(0)">待支付订单</a>
          <a class="tab" :class="{on: num == 1}" @click="clicktab(1)">已支付订单</a>
          <a class="tab" :class="{on: num == 2}" @click="clicktab(2)">已过期订单</a>
          <a class="tab" :class="{on: num == 3}" @click="clicktab(3)">已取消订单</a>
        </ul>
        <div class="notdata" v-if="notdata">
          ★暂时没有数据
        </div>
        <div class="person_info" v-if="!notdata">
          <div class="order_content" v-for="(item, index) in pageObj.list" :key="index">
            <div class="order_title clearfix">
              <span class="order_num">订单号：{{item.orderNo}}</span>
              <span class="time">订单创建时间：{{item.createTime}}</span>
            </div>
            <div class="order_body clearfix">
              <div class="body_left clearfix">
                <div class="img_box fl" v-if="item.courseLogo">
                  <img :src="item.courseLogo" alt="">
                </div>
                <p class="fl">
                  {{item.courseName}}
                </p>
              </div>
              <ul class="body_right clearfix">
                <li class="money">原价￥{{item.payableAmount}}</li>
                <li class="money">需付￥{{item.payAmount}}</li>
                <li>{{item.payTypeAndAmountStr}}</li>
                <li v-if="item.payType == 1">支付宝支付</li>
                <li v-if="item.payType == 2">微信支付</li>
                <li class="alipay" v-if="item.payStatus == 0">等待支付</li>
                <li class="alipay" v-if="item.payStatus == 1">已支付</li>
                <li class="alipay" v-if="item.payStatus == 2">已过期</li>
                <li class="alipay" v-if="item.payStatus == 3" style="color: #e01c2b">已取消</li>
                <li v-if="item.payStatus == 1">
                  <router-link :to="{name: 'view-courseDetail', query: {courseId: item.courseId}}" class="go_btn">进入学习</router-link>
                </li>
                <li v-else-if="item.payStatus == 0">
                  <a href="javascript:" class="go_btn go_pay" @click="pay(item.orderNo)">继续支付</a>
                  <a href="javascript:" class="cancel" @click="getOrderInfo(item.orderNo)">我已付款</a>
                  <a href="javascript:" class="cancel" @click="cancelOrder(item.orderNo)">取消订单</a>
                </li>
                <li v-else-if="item.payStatus == 2 || item.payStatus == 3">
                  订单无效
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
import {orderList, cancelOrder} from '~/api/account/course.js'
import DPage from '~/components/Page'
import {myHttp} from '~/utils/myhttp.js'
import {checkPayResult} from '~/api/order.js'

export default {
  data () {
    return {
      num: -1,
      showPay: false,
      payData: null,
      notdata: true,
      orderNo: '',
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
      getOrderInfo (no) {
          let that = this;
          if (this.checkPay) {
              return false;
          }
          checkPayResult({orderNo: no}).then(res => {
              res = res.data
              console.log(res)
              if (res.data.payStatus === 0) {
                  /*
                  setTimeout(function () {
                      that.getOrderInfo(no);
                  }, 2000);*/
                  this.$msgBox({
                      content: '未完成付款',
                      isShowCancelBtn: false
                  }).then(res => {
                  }).catch(() => {
                  })
                  return;
              } else if (res.data.payStatus === 1) {
                  //this.close();
                  this.$msgBox({
                      content: '购买成功',
                      isShowCancelBtn: false
                  }).then(res => {
                      window.location.reload();
                  }).catch(() => {
                      window.location.reload();
                  })
              } else {
                  //that.payStep = 2;
                  this.$msgBox({
                      content: '未完成付款',
                      isShowCancelBtn: false
                  }).then(res => {
                  }).catch(() => {
                  })
                  return;
              }
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
          orderList(this.obj).then(res => {
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
      },
      cancelOrder(orderNo) {
          cancelOrder({'orderNo':orderNo}).then(res => {
              res = res.data;
              if (res.code === 200) {
                  //this.studyCardList = res.data.list || [];
                 this.getOrderList();
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
      },
    closeOrder (orderNo) {
      myHttp.call(this, {
        method: orderClose,
        params: {orderNo}
      }).then(res => {
        this.$msgBox({
          content: '取消成功',
          isShowCancelBtn: false,
          edit: false
        }).then(async (val) => {
          this.getOrderList()
        }).catch(() => {
          this.getOrderList()
        })
      })
    }
  },
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
      width: 100px;
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

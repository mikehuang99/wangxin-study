<template>
  <div>
    <y-header></y-header>
    <div class="container account_cont clearfix">
      <y-side :type="'myexammanagement'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: num == -1}" @click="clicktab(-1)">我的考试</a>
        </ul>
        <div class="notdata" v-if="notdata">
          ★暂时没有数据
        </div>
        <div class="person_info" v-if="!notdata">
          <div class="exam_management_content">
            <div class="order_title clearfix">
            </div>
            <div class="exam_management_body clearfix">
              <ul class="exam_management_body_detail clearfix">
                <li class="money_title">考试标题</li>
                <li class="money_title">考试类型</li>
                <li class="money_title">考试时间</li>
                <li class="money_title">课程名称</li>
                <li class="money_title" style="text-align: left;"><span style="margin-left: 10px">操作</span></li>
              </ul>
            </div>
          </div>
          <div class="exam_management_content" v-for="(item, index) in pageObj.list" :key="index">
            <div class="order_title clearfix">
            </div>
            <div class="exam_management_body clearfix">
              <ul class="exam_management_body_detail clearfix">
                <li class="money">{{item.title}}</li>
                <li class="money" v-if="item.examType==1">定时考试</li>
                <li class="money" v-if="item.examType==2">时段考试</li>
                <li class="money">{{item.startTime}}<br/>至<br/>{{item.endTime}}</li>
                <li class="money">{{item.courseName}}</li>
                <li>
                  <router-link :to="{name: 'account-enterexam', query: {examManagementId: item.id}}" class="mep_go_btn">进入考试</router-link>
                  <router-link :to="{name: 'account-myexampaper', query: {examManagementId: item.id,examPaperId:item.examPaperId}}" class="mep_go_btn">回顾考试</router-link>
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
import { queryExamManagementsByStudentUserNo} from '~/api/account/course.js'
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
          queryExamManagementsByStudentUserNo(this.obj).then(res => {
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
  .exam_management_content {
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
  .exam_management_body {
    margin: 15px 0;
  }
  .exam_management_body_left {
    padding: 0 20px;
    float: left;
    // position: relative;
    .img_box {
      width: 122px;
      height: 42px;
      display: inline-block;
    }
    img {
      width: 122px;
      height: 42px;
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
  .exam_management_body_detail {
    float: left;
    li {
      float: left;
      height: 42px;
      width: 180px;
      text-align: center;
      border-left: 2px solid rgba(245, 245, 245, 1);
      font-size: 14px;
      color: blue;
      &.alipay {
        color: #0099FF;
      }
      &.money_title {
        color: blue;
        font-weight: bold;
        font-size: 16px;
      }
      &.money {
        color: black;
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
      .mep_go_btn {
        display: block;
        width: 76px;
        height: 20px;
        margin-left: 10px;
        line-height: 20px;
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

<template>
  <div>
    <y-header></y-header>
    <div class="container account_cont clearfix">
      <y-side :type="'teacherUserStudyLog'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab" :class="{on: num == -1}" @click="clicktab(-1)">学生学习记录</a>
        </ul>
        <br/>
        <br/>
        <div style="margin-left: 20px">
          <div style="float:left;margin-right: 10px;font-size: 18px;font-weight: bold">课程名称:</div>
          <div style="float: left;">
            <input type="text" v-model="obj.courseName" style="height: 25px;width: 500px" placeholder="请输入课程名称">
          </div>
          <div @click="queryCourses" v-if="queryState==false" class="fr solid_btn">
            查询
          </div>
          <div v-if="queryState==true" class="fr solid_btn">
            查询中...
          </div>
        </div>
        <br/>
        <br/>
        <div class="notdata" v-if="notdata">
          ★暂时没有数据
        </div>
        <div class="person_info" v-if="!notdata">
          <div class="order_content" v-for="(item, index) in pageObj.list" :key="index">
            <div class="order_title clearfix">
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
                <li>
                  <router-link :to="{name: 'teacherUserStudyLog-studyCourseObjectList', query: {courseId: item.id,courseName: item.courseName}}" class="go_btn">学习情况</router-link>
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
import { queryFreshCoursesOnUserStudyLog} from '~/api/account/user.js'
import DPage from '~/components/Page'
import {myHttp} from '~/utils/myhttp.js'
export default {
  data () {
    return {
        queryState: false,
      num: -1,
      showPay: false,
      payData: null,
      notdata: true,
      orderNo: '',
      obj: {
          courseName:'',

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
      this.queryCourses();
    },
    getPage: function (int) {
      this.obj.pageCurrent = int;
      this.queryCourses();
    },
      queryCourses() {
        this.queryState = true;
          queryFreshCoursesOnUserStudyLog(this.obj).then(res => {
              this.queryState = false;
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
          this.queryState = false;
      }},
  mounted () {
    this.obj = {
      orderStatus: 0,
      pageCurrent: 1,
      pageSize: 10
    }
    this.queryCourses();
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

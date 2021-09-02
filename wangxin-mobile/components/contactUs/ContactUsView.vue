<template>
  <div>
    <div>
       <div class="account_item">
         <div class="account_item_left" v-html="website.mobileContactUs">
         </div>
      </div>
    </div>
    <div style="height: 70px">
      <Footer></Footer>
    </div>
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" :toUrlParams="toUrlParams" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
	</div>
</template>
<script>
import loading from '~/components/common/loading.vue'
import noContent from '~/components/common/no_content/no_content.vue'
import {loadMore} from '~/plugins/loadMorePlugin.js'
import {listUserStudyCard,checkBeforebindStudyCardUserNo,bindStudyCardUserNo} from '~/api/market'
import {userView,userUpdate} from '~/api/user'
import { logout } from '~/store/common'
import Footer from '~/components/myAccount/Footer.vue'
import Alert from "~/components/common/alert";

import {findWebsite} from '~/api/index'

export default {
    data () {
    return {
        isShowAlert: false,
        timeOutFlag: false,
        toUrl: '',
        alertTitle: '',
        alertContent: '',
        toUrlParams: [],

        touchend: false,
        studyLogArr: [],
        pageOnShowing: false,//页面是否正在显示
      // 请求条数
      offset: 0,

      // 是否显示加载动画
      showLoading: false,

      // 无内容样式
      noContent: {
        class: 'class',
        //name: '没有找到课程'
        name: '什么也没有'
      },
      obj: {
          courseName: ''
      },
        website:'',
      page: {
          pageCurrent: 0,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
      },
        userInfo:{
            age: '',
            headImgUrl: '',
            id: '',
            identityCardNo: '',
            mobile: '',
            nickname: '',
            realName: '',
            remark: '',
            sex: '',
            userNo: '',
            userType: 2
        },
        isUpdate: false

    }
  },
  mounted () {
      //进入页面钩子函数代码开始
      this.$nextTick(()=>{
          this.pageOnShowing = true;//进入页面设为true
      });
      //this.userView();
      this.getWebsite();

  },
  //离开当前页面后执行
  destroyed: function () {
      this.pageOnShowing = false;//离开页面设为false

  },
  props: ['courseName'],
  mixins: [loadMore],
  computed: {

  },
  components: {
    loading,
    noContent,
      Footer,
      Alert
  },
    filters:{


    },
  methods: {
      closeAlert(){
          this.isShowAlert = false;
      },
      toCancelUpdateUserInfo(){
          this.isUpdate = false;
      },
      toUpdateUserInfo(){
          this.isUpdate = true;
      },
      updateUserInfo(){
          userUpdate(this.userInfo).then(res => {
              res = res.data;
              if (res.code === 200) {
                  this.isUpdate = false;
              }else if (res.code > 300 && res.code < 400) {
                  if(res.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              } else {
                  console.log('88999！')
                  this.isShowAlert = true;
                  this.alertTitle = "提示";
                  this.alertContent = res.msg;
                  return;
              }
          }).catch(() => {
              console.log("请求异常");
          });
      },
      userView(){
          userView({orgNo: 'wx'}).then(res => {
              res = res.data;
              if (res.code === 200) {
                  this.userInfo = res.data;
              }else if (res.code > 300 && res.code < 400) {
                  if(res.code === 304){
                      console.log("该账号被异地登录");
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }
              } else {
                  console.log('88999！')
              }
          }).catch(() => {
              console.log("请求异常");
          });
      },
      getWebsite() {
          findWebsite().then(res => {
              if (res.data.code === 200) {
                  this.website = res.data.data;
              } else {
                  console.log('findWebsite请求错误');
              }
          });
      },
  },
  watch: {

  }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .course_child_list {
    .course_child_con {
      background: #fff;
  }
    &.n{
      padding: 0;
      .course_child_con{
        border-bottom: 1px solid #D0D0D0;
        border-top: 1px solid #D0D0D0;
        -webkit-box-shadow: none;
        box-shadow: none;
        padding: 0 10px;
        margin: 0 0 10px 0;
        .course_content_box{
          overflow: hidden;
          margin-left: 1px;
          margin-right: 1px;
          //border: solid 1px red;
          border-radius: 5px;
          background-color: #fff;
          .context_area{
            //float: right;
            li {
              margin-bottom: 0.7rem;
              font-size: 1.2rem;
            }
            .course_title_h1{
              h1 {
                font-size: 1.3rem;
                margin-top: 5px;
              }
              i {
                display: inline-block;
                overflow: hidden;
                max-width: 90%;
                text-overflow: ellipsis;
                white-space: pre-line;
              }
              span {
                display: inline-block;
                margin-top: 0.5rem;
                margin-left: 3px;
              }
          }
            .course_item {
              text-overflow: ellipsis;
              max-width: 300px;
              white-space: nowrap;
              overflow: hidden;
              padding-left: 18px;
              background: url('/wxmobile/img/wxImg/item.png') no-repeat 0 3px;
              background-size: 12px 12px;
            }
          }
      }
    }
  }
  }
  .no_data {
    text-align: center;
    line-height: 2rem;
    font-size: 1rem;
    color: #666;
  }
  .loading-enter-active, .loading-leave-active {
    transition: opacity 1s
  }
  .loading-enter, .loading-leave-active {
    opacity: 0
  }
  .btn_box {
    float: right;
    width: 68px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
  .cancel_btn_box {
    float: left;
    width: 68px;
    background-color: red;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
  .continue_btn_box {
    float: right;
    width: 68px;
    background-color: green;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
  .studyCard_sty {
    width: 100%;
    height: 100px;
    background-image: radial-gradient(circle at 18px 16px ,transparent 0%, transparent 16px,#FF7744  16px, #FF7744  10%);
    background-position: 250px -16px;
    background-size: 100% 100px;
  }
  .add_study_card_btn_box {
    width: 150px;
    border-radius: 5px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    cursor: pointer;
  }
  .account_item{
    font-size: 18px;
    font-weight: bold;
    line-height:40px
  }
  .account_item_left{
    margin-left:20px;
    display: inline-block
  }
  .account_item_right{
    width: 220px;
    margin-right: 10px;
    display: inline-block;
    float: right
  }
  .account_item_right input{
    //background-size: 13px 50px;
    width: 100%;
    background-position: 3%;
    font-size: 18px;
  }
  .account_item_right select{
    //background-size: 13px 50px;
    width: 100%;
    background-position: 3%;
    font-size: 18px;
  }
</style>

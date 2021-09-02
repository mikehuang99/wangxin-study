<template>
  <div class="login_contain">
    <div class="title_contain">
      <span class="title">{{website.websiteTitle}}</span>
    </div>
    <div style="text-align: center;font-size: 20px;font-weight: bold;color: red">用户登录</div>
    <div class="login_form">
        <div class="mobile_contain">
          <div class="mobile">
            <span style="float: right;margin-top: 8px;background-color: #4CAF50;color: white">手机号码</span>
          </div>
          <div style="display: inline-block;">
            <input style="height: 30px;width: 200px" type="text" v-model="pobj.mobile" placeholder="输入手机号">
          </div>
        </div>
        <br/>
        <div class="mobile_contain">
          <div class="mobile">
            <span style="float: right;margin-top: 8px;background-color: #4CAF50;color: white">密<span style="opacity:0;">密码</span>码</span>
          </div>
          <div style="display: inline-block;">
            <input style="height: 30px;width: 200px" type="password" v-model="pobj.password" placeholder="输入密码">
          </div>
      </div>
      <div class="login_button_contain">
        <span class="btn_box" @click="login">登<span style="opacity:0;">旺鑫</span>录</span>
      </div>
      <p class="return_index">
        <a @click="toIndex" style="text-decoration: underline">返回首页</a>
        <a @click="toReg" style="text-decoration: underline">去注册</a>
        <a @click="toReset" style="text-decoration: underline;float: right">忘记密码?</a>
      </p>
    </div>
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
  </div>
</template>
<script>
import { setStore,getStore,removeStore } from '~/store/common'
import Alert from "../components/common/alert";
import {userLogin,userView} from '~/api/user'
import {findWebsite} from '~/api/index'
import VueCookie from 'vue-cookie'


export default {
  data () {
    return {
        isShowAlert: false,
        timeOutFlag: false,
        toUrl: '',
        alertTitle: '',
        alertContent: '',
        phone: '',
        password: '',
        pobj:{
            mobile: '',
            password: '',
            clientId: ''
        },
        website: ''
    }
  },
  head: {

  },
  mounted(){
      this.getWebsite();
  },
  methods: {
      toIndex(){
          this.$router.push({path: '/', query: {}})
      },
      toReset(){
          this.$router.push({path: '/reset', query: {}})
      },
      toReg(){
          this.$router.push({path: '/reg', query: {}})
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
      closeAlert(){
          this.isShowAlert = false;
      },
      login () {
          if (!/^1[2|3|4|5|6|7|8|9][0-9]\d{8}$/.test(this.pobj.mobile.trim())|| this.pobj.mobile.trim().length !== 11) {
              this.isShowAlert = true;
              this.alertTitle = "参数错误";
              this.alertContent = "请输入正确的手机号码";
              return false;
          }
          if (this.pobj.password.length < 6) {
              this.isShowAlert = true;
              this.alertTitle = "参数错误";
              this.alertContent = "密码不能少于6位";
              return false;
          }
          this.$nuxt.$loading.start();
          userLogin(this.pobj).then(res => {
              this.$nuxt.$loading.finish();
              if (res.data.code === 200) {
                  VueCookie.set("wangxinMobileToken", res.data.data.token, {expires: 60});
                  userView({orgNo: 'wx'})
                      .then(res => {
                          if (res.data.code == 200) {
                              VueCookie.set("wangxinMobileUserInfo", JSON.stringify(res.data.data), {expires: 60});
                          } else if (res.data.code > 300 && res.data.code < 400) {
                              this.isShowAlert = true;
                              this.alertTitle = "获取用户信息失败";
                              this.alertContent = "获取用户信息失败";
                              return;
                          }
                      });

                  let lastUrlTemp = getStore("lastUrlTemp");
                  if(lastUrlTemp){
                      removeStore("lastUrlTemp");
                      //this.$router.push({path: lastUrlTemp, query: {}});
                      window.location.replace(lastUrlTemp)
                  }else{
                      this.isShowAlert = true;
                      this.alertTitle = "登录成功";
                      this.alertContent = "登录成功";
                      this.timeOutFlag = true;
                      this.toUrl = "/index";
                  }


                  /*
                  getUserInfo({orgNo: 'wx'})
                      .then(res => {
                          if (res.data.code == 200) {
                              VueCookie.set("wangxinUserInfo", JSON.stringify(res.data.data), {expires: 1});
                          } else if (res.data.code > 300 && res.data.code < 400) {
                              this.$msgBox({
                                  content: '获取用户信息失败,请稍后再试',
                                  isShowCancelBtn: false
                              }).catch(() => {})
                              return;
                          }
                      });

                  this.$store.commit('GET_TEMPORARYURL');

                  this.$store.dispatch('GET_USERINFO',store=>{
                      //this.userInfo = this.$store.state.userInfo;
                      console.log("=====>"+this.userInfo)
                      //window.location.href = this.$store.state.temporaryUrl;
                      this.$router.push({path: '/index', query: {}})
                  });*/
              } else {
                  this.isShowAlert = true;
                  this.alertTitle = "参数错误";
                  this.alertContent = res.data.msg;
                  return false;
              }
          }).catch(() => {
              this.$nuxt.$loading.finish();
              this.isShowAlert = true;
              this.alertTitle = "参数错误";
              this.alertContent = "登录失败，请稍后重试";
              return false;
          })
          return false;
      }
  },
  components: {
      Alert
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .login_contain{
    width: 100%;
    height: 100%;
  }
  .title_contain{
    margin-top: 40%;
    text-align: center
  }
  .title_contain .title{
    font-weight: bold;
    font-size: 20px
  }
  .login_form{
    margin-top: 10px;
    position: relative;
  }
  .login_form .mobile_contain{
    margin-left:10px;
    text-align: left;
    margin-top: 10px;
  }
  .mobile_contain .mobile{
    display: inline-block;
    width: 100px;
    font-size: 20px;
    font-weight: bold;
    height: 30px;
  }
  .login_button_contain{
    text-align: center;
    margin-top: 20px;
  }
  .return_index{
    text-align: left;
    font-size:20px;
    margin-top: 20px;
  }
  .btn_box {
    width: 200px;
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
</style>

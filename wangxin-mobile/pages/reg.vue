<template>
  <div class="login_contain">
    <div class="title_contain">
      <span class="title">{{website.websiteTitle}}</span>
    </div>
    <div style="text-align: center;font-size: 20px;font-weight: bold;color: red">用户注册</div>
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
          <span style="float: right;margin-top: 8px;background-color: #4CAF50;color: white">图片验证码</span>
        </div>
        <div style="display: inline-block;">
          <input style="height: 30px;width: 100px" type="text" v-model="pobj.verifyCode" placeholder="输入图片验证码">
        </div>
        <div style="display: inline-block;">
          <img id="verifyCode"  @click="updateVerifyImgUrl"/>
        </div>
      </div>
      <br/>
      <div class="mobile_contain">
        <div class="mobile">
          <span style="float: right;margin-top: 8px;background-color: #4CAF50;color: white">手机验证码</span>
        </div>
        <div style="display: inline-block;">
          <input style="height: 30px;width: 100px" type="text" v-model="pobj.code" placeholder="输入手机验证码">
        </div>
        <div style="display: inline-block;">
          <button class="send_mobile_verify_code" :disabled="mobileCodeDisabled" @click="getMobileCode">{{mobileCodeTxt}}</button>
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
      <div class="mobile_contain">
        <div class="mobile">
          <span style="float: right;margin-top: 8px;background-color: #4CAF50;color: white">重复密码</span>
        </div>
        <div style="display: inline-block;">
          <input style="height: 30px;width: 200px" type="password" v-model="pobj.repassword" placeholder="重新输入输入密码">
        </div>
      </div>
      <xieyi v-if="isShowXieyi"></xieyi>
      <div style="margin-top: 10px;font-size: 18px;text-align: center">
        <input type="checkbox" id="tongyi" class="checkbox-agree" v-model="pobj.check"/>
        同意<span style="text-align:center;text-decoration: underline;" @click="showXieyi">《用户协议》</span>
      </div>
      <div class="login_button_contain">
        <span class="btn_box" @click="register">注<span style="opacity:0;">旺鑫</span>册</span>
      </div>
      <p class="return_index">
        <a @click="toIndex" style="text-decoration: underline">返回首页</a>
        <a @click="toLogin" style="text-decoration: underline">去登录</a>
        <a @click="toReset" style="text-decoration: underline;float: right">忘记密码?</a>
      </p>
    </div>
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
  </div>
</template>
<script>
    import { setStore } from '~/store/common'
    import {findWebsite} from '~/api/index'
    import {getMobileCode,register} from '~/api/index'

    import xieyi from './xieyi.vue';

    import {
        baseUrl
    } from '~/util/client'
    import Alert from "../components/common/alert";

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
                pobj: {
                    mobile: '',
                    code: '',
                    password: '',
                    repassword: '',
                    check: false,
                    randomKey: '',
                    verifyCode: ''
                },
                website:'',
                isShowXieyi: false,
                mobileCodeDisabled: false,
                mobileCodeTxt: '发送手机验证码'

            }
        },
        head: {

        },
        mounted(){
            this.updateVerifyImgUrl();
            this.getWebsite();

        },
        methods: {
            toIndex(){
                this.$router.push({path: '/', query: {}})
            },
            toReset(){
                this.$router.push({path: '/reset', query: {}})
            },
            toLogin(){
                this.$router.push({path: '/login', query: {}})
            },
            closeAlert(){
                this.isShowAlert = false;
            },
            getMobileCode (event) {
                if (!this.pobj.randomKey) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "随机字符串为空，程序异常";
                    return false;
                }
                if (!/^1[2|3|4|5|6|7|8|9][0-9]\d{8}$/.test(parseInt(this.pobj.mobile))) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "请填写正确手机号码";
                    return false;
                }
                if (!this.pobj.verifyCode) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "请输入图片验证码";
                    return false;
                }
                getMobileCode({
                    clientId: 'dskfksdjkf87328423kskdjdsfkkwieikk',
                    mobile: this.pobj.mobile,
                    verifyCode: this.pobj.verifyCode,
                    randomKey: this.pobj.randomKey
                }).then(res => {
                    if (res.data.code === 200) {
                        this.mobileCodeTimeOut();
                    } else {
                        this.isShowAlert = true;
                        this.alertTitle = "请求错误";
                        this.alertContent = res.data.msg;
                        this.mobileCodeTxt = "重新发送验证码";
                    }
                })
            },
            mobileCodeTimeOut () {
                this.mobileCodeDisabled = true;
                let maxSeconds = 60;
                this.mobileCodeTxt = maxSeconds;
                let timeOutJob = setInterval(() => {
                    if (maxSeconds <= 0) {
                        clearInterval(timeOutJob);
                        this.mobileCodeTxt = '重新获取验证码';
                        this.mobileCodeDisabled = false;
                    } else {
                        maxSeconds--;
                        this.mobileCodeTxt = maxSeconds + 's';
                    }
                }, 1000)
            },
            showXieyi(){
              this.isShowXieyi = true;
            },
            /**
             * 产生36位UUID
             * @returns {string}
             */
            generalUUID() {
                return (this.return4Bit() + this.return4Bit() + "-" + this.return4Bit() + "-" + this.return4Bit() + "-" + this.return4Bit() + "-" + this.return4Bit() + this.return4Bit() + this.return4Bit());
            },
            //产生四位字符
            return4Bit()
            {
                return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
            },
            updateVerifyImgUrl(){
                this.pobj.randomKey = this.generalUUID();
                document.images.verifyCode.src=baseUrl+"/pccommon/user/user/getVerifyCode"+"?timeStamp="+new Date()+"&randomKey="+this.pobj.randomKey;
            },
          getWebsite() {
                findWebsite().then(res => {
                  if (res.data.code === 200) {
                      this.website = res.data.data;
                  } else {
                      console.log('88999！')
                  }
            });
          },
            // 注册
            register: function () {
                if (!/^1[2|3|4|5|6|7|8|9][0-9]\d{8}$/.test(this.pobj.mobile.trim())|| this.pobj.mobile.trim().length !== 11) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "请输入正确的手机号码";
                    return false;
                }
                if (!this.pobj.code || this.pobj.code.length !== 6) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "请输入正确的手机验证码";
                    return false;
                }
                if (this.pobj.password.length < 6 || this.pobj.password.length > 16) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "请输入6-16位的登录密码,区分大小写,不要有空格";
                    return false;
                }
                if (this.pobj.password !== this.pobj.repassword) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "两次密码不一致";
                    return false;
                }
                if (!this.pobj.check) {
                    this.isShowAlert = true;
                    this.alertTitle = "参数错误";
                    this.alertContent = "请先阅读用户协议";
                    return false;
                }
                this.pobj.agent = '12';
                this.pobj.clientId = '222';
                this.pobj.cookie = '12';
                this.pobj.ip = '12';
                // console.log(this.pobj)
                this.$nuxt.$loading.start();
                register(this.pobj).then(res => {
                    console.log(res)
                    this.$nuxt.$loading.finish();
                    if (res.data.code === 200) {
                        this.pobj = {
                            mobile: '',
                            code: '',
                            password: '',
                            repassword: '',
                            check: false
                        }
                        this.isShowAlert = true;
                        this.alertTitle = "注册成功";
                        this.alertContent = "注册成功";
                        this.timeOutFlag = true;
                        this.toUrl = "/login";
                        //console.log("注册成功")
                        //this.$router.push({path: '/login'});
                    } else {
                        this.isShowAlert = true;
                        this.alertTitle = "注册失败";
                        this.alertContent = res.data.msg;
                        return false;
                    }
                }).catch(() => {
                    this.$nuxt.$loading.finish();

                    this.isShowAlert = true;
                    this.alertTitle = "注册失败";
                    this.alertContent = "注册失败";
                    return false;
                })
            }
        },
        components: {
            Alert,
            xieyi
        }
    }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .login_contain{
    width: 100%;
    height: 100%;
    z-index: 1;
  }
  .title_contain{
    margin-top: 20%;
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
    font-size: 18px;
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
  .send_mobile_verify_code {
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    height: 30px;
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
  .checkbox-agree{
    width: 20px;
    height: 20px;
    cursor: default;
    background-color: initial;
    appearance: checkbox;
    padding: initial;
    box-sizing: border-box;
    border: initial;
  }
</style>

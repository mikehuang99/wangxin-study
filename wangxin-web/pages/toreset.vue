<template>
  <div>
    <!--
    <div class="outer_label">
      <img class="inner_label login_logo" @click="toIndex" :src="webInfo.logoUrl">
    </div>-->
    <y-header :active="'index'"></y-header>
    <div class="login_form">
      <div>
        <div style="float:left;margin-top: 15px;font-size: 18px">手机号码：</div>
        <input type="text"  class="qxs-ic_user qxs-icon" placeholder="请输入手机号码" v-model="pobj.mobile">
      </div>
      <div class="error_msg">{{errTip1}}</div>
      <div>
        <div style="float:left;margin-top: 15px;font-size: 18px">图片验证码：</div>
        <input type="text" class="qxs-ic_user qxs-icon"  placeholder="请输入图片验证码"  v-model="pobj.verifyCode" maxlength="6">
        <img id="verifyCode"  @click="updateVerifyImgUrl"/>
        <span>点击图片可以刷新验证码</span>
      </div>
      <div>
          <div style="float:left;margin-top: 15px;font-size: 18px">验证码：</div>
          <input type="text" class="qxs-ic_user qxs-icon"  placeholder="请输入手机验证码"  v-model="pobj.code" maxlength="6">
          <y-button :mobile="pobj.mobile" :verifyCode="pobj.verifyCode" :randomKey="pobj.randomKey"></y-button>
      </div>
      <div>
        <div style="float:left;margin-top: 15px;font-size: 18px">密码：</div>
        <input type="password"  class="qxs-ic_password qxs-icon"  placeholder="请输入密码" v-model="pobj.newPassword">
      </div>
      <div>
        <div style="float:left;margin-top: 15px;font-size: 18px">再次输入密码：</div>
        <input type="password"  class="qxs-ic_password qxs-icon"  placeholder="请再次输入密码" v-model="pobj.confirmPassword">
      </div>
    </div>
    <div class="login_button_label">
      <button class="login_btn"  type="primary"  @click="resetPassword" round :loading="isBtnLoading">重置密码</button>
      <div style="padding-top: 10px">
        <span style="font-size:18px;color: #A9A9AB;text-decoration:underline"><router-link to="/tologin">登陆</router-link></span>
        <span style="font-size:18px;color: #A9A9AB;text-decoration:underline"><router-link to="/register">注册</router-link></span></div>
    </div>
    <y-footer></y-footer>
  </div>
</template>

<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import {userLogin, getUserInfo, register} from '~/api/user.js'
    import {updatePassword} from '~/api/account/user.js'
    import YButton from '~/components/common/CodeButton'

    export default {
        components: {
            YHeader,
            YFooter,
            YButton
        },
        name: "tologin",
        data () {
            return {
                userName: '',
                password: '',
                isBtnLoading: false,
                webInfo: this.$store.state.webInfo,
                obj:{
                    mobile: '',
                    password: '',
                    clientId: ''
                },
                errTip1: '',
                errTip2: '',
                pobj: {
                    mobile: '',
                    code: '',
                    newPassword: '',
                    confirmPassword: '',
                    randomKey: '',
                    verifyCode: ''
                },
                xieyi: false,
                service: {}
            }
        },
        mounted(){
            if (this.webInfo) {
                this.service = this.webInfo
            }
            this.updateVerifyImgUrl();

        },
        methods: {
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
                document.images.verifyCode.src="/pccommon/user/user/getVerifyCode"+"?timeStamp="+new Date()+"&randomKey="+this.pobj.randomKey;
            },
            toIndex(){
                this.$router.push({name: 'index'})
            },
            showMsg (msg) {
                this.$msgBox({
                    content: msg,
                    isShowCancelBtn: false
                }).catch(() => {})
            },
            resetPassword (e) {
                e.preventDefault()
                if (!(/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(this.pobj.mobile.trim()))) {
                    this.showMsg('请输入正确格式的手机号码')
                    return
                }
                if (this.pobj.code.length !== 6) {
                    this.showMsg('请输入正确的验证码')
                    return
                }
                if (this.pobj.newPassword.length < 6 || this.pobj.newPassword.length > 16) {
                    this.showMsg('请输入6-16位的登录密码,区分大小写,不可有空格')
                    return
                }
                if (this.pobj.newPassword !== this.pobj.confirmPassword) {
                    this.showMsg('两次输入密码不一致')
                    return
                }
                this.pobj.clientId = this.$store.state.clientData.id
                console.log(this.pobj)
                updatePassword(this.pobj).then(res => {
                    let result = res.data
                    if (result.code === 200) {
                        this.$msgBox({
                            content: '修改成功',
                            isShowCancelBtn: false
                        }).then(() => {
                            this.$store.commit('SIGN_OUT')
                            this.$router.push({name: 'tologin'})
                        }).catch(() => {
                            this.$store.commit('SIGN_OUT')
                            this.$router.push({name: 'tologin'})
                        })
                    } else {
                        this.$msgBox({
                            content: result.msg,
                            isShowCancelBtn: false
                        }).catch(() => {})
                    }
                }).catch(() => {
                    this.showMsg('系统可能繁忙，请稍后再重试')
                })
            }
        }
    }
</script>

<style scoped>
  body {
    padding: 0;
    margin:0;
    font-family: "Microsoft YaHei UI Light";
  }
  .outer_label {
    position: relative;
    left: 0;
    top: 0;
    width: 100%;
    height: 100px;
    background: white;
    /*background-color: #000099;*/
    text-align: center;
    filter: brightness(1.4);
  }
  .inner_label {
    position: absolute;
    left:120px;
    right: 0;
    bottom: 0;
    top:0;
  //margin: auto;
    height: 50px;
  }
  .qxs-icon {
    height: 50px;
    width: 30%;
    margin-bottom: 5px;
    padding-left: 10%;
    border: 0;
    border-bottom: solid 2px lavender;
  }
  .login_form {
    padding-top: 10%;
    padding-left: 10%;
    padding-right: 10%;
  }
  .qxs-ic_user {
    background: url("../assets/image/wx/user.jpg") no-repeat;
    background-size: 13px 15px;
    background-position: 3%;
    font-size: 18px;
  }
  .qxs-ic_password {
    background: url("../assets/image/wx/password.jpg") no-repeat;
    background-size: 13px 15px;
    background-position: 3%;
    margin-bottom: 20px;
    font-size: 18px;
  }
  .login_logo {
    height: 100%;
    cursor:pointer;
  }
  .login_button_label {
    padding-top: 0%;
    padding-left: 10%;
    padding-right: 10%;
  }
  .login_btn {
    width: 20%;
    font-size: 16px;
    text-align: center;
    background: -webkit-linear-gradient(left, #000099, #2154FA); /* Safari 5.1 - 6.0 */
    background: -o-linear-gradient(right, #000099, #2154FA); /* Opera 11.1 - 12.0 */
    background: -moz-linear-gradient(right, #000099, #2154FA); /* Firefox 3.6 - 15 */
    background: linear-gradient(to right, #000099 , #2154FA); /* 标准的语法 */
    filter: brightness(1.4);
  }
  .error_msg {
    width: 300px;
    color: red;
    font-size: 12px;
  }
  .xieyi {
    position: fixed;
    top: 0px;
    left: 0px;
    right: 0px;
    //height: 400px;
    bottom: 0px;
    //overflow: scroll;
    //background: rgba(0, 0, 0, 0.3);
    background: white;
    z-index: 3;
  }
  .xieyi_title {
    color: #333;
    font-size: 18px;
    line-height: 70px;
    text-align: center;
  }
  .xieyi_content {
    width: 900px;
    height: 500px;
    margin: 50px auto 0;
    border-radius: 0px;
    background: #fff;
    position: relative;
  }
  .xieyi_body {
    height: 350px;
    overflow-y: auto;
    padding: 0 20px;
  }
  .xieyi_btn {
    position: absolute;
    bottom: 10px;
    width: 166px;
    height: 46px;
    background: blue;
    color: #fff;
    border: none;
    outline: none;
    cursor: pointer;
    left: 50%;
    margin-left: -83px;
    border-radius: 0px;
  }
</style>

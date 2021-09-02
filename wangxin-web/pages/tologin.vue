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
        <input type="text"  class="qxs-ic_user qxs-icon"  placeholder="请输入手机号码" v-model="obj.mobile">
      </div>
      <div class="error_msg">{{errTip1}}</div>
      <div>
        <div style="float:left;margin-top: 15px;font-size: 18px">密码：</div>
        <input type="password"  class="qxs-ic_password qxs-icon"  placeholder="请输入密码" v-model="obj.password">
      </div>
      <div class="error_msg">{{errTip2}}</div>
    </div>
    <div class="login_button_label">
      <button class="login_btn"  type="primary"  @click="login" round :loading="isBtnLoading">登录</button>
      <div style="padding-top: 10px">
        <span style="font-size:18px;color: #A9A9AB;text-decoration:underline"><router-link to="/register">注册</router-link></span>
        <span style="font-size:18px;color: #A9A9AB;text-decoration:underline"><router-link to="/toreset">忘记密码？</router-link></span>
      </div>
    </div>
    <y-footer></y-footer>
  </div>
</template>

<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import {userLogin,getUserInfo} from '~/api/user.js'
    import VueCookie from 'vue-cookie'


    export default {
        components: {
            YFooter,
            YHeader
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
                errTip2: ''
            }
        },
        methods: {
            toIndex(){
                this.$router.push({name: 'index'})
            },
            login (e) {
                e.preventDefault();
                if (this.subState) {
                    return false;
                }
                this.errTip1 = '';
                this.errTip2 = '';
                if (!(/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(this.obj.mobile.trim())) && !(/^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/).test(this.obj.mobile.trim())) {
                    this.errTip1 = '请输入正确手机号或者邮箱';
                    return false;
                }
                if (this.obj.password.length < 6) {
                    this.errTip2 = '请输入正确的账号或密码';
                    return false;
                }
                this.$nuxt.$loading.start();
                userLogin(this.obj).then(res => {
                    this.subState = false;
                    this.$nuxt.$loading.finish();
                    if (res.data.code === 200) {
                        VueCookie.set("wangxinToken", res.data.data.token, {expires: 1});
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
                        /*
                        this.$store.commit('SET_TOKEN', res.data.data.token);
                        */
                        this.$store.commit('GET_TEMPORARYURL');

                        this.$store.dispatch('GET_USERINFO',store=>{
                            //this.userInfo = this.$store.state.userInfo;
                            console.log("=====>"+this.userInfo)
                            //window.location.href = this.$store.state.temporaryUrl;
                            this.$router.push({path: '/index', query: {}})
                        });
                    } else {
                        this.errTip2 = res.data.msg;
                    }
                }).catch(() => {
                    this.$nuxt.$loading.finish();
                    this.$msgBox({
                        content: '登录失败,请稍后再试',
                        isShowCancelBtn: false
                    }).catch(() => {})
                })
                return false;
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
    width: 90%;
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
</style>

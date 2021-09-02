<template>
  <div class="wx_header">
    <div class="h_top" v-if="!hideTop">
      <div class="wx_header_top_body">
        <ul class="wx_top_list clearfix" v-if="userInfo">
          <li v-if="isTeacher"><nuxt-link :to="{name: 'account-teacher'}">教师平台</nuxt-link></li>
          <li class="wx_header_li_left">
            <nuxt-link :to="{name: 'account'}">【{{userInfo.nickname}}】主页</nuxt-link>
          </li>
          <li class="wx_header_li_left"><nuxt-link :to="{name: 'account-mycourse'}">我的课程</nuxt-link></li>
          <li class="wx_header_li_left"><a href="javascript:" @click="signOut">安全退出</a></li>
        </ul>
        <ul class="wx_top_list clearfix" v-else>
          <li class="wx_header_li_left"><a href="javascript:" @click="login">登录</a></li>
          <li><a href="javascript:" @click="register">注册</a></li>
        </ul>
      </div>
    </div>
    <div class="wx_h_navigation">
      <div class="wx_h_logo">
        <a :href="mainUrl">
          <img :src="webInfo.logoUrl" alt="" v-if="webInfo">
        </a>
      </div>
      <ul class="wx_h_navigation_ul clearfix" v-if="!hideTop && navigationList">
        <li v-for="(item, index) in navigationList" :key="index" >
          <nuxt-link :class="{active: isNow === item.navigationUrl}" v-if="!(item.navigationUrl.indexOf('http')==0||item.navigationUrl.indexOf('https')==0)"
                     :to="item.navigationUrl" :target="item.target">
            <span class="wx_h_navigation_t">{{item.navigationTitle}}</span>
          </nuxt-link>

          <a :href="item.navigationUrl" v-if="item.navigationUrl.indexOf('http')==0||item.navigationUrl.indexOf('https')==0">
            <span class="wx_h_navigation_t">{{item.navigationTitle}}</span>
          </a>

        </li>
      </ul>
      <nuxt-link v-if="hideTop" :to="{name: 'index'}" class="wx_to_index font_14 c_blue">返回首页</nuxt-link>
    </div>
  </div>
</template>
<script>
  import {serviceInfo, getNavigation} from '~/api/main.js'
  import {userLoginOut} from '~/api/user.js'
  import VueCookie from 'vue-cookie'

  //import validator from 'wxvalidator001'


  export default {
  props: {
    active: {
      type: String
    },
    hideTop: {
      type: Boolean
    },
    hideSearch: {
      type: Boolean
    }
  },
  data () {
    return {
      webInfo: this.$store.state.webInfo,
      mainUrl: this.$store.state.clientData.mainUrl,
      userInfo: '',
      name: '',
      isTeacher: false,
      navigationList: this.$store.state.navigationList.list,
      isNow: '',

    }
  },
    head () {
        return {
            title: this.$store.state.clientData.name,
            meta: [
                {
                    hid: 'keywords',
                    name: 'keywords',
                    content: this.$store.state.webInfo.websiteKeyword
                },
                {
                    hid: 'description',
                    name: 'description',
                    content: this.$store.state.webInfo.websiteDesc
                }
            ],
            link: [
               { rel: 'icon', type: 'image/x-icon', href: this.$store.state.webInfo.faviconIcoUrl }
            ]
        }
    },
  methods: {
    signOut () {
        userLoginOut({}).then(res => {
            if (res.data.code === 200) {
                this.$store.commit('SIGN_OUT');
                this.userInfo = '';
                console.log("退出登录时候的路径："+this.$route.path);

                this.$router.push({name: 'tologin'});
            } else {
                /*
                this.$msgBox({
                    content: '退出失败,请稍后再试',
                    isShowCancelBtn: false
                }).catch(() => {});*/
                this.$router.push({name: 'tologin'});

            }
        }).catch(() => {
            this.$msgBox({
                content: '退出发生异常,请稍后再试',
                isShowCancelBtn: false
            }).catch(() => {})
        });
        VueCookie.delete("wangxinUserInfo");
        VueCookie.delete("wangxinToken");
        /*
      if (this.$route.path.includes('account')) {
        this.$router.push({name: 'tologin'})
      } else {
        window.location.reload()
      }*/
      console.log('退出登录');
    },
    login () {
      this.$store.commit('SET_TEMPORARYURL');
      this.$router.push({name: 'tologin'});
    },
    register () {
      this.$router.push({name: 'register'});

    },
    ff(){
        alert(22);
    }
  },
  mounted () {

      /*
      validator.FormValidate.Form().validatePhone('','13788888888',this.ff)
      */

      // 获取PC客户端信息
      serviceInfo().then(res => {
          if (res.data.code  == 200) {
              this.$store.state.clientData.no = res.data.data.orgNo;
              this.$store.state.clientData.id = 'dskfksdjkf87328423kskdjdsfkkwieikk';
              this.$store.state.clientData.name = res.data.data.websiteTitle;
              this.$store.state.clientData.domain = res.data.data.cookieDomain;
              this.$store.state.clientData.tokenName = res.data.data.cookieTokenName;
              this.$store.state.clientData.mainUrl = res.data.data.pcwebMainUrl;
          } else{
              console.log(res.data)
          }
      }).catch(() => {
          console.log("获取PC客户端信息")
      })

      this.isNow = this.$route.path;

     // setTimeout(function () {
          this.userInfo= JSON.parse(VueCookie.get("wangxinUserInfo"));
      //}, 2000);


     if (VueCookie.get("wangxinToken") && this.userInfo) {
      this.name = this.userInfo.mobile
      if (this.userInfo.userType === 2 || this.userInfo.userType === 4) {
        this.isTeacher = true
      }
    }
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .wx_header {
    background: #fff;
  }
  .h_top {
    background: deepskyblue;
    .wx_header_top_body {
      margin: 0px auto;
      width: 1200px;
      height: 40px;
    }
  }
  .wx_top_list {
    float: right;
    li {
      float: left;
      color: #ccc;
      font-size: 18px;
      border-radius: 0px;
      background-color: black;
      margin-top: 9px;
      padding: 0px 6px 0px 6px;
      position: relative;
      img {
        width: 14px;
        height: 13px;
        position: absolute;
        left: -20px;
      }
      .vip_icon {
        position: relative;
        left: 0;
        top: 1px;
        cursor: pointer;
      }
      a {
        color: #ccc;
        &:hover {
          text-decoration: none;
          color: #fff;
        }
        &.c_gold {
          color: #CA9E70;
        }
      }
      &.wx_header_li_left {
        border-left: 1px solid #ccc;
        padding-left: 10px;
      }
    }
  }

  .wx_h_navigation {
    width: 1200px;
    margin: 0 auto;
    height: 110px;
    position: relative;
    .wx_to_index {
      background-color: lightcyan;
      position: absolute;
      right: 10px;
      bottom: 20px;
    }
  }
  .wx_h_logo {
    display: inline-block;
    position: absolute;
    top: 25px;
    // left: 20px;
    img {
      height: 52px;
    }
  }
  .wx_h_navigation_ul {
    display: inline-block;
    margin-left: 280px;
    li {
      float: left;
      height: 110px;
      line-height: 110px;
      font-size: 16px;
      padding: 0 15px;
      .active {
        color: white;
      }
      a:hover {
        color: red;
        text-decoration: none;
      }
    }
    .wx_h_navigation_t {
      background-color: lightskyblue;
      color: white;
      font-size: 22px;
      border-radius: 0px;
      padding: 0px 6px 0px 6px;
    }
  }
</style>

<template>
  <div class="i_footer">
    <div class="footer_content clearfix" v-if="list">
      <div class="footer_msg" v-for="(itemNav, indexNav) in list" :key="'nav'+indexNav">
        <ul>
          <li class="m_header">{{itemNav.navigationName}}</li>
          <li class="m_row"></li>
          <li v-for="(that, num) in itemNav.bottomNavigationList" :key="num">
            <!--
            <a :href="clientData.mainUrl+'/terraceDetail?id='+that.id">{{that.navigationName}}</a>-->
            <router-link  :to="{name: 'terrace-terraceDetail', query: {navigationId: that.id}}">{{that.navigationName}}</router-link>
          </li>
        </ul>
      </div>
      <div class="m_right" style="display: inline"  v-for="(itemQr, indexQr) in qrCodeList" :key="'qr'+indexQr" v-if="service">
        <div class="footer_msg" style="display: inline" >
          <ul>
            <li class="m_header">{{itemQr.qrCodeName}}</li>
            <li>
              <img :src="itemQr.qrCodeImageUrl" alt="">
            </li>
          </ul>
        </div>
        <!--
         <div class="footer_msg" style="display: inline" >
            <ul>
              <li class="m_header">微信小程序</li>
              <li>
                <img :src="service.weixinQrUrl" alt="">
              </li>
            </ul>
          </div>-->
        <!--
          <div class="footer_msg" v-if="service.weibo">
            <ul>
              <li class="m_header">官方微博</li>
              <li>
                <img :src="service.weibo" alt="">
              </li>
            </ul>
          </div>-->
      </div>
    </div>
    <div class="friend_link" v-if="friendList && friendList.length">
      <ul class="clearfix">
        <li class="link_one">友情链接:</li>
        <li v-for="(item, index) in friendList" :key="index"><a :href="item.linkUrl" :target="item.linkTarget">{{item.linkName}}</a></li>
      </ul>
    </div>
    <div class="copyright" v-if="service">
      <span v-html="service.copyright"></span>
    </div>
    <div class="icp_num">
      <a href="http://www.wangxinvip.com/" target="_blank" class="lingke_link">旺鑫教育云</a> 提供技术支持
      <span v-if="service.icp">&nbsp;|&nbsp;</span>
      <a href="http://www.miitbeian.gov.cn/" target="_blank" class="lingke_link">{{service.icp}}</a>
      <span v-if="service.gonganbeian">&nbsp;|&nbsp;</span>
      <a :href="'http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=' + service.gonganbeianId" target="_blank" v-if="service.gonganbeian" class="lingke_link"><img src="~/assets/image/gonganbeian_icon.png" class="gonganbeian_icon" alt="">&nbsp;{{service.prn}}</a>
    </div>
  </div>
</template>
<script>
  import {friendLink, aboutList,bottomQrCodeList} from '~/api/main.js'
  export default {
    name: 'Footer',
    // serverCacheKey() {
    //   // Will change every 10 secondes
    //   return Math.floor(Date.now() / 10000)
    // },
    data () {
      return {
        clientData: this.$store.state.clientData,
        service: this.$store.state.webInfo,
        //list: this.$store.state.aboutList ? this.$store.state.aboutList.list: [],
          list:[],
        friendList: [],
          qrCodeList:[]
      }
    },
    mounted () {
      friendLink().then(res => {
        if (res.data.code === 200) {
          this.friendList = res.data.data.friendLinkList
        } else {
          console.log('友情链接获取失败！')
        }
      });
        aboutList().then(res => {
            if (res.data.code == 200) {
                this.list =  res.data.data.bottomNavigationList|| [];
            }
        }).catch(error => {
            reject(error)
        });
        bottomQrCodeList().then(res => {
            if (res.data.code == 200) {
                this.qrCodeList =  res.data.data.list|| [];
            }
        }).catch(error => {
            reject(error)
        })
    }
  }
</script>
<style lang="scss" rel="stylesheet/scss">
  .i_footer {
    background: deepskyblue;
    padding-top: 60px;
    .footer_content {
      width: 1200px;
      margin: 0 auto;
      padding-bottom: 30px;
      border-bottom: 1px solid rgb(102, 102, 102);
    }
    .footer_msg {
      float: left;
      width: 130px;
      img {
        width: 88px;
        height: 88px;
      }
      li {
        color: #ccc;
        font-size: 14px;
        margin-bottom: 20px;
        a {
          color: black;
          &:hover {
            text-decoration: none;
            color: #fff;
          }
        }
        &.m_header {
          color: black;
          font-size: 18px;
          font-weight: bold;
        }
        &.m_row {
          width: 22px;
          height: 3px;
          background: black;
          margin-bottom: 25px;
        }
      }
    }
    .m_right {
      float: right;
      .big_phone {
        color: #fff;
        font-size: 18px;
        margin-left: 56px;
      }
      .small_phone {
        color: #fff;
        font-size: 14px;
        margin-top: 5px;
        margin-left: 56px;
      }
    }
    .m_phone {
      position: relative;
      img {
        width: 46px;
        height: 43px;
        position: absolute;
        top: 0;
        left: 0;
      }
    }
    .qq_btn {
      margin-top: 10px;
      position: relative;
      img {
        width: 11px;
        height: 13px;
        position: absolute;
        top: 12px;
        left: 25px;
      }
      a {
        display: block;
        width: 113px;
        height: 36px;
        padding-left: 15px;
        background: rgb(213, 20, 35);
        line-height: 36px;
        color: #fff;
        border-radius: 5px;
        text-align: center;
        &:hover {
          text-decoration: none;
          color: #fff;
        }
      }
    }
    .m_test {
      width: 262px;
      height: 30px;
      line-height: 30px;
      // background: rgb(34, 34, 34);
      color: #ccc;
      font-size: 14px;
      padding-left: 10px;
      margin-top: 8px;
      img {
        width: 19px;
        height: 14px;
        position: relative;
        top: 2px;
      }
    }
    .friend_link {
      width: 1200px;
      margin: 25px auto 0px;
      li {
        float: left;
        font-size: 14px;
        &.link_one {
          color: #fff;
          margin-right: 18px;
        }
        a {
          color: white;
          margin: 0px 10px;
          &:hover {
            color: blue;
            text-decoration: none;
          }
        }
      }
    }
    .copyright {
      background: blue;
      height: 32px;
      line-height: 30px;
      text-align: center;
      color: #ccc;
      margin-top: 25px;
      font-size: 16px;
      a {
        color: #ccc;
      }
    }
    .icp_num {
      line-height: 30px;
      text-align: center;
      color: white;
      .lingke_link {
        color: white;
        &:hover {
          text-decoration: underline;
        }
      }
    }
    .gonganbeian_icon {
      width: 16px;
      height: 16px;
      position: relative;
      top: 3px;
    }
  }
</style>

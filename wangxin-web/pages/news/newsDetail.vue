<template>
  <div class="index_page">
    <y-header :active="'index'"></y-header>
    <div class="news_detail">
        <div style="text-align: center;height: 40px;font-size: 28px"><p v-html="newsObj.newsTitle"></p></div>
      <div style="text-align: center;height: 20px;font-size: 18px">审核时间：{{newsObj.createTime}}</div>
      <br/>
        <div style="text-indent: 2em;">
          <p v-html="newsObj.newsContent"></p>
          <br/>
          《全文完》
        </div>
    </div>
  <y-footer></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YBanner from '~/components/Banner'
import YFooter from '~/components/common/Footer'
import RightTap from '~/components/common/RightTap'
import {newsView} from '~/api/main.js'
export default {
  components: {
    YHeader,
    YBanner,
    YFooter,
    RightTap
  },
  data () {
    return {
        newsObj: {}
    }
  },
  methods: {
      viewNews(){
          this.newsObj.id = this.id;
          newsView(this.newsObj).then(res => {
              res = res.data;
              if (res.code === 200) {
                  //this.studyCardList = res.data.list || [];
                  this.newsObj = res.data;
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
              this.loading = false;
          }).catch(() => {
              this.loading = false;
          })
      }
  },
  mounted () {
      this.id = this.$route.query.id;
      console.log("id:====>"+this.$route.query.id);
      this.viewNews();
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
.index_page {
  .clearfix:before, .clearfix:after {
    content: "";
    display: table;
  }
  .clearfix:after {
    clear: both;
  }
  .i_content {
    background: lightgrey;
    &:nth-child(2n) {
      background: white;
    }
    .i_topic {
      width: 1200px;
      margin: 0 auto;
      padding: 50px 0 40px 0;
    }
  }
  .topic_header {
    .col_block {
      display: inline-block;
      width: 24px;
      height: 24px;
      background: blue;
      position: relative;
      top: 4px;
    }
    .big_text {
      // text-align: center;
      font-size: 24px;
    }
    .small_text {
      // text-align: center;
      font-size: 18px;
      color: blue;
      font-weight: bold;
      margin-top: 10px;
    }
    .link_text:hover {
      text-decoration: none;
      color: #333;
    }
  }
  .topic_body {
    margin-top: 25px;
  }
  .news_detail {
    position: relative;
    height: 100%;
    top: 0px;
    font-size: 16px;
    width: 1020px;
    left: 50%;
    z-index: 10;
    margin-left: -510px;
    //border: 1px solid #bbbbbb;
  }
}
</style>

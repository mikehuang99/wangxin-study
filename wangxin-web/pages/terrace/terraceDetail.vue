<template>
  <div>
    <y-header :active="'index'"></y-header>
    <div class="person_body clearfix">
      <y-side :showSide="'wm'"></y-side>
      <div class="person_content">
        <div class="person_title">
          {{articleTitle}}
        </div>
        <div class="person_info">
          <p class="top_text" v-html="articleContent"></p>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YFooter from '~/components/common/Footer'
import YSide from '~/components/TerraceSide'
import {aboutInfo} from '~/api/main.js'
export default {
  head () {
      return {
        title: this.articleTitle,
        meta: [
            {
                name: 'keywords',
                content: this.webInfo.websiteKeyword
            },
            {
                name: 'description',
                content: this.webInfo.websiteDesc
            }
        ]
      }
  },
  data () {
    return {
      webInfo: this.$store.state.webInfo,
        navigationId: '',
        articleTitle: '',
        articleContent: ''
    }
  },
    watch: {
        $route(){
            this.navigationId= this.$route.query.navigationId
        },
        navigationId() {
            console.log("重新请求" + this.navigationId + "的数据");
            this.getAboutList();
        }
    },
  /*
  validate ({ params }) {
    // 必须是number类型
    return /^\d+$/.test(params.id)
  },*/
  /*
  async asyncData(context) {
      let id = this.$route.query.id;
    //let {data} = await aboutInfo({navigationId: context.params.id});
      let {data} = await aboutInfo({navigationId: id});
    if (data.code == 200) {
      return {
          articleTitle: data.data.articleTitle,
          articleContent: data.data.articleContent
      }
    }else{
      console.log(data)
      return {
          articleTitle: '',
          articleContent: ''
      }
    }
  },*/
    methods:{
      getAboutList() {
          aboutInfo({'navigationId':this.navigationId}).then(res => {
              if (res.data.code == 200) {
                  this.articleTitle= res.data.data.articleTitle,
                  this.articleContent= res.data.data.articleContent;
              }
          }).catch(error => {
              //reject(error)
          })
      }
    },
  mounted () {
      //alert("dd");
      this.navigationId = this.$route.query.navigationId;
      this.getAboutList();
      /*
    if (this.articleTitle == '') {
      this.$msgBox({
        content: '获取信息失败',
        isShowCancelBtn: false
      })
    }*/
  },
  components: {
    YHeader,
    YFooter,
    YSide
  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .person_body {
    width: 1200px;
    margin: 30px auto 0;
    min-height: 1000px;
  }
  .person_content {
    width: 1012px;
    float: right;
    background: #fff;
    border-radius: 6px;
    .person_title {
      font-size: 14px;
      font-weight: 700;
      color: #333;
      text-align: center;
      line-height: 100px;
    }
  }
  .person_info {
    padding: 0px 50px 50px;
    min-height: 400px;
    font-size: 14px;
    color: #333;
    line-height: 28px;
  }
</style>

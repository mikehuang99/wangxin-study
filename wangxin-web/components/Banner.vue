<template>
  <div class="banner">
    <ul :style="'height:'+ height+ 'px;'" @mouseout="mout" @mouseover="mover">
      <li class="item" v-for="(item, index) in data" :key="index" :style="'background-image: url('+item.advertImg+');'" :class="{on:index === num}">
        <a :href="item.advertUrl" :title="item.advertTitle" :target="item.advertTarget"></a>
      </li>
    </ul>
    <ul class="page_dots">
      <span class="dots" v-for="(item, index) in data" :class="{on:index === num}" @mouseenter="num = index" :key="index"></span>
    </ul>
    <y-class-block :height="height" :classList="classList" />
    <div class="class_news">
      <div class="class_news_item" v-for="(item, i) in newsPackageList">
        <div>
          <a style="position: relative;float: left;width: 290px;cursor:pointer" v-if="item.title1" @click="toNews(item.id1)">◉{{item.title1}}</a>
          <a style="position: relative;float: left;width: 290px;cursor:pointer" v-if="item.title2" @click="toNews(item.id2)">◉{{item.title2}}</a>
          <a style="position: relative;float: left;cursor:pointer" v-if="item.title3" @click="toNews(item.id3)">◉{{item.title3}}</a>
          <a style="position: relative;float: right;cursor:pointer;margin-right: 120px" v-if="i==0" href="/news/newsList">更多>></a>
        </div>
        <br/>
        <!--
        <div>
          <div style="position: relative;float: left;width: 500px">◉[2020-10-09]体育类课外</div>
          <div style="position: relative;float: left">◉[2020-10-09]体育类课外辅导教育井喷式爆发2</div>
          <div style="position: relative;float: right;text-decoration:underline">更多资讯>></div>
        </div>
        <br/>
        <div>
          <div style="position: relative;float: left;width: 500px">◉[2020-10-09]体育类课外辅导教育井喷式爆发</div>
          <div style="position: relative;float: left">◉[2020-10-09]体育类课外辅导教育井喷</div>
        </div>
        <br/>
        <div>
          <div style="position: relative;float: left;width: 500px">◉[2020-10-09]体育类课外辅导教育井喷式爆发</div>
          <div style="position: relative;float: left">◉[2020-10-09]体育类课外辅导教育井喷式爆发2</div>
        </div>
        <br/>
        <div>
          <div style="position: relative;float: left;width: 500px">◉[2020-10-09]体育类课外辅导教育井喷式爆发</div>
          <div style="position: relative;float: left">◉[2020-10-09]体育类课外辅导教育井喷式爆发2</div>
        </div>
        <br/>
        -->
        <!--
        <li>[2020-10-07]教育市场领域精细化火热进行中</li>
        <li>[2020-10-06]小学课外辅导是否有必要</li>
        <li>[2020-10-05]体育即将加码，会削弱英语的主科地位吗？</li>
        <li>[2020-10-04]大学学什么？放养式学习还能适应当今社会吗？</li>
        <li>[2020-10-04]高数在线教育引爆大学教育市场</li>
        <li>[2020-10-03]没有文科情怀的理科生难学好理科</li>
        <li>[2020-09-09]疫情后期的在线教育是否还会持续火爆？</li>-->
      </div>
    </div>
  </div>
</template>
<script>
import YClassBlock from './ClassBlock'
// import {mapState} from 'vuex'
import {newsList} from '~/api/main.js'
export default {
  props: {
    data: {
      // 手机号
      type: [Object, Array],
      default: null
    },
    classList: {
      type: [Object, Array],
      default () {
        return []
      }
    },
    height: {
      type: [String, Number],
      default: 500
    }
  },
  data () {
    return {
      webInfo: this.$store.state.webInfo,
      num: 0,
      interval: null,
        newsPackageList: [
            //{id1: '1',title1: '新是德弗里是对方是否斯地方闻11',id2: '12',title2: '新是德弗里是对方是否斯地方闻1112',id3: '13',title3: '新是德弗里是对方是否斯地方闻1113'},
            //{id1: '2',title1: '新是德弗里是对方是否斯地方闻1121',id2: '22',title2: '新是德弗里是对方是否斯地方闻1122',id3: '23',title3: '新是德弗里是对方是否斯地方闻1123'},
            //{id1: '3',title1: '新是德弗里是对方是否斯地方闻1131',id2: '32',title2: '新是德弗里是对方是否斯地方闻1132',id3: '33',title3: '新是德弗里是对方是否斯地方闻1133'},
            //{id1: '4',title1: '新是德弗里是对方是否斯地方闻1141',id2: '42',title2: '新是德弗里是对方是否斯地方闻1142',id3: '43',title3: '新是德弗里是对方是否斯地方闻1143'},
            //{id1: '5',title1: '新是德弗里是对方是否斯地方闻1151',id2: '52',title2: '新是德弗里是对方是否斯地方闻1152',id3: '53',title3: '新是德弗里是对方是否斯地方闻1153'}
        ]
    }
  },
  methods: {
    change () {
      this.interval = setInterval(() => {
        if (this.num + 1 >= this.data.length) {
          this.num = 0;
        } else {
          this.num++;
        }
      }, 3000)
    },
      toNews(id){
          this.$router.push({path: '/news/newsDetail', query: {id: id}})
      },
      getNewsList() {
          newsList(this.obj).then(res => {
              res = res.data;
              if (res.code === 200) {
                  let newsList2 = res.data.list || [];

                  let count = parseInt(newsList2.length/3);
                  console.log("count====>"+count);
                  let remainder =  newsList2.length/3;
                  for(let k=0;k<count;k++){
                      this.newsPackageList.push({"id1":newsList2[k].id,"title1":newsList2[k].newsTitle,"id2":newsList2[k+1].id,"title2":newsList2[k+1].newsTitle,"id3":newsList2[k+2].id,"title3":newsList2[k+2].newsTitle})
                  }

                  /*
                  if(newsList2.length==1){
                      this.newsPackageList.push({"id1":newsList2[0].id,"title1":newsList2[0].newsTitle,"id2":'',"title2":'',"id3":'',"title3":''})
                  }
                  if(newsList2.length==2){
                      this.newsPackageList.push({"id1":newsList2[0].id,"title1":newsList2[0].newsTitle,"id2":newsList2[1].id,"title2":newsList2[1].newsTitle,"id3":'',"title3":''})
                  }
                  if(newsList2.length==3){
                      this.newsPackageList.push({"id1":newsList2[0].id,"title1":newsList2[0].newsTitle,"id2":newsList2[1].id,"title2":newsList2[1].newsTitle,"id3":newsList2[2].id,"title3":newsList2[2].newsTitle})
                  }
                  if(newsList2.length==4){
                      this.newsPackageList.push({"id1":newsList2[0].id,"title1":newsList2[0].newsTitle,"id2":newsList2[1].id,"title2":newsList2[1].newsTitle,"id3":newsList2[2].id,"title3":newsList2[2].newsTitle});
                      this.newsPackageList.push({"id1":newsList2[3].id,"title1":newsList2[3].newsTitle,"id2":'',"title2":'',"id3":'',"title3":''});
                  }
                  if(newsList2.length==5){
                      this.newsPackageList.push({"id1":newsList2[0].id,"title1":newsList2[0].newsTitle,"id2":newsList2[1].id,"title2":newsList2[1].newsTitle,"id3":newsList2[2].id,"title3":newsList2[2].newsTitle});
                      this.newsPackageList.push({"id1":newsList2[3].id,"title1":newsList2[3].newsTitle,"id2":newsList2[4].id,"title2":newsList2[4].newsTitle,"id3":'',"title3":''});
                  }
                  if(newsList2.length==6){
                      this.newsPackageList.push({"id1":newsList2[0].id,"title1":newsList2[0].newsTitle,"id2":newsList2[1].id,"title2":newsList2[1].newsTitle,"id3":newsList2[2].id,"title3":newsList2[2].newsTitle});
                      this.newsPackageList.push({"id1":newsList2[3].id,"title1":newsList2[3].newsTitle,"id2":newsList2[4].id,"title2":newsList2[4].newsTitle,"id3":newsList2[5].id,"title3":newsList2[5].newsTitle});
                  }*/
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
      },
    mout () {
      this.change();
    },
    mover () {
      clearInterval(this.interval);
    }
  },
  mounted () {
    // console.log(this.data)
    this.change();
    this.getNewsList();
  },
  components: {
    YClassBlock
  }
}
</script>
<style lang="scss" scoped>
  .class_news {
    position: relative;
    height: 100%;
    top: 0px;
    font-size: 16px;
    width: 1020px;
    left: 50%;
    z-index: 10;
    margin-left: -420px;
    border: 1px solid #bbbbbb;
  }
  .class_news_item {
    margin-left: 10px;
    li{
      line-height: 28px;
    }
    a:hover{
      color:red;
      font-weight:bolder;
    }
    /*
    :before {
        content: "◉";
        color: blue;
    }*/
  }
.banner{
  position: relative;
  ul{
    position: relative;
  }
  .item{
    opacity: 0;
    height: 100%;
    background-position: center center;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 9;
    transition: all 1s;
    &.on{
      opacity: 1;
      z-index: 10;
    }
    a{
      width: 100%;
      height: 100%;
      display: block;
    }
  }
  .page_dots{
    position: absolute;
    bottom: 20px;
    right: 50%;
    margin-right: -600px;
    text-align: center;
    height: 0;
    z-index: 11;
    &.old {
      left: 0;
      right: 0;
      margin-right: 0;
      bottom: 30px;
    }
    .dots{
      display: inline-block;
      height: 8px;
      background: rgba(255, 255, 255, 0.3);
      margin: 0 1px;
      color: #fff;
      font-size: 14px;
      margin: 0 4px;
      width: 30px;
      &.old_dot {
        line-height: 30px;
        height: 30px;
        margin: 0 1px;
      }
      &.on{
        background-color: rgba(255, 255, 255, 0.8);
        color: #333;
      }
    }
  }
}
</style>

<template>
  <div class="index_page">
    <y-header :active="'index'"></y-header>
    <div class="news_list">
      <div style="display:inline;" v-if="newsTypeList.length!=0">标签：</div>
      <div style="overflow:scroll;width:1020px;display:inline;" v-for="(item2, index2) in newsTypeList" >
        <a class="bb" :class="{'seletedNewsTypeId':displayId===item2.id}" @click="getNewsListByNewsTypeId(item2.id)" :id="item2.id">{{item2.newsType}}</a>
      </div>
      <a class="cc" style="background-color: lightgrey" v-if="newsTypeList.length!=0" @click="getNewsListByNewsTypeId('')">查看所有</a>
      <div class="notdata" v-if="notdata">
        ★暂时没有数据
      </div>
      <div class="notdata" style="background-color: white" v-if="loading">
        数据正在加载中......
      </div>
      <ul v-for="(item, index) in this.pageObj.list" :key="index" class="news_list_item">
        <a @click="toNews(item.id)">
          <div style="border: 1px dashed lightgrey">
            <span style="font-size: 20px;font-weight: bold">◉{{item.newsTitle}}</span><br/>
            <div>
              {{item.newsContent | filterNewsContent}}
            </div>
          </div>
        </a>
      </ul>
    </div>
    <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
    <br/>
    <y-footer></y-footer>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YBanner from '~/components/Banner'
import YFooter from '~/components/common/Footer'
import RightTap from '~/components/common/RightTap'
import DPage from '~/components/Page'
import {newsList,newsTypeList} from '~/api/main.js'
export default {
  components: {
    YHeader,
    YBanner,
    YFooter,
    RightTap,
    DPage
  },
  data () {
    return {
        displayId: '',
        newsTypeList: [],
        pageObj : {
            list: [],
            pageCurrent: '1',
            pageSize: '',
            totalCount: '',
            totalPage: ''
        },
        obj : {
            pageCurrent: 1,
            pageSize: 20
        },
        notdata: false,
        loading: false
    }
  },
    filters: {
        filterNewsContent(value) {
            if (!value) return '';
            value = value.replaceAll('<p>','');
            value = value.replaceAll('<br>','');
            value = value.replaceAll('</p>','');
            value = value.replaceAll('<table>','');
            value = value.replaceAll('</table>','');
            let reTag = /<img(?:.|\s)*?>/g;
            value = value.replaceAll(reTag,'');//过滤其中的所有的img标签

            if (!value) return '';
            if (value.length > 180) {
                return value.slice(0, 180) + '...'
            }
            return value;
        }
    },
  methods: {
      toNews(id){
          this.$router.push({path: '/news/newsDetail', query: {id: id}})
      },
      getPage (int) {
          window.scrollTo(0, 0);
          console.log("点击的页数："+int);
          this.obj.pageCurrent = int;
          //this.pageObj.pageCurrent = int;
          this.getNewsList();
      },
      getNewsListByNewsTypeId(newsTypeId){
          this.displayId = newsTypeId;
          this.obj.newsTypeId = newsTypeId;
          this.obj.pageCurrent = 1;
          this.getNewsList();
      },
      getNewsList() {
          this.loading = true;
          newsList(this.obj).then(res => {
              res = res.data;
              if (res.code === 200) {
                  //this.studyCardList = res.data.list || [];
                  this.pageObj = res.data;
                  if (res.data.list.length > 0) {
                      this.notdata = false;
                  } else {
                      this.notdata = true;
                  }
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
      },
      getNewsTypeList() {
          newsTypeList(this.obj).then(res => {
              res = res.data;
              if (res.code === 200) {
                  let newsTypeList2 = res.data.list || [];
                  for(let i=0;i<newsTypeList2.length;i++){
                      this.newsTypeList.push({"id":newsTypeList2[i].id,"newsType":newsTypeList2[i].newsType});
                  }
                 //console.log("====>"+res.data.list);
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
      }
  },
  mounted () {
      this.getNewsTypeList();
      this.getNewsList();
  }
}
</script>
<style lang="scss" rel="stylesheet/scss">
  .seletedNewsTypeId{
    background-Color:deepskyblue!important
  }
  .bb{
    background-Color:lightgrey;
    height:100px;
    width:50px;
    line-height:50px;
    display:inline;
    word-break:break-all;
    //word-wrap:break-word;
    font-size: 20px;
    margin-right: 30px;
    margin-left: 10px;
    box-shadow:0px 0px 7px #000;
    cursor:pointer;
    white-space:nowrap;//加入这个就不会一行的文字换行显示了
  }
  .cc{
    background-Color:deepskyblue;
    box-shadow:0px 0px 7px #000;
    height:100px;
    width:50px;
    line-height:50px;
    display:inline;
    word-break:break-all;
    //word-wrap:break-word;
    font-size: 20px;
    margin-right: 30px;
    margin-left: 10px;
    cursor:pointer;
    white-space:nowrap;//加入这个就不会一行的文字换行显示了
  }
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
  .news_list {
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
  .news_list_item {
    margin-left: 10px;
    a{
      line-height: 28px;
    }
    a:hover{
      color:red;
      font-weight:bolder;
      cursor:pointer;
    }
    /*
    :before {
        content: "◉";
        color: blue;
    }*/
  }
}
</style>

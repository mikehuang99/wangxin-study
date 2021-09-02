<template>
  <div class="index_page">
    <y-header :active="'index'"></y-header>
    <y-banner :data="advertData" :height="380" :classList="classList"></y-banner>
    <div class="i_content" v-for="(item, index) in topicData" :key="index">
      <div class="i_topic">
        <div class="topic_header">
        <div class="big_text">
          <span class="col_block"></span>
          {{ item.name }}
          <span class="small_text">{{ item.topicName }}</span>
          <nuxt-link :to="{name: 'list'}" class="fr small_text link_text">更多课程>></nuxt-link>
        </div>
        </div>
        <div class="topic_body">
          <ul class="clearfix course_list">
            <li v-for="(that, int) in item.topicCourseList" :key="int">
              <router-link  :to="{name: 'view-courseDetail', query: {courseId: that.id}}">
                <div class="img_box">
                  <img :src="that.courseLogo" alt="">
                  <div class="live_time" v-if="that.courseType === 1">
                    <span>直播</span>
                  </div>
                  <div class="live_time" v-if="that.courseType === 2">
                    <span>直播+点播</span>
                  </div>
                </div>
              <p>{{ that.courseName }}</p>
              <div class="price_box" v-if="that.isFree === 1" >免费</div>
              <div class="price_box"  v-if="that.isFree===0 && that.courseOriginal===that.courseDiscount"><span style="font-weight:bold">￥{{that.courseOriginal.toFixed(2)}}元{{that.costTimeType | costTimeTypeChange}}</span></div>
              <div class="price_box"  v-if="that.isFree===0 && that.courseOriginal > that.courseDiscount"><span style="font-size:10px;text-decoration:line-through">原￥{{that.courseOriginal}}元</span><span style="font-weight:bold;;color: #FF5151">现￥{{that.courseDiscount}}元{{that.costTimeType | costTimeTypeChange}}</span></div>
              <div class="price_box"  v-if="that.isFree===0 && that.courseOriginal < that.courseDiscount"><span style="font-weight:bold">￥{{that.courseDiscount}}元{{that.costTimeType | costTimeTypeChange}}</span></div>
              </router-link >
            </li>
            <li v-for="thatLive in item.liveCourseList" :key="thatLive.id">
              <nuxt-link target="_blank" :to="{name: 'live-id', params: {id: thatLive.id}}">
                <div class="img_box">
                  <img :src="thatLive.courseLogo" alt="">
                  <div class="live_time">
                    <p style="font-size: 12px;" v-if="thatLive.liveTime">开播时间：{{thatLive.liveTime}}</p>
                    <p style="font-size: 12px;" v-if="thatLive.endTime">有效期至：{{thatLive.endTime}}</p>
                  </div>
                </div>
              <p>{{ thatLive.courseName }}（直播）</p>
              <span class="price_box" v-if="thatLive.isFree">免费</span>
              <span class="price_box" v-else>￥{{thatLive.courseOriginal.toFixed(2)}}<span class="font_12 padl_10" v-if="openVip && thatLive.courseDiscount != thatLive.courseOriginal">SVIP:{{thatLive.courseDiscount ? '￥' + thatLive.courseDiscount.toFixed(2) : '免费'}}</span></span>
              </nuxt-link>
            </li>
            <li v-for="thatGroup in item.topicCourseCombinaRefList" :key="thatGroup.id">
              <nuxt-link target="_blank" :to="{name: 'liveAndBunch', params: {id: thatGroup.id}}">
                <div class="img_box">
                  <img :src="thatGroup.courseLogo" alt="">
                </div>
              <p>{{ thatGroup.courseName }} (录播+直播)</p>
              <span class="price_box" v-if="thatGroup.isFree">免费</span>
              <span class="price_box" v-else>￥{{thatGroup.courseOriginal.toFixed(2)}}<span class="font_12 padl_10" v-if="openVip && thatGroup.courseDiscount != thatGroup.courseOriginal">SVIP:{{thatGroup.courseDiscount ? '￥' + thatGroup.courseDiscount.toFixed(2) : '免费'}}</span></span>
              </nuxt-link>
            </li>
          </ul>
          <ul class="test_list clearfix">
            <li :class="{test_option: true, right_0: (num % 2 == 1)}" v-for="(resource, num) in item.topicCourseLibList" :key="resource.id">
              <nuxt-link target="_blank" :to="{name: 'libraryDetail', params: {id: resource.id}}">{{resource.courseName}}</nuxt-link>
            </li>
          </ul>
        </div>
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
import {topicCourse, advertList, indexClass} from '~/api/main.js'
export default {
  components: {
    YHeader,
    YBanner,
    YFooter,
    RightTap
  },
    /*
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
        ]
      }
  },*/
  data () {
    return {
      webInfo: this.$store.state.webInfo,
      clientNo: this.$store.state.clientData.no,
      openVip: false,
      advertData: this.$store.state.advertData,
    }
  },
  async asyncData(context) {
    let clientNo = context.store.state.clientData.no;
    // console.log(clientNo)

    try {
      let dataObj = {}
      // 轮播图
      let { data } = await advertList({platShow: 0})
      console.log("data.data.advertList: "+ data.data.advertList)
      // 轮播图上的分类
      let blockData = await indexClass();
        console.log("blockData:"+blockData);
      // 推荐课程
      let topicdata = await topicCourse({topicLocation: 0});
      console.log("topicdata:"+topicdata);
      // 活动标
      let topicList = topicdata.data.data.list || []
      let courseNoList = []

      topicList.forEach(item => {
        for (let that in item) {
          if (item[that] && typeof item[that] == 'object' && item[that].length && that != 'topicCourseLibList' && that != 'topicResourceInfoList') {
            item[that].forEach(course => {
              // console.log(course)
              courseNoList.push(course.courseNo)
            })
          }
        }
      })

      dataObj.advertData = data.data.advertList || []  //轮播图
      dataObj.topicData = topicdata.data.data.list || []  //课程专区
      dataObj.classList = blockData.data.data.courseSubjectList || [] //轮播分类
      return dataObj
    } catch (e) {
      context.error({ message: 'User not found index', statusCode: 404 })
    }
  },
  methods: {
  },
  mounted () {
    if (this.webInfo && this.webInfo.isEnableVip) {
      this.openVip = true
    }
    // this.$store.dispatch('REDIRECT_LOGIN', 301)
  },
    filters:{
        costTimeTypeChange(value){
            console.log("costTimeType====>"+value)
            //计费类型，0代表长期，1按天，2按周(7天)，3按月(30天)，4按年(365天)
            if(value===0){
                return "";
            }else if(value===1){
                return "/天";
            }else if(value===2){
                return "/周";
            }else if(value===3){
                return "/月";
            }else if(value===4){
                return "/年";
            }else{
                return "";
            }
        }
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
  .course_list {
    li {
      float: left;
      width: 285px;
      height: 204px;
      border-radius: 0px;
      margin: 0px 20px 20px 0px;
      &:nth-child(4n) {
        margin-right: 0px;
      }
      &:hover {
        box-shadow: 0px 10px 18px blue;
        transform: translateY(-2px);
        transition: all .3s;
      }
      a {
        display: block;
        width: 100%;
        height: 100%;
        position: relative;
        background: #fff;
        border-radius: 0px;
        .img_box {
          width: 285px;
          height: 140px;
          position: relative;
          /*
          .qizi {
            background: url(../assets/image/activity/qizi.png) no-repeat center;
            position: absolute;
            top: 0;
            left: 10px;
            width: 46px;
            height: 46px;
            color: #fff;
            font-size: 14px;
            text-align: center;
          }*/
          img {
            width: 285px;
            height: 140px;
            border-radius: 0px 0px 0 0;
          }
          .live_time {
            position: absolute;
            top: 0;
            right: 0;
            font-size: 24px;
            font-weight: bold;
            line-height: 26px;
            background: transparent;
            color: rgba(220,20,60,0.8);//设置透明度
            padding-bottom: 2px;
            border: 3px solid rgba(220,20,60,0.8);
          }
        }
        &:hover {
          color: blue;
          text-decoration: none;
        }
        p {
          font-size: 16px;
          margin-top: 5px;
          padding-left: 10px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          word-break: break-all;
        }
        .price_box {
          font-size: 16px;
          position: absolute;
          bottom: 10px;
          left: 10px;
          color: red;
        }
      }
    }
  }
  .test_list{
    .test_option{
      float: left;
      width: 520px;
      padding: 18px 25px;
      margin-right: 55px;
      margin-bottom: 20px;
      border-radius: 8px;
      font-size: 14px;
      background-color: #fff;
      &.right_0 {
        margin-right: 0px;
      }
    }
    .iconfont{
      margin-right: 6px;
    }
  }
}
</style>

<template>
  <div class="courselist_page">
    <y-header></y-header>
    <y-header-list :classList="classList" :fourNow="free" ></y-header-list>
    <div class="course_content">
      <ul class="clearfix">
        <li v-for="(item, index) in pageObj.list" :key="index">
          <!--
          <nuxt-link target="_blank" :to="{name: 'view-id', params: {id: item.id}}" class="course_info">-->
          <router-link :to="{ name: 'view-courseDetail', query: { courseId: item.id }}" class="course_info">
            <div class="img_box">
              <img class="course_img" :src="item.courseLogo" alt="">
              <div class="live_time" v-if="item.courseType === 1">
                <span>直播</span>
              </div>
              <div class="live_time" v-if="item.courseType === 2">
                <span>直播+点播</span>
              </div>
            </div>
            <p>{{item.courseName}}</p>
            <div class="price_box" v-if="item.isFree">免费</div>
            <div class="price_box"  v-if="item.isFree===0 && item.courseOriginal===item.courseDiscount"><span style="font-weight:bold">￥{{item.courseOriginal.toFixed(2)}}元{{item.costTimeType | costTimeTypeChange}}</span></div>
            <div class="price_box"  v-if="item.isFree===0 && item.courseOriginal > item.courseDiscount"><span style="font-size:10px;text-decoration:line-through">原￥{{item.courseOriginal}}元</span><span style="font-weight:bold;;color: #FF5151">现￥{{item.courseDiscount}}元{{item.costTimeType | costTimeTypeChange}}</span></div>
            <div class="price_box"  v-if="item.isFree===0 && item.courseOriginal < item.courseDiscount"><span style="font-weight:bold">￥{{item.courseDiscount}}元{{item.costTimeType | costTimeTypeChange}}</span></div>
          </router-link>
        </li>
      </ul>
      <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
    </div>
    <y-footer></y-footer>
    <right-tap></right-tap>
  </div>
</template>
<script>
import YHeader from '~/components/common/Header'
import YFooter from '~/components/common/Footer'
import YHeaderList from '~/components/HeaderList'
import DPage from '~/components/Page'
import RightTap from '~/components/common/RightTap'
import {courseList, courseClass} from '~/api/course.js'
import {courseChange} from '~/utils/commonfun.js'
export default {
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
  },
  data () {
    return {
      openVip: false,
      free: '',
      activityList: [],
      classList: []
    }
  },
  async asyncData (context) {
      console.log("=====================================>>>>>>>>>>>>>>>>>>>>>>>>")
    let dataObj = {}
    let clientNo = context.store.state.clientData.no
    dataObj.clientNo = clientNo
    dataObj.webInfo = context.store.state.webInfo
    let subjectId1 = context.query.subjectno1 || ''
    let subjectId2 = context.query.subjectno2 || ''
    let subjectId3 = context.query.subjectno3 || ''
    let isFree = ''
    let courseType = context.query.courseType;
    let free = 3
    let isVipFree = ''
    if (context.query.four) {
      let four = context.query.four
      if (parseInt(four) === 3) {
        isFree = ''
        isVipFree = ''
        free = 3
      } else if (parseInt(four) === 2) {
        isFree = 0
        isVipFree = ''
        free = 2
      } else if (parseInt(four) === 1) {
        isFree = 1
        isVipFree = ''
        free = 1
      } else if (parseInt(four) === 4) {
        isVipFree = 1
        isFree = ''
        free = 4
      }
    } else {
      isFree = ''
      free = 3
    }

      if (context.query.fiveNow) {
          let fiveNow = context.query.fiveNow
          if (parseInt(fiveNow) === 5) {
              courseType = ''
          } else if (parseInt(fiveNow) === 2) {
              courseType = 2
          } else if (parseInt(fiveNow) === 1) {
              courseType = 1
          } else if (parseInt(fiveNow) === 0) {
              courseType = 0
          }
      } else {
          courseType = '';
      }
      console.log("=======>>>>>>>"+courseType)
    let obj = {
          subjectId1,
          subjectId2,
          subjectId3,
          //orgNo: clientNo,
          pageCurrent: 1,
          pageSize: 20,
          isFree,
          courseType
        }
    let pageObj = {
      list: [],
      pageCurrent: '',
      pageSize: '',
      totalCount: '',
      totalPage: ''
    }
    let classObj = {
      subjectType: 5,
      orgNo: clientNo
    }
    try {
      let allData = await Promise.all([courseList(obj), courseClass(classObj)])
      // 课程列表
      let courseData = allData[0]
      if (courseData.data.data.list.length > 0) {
        pageObj = courseData.data.data
      }
      // 分类
      let classData = await allData[1]
      let classList = classData.data.data.courseSubjectList || []
      dataObj.obj = obj
      dataObj.pageObj = pageObj
      dataObj.classList = classList
      dataObj.free = free
      return dataObj
    } catch (e) {
      context.error({ message: 'User not found list', statusCode: 404 })
    }
  },
  watch: {
    '$route' () {
      courseChange(this)
        this.obj.courseType = this.$route.query.courseType;
        this.obj.courseName = this.$route.query.courseName;
      if(this.obj.courseType == 5){
          this.obj.courseType = '';
      }
      this.getCourse()
    }
  },
  methods: {
    getCourse () {
      this.$nuxt.$loading.start();
      courseList(this.obj).then(res => {
        this.$nuxt.$loading.finish();
        console.log(res)
        let result = res.data
        if (result.code === 200) {
          if (result.data.list.length > 0) {
            this.pageObj = result.data;
          } else {
            this.pageObj = {}
          }
        } else {
          this.$msgBox({
            content: result.msg,
            isShowCancelBtn: false
          })
          this.pageObj = {}
        }
      }).catch(() => {
        this.$nuxt.$loading.finish();
        this.$msgBox({
          content: '数据加载失败，请稍后重试',
          isShowCancelBtn: false
        })
      })
    },
    getPage (int) {
      window.scrollTo(0, 0);
      this.obj.pageCurrent = int;
      this.getCourse();
    }
  },
  mounted () {
    if (this.webInfo && this.webInfo.isEnableVip) {
      this.openVip = true
    }
    courseChange(this)
    if (this.$route.query.free) {
      this.obj.isFree = 1
      this.free = 1
    }
    // this.getCourse()
  },
  components: {
    YHeader,
    YFooter,
    YHeaderList,
    DPage,
    RightTap
  },
    filters:{
        costTimeTypeChange(value){
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
.courselist_page {
  .course_content {
    background: rgb(245, 245, 245);
    padding: 30px 0 10px 0;
    ul {
      width: 1200px;
      margin: 0 auto;
    }
    li {
      float: left;
      border-radius: 0px;
      background: #fff;
      margin: 0px 20px 20px 0px;
      &:nth-child(4n) {
        margin-right: 0px;
      }
    }
  }
  .course_info {
    display: block;
    width: 285px;
    height: 204px;
    border-radius: 0px;
    position: relative;
    &:hover {
      text-decoration: none;
      color: blue;
      box-shadow: 0px 3px 18px blue;
      transform: translateY(-2px);
      transition: all .3s;
    }
    .img_box {
      position: relative;
      width: 285px;
      height: 140px;
      /*
      .qizi {
        background: url(../assets/image/activity/qizi.png) no-repeat center;
        position: absolute;
        top: 0;
        left: 10px;
        width: 46px;
        height: 43px;
        padding-top: 3px;
        color: #fff;
        font-size: 14px;
        text-align: center;
      }*/
      .course_img {
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
    p {
      font-size: 16px;
      margin-top: 5px;
      padding: 0 10px;
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
</style>

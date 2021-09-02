<template>
  <div class="box">
    <!--
    <top-header :headerName="this.headerName" :headerNameClass="this.headerNameClass"></top-header>-->
    <course-detail-header ref="courseDetailHeader" :courseDetail="courseDetail" :voi="videooasid" :playvideo="playvideo" :playimg="playimg" :lessonname="lessonname"></course-detail-header>
    <description ref="courseDetailDescription" :courseDetail="courseDetail" :lessonname="lessonname"></description>
    <label-content :courseDetail="courseDetail"></label-content>
    <footer-btn :courseDetail="courseDetail"></footer-btn>
  </div>
</template>
<script>
//import topHeader from '~/components/common/header.vue'
import courseDetailHeader from '~/components/courseDetail/header.vue'
import description from '~/components/courseDetail/description.vue'
import labelContent from '~/components/courseDetail/labelContent.vue'
import footerBtn from '~/components/courseDetail/footerBtn.vue'
import {findCourseDetailNotLogin,findCourseDetailHadLogin} from '~/api/index'

import VueCookie from 'vue-cookie'

import { logout } from '~/store/common'

export default {
  data(){
        return{
            headerName: '课程详情',
            headerNameClass: 1,
            videooasid:'',
            playvideo:false,
            playimg:true,
            lessonname: '',
            courseDetail: ''
        }
  },
  computed: {

  },
  methods: {
      updateLessonId(lessonId){
          this.$refs.courseDetailHeader.updateLessonId(lessonId);
      },
      updateLessonName(lessonName){
          this.$refs.courseDetailDescription.updateLessonName(lessonName);
      },
      playVideo(videoSrc){
          console.log("父playVideo：");
          this.$refs.courseDetailHeader.playA(videoSrc);
      },
    findCourseDetail(){
        let id = this.$route.query.id;

        this.userInfo = JSON.parse(VueCookie.get("wangxinMobileUserInfo"));
        if (VueCookie.get("wangxinMobileToken") && this.userInfo) {
            //如果返回未登录的信息要跳转到登录页面去
            findCourseDetailHadLogin({id: id}).then(res => {
                res = res.data;
                if (res.code === 200 ) {
                    this.courseDetail = res.data;
                } else if (res.code > 300 && res.code < 400) {
                    if(res.code === 304){
                        console.log("该账号被异地登录");
                        logout();
                        this.$router.push({path: '/login', query: {}});
                    }else {
                        console.log("登录超时、异常或者被异地登录，请重新登录")
                        logout();
                        this.$router.push({path: '/login', query: {}});
                    }
                }else{
                    console.log("系统错误");
                }
            }).catch(error => {
                //reject(error)
                console.log(error);
            })
        }else{
            findCourseDetailNotLogin({id: id}).then(res => {
                res = res.data;
                if (res.code === 200 ) {
                    this.courseDetail = res.data;
                }else if (res.code > 300 && res.code < 400) {
                    if(res.code === 304){
                        console.log("该账号被异地登录");
                        logout();
                        this.$router.push({path: '/login', query: {}});
                    }else {
                        console.log("登录超时、异常或者被异地登录，请重新登录")
                        logout();
                        this.$router.push({path: '/login', query: {}});
                    }
                }else{
                    console.log("系统错误");
                }
            }).catch(error => {
                console.log("加载数据错误");
            })
        }

    }
  },
    mounted () {
        this.findCourseDetail();
    },
  components: {
      //topHeader,
      courseDetailHeader,
      description,
      labelContent,
      footerBtn
  }
}
</script>
<style lang="sass" scoped>
.box
  padding-bottom: 50px
</style>

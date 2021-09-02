<template>
  <div>
    <div class="label_container" style="z-index: 5000">
      <ul class="label_tabs">
          <li v-for="(items, index) in tabArr" :key="index"><a @click="changeActive(index)" :class="{on: changeActivated === index}">{{items}}</a></li>
      </ul>
      <div class="xiangqing" v-if="status === 0">
        <p  v-html="courseDetail.introduce" style="margin-left:10px;font-size: 16px;background-color: #F0F0F0;word-wrap: break-word;word-break: normal ">
        </p>
      </div>
      <div class="xiangqing" v-if="status === 1" style="margin-left:10px;margin-top:20px;position: relative;margin-left:10px;overflow-y:scroll; overflow-x:scroll;height: calc(100% - 200px)">
        <ul class="chapter_lesson">
          <li v-for="(item, index) in this.courseDetail.chapterList" :key="index">
            <span style="font-size: 18px;font-weight:bold">
              第{{index+1}}章
            {{item.chapterName}}
            </span>
            <ul>
              <li v-for="(item2, index2) in item.lessonList" :key="index2">
                <span :id="item2.id" name="perioidName" style="margin-left: 10px;font-size: 16px;" @click="gotop(item2.id,item2.lessonName)">
                第{{index2+1}}课时
                {{item2.lessonName}}<span v-if="item2.isFree==1" style="font-weight: bold;color: orangered">[免费]</span><span v-if="item2.lessonType==1">[直播]</span><span v-if="item2.lessonType==2">[回放]</span>
                </span>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <div v-if="status === 2" style="margin-left:10px;margin-top:20px;position: relative;margin-left:10px;overflow-y:scroll; overflow-x:scroll;height: calc(100% - 200px)">
      </div>
      <div class="xiangqing" v-if="status === 3">
        <p style="margin-left:10px;margin-top:20px;font-size: 16px;font-weight: bold;background-color: #F0F0F0;word-wrap: break-word;word-break: normal ">
          讲师：{{this.courseDetail.teacher.teacherName}}
        </p>
        <p  v-html="this.courseDetail.teacher.introduce" style="text-indent:2em;margin-left:10px;font-size: 16px;background-color: #F0F0F0;word-wrap: break-word;word-break: normal ">
        </p>
      </div>
    </div>
		<no-content :content="noContent" v-show="isNoContent"></no-content>
		<transition name="loading">
			<loading v-show="showLoading"></loading>
		</transition>
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
  </div>
</template>
<script>
import loading from '~/components/common/loading.vue'
import {getStore,setStore,logout} from '~/store/examStore.js'
import noContent from '~/components/common/no_content/no_content.vue'
import {queryCourseVideoByLessonId} from '~/api/course'
import Alert from "~/components/common/alert";


export default {
    props: ['courseDetail'],

    data () {
    return {
        isShowAlert: false,
        timeOutFlag: false,
        toUrl: '',
        alertTitle: '',
        alertContent: '',

      status: 0,
      tabArr: [
        '详情',
        '章节',
        '练习题库',
        '教师信息'
      ],
      name: '课程详细信息',
      className: 1,
      // 位置左侧是选中的id
      changeActivated: 0,
      backgroundColor: 1,
      back: 0,
      isNoContent: false,
      // 是否显示加载动画
      showLoading: false,
      listArr: [],
      // 无内容样式
      noContent: {
        class: 'menu',
        name: '你没有订单'
      }
    }
  },
  mounted () {

  },
  computed: {

  },
    filters:{
      fil(value){
          return value+"_exam";
      }
    },
  methods: {
      closeAlert(){
          this.isShowAlert = false;
      },
      clickLessonExam(lessonId,lessonName){
          console.log("exam点击得到的课时lessonId："+lessonId)
          console.log("exam点击得到的课时lessonName："+lessonName)
          let x=document.getElementsByName("perioidName_exam");
          for(let i=0;i<x.length;i++){
              x[i].style = "margin-left: 10px;font-size: 16px;";
          }
          document.getElementById(lessonId+"_exam").style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
          setStore("selected_lesson_id_exam",lessonId+"_exam")
          //this.$router.push("/exam/index?id=1080759557655564289")
          this.$router.push({path:'/exam/index',query:{id:3}})
          /*
          axios.get('/mobileApi/countExamQuestionCourses?id='+ lessonId + '&bindType=4' +'&timeStamp='+new Date().getTime()).then(res => {
              let count = res.data.count;
              console.log("count:=====>"+count)
          })*/
          //window.scrollTo(0,0);
      },
      gotop(lessonId,lessonName){
          let that = this;
          queryCourseVideoByLessonId({lessonId: lessonId}).then(res => {
              res = res.data;
              if (res.code === 200) {
                  if(!res.data.list){
                      this.isShowAlert = true;
                      this.alertTitle = "提示";
                      this.alertContent = "查不到数据，没有视频可以播放";
                      return;
                  }
                  if (res.data.list.length > 0) {
                      that.lessonType = res.data.list[0].lessonType;

                      if(res.data.list[0].lessonType==0||res.data.list[0].lessonType==3){
                          that.videoSrc = res.data.list[0].videoOasUrl;
                      }
                      if(res.data.list[0].lessonType == 1){
                          that.videoSrc = res.data.list[0].liveBroadcastHlsAddress;
                          console.log("直播地址："+this.videoSrc )
                      }
                      that.$parent.playVideo(that.videoSrc);
                      that.$parent.updateLessonName(lessonName);
                      that.$parent.updateLessonId(lessonId);
                      /*
                          that.videoPlayObject.src(this.videoSrc);
                          this.videoPlayObject.play(); //播放控制
                    */

                      let x=document.getElementsByName("perioidName");
                      for(let i=0;i<x.length;i++){
                          x[i].style = "margin-left: 10px;font-size: 16px;";
                      }
                      document.getElementById(lessonId).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
                      setStore("selected_lesson_id",lessonId)

                  }else{
                      //console.log('查不到数据，数据异常')
                      this.isShowAlert = true;
                      this.alertTitle = "提示";
                      this.alertContent = "查不到数据，没有视频可以播放";
                      return;
                  }
              } else {
                  if (res.code > 300 && res.code < 400) {
                      if(res.code == 304){
                          //console.log("'该账号被异地登录'")
                          logout();
                          this.$router.push({path: '/login', query: {}});
                      }
                      //console.log("登录超时、异常或者被异地登录，请重新登录")
                      logout();
                      this.$router.push({path: '/login', query: {}});
                  }else {
                      if (!res.msg) {
                          //console.log("系统错误")
                          this.isShowAlert = true;
                          this.alertTitle = "提示";
                          this.alertContent = "系统错误";
                          return;
                      }else{
                          //console.log(res.msg)
                          this.isShowAlert = true;
                          this.alertTitle = "提示";
                          this.alertContent = res.msg;
                          return;
                      }
                  }
              }
          }).catch(error => {
             console.log("+=="+error)
          });


            //let v = res.data.videoOasUrl;
          /*
        let v = "";
            this.$emit('setVideoOasUrl',v)
            this.$emit('lessonName',lessonName)

            if(typeof (v) == 'undefined'|| v === null){
                this.$emit('setVideoOasUrl','no');
                this.$emit('playVideo',false)
                this.$emit('playImg',true)
            }else{
                this.$emit('playVideo',true)
                this.$emit('playImg',false)
            }*/

        //window.scrollTo(0,0);
    },
    changeActive (index) {
      console.log("===============>>>>"+index)

      this.status = index;
      this.isNoContent = false

        if(index==2){
           // this.$router.push({path: '/exam/index?id=123'})
            //this.$router.push({path: '/exam/index', query: {id: this.courseDetail.id}})
            this.$router.push({path: '/exam/index',query:{id:this.$route.query.id,"courseId":this.courseDetail.id}})
        }
        /*
      let phone = getStore('user')
        console.log("用户手机号："+phone)
      let data = await getOrderList(phone, index - 1)
      if (data.status) {
        this.listArr = data.result
      }
      if (JSON.stringify(data.result) === '{}') { // 如果没有数据那就 该咋咋把
        this.isNoContent = true
      }*/
        setTimeout(function (){
            console.log("selected_lesson_id========>>>>>??:"+getStore("selected_lesson_id22"));
            console.log("selected_lesson_id_exam========>>>>>??:"+(document.getElementById(getStore("selected_lesson_id_exam"))==null));
            if(getStore("selected_lesson_id") == null || document.getElementById(getStore("selected_lesson_id"))==null){

            }else{
                document.getElementById(getStore("selected_lesson_id")).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
            }
            if(getStore("selected_lesson_id_exam") == null || document.getElementById(getStore("selected_lesson_id_exam"))==null){

            }else{
                document.getElementById(getStore("selected_lesson_id_exam")).style = "margin-left: 10px;font-size: 16px;color: red;font-weight: bold";
            }
        }, 200);

      this.changeActivated = index
    }
  },
  components: {
    noContent,
    loading,
      Alert
  }
}
</script>
<style lang="sass" scoped>
.label_container
  .chapter_lesson
    li
      line-height: 30px
.label_tabs
	overflow: hidden
	border-top: #c7c5c6
	background-color: #fff
	margin: 0px 0 10px 0
	position: relative
	top: 10px
	border-top: 1px solid #ddd
	z-index: 10000
	width: 100%
	li
		width: 25%
		height: 3.5rem
		padding: 0 1rem
		float: left
		a
			height: 100%
			line-height: 3.5rem
			display: block
			text-align: center
			color: #484848
			&.on
				color: red
				border-bottom: 2px solid  red
.label_m_list
	background-color: #fff
	border-top: 1px solid #eee
	border-bottom: 1px solid #eee
	margin: 0px 0 10px 0
	.label_m_list_c
		height: 40px
		overflow: hidden
  .xiangqing
    padding: 30px 30px 0px 0px
    margin: 10px 0 0 10px
    background-color: #ef4040
  .xiangqing_content
    padding: 30px 30px 0 0px
    margin: 100px 0 0 100px
    word-wrap: break-word
    word-break: normal
    font-size: 100px
</style>

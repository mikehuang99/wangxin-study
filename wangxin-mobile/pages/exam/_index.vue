<template>
  <div>
    <div id="exam_body">
    <header-top :voi="videooasid" :playvideo="playvideo" :playimg="playimg" :lessonname="lessonname"></header-top>
    <title-des :courseDetail="courseDetail"></title-des>
    <correct-percent :userRightCount="this.userRightCount" :userErrorCount="this.userErrorCount"></correct-percent>

    <exam-question-types></exam-question-types>
    <exam-label-content :courseDetail="courseDetail" @setVideoOasUrl="(val)=>videooasid=val"
                   @playVideo="(val)=>playvideo=val"
                   @playImg="(val)=>playimg=val"
                   @lessonName="(val)=>lessonname=val"></exam-label-content>
    </div>
  </div>
</template>

<script>
    import headerTop from '~/components/exam/header.vue'
    import titleDes from '~/components/exam/titleDes.vue'
    import correctPercent from '~/components/exam/correctPercent.vue'
    import examLabelContent from '~/components/exam/examLabelContent.vue'
    import examQuestionTypes from '~/components/exam/examQuestionTypes.vue'
    import {viewSystemConfig} from '~/api/system'

    import {getStore,removeStore,setStore} from '~/store/common.js'
    import {findCourseDetailHadLogin} from '~/api/index'


    import VueCookie from 'vue-cookie'

    import { logout } from '~/store/common'


    export default {
        name: "index",
        data(){
            return{
                videooasid:'',
                playvideo:false,
                playimg:true,
                lessonname: '',
                courseDetail: {
                    id:''
                },
                isOpenDoExercisesDataSync: 0, //是否开启刷题数据同步,1为开启，0为关闭
                userRightCount:'',
                userErrorCount:''
            }
        },
        mounted(){
            let systemConfig = JSON.parse(getStore("systemConfig"));
            if(systemConfig==null){
                this.getSystemConfig();
            }else {
                this.isOpenDoExercisesDataSync = systemConfig.isOpenDoExercisesDataSync;
            }

            this.getSystemConfig();
            this.findCourseDetail();

        },
        methods:{
            calUserRightAndErrorCount(){
                if(this.isOpenDoExercisesDataSync!=1){//只是不同步的情况下计算，同步的情况下在电脑端计算
                    this.userRightCount = 0;
                    this.userErrorCount = 0;

                    let lessonIdList = [];
                    let chapterList = this.courseDetail.chapterList;
                    if(chapterList){
                        for(let i=0;i<chapterList.length;i++){
                            let lessonList = chapterList[i].lessonList;
                            for(let n=0;n<lessonList.length;n++){
                                //console.log("lessonList[n].id:"+lessonList[n].id);
                                lessonIdList.push(lessonList[n].id);
                            }
                        }
                    }
                    for(let c=0;c<lessonIdList.length;c++){
                        let answerRightArray = JSON.parse(getStore("answer_right_"+lessonIdList[c]));
                        let rightCount = answerRightArray==null?0:answerRightArray.length;
                        this.userRightCount = Number(rightCount) + Number(this.userRightCount);

                        let answerErrorArray = JSON.parse(getStore("answer_error_"+lessonIdList[c]));
                        let errorCount = answerErrorArray==null?0:answerErrorArray.length;
                        this.userErrorCount = Number(errorCount) + Number(this.userErrorCount);
                    }
                    if(this.userRightCount==0&&this.userErrorCount==0){
                        this.userRightCount = 100;
                        this.userErrorCount = 0;
                    }
                    console.log("this.userRightCount:"+this.userRightCount);
                    console.log("this.userErrorCount:"+this.userErrorCount);
                }else{
                    this.userRightCount = this.courseDetail.userRightCount;
                    this.userErrorCount = this.courseDetail.userErrorCount;
                }

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
                            this.calUserRightAndErrorCount();
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
                    logout();
                    this.$router.push({path: '/login', query: {}});
                }
            },
            getSystemConfig () {
                viewSystemConfig({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        // console.log("====>"+res.data)
                        setStore("systemConfig", JSON.stringify(res.data));
                    } else if (res.code > 300 && res.code < 400) {
                        setStore("lastUrlTemp",window.location.href);
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
                }).catch(() => {
                })
            },
        },
        components: {
            headerTop,
            titleDes,
            correctPercent,
            examQuestionTypes,
            examLabelContent
        }
    }
</script>

<style scoped>

</style>

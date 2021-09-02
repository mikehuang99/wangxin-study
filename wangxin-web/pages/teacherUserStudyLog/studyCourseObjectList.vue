<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'teacherUserStudyLog'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on" >课程学生</a>
          <div @click="back" class="fr solid_btn">返回</div>
        </ul>
        <div class="main_cont form">
          <span>课程名称：{{courseName}}</span><br/><br/>
          <form action="">
            <div class="form_group">
              <div class="label">学生编号:</div>
              <div class="form_ctl_search">
                <input type="text" v-model="pda.bindUserNo" class="form_input_mid" placeholder="请输入学生编号">
              </div>
              <div class="label">学生姓名:</div>
              <div class="form_ctl_search">
                <input type="text" v-model="pda.realName" class="form_input_mid" placeholder="请输入学生姓名">
              </div>
            </div>
            <div class="form_group">
              <div class="label">进度>=:</div>
              <div class="form_ctl_search">
                <input type="text" v-model="pda.totalStudyProgressGreaterThan" class="form_input_mid" placeholder="请输入0到100数字，不要输入%">
              </div>
              <div class="label">进度<=:</div>
              <div class="form_ctl_search">
                <input type="text" v-model="pda.totalStudyProgressLessThan" class="form_input_mid" placeholder="请输入0到100数字，不要输入%">
              </div>
            </div>
            <div class="form_group">
              <div class="form_ctl_search">
                <label></label>
              </div>
              <div @click="getStudyCourseObjectList(true)" v-if="queryState==false" class="fr solid_btn">
                查询
              </div>
              <div v-if="queryState==true" class="fr solid_btn">
                查询中...
              </div>
            </div>
          </form>
          <div class="main_cont">
            <div class="notdata" v-if="notdata">
              ★暂时没有数据
            </div>
            <table class="exam_object_list_table table" v-else>
              <thead>
              <tr>
                <th><input type="checkbox" @click="allSelect()"></th>
                <th>序号</th>
                <th>学生编号</th>
                <th>学生姓名</th>
                <th>手机号码</th>
                <th>学习进度</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="(item,index) in courseList" :key="item.id">
                <td><input type="checkbox" :value="item.id" v-model="pda.ids"></td>
                <td>{{index=index+1}}</td>
                <td><label :title="item.bindUserNo" class="test_a">{{item.bindUserNo}}</label></td>
                <td><label :title="item.realName" class="test_a">{{item.realName | resetValueIfNull}}</label></td>
                <td><label :title="item.mobile" class="test_a">{{item.mobile | resetValueIfNull}}</label></td>
                <td><label class="test_a">{{item.totalStudyProgress}}%</label></td>
                <td class="operate">
                  <span>
                  <router-link target="_blank" :to="{name: 'teacherUserStudyLog-userStudyLogByCourseId', query: {courseId: pda.courseId,courseName: courseName,studyUserNo: item.bindUserNo,realName: item.realName}}" class="go_btn">详细章节学习情况</router-link>
                  </span>
                </td>
              </tr>
              </tbody>
            </table>
            <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
            <div v-if="queryState==true" style="text-align:center;color: red">
              正在刷新中...
            </div>
          </div>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
  </div>
</template>

<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import YSide from '~/components/account/Side'
    import DPage from '~/components/Page'
    import {courseUserListByTeacherUserNo} from '~/api/account/course.js'

    export default {
        name: "question",

        data() {
            return {
                studyUserNo:'',

                courseName: '',

                isAllSelect:false,
                chapterIds:[],
                lessonIds:[],
                subject: "",
                answer: "",
                ccpCate1:[],
                ccpCate2:false,
                ccpCate3:false,
                cate1: [],
                cate2: false,
                cate3: false,
                queryState:false,
                obj: {
                    "id": '',

                },
                notdata: true,
                courseList: [],
                pda: {
                    ids:[],
                    couseId: '',
                    isFinishUserTotalScore:'',
                    pageCurrent: 1,
                    pageSize: 20
                },
                pageObj: {
                    pageCurrent: '',
                    pageSize: '',
                    totalCount: '',
                    totalPage: ''
                },
                examStatusList:[{
                    "id":"0",
                    "name":"未考试"
                },{
                    "id":"1",
                    "name":"已经完成考试评分"
                },{
                    "id":"2",
                    "name":"等待教师评分"
                }]

            }
        },
        components: {
            YHeader,
            YFooter,
            YSide,
            DPage
        },
        filters: {
            cutTile(value) {
                if (!value) return ''
                if (value.length > 14) {
                    return value.slice(0, 14) + '...'
                }
                return value
            },
            resetValueIfNull(value) {
                if (!value) return '_';
                return value
            },
            examStatus(value) {
               // if (!value) return '_';
                if(value==0){
                    return '未考试';
                }
                if(value==1){
                    return '已经完成考试评分';
                }
                if(value==2){
                    return '等待教师评分';
                }
            }
        },
        methods: {
            back(){
                this.$router.go(-1);//返回上一层
            },
            allSelect(){
                if(this.isAllSelect==false){
                    //全选
                    for(var i=0;i<this.courseList.length;i++){
                        //this.ids[i]=this.courseList[i].id;
                        this.pda.ids.push(this.courseList[i].id)
                    }
                }else {
                    //取消全选
                    this.pda.ids = [];
                }
                this.isAllSelect = !this.isAllSelect;
            },
            getPage: function (int) {
                this.pageCurrent = int;
                this.getStudyCourseObjectList(false);
            },
            //isStartFromFirstPage，是为了用户停留在非第一页的情况下，点击查询而查询不到数据，设置该值为false表示从第一页开始查
            getStudyCourseObjectList(isStartFromFirstPage) {
                this.queryState = true;
                let astat = this.tab === 3 ? '' : this.tab;
                this.pda.applyStatus = astat;
                if(isStartFromFirstPage){
                    this.pda.pageCurrent = 1;
                }else {
                    this.pda.pageCurrent = this.pageCurrent;
                }
                this.pda.examManagementId = this.$route.query.examManagementId;
                courseUserListByTeacherUserNo(this.pda).then(res => {
                    let result = res.data
                    console.log(result)
                    console.log('course----')
                    if (result.code === 200 && result.data.list.length > 0) {
                        this.courseList = result.data.list;
                        this.notdata = false;
                        this.pageObj = result.data;
                    } else if (result.code > 300 && result.code < 400) {
                        if(result.code == 304){
                            this.$msgBox({
                                content: '该账号被异地登录',
                                isShowCancelBtn: false
                            }).then(() => {
                                this.$store.dispatch('REDIRECT_LOGIN', result.code)
                            }).catch(() => {
                                this.$store.dispatch('REDIRECT_LOGIN', result.code)
                            })
                        }
                        this.$msgBox({
                            content: '登录超时、异常或者被异地登录，请重新登录',
                            isShowCancelBtn: false
                        }).then(() => {
                            this.$store.dispatch('REDIRECT_LOGIN', result.code)
                        }).catch(() => {
                            this.$store.dispatch('REDIRECT_LOGIN', result.code)
                        })
                    } else {
                        this.notdata = true;
                        this.pageObj.totalPage = 0;
                    }
                    this.queryState = false;
                }).catch(msg => {
                    this.queryState = false;
                    this.$msgBox({
                        content: '加载数据失败，请稍后刷新再试！！',
                        isShowCancelBtn: false
                    })
                })
            }

        },
        // 页面打开
        mounted() {


            this.pda.courseId = this.$route.query.courseId;
            this.courseName = this.$route.query.courseName;



            this.getStudyCourseObjectList(false);





        }
    }

</script>

<style lang="scss" rel="stylesheet/scss">
  @import '~/assets/css/account.scss';

  .exam_object_list_table {
    .name {
      width: 400px;

      .txt {
        width: 190px;
        padding-top: 12px;
        font-size: 14px;
      }
    }

    .icon_min {
      width: 35px;
      vertical-align: middle;
    }
  }

  .form_ctl_search {
    margin-left: 12px;
    float: left;
    width: 200px;
    position: relative;
    z-index: 9;
  }
  .form_input_mid {
    text-indent: 1em;
    width: 230px;
    height: 34px;
    margin-right: 10px;
    border: 1px solid #e7e7e7;
  }

  .test_a {
    text-align: center;
  }

  .btn_box {
    background-color: #4CAF50;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
  }
</style>

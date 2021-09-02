<template>
  <div>
    <div>
    <ul v-load-more="loaderMore" v-if="courseArr.length">
      <div tag='li'

                   :data-id="items.id" :key="index" v-for="(items, index) in courseArr">
        <div class="course_child_list n">
          <div class="course_child_con" :id="items.id | conFilter">
            <div class="course_content_box">
              <b href="#" class="con_left_area">
                <img :src="items.courseLogo" height="20%" width="100%">
              </b>
              <ul class="con_right_area on" style="width:70%;padding-left:6px;">
                <li class="course_title_h1">
                  <h1>
										<i>{{items.courseName}}</i>
									</h1>
                </li>
                <div>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold">有效期:{{items.validTerm}}</span>
                    </div>
                  </li>
                  <li class="course_price">
                    <div>
                      <span style="font-weight:bold">学习进度:{{items.totalStudyProgress}}%</span>
                      <span class="had_finish" v-if="items.totalStudyProgress==100"></span>
                    </div>
                  </li>
                </div>
                <div class="btn_box" @click="toDetailPage(items.courseId)">
                  进入学习
                </div>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </ul>
		<no-content :content="noContent" v-show="this.courseArr.length===0"></no-content>
		<p class="no_data" v-show="this.touchend">没有更多课程</p>
		<transition name="loading">
			<loading v-show="showLoading"></loading>
		</transition>
    </div>
    <div style="height: 70px">
      <Footer></Footer>
    </div>
	</div>
</template>
<script>
import loading from '~/components/common/loading.vue'
import noContent from '~/components/common/no_content/no_content.vue'
import {loadMore} from '~/plugins/loadMorePlugin.js'
import {findCourselist} from '~/api/index'
import {listMyCourse} from '~/api/course'
import { logout } from '~/store/common'
import Footer from '~/components/index/footer/Footer.vue'


export default {
    data () {
    return {
        touchend: false,
        courseArr: [],
        pageOnShowing: false,//页面是否正在显示
      // 请求条数
      offset: 0,
      // 是否可以请求
      preventRepeatreuqest: false,
      // 模糊查询的当前请求条数 0
      likeOffset: 0,
      // 是否显示加载动画
      showLoading: false,
      // 无内容是否显示
      isDisplayNo: false,
      // 无内容样式
      noContent: {
        class: 'class',
        //name: '没有找到课程'
        name: '什么也没有'
      },
      obj: {
          subjectId1: '',
          subjectId2: '',
          subjectId3: '',
          courseName: '',
          isFree: '',//是否免费：1免费，0收费
          courseType: '',//课程类型，0点播，1直播，2点播+直播
          sortType: ''//排序类型数值,1、智能排序（按预设sort排序）,2、学习数量,3、购买数量,4、原价最高,5、原价最低,6、折扣价后最高,7、折扣价后最低
      },
      page: {
          pageCurrent: 0,
          pageSize: 20,
          totalCount: 0,
          totalPage: 0
      }
    }
  },
  mounted () {
      //进入页面钩子函数代码开始
      this.$nextTick(()=>{
          this.pageOnShowing = true;//进入页面设为true
      });
      this.initData()
  },
  //离开当前页面后执行
  destroyed: function () {
      this.pageOnShowing = false;//离开页面设为false

  },
  props: ['courseName','sortType',  'selectedFeeType','selectedCourseType','levelTwoId','levelThreeId'],
  mixins: [loadMore],
  computed: {

  },
  components: {
    loading,
    noContent,
      Footer
  },
    filters:{
        conFilter(item) {
            return "con_"+item;
        },
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

    },
  methods: {
      reloadCourseList(courseName) {
          this.courseName = courseName;
      },
      toDetailPage(id){
          /*
         let itemLength = document.getElementsByClassName("con_"+id).length;
         for(let i=0;i<itemLength;i++){
               document.getElementsByClassName("con_"+id)[i].style = 'background: #fff';
         }
          document.getElementById("con_"+id).style = 'background: #D3D3D3';
          */
          this.$router.push({path: 'courseDetail/index', query: {id: id}});
      },
    initData () {
      this.offset = 0
      this.findCourselist();
    },
    findCourselist(offset) {

          if(offset==0){
              this.page.pageCurrent=0;
              this.page.pageSize = 20;
              this.page.totalCount = 0;
              this.page.totalPage = 0;

              this.courseArr = [];
          }
          if(this.page.pageCurrent!=0&&(this.page.pageCurrent+1)>this.page.totalPage){
              this.touchend = true;
              return;
          }
          this.showLoading = true;
          listMyCourse({'courseName':this.obj.courseName,'subjectId1':this.obj.subjectId1,'subjectId2':this.obj.subjectId2,'subjectId3':this.obj.subjectId3,
               'isFree':this.obj.isFree,'courseType':this.obj.courseType,
               'sortType':this.obj.sortType,
               'pageCurrent':this.page.pageCurrent+1,'pageSize':this.page.pageSize}).then(res => {
                if (res.data.code === 200) {
                    this.showLoading = false;
                    let list = res.data.data.list || [];
                    this.courseArr = this.courseArr.concat(list);
                    this.page.pageCurrent= res.data.data.pageCurrent;
                    this.page.pageSize = res.data.data.pageSize;
                    this.page.totalCount = res.data.data.totalCount;
                    this.page.totalPage = res.data.data.totalPage;
                    //alert(list.length)
                }else if (res.data.code > 300 && res.data.code < 400) {
                    if(res.data.code === 304){
                        console.log("该账号被异地登录");
                        logout();
                        this.$router.push({path: '/login', query: {}});
                    }else {
                        console.log("登录超时、异常或者被异地登录，请重新登录")
                        logout();
                        this.$router.push({path: '/login', query: {}});
                    }
                } else {
                      console.log('88999！')
                }
           }).catch(() => {
               console.log("请求异常");
               this.showLoading = false;
           });

    },
    loaderMore () { // 加载更多的方法
        this.findCourselist();
    },
  },
  watch: {
    courseName: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.courseName = value;
        this.findCourselist(0);
    }
      /*,
sortType: function (value) {
  this.obj.sortType = value;
  this.findCourselist(0);
},
levelTwoId: function (value) {
  this.obj.subjectId2 = value;
  this.findCourselist(0);
},
levelThreeId: function (value) {
  this.obj.subjectId3 = value;
  this.findCourselist(0);
},
selectedFeeType: function (value) {
  this.obj.isFree = value;
  this.findCourselist(0);
},
selectedCourseType: function (value) {
  this.obj.courseType = value;
  this.findCourselist(0);
}*/
  }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .course_child_list {
    .course_child_con {
      background: #fff;
  }
    &.n{
      padding: 0;
      .course_child_con{
        &.jie{
          background: #fff url("/wxmobile/img/jie_class.png") no-repeat 96% 1rem;
          background-size: 85px 68px;
      }
        border-bottom: 1px solid #D0D0D0;
        border-top: 1px solid #D0D0D0;
        -webkit-box-shadow: none;
        box-shadow: none;
        padding: 0 10px;
        margin: 0 0 10px 0;
        .course_content_box{
          overflow: hidden;
          .con_right_area{
            float: right;
            li {
              margin-bottom: 0.7rem;
              font-size: 1.2rem;
          }
            .course_title_h1{
              h1 {
                font-size: 1.6rem;
                margin-top: 19px;
              }
              i {
                display: inline-block;
                overflow: hidden;
                max-width: 90%;
                text-overflow: ellipsis;
                white-space: pre-line;
              }
              span {
                display: inline-block;
                margin-top: 0.5rem;
                margin-left: 3px;
              }
              .zhekou {
                &:after {
                  content: '折';
                  display: inline-block;
                  width: 16px;
                  height: 16px;
                  line-height: 17px;
                  background: #ff780b;
                  font-size: 1rem;
                  color: #fff;
                  border-radius: 10px;
                  text-align: center;
                  position: relative;
                  top: -7px;
                }
              }
              .zhibo {
                &:after {
                  content: '直播';
                  display: inline-block;
                  width: 32px;
                  height: 16px;
                  line-height: 17px;
                  background: red;
                  font-size: 1rem;
                  color: #fff;
                  border-radius: 2px;
                  text-align: center;
                  position: relative;
                  top: -7px;
                }
              }
              .zhiboandlubo {
                &:after {
                  content: '直播+录播';
                  display: inline-block;
                  width: 80px;
                  height: 16px;
                  line-height: 17px;
                  background: #ff780b;
                  font-size: 1rem;
                  color: #fff;
                  border-radius: 2px;
                  text-align: center;
                  position: relative;
                  top: -7px;
                }
              }
          }
            .course_price {
              text-overflow: ellipsis;
              max-width: 200px;
              white-space: nowrap;
              overflow: hidden;
              padding-left: 18px;
              background: url('/wxmobile/img/wxImg/item.png') no-repeat 0 3px;
              background-size: 12px 12px;
              .had_finish {
                margin-left: 15px;
                font-weight: bold;
                &:before {
                  content: '已完成';
                  display: inline-block;
                  width: 40px;
                  height: 16px;
                  margin-left: 0px;
                  line-height: 17px;
                  background: green;
                  font-size: 1rem;
                  color: #fff;
                  border-radius: 3px;
                  text-align: center;
                  position: relative;
                  top: 0px;
                }
              }
            }
          }
          .con_left_area{
            width: 30%;
            float: left;
            padding: 15px 0 15px 0;
            position: relative;
            img {
              border: 1px solid #eee;
              border-radius: 8px;
          }
            p {
              font-size: 1.2rem;
              text-align: center;
              color: #666;
              margin: 6px 0 0 0;
          }
            span {
              color: #cf1b1b;
          }
        }
      }
    }
  }
  }
  .no_data {
    text-align: center;
    line-height: 2rem;
    font-size: 1rem;
    color: #666;
  }
  .loading-enter-active, .loading-leave-active {
    transition: opacity 1s
  }
  .loading-enter, .loading-leave-active {
    opacity: 0
  }
  .btn_box {
    float: right;
    width: 100px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 12px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }
</style>

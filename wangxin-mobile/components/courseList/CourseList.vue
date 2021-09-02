<template>
  <div>
    <div>
    <ul v-load-more="loaderMore" v-if="courseArr.length">
      <div tag='li'
                   @click="toDetailPage(items.id)"
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
                    <span class="zhibo" v-if="items.courseType==1"></span>
                    <span class="zhiboandlubo" v-if="items.courseType==2"></span>
                    <span class="zhekou" v-if="items.isFree===0 && items.courseOriginal > items.courseDiscount"></span>
									</h1>
                </li>
                <div>
                  <li class="course_price">
                    <div v-if="items.isFree===1">
                      <span style="font-weight:bold;font-size:14px;color: orangered;">免费</span>
                      <span class="study_count" v-if="items.studyCount>=100">[{{items.studyCount}}]</span>
                    </div>
                    <div v-if="items.isFree===0 && items.courseOriginal===items.courseDiscount">
                      <span style="font-weight:bold">{{items.courseOriginal}}元{{items.costTimeType | costTimeTypeChange}}</span>
                      <span class="study_count" v-if="items.studyCount>=100">[{{items.studyCount}}]</span>
                    </div>
                    <div v-if="items.isFree===0 && items.courseOriginal > items.courseDiscount">
                      <span style="font-size:10px;text-decoration:line-through">原{{items.courseOriginal}}元</span>
                      <span style="font-weight:bold;;color: #FF5151">现{{items.courseDiscount}}元{{items.costTimeType | costTimeTypeChange}}</span>
                      <span class="study_count" v-if="items.studyCount>=100">[{{items.studyCount}}]</span>
                    </div>
                    <div v-if="items.isFree===0 && items.courseOriginal < items.courseDiscount">
                      <span style="font-weight:bold">{{items.courseDiscount}}元{{items.costTimeType | costTimeTypeChange}}</span>
                      <span class="study_count" v-if="items.studyCount>=100">[{{items.studyCount}}]</span>
                    </div>
                  </li>
                </div>
                <div style="height: 20px">
                  <img src="/wxmobile/img/wxImg/item.png" style="display: inline-block;width: 12px;height: 12px"/>
                  <div style="margin-left: 3px;font-size:12px;display: inline-block">
                    {{items.subjectName1}}<span v-if="items.subjectName2">/</span>{{items.subjectName2}}<span v-if="items.subjectName3">/</span>{{items.subjectName3}}
                  </div>
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
      //this.findCourselist();
      //进入页面钩子函数代码开始
      this.$nextTick(()=>{
          console.log("进入页面")
          this.pageOnShowing = true;//进入页面设为true
      });
      this.initData()

      //进入页面钩子函数代码结束
      /*
      if (this.$route.query.name) {
        this.likeCourse()
      } else {
        this.initData()
      }*/

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
      toDetailPage(id){
         let itemLength = document.getElementsByClassName("con_"+id).length;
         for(let i=0;i<itemLength;i++){
               document.getElementsByClassName("con_"+id)[i].style = 'background: #fff';
         }
          document.getElementById("con_"+id).style = 'background: #D3D3D3';
          this.$router.push({path: 'courseDetail/index', query: {id: id}});
      },
    setStyle (data) {
      // 如果数据为空 把空样式显示改为 true
      if (data.data.length === 0) this.isDisplayNo = true
      //if (data.data.length < 12)

      this.showLoading = false
    },
     likeCourse () {
          /*
      let data = await getSearchCourse(this.$route.query.name, this.likeOffset)
      this.setStyle(data)*/
    },
    initData () {
      this.offset = 0
      //let data = await courselist(this.offset, this.courseId, this.coursetype, this.courseSort, this.selectScreen)
       // console.log("-------======>>>>"+eval(data.data.list))
       // console.log("-------======>>>>"+eval(data.data))
      //this.setStyle(data)
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
           findCourselist({'courseName':this.obj.courseName,'subjectId1':this.obj.subjectId1,'subjectId2':this.obj.subjectId2,'subjectId3':this.obj.subjectId3,
               'courseName':this.obj.courseName,'isFree':this.obj.isFree,'courseType':this.obj.courseType,
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
                } else {
                      console.log('88999！')
                }
           }).catch(() => {
               console.log("请求异常");
               this.showLoading = false;
           });

    },
    controllerLoad () {
        if (this.touchend) {
        return
      }
      // 到达底部防止重复加载
      if (this.preventRepeatreuqest) {
        return
      }
      // 更改变量为true 禁止再次加载
      this.preventRepeatreuqest = true
      // loading显示
      this.showLoading = true
      if (this.$route.query.name) { // 判断谁加offset
        this.likeOffset += 15
      } else {
        this.offset += 15
      }
      console.log("======>" + this.offset);
    },
    setData (data) {
      // 恢复控制变量为false
      setTimeout(() => {
        this.preventRepeatreuqest = false
        // 恢复控制变量为false
        this.showLoading = false
      }, 2000)
      // 小于15条 限时暂无更多
        console.log("数据长度："+data.data.length)
      //if (data.data.length < 15) {
       // return false
      //}
    },
    // 加载到底部加载更多
     likeLoadMore () {
      this.controllerLoad() // 设置变量是否可以再次加载
      //let data = await getSearchCourse(this.$route.query.name, this.likeOffset)
      // 提交数据 --》状态管理
      //this.setData(data)
    },
    // 加载到底部加载更多
     filterLoadMore () {
      this.controllerLoad() // 设置变量是否可以再次加载
        if(this.offset == 0){
            this.offset = 15;
        }
        this.offset= this.offset + 15;


    },
    loaderMore () { // 加载更多的方法
        console.log("加载。。。。。。。。");
        this.findCourselist();
        /*
        if(this.pageOnShowing){//页面打开的时候才执行
          if (this.$route.query.name) {
             this.likeLoadMore() // 如果有参数 就查询走模糊查询
          } else {
            this.filterLoadMore() // 然则 查询筛选的把
          }
        }*/
    },
  },
  watch: {
    courseName: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.courseName = value;
        this.findCourselist(0);
    },
    sortType: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.sortType = value;
        this.findCourselist(0);
    },
    levelTwoId: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.subjectId2 = value;
        this.findCourselist(0);
    },
    levelThreeId: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.subjectId3 = value;
        this.findCourselist(0);
    },
    selectedFeeType: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.isFree = value;
        this.findCourselist(0);
    },
    selectedCourseType: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.courseType = value;
        this.findCourselist(0);
    }
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
              background: url('/wxmobile/img/wxImg/price.png') no-repeat 0 3px;
              background-size: 12px 12px;
              .study_count {
                margin-left: 15px;
                font-weight: bold;
                &:before {
                  content: '学习人数';
                  display: inline-block;
                  width: 55px;
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

</style>

<template>
  <div>
    <div>
    <ul v-if="studyLogArr.length">
      <div tag='li' :data-id="item.id" :key="index" v-for="(item, index) in studyLogArr">
        <div class="course_child_list n">
          <div class="course_child_con" >
            <div class="course_content_box coupon_sty">
              <ul class="context_area on" style="width:100%;padding-left:6px;">
                <li class="course_title_h1">
                  <h1>
                    <i>[{{index+1}}].{{item.couponName}}</i>
									</h1>
                </li>
                <div>

                  <li class="course_item">
                    <div>
                      <span>
                         优惠金额:<span style="font-weight: bold;color: red;font-size: 18px">{{item.couponAmount}}元</span>
                        <span v-if="item.isLimitCourse==0">[不限制课程]</span><span v-if="item.isLimitCourse==1">[部分课程适用]</span>
                      </span>
                    </div>
                  </li>
                  <li class="course_item">
                    <div>
                      <span>
                           [有效期:{{item.couponTimeBegin}}至{{item.couponTimeEnd}}]
                      </span>
                    </div>
                  </li>
                </div>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </ul>
		<no-content :content="noContent" v-show="this.studyLogArr.length===0"></no-content>
		<p class="no_data" v-show="this.touchend">没有更多优惠券</p>
		<transition name="loading">
			<loading v-show="showLoading"></loading>
		</transition>
    </div>
    <div style="height: 70px">
      <Footer></Footer>
    </div>
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" :toUrlParams="toUrlParams" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
	</div>
</template>
<script>
import loading from '~/components/common/loading.vue'
import noContent from '~/components/common/no_content/no_content.vue'
import {loadMore} from '~/plugins/loadMorePlugin.js'
import {listUserCoupon} from '~/api/market'
import { logout } from '~/store/common'
import Footer from '~/components/myCoupon/Footer.vue'
import Alert from "~/components/common/alert";


export default {
    data () {
    return {
        isShowAlert: false,
        timeOutFlag: false,
        toUrl: '',
        alertTitle: '',
        alertContent: '',
        toUrlParams: [],

        touchend: false,
        studyLogArr: [],
        pageOnShowing: false,//页面是否正在显示
      // 请求条数
      offset: 0,

      // 是否显示加载动画
      showLoading: false,

      // 无内容样式
      noContent: {
        class: 'class',
        //name: '没有找到课程'
        name: '什么也没有'
      },
      obj: {
          courseName: ''
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
  props: ['courseName'],
  mixins: [loadMore],
  computed: {

  },
  components: {
    loading,
    noContent,
      Footer,
      Alert
  },
    filters:{


    },
  methods: {
      closeAlert(){
          this.isShowAlert = false;
      },
      reloadCouponList(courseName) {
          this.courseName = courseName;
      },
      toDetailPage(id){
          this.$router.push({path: '/courseDetail/index', query: {id: id}});
      },
    initData () {
      this.offset = 0
      this.findCouponList();
    },
    findCouponList(offset) {

          if(offset==0){
              this.page.pageCurrent=0;
              this.page.pageSize = 20;
              this.page.totalCount = 0;
              this.page.totalPage = 0;

              this.studyLogArr = [];
          }

          this.showLoading = true;
        listUserCoupon({
               }).then(res => {
                if (res.data.code === 200) {
                    this.showLoading = false;
                    let list = res.data.data.list || [];
                    this.studyLogArr = list;
                    /*
                    this.studyLogArr = this.studyLogArr.concat(list);
                    this.page.pageCurrent= res.data.data.pageCurrent;
                    this.page.pageSize = res.data.data.pageSize;
                    this.page.totalCount = res.data.data.totalCount;
                    this.page.totalPage = res.data.data.totalPage;
                    */

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

    }
  },
  watch: {
    courseName: function (value) {
        if(typeof(value)=="undefined"){
            return;
        }
        this.obj.courseName = value;
        this.findCouponList(0);
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
        border-bottom: 1px solid #D0D0D0;
        border-top: 1px solid #D0D0D0;
        -webkit-box-shadow: none;
        box-shadow: none;
        padding: 0 10px;
        margin: 0 0 10px 0;
        .course_content_box{
          overflow: hidden;
          margin-left: 1px;
          margin-right: 1px;
          //border: solid 1px red;
          border-radius: 5px;
          background-color: #fff;
          .context_area{
            //float: right;
            li {
              margin-bottom: 0.7rem;
              font-size: 1.2rem;
            }
            .course_title_h1{
              h1 {
                font-size: 1.3rem;
                margin-top: 5px;
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
          }
            .course_item {
              text-overflow: ellipsis;
              max-width: 300px;
              white-space: nowrap;
              overflow: hidden;
              padding-left: 18px;
              background: url('/wxmobile/img/wxImg/item.png') no-repeat 0 3px;
              background-size: 12px 12px;
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
    width: 68px;
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
  .cancel_btn_box {
    float: left;
    width: 68px;
    background-color: red;
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
  .continue_btn_box {
    float: right;
    width: 68px;
    background-color: green;
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
  .coupon_sty {
    width: 100%;
    height: 100px;
    background-image: radial-gradient(circle at 18px 16px ,transparent 0%, transparent 16px,#FF7744  16px, #FF7744  10%);
    background-position: 250px -16px;
    background-size: 100% 100px;
  }
</style>

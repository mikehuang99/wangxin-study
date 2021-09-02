<template>
    <div class="myinfo_contain" id="myinfo_contain">
      <div class="myinfo_contain_top">
          <div class="myinfo_contain_top_header">
            <p>
              <img v-if="isLogin" :src="headImgUrl==null?'/wxmobile//img/wxImg/no_img.png':headImgUrl" alt="">
              <img v-if="!isLogin" src="/wxmobile/img/wxImg/no_img.png" alt=""></p>
            <br/>
            <div style="font-size: 18px">{{mobile}}</div>
            <div v-if="isLogin" class="btn_box" @click="userLogout">退出登录</div>
            <div v-if="!isLogin" class="btn_box" @click="toLogin">登录</div>
          </div>
      </div>
      <div class="my-grid-container">
        <div class="my-grid-item" @click="toMyCourse">我的课程</div>
        <div class="my-grid-item" @click="toMyExamManagement">我的考试</div>
        <div class="my-grid-item" @click="toMyOrder">我的订单</div>
        <div class="my-grid-item" @click="toMyStudyCard">我的学习卡</div>
        <div class="my-grid-item" @click="toCollectCoupon">领取优惠券</div>
        <div class="my-grid-item" @click="toMyCoupon">我的优惠券</div>
        <div class="my-grid-item" @click="toMyDot">点券记录</div>
        <div class="my-grid-item" @click="toMyPoint">积分记录</div>
        <div class="my-grid-item" @click="toMyStudyLog">学习记录</div>
        <div class="my-grid-item" @click="toMyAccount">我的信息</div>
        <div class="my-grid-item" @click="toResetPsw">重置密码</div>
        <div class="my-grid-item" @click="toContactUs">客服信息</div>
      </div>
      <Footer></Footer>
    </div>
</template>

<script>
    import Footer from '~/components/index/footer/Footer.vue'
    import {userLogout} from '~/api/user'

    import VueCookie from 'vue-cookie'

    export default {
        name: "myInfo",
        data () {
            return {
                realName: '',
                headImgUrl: '',
                nickname: '',
                mobile: '',
                isLogin: false
            }
        },
        components: {
            Footer
        },
        methods: {
            toContactUs(){
                this.$router.push({path: '/my/contactUs/contactUsIndex', query: {}});

            },
            toResetPsw(){
                this.$router.push({path: '/reset', query: {}});
            },
            toMyAccount(){
                this.$router.push({path: '/my/myAccount/myAccountIndex', query: {}});
            },
            toMyDot(){
                this.$router.push({path: '/my/myDot/myDotIndex', query: {}});
            },
            toMyPoint(){
                this.$router.push({path: '/my/myPoint/myPointIndex', query: {}});
            },
            toMyStudyCard(){
                this.$router.push({path: '/my/myStudyCard/myStudyCardIndex', query: {}});
            },
            toCollectCoupon(){
                this.$router.push({path: '/my/collectCoupon/collectCouponIndex', query: {}});
            },
            toMyCoupon(){
                this.$router.push({path: '/my/myCoupon/myCouponListIndex', query: {}});
            },
            toMyStudyLog(){
                this.$router.push({path: '/my/myStudyLog/myStudyLogIndex', query: {}});
            },
            toMyCourse(){
                this.$router.push({path: '/myCourseAllList', query: {}});
            },
            toMyExamManagement(){
                this.$router.push({path: '/my/myExamManagement/myExamManagementIndex', query: {}});
            },
            toMyOrder(){
                this.$router.push({path: '/my/myOrder/myOrderListIndex', query: {}});
            },
            toLogin() {
                this.$router.push({path: '/login', query: {}});
            },
            userLogout() {
                userLogout().then(res => {
                    if (res.data.code === 200) {
                        //this.$router.push({path: '/login', query: {}});
                    } else {
                        console.log('退出失败！')
                    }
                });
                VueCookie.delete("wangxinMobileUserInfo");
                VueCookie.delete("wangxinMobileToken");
                this.$router.push({path: '/login', query: {}});

            },
             findClientHeight(){
                let clientHeight=0;
                if(document.body.clientHeight && document.documentElement.clientHeight)
                {
                     clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
                }else{
                     clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
                }
                return clientHeight;
             }
        },
        mounted() {
            /*
            setTimeout(() => {
                document.getElementById("myinfo_contain").style.height = (this.findClientHeight()-70)+"px";
            }, 200)*/
            this.userInfo = JSON.parse(VueCookie.get("wangxinMobileUserInfo"));
            if (VueCookie.get("wangxinMobileToken") && this.userInfo) {
                this.headImgUrl = this.userInfo.headImgUrl;
                this.realName = this.userInfo.realName;
                this.nickname = this.userInfo.nickname;
                this.mobile = this.userInfo.mobile;
                this.isLogin = true;
            }
        }
    }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
.myinfo_contain{
  width: 100%;
  overflow-y: scroll;
}
.myinfo_contain .myinfo_contain_top{
  position:relative;
  width: 100%;
  height: 220px;
  overflow:hidden;
  background: #fff url('/wxmobile/img/wxImg/myinfotop.png')no-repeat 0 0;
  -moz-background-size:100% 100%;
  background-size:100% 100%;
}
.myinfo_contain_top_header{
  margin-top: 20px;
  position: absolute;
  top: 30%;
  left: 50%;
  -webkit-transform: translate(-50%,-42%);
  -o-transform: translate(-50%,-42%);
  -ms-transform: translate(-50%,-42%);
  transform: translate(-50%,-42%);
  text-align: center;
}
.myinfo_contain_top_header p{
  width: 220px;
  height: 80px;
  text-align: center;
  font-size: 15px;
  font-weight: bold;
}
.myinfo_contain_top_header img{
  width: 100%;
  height: 100%;
  padding: 10px;
  border-radius: 50%;
  background: rgba(230,230,250, 0.4);
}
.order_area{
  height:40px;
  background: white;
  margin-top: 10px;
  position: relative;
}
.order_area .order_area_head{

}
.order_area .order_area_head .order_area_head_left{
  float: left;
  margin-top:5px;
  font-size: 18px;
  height: 30px;
  width: 30px;
  background: url(/img/wxImg/myorder.png) no-repeat 1px center;
  -moz-background-size:100% 100%;
  background-size:100% 100%;
}
.order_area .order_area_head .order_area_head_right{
  height: 30px;
  float: right;
  margin-top:5px;
  font-size: 18px;
}
.order_area_body{
   width: 100%;
  margin-top: 10px;
}
.my_course_area{
  height:40px;
  background: white;
  margin-top: 5px;
  position: relative;
}
.card_area{
  height:40px;
  background: white;
  margin-top: 3px;
  position: relative;
}
.btn_box {
  border-radius: 10px;
  width: 200px;
  background-color: orangered;
  border: none;
  color: white;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 20px;
  margin: 4px 2px;
  cursor: pointer;
}
.my-grid-container {
  margin-top: 10px;
  display: grid;
  grid-template-columns: auto auto auto;
  background-color: orangered;
  padding: 5px;
}
.my-grid-item {
  background-color: rgba(255, 255, 255, 0.8);
  border: 2px solid orangered;
  padding: 10px;
  font-size: 12px;
  text-align: center;
}
</style>

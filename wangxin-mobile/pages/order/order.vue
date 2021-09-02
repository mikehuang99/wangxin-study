<template>
  <div>
    <div class="order_a" id="order_a">
    <top-header :headerName="this.headerName" :headerNameClass="this.headerNameClass"></top-header>
    <div>
      <div class="course_des">
          <div class="course_des_left">
            <img :src="courseDetail.courseLogo" height="70%" width="100%">
          </div>
          <div class="course_des_right">
            <div style="margin-left: 10px">{{courseDetail.courseName}}</div>
            <div style="margin-left: 10px;font-size:16px;text-decoration:line-through">
              原价{{courseDetail.courseOriginal}}元{{courseDetail.costTimeType | costTimeTypeChange}}
            </div>
            <div style="margin-left: 10px;font-size:18px;font-weight: bold">
              折扣价{{courseDetail.courseDiscount}}元{{courseDetail.costTimeType | costTimeTypeChange}}
            </div>
          </div>
      </div>
      <div class="deduction_area">
        <div style="margin-left: 10px">学习卡和优惠券不能同时使用</div>
        <div class="study_card_contain">
          <div class="study_card_contain_left_img">
          </div>
          <div class="study_card_contain_content" @click="openOrderStudyCard">
            <span  v-bind:class="{forbidden: couponCanUseValueAmount!=0}">使用学习卡</span>
            <span v-if="studyCardCanUseValueAmount!==0" style="color: orangered">[使用{{studyCardCanUseValueAmount}}元]</span>
          </div>
        </div>
          <div class="coupon_contain">
              <div class="coupon_contain_left_img">
              </div>
              <div class="coupon_contain_content" @click="openOrderCoupon">
                <span v-bind:class="{forbidden: studyCardCanUseValueAmount!=0}">使用优惠券</span>
                <span v-if="couponCanUseValueAmount!==0" style="color: orangered">[使用{{couponCanUseValueAmount}}元]</span>
              </div>
          </div>
      </div>
      <div class="pay_method_contain">
        <div class="alipay_contain" @click="selectPayMethod(1)" v-if="isOpenZfbMobile==1">
          <div v-bind:class="{alipay_contain_left_img_green: alipay,alipay_contain_left_img_grey: !alipay}">
          </div>
          <div class="alipay_contain_content">
            <span v-bind:class="{forbidden: weixinpay}">支付宝支付</span>
          </div>
        </div>
        <div class="weixinpay_contain" @click="selectPayMethod(2)" v-if="isOpenWeixinpayMobile==1">
          <div v-bind:class="{weixinpay_contain_left_img_green: weixinpay,weixinpay_contain_left_img_grey: !weixinpay}">
          </div>
          <div class="weixinpay_contain_content">
            <span v-bind:class="{forbidden: alipay}">微信支付</span>
          </div>
        </div>
      </div>
      <div class="price_area">
        <div class="price_area_actual_price">
          需付<span>{{this.order.shouldPay}}</span>元
        </div>
      </div>
      <div class="pay_button" @click="saveOrderMain" v-if="!(isWeixinBrowser&&alipay)">
        <div class="btn_box">{{payDesc}}</div>
      </div>
      <div class="pay_button" style="margin-left: 0px;" @click="copyUrl" v-if="isWeixinBrowser&&alipay">
        <div class="btn_box" style="width: 100%">点击这里复制网址粘贴到浏览器下单</div>
      </div>
    </div>
    </div>
    <!--
    <div style="height: 70px">
      <Footer></Footer>
    </div>-->
    <order-study-card v-if="orderStudyCard" :courseId="courseDetail.id" :selectedStudyCardId2="selectedStudyCardId2" @closePopupOrderStudyCard="closePopupOrderStudyCard"></order-study-card>
    <order-coupon v-if="orderCoupon" :courseId="courseDetail.id" :selectedCouponId2="selectedCouponId2" @closePopupOrderCoupon="closePopupOrderCoupon"></order-coupon>
    <alert :alertTitle="alertTitle" :alertContent="alertContent"
           :timeOutFlag="timeOutFlag" :toUrl="toUrl" :toUrlParams="toUrlParams" @closeAlert="closeAlert()" v-if="isShowAlert"></alert>
  </div>
</template>

<script>
    import topHeader from '~/components/common/header.vue'
    import orderStudyCard  from "./orderStudyCard"
    import orderCoupon  from "./orderCoupon";
    import VueCookie from 'vue-cookie'
    import {findCourseDetailHadLogin} from '~/api/index'
    import {saveOrder,checkPayResult} from '~/api/order'
    import {viewSystemConfig} from '~/api/system'
    import { logout,setStore } from '~/store/common'
    import Alert from "~/components/common/alert";
    import Footer from '~/components/index/footer/Footer.vue'


    export default {
        name: "index",
        data() {
            return {
                isShowAlert: false,
                timeOutFlag: false,
                toUrl: '',
                alertTitle: '',
                alertContent: '',
                toUrlParams: [],

                headerName: '订单详情',
                headerNameClass: 1,
                courseDetail: '',
                orderStudyCard: false,
                orderCoupon: false,
                order: {
                    courseId: 0,
                    payType: 0,
                    shouldPay: 0,
                    remark: '',
                    cardId: '',//如果cardType是学习卡，就是学习卡ID，如果cardType是优惠券，就是优惠券ID
                    cardType: '',//选择学习卡为1，选择优惠券为2
                    orderNo: ''
                },
                selectedStudyCardId: '',
                selectedStudyCardId2: '',
                studyCardCanUseValueAmount:0,
                selectedCouponId: '',
                selectedCouponId2: '',
                couponCanUseValueAmount:0,

                studyCardCanUse: true,
                couponCanUse: true,
                alipay: false,
                weixinpay: false,

                payDesc: '立刻支付',
                onPayDescCount: 1000,
                onPaying: false,

                isOpenZfbMobile: 0,
                isOpenWeixinpayMobile: 0,
                isWeixinBrowser: false

            }
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
        mounted () {
            /*
            setTimeout(() => {
                document.getElementById("order_a").style.height = (this.findClientHeight()-70)+"px";
            }, 200)*/
            if(/MicroMessenger/i.test(navigator.userAgent)){
                this.isWeixinBrowser = true;
            }
            let orderNo = this.$route.query.out_trade_no;
            if(orderNo){
                this.onPayDesc();
                this.getOrderInfo(orderNo);
                return;
            }

            let id= this.$route.query.id
            console.log("id:::==>"+id);
            this.findCourseDetail();
            this.getSystemConfig();
        },
        methods: {
            copyUrl(){
                let input = document.createElement("input");   // 直接构建input
                input.value = window.location.href;  // 设置内容
                document.body.appendChild(input);    // 添加临时实例
                input.select();   // 选择实例内容
                document.execCommand("Copy");   // 执行复制
                document.body.removeChild(input); // 删除临时实例
                this.isShowAlert = true;
                this.alertTitle = "提示";
                this.alertContent = "复制成功。请打开浏览器，粘贴网址打开下单";
            },
            getSystemConfig () {
                viewSystemConfig({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        // console.log("====>"+res.data)
                        this.isOpenZfbMobile = res.data.isOpenZfbMobile;
                        this.isOpenWeixinpayMobile = res.data.isOpenWeixinpayMobile;
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
            onPayDesc(){
                let that = this;
                if(that.onPayDescCount<0){
                    that.onPayDescCount = 1000;
                }
                let c = that.onPayDescCount%3;
                switch (c) {
                    case 0:
                        that.payDesc = "正在支付.";
                        break;
                    case 1:
                        that.payDesc = "正在支付..";
                        break;
                    case 2:
                        that.payDesc = "正在支付...";
                        break;
                    default:
                        that.payDesc = "正在支付";
                        break;
                }
                setTimeout(function () {
                    //if(this.onPaying) {
                    that.onPayDescCount = that.onPayDescCount -1;
                        that.onPayDesc();
                        /*
                    }else{
                        this.payDesc = "立刻支付";
                    }*/
                }, 200);
            },
            getOrderInfo (no) {
                let that = this;
                checkPayResult({orderNo: no}).then(res => {
                    res = res.data
                    console.log(res)
                    if (res.data.payStatus === 0) {
                        setTimeout(function () {
                            that.getOrderInfo(no);
                        }, 2000);
                    } else if (res.data.payStatus === 1) {
                        //购买成功
                        let courseId = res.data.courseId;

                        this.isShowAlert = true;
                        this.alertTitle = "提示";
                        this.alertContent = "购买成功，即将跳转...请稍后";
                        this.timeOutFlag = true;
                        this.toUrl = "/courseDetail/index";
                        this.toUrlParams = {"id":courseId,"timeStamp":new Date().getTime()}


                        //this.$router.push({path: '/courseDetail/index', query: {"id":courseId}})
                        return;
                    } else {
                        //未完成付款

                        return;
                    }
                })
            },
            closeAlert(){
                this.isShowAlert = false;
            },
            saveOrderMain(){
                if(this.onPaying){
                    return;
                }
                if(this.order.shouldPay>0&&this.order.payType!==1&&this.order.payType!==2){
                    this.isShowAlert = true;
                    this.alertTitle = "提示";
                    this.alertContent = "请选择支付方式";
                    return;
                }
                this.onPaying = true;
                this.onPayDesc();
                saveOrder(this.order).then(res => {
                    res = res.data;
                    if (res.code === 200 ) {
                        if(this.order.shouldPay==0){
                            let courseId = this.order.courseId;

                            this.isShowAlert = true;
                            this.alertTitle = "提示";
                            this.alertContent = "购买成功，即将跳转...请稍后";
                            this.timeOutFlag = true;
                            //this.toUrl = "/courseDetail/index?id="+courseId;
                            this.toUrl = "/courseDetail/index";
                            this.toUrlParams = {"id":courseId,"timeStamp":new Date().getTime()}
                            return;
                        }
                        if(this.order.payType==1){
                            if(!res.data.alipayHtmlForm){
                                console.log("请求支付宝失败");
                                this.isShowAlert = true;
                                this.alertTitle = "提示";
                                this.alertContent = "请求支付宝失败";
                                return;
                            }else{
                                /*
                                //返回参数 
                                let routerData = this.$router.resolve({path:'/order/alipayForm',query:{name:res.data.alipayHtmlForm}});
                                //打开新页面
                                window.open(routerData.href,'_ blank');*/

                                this.$router.push({path:'/order/alipayForm',query:{name:res.data.alipayHtmlForm}});

                            }
                        }

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
                }).catch(error => {
                    //reject(error)
                    console.log(error);
                })

                if(1==1){
                    return;
                }
                /*
                 let htmls = "<form name=\"punchout_form\" method=\"post\" action=\"https://openapi.alipay.com/gateway.do?charset=UTF-8&method=alipay.trade.wap.pay&sign=J3rx9Z5DIu4TR6Q8fK77K8t%2FRrusb%2FHg752OhMg6jgm4WAuXX%2FrbH48rdwr6xjgZh5K8qQzt0t9zoryihiUXZ6hIDBTxxBzHflIBzeOrsr5QdvOhOXRSj7tco7ycoX0SsWmvscFKblLZPTlXqaffOgDnT1XpN3ilHqnjsjT6nEsBM9mywQQPdxxnlpAZ%2B7n7tUIpiauZ%2FRGaVhj%2B%2BgKYgwCEiCjZG8QSih9%2F14UHH%2FcNNZFgCkMgbq%2Bhl2cEuHuVF8j7zxJdzbL9VsdOYl7ZEYmyBlAfHTTwXbyim3%2B3GqI9KQ%2BUBFUyLaNr1NBxrYtrFbOeFcEF9Cdvs952Td1bRg%3D%3D&return_url=http%3A%2F%2F127.0.0.1%3A8080%2Falipay.trade.wap.pay-JAVA-UTF-8%2Freturn_url.jsp&notify_url=http%3A%2F%2F127.0.0.1%3A8080%2Falipay.trade.wap.pay-JAVA-UTF-8%2Fnotify_url.jsp&version=1.0&app_id=2021001184681274&sign_type=RSA2&timestamp=2021-03-11+13%3A33%3A01&alipay_sdk=alipay-sdk-java-3.3.0&format=json\">\n" +
                    "<input type=\"hidden\" name=\"biz_content\" value=\"{&quot;body&quot;:&quot;购买测试商品0.01元&quot;,&quot;out_trade_no&quot;:&quot;2021311132423152&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_WAY&quot;,&quot;subject&quot;:&quot;手机网站支付测试商品&quot;,&quot;timeout_express&quot;:&quot;2m&quot;,&quot;total_amount&quot;:&quot;0.01&quot;}\">\n" +
                    "<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n" +
                    "</form>\n" +
                    "<script>document.forms[0].submit();<\/script>";
                //返回参数 
                let routerData = this.$router.resolve({path:'/order/alipayForm',query:{name:htmls}});
                //打开新页面
                window.open(routerData.href,'_ blank');*/
                /*
                const div = document.createElement('div');
                div.innerHTML = this.htmls;
                document.body.appendChild(div);
                document.forms[0].submit();*/
            },
            selectPayMethod(payMethod){
                this.order.payType = payMethod;
                if(payMethod==1){
                    this.alipay = true;
                    this.weixinpay = false;
                }
                if(payMethod==2){
                    this.alipay = false;
                    this.weixinpay = true;
                }
            },
            toFixed22(value){
                if(typeof(value)=='undefined'){
                    this.isShowAlert = true;
                    this.alertTitle = "提示";
                    this.alertContent = "页面正在加载，请稍后再点击购买";
                    return;
                }
                return Number(value.toFixed(2));
            },
            selectCoupon(item,cardType){
                if(item.id==this.selectedCouponId){
                    this.couponCanUseValueAmount = 0;
                    this.selectedCouponId = '';
                    this.order.cardId = '';
                    this.order.cardType = 0;
                    this.order.shouldPay = this.courseDetail.courseDiscount.toFixed(2);
                    return;
                }
                let couponAmount = item.couponAmount;
                if(couponAmount>0){
                    if(couponAmount>=this.courseDetail.courseDiscount.toFixed(2)){
                        this.order.shouldPay = 0;
                    }else{
                        this.order.shouldPay = this.courseDetail.courseDiscount.toFixed(2)-couponAmount;
                    }
                    this.couponCanUseValueAmount = couponAmount;
                }
                this.order.shouldPay = this.order.shouldPay.toFixed(2);
                this.selectedCouponId = item.id;

                this.order.cardType = cardType;
                this.order.cardId = item.couponId;

            },
            selectStudyCard(item,cardType){
                if(item.id==this.selectedStudyCardId){
                    this.studyCardCanUseValueAmount = 0;
                    this.selectedStudyCardId = '';
                    this.order.cardId = '';
                    this.order.cardType = 0;
                    this.order.shouldPay = this.courseDetail.courseDiscount.toFixed(2);
                    return;
                }
                console.log("item.totalFaceValueAmount:"+item.totalFaceValueAmount);
                console.log("item.usedFaceValueAmount:"+item.usedFaceValueAmount);
                console.log("item.frozenFaceValueAmount:"+item.frozenFaceValueAmount);

                let canUseValueAmount = item.totalFaceValueAmount-item.usedFaceValueAmount-item.frozenFaceValueAmount;
                if(canUseValueAmount>0){
                    if(canUseValueAmount>=this.courseDetail.courseDiscount.toFixed(2)){
                        this.order.shouldPay = 0;
                    }else{
                        this.order.shouldPay = this.courseDetail.courseDiscount.toFixed(2)-canUseValueAmount.toFixed(2);
                    }
                    this.studyCardCanUseValueAmount = canUseValueAmount;
                }
                this.order.shouldPay = this.order.shouldPay.toFixed(2);
                this.selectedStudyCardId = item.id;

                this.order.cardType = cardType;
                this.order.cardId = item.id;
            },
            closePopupOrderStudyCard(){
                this.selectedStudyCardId2 = this.selectedStudyCardId;
               this.orderStudyCard =false;
            },
            closePopupOrderCoupon(){
                this.selectedCouponId2 = this.selectedCouponId;
                this.orderCoupon = false;
            },
            openOrderStudyCard(){
                if(this.couponCanUseValueAmount!=0){
                    return;
                }
              this.orderStudyCard =true;
            },
            openOrderCoupon(){
                if(this.studyCardCanUseValueAmount!=0){
                    return;
                }
                this.orderCoupon =true;
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
                            this.order.courseId = this.courseDetail.id;
                            this.order.shouldPay = this.toFixed22(this.courseDetail.courseDiscount);
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
                    }).catch(error => {
                        //reject(error)
                        console.log(error);
                    })
                }else{
                    logout();
                    setStore("lastUrlTemp",window.location.href);
                    this.$router.push({path: '/login', query: {}});
                }
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
        components: {
            topHeader,
            orderStudyCard,
            orderCoupon,
            Alert,
            Footer
        }
    }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  /*
  .order_a{
    overflow-y:auto;
  }*/
  .course_des{
    width: 100%;
    height: 100px;
    border: solid 1px red;
    margin-top: 44px;
    margin-bottom: 10px
  }
  .course_des_left{
    float:left;
    height: 100px;
    width: 30%;
    border: solid 1px red;
  }
  .course_des_left img{
    border-radius: 8px;
    margin-top: 15px;
  }
  .course_des_right{
    float:right;
    height: 100px;
    width: 70%;
    border: solid 1px red;
  }
  .deduction_area{
    height: 120px;
    border: solid 1px red;
  }
  .study_card_contain{
    width: 100%;
    height: 50px;
    border: solid 1px red;
  }
  .study_card_contain_left_img{
    height:30px;
    width:30px;
    display:inline-block;
    margin-top: 10px;
    background: url('/wxmobile/img/wxImg/study_card.png')no-repeat center center;
  }
  .study_card_contain_content{
    display:inline-block;
    height: 30px;
    margin-top: -10px;
    margin-left:0px;
    font-weight: bold;
    font-size: 18px;
  }
  .study_card_contain_content span{
    position:relative;
    top: -10px;
  }
  .coupon_contain{
    width: 100%;height: 50px;border: solid 1px red;
  }
  .coupon_contain_left_img{
    height:30px;width:30px;display:inline-block;margin-top: 10px;background: url('/wxmobile/img/wxImg/coupon.png')no-repeat center center
  }
  .coupon_contain_content{
    display:inline-block;height: 30px;margin-top: -10px;margin-left:0px;font-weight: bold;font-size: 18px;
  }
  .coupon_contain_content span{
    position:relative;top: -10px
  }
  .pay_method_contain{
    height: 100px;border: solid 0px red;margin-top: 20px
  }
  .alipay_contain{
    width: 100%;height: 50px;border: solid 0px red;
  }
  .alipay_contain_left_img_green{
    height:30px;width:30px;display:inline-block;margin-top: 10px;background: url('/wxmobile/img/wxImg/green_hook.png')no-repeat center center;
  }
  .alipay_contain_left_img_grey{
    height:30px;width:30px;display:inline-block;margin-top: 10px;background: url('/wxmobile/img/wxImg/grey_hook.png')no-repeat center center;
  }
  .alipay_contain_content{
    display:inline-block;height: 30px;margin-top: -10px;margin-left:0px;font-weight: bold;font-size: 18px;
  }
  .alipay_contain_content span{
    position:relative;top: -10px
  }
  .weixinpay_contain{
    width: 100%;height: 50px;border: solid 0px red;
  }
  .weixinpay_contain_left_img_green{
    height:30px;width:30px;display:inline-block;margin-top: 10px;background: url('/wxmobile/img/wxImg/green_hook.png')no-repeat center center
  }
  .weixinpay_contain_left_img_grey{
    height:30px;width:30px;display:inline-block;margin-top: 10px;background: url('/wxmobile/img/wxImg/grey_hook.png')no-repeat center center
  }
  .weixinpay_contain_content{
    display:inline-block;height: 30px;margin-top: -10px;margin-left:0px;font-weight: bold;font-size: 18px;
  }
  .weixinpay_contain_content span{
    position:relative;top: -10px
  }
  .price_area{
    margin-top: 10px;margin-left: 20px
  }
  .price_area_original_price{
    text-decoration: line-through;color: orange;font-size: 18px
  }
  .price_area_actual_price{
    color: red;font-size: 18px;font-weight: bold
  }
  .price_area_actual_price span{
    font-size: 20px;font-weight: bold
  }
  .pay_button{
    margin-top: 30px;margin-right: 20px;float: right;
  }
  .forbidden{
    color: lightgrey;
  }
  .btn_box {
    width: 150px;
    border-radius: 5px;
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
</style>

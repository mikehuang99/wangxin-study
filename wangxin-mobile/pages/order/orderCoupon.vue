<template>
  <div class="popup_order_coupon_contain">
    <div class="popup_order_coupon_content">
      <div class="popup_order_coupon_title">优惠券</div>
      <div v-for="(item, indexCoupon) in couponList" v-bind:key="indexCoupon" @click="selectCoupon(item,2)">
        <div class="popup_order_coupon_item">
          {{item.couponName | cutTile}}<br/>
          优惠金额:<span style="font-weight: bold;color: red">{{item.couponAmount}}元</span><span v-if="item.isLimitCourse==0">[不限制课程]</span><span v-if="item.isLimitCourse==1">[部分课程适用]</span><br/>
          [有效期:{{item.couponTimeBegin}}至{{item.couponTimeEnd}}]
          <span class="popup_order_coupon_item_had_selected" v-if="item.id==selectedCouponId" style="position: relative;float:right;border:1px solid orangered;background-color: orangered;color: white;">已选择√</span>
        </div>
        <div style="height: 10px">

        </div>
      </div>
      <div v-if="couponList.length==0">
        <div style="margin-top: 50px;"></div>
        <div style="font-size: 20px">没有优惠券</div>
      </div>
    </div>
    <div style="margin-top: 20px">
      <span class="btn_box_ok" @click="closePopupOrderCouponOk">关闭</span>
      <!--
      <span class="btn_box_cancel" @click="closePopupOrderCouponCancel">取消</span>-->
    </div>
  </div>
</template>

<script>
    import {listUserCoupon} from '~/api/market'
    import { logout } from '~/store/common'

    export default {
        props: {
            courseId: {
                type: [Object, String],
                default: ''
            },
            selectedCouponId2: {
                type: [Object, String],
                default: ''
            }
        },
        name: "orderCoupon",
        data () {
            return {
                couponList: [],
                couponSize: '',
                selectedCouponId: this.selectedCouponId2
            }
        },
        filters: {
            cutTile(value) {
                if (!value) return ''
                if (value.length > 14) {
                    return value.slice(0, 14) + '...'
                }
                return value
            },
            toFixed2(value){
                if(typeof(value)=='undefined'){
                    return '数据错误，请重新尝试';
                }
                return Number(value.toFixed(2));
            }
        },
        mounted(){
            this.listUserCoupon();
        },
        methods: {
            selectCoupon(item,cardType){
                if(this.selectedCouponId==item.id){
                    this.selectedCouponId = '';
                }else {
                    this.selectedCouponId = item.id;
                }
                this.$parent.selectCoupon(item,cardType);
            },
            listUserCoupon(){
                listUserCoupon({"courseId":this.courseId}).then(res => {
                    res = res.data;
                    if (res.code === 200 ) {
                        //this.courseDetail = res.data;
                        this.couponList = res.data.list || [];
                        this.couponSize = this.couponList.length;
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
            },
            closePopupOrderCouponOk(){
                    this.$emit('closePopupOrderCoupon');
            },
            closePopupOrderCouponCancel(){
                this.$emit('closePopupOrderCoupon');
            }
        }
    }
</script>

<style scoped>
  .popup_order_coupon_contain{
    border-radius: 20px;
    background-color: lightgrey;
    width: 100%;
    height: 400px;
    position: fixed;
    left: 0px;
    top: 10%;
    overflow-y: auto;
    padding-left: 17px;
    padding-right: 17px;
    z-index: 9999999999;
  }
  .popup_order_coupon_content{
    margin-top: 20px;
    text-align: center;
    height: 300px;
    overflow-y: auto;
    border: solid 1px orangered;
    border-radius: 8px;
  }
  .popup_order_coupon_title{
    font-weight: bold;font-size: 18px;
  }
  .popup_order_coupon_item{
    border: solid 1px red;
    border-radius:5px;
    background-color:greenyellow;
    padding-bottom: 20px;
  }
  .btn_box_ok {
    border-radius: 20px;
    width: 100px;
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
    float: left;
  }
  .btn_box_cancel {
    border-radius: 20px;
    width: 100px;
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
    float: right;
  }
</style>

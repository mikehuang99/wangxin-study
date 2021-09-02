<template>
  <div class="">
    <div class="mask" @click="close()"></div>
    <div class="paymodal codemodal" v-if="payStep === 1">
      <div class="modal_head">
        <span class="fl">收银台</span>
        <a href="javascript:" @click="close()" class="close iconfont">关闭</a>
      </div>
      <div class="modal_body">
        <div class="tip" v-if="order.payType === 1">支付宝支付 {{this.order.shouldPay}}元</div>
        <div class="tip" v-else>微信支付 {{this.order.shouldPay}}元</div>
        <div class="code" id="qrcode">
          <canvas id="canvas222"></canvas>
        </div>
        <p class="tip2" v-if="order.payType === 2">请使用支付宝扫描二维码支付以完成订单</p>
        <p class="tip2" v-else>请使用微信扫描二维码支付以完成订单</p>
        <div class="finish_pay" @click="getOrderInfoSelf">完成支付如果页面没有跳转请点击此处</div>
        <div class="tip_panel">
          提示: <br>
          支付成功前请不要手动关闭页面<br>
          二维码两个小时内有效,请及时扫码支付，否则订单将被取消。<span style="color: red">最后支付时间{{order.lastDateForPayment}}</span>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
    import {orderMainSave,checkPayResult} from '~/api/order.js'
    import  {continuePay} from '~/api/account/course.js'
    import VueCookie from 'vue-cookie'


    import QRCode from 'qrcode'
    export default {
        props: {
            data: {
                type: [Object, Array],
                default: null
            },
            orderNo: {
                type: String,
                default: ''
            },
            isCourse: {
                type: Boolean
            }
        },
        data () {
            return {
                studyCardList: [],
                couponList: [],
                studyCardSize: '',
                couponSize: '',
                isVip: false,
                btntext: '下一步',
                checkPay: false,
                userInfo: [],
                courseData: '',
                order: {
                    courseId: 0,
                    payType: 0,
                    shouldPay: 0,
                    remark: '',
                    cardId: '',//如果cardType是学习卡，就是学习卡ID，如果cardType是优惠券，就是优惠券ID
                    cardType: '',//选择学习卡为1，选择优惠券为2
                    orderNo: '',
                    lastDateForPayment: '' //最后支付期限
                },
                orderInfo: {
                    payType: 1
                },
                payStep: 1,
                player: null,
                selectedId: '',
                isOpenZfbPc: 0,
                isOpenWeixinpayPc: 0

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
            },
            toFixed2(value){
                if(typeof(value)=='undefined'){
                    return '数据错误，请重新尝试';
                }
                return Number(value.toFixed(2));
            }
        },

        mounted () {
            this.userInfo = JSON.parse(VueCookie.get("wangxinUserInfo"));
            // this.qrcode('weixin://wxpay/bizpayurl?pr=GlkCzqF');
            /*
            this.order.courseId = this.data.course.id;
            this.courseData = this.data.course;
            console.log(this.courseData)
            this.getStudyCardList();
            this.getCouponUserList();
            this.getSystemConfig();

            this.order.shouldPay = this.toFixed22(this.courseData.courseDiscount);
            */

            this.continuePay();
            this.checkPayResult();
        },
        methods: {
            toFixed22(value){
                if(typeof(value)=='undefined'){
                    this.$msgBox({
                        content: '请等待页面数据加载完整再点击购买按钮',
                        isShowCancelBtn: false
                    }).then(() => {

                    }).catch(() => {
                    })
                    return;
                }
                return Number(value.toFixed(2));
            },
            setPayType(payType){
                this.order.payType = payType;
            },

            //是否过期
            isOutDate(endDate){
                let endDateTime = new Date(endDate).getTime();
                let nowTime = new Date().getTime();
                if(nowTime>endDateTime){
                    return true;
                }else{
                    return false;
                }
            },


            reload () {
                window.location.reload();
            },
            close (event) {
                this.checkPay = true;
                this.$emit('hidefun', event);
            },
            sel (cno) {
                this.checkPay = true;
                this.$emit('btnClick', cno)
            },
            qrcode2 (url) {
                console.log("ddd-------");
                let canvas222 = document.getElementById('canvas222');
                QRCode.toCanvas(canvas222, url, {
                    width: 180,
                    height: 180
                }, function (error) {
                    if (error) {
                        console.error(error);
                    }else {
                        console.log('success!')
                    }
                })
            },
            startView () {

            },
            submitOrderWarn(){
                console.log("支付类型："+this.orderInfo.payType);
                this.$msgBox({
                    content: "确定提交订单吗？",
                    isShowCancelBtn: true
                }).then(() => {
                    this.submit();
                }).catch(() => {})
            },
            continuePay () {
                let that = this;
                console.log("订单内容："+JSON.stringify(this.order));
                /*
                if(parseFloat(that.order.shouldPay) > 0){
                    if(that.order.payType === 0){
                        this.$msgBox({
                            content: '必须选择支付方式',
                            isShowCancelBtn: false
                        })
                        return;
                    }
                }*/

                this.payStep = 1;
                // if(1===1) {
                //     setTimeout(function () {
                //        that.qrcode2('66777');
                //   }, 1000);
                //   return;
                // }
                that.btntext = '正在提交...';

                console.log("orderNo:"+that.orderNo);

                continuePay({'orderNo':that.orderNo}).then(res => {
                    res = res.data;
                    console.log(res)
                    that.btntext = '下一步';
                    if (res.code === 200) {
                        setTimeout(function () {
                            that.qrcode2(res.data.qrCode);
                        }, 200);
                        this.order.shouldPay = res.data.payAmount;
                        this.order.payType = res.data.payType;
                        //this.order.orderNo = res.data.orderNo;
                        this.order.lastDateForPayment = res.data.lastDateForPayment;
                        that.checkPayResult(res.data.orderNo);
                    } else if (res.code >= 300 && res.code < 400) {
                        this.$msgBox({
                            content: res.msg,
                            isShowCancelBtn: false
                        }).then(() => {
                            this.$store.dispatch('REDIRECT_LOGIN');
                        }).catch(() => {})
                    } else {
                        this.$msgBox({
                            content: res.msg,
                            isShowCancelBtn: false
                        }).then(() => {
                        }).catch(() => {});
                        this.close();

                    }
                }).catch(() => {
                    this.$msgBox({
                        content: '提交失败,请重试',
                        isShowCancelBtn: false
                    })
                    that.btntext = '重新提交';
                })
            },
            checkPayResult () {
                let that = this;
                if (this.checkPay) {
                    return false;
                }
                checkPayResult({orderNo: this.orderNo}).then(res => {
                    res = res.data
                    console.log(res)
                    if (res.data.payStatus === 0) {
                        setTimeout(function () {
                            that.checkPayResult(this.orderNo);
                        }, 2000);
                    } else if (res.data.payStatus === 1) {
                        this.close();
                        this.$msgBox({
                            content: '购买成功',
                            isShowCancelBtn: false
                        }).then(res => {
                            window.location.reload();
                        }).catch(() => {
                            window.location.reload();
                        })
                    } else {
                        that.payStep = 2;
                    }
                })
            },
            getOrderInfoSelf () {//手动点击完成支付
                let that = this;

                if (this.checkPay) {
                    return false;
                }
                checkPayResult({orderNo: that.orderNo}).then(res => {
                    res = res.data
                    console.log(res)
                    if (res.data.payStatus === 0) {
                        this.$msgBox({
                            content: '尚未完成支付',
                            isShowCancelBtn: false
                        }).then(res => {
                        }).catch(() => {
                        })
                        return;
                    } else if (res.data.payStatus === 1) {
                        this.close();
                        this.$msgBox({
                            content: '购买成功',
                            isShowCancelBtn: false
                        }).then(res => {
                            window.location.reload();
                        }).catch(() => {
                            window.location.reload();
                        })
                    } else {
                        that.payStep = 2;
                    }
                })
            }
        }
    }
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .payrun{
    min-height: 180px;
    .modal_body{
      padding: 30px;
      //height: 180px;
    }
    .ewm_img {
      width: 130px;
      display: block;
      margin: 10px auto 0;
      text-align: center;
      img {
        width: 130px;
        height: 130px;
      }
      p {
        line-height: 20px;
      }
    }
    .icon{
      font-size: 80px;
      width: 80px;
      margin: 0 auto;
      text-align: center;
      &.minIcon {
        font-size: 26px;
        width: 160px;
        span {
          position: relative;
          top: -5px;
          padding-left: 6px;
        }
      }
    }
    .order_btn {
      background: #fff;
      border: 1px solid rgb(180, 180, 180);
      color: #999;
      text-decoration: none;
      margin: 30px 5px;
    }
    .tip{
      font-size: 16px;
      text-align: center;
      padding-bottom: 20px;
    }
    .c_green{
      color: #05d005;
    }
  }
  .codemodal{
    .tip{
      margin: 30px auto 0;
      text-align: center;
      font-size: 16px;
    }
    .code{
      width: 182px;
      height: 182px;
      border: 1px solid #ddd;
      margin: 20px auto;
    }
    .tip2{
      margin: 0 auto;
      width: 380px;
      padding: 13px 0 13px 70px;
      height: 40px;
      line-height: 20px;
      font-size: 14px;
      color: black;
      background-color: lightskyblue;
    }
    .finish_pay{
      margin: 10px auto;
      width: 380px;
      height: 20px;
      line-height: 20px;
      font-size: 14px;
      color: black;
      text-align: center;
      cursor:pointer;
      text-decoration:underline;
      background-color: greenyellow;
    }
    .tip_panel{
      margin-top: 20px;
      padding: 20px;
      line-height: 20px;
      border-top: 1px solid #f1f1f1;
    }
  }
  .paymodal{
    position: fixed;
    left: 50%;
    top: 10%;
    z-index: 999;
    width: 720px;
    margin-left: -240px;
    background-color: #fff;
    border-radius: 0px;
    height: 500px;
    overflow: scroll;
    .modal_head{
      line-height: 46px;
      background-color: #f9f9f9;
      text-align: right;
      padding: 0 20px;
      .close{
        font-size: 14px;
      }
    }
    .modal_foot{
      border-top: 1px solid #e7e7eb;
      padding: 20px;
      text-align: center;
    }
    .table{
      width: 100%;
    }
    th{
      line-height: 36px;
      background-color: #f8f8f8;
    }
    td{
      vertical-align: middle;
      padding: 20px;
    }
    .name{
      width: 140px;
      padding-left: 12px;
      font-size: 16px;
    }
    .img{
      display: inline-block;
      width: 120px;
      padding: 0;
    }
    .name{
      display: inline-block;
      vertical-align: top;
    }
    .tabs {
      height: 48px;
      .tab{
        float: left;
        line-height: 46px;
        text-align: center;
        font-size: 16px;
        width: 50%;
      }
      span{
        cursor: default;
        display: inline-block;
        line-height: 46px;
      }
      .on{
        color: #ee7e00;
        span{
          border-bottom: 2px solid #ee7e00;
        }
      }
    }
    .remark{
      padding: 20px;
      overflow: hidden;
      font-size: 14px;
      border-top: 1px solid #f1f1f1;
      input{
        text-indent: .5em;
        height: 22px;
        width: 350px;
        border: none;
      }
    }
    .should_pay{
      padding: 20px;
      overflow: hidden;
      border-top: 1px solid #f1f1f1;
    }
    .pay_type{
      padding: 20px;
      overflow: hidden;
      border-top: 1px solid #f1f1f1;
      input{
        display: none;
        &:checked + .pay{
          border-color: blue;
        }
      }
    }
    .coupon_type{//优惠券
      padding: 20px;
      overflow: hidden;
      border-top: 1px solid #f1f1f1;
      input {
        display: none;
      }
      /*
      input{
        display: none;
        &:checked + .coupon_detail{
          border-color: blue;
        }
      }*/
    }
    .coupon_detail{
      width: 320px;
      height: 93px;
      border: 5px solid #fff;
      font-weight: bold;
      float: left;
      line-height: 20px;
      text-align: center;
      background-repeat: no-repeat;
      background-position: center center;
      background-color: lightskyblue;
    }
    .study_card_type{//学习卡
      padding: 20px;
      overflow: hidden;
      border-top: 1px solid #f1f1f1;
      input{
        display: none;
        /*
        &:checked + .study_card_detail{
          border-color: blue;
        }*/
      }
    }
    .study_card_detail{
      width: 320px;
      height: 93px;
      border: 5px solid #fff;
      font-weight: bold;
      float: left;
      line-height: 20px;
      text-align: center;
      background-repeat: no-repeat;
      background-position: center center;
      background-color: lightblue;
    }
    .pay{
      width: 218px;
      height: 50px;
      border: 1px solid #fff;
      float: left;
      background-repeat: no-repeat;
      background-position: center center;
      &.pay_ali {
        background-image: url(data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABGAAD/4QMsaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzE0MCA3OS4xNjA0NTEsIDIwMTcvMDUvMDYtMDE6MDg6MjEgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDQyAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDozMjlEQTRGOTRBODgxMUU4OEJBREIyOUE3Mjg5MjBCRCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDozMjlEQTRGQTRBODgxMUU4OEJBREIyOUE3Mjg5MjBCRCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjMyOURBNEY3NEE4ODExRTg4QkFEQjI5QTcyODkyMEJEIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjMyOURBNEY4NEE4ODExRTg4QkFEQjI5QTcyODkyMEJEIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+/+4ADkFkb2JlAGTAAAAAAf/bAIQABAMDAwMDBAMDBAYEAwQGBwUEBAUHCAYGBwYGCAoICQkJCQgKCgwMDAwMCgwMDQ0MDBERERERFBQUFBQUFBQUFAEEBQUIBwgPCgoPFA4ODhQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQUFBQU/8AAEQgAKQCWAwERAAIRAQMRAf/EAKkAAAICAwEBAAAAAAAAAAAAAAAHAQYCBQgEAwEBAQADAQEBAAAAAAAAAAAAAAECBgcEBQgQAAAFAwIDBQUECAcAAAAAAAECAwQFABEGEgchMRNBIjIUCFFxgRUX0SPTJPBhwUJSglUWobGyQ2M0hBEAAQMCAwMIBgkFAAAAAAAAAAECAxEEITEFQRIGUXGBkaHRIqKxwtITUxZhwTJCUmKCkjPhciPTB//aAAwDAQACEQMRAD8A7+oAoDA3OgU5byjfrMnUs5/t10nGw6Shk2oFRSWVUIUbAooZYpuJrXAClCwD8a6lZcM2zIk96ivfTHFUROalMssanDdT4zvHzOS3cjGIuHhRapyrvIvPsNP9bdzrgATYXEbB+Uacfh0q9q8PWGfu/M72j5ScW6suHvfJH7BspXcze2C6Jpty4jiuL+XFywbJ6xLz03R42uFeaDSNKnwiRHUzo9y59J77rXdetURZXOYjsqxsT1DW/W7c7+tgPt/KNPwq9Xy7YfD8zu88HzdqvxfJH7A8dn9x32eRzxGXImSZjDEBc6ICVNVJYDaD6RvpNcogYL/r7bVoeu6S2ykarF8DuXZTZ3HVOGNddqUTkkRPeMzptRcl7C9u8ggo9YWshKNGrkAAworuEkj2NyHSYwDYbVrZuYrC+o3ETbu/SUGq3W0if+4xXa/K+DTzdtfV1cQ7nLxVC0UabPIIORW8tHyjR250ibooLpKn0l5jpIYR7aooUHDN8cfzzcTItvcdjnyx8X6gSU4YqQRwqJKAjoTMCgnMJlOoUvd/2zDQULBubuLC7VYg7zXIEHbqKZKIJLJMEyqr3crERIIFOcgWAxwv3qEPMvu7gUZiePZnkculj8NkyCDmLGUMCChvMoA4KQQATABwINxABGpUUFtmXq/2wxqRjGUKKuVNnwiDp9EnTFFlZQhPvhUEo8QMJ+7fgUaVLQYMJvntHkkszgoTMY15Mv1BSZsk1vvVVAKJtJAMAXEQKNqooMEOVCE0AUAUAUBAhzqA4FSM3I6IZ5rFmCgC5BIQKoKIG74EE1wA1r2vwrv7t7dXdz2H5Oh3Fc3fru1StM+gYZMRgFSEVRw3NlEj6FE1CkQMQxRsIGAQbWEBCtbdfTJVFmt69Kesb2zSbZaOSC6pnkn+sbW9MOzlUoMHkLOSwIC5EowQEEUtRU79bUmp4rd3lyGtR4euHxLJuvjZXd/k24rlihvnFFpHcMjR8cslK/xomGWfhX0CCymPgo1NFswiJyLkTiChiTgogUzfiW5CESTNfUHPlzroFlLNJVXPje38lc/3Kcl1S2toERGMlY9cf8lPs45eFq5jU9M1hdZLbloZf6l61fjDKL9X1G8f8/8AtT8zPWPp6i2WPY8Zlm8xB4MdgtpYSM1mMS7kXALc26ZDs0FhKSwHD7wQDVYA51zs66KR1sDPR0mrvW5V27DCkmQyJ4EGzs+M+UM0AnWIkCYgYBD78tg4qWtWJlVciybGy0/mGC5juDhe1mL4/kqCCkVhriEbJRy7p2cog4FRZYpQBJE4p3L+8Yhy8wrJCKhQ/T5jTo2P7u4NPw5ZiQx4UJFeLI8cMV3U0x8wUUzvmJwUMQTpd0oXJqMYeOoaxQrjxZMltxmXpikd0MZgF4HIm0k2jnTNSZkZRJE/mkQ5OljEMB0VSmDUlcL8OV6bAmCjTe7Z41Oen7bWNURx8jU7dCUWDLZSSZIC5fMtS5kDtlyH1GE4/d6tBC+EAsAVUJXE8GYeoDcnbl7jePJlwObQlhBm2XiHL1dBiRI6KBRcmFQwgWyoDf8AhKagLFH464y3dbHM2yp5t3IZDHKoooOIaVk/mAJodUxCoN+v0TqAKp/GQf8ACiEqdOhyqkCgCgCgCgIGooOJMdxhrLouJeclE4TG2qvllX6iZllFXBw1gi3SLxOfTxN2FCw8a7feXrolSONiySOSu6mFE5VXZj1n5r0vSmTxrNPIkULcN7NVXPdam1adRcGjXbZ+BWrLcScjFgsRJR+RQjewcA8OgpS+84V8WSS+jxdbRv8A7c+/sNqibpsq7jLyRi/my7KdqoXbBNqMhjMrYTjzIyy+MtimcIHbuVxBZa2lMDEEwl0hq1+MQ4Wr4Wpa3BJbujbFuSLRMWpgmfXsyTlNj0fh25huWyum95EiVSiriuzDL6c1FTuxMvpvOpF3INl2SZDA0YIOkzpH8s3uUDABwDgcwmP/ADVt+hQMhtGtYqOqlXKi/eXuy6Dn/FVxLPfPc5rmtTwtqlME78+kYvpmv5rJR/42X+a9a5xf9mL9X1G1f8/zn/R6xbt3t1doIBo8wbO1gnJCVb9NTDGDdSTknKKwcABsgAmLe1ynMJbcwGueHXjks+0e+B8MCPTx3ITbIJy4SKeAqP2v9yeTAQP4bDyN3gS1XA3f0au9WJnVDrPZ/dbaPImDPB8HN8gkohsCZcKkG6kdJtUUQC4eXWADHte5jkE973Eb3rIwUVPpgVRU3q3yImoRQxZaxykMUwh+ceBxAB4VDJciv+obCdrdm9pctxfHJQWs5mD9hLkgXr3rHODV6UVFGqRraCFKIga3DSUA7KKG5jcg5XaiI2R21V3a+UFjVIiNLH/PkEl0wceSIIiQq5DWNovxsHCiGJzb6hZvZmRzDb5bbQ8H8jQcmHIghmyKLcE/NsxDzQJJlKYvTBXxAPd1dl6imSDRid29o0N+Mew/EsOxN3jcmZMsZmUM2QB23kFElQKmIEbABD9Qmi4KXsco1kY0OtA5UITQBQBQBQEDUUHG8GxaZfjiOLJP0I7Iox45dxyTxTotnqLwqYKEBTiBViCkGnV4ijb227LcyOtJ1m3VfG9qI6iVVqtyw/CtetD89WEUeo2aWyPSOWNyuajsGvR27VK/iTdw5zcMtjcmTU8xlL1jARBOK71ZwRQ2kOPcKFi3t/EcK8UnEkG7SBrpHciIvb/Q+hb8GTou9cvbFGma12ejtQ2c3upG4fHNMS2mslHsVAUdy66YKC5Uvc4FKoAatYh31LB7CWDjXkttEkununva7zsmp93krTsT93IfRv8AiaKxjbb6fk3Ny7evl2r1G9h988ZyJsWK3GhUgIYLGdJpebaCI8BMZI4GOT+XX768M/Dc8DlfaSLzV3XfuyXsPo2fGFpdtSO9jRPppvN6s07RlYJA4DHi8mMEMiLWSBMrgGq5lkQFITCUAKYxumPeG4cPdWr6jc3j92O5rVtaVSi4m56VZ2ESultKUfStFqmHoPtnU1DYBjc/nox7c0q3ahZQqZSru3HBJo3MoUus2tUxEyh2aq+SbAhG2eYr5zhrCdkGpY+eKKzGeiym1+TlWKpmztDmPAipDabjxLYe2oDW7YZGTcOMc5XKxTNGYi5iahGjhImtUiEc+VZ3KocBOUVCpAJwAbDVBi0nUo/edfBWEOwatHmODkTuUQTBN4s7CQBtoU0gAGLYwmubjqoUoG7m78xiOYT8Ughi5Y7G8fbT4hkayiDuRFwq5Kdo0MW4AezcAL3D945Q08aAY2W578i2vHOyRJxlnLJqpDQDwNC55WTAiTNkoHMpzLrJpHty4j2UIffCskcZzt6zyViybM8jctVkXLBwURQazLQx2zlBTT3xIk5TOmNuIlC9ClS2t3SmtyJ0keXH0IttBRwEzg6wH6zPJxWMkMcgFgAwJFROsdTj3DpW8VAOUOVCBQBQBQBQGB6hDlnJ9hczaSzoMfapScOqoZRqYFkkVCJmNcE1CrGLxLe1yiIDz4cq6pZcTWzo096qseiUyVa81OXoOHanwZeMmVbdqPYq4eJEVE5F3lTmwqak2yO5xwADQmoC+EDPGhgD3XWr2pxDYJ9/yu7j5i8KauucS4fnj9sPojud/Qw9v/bafjVfmGw+J5X9xh8par8Hzx+2H0R3N7YQA/8AW0/Gp8xWHxPK7uL8o6r8Lzs9od+z23LzA4564lzpmmpQyYrpojqTSSR1aCauGowiYwmEA/V2XrQte1Vt69qMTwMrTlVVzU6rwzoT9Nicsior350yREy9Jutx9u2m5TGJhpZ+u1gmMm3lJFi1EUzPgZ6joomWIYp0ylW6a2pMdVyAFa2bkfPb/bVlty8yQIWQdLQc+9Tk0ot0czgzR0KBEXBwcqnOqp1xTKobqGGxr250LU1eBbY5FgUkuVnl5nmIOJCTlTwKsc3IfrSy6jowebKfXZNRQRDu8Q4UBYRwhH6mBuR55TzHyMce+W6C9LQLwHfW1+LVcNGnlahDy/TWHV3Gf7iP+m+dO41jGN2bhukoRsaPWXWKskc4CYpzCvYbW8IUKG4W3DLcf5CzmHzhCCh5Aks5j2hjIHdrt0zg2u4TMRVLoqGBYOmNxMUKlCE7fbds9ugnmUQ/cLwkvInlmrB0Yy52izhMgOQBdQx1FQVVKZYRUERAxhqg9OGYQjh77K3yT1R2OUzKk6qRQhSAgdRsg26RRAR1AANwG4+2gLaFAFAFAFAFAYD4g9/7KIYrmgD9lEKhmFAhA86FMfb7qm0hiPP9PaFEJ3GX7o+77apmpP2/soQn2UAfbQAPL4UAD2fCgI7PiFAT7KAkOQUAUAUB/9k=);
      }
      &.pay_weixin {
        background-image: url(data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABGAAD/4QMsaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjYtYzE0MCA3OS4xNjA0NTEsIDIwMTcvMDUvMDYtMDE6MDg6MjEgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDQyAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDozMjlEQTRGRDRBODgxMUU4OEJBREIyOUE3Mjg5MjBCRCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDozMjlEQTRGRTRBODgxMUU4OEJBREIyOUE3Mjg5MjBCRCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjMyOURBNEZCNEE4ODExRTg4QkFEQjI5QTcyODkyMEJEIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjMyOURBNEZDNEE4ODExRTg4QkFEQjI5QTcyODkyMEJEIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+/+4ADkFkb2JlAGTAAAAAAf/bAIQABAMDAwMDBAMDBAYEAwQGBwUE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);
      }
    }
  }
  //paymodal强制显示滚动条开始-----
  .modal_body::-webkit-scrollbar {
    width: 6px;
    height: 13px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
  }
  .modal_body::-webkit-scrollbar-thumb {
    background-color: rgba(0, 0, 0, 0.5);
    background-clip: padding-box;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
    min-height: 28px;
  }
  .modal_body::-webkit-scrollbar-thumb:hover {
    background-color: rgba(0, 0, 0, 0.5);
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
  }
  //paymodal强制显示滚动条结束-----
</style>

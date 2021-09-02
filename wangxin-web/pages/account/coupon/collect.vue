<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'cyhq'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on">领取优惠券</a>
        </ul>
        <div class="main_cont_head">
          提示：双击可以领取优惠券
        </div>
        <div class="main_cont">
          <div class="notdata" v-if="notdata">
            ★暂时没有数据
          </div>
          <div class="collect_coupon_type" >
            <div v-for="(item, index) in couponsList">
              <input type="radio" :id="item.id" name="studyCardType" :value="item.id" @click="collectCoupon(item.id)">
              <label :for="item.id" class="collect_coupon_detail">
                <span style="color: white">
                  {{item.couponName | cutTile}}<br/>
                  优惠金额:<span style="font-weight: bold;color: red">{{item.couponAmount}}元</span><span v-if="item.isLimitCourse==0">[不限制课程]</span><span v-if="item.isLimitCourse==1">[部分课程适用]</span><br/>
                  [有效期:{{item.couponTimeBegin}}至{{item.couponTimeEnd}}]</span>
              </label>
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
    import {saveCouponUser,canCollectCouponsList} from '~/api/account/user.js'
    import {myHttp} from '~/utils/myhttp.js'
    export default {
        components: {
            YHeader,
            YFooter,
            YSide,
            DPage
        },
        data () {
            return {
                couponsList: [],
                notdata: true,
                pageCurrent: 1,
                pageObj: {
                    pageCurrent: '',
                    pageSize: '',
                    totalCount: '',
                    totalPage: ''
                },
                time: 0,
            }
        },
        filters: {
            cutTile(value) {
                if (!value) return ''
                if (value.length > 14) {
                    return value.slice(0, 14) + '...'
                }
                return value
            }
        },
        methods: {
            collectCoupon(couponId){
                if(new Date().getTime()-this.time<1000){
                    let that = this;
                    this.$msgBox({
                        content: '确认领取该优惠券?'
                    }).then(async (val) => {
                        saveCouponUser({couponId: couponId}).then(res => {
                            res = res.data;
                            if (res.code === 200) {
                                this.$msgBox({
                                    content: '领取成功',
                                    isShowCancelBtn: false
                                })
                                that.getCouponList();
                            } else {
                                if (res.code >= 300 && res.code < 400) {
                                    if(res.code == 304){
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
                                        content: res.msg,
                                        isShowCancelBtn: false
                                    }).then(() => {
                                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                                    }).catch(() => {
                                        this.$store.dispatch('REDIRECT_LOGIN', result.code)
                                    })
                                } else {
                                    this.$msgBox({
                                        content: res.msg,
                                        isShowCancelBtn: false
                                    }).catch(() => {})
                                }
                            }
                        })
                    }).catch(() => {
                        //this.$msgBox({
                        //  content: '删除失败',
                        //  isShowCancelBtn: false
                        //})
                    })
                }else{
                    //console.log("这是单击");
                }
                this.time = new Date().getTime();
            },
            getCouponList () {
                canCollectCouponsList({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.couponsList = res.data.list || [];
                        if(this.couponsList.length == 0) {
                            this.notdata = true;
                        }else{
                            this.notdata = false;
                        }
                    } else {
                        if (res.code >= 300 && res.code < 400) {
                            if(res.code == 304){
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
                                content: res.msg,
                                isShowCancelBtn: false
                            }).then(() => {
                                this.$store.dispatch('REDIRECT_LOGIN', result.code)
                            }).catch(() => {
                                this.$store.dispatch('REDIRECT_LOGIN', result.code)
                            })
                        } else {
                            this.$msgBox({
                                content: res.msg,
                                isShowCancelBtn: false
                            }).catch(() => {})
                        }
                    }
                }).catch(() => {
                    this.couponsList = res.data.list || [];
                    if(this.couponsList.length == 0) {
                        this.notdata = true;
                    }else{
                        this.notdata = false;
                    }
                })
            }
        },
        mounted () {
            this.getCouponList()
        }
    }
</script>
<style lang="scss">
  @import '~/assets/css/account.scss';
  .main_cont_head{
    min-height: 30px;
    padding: 20px;
  }
  .collect_coupon_type{//学习卡
    padding: 20px;
    overflow: hidden;
    border-top: 1px solid #f1f1f1;
    input{
      display: none;
      &:checked + .collect_coupon_detail{
        border-color: blue;
      }
    }
  }
  .collect_coupon_detail{
    width: 308px;
    height: 80px;
    border: 5px solid #fff;
    font-weight: bold;
    float: left;
    line-height: 20px;
    text-align: center;
    background-repeat: no-repeat;
    background-position: center center;
    background-color: deepskyblue;
  }
</style>

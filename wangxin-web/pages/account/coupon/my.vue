<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'myyhq'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on">我的优惠券</a>
        </ul>
        <div class="main_cont">
          <div class="notdata" v-if="false">
            ★暂时没有数据
          </div>
          <div class="my_coupon_type" >
            <div v-for="(item, index) in couponsList">
              <input type="radio" :id="item.id" name="studyCardType" :value="item.id">
              <label :for="item.id" class="my_coupon_detail">
                <span style="color: white">
                  {{item.couponName | cutTile}}<br/>
                  优惠金额:<span style="font-weight: bold;color: red">{{item.couponAmount}}元</span><span v-if="item.isLimitCourse==0">[不限制课程]</span><span v-if="item.isLimitCourse==1">[部分课程适用]</span><br/>
                  [有效期:{{item.couponTimeBegin}}至{{item.couponTimeEnd}}]<br/>
                <span v-if="item.isUsed==0" style="color: #e01c2b">
                    <span v-if="isOutDate(item.couponTimeEnd)">已经过期</span>
                    <span v-if="!isOutDate(item.couponTimeEnd)">未使用</span>
                </span><span v-if="item.isUsed==1">已使用</span></span>
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
    import {couponUserList} from '~/api/account/user.js'
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
                }
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
            isOutDate(endDate){
                let endDateTime = new Date(endDate).getTime();
                let nowTime = new Date().getTime();
                if(nowTime>endDateTime){
                    console.log("已经过期")
                    return true;
                }else{
                    return false;
                }
            },
            getCouponUserList () {
                console.log("99999:====>")
                couponUserList({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.couponsList = res.data.list || [];
                    } else {
                        if (res.code >= 300 && res.code < 400) {
                            if(res.code == 304){
                                this.$msgBox({
                                    content: '该账号被异地登录',
                                    isShowCancelBtn: false
                                }).then(() => {
                                    this.$store.dispatch('REDIRECT_LOGIN', res.code)
                                }).catch(() => {
                                    this.$store.dispatch('REDIRECT_LOGIN', res.code)
                                })
                            }
                            this.$msgBox({
                                content: res.msg,
                                isShowCancelBtn: false
                            }).then(() => {
                                this.$store.dispatch('REDIRECT_LOGIN', res.code)
                            }).catch(() => {
                                this.$store.dispatch('REDIRECT_LOGIN', res.code)
                            })
                        } else {
                            this.$msgBox({
                                content: res.msg,
                                isShowCancelBtn: false
                            }).catch(() => {})
                        }
                    }
                }).catch(() => {
                })
                console.log("9999结尾")
            }
        },
        mounted () {
            this.getCouponUserList()
        }
    }
</script>
<style lang="scss">
  @import '~/assets/css/account.scss';
  .my_coupon_type{//学习卡
    padding: 20px;
    overflow: hidden;
    border-top: 1px solid #f1f1f1;
    input{
      display: none;
      &:checked + .my_coupon_detail{
        border-color: blue;
      }
    }
  }
  .my_coupon_detail{
    width: 308px;
    height: 100px;
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

<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'dot'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on">点券</a>
          <div @click="openDotToPoint" class="fr solid_btn">点券兑换积分</div>
        </ul>
        <div class="main_cont_head">
          <div class="point_type" >
            <div style="font-size: 18px;font-weight: bold">点券总量：<span style="color: blue">{{this.viewDot.totalDot}}</span></div>
            <hr/>
              <div style="font-size: 18px;font-weight: bold;display: inline-block;">
                <div style="float: left;">
                推广链接：{{this.viewDotConfig.regLink}}?id={{this.userNo}}
                </div>
                <div style="float:right;">
                <button @click="copyContenToClip()">复制链接</button>
                </div>
              </div>
            <hr>
            <!--
            <div style="font-size: 18px;font-weight: bold;">获取点券规则：</div>
              <div style="font-size: 14px;font-weight: bold">初次注册获取的点券：{{this.viewDotConfig.regDot}}</div>
              <div style="font-size: 14px;font-weight: bold">每次登陆获取点券：{{this.viewDotConfig.loginDot}}</div>
              <div style="font-size: 14px;font-weight: bold">登陆一天获得的最大点券：{{this.viewDotConfig.loginMaxDotPerDay}}</div>
              <div style="font-size: 14px;font-weight: bold">分享每次获得得点券：{{this.viewDotConfig.shareDot}}</div>
              <div style="font-size: 14px;font-weight: bold">分享链接每天获得的最大点券：{{this.viewDotConfig.shareMaxDotPerDay}}</div>
              <div style="font-size: 14px;font-weight: bold">分享后每次被注册获得的点券：{{this.viewDotConfig.shareRegDot}}</div>
              <div style="font-size: 14px;font-weight: bold">分享链接被注册每天获得的最大点券：{{this.viewDotConfig.shareRegMaxDotPerDay}}</div>
              <div style="font-size: 14px;font-weight: bold">点券兑换积分规则：{{this.viewDotConfig.dotPoint}}点券可以兑换1个积分</div>
              -->
          </div>
        </div>
        <div class="main_cont">
          <div class="notdata" v-if="false">
            ★暂时没有数据
          </div>
          <table class="course_table table" v-else>
            <thead>
            <tr>
              <th>序号</th>
              <th>时间</th>
              <th>操作类型</th>
              <th>点券量</th>
              <th>点券总量</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item,index) in pointOperateRecordList" :key="item.id">
              <td>{{index=index+1}}</td>
              <td>{{item.createTime}}</td>
              <td><span v-if="item.operateType==0">增加</span><span v-if="item.operateType==1">减少</span></td>
              <td>{{item.dot}}</td>
              <td>{{item.totalDot}}</td>
            </tr>
            </tbody>
          </table>
          <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
    <dot-to-point v-if="isOpenDotToPoint"
                    :open="false"
                  @getDotOperateRecordList="getDotOperateRecordList()"
                  @hidefun="hideDotToPoint()"></dot-to-point>
  </div>
</template>
<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import YSide from '~/components/account/Side'
    import AddStudyCard from '~/components/account/AddStudyCard'
    import DPage from '~/components/Page'
    import {dotOperateRecordList,viewDotByUserNo,viewDotConfig} from '~/api/account/user.js'
    import DotToPoint from "~/components/account/DotToPoint";
    import VueCookie from 'vue-cookie'

    export default {
        components: {
            DotToPoint,
            AddStudyCard,
            YHeader,
            YFooter,
            YSide,
            DPage
        },
        data () {
            return {
                isOpenDotToPoint: false,
                notdata: true,
                pageCurrent: 1,
                pageObj: {
                    pageCurrent: '',
                    pageSize: '',
                    totalCount: '',
                    totalPage: ''
                },
                pointOperateRecordList:[{id:'22322',create_time:'2020-09-09 20:09',operate_type:'减少',point:'9'}],
                viewDot: '',
                viewDotConfig: '',
                userNo: ''
            }
        },
        methods: {
            openDotToPoint(){
                this.isOpenDotToPoint = true;
            },
            hideDotToPoint(){
                this.isOpenDotToPoint = false;
            },
            viewDotByUserNo () {
                viewDotByUserNo({}).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.viewDot = res.data || [];

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
                            }).catch(() => {
                            })
                        }
                    }
                }).catch(() => {
                })
            },
            getViewDotConfig () {
                viewDotConfig({}).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.viewDotConfig = res.data || [];

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
                            }).catch(() => {
                            })
                        }
                    }
                }).catch(() => {
                })
            },
            getDotOperateRecordList () {
                dotOperateRecordList({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.pointOperateRecordList = res.data.list || [];

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
                })
            },
            //
            copyContenToClip(){
                let content = this.viewDotConfig.regLink+'?id='+this.userNo;
                let aux = document.createElement("input");
                aux.setAttribute("value", content);
                document.body.appendChild(aux);
                aux.select();
                document.execCommand("copy");
                document.body.removeChild(aux);
                this.$msgBox({
                    content: '复制成功',
                    isShowCancelBtn: false
                }).catch(() => {})
            }
        },
        mounted () {
            this.getDotOperateRecordList();
            this.viewDotByUserNo();
            this.getViewDotConfig();
            try {
                this.userNo = JSON.parse(VueCookie.get("wangxinUserInfo")).userNo;
            }catch (e) {
                this.$router.push({name: 'index'});
            }
        }
    }
</script>
<style lang="scss">
  @import '~/assets/css/account.scss';
  .main_cont_head{
    min-height: 100px;
    padding: 20px;
  }
  .point_type{
    padding: 20px;
    overflow: hidden;
    border-top: 1px solid #f1f1f1;

  }
</style>

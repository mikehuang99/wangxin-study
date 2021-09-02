<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'point'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on">积分</a>
        </ul>
        <div class="main_cont_head">
          <div class="point_type" >
              <div style="font-size: 18px;font-weight: bold">总积分：<span style="color: blue">{{viewPoint.totalPoint}}</span></div>
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
              <th>积分</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item,index) in pointOperateRecordList" :key="item.id">
              <td>{{index=index+1}}</td>
              <td>{{item.createTime}}</td>
              <td><span v-if="item.operateType==0">增加</span><span v-if="item.operateType==1">减少</span></td>
              <td>{{item.point}}</td>
            </tr>
            </tbody>
          </table>
          <d-page v-if="pageObj.totalPage > 1" :page="pageObj" @btnClick="getPage"></d-page>
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
    import AddStudyCard from '~/components/account/AddStudyCard'
    import DPage from '~/components/Page'
    import {pointOperateRecordList,viewPointByUserNo} from '~/api/account/user.js'
    export default {
        components: {
            AddStudyCard,
            YHeader,
            YFooter,
            YSide,
            DPage
        },
        data () {
            return {
                studyCardList: [{id:'2sdjfdsfsdf2',cardName:'学习卡1'},{id:'3sdfsfdf3',cardName:'学习卡2'},{id:'123213123123',cardName:'学习卡3'},
                    {id:'123312323',cardName:'学习卡4'},{id:'sdfsdfwew',cardName:'学习卡5'},{id:'sdfkjwefwfwefw',cardName:'学习卡6'}],
                notdata: true,
                pageCurrent: 1,
                pageObj: {
                    pageCurrent: '',
                    pageSize: '',
                    totalCount: '',
                    totalPage: ''
                },
                isOpenAddStudyCard: false,
                pointOperateRecordList:[{id:'22322',create_time:'2020-09-09 20:09',operate_type:'减少',point:'9'}],
                viewPoint: ''
            }
        },
        methods: {
            openAddStudyCard(){
                this.isOpenAddStudyCard = true;
            },
            hideAddStudyCard(){
                this.isOpenAddStudyCard = false;
            },
            viewPointByUserNo () {
                viewPointByUserNo({}).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.viewPoint = res.data || [];

                    } else {
                        if (res.code >= 300 && res.code < 400) {
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
            getPointOperateRecordList () {
                pointOperateRecordList({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.pointOperateRecordList = res.data.list || [];

                    } else {
                        if (res.code >= 300 && res.code < 400) {
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
            }
        },
        mounted () {
            this.getPointOperateRecordList();
            this.viewPointByUserNo();
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

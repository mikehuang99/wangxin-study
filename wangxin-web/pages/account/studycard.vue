<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'studycard'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on">学习卡</a>
          <div @click="openAddStudyCard" class="fr solid_btn">新增学习卡</div>
        </ul>
        <div class="main_cont">
          <div class="notdata" v-if="false">
            ★暂时没有数据
          </div>
          <div class="study_card_type" >
            <div v-for="(item, index) in studyCardList">
              <input type="radio" :id="item.id" name="studyCardType" :value="item.id" >
              <label :for="item.id" class="study_card_detail">
                <span style="color: white">
                  {{item.cardTitle | cutTile}}<br/>
                  已用{{item.usedFaceValueAmount==null?0:item.usedFaceValueAmount}}元/冻结{{item.frozenFaceValueAmount==null?0:item.frozenFaceValueAmount}}元/原面额{{item.totalFaceValueAmount}}元<br/>
                  [有效期:{{item.studyCardTimeBegin}}至{{item.studyCardTimeEnd}}]
                </span>
              </label>
            </div>
          </div>
        </div>
      </div>
    </div>
    <y-footer></y-footer>
    <add-study-card v-if="isOpenAddStudyCard"
                    :open="false"
                    @getStudyCardList="getStudyCardList()"
                    @hidefun="hideAddStudyCard()"></add-study-card>
  </div>
</template>
<script>
    import YHeader from '~/components/common/Header'
    import YFooter from '~/components/common/Footer'
    import YSide from '~/components/account/Side'
    import AddStudyCard from '~/components/account/AddStudyCard'
    import DPage from '~/components/Page'
    import {studyCardList,bindStudyCardUserNo} from '~/api/account/user.js'
    import {myHttp} from '~/utils/myhttp.js'
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
                studyCardList: [],
                notdata: true,
                pageCurrent: 1,
                pageObj: {
                    pageCurrent: '',
                    pageSize: '',
                    totalCount: '',
                    totalPage: ''
                },
                isOpenAddStudyCard: false
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
            openAddStudyCard(){
                this.isOpenAddStudyCard = true;
            },
            hideAddStudyCard(){
              this.isOpenAddStudyCard = false;
            },
            getStudyCardList () {
                studyCardList({

                }).then(res => {
                    res = res.data;
                    if (res.code === 200) {
                        this.studyCardList = res.data.list || [];

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
            }
        },
        mounted () {
            this.getStudyCardList()
        }
    }
</script>
<style lang="scss">
  @import '~/assets/css/account.scss';
  .study_card_type{//学习卡
    padding: 20px;
    overflow: hidden;
    border-top: 1px solid #f1f1f1;
    input{
      display: none;
      &:checked + .study_card_detail{
        border-color: blue;
      }
    }
  }
  .study_card_detail{
    width: 308px;
    height: 90px;
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

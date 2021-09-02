<template>
  <div>
    <y-header></y-header>
    <div class="person_body container account_cont clearfix">
      <y-side :type="'xxjl'"></y-side>
      <div class="main_box">
        <ul class="tabs clearfix">
          <a class="tab on">学习记录</a>
        </ul>
        <div class="main_cont">
          <div class="notdata" v-if="notdata">
            ★暂时没有数据
          </div>
          <table class="course_table table" v-else>
            <thead>
            <tr>
              <th>课程名称</th>
              <th>学习内容</th>
              <th>学习时间</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(item, index) in pageObj.list">
              <td>{{item.courseName}}</td>
              <td class="name">{{item.lessonName}}</td>
              <td>{{item.createTime}}</td>
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
    import DPage from '~/components/Page'
    import {studyList} from '~/api/account/user.js'
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
        methods: {
            getPage (int) {
                this.pageCurrent = int
                this.getStudyList()
            },
            getStudyList () {
                myHttp.call(this, {
                    method: studyList,
                    params: {
                        pageCurrent: this.pageCurrent,
                        pageSize: 20
                    }
                }).then(res => {
                    console.log(res)
                    this.pageObj = res.data
                    if (!res.data.list.length) {
                        this.notdata = true
                    } else {
                        this.notdata = false
                    }
                })
            }
        },
        mounted () {
            this.getStudyList()
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
      &:checked + .study_card_detail_2{
        border-color: blue;
      }
    }
  }
  .study_card_detail_2{
    width: 218px;
    height: 50px;
    border: 5px solid #fff;
    font-weight: bold;
    float: left;
    line-height: 40px;
    text-align: center;
    background-repeat: no-repeat;
    background-position: center center;
    background-color: lightblue;
  }
</style>

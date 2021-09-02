<template>
  <div>
    <div>
      <my-study-card-list-header v-on:keyup.13="submits()" :reloadStudyCardList="reloadStudyCardList"></my-study-card-list-header>
      <div class="container full">
        <div class="course_list">
          <my-study-card-list ref="myStudyLogList" :courseName="courseName"
                      ></my-study-card-list>
        </div>
      </div>
    </div>
  </div>

</template>
<script>
  import myStudyCardList from '~/components/myStudyCard/myStudyCardList.vue'
  import myStudyCardListHeader from '~/components/myStudyCard/Header.vue'

  export default {
    data () {
      return {
        courseName: ''
      }
    },
    components: {
        myStudyCardList,
        myStudyCardListHeader
    },
    computed: {

    },
    mounted () {


    },
    methods: {
        openAddStudyCardDialog(){
            this.$refs.myStudyLogList.openAddStudyCardDialog();
        },
      reloadStudyCardList(courseName) {
        this.courseName = courseName;

      },
      submits () {
      }
    },
    watch: {
      //'$route': 'updateData'
    }
  }
</script>
<style lang="sass" scoped>
  .search_criteria
    width: 100%
    height: 46px
    background: #fff
    margin: 0 0 10px 0
    line-height: 44px
    position: fixed
    z-index: 1200
    border-bottom: 1px solid #fff
    .search_criteria_ul
      width: 100%
      position: relative
      .search_criteria_tabs
        width: 33.3%
        background: url("/wxmobile/img/smart_line.png") no-repeat right 12px
        background-size: 1px 20px
        float: left
        >a
          height: 100%
          width: 100%
          font-size: 1.4rem
          display: inline-block
          text-decoration: none
          text-overflow: ellipsis
          color: black
          white-space: nowrap
          text-align: center
          background: url("/wxmobile/img/sorting_01.png") no-repeat 85% 19px
          background-size: 10px 5px
          padding-right: 12px
          -webkit-tap-highlight-color: transparent
          overflow: hidden
          border-bottom: 1px solid #ccc
          &.on
            color: #ef3f41
            background-position: 85% 19px
            background-image: url("/wxmobile/img/sorting_02.png")
        .search_criteria_tab
          position: absolute
          width: 100%
          top: 45px
          left: 0
          z-index: 100
          .localtion_order_list
            min-height: 250px
            max-height: 350px
            background: #fff
            overflow-y:scroll
            li
              margin-left: 33px
              position: relative
              height: 45px
              border-bottom: 1px solid
              span
                float:left
                font-size: 1.4rem
                &.on
                  color:#ef4040
              a
                width: 85%
                height: 100%
                display: inline-block
                float: left
                font-size: 1.4rem
                -webkit-tap-highlight-color: transparent
                position: relative
                &.on
                  color:#ef4040
              i
                width: 14px
                height: 11px
                float: left
                background-size: 15px
                position: absolute
                display: none
                top: 16px
                left: -20px
                background: url('/wxmobile/img/ok_go.png')no-repeat
                &.on
                  display: inline-block
          .order_list
            max-height: 350px
            li
              border-bottom: 1px solid #bbb
              a
                display: inline-block
                float: left
                font-size: 1.4rem
              i
                width: 17px
                height: 17px
                display: block
                top: 12px
                left: -23px
            .icon_bg_1
              background: url('/wxmobile/img/icon_sort_lisrt.png')no-repeat
              background-size: 17px 17px
            .icon_bg_2
              background: url('/wxmobile/img/icon_me_jin.png')no-repeat
              background-size: 17px 17px
            .icon_bg_3
              background: url('/wxmobile/img/icon_mast_star.png')no-repeat
              background-size: 17px 17px
            .icon_bg_4
              background: url('/wxmobile/img/icon_teacher_good.png')no-repeat
              background-size: 17px 17px
            .icon_bg_5
              background: url('/wxmobile/img/icon_pay_big.png')no-repeat
              background-size: 17px 17px
            .icon_bg_6
              background: url('/wxmobile/img/icon_pay_big.png')no-repeat
              background-size: 17px 17px
          .tabbar
            overflow-y: scroll
            max-height: 360px
            min-height: 360px
            &.screen_area
              width: 100%
              background: #fff
              // 主体区域
              .main
                max-height: 100%
                overflow-y: auto
                // padding: 17px 0 10px 15px
                padding-bottom: 4.5rem
                .screen_title
                  h3
                    height: 30px
                    background: #eee
                    line-height: 30px
                    span
                      display: inline-block
                      height: 12px
                      margin-right: 8px
                      border-left: 2px solid #ef4040
                      position: relative
                      top: 2px
                .screen_detail
                  overflow: hidden
                  border-bottom: 1px solid #eee
                  padding: 5px 10px 5px 0px
                  li
                    width: 25%
                    padding-left: 10px
                    float: left
                    &.on
                      span
                        color: #fff
                        background: #e4393c
                    span
                      text-align: center
                      border: 1px solid #e4393c
                      border-radius: 2px solid #eee
                      display: inline-block
                      width: 100%
                      height: 100%
                      line-height: 26px
                      border-radius: 50px
                      border-color: #bbb
                      color: #333
                      font-size: 1.1rem
                      &.on
                        border-color: #e4393c
                        background: #fde8e8
                        color: #e4393c
              .screen_bottom
                width: 100%
                height: 50px
                overflow: hidden
                background: #e7e7e7
                position: absolute
                left: 0
                bottom: 0
                .screen_reset
                  font-size: 1.4rem
                  text-align: center
                  float: left
                  border-radius: 20px
                  background: #fff
                  color: #999
                  width: 80px
                  height: 35px
                  border: 1px solid #ddd
                  line-height: 35px
                  float: left
                  margin-top: 7px
                  margin-right: 10px
                .screen_ok
                  font-size: 1.4rem
                  text-align: center
                  float: left
                  border-radius: 20px
                  background: #ef4040
                  color: #fff
                  width: 80px
                  height: 35px
                  border: 1px solid #ef4040
                  line-height: 35px
                  margin-top: 7px
                  float:right
                  margin-right: 10px
  .course_list
    position: relative
    top: 52px
    margin-top: 0px
  .search_tabs
    position: relative
    overflow: hidden
    width: 100%
  .downlist-enter-active, .downlist-leave-active
    transition: all .2s
    transform: translateY(0)
  .downlist-enter, .downlist-leave-active
    opacity: 0
    transform: translateY(-100%)
  .showcover-enter-active, .showcover-leave-active
    transition: opacity .2s
  .showcover-enter, .showcover-leave-active
    opacity: 0
  .subject_header_img
    width: 100%
    height: 6rem
    background: url('/wxmobile/img/head_img_bj.jpg')no-repeat center center
    background-size: 100% 100%
    margin-top: 1.5rem
    position: relative
    span
      display: inline-block
      width: 1.7rem
      height: 1.7rem
      background: url('/wxmobile/img/icon_more_head.png')no-repeat center center
      background-size: 1.7rem
      margin-left: 1rem
      position: absolute
      top: 2.5rem
      right: 2rem
    p
      font-size: 1.8rem
      color: #fff
      font-weight: bold
      padding: 2rem 0
      position: relative
      padding-left: 50%
      a
        position: absolute
        color: #fff
        display: inline-block
        height: 100%
        -webkit-transform: translate(-50%)
        -ms-transform: translate(-50%)
        -o-transform: translate(-50%)
        transform: translate(-50%)
        display: inline-block
  #courseSubject
    height: 33rem
    .left_side
      width: 25%
      height: 100%
      overflow: scroll
      float: left
      background: #fff
      p
        display: block
        text-align: center
        background: #f0f0f0
        font-size: 1.2rem
        -webkit-tap-highlight-color:rgba(255,0,0,0)
        &.activeLevelOne
          background: #fff
          border-left: 2px solid #ef4040
          -webkit-tap-highlight-color:rgba(255,0,0,0)
    .right_side
      float: right
      width: 75%
      overflow: scroll
      height: 100%
      background: #fff
      .right_side_i
        padding: 0 1rem
        .right_two
          .third_menu
            background: url(/wxmobile/img/area_no_new.png)no-repeat right 16px
            background-size: 1.3rem 0.6rem
            display: inline-block
            width: 100%
            padding: 1rem 0
            line-height: 1.4rem
            font-size: 1.3rem
            &.active
              background: url(/wxmobile/img/area_no_new_on.png)no-repeat right 16px
              background-size: 1.3rem 0.6rem
          ul
            overflow: hidden
            border-top: 1px solid #eee
            border-left: 1px solid #eee
            li
              float: left
              border-right: 1px solid #eee
              border-bottom: 1px solid #eee
              width: 33.33%
              a
                display: inline-block
                width: 100%
                height: 100%
                text-align: center
                -webkit-tap-highlight-color: transparent
                &:active
                  outline: none
                &.on
                  color: #ef4040
  .back cover
    position: fixed
    width: 100%
    height: 100%
    z-index: 10
    background-color: rgba(0,0,0,0.3)
</style>

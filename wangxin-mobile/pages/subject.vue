<template>
  <div>
    <header id="header" class="header">
      <p>全部科目</p>
    </header>
    <div class="subject_content_main">
      <div class="subjects" style="display:block">
        <div class="left_side">
          <p v-for="(value,index) in subjectLevel.levelOne"  :key="index" :data-id="value.levelOne"  :class="{activeLevelOne: changeActivated == index}" @click="chooseLevelOne(index,value.levelOne,value.subjectName)" >{{value.subjectName}}</p>
        </div>
        <div class="right_side">
          <div class="right_side_i on">
            <div class="subject_header_img" :style="{background: `url(/wxmobile/img/headImg/head_pic.jpg)no-repeat center center`}">
            </div>
						<div class="right_two" v-for="(items,index) in subjectLevel.levelTwo" :key="index">
							<a  :class="{active: isActive == index}" href="javascript:;" :data-id="items.levelTwoId" :data-pid="items.pid" class="switch_a area_r mar_l third_menu" @click="chooseLevelTwo(index,items.levelTwoId)">{{items.twoSubjectName}}</a>
							<div v-show="isActive == index">
								<ul>
                  <li :data-id="items.levelTwoId" @click="chooseClass(items.twoSubjectName, items.levelTwoId, 2)"><nuxt-link to="/courseAllList">全部</nuxt-link></li>
									<li  v-for="(item,indexs) in subjectLevel.levelThree" v-if="item.pid == items.levelTwoId" :key="indexs" :data-id="item.levelThreeId" @click="chooseClass(item.threeSubjectName,item.levelThreeId,3)"><nuxt-link to='/courseAllList'>{{item.threeSubjectName}}</nuxt-link></li>
								</ul>
							</div>
						</div>
          </div>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>
<script>
import Footer from '~/components/index/footer/Footer.vue'
import {findSubjects} from '~/api/index'

export default {
  data () {
    return {
      // 变化的课程名字
      currentSubject: 'xxx',
      // 当前选中的ID
      currentId: 'xxx',
      // 分类数组
      subjectLevel: [],
      // 位置左侧是选中的id
      changeActivated: 0,
      isActive: ''
    }
  },
  created () {
  },
  computed: {

  },
  mounted () {
      this.findSubjects();
    this.changeActivated = 0
  },
  methods: {
      findSubjects() {
          findSubjects().then(res => {
              if (res.data.code === 200 || 1==1) {
                  //this.newsList = res.data.data.list || [];

                  let subjectData = res;
                  let levelOne = [];
                  let levelTwo = [];
                  let levelThree = [];

                  let courseSubjectList = res.data.data.courseSubjectList || [];
                  for(let i=0;i<courseSubjectList.length;i++){
                      console.log("courseSubjectList[].subjectName:"+courseSubjectList[i].subjectName);
                      levelOne.push({
                          "levelOne":  courseSubjectList[i].id,
                          "subjectName": courseSubjectList[i].subjectName
                      });
                      if(i==0){
                          let twoList = courseSubjectList[i].twoList;
                          for(let j=0;j<twoList.length;j++){
                              levelTwo.push({
                                  "levelTwoId":  twoList[j].id,
                                  "pid": courseSubjectList[i].id,
                                  "twoSubjectName": twoList[j].subjectName
                              });
                              if(j==0) {
                                  let threeList = twoList[j].threeList;
                                  for(let n=0;n<threeList.length;n++){
                                      levelThree.push({
                                          "levelThreeId": threeList[n].id,
                                          "threeSubjectName": threeList[n].subjectName,
                                          "pid": twoList[j].id
                                      });
                                  }
                              }
                          }
                      }
                  }

                  /*
                  levelOne.push({
                      "levelOne":  "23434",
                      "subjectName": "大数据"
                  });
                  levelOne.push({
                      "levelOne":  "9888",
                      "subjectName": "云计算"
                  });
                  levelOne.push({
                      "levelOne":  "32443",
                      "subjectName": "云计算3"
                  });
                  levelTwo.push({
                      "levelTwoId":  "2324",
                      "pid": "9888",
                      "twoSubjectName": "云计算2"
                  });
                  levelTwo.push({
                      "levelTwoId":  "1233",
                      "pid": "9888",
                      "twoSubjectName": "云1计算2"
                  });
                  levelTwo.push({
                      "levelTwoId":  "3244",
                      "pid": "9888",
                      "twoSubjectName": "云4算2"
                  });
                  levelTwo.push({
                      "levelTwoId":  "2122",
                      "pid": "9888",
                      "twoSubjectName": "是否"
                  });
                  levelThree.push({
                      "levelThreeId": "213123jk",
                      "threeSubjectName": "结尾",
                      "pid": "3244"
                  });
                  levelThree.push({
                      "levelThreeId": "2342342",
                      "threeSubjectName": "结3",
                      "pid": "3244"
                  });
                  */
                  let  subject = {"levelOne": levelOne,"levelTwo" : levelTwo,"levelThree" : levelThree};
                  this.subjectLevel = subject;
              } else {
                  console.log('88999！')
              }
          });
      },
      selectLevelOne(levelOneId) {
          findSubjects().then(res => {
              if (res.data.code === 200 || 1==1) {
                  //this.newsList = res.data.data.list || [];

                  let subjectData = res;
                  let levelOne = [];
                  let levelTwo = [];
                  let levelThree = [];

                  let courseSubjectList = res.data.data.courseSubjectList || [];
                  for(let i=0;i<courseSubjectList.length;i++){
                      console.log("courseSubjectList[].subjectName:"+courseSubjectList[i].subjectName);
                      levelOne.push({
                          "levelOne":  courseSubjectList[i].id,
                          "subjectName": courseSubjectList[i].subjectName
                      });
                      if(courseSubjectList[i].id == levelOneId){
                          let twoList = courseSubjectList[i].twoList;
                          for(let j=0;j<twoList.length;j++){
                              levelTwo.push({
                                  "levelTwoId":  twoList[j].id,
                                  "pid": courseSubjectList[i].id,
                                  "twoSubjectName": twoList[j].subjectName
                              });
                              if(j==0) {
                                  let threeList = twoList[j].threeList;
                                  for(let n=0;n<threeList.length;n++){
                                      levelThree.push({
                                          "levelThreeId": threeList[n].id,
                                          "threeSubjectName": threeList[n].subjectName,
                                          "pid": twoList[j].id
                                      });
                                  }
                              }
                          }
                      }
                  }
                  let  subject = {"levelOne": levelOne,"levelTwo" : levelTwo,"levelThree" : levelThree};
                  this.subjectLevel = subject;
              } else {
                  console.log('88999！')
              }
          });
      },
      selectGradeTwo(id,levelTwoId) {
          findSubjects().then(res => {
              if (res.data.code === 200 || 1==1) {
                  //this.newsList = res.data.data.list || [];

                  let subjectData = res;
                  let levelOne = [];
                  let levelTwo = [];
                  let levelThree = [];

                  let courseSubjectList = res.data.data.courseSubjectList || [];
                  for(let i=0;i<courseSubjectList.length;i++){
                      console.log("courseSubjectList[].subjectName:"+courseSubjectList[i].subjectName);
                      levelOne.push({
                          "levelOne":  courseSubjectList[i].id,
                          "subjectName": courseSubjectList[i].subjectName
                      });
                      if(courseSubjectList[i].id == id){
                          let twoList = courseSubjectList[i].twoList;
                          for(let j=0;j<twoList.length;j++){
                              levelTwo.push({
                                  "levelTwoId":  twoList[j].id,
                                  "pid": courseSubjectList[i].id,
                                  "twoSubjectName": twoList[j].subjectName
                              });
                              if(twoList[j].id == levelTwoId) {
                                  let threeList = twoList[j].threeList;
                                  for(let n=0;n<threeList.length;n++){
                                      levelThree.push({
                                          "levelThreeId": threeList[n].id,
                                          "threeSubjectName": threeList[n].subjectName,
                                          "pid": twoList[j].id
                                      });
                                  }
                              }
                          }
                      }
                  }
                  let  subject = {"levelOne": levelOne,"levelTwo" : levelTwo,"levelThree" : levelThree};
                  this.subjectLevel = subject;
              } else {
                  console.log('88999！')
              }
          });
      },

    // 定位左侧选中
    async chooseLevelOne (index, levelOneId, name) {
      this.changeActivated = index

        this.selectLevelOne(levelOneId);
      this.currentSubject = name
      this.currentId = levelOneId
    },
    chooseClass (name, courseId, type) {

    },
    // 三级分类选中展开
    chooseLevelTwo (type,levelTwoId) {
      this.isActive = type;
        this.selectGradeTwo(this.currentId ,levelTwoId)
    }
  },
  components: {
      Footer
  }
}
</script>
<style lang="sass" scoped>
@import '~static/css/style.sass'
#header
  p
    font-size: 1.7rem
    line-height: 35px
.header
	background: #fff
	border-bottom: 1px solid $theme_gray
	p
		color: $theme_black
.subject_content_main
	width: 100%
	height: 100%
	padding-top: 44px
	overflow: hidden
	position: absolute
	left: 0
	top: 0
.subjects
  height: 100%
  .left_side
    min-height: 100% !important
    padding-bottom: 6.5rem
    a
      padding: 1.3rem 0 1.3rem 1rem
  .right_side
    min-height: 100% !important
    padding-bottom: 6.5rem

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
		height: 40px
		line-height: 40px
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
.right_side
  background: #fff
  ul
    li
      line-height: 44px
.subject_header_img
  width: 100%
  height: 6rem
  background: url('/wxmobile/img/head_img_bj.jpg')no-repeat center center
  background-size: 100% 100%
  margin-top: 1.5rem
  position: relative
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
</style>

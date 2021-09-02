<!-- 列表页分类 -->
<template>
  <div class="header_list">
    <div class="list_content">
      <ul class="content_ul clearfix">
        <li :class="{now: oneNow == 0}" @click="changeOne([], -1, '')"><a href="javascript:">全部</a></li>
        <li :class="{now: oneNow == item.id}" v-for="(item, index) in classList" :key="index" @click="changeOne(item.twoList, index, item.id)"><a href="javascript:">{{item.subjectName}}</a></li>
      </ul>
    </div>
    <div class="list_content" v-if="twoList.length">
      <ul class="content_ul clearfix">
        <li :class="{now: twoNow == 0}" @click="changeTwo([], -1, '')"><a href="javascript:">全部</a></li>
        <li :class="{now: twoNow == two.id}" v-for="(two, index) in twoList" :key="index" @click="changeTwo(two.threeList, index, two.id)"><a href="javascript:">{{two.subjectName}}</a></li>
      </ul>
    </div>
    <div class="list_content" v-if="threeList.length">
      <ul class="content_ul clearfix">
        <li :class="{now: threeNow == 0}" @click="changeThree(-1, '')"><a href="javascript:">全部</a></li>
        <li :class="{now: threeNow == three.id}" @click="changeThree(index, three.id)" v-for="(three, index) in threeList" :key="index"><a href="javascript:">{{three.subjectName}}</a></li>
      </ul>
    </div>
    <div class="list_content">
      <ul class="content_ul clearfix">
        <li :class="{now: fourNow == 3}" @click="changeFour(3)"><a href="javascript:">全部</a></li>
        <li :class="{now: fourNow == 2}" @click="changeFour(2)"><a href="javascript:">付费</a></li>
        <li :class="{now: fourNow == 1}" @click="changeFour(1)"><a href="javascript:">免费</a></li>
      </ul>
    </div>
    <div class="list_content">
      <ul class="content_ul clearfix">
        <li :class="{now: fiveNow == 5}" @click="changeFive(5)"><a href="javascript:">全部</a></li>
        <li :class="{now: fiveNow == 0}" @click="changeFive(0)"><a href="javascript:">点播</a></li>
        <li :class="{now: fiveNow == 1}" @click="changeFive(1)"><a href="javascript:">直播</a></li>
        <li :class="{now: fiveNow == 2}" @click="changeFive(2)"><a href="javascript:">直播+录播</a></li>
      </ul>
    </div>
    <div class="list_content">
      <input type="text" v-model="courseName" style="line-height: 20px;width: 500px;margin: 10px 0px;padding: 5px 10px;" placeholder="请输入课程名称">
      <div class="search_box" @click="searchCourse">搜索</div>
    </div>
  </div>
</template>
<script>
import {courseClass} from '~/api/course.js'
export default {
  props: {
    fourNow: {
      default: 3
    },
    courseType: {
      default: ''
    },
    classList: {
      type: [Array, Object],
      default () {
        return []
      }
    }
  },
  data () {
    return {
      webInfo: this.$store.state.webInfo,
      twoList: [],
      threeList: [],
      oneNow: 0,
      twoNow: 0,
      threeNow: 0,
      fourNow1: 3,
      fiveNow: 5,
      courseName: '',
      subjectObj: {
        subjectNo1: '',
        subjectNo2: '',
        subjectNo3: '',
        isFree: ''
      }
    }
  },
  methods: {
    changeOne (twoList, oneNow, subjectNo1) {
      if (subjectNo1) {
        this.oneNow = subjectNo1
      } else {
        this.oneNow = 0
      }
      if (twoList) {
        this.twoList = twoList
      } else {
        this.twoList = []
      }
      this.subjectObj.subjectNo2 = ''
      this.subjectObj.subjectNo3 = ''
      this.twoNow = 0
      this.threeNow = 0
      this.threeList = []
      this.subjectObj.subjectNo1 = subjectNo1
      this.goCourseList()
    },
    changeTwo (threeList, twoNow, subjectNo2) {
      if (subjectNo2) {
        this.twoNow = subjectNo2
      } else {
        this.twoNow = 0
      }
      if (threeList) {
        this.threeList = threeList
      } else {
        this.threeList = []
      }
      this.subjectObj.subjectNo3 = ''
      this.threeNow = 0
      this.subjectObj.subjectNo2 = subjectNo2
      this.goCourseList()
    },
    changeThree (threeNow, subjectNo3) {
      if (subjectNo3) {
        this.threeNow = subjectNo3
      } else {
        this.threeNow = 0
      }
      this.subjectObj.subjectNo3 = subjectNo3
      this.goCourseList()
    },
    changeFour (fourNow) {
      console.log(fourNow)
      this.fourNow1 = fourNow
      this.goCourseList()
    },
    changeFive (fiveNow) {
      this.fiveNow = fiveNow
      this.goCourseList()
    },
     searchCourse(){
         this.goCourseList()
     },
    goCourseList () {
      let that = this
      let subjectObj1 = this.subjectObj
        let nowTime = new Date().getTime();
      //if (this.courseType === 'course') {
      //  this.$router.push({name: 'list', query: {subjectno1: subjectObj1.subjectNo1, subjectno2: subjectObj1.subjectNo2, subjectno3: subjectObj1.subjectNo3, four: that.fourNow1}})
      //} else if (this.courseType === 'live') {
       // this.$router.push({name: 'live', query: {subjectno1: subjectObj1.subjectNo1, subjectno2: subjectObj1.subjectNo2, subjectno3: subjectObj1.subjectNo3, four: that.fourNow1, courseSubject: that.fiveNow}})
        this.$router.push({name: 'list', query: {subjectno1: subjectObj1.subjectNo1, subjectno2: subjectObj1.subjectNo2, subjectno3: subjectObj1.subjectNo3, four: that.fourNow1, courseType: that.fiveNow,courseName:that.courseName,timeStamp:nowTime}})

        //}
    },
    getCourseClass () {
      courseClass({
        subjectType: 5,
        orgNo: this.clientData.no
      }).then(res => {
        console.log(res)
        if (res.code === 200 && res.data.list.length > 0) {
          this.classList = res.data.list
          console.log(this.classList)
          this.getNextClass()
        } else {
          this.classList = []
        }
      }).catch(msg => {
        console.log(msg)
      })
    },
    getNextClass () {
      if (this.$route.query.subjectno1) {
        for (let i = 0; i < this.classList.length; i++) {
          if (this.classList[i].id === this.$route.query.subjectno1) {
            this.oneNow = this.$route.query.subjectno1
            if (this.classList[i].twoList) {
              this.twoList = this.classList[i].twoList
              this.getThreeClass()
            }
          }
        }
      }
    },
    getThreeClass () {
      if (this.$route.query.subjectno2) {
        for (let i = 0; i < this.twoList.length; i++) {
          if (this.twoList[i].id === this.$route.query.subjectno2) {
            this.twoNow = this.$route.query.subjectno2
            if (this.twoList[i].threeList) {
              this.threeList = this.twoList[i].threeList
              if (this.$route.query.subjectno3) {
                this.threeNow = this.$route.query.subjectno3
              }
            }
          }
        }
      }
    }
  },
  mounted () {
    if (this.$route.query.subjectno1 || this.$route.query.subjectno1 === '') {
      this.subjectObj.subjectNo1 = this.$route.query.subjectno1
    }
    if (this.$route.query.subjectno2 || this.$route.query.subjectno2 === '') {
      this.subjectObj.subjectNo2 = this.$route.query.subjectno2
    }
    if (this.$route.query.subjectno3 || this.$route.query.subjectno3 === '') {
      this.subjectObj.subjectNo3 = this.$route.query.subjectno3
    }
    if (this.$route.query.four) {
      this.fourNow1 = this.$route.query.four
    }
    if (this.$route.query.courseType) {
      this.fiveNow = this.$route.query.courseType
    } else {
      this.fiveNow = 5
    }
    this.getNextClass()

  }
}
</script>
<style lang="scss" rel="stylesheet/scss" scoped>
  .header_list {
    border-top: 1px solid rgb(215, 215, 215);
    background: #fff;
    .list_content {
      width: 1200px;
      margin: 0 auto;
      height: 73px;
      border-bottom: 1px solid rgb(215, 215, 215);
      position: relative;
      &:last-child {
        border-bottom: none;
      }
      span {
        position: absolute;
        display: inline-block;
        font-size: 14px;
        font-weight: bold;
        height: 26px;
        line-height: 26px;
        top: 23px;
      }
    }
  }
  .content_ul {
    display: inline-block;
    // margin-left: 60px;
    margin-top: 23px;
    li {
      float: left;
      font-size: 14px;
      height: 26px;
      line-height: 26px;
      padding: 0 6px;
      border-radius: 0px;
      margin: 0 6px;
      a:hover {
        color: blue;
        text-decoration: none;
      }
      &.now {
        background: blue;
        a {
          color: #fff;
        }
      }
    }
  }
  .search_box {
    background-color: blue;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    line-height: 30px;
    margin: 10px 0px;
    cursor: pointer;
    width: 200px;
  }
</style>

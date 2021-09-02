<template>
  <div class="course_detail_des">
    <div class="course_detail_des_d">
      <p class="course_name_d">{{this.courseDetail.courseName}}</p>
      <p class="course_detail_price">
        <span class="zhe_kou_price"><i>折扣价¥</i>{{this.courseDetail.courseDiscount}}元{{this.courseDetail.costTimeType | costTimeTypeChange}}</span>
        <span class="original_price"><i>原价¥</i>{{this.courseDetail.courseOriginal}}元{{this.courseDetail.costTimeType | costTimeTypeChange}}</span>
        <br/>
        <span class="study_count"  v-if="this.courseDetail.studyCount>=100">【{{this.courseDetail.studyCount}}】</span>
      </p>
      <div>
        <span v-if="continueBuy==true&&longTerm==false&&courseDetail.isPutaway==1&&isLogin==true">有效期至{{courseDetail.validTerm}}</span>
        <a class="btn_box" @click="toOrder(courseDetail.id)"  :class="{disabled: true}" v-if="continueBuy==false&&longTerm==false&&courseDetail.isPutaway==1&&isLogin==true" >立即购买</a>
        <a class="btn_box" @click="toOrder(courseDetail.id)" :class="{disabled: true}" v-if="continueBuy==true&&longTerm==false&&courseDetail.isPutaway==1&&isLogin==true">续期</a>
        <a class="btn_box" @click="toOrder(courseDetail.id)" :class="{disabled: true}" v-if="isLogin==false">立即登录</a>
      </div>
      <p v-if="lessonName" class="course_detail_price">
        <span style="font-size: 10px;color: #333333">正在播放课时：{{lessonName}}</span>
      </p>
    </div>
  </div>
</template>
<script>
export default {
    props: ['playvideo','courseDetail'],
    data(){
      return{
          lessonName: '',
          longTerm: false,
          continueBuy: false,
          isPay: false,
          isLogin: true
      }
    },
    methods: {
        updateLessonName(lessonName){
            this.lessonName = lessonName;
        },
        toOrder(id){
            this.$router.push({path: '/order/order', query: {"id":id}})

        }
    },
    computed: {

    },
    filters:{
        conFilter(item) {
            return "con_"+item;
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
        }

    },
    watch: {
        courseDetail: function (value) {
            if(value.isPay==1){
                if(value.validTerm=='9999-12-31 23:59'){
                    this.longTerm = true;
                }
                this.continueBuy = true;
                this.isPay = true;
            }
            console.log("this.longTerm:"+this.longTerm);
            console.log("this.continueBuy:"+this.continueBuy);
            console.log("this.isPay:"+this.isPay);

        }
    }
}
</script>
<style lang="scss" rel="stylesheet/scss">
.course_detail_des {
  background-color: #fff;
}
.course_detail_des	.course_detail_des_d {
  position: relative;
  padding-left: 15px;
}
.course_detail_des	.course_detail_des_d .course_name_d {
  color: grey;
  font-size: 1.5rem;
  padding: 6px;
  //max-width: 100%
  text-overflow: ellipsis;
  overflow: hidden;
  //white-space: nowrap
}
.course_detail_des	.course_detail_des_d 		.course_detail_price {
  padding: 6px 0 5px 0;
}
.course_detail_des	.course_detail_des_d 		.course_detail_price			span {
  &.zhe_kou_price {
    color: red;
    font-size: 1.7rem;
  }
}
.course_detail_des	.course_detail_des_d .course_detail_price	i{
  font-size: 1.1rem;
  margin-right: 3px;
  font-weight: bold;
}
.course_detail_des	.course_detail_des_d .course_detail_price .original_price{
  color: lightgrey;
  text-decoration: line-through;
  padding-left: 5px;
}
.course_detail_des	.course_detail_des_d img {
  padding-right: 10px;
}
.btn_box {

  border-radius: 3px;
  width: 100px;
  background-color: orangered;
  border: none;
  color: white;
  padding: 5px 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 12px;
  //margin: 4px 2px;
  margin-left: 60%;
  margin-bottom: 4px;
  cursor: pointer;
}
.study_count {
  margin-left: 0px;
  font-weight: bold;
  &:before {
    content: '学习人数';
    display: inline-block;
    width: 55px;
    height: 16px;
    margin-left: 0px;
    line-height: 17px;
    background: green;
    font-size: 1rem;
    color: #fff;
    border-radius: 3px;
    text-align: center;
    position: relative;
    top: 0px;
  }
}
</style>

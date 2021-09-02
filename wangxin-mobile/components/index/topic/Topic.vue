<template>
  <div class="container pad_l_r_5 white_bgc bor_t_b">
		<div class="cls_lei">
			<TopicHeader :headinfo="parentHeadinfo.headinfo"></TopicHeader>
			<ul class="cls_list">
				<li v-for="(value, index) in parentHeadinfo.course.classList" :key="index" :data-id="value.coueseGradeId">
					<nuxt-link :to="{path: 'courseDetail/index', query: {id: value.courseId}}" class="con">
						<img :src=value.imgurl alt="" />
            <div class="topic_con_txt_2" v-if="value.courseType===1"><span style="font-weight:bold">直播</span></div>
            <div class="topic_con_txt_2" v-if="value.courseType===2"><span style="font-weight:bold">点播+直播</span></div>
            <div class="topic_con_txt">{{value.classname}}</div>
          </nuxt-link>
          <div style="text-align: center;font-size: 14px;color: #FF5151" v-if="value.isFree===1"><span style="font-weight:bold">免费</span></div>
          <div style="text-align: center;font-size: 14px;color: #2828FF" v-if="value.isFree===0 && value.courseOriginal===value.courseDiscount"><span style="font-weight:bold">{{value.courseOriginal}}元{{value.costTimeType | costTimeTypeChange}}</span></div>
          <div style="text-align: center;font-size: 14px;color: #2828FF" v-if="value.isFree===0 && value.courseOriginal > value.courseDiscount"><span style="font-size:10px;text-decoration:line-through">原{{value.courseOriginal}}元</span><span style="font-weight:bold;;color: #FF5151">现{{value.courseDiscount}}元{{value.costTimeType | costTimeTypeChange}}</span></div>
          <div style="text-align: center;font-size: 14px;color: #2828FF" v-if="value.isFree===0 && value.courseOriginal < value.courseDiscount"><span style="font-weight:bold">{{value.courseDiscount}}元{{value.costTimeType | costTimeTypeChange}}</span></div>
				  <div><br/></div>
        </li>
			</ul>
			<!-- 更多课程 -->
			<ul class="cls_more_class">
				<li v-for="(value,index) in parentHeadinfo.course.classMoreList" :key="index" :data-id="value.coueseGradeId">
					<nuxt-link to="courseAllList">
						<p><img :src=value.imgurl style="position: absolute; top:3px;" alt="" /></p>
						<span>{{value.classname}}</span>
					</nuxt-link>
				</li>
			</ul>
		</div>
	</div>
</template>
<script>
import TopicHeader from '~/components/index/topic/TopicHeader.vue'
export default {
  props: ['parentHeadinfo'],
  components: {
      TopicHeader
  },
  computed: {

  },
  methods: {

  },
    filters:{
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
}
</script>
<style lang="sass" scoped>
  .cls_lei
    padding: 0 0 10px 0
    margin-top: 1rem
    .cls_list
      overflow: hidden
      li
        width: 50%
        float: left
        padding: 0 5px 0 0
        border: red 0px solid
        .topic_con_txt
          width: 100%
          height: 2.3rem
          font-size: 1.2rem
          line-height: 2.3rem
          text-align: center
          color: #fff
          background-color: rgba(0,0,0,0.4)
          position: absolute
          left: 0
          bottom: 0
        .topic_con_txt_2
          width: 100%
          height: 2.3rem
          font-size: 1.2rem
          line-height: 2.3rem
          text-align: center
          color: #fff
          background-color: rgba(0,0,0,0.3)
          position: absolute
          left: 0
          bottom: 2.4rem
        .con
          display: block
          overflow: hidden
          border: 1px solid #eee
          position: relative
          border-radius: 8px
          .topic_con_txt_2
            display: inline-block
            width: 100%
            text-align: center
          .topic_con_txt
            display: inline-block
            width: 100%
            text-align: center
            border-bottom-left-radius: 8px
            border-bottom-right-radius: 8px
          img
            width: 100%
            height: 120px
            border-radius: 8px
        &.fr
          padding: 0 0 0 5px
        .time_dao
          margin-top: 5px
          p
            text-align: center
    .cls_more_class
      border-top: 1px solid #eee
      border-ledt: 1px solid #eee
      overflow: hidden
      margin: 8px 0 0 0
      li
        width: 50%
        float: left
        a
          position: relative
          height: 49px
          display: block
          font-size: 12px
          color: #666
          overflow: hidden
          border-bottom: 1px solid #eee
          border-right: 1px solid #eee
          span
            display: block
            margin: 0 80px 0 0
            line-height: 50px
            font-size: 1.3rem
            color: #333
            text-align: center
          p
            width: 26px
            margin-top: 5px
            float: right
            position: absolute
            top: 5px
            right: 28px
            img
              width: 100%
              position: absolute
              top: 5px
              right: 0px
</style>

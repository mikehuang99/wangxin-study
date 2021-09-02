<template>
  <div>
    <Topic :parentHeadinfo="value" :key=index v-for="(value, index) in allparentinfo"></Topic>
  </div>
</template>
<script>
import Topic from '~/components/index/topic/Topic.vue'
import {findTopics} from '~/api/index'

export default {
  //props: ['allparentinfo'],
    data () {
        return {
            activeIndex: 0,
            intnum: undefined,
            newsList: [],
            allparentinfo: []
        }
    },
  components: {
      Topic
  },
    mounted(){
        this.findTopics();
    },
    methods: {
        findTopics()
        {
            findTopics().then(res => {
                if (res.data.code === 200) {
                   // this.allparentinfo = res.data.data.list || [];
                    let topicData = res.data.data.list;
                    let topicList = [];
                    for(let i=0; i< topicData.length; i++){
                        let classList = [];
                        for(let n=0;n < topicData[i].topicCourseList.length;n++){
                            classList.push({
                                "classname": topicData[i].topicCourseList[n].courseName,
                                "imgurl": topicData[i].topicCourseList[n].courseLogo,
                                "coueseGradeId": topicData[i].topicCourseList[n].id,
                                "isFree": topicData[i].topicCourseList[n].isFree,
                                "courseOriginal": topicData[i].topicCourseList[n].courseOriginal,
                                "courseDiscount": topicData[i].topicCourseList[n].courseDiscount,
                                "costTimeType": topicData[i].topicCourseList[n].costTimeType,
                                "courseType": topicData[i].topicCourseList[n].courseType,
                                "courseId": topicData[i].topicCourseList[n].courseId
                            });
                        }
                        topicList.push({
                            "headinfo": {
                                "before": "更多课程",
                                "after": topicData[i].topicName,
                                "imgurl": "img/icon_middle.png"
                            },
                            "course": {
                                "classList": classList
                            }
                        })
                    }

                    this.allparentinfo = topicList;
                } else {
                    console.log('88999！')
                }
            });
        }

    }
}
</script>

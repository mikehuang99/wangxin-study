<template>
  <div class="popup_order_study_card_contain">
    <div class="popup_order_study_card_content">
      <div class="popup_order_study_card_title">学习卡</div>
      <div v-for="(item, indexSc) in studyCardList" v-bind:key="indexSc" @click="selectStudyCard(item,1)">
        <div class="popup_order_study_card_item">
        {{item.cardTitle | cutTile}}<br/>
         <span style="font-size: 12px;font-weight: bold">
        剩余面额：{{(item.totalFaceValueAmount-item.usedFaceValueAmount-item.frozenFaceValueAmount) | toFixed2}}元
         </span><br/>
        [有效期:{{item.studyCardTimeBegin}}至{{item.studyCardTimeEnd}}]
          <span class="popup_order_study_card_item_had_selected" v-if="item.id==selectedStudyCardId" style="position: relative;float:right;border:1px solid orangered;background-color: orangered;color: white;">已选择√</span>
        </div>
        <div style="height: 10px">

        </div>
      </div>
      <div v-if="studyCardList.length==0">
        <div style="margin-top: 50px;"></div>
        <div style="font-size: 20px">没有学习卡</div>
      </div>
    </div>
    <div style="margin-top: 20px">
      <span class="btn_box_ok" @click="closePopupOrderStudyCardOk">关闭</span>
      <!--
      <span class="btn_box_cancel" @click="closePopupOrderStudyCardCancel">取消</span>-->
    </div>
  </div>
</template>

<script>
    import {listUserStudyCard} from '~/api/market'
    import { logout } from '~/store/common'

    export default {
        props: {
            courseId: {
                type: [Object, String],
                default: ''
            },
            selectedStudyCardId2: {
                type: [Object, String],
                default: ''
            }
        },
        name: "orderStudyCard",
        data () {
            return {
                studyCardList: [],
                studyCardSize: '',
                selectedStudyCardId: this.selectedStudyCardId2
            }
        },
        filters: {
            cutTile(value) {
                if (!value) return ''
                if (value.length > 14) {
                    return value.slice(0, 14) + '...'
                }
                return value
            },
            toFixed2(value){
                if(typeof(value)=='undefined'){
                    return '数据错误，请重新尝试';
                }
                return Number(value.toFixed(2));
            }
        },
        mounted(){
            this.listUserStudyCard();
        },
        methods: {
            selectStudyCard(item,cardType){
                if(this.selectedStudyCardId==item.id){
                    this.selectedStudyCardId = '';
                }else {
                    this.selectedStudyCardId = item.id;
                }
                this.$parent.selectStudyCard(item,cardType);
            },
            listUserStudyCard(){
                listUserStudyCard({"courseId":this.courseId}).then(res => {
                    res = res.data;
                    if (res.code === 200 ) {
                        //this.courseDetail = res.data;
                        this.studyCardList = res.data.list || [];
                        this.studyCardSize = this.studyCardList.length;
                    } else if (res.code > 300 && res.code < 400) {
                        if(res.code === 304){
                            console.log("该账号被异地登录");
                            logout();
                            this.$router.push({path: '/login', query: {}});
                        }else {
                            console.log("登录超时、异常或者被异地登录，请重新登录")
                            logout();
                            this.$router.push({path: '/login', query: {}});
                        }
                    }else{
                        console.log("系统错误");
                    }
                }).catch(error => {
                    //reject(error)
                    console.log(error);
                })
            },
            closePopupOrderStudyCardOk(){
                    this.$emit('closePopupOrderStudyCard');
            },
            closePopupOrderStudyCardCancel(){
                this.$emit('closePopupOrderStudyCard');
            }
        }
    }
</script>

<style scoped>
  .popup_order_study_card_contain{
    border-radius: 20px;
    background-color: lightgrey;
    width: 100%;
    height: 400px;
    position: fixed;
    left: 0px;
    top: 10%;
    overflow-y: auto;
    padding-left: 17px;
    padding-right: 17px;
    z-index: 9999999999;
  }
  .popup_order_study_card_content{
    margin-top: 20px;
    text-align: center;
    height: 300px;
    overflow-y: auto;
    border: solid 1px orangered;
    border-radius: 8px;
  }
  .popup_order_study_card_title{
    font-weight: bold;font-size: 18px;
  }
  .popup_order_study_card_item{
    border: solid 1px red;
    border-radius:5px;
    background-color:greenyellow;
    padding-bottom: 20px;
  }
  .btn_box_ok {
    border-radius: 20px;
    width: 100px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    cursor: pointer;
    float: left;
  }
  .btn_box_cancel {
    border-radius: 20px;
    width: 100px;
    background-color: orangered;
    border: none;
    color: white;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    cursor: pointer;
    float: right;
  }
</style>

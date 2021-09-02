<template>
  <div class="c_detail" style="margin-top: 44px;background-color: #fff">
    <div class="class_pay">
      <p class="pad_p" >
        <span class="price_sp">{{this.courseDetail.courseName}}</span>
      </p>
    </div>
  </div>
</template>
<script>
import { mapState } from 'vuex'
export default {
    props: ['courseDetail'],
    data(){
      return{
      }
    },
  computed: {

  }
}
</script>
<style lang="sass" scoped>
@import '~static/css/style.sass'
.c_detail
	background-color: #fff
	.class_pay
		position: relative
		padding-left: 15px
		.pad_p
			padding: 6px 0 5px 0
			span
				&.price_sp
					color: red
					font-size: 1.7rem
					i
						font-size: 1.1rem
						margin-right: 3px
						font-weight: bold
				&.price_original
					color: grey
					text-decoration: line-through
					padding-left: 5px
</style>

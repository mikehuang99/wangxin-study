 <template>
	<div class="loading_flower_container">
    <svg class="load_flower" xmlns="http://www.w3.org/2000/svg" version="1.1">
      <ellipse cx="40" cy="15" rx="30" ry="15"
               style="stroke:#006600; fill:#00cc00"/>
		</svg>
	</div>
</template>

<script>
export default {
  data () {
    return {
      py: 0,
      loadTimer: null
    }
  },
  mounted () {
    this.loadTimer = setInterval(() => {
      this.py = this.py + 1;
    }, 1200)
  },
  beforeDestroy () {
    clearInterval(this.loadTimer)
  }
}
</script>

<style lang="scss" scoped>
	@keyframes load{
		0%   {
      transform: translateY(0px);
    }
		50%  {transform: translateY(-50px);}
		100% {transform: translateY(0px);}
	}
  @-o-keyframes ellipse{
    0%   {transform: scale(1);}
    50%  {transform: scale(0.3);}
    100% {transform: scale(1);}
  }
  @-ms-keyframes ellipse{
    0%   {transform: scale(1);}
    50%  {transform: scale(0.3);}
    100% {transform: scale(1);}
  }
  @-webkit-keyframes ellipse{
    0%   {transform: scale(1);}
    50%  {transform: scale(0.3);}
    100% {transform: scale(1);}
  }
  @keyframes ellipse{
		0%   {transform: scale(1);}
		50%  {transform: scale(0.3);}
		100% {transform: scale(1);}
	}
	.loading_flower_container{
		position: fixed;
    -webkit-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
		top: 50%;
		left: 50%;
    width: 5rem;
    height: 4rem;
	}
	.load_flower{
		position: absolute;
    width: 20rem;
    height: 20rem;
		z-index: 2000000;
    top: 2.2rem;
    left: 0.11rem;
		animation: ellipse .6s infinite ease-in-out;
	}
</style>

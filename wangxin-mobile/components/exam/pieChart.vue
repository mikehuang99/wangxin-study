<template>
  <canvas :id="canvasId" :width="width" :height="height"></canvas>

</template>

<script>
    export default {
        name: "pieChart",
        data(){
            return {
                canvas: '',
                ctx: '',
                totalValue: ''
            }
        },
        props: {
            canvasId: {
                type: String,
                default: ''
            },
            width: {
                type: [String, Number],
                default: ''
            },
            height: {
                type: [String, Number],
                default: ''
            },
            type: {
                type: String,
                default: 'ring' // 圆环ring 柱状图bar 折线图 line 饼状pie
            },
            data: { // 存放图表数据
                type: Array,
                default: function () {return []}
            },
            options: {
                type: Object,
                required: false
            }
        },
        mounted () {
            // this.wxChart(this.canvasId, this.type, this.data, this.options)
        },
        methods:{
            reDraw(){
                this.wxChart(this.canvasId, this.type, this.data, this.options)
            },
            wxChart (canvas, type, data, options) {
                this.canvas = document.getElementById(canvas)
                this.ctx = this.canvas.getContext('2d')
                this.type = type
                this.data = data // 存放图表数据
                this.dataLength = this.data.length // 图表数据的长度
                this.totalValue = this.getTotalValue() // 获取饼图数据总和
                this.init(options)
            },
            init: function (options) {
                if (this.dataLength === 0) {
                    return false
                }
                if (options) {
                    for (let key in options) {
                        if (key === 'colorList' && Array.isArray(options[key])) {
                            this[key] = options[key].concat(this[key])
                        } else {
                            this[key] = options[key]
                        }
                    }
                }
                this.drawPieUpdate()
            },
            /**
             * 绘制完整的饼状图或环形图
             */
            drawPieUpdate: function () {
                // 字体
                this.ctx.font = "18px bold 黑体";
                // 颜色
                this.ctx.fillStyle = "blue";
                // 水平对齐方式
                this.ctx.textAlign = "left";
                // 垂直对齐方式
                this.ctx.textBaseline = "bottom";
                // 绘制文字（参数：文字描述，x坐标，y坐标）
                let r = this.data[1]['value']/(this.data[0]['value']+this.data[1]['value']) ;
                let rn = Number(r.toFixed(2))*100;
                let rnp = Number(rn.toFixed(0));
                console.log("正确率："+rnp)
                this.ctx.fillText("正确率"+rnp+"%", this.width/2-40, this.height-5);

                this.drawPieChart()
            },
            drawPieChart: function () {
                let x = this.width / 2
                let y = this.height / 2
                for (let i = 0; i < this.dataLength; i++) {
                    this.ctx.beginPath()
                    this.ctx.fillStyle = this.colorList[i]
                    this.ctx.moveTo(x, y)
                    this.data[i].start = i === 0 ? -Math.PI / 2 : this.data[i - 1].end
                    this.data[i].end = this.data[i].start + this.data[i].value / this.totalValue * 2 * Math.PI
                    // 绘制扇形
                    this.ctx.arc(x, y, this.radius, this.data[i].start, this.data[i].end)
                    this.ctx.closePath()
                    this.ctx.fill()

                    this.data[i].middle = (this.data[i].start + this.data[i].end) / 2
                    this.ctx.strokeStyle = this.colorList[i]
                }
                // 类型是环形图，绘制一个内圆
                if (this.type === 'ring') {
                    this.ctx.beginPath()
                    this.ctx.fillStyle = this.bgColor
                    this.ctx.arc(x, y, this.innerRadius, 0, Math.PI * 2)
                    this.ctx.fill()
                }
            },
             //获取饼状或环形图的数据总和
            getTotalValue () {
                let total = 0
                for (let i = 0; i < this.dataLength; i++) {
                    total += this.data[i].value
                }
                return total
            }
        }
    }
</script>

<style scoped>

</style>

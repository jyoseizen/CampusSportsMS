
<template>
  <div class="pages" >

    <img src="static/img/bj2.jpg" ></img>

    <div class="ach">
      <div id="myCharttow" :style="{width: '800px', height: '600px'}"></div>
      <!--    <div id="myChart" :style="{width: '300px', height: '300px'}"></div>-->
    </div>
    <div class="bing">

      <div class="bin2">
        <div id="myChart2" :style="{width: '450px', height: '280px'}"></div>
      </div>

      <div class="bin">
        <div id="myChart" :style="{width: '450px', height: '280px'}"></div>
      </div>
      <!--      <div id="line"  :style="{width: '450px', height: '281px'}"></div>-->

    </div>

  </div>
</template>

<script>
import {EgetRankingAPI, EgetEcharts2API, EgetSortAPI} from '@/request/api';


export default {

  name: 'College_integrate',
  data() {
    return {
      tableData: [],
      data2: [],

      //排行榜数据
      collegeName: '',
      rankingIntegral: '',

      //柱状图数据
      name: [],
      num: [],
      msg: 'Welcome to Your Vue.js App'
    }
  },
  created() {

    this.getRanking();  //排行榜数据
    this.getSort();   //右下饼图数据

  },
  mounted() {

    this.rankingLine();  //排行榜柱状图
    this.rankingLine1();
    this.rankingLine2()

  },
  methods: {

    //右上饼图
    rankingLine2() {
      let myChart2 = this.$echarts.init(document.getElementById('myChart2'))

      // 绘制图表
      let options1 = {
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical', //左上角属性排列
          left: 'left' //属性位置
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: [],
          }
        ]
      };
      EgetEcharts2API().then(res => {
        console.log("饼图==》",res)
        this.gradeInfo = res.data;
        res.data.forEach((item) => {
          options1.series[0].data.push({
            // if(series[0].data.usersSex == 0){
            //
            // }
            name: item.collegeName,
            value: item.num
          })
        });
        myChart2.setOption(options1)
      })
    },

    //右下饼图
    rankingLine1() {
      let myChart = this.$echarts.init(document.getElementById('myChart'))

      // 绘制图表
      let options = {
        title: {
          // text: 'Referer of a Website',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          // orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            // radius: '50%',
            data: [],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };

      EgetSortAPI().then(res => {
        console.log("饼图==》",res)
        this.gradeInfo = res.data;
        res.data.forEach((item) => {
          options.series[0].data.push({
            // if(series[0].data.usersSex == 0){
            //
            // }
            name: item.usersSex,
            value: item.sum
          })
        });
        myChart.setOption(options)
      })

    },

    //积分排行榜
    rankingLine() {
      let myCharttow = this.$echarts.init(document.getElementById('myCharttow'))

      // 绘制图表
      myCharttow.setOption({
        title: {text: '积分排行详情'},
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },

        legend: {},

        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },

        //x轴
        xAxis: {
          type: 'value',
          // data: this.name,
          // name:'学院',
          // // nameLocation : 'end',
          // axisTick: {
          //   color:'blue',
          //   width:300
          // },
        },

        //y轴
        yAxis: {
          type: 'category',
          data: this.collegeName
        },
        series: [{
          name: '积分',
          type: 'bar',
          data: this.rankingIntegral,
        }]
      });
    },

    // 排行榜数据
    getRanking() {
      EgetRankingAPI().then(res => {
        console.log("积分数据==》", res.data)
        this.collegeName = res.data.collegeName
        this.rankingIntegral = res.data.rankingIntegral
        this.rankingLine();
      })
    },

  }
}
</script>

<style scoped>
*{
  margin: 0px;
  padding: 0px;
}
.pages {
  /*上 右 下 左*/
  position: relative;
  display: flex;
  box-shadow: 0 0 5px #888888;

}


.ach {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 800px;
  height: 575px;
  z-index: 999;
  margin-top:20px;
  margin-left: 20px;
  /*border: 1px solid #921b1b*/
  /*padding: 35px*/
}

.bing {
  position: absolute;
  left: 800px;
  width: 450px;
  height: 575px;
  /*border: 1px solid #921b1b;*/
  z-index: 999;
  /*margin-left: 20px;*/
  margin-top:20px;
  margin-left: 30px;
}

.bin {
  width: 100%;
  height: 49%;
  /*border: 1px solid #921b1b;*/
  margin-top: 20px;
}

.bin2 {
  width: 100%;
  height: 49%;
  /*margin-top:20px;*/
  /*border: 1px solid #921b1b;*/

}

/*四个分格*/
.el-row {
  margin-bottom: 20px;

&
:last-child {
  margin-bottom: 0;
}

}
.el-col {
  border-radius: 4px;

}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

/*高度*/
.grid-content {
  border-radius: 4px;
  min-height: 100px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.btn2 {
  width: 250px;
  height: 100px;
  box-shadow: 0 0 3px #888888;
  /*background-color: #C2E8FF;*/

}

.imgs {
  width: 80px;
  height: 80px;
  /*margin-left: 1px;*/
  padding: 10px;
  /*border: 1px solid red;*/
  float: left;
  background-color: #C2E8FF;
}

.imgs2 {
  width: 80px;
  height: 80px;
  /*margin-left: 1px;*/
  padding: 10px;
  /*border: 1px solid red;*/
  float: left;
  background-color: #FFE4E1;
}

.imgs3 {
  width: 80px;
  height: 80px;
  /*margin-left: 1px;*/
  padding: 10px;
  /*border: 1px solid red;*/
  float: left;
  background-color: #BCD2EE;
}

img{
    width: 84.8vw;
    height: 93vh;
    z-index: -9999;
  opacity:0.7;
}
</style>


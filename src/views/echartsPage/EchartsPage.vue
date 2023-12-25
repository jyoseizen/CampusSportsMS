<template>
  <div class="page">
    <div>
      <el-row :gutter="5">
        <el-col :span="6">
          <div>
            <button type="button" class="btn" @click="goUmanage">立即报名</button>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="btn2">
            <div class="imgs"><img src="static/img/icons8-人群-80.png"></div>
            <div style="padding-top: 10px;font-size:20px;color:#8C8C8C;">报名总人数:</div>
            <div style="padding-top: 10px;font-size:30px;color:#8C8C8C;">{{ massage }}人</div>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="btn2">
            <div class="imgs2"><img src="static/img/icons8-女商人-80.png"></div>
            <div style="padding-top: 10px;font-size:20px;color:#8C8C8C;">报名女生数:</div>
            <div style="padding-top: 10px;font-size:30px;color:#8C8C8C;">{{ massage2 }}人</div>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="btn2">
            <div class="imgs3"><img src="static/img/icons8-商人-80.png"></div>
            <div style="padding-top: 10px;font-size:20px;color:#8C8C8C;">报名男生数:</div>
            <div style="padding-top: 10px;font-size:30px;color:#8C8C8C;">{{ massage3 }}人</div>
          </div>
        </el-col>
        <!--        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>-->
      </el-row>

      <!--      <el-row>-->
      <!--        <button type="button" class="btn">立即报名</button>-->
      <!--      </el-row>-->
    </div>

    <div class="ech">
      <div id="myChart" :style="{width: '1255px', height: '500px'}"></div>
      <!--    <div id="myChart" :style="{width: '300px', height: '300px'}"></div>-->
    </div>

  </div>
</template>

<script>
import {EgetGuantityAPI, EgetEchartsAPI,EselectList} from '@/request/api';
// export default {
//   name: "EchartsPage"
// }
export default {

  name: 'EchartsPage',
  data() {
    return {
      tableData: [],
      massage: '',
      massage2: '',
      massage3: '',

      //柱状图数据
      name: [],
      num: [],
      msg: 'Welcome to Your Vue.js App'
    }
  },
  created() {
    this.selectList();
    this.getGuantity();
    this.getEcharts();
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))

      // 绘制图表
      myChart.setOption({
        title: {text: '各学院报名详情'},
        tooltip: {},
        legend:{
          data:["人数"]
        },

        //x轴
        xAxis: {
          data: this.name,
          name:'学院',
          // nameLocation : 'end',
          axisTick: {
            color:'blue',
            width:300
          },
        },

        //y
        yAxis: {
          name: '人数'
        },
        series: [{
          name: '销量',
          type: 'bar',
          data: this.num,
        }]
      });
    },

    //更新报名表
    selectList(){
        EselectList({
          // params,
        }).then(res => {
          console.log("更新数据")
        })
    },

    //报名人数
    getGuantity() {
      EgetGuantityAPI().then(res => {
        console.log('人数===》', res.data)
        console.log('token', window.sessionStorage.getItem('token'))
        this.massage = res.data.num
        this.massage2 = res.data.girl
        this.massage3 = res.data.boy
      })
    },

    //柱状图数据
    getEcharts() {
      EgetEchartsAPI().then(res => {
        console.log("图标数据===》", res.data);

        this.name = res.data.name
        this.num = res.data.num
        this.drawLine()
      })
    },

    goUmanage(){

      this.$router.replace('/uManage')
    },

  }
}
</script>

<style scoped>

.page {
  /*上 右 下 左*/
  padding: 10px 10px 10px 10px;
  margin: 3px 0px 0px 3px;
  /*box-shadow: 0 5px 15px rgba(0,0,0,.8);*/
  /*background-color:#ff9900;*/
  /*-moz-box-shadow: 10px 10px 5px #888888; !* 老的 Firefox *!*/
  box-shadow: 0 0 5px #888888;
  /*border:1px solid #fff*/
  /*border:1px dashed #888888*/
  /*box-shadow: 0 5px 15px*/
}

.btn {
  width: 220px;
  height: 100px;
  margin-left: 35px;
  border-radius: 40px;
  background-color: #5CACEE;
  border: 1px solid #fff;
  /*box-shadow: 0 5px 15px rgba(0,0,0,.8);*/
  display: flex;
  justify-content: center;

  /*文字*/
  color: white;
  font-size: 35px;
  font-weight: 700;
  line-height: 100px;
}

.ech {
  width: 1255px;
  height: 500px;
  /*padding: 35px*/
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

</style>

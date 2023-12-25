<template>
  <div class="ac">
    <el-row :gutter="20" class="el-row" type="flex">
      <el-col :span="8" v-for="(item,index) in manages" :key="item.id" class="el-col">
        <el-card class="box" :key="index" onclick="">
          <!--        <div class="box">-->
          <div slot="header" class="clearfix">
            <span>{{ item.competitionNe }}</span></div>
          <div>
            <div class="text item">
              <div class="item_tag"><span>地点：</span></div>
              <div class="item_desr"><span>{{ item.competitionSite }}</span></div>
            </div>
            <div class="text item">
              <div class="item_tag"><span>报名开始时间：</span></div>
              <div class="item_desr">{{ item.applyStart }}</div>
            </div>
            <div class="text item">
              <div class="item_tag"><span>报名结束时间：</span></div>
              <div class="item_desr"><span>{{ item.applyEnd }}</span></div>
            </div>
            <div class="text item">
              <div class="item_tag"><span>比赛开始时间：</span></div>
              <div class="item_desr">{{ item.competitionStart }}</div>
            </div>
            <div class="text item">
              <div class="item_tag"><span>剩余参赛人数：</span></div>
              <div class="item_desr">{{ item.competitionTnor }}</div>
            </div>
            <template >
              <div><el-button :type="item.applyEnd < time ?'info':'primary'"
                              @click="apply(item)"
                              :disabled="item.applyEnd < time ? true:false "
              >{{item.applyEnd > time? '立即报名':'不可报名'}}</el-button></div>
            </template>

          </div>
          <!--        </div>-->
        </el-card>
      </el-col>
    </el-row>
    <!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[6, 12, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import {MgetLisetAPI, ApplyAPI} from '@/request/api';

export default {
  data() {
    return {
      //分页
      currentPage: 1,
      pageSize: 6,
      total: 0,

      time: '',

      manages: [],
      competitionNb: '',
      usersStnumber: '',

    }
  },

  created() {

    this.getList()
    this.getDate()
  },

  methods: {
    //分页查询
    getList() {
      //查询
      MgetLisetAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log('=====>', res)
        this.manages = res.data.data.records
        this.competitionNb = res.data.data.records[0].competitionNb
        //比赛条数
        this.total = res.data.data.total
      })
    },

    //报名按钮
    apply(item) {
      this.$confirm('确定要报名该比赛项目？', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(() => {
          ApplyAPI({
            params: { competitionNb: item.competitionNb,
                      usersStnumber: window.sessionStorage.getItem('id')}
          }).then(res => {
            if (res.data.code == "200") {
              this.$message({
                type: 'success',
                message: '报名成功'
              });} else {
              this.$message({
                type: 'error',
                message: '报名失败'
              });}
            this.getList()})
        })
        .catch(action => {
          this.$message({
            type: 'error',
            message: action === 'cancel'
              ? '已放弃报名该比赛项目'
              : '已提交报名申请'
          })
        });
      this.getList()
    },

    getDate() {
      const myDate = new Date()
      //获取当前年
      const year = myDate.getFullYear()
      //获取当前月
      const month = myDate.getMonth() + 1
      //获取当前日
      const date = myDate.getDate()
      //获取当前小时数(0-23)
      const h = myDate.getHours()
      //获取当前分钟数(0-59)
      const m = myDate.getMinutes()
      const s = myDate.getSeconds()

      //获取当前时间
      this.time =
        year +
        '-' +
        this.convert(month) +
        '-' +
        this.convert(date) +
        ' ' +
        this.convert(h) +
        ':' +
        this.convert(m) +
        ':' +
        this.convert(s)

      console.log("当前时间", this.time)
    },

    //日期时间处理
    convert(val) {
      return val < 10 ? '0' + val : val
    },

    //最大条数
    sizeChange(pageSize) {
      this.page.pageSize = pageSize
    },
    //改变当前每页的个数触发
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.getList()
    },
    //改变当前页码触发
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.getList()
    },
  }
}


</script>


<style scoped>

.ac {
  /*上 右 下 左*/
  padding: 10px 10px 10px 10px;
  margin: 3px 0px 0px 3px;
  /*box-shadow: 0 5px 15px rgba(0,0,0,.8);*/
  /*background-color:#ff9900;*/
  /*-moz-box-shadow: 10px 10px 5px #888888; !* 老的 Firefox *!*/
  box-shadow: 0 0 5px #888888;
  /*margin: 20px;*/
  /*border:1px solid #fff*/
  /*border:1px dashed #888888*/
  /*box-shadow: 0 5px 15px*/
}

.box {
  width: 100%;
  height: 100px;
  /*margin: auto auto 1px;*/
  border-radius: 8px;
  border: 2px solid #009999;
  /*box-shadow: 0 0 20px #E8E8E8;*/
  /*box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04)*/
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
}

.all {
  margin-top: -30px;
  word-break: break-all;
  height: 100%;
}

.mid {
  margin-top: 25%;
  height: 50%;
}

.mid_item {
  justify-content: center;
  align-items: center;
}

.item {
  margin-bottom: 4px;
}

.item_tag {
  margin-left: 7%;
  float: left;

  /*文本左对齐*/
  text-align: left;
}

.item_desr {
  margin-left: 50%;
  min-height: 30px;
  text-align: left;
}

.text {
  width: 100%;
  font-size: 12px;
  font-family: "Microsoft YaHei";
  color: #909399;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.el-card {
  min-width: 100%;
  height: 100%;
  margin-right: 20px;
  /*transition: all .5s;*/
}

.el-card:hover {
  margin-top: -5px;
}

.el-row {
  margin-bottom: 20px;
  display: flex;
  flex-wrap: wrap
}

.el-col {
  border-radius: 4px;
  align-items: stretch;
  margin-bottom: 40px;
}
</style>

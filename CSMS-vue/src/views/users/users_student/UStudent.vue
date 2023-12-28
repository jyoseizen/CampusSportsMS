<template>
  <div class="as">

    <basic-container>
      <avue-crud :data="data"
                 :option="option"
                 v-model="form"
                 @on-load="getList"
                 @size-change="sizeChange"
                 @row-del="deletes"
                 @refresh-change="refreshList"
      >

        <template slot="state" slot-scope="scope">
          <el-tag

          :type=" scope.row.competitionEnd < time ?'danger':'info'"
          >
<!--            {{ scope.row.competitionEnd == }}-->
            {{ scope.row.competitionEnd < time ? "已结束":"未开始" }}

          </el-tag>
          <!--          </el-tooltip>-->
        </template>

      </avue-crud>
    </basic-container>

    <!--    分页-->
    <div style="margin: 10px 0">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script scoped>
import {studentAvue} from '@/api/Avue/Ustudent';
import {seleteStudentAPI, studentDeleteAPI} from '@/request/api';

export default {
  data() {
    return {
      option: studentAvue,
      form: {},
      data: [],
      search: '',
      message:"success",
      //分页
      currentPage: 1,
      pageSize: 10,
      total: 0,
      time: '',
      state: '',

      usersStnumber: '',
      competitionNb: ''
    }
  },
  created() {
    this.getList()
    this.getDate()
  },
  methods: {

    //分页请求
    getList() {
      seleteStudentAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          search: this.search,
          usersStnumber: window.sessionStorage.getItem('id'),

        }
      }).then(res => {
        console.log("用户报名列表===》", res.data.data.records)
        console.log(this.time)
        this.data = res.data.data.records
        this.total = res.data.data.total

        // let date1 = new Date(this.time)
        // let time1 = date1.getTime();
        // console.log(time1)

        // for (let i = 0; i < res.data.data.records.length; i++) {
        //   // this.state = ''
        //   let date2 = new Date(res.data.data.records[i].competitionEnd)
        //   let time2 = date2.getTime();
        //   console.log("时间", time2)
        //
        //   if (time1 < time2) {
        //     console.log("比较")
        //
        //     // this.state = '已结束'
        //   }
        //   else {
        //     console.log("比较2")
        //     this.state = '未开始'
        //   }
        //   // if (date2 <date1){
        //   //   this.state = '已结束'
        //   // }
        //   // if (res.data.data.records[i].competitionEnd.getTime()>this.time.getTime()){
        //
        // }

      })

    },

    // 取消报名
    deletes(params) {

      this.$confirm('确定要取消参加该比赛？', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(() => {

          studentDeleteAPI({
            params: {
              usersStnumber: window.sessionStorage.getItem('id'),
              competitionNb: params.competitionNb,
            }
          }).then(res => {
            console.log("删除回调===>", res)
            if (res.data.code == "200") {
              this.$message({
                type: 'success',
                message: '取消报名成功'
              });
            } else {
              this.$message({
                type: 'error',
                message: '取消报名失败，已超过报名时间'
              });
            }
            this.getList()
          })

        })
        .catch(action => {
          this.$message({
            type: 'error',
            message: action === 'cancel'
              ? '已放弃取消报名该比赛项目'
              : '已提取消报名比赛'
          })
        });
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

    //清空搜索栏
    emptyInput() {
      this.users_ne = "";
      this.users_nb = "";
      this.college_ne = "";
    },

    //刷新按钮
    refreshList() {
      this.getList()
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
    }
  },
}
</script>
<style scoped>
/*body{*/
/*  !*padding: 25px 50px 75px 100px;*!*/
/*  !*margin :auto 100px;*!*/
/*}*/
.as {
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

</style>


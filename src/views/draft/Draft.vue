<template>
  <div class="as">

    <div style="margin-bottom:10px">
      <el-input placeholder="请输入用户姓名" v-model="users_ne" style="width:200px">
      </el-input>

      <el-input placeholder="请输入用户学号" v-model="users_nb" style="width:200px">
      </el-input>

      <el-input placeholder="请输入院系" v-model="college_ne" style="width:200px">
      </el-input>

      <el-button icon="el-icon-search" @click="searchLiset"></el-button>
      <el-button icon="el-icon-delete" @click="emptyInput"></el-button>

      <!--      <el-input placeholder="请输入班级名称" v-model="es" style="width:200px">-->
      <!--        &lt;!&ndash;          <el-input placeholder="请输入班级名称" v-model="ds" style="width:200px"></el-input>&ndash;&gt;-->
      <!--        &lt;!&ndash;            <el-input placeholder="请输入班级名称" v-model="bs" style="width:200px"></el-input>&ndash;&gt;-->
      <!--        <template #append>-->
      <!--          <el-button icon="el-icon-search" @click="searchLiset"></el-button>-->
      <!--        </template>-->
      <!--      </el-input>-->

    </div>

    <basic-container>
      <avue-crud :data="data"
                 :option="option"
                 v-model="form"

                 @on-load="getList"
                 @size-change="sizeChange"
                 @row-update="update"
                 @row-save="insert"
                 @row-del="deletes"
                 @refresh-change="refreshList"

      >

        <template slot="draftState" slot-scope="scope" >
          <el-switch
            class="switch"
            active-value="1"
            inactive-value="0"
            active-text="已发布"
            inactive-text="未发布"
            active-color="#409EFF"
            inactive-color="#CCCCCC"
            v-model=scope.row.draftState
            @change="changeSwitch(scope.row)"

          >
          </el-switch>
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
import {draftAvue} from '@/api/Avue/draft';
import {DgetLisetAPI,DselectStateAPI,DupdateStateAPI,DdeleteAPI,DinsertAPI} from '@/request/api';

export default {
  data() {
    return {
      option: draftAvue,
      form: {},
      data: [],
      search: '',
      //分页
      currentPage: 1,
      pageSize: 10,
      total: 0,

      users_ne: '',
      users_nb: '',
      college_ne: '',
    }
  },
  created() {
    this.getList()
  },
  methods: {

    //分页请求
    getList() {
      DgetLisetAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          search: this.search

        }
      }).then(res => {
        console.log('11111111')
        // console.log(res)
        console.log(res.data.data.records)

        this.data = res.data.data.records
        this.total = res.data.data.total

      })

    },

    //修改状态
    //写在methods里面的方法
    changeSwitch(row){
      console.log("开关===>",row);
      let status = row.draftState == "1" ? '已发布' : '未发布'
      console.log(row.draftState)
      this.$confirm(`是否${status}此接口状态？`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        DupdateStateAPI(
            row.draftId,
            row.draftState
        ).then(res =>{
          console.log(res)

      })
          .catch(() => {
        loading();
      })
    }).catch(() => {
  //这里处理取消操作时候的效果
  // this.getTableData()
})
},

    //查询
    searchLiset() {
      DselectStateAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          usersName: this.users_ne,
          usersStnumber: this.users_nb,
          collegeName: this.college_ne
        }
      }).then(res => {
        console.log(res.data.data.records)
        // console.log(res)
        console.log('111')
        this.data = res.data.data.records
        this.total = res.data.data.total
      })

    },

    //新增
    insert(params, done, loading) {
      DinsertAPI(params).then(res => {
        done();  // 关闭表单
        loading(); // 按钮停止加载
        this.getList()
        if (res.data.code == '200') {
          this.$message({
            type: "success",
            message: "新增成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "新增成功"
          })
        }
        console.log(res)
        console.log('ssss')
      })
      // console.log(res)
      console.log('999')
    },

    //修改
    update(params, index, done, loading) {
      UupdateAPI(params).then(res => {
        done();
        this.getList()
        console.log(res)
        if (res.data.code == '200') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "更新失败"
          })
        }
      })
        .catch(() => {
          loading();

        })

      // this.onLoad(this.page, this.query);
      console.log(params)

      // this.getList()
    },

    // 删除按钮
    deletes(params) {

      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return DdeleteAPI(params.draftId)
      }).then((res) => {
        this.getList();
        // this.$message.success('删除成功')
        if (res.data.code == '200') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: "删除失败"
          })
        }
        console.log(res);

      })

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
<style >
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

/* switch按钮样式 */
.switch .el-switch__label {
  position: absolute;
  display: none;
  color: #fff !important;
}
/*打开时文字位置设置*/
.switch .el-switch__label--right {
  z-index: 1;
}
/* 调整打开时文字的显示位子 */
.switch .el-switch__label--right span{
  margin-right: 9px;
}
/*关闭时文字位置设置*/
.switch .el-switch__label--left {
  z-index: 1;
}
/* 调整关闭时文字的显示位子 */
.switch .el-switch__label--left span{
  margin-left: 9px;
}
/*显示文字*/
.switch .el-switch__label.is-active {
  display: block;
}
/* 调整按钮的宽度 */
.switch.el-switch .el-switch__core,
.el-switch .el-switch__label {
  width: 68px !important;
  margin: 0;
}

</style>


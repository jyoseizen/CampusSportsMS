<template>
  <div class="ae">
    <div style="margin-bottom:10px">
      <el-input placeholder="请输入比赛名称" v-model="competition_ne" style="width:200px">
      </el-input>

      <el-input placeholder="请输入学院名称" v-model="college_name" style="width:200px">
      </el-input>

      <el-input placeholder="请输入用户名称" v-model="users_name" style="width:200px">
      </el-input>

      <el-button icon="el-icon-search" @click="searchLiset"></el-button>
      <el-button icon="el-icon-delete" @click="emptyInput"></el-button>

    </div>
    <basic-container>
      <avue-crud :data="data"
                 :option="option"
                 @on-load="searchLiset"
                 @size-change="sizeChange"
                 @row-save="insert"
                 @row-update="update"
                 @row-del="deletes"
                 @refresh-change="refreshList"
      >

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
import {enrollAvue} from '@/api/Avue/enroll';
import {EselectAll, EselectList,EupdateAPI} from '@/request/api';

export default {
  data() {
    return {
      option: enrollAvue,
      obj: {},
      data: [],
      test:[],
      search: '',
      // usersSex: '',
      competition_ne: '',
      college_name: '',
      users_name: '',

      //分页
      currentPage: 1,
      pageSize: 10,
      total: 0,

    }
  },

  created() {
    this.updateList()
  },

  methods: {

    //条件分页查询
    searchLiset() {
      // done();
      EselectAll({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          competitionNe: this.competition_ne,
          collegeName: this.college_name,
          usersName: this.users_name
        }
      }).then(res => {
        console.log(res.data.data.records)
        // console.log(res)
        console.log('111')
        this.data = res.data.data.records
        this.total = res.data.data.total
        for (let i = 0; i <= res.data.data.records.length; i++) {
          if (res.data.data.records[i].usersSex == 0) {
            this.data[i].usersSex = '男'
          } else {
            this.data[i].usersSex = '女'
          }
        }
      })

    },

    // //新增
    // insert(params, done, loading) {
    //   CinsertAPI(params).then(res => {
    //     done();  // 关闭表单
    //     loading(); // 按钮停止加载
    //     this.getList()
    //     if (res.data.code == '200') {
    //       this.$message({
    //         type: "success",
    //         message: "新增成功"
    //       })
    //     } else {
    //       this.$message({
    //         type: "error",
    //         message: "新增成功"
    //       })
    //     }
    //     console.log(res)
    //     console.log('ssss')
    //   })
    //
    // },

    //修改
    update(params, index, done, loading) {
      console.log("params==>",params)
      this.test =params
      if (params.usersSex =="男"){
        this.test.usersSex =0
      }else{
        this.test.usersSex =1
      }
      console.log("1111111111111")
      console.log(this.test)
      EupdateAPI(this.test).then(res => {
        console.log("res==>",res)
        done()
        if (res.data.code == "200") {
          this.$message({
            type: "success",
            message: "更新成功",

          })
        } else {
          this.$message({
            type: "error",
            message: "更新失败"

          })
        }
        this.getList()
      })
        .catch(() => {
          loading();
        })
    },

    //删除
    deletes(params) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return CdeleteAPI(params.competitionNb)
      }).then((res) => {
        this.getList();
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

    //更新列表
    updateList() {
      EselectList({
        // params,
      }).then(res => {
        console.log("更新数据")
      })
    },

    //最大条数
    sizeChange(pageSize) {
      this.page.pageSize = pageSize
    },
    //改变当前每页的个数触发
    handleSizeChange(pageSize) {
      this.pageSize = pageSize;
      this.searchLiset()
    },
    //改变当前页码触发
    handleCurrentChange(pageNum) {
      this.currentPage = pageNum
      this.searchLiset()
    },

    // 增加按钮
    handleRowSave(row, done, loading) {
      this.data.splice(0, 0, row);
      done();  // 关闭表单
      loading(); // 按钮停止加载
    },
    // 修改按钮
    handleRowUpdate(row, index, done, loading) {
      this.data.splice(index, 1, row);
      done();  // 关闭表单
      loading(); // 按钮停止加载
    },
    // 删除按钮
    rowDel(row, index) {
      this.$confirm('是否删除该条信息？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.data.splice(index, 1);
      });
    },
    //清空搜索栏
    emptyInput() {
      this.competition_ne = "";
      this.college_name = "";
      this.users_name = "";
    },
    //刷新按钮
    refreshList() {
      this.searchLiset()
    },
  },
}
</script>
<style scoped>
/*body{*/
/*  !*padding: 25px 50px 75px 100px;*!*/
/*  !*margin :auto 100px;*!*/
/*}*/
.ae {
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


<template>
  <div class="as">

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
import {siteAvue} from '@/api/Avue/site';
import {SgetLisetAPI,SinsertAPI,SupdateAPI,SdeleteAPI} from '@/request/api';

export default {
  data() {
    return {
      option:siteAvue,
      form:{},
      data: [],
      search:'',
      //分页
      currentPage:1,
      pageSize:10,
      total:0,
    }
  },
  created() {
    this.getList()
  },
  methods: {

    //分页请求
    getList(){
      SgetLisetAPI({
        params:{
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          search:this.search

        }
      }).then(res => {
        console.log('11111111')
        // console.log(res)
        console.log(res.data.data.records)

        this.data = res.data.data.records
        this.total = res.data.data.total

      })

    },

    //新增
    insert(params,done,loading){
      SinsertAPI(params).then(res =>{
        done();  // 关闭表单
        loading(); // 按钮停止加载
        this.getList()
        if(res.data.code =='200'){
          this.$message({
            type:"success",
            message: "新增成功"
          })
        }else{
          this.$message({
            type:"error",
            message: "新增失败"
          })
        }
      })
    },

    //修改
    update(params,index,done,loading){
      SupdateAPI(params).then(res =>{
        done();
        this.getList()
        console.log(res)
        if(res.data.code =='200'){
          this.$message({
            type:"success",
            message: "更新成功"
          })
        }else{
          this.$message({
            type:"error",
            message: "更新失败"
          })
        }
      })
        .catch(() =>{
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
        return SdeleteAPI(params.siteId)
      }).then((res) => {
        this.getList();
        if(res.data.code =='200'){
          this.$message({
            type:"success",
            message: "删除成功"
          })
        }else{
          this.$message({
            type:"error",
            message: "删除失败"
          })
        }
        console.log(res);

      })

      // this.$confirm('是否删除该条信息？', '提示', {
      //   confirmButtonText: '确定',
      //   cancelButtonText: '取消',
      //   type: 'warning'
      // }).then(() => {
      //   UdeleteAPI(params).then(res =>{
      //     console.log(params)
      //     if(res.data.code =='0'){
      //       this.$message({
      //         type:"success",
      //         message: "删除成功"
      //       })
      //     }else{
      //       this.$message({
      //         type:"error",
      //         message: "删除失败"
      //       })
      //     }
      //     console.log(params)
      //   })
      //   this.data.splice(index, 1);
      //   console.log(params)
      // });
      // console.log(params)
    },

    //清空搜索栏
    emptyInput(){
      this.users_ne ="";
      this.users_nb="";
      this.college_ne="";
    },

    //刷新按钮
    refreshList(){
      this.getList()
    },

    //最大条数
    sizeChange(pageSize) {
      this.page.pageSize = pageSize
    },
    //改变当前每页的个数触发
    handleSizeChange(pageSize ){
      this.pageSize = pageSize;
      this.getList()
    },
    //改变当前页码触发
    handleCurrentChange(pageNum){
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
.as{
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


<template>
  <div class="ae">
    <div style="margin-bottom:10px">
      <el-input placeholder="请输入比赛名称" v-model="competition_ne" style="width:200px">
      </el-input>

      <!--      开始时间-->
      <el-date-picker
        v-model="competition_st"
        type="datetime"
        placeholder="选择开始时间">
      </el-date-picker>

      <!--      结束时间-->
      <el-date-picker
        v-model="competition_ed"
        type="datetime"
        placeholder="选择结束时间">
      </el-date-picker>
      <el-button icon="el-icon-search" @click="searchLiset"></el-button>
      <el-button icon="el-icon-delete" @click="emptyInput"></el-button>
    </div>

    <basic-container>

      <avue-crud :data="data"
                 :option="option"
                 @on-load="getList"
                 @size-change="sizeChange"
                 @row-update="update"
                 @row-save="insert"
                 @row-del="deletes"
      >

        <template slot="menuLeft">
          <el-button type="primary"
                     icon="el-icon-plus"
                     size="small"
                     @click.native="dialogFormVisible = true">发布比赛
          </el-button>


          <!--          自定义新增-->
          <el-dialog title="新增比赛项目" :visible.sync="dialogFormVisible">

            <el-form :model="formsing" label-width="100px" :rules="rulesing" ref="formsing">

              <!--        一行-->
              <el-row>

                <el-col :span="12">
                  <el-form-item label="比赛编号:" :label-width="formLabelWidth" prop="Cnumber">
                    <el-input v-model="formsing.Cnumber"
                              autocomplete="off"
                              placeholder="请输入比赛编号"
                    ></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="比赛名称:" :label-width="formLabelWidth" prop="Cname">
                    <el-input v-model="formsing.Cname"
                              autocomplete="off"
                              placeholder="请输入比赛名称"
                    ></el-input>
                  </el-form-item>
                </el-col>

              </el-row>

              <!--        两行-->
              <el-row>

                <el-col :span="12">
                  <el-form-item label="比赛开始时间:" :label-width="formLabelWidth" prop="Cstart">
                    <el-date-picker
                      v-model="formsing.Cstart"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      type="datetime"
                      placeholder="请选择 比赛开始时间">
                    </el-date-picker>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="比赛结束时间:" :label-width="formLabelWidth" prop="Cend">
                    <el-date-picker
                      v-model="formsing.Cend"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      type="datetime"
                      placeholder="请选择 比赛结束时间">
                    </el-date-picker>
                  </el-form-item>
                </el-col>

              </el-row>

              <!--        三行-->
              <el-row>

                <el-col :span="12">
                  <el-form-item label="报名开始时间:" :label-width="formLabelWidth" prop="Astart">
                    <el-date-picker
                      v-model="formsing.Astart"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      type="datetime"
                      placeholder="请选择 报名开始时间">
                    </el-date-picker>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="报名结束时间:" :label-width="formLabelWidth" prop="Aend">
                    <el-date-picker
                      v-model="formsing.Aend"
                      value-format="yyyy-MM-dd HH:mm:ss"
                      type="datetime"
                      placeholder="请选择 报名结束时间">
                    </el-date-picker>

                  </el-form-item>
                </el-col>
              </el-row>
                <!--      四行-->
              <el-row>

                  <el-col :span="12">
                    <el-form-item label="比赛地点:" :label-width="formLabelWidth" prop="Csite">
                      <el-select v-model="formsing.Csite"
                                 placeholder="请选择地点"
                      >
                        <el-option
                          v-for="item in competitionOption"
                          :key="item.value"
                          :label="item.competition_site"
                          :value="item.competition_site">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </el-col>

                  <el-col :span="12">
                    <el-form-item label="参赛人数:" :label-width="formLabelWidth" prop="Ctnor">
                      <el-input v-model="formsing.Ctnor"
                                autocomplete="off"
                                placeholder="请输入参赛人数"
                      ></el-input>
                    </el-form-item>
                  </el-col>

              </el-row>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="insert('formsing')">保存</el-button>
            </div>
          </el-dialog>
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
import {manageAvue} from '@/api/Avue/manage';
import {MgetLisetAPI, MselectAll, CinsertAPI, CupdateAPI, CdeleteAPI, CselectSiteAPI} from '@/request/api';
import md5 from "js-md5";

export default {
  data() {
    return {
      option: manageAvue,
      obj: {},
      data: [],
      search: '',
      competition_ne: '',
      competition_st: '',
      competition_ed: '',
      // competitionNe:'',
      //分页
      currentPage: 1,
      pageSize: 10,
      total: 0,

      dialogFormVisible: false,
      formLabelWidth: '90px',
      vale: '',
      competitionOption: [],
      competition_site: '',
      formsing: {
        Cnumber: '',
        Cname: '',
        Cstart: '',
        Cend: '',
        Astart: '',
        Aend: '',
        Csite: '',
        Ctnor: '',
      },
      //校验表单
      rulesing: {
        Cnumber: [
          {required: true, trigger: '请输入比赛编号', validator: this.nameValidator}
        ],

        Cname: [
          {required: true, trigger: '请输入比赛名称', validator: this.idValidator}
        ],

        Cstart: [
          {required: true, message: '请选择比赛开始时间', trigger: 'blur'},
        ],

        Cend: [
          {required: true, message: '请选择比赛结束时间', trigger: 'blur'},
        ],

        Astart: [
          {required: true, message: '请选择报名开始时间', trigger: 'blur'},
        ],

        Aend: [
          {required: true, message: '请选择报名结束时间', trigger: 'blur'},
        ],

        Csite: [
          {required: true, message: '请选择比赛场地', trigger: 'blur'},
        ],

        Ctnor: [
          {required: true, message: '请输入比赛人数', trigger: 'blur',validator: this.numberValidator},
        ],

      },

    }
  },

  created(){
    this.getSite();
  },

  methods: {
    //校验规则
    //参赛人数
    numberValidator(rule, value, callback) {
      const numberReg = /^40$|^([1-3]\d)$|^\d?$/
      if (!value) {
        callback(new Error('号码不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (numberReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('号码格式错误'))
          console.log('号码格式错误')
        }
      }
    },
    //比赛名称
    idValidator(rule, value, callback) {
      const idReg = /^[\u4e00-\u9fa50-9]{4,12}$/
      if (!value) {
        callback(new Error('比赛名称不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (idReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('比赛名称格式错误'))
          console.log('比赛名称格式错误')
        }
      }
    },
    //比赛编号
    nameValidator(rule, value, callback) {
      const nameReg = /^glxy[0-9]\d{5}$/
      if (!value) {
        callback(new Error('名字不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        // if (nameReg.test(value)) {
          // 如果符合
          callback()
        // } else {
        //   callback(new Error('名字格式错误'))
        //   console.log('名字格式错误')
        // }
      }
    },

    //获取场地字典
    getSite(){
      CselectSiteAPI().then( res =>{
        console.log("场地 ===》",res)
        this.competitionOption = res.data
      })
    },

    //分页查询
    getList() {
      MgetLisetAPI({
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

    //条件查询
    searchLiset() {
      // done();
      MselectAll({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          competitionNe: this.competition_ne,
          competitionStart: this.competition_st,
          competitionEnd: this.competition_ed
        }
      }).then(res => {
        console.log(res.data.data.records)
        // console.log(res)
        console.log('111')
        this.data = res.data.data.records
        this.total = res.data.data.total
      })
    },

    insert(){
      this.$refs["formsing"].validate(valid => {
        if (valid) {

          console.log("比赛表单==》",this.formsing)
          let competitionJson = {};
          competitionJson.competitionNb = this.formsing.Cnumber
          competitionJson.competitionNe = this.formsing.Cname
          competitionJson.competitionSite = this.formsing.Csite
          competitionJson.competitionPe = this.formsing.Ctnor
          competitionJson.competitionStart = this.formsing.Cstart
          competitionJson.competitionEnd = this.formsing.Cend
          competitionJson.applyStart = this.formsing.Astart
          competitionJson.applyEnd = this.formsing.Aend
          CinsertAPI(competitionJson).then(res =>{
            this.getList()
            console.log("比赛新增回调 ==》",res)
            if (res.data.code == '200') {
              this.$message({
                type: "success",
                message: "新增成功"

              },
              )
            } else {
              this.$message({
                type: "error",
                message: "新增失败"
              },
              )
            }
              this.dialogFormVisible=false
          }

          )

        } else {
          console.log('信息错误')
          return false
        }
      })
    },

    //修改
    update(params, index, done, loading) {
      CupdateAPI(params).then(res => {
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

    },

    //删除
    deletes(params) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log("比赛信息==》", params)
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
      this.competition_st = "";
      this.competition_ed = "";
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


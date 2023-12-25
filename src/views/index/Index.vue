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
                 @selection-change="selectionChange"

      >


        <template slot="menuLeft">

          <el-button type="primary"
                     icon="el-icon-plus"
                     size="small"
                     @click.native="dialogFormVisible = true">新增
          </el-button>

          <el-button type="danger" icon="el-icon-delete" @click="toggleSelection()">删除选中</el-button>
          <el-button type="success" @click="handleExcel" icon="el-icon-download">导出表格</el-button>
          <el-button type="success" @click="handleGet" icon="el-icon-upload">下载模版</el-button>
          <!--          记得加：headers(请求头，一般用来放token)-->
          <el-upload class="upload"
                     action="http://localhost:9090/users/upload"
                     :show-file-list="false"
                     :before-upload="Bupload"
                     :headers="token"
                     :on-success="(res,file)=> {return  Osuccess(res,file)}"
                     :on-error="Oerror"
                     :disabled="BtnDisabled">
            <el-button type="success" @click="" :icon="BtnIcon" :disabled="BtnDisabled">{{ BtnText }}</el-button>
            <div class="upload2" slot="tip">只能上传Excel文件</div>
          </el-upload>

          <!--          自定义新增-->
          <el-dialog title="新增用户" :visible.sync="dialogFormVisible">

            <el-form :model="forms" label-width="100px" :rules="rules" ref="forms">

              <!--        一行-->
              <el-row>

                <el-col :span="12">
                  <el-form-item label="姓名:" :label-width="formLabelWidth" prop="name">
                    <el-input v-model="forms.name"
                              autocomplete="off"
                              placeholder="请输入姓名"
                    ></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="学号:" :label-width="formLabelWidth" prop="id">
                    <el-input v-model="forms.id"
                              autocomplete="off"
                              placeholder="请输入学号"
                    ></el-input>
                  </el-form-item>
                </el-col>

              </el-row>

              <!--        两行-->
              <el-row>

                <el-col :span="12">
                  <el-form-item label="性别:" :label-width="formLabelWidth" prop="sex" style="float:left">
                    <el-radio v-model="forms.sex" label="0" border>男</el-radio>
                    <el-radio v-model="forms.sex" label="1" border>女</el-radio>
                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="号码:" :label-width="formLabelWidth" prop="number">
                    <el-input v-model.number="forms.number"
                              autocomplete="off"
                              placeholder="请输入电话号码"
                    ></el-input>
                  </el-form-item>
                </el-col>

              </el-row>

              <!--        三行-->
              <el-row>

                <el-col :span="12">
                  <el-form-item label="学院:" :label-width="formLabelWidth" prop="college">

                    <!--               @change="selectcollege($event)"-->
                    <el-select v-model="forms.college"
                               placeholder="请选择学院"
                    >
                      <el-option
                        v-for="item in collegeOption"
                        :key="item.value"
                        :label="item.college_name"
                        :value="item.college_name">
                      </el-option>
                    </el-select>

                  </el-form-item>
                </el-col>

                <el-col :span="12">
                  <el-form-item label="密码:" :label-width="formLabelWidth" prop="password">
                    <el-input v-model="forms.password"
                              type="password"
                              placeholder="请输入密码"
                              required
                              show-word-limit
                    ></el-input>
                    <!--          <el-input v-model="form.name" autocomplete="off"></el-input>-->
                  </el-form-item>
                </el-col>

              </el-row>


            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="insert('forms')">保存</el-button>
            </div>
          </el-dialog>
        </template>
        <!--      <template slot="menuRight">-->
        <!--        <el-button type="primary" icon="el-icon-edit" circle size="small">333</el-button>-->
        <!--      </template>-->


        <!--      </template>-->
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
import {indexAvue} from '@/api/Avue/index';
import md5 from 'js-md5';
import {
  UgetListAllAPI,
  UgetListAPI,
  UselectAllAPI,
  UupdateAPI,
  UinsertAPI,
  UdeleteAPI,
  Uselect,
  UdeleteListAPI
} from '@/request/api';

export default {
  data() {
    return {
      //导入信息按钮
      BtnText: '导入信息',
      BtnIcon: 'el-icon-upload',
      BtnDisabled: false,  //上传文件时上传按钮不可用

      option: indexAvue,
      form: {},
      data: [],
      usersParams: [],
      search: '',
      usersSex: '',
      collegeOption: [],
      imgsUrl: "默认头像.jpg",
      //分页
      currentPage: 1,
      pageSize: 10,
      total: 0,
      test1: [],
      lists: [],
      dialogFormVisible: false,

      options: [],
      forms: {
        id: '',
        name: '',
        sex: '',
        number: '',
        college: '',
        password: '',

      },

      // headerObj:window.sessionStorage.getItem('token'),
      token: {
        token: window.sessionStorage.getItem('token')
      },

      //校验表单
      rules: {
        name: [
          {required: true, trigger: 'blur', validator: this.nameValidator}
        ],

        id: [
          {required: true, trigger: 'blur', validator: this.idValidator}
        ],

        college: [
          {required: true, message: '请选择学院', trigger: 'blur'},
        ],

        sex: [
          {required: true, message: '请选择性别', trigger: 'blur'},
        ],

        password: [
          {required: true, trigger: 'blur', validator: this.passwordValidator},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],

        number: [
          {required: true, trigger: 'blur', validator: this.numberValidator}
        ]
      },
      formLabelWidth: '90px',
      vale: '',

      //搜索栏
      users_ne: "",
      users_nb: "",
      college_ne: "",
    }
  },
  created() {
    this.getList()
    this.selectCollegeName()

  },
  methods: {

    //校验规则
    //密码
    passwordValidator(rule, value, callback) {
      const passwordReg = /^[a-zA-Z0-9_]{1,30}$/
      if (!value) {
        callback(new Error('密码不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (passwordReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('密码只能是数字、下划线、字母!'))
          console.log('密码只能是数字、下划线、字母!')
        }
      }
    },
    //号码
    numberValidator(rule, value, callback) {
      const numberReg = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/
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
    //学号
    idValidator(rule, value, callback) {
      const idReg = /^20[0-9]\d{9}$/
      if (!value) {
        callback(new Error('学号不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (idReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('学号格式错误'))
          console.log('学号格式错误')
        }
      }
    },
    //名字
    nameValidator(rule, value, callback) {
      const nameReg = /^(?=.{2,5}$)(((?![\u3000-\u303F])[\u2E80-\uFE4F]|\·)*(?![\u3000-\u303F])[\u2E80-\uFE4F](\·)*)$/
      if (!value) {
        callback(new Error('名字不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (nameReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('名字格式错误'))
          console.log('名字格式错误')
        }
      }
    },

    //获取学院字典
    selectCollegeName() {
      Uselect().then(res => {
        console.log("学院 ==》", res)
        this.collegeOption = res.data
        console.log(res)
      })
    },

    //导入失败状态改变
    Oerror() {
      this.BtnIcon = 'el-icon-upload';
      this.BtnText = '导入信息';
      this.BtnDisabled = false;
      this.getList();
      // console.log("错误信息",err);
      this.$message({
        showClose: true,
        message: '导入信息失败',
        type: 'error'
      });
    },

    //导入成功状态改变
    Osuccess(res, file) {
      if (res.code == 200) {
        this.$message({
          showClose: true,
          message: '导入信息成功',
          type: 'success'
        });
      } else {
        this.$message({
          showClose: true,
          message: '导入信息成功',
          type: 'success'
        });
      }
      this.BtnIcon = 'el-icon-upload';
      this.BtnText = '导入信息';
      this.BtnDisabled = false;
      this.getList();

    },

    //文件上传状态
    Bupload() {
      this.BtnIcon = 'el-icon-loading';
      this.BtnText = '正在导入';
      this.BtnDisabled = true;
    },

    //多选
    selectionChange(list) {
      this.lists = []
      for (let i = 0; i < list.length; i++) {
        // console.log("多选数据 ==》",list[i].usersStnumber)
        this.lists[i] = list[i].usersStnumber
      }
    },

    //多选删除
    toggleSelection() {
      console.log("选中数据 =》", this.lists)
      for (let i = 0; i < this.lists.length; i++) {
        UdeleteListAPI({
          params: {
            usersStnumber: this.lists[i]
          }
        }).then(res => {
          this.getList()
        })
      }
      this.$message({
        type: "success",
        message: "删除成功",
      })
      this.getList()
    },

    //导出表格
    handleExcel() {
      UgetListAllAPI().then(res => {
        console.log("所有数据===>", res)
        let opt = {
          title: '用户信息表格',
          column: [{
            label: '姓名',
            prop: 'usersName'
          },
            {
              label: '学号',
              prop: 'usersStnumber'
            },
            {

              label: '性别',
              prop: 'sex',
            },
            {
              label: '电话',
              prop: 'usersPhone'
            },
            {
              label: '学院',
              prop: 'collegeName'
            },

          ],
          data: res.data
        }
        this.$Export.excel({
          title: opt.title,
          columns: opt.column,
          data: opt.data,
        });
      })
    },

    //下载模板
    handleGet() {
      // let template = {
      let template = {
        title: '导入模板',
        column: [{
          label: '导入Excel模板',
          prop: 'header',
          children: [
            {
              label: '姓名',
              prop: 'title1'
            }, {
              label: '学号',
              prop: 'title2'
            }, {
              label: '性别',
              prop: 'title3'
            },
            {
              label: '电话',
              prop: 'title4'
            },
            {
              label: '院系',
              prop: 'title5'
            },
            // {
            //   label: '参与项目',
            //   prop: 'title6'
            // },
            // {
            //   label: '密码',
            //   prop: 'title7'
            // },
          ]
        }],
        data: [{
          title1: "张三",
          title2: "201813007200",
          title3: "男",
          title4: "15077716861",
          title5: "理工学院",
          // title6: "gl1001",
          // title7: "123456",
        }, {
          title1: "李四",
          title2: "201813007200",
          title3: "女",
          title4: "15077716862",
          title5: "经济与管理学院",
          // title6: "gl1002",
          // title7: "123456",
        }]
      }
      this.$Export.excel({
        title: template.title,
        columns: template.column,
        data: template.data,

      });

    },

    //分页请求
    getList() {
      UgetListAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          search: this.search

        }
      }).then(res => {
        this.data = res.data.data.records
        this.total = res.data.data.total
        console.log("1111111111111")
        console.log(this.data)
        for (let i = 0; i <= res.data.data.records.length; i++) {
          if (res.data.data.records[i].usersSex === 0) {
            this.data[i].usersSex = '男'
          } else {
            this.data[i].usersSex = '女'
          }
        }


      })

    },

    //查询
    searchLiset() {
      UselectAllAPI({
        params: {
          PageNum: this.currentPage,
          PageSize: this.pageSize,
          usersName: this.users_ne,
          usersStnumber: this.users_nb,
          collegeName: this.college_ne
        }
      }).then(res => {
        console.log(res.data.data.records)
        console.log('111')
        this.data = res.data.data.records
        this.total = res.data.data.total
        //把0和1转换成男女
        for (let i = 0; i <= res.data.data.records.length; i++) {
          if (res.data.data.records[i].usersSex === 0) {
            this.data[i].usersSex = '男'
          } else {
            this.data[i].usersSex = '女'
          }
        }
      })
    },

    //自定义新增
    insert() {

      this.$refs["forms"].validate(valid => {
        if (valid) {

          let userJson = {};
          userJson.usersStnumber = this.forms.id,
            userJson.usersPwd = md5(this.forms.password),
            userJson.usersName = this.forms.name,
            userJson.usersSex = this.forms.sex,
            userJson.usersPhone = this.forms.number,
            userJson.collegeName = this.forms.college,
            userJson.usersUrl = this.imgsUrl,
            UinsertAPI(userJson).then(res => {
                this.getList()
                if (res.data.code == '200') {
                  this.$message({
                    type: "success",
                    message: "新增成功"
                  })
                } else {
                  this.$message({
                    type: "error",
                    message: "新增失败"
                  })
                }
                this.dialogFormVisible = false
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
      this.test1 = params
      if (params.usersSex === 0) {
        this.test1.usersSex = 0
      } else {
        this.test1.usersSex = 1
      }
      this.test1.usersPwd = md5(params.usersPwd)
      console.log("修改数据==》", params)
      UupdateAPI(this.test1).then(res => {
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
        return UdeleteAPI(params.usersStnumber)
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

.upload {
  /*display: flex;*/
  float: right;
  margin-left: 4.5px;
}

.upload2 {
  /*display: flex;*/
  float: right;
  margin-left: 4px;
  font-size: 5px;
  text-align: center;
  line-height: 41px;
  color: #B8B8B8;
}


</style>


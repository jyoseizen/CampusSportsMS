<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex;background:linear-gradient(to bottom,#87CEFA,#b6fbff)">
    <div style="width: 200px; padding-left: 30px; font-weight: bold; color: dodgerblue">运动会报名系统</div>
    <div style="flex: 1"></div>
    <div style="width: 100px;">
      <el-dropdown v-for="(item,index) in dataList" :key="index">

        <!--          {{ item.usersName}}-->

        <span class="el-link" style="color:#409EFF;">
                  <div class="imgdiv">
                    <img :src="img+item.usersUrl" alt="" class="img">
                  </div>
          <div style="  width: 40px;height: 40px;"><i class="el-icon-arrow-down el-icon--right"></i></div>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click.native="dialogFormVisible = true">个人信息</el-dropdown-item>
            <el-dropdown-item @click.native="loginOut">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>


    <el-dialog title="个人信息" :visible.sync="dialogFormVisible">

      <el-upload
        action="http://localhost:9090/users/uploadImg"
        :on-success="(res,file)=> {return  handleVideoSuccess(res,file)}"
        :before-upload="beforeUploadVideo"
        :show-file-list="false"
        :data="{usersStnumber:this.uploadData}"
        :headers="token"
      >
        <div>上传头像</div>

      </el-upload>

      <el-form :model="form" label-width="100px" :rules="rules" ref="form">

        <!--        一行-->
        <el-row>

          <el-col :span="12">
            <el-form-item label="学号:" :label-width="formLabelWidth">
              <el-input v-model="form.id" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="姓名:" :label-width="formLabelWidth">
              <el-input v-model="form.name" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <!--        两行-->
        <el-row>

          <el-col :span="12">
            <el-form-item label="性别:" :label-width="formLabelWidth">
              <!--              <el-input v-model="form.sex" autocomplete="off" :disabled="true"></el-input>-->
              <el-tag size="medium" style="float:left" :type="vale" effect="dark">{{ form.sex }}</el-tag>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="号码:" :label-width="formLabelWidth" prop="number">
              <el-input v-model.number="form.number"
                        autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-col>

        </el-row>

        <!--        三行-->
        <el-row>

          <el-col :span="12">
            <el-form-item label="学院:" :label-width="formLabelWidth">
              <el-tag size="medium" style="float:left">{{ form.college }}</el-tag>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="密码:" :label-width="formLabelWidth" prop="password">
              <el-input v-model="form.password"
                        type="password"
                        placeholder="请输入密码"
                        required
                        show-word-limit
              ></el-input>
              <!--          <el-input v-model="form.name" autocomplete="off"></el-input>-->
            </el-form-item>
          </el-col>

        </el-row>

        <!--        四行-->
        <el-row>

          <el-col :span="12">
            <el-form-item label="邮箱:" :label-width="formLabelWidth" prop="email">
              <el-input v-model="form.email"
                        autocomplete="off"
              ></el-input>
            </el-form-item>
          </el-col>

        </el-row>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateUsers('form')">修 改</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {UselectByIdAPI, UpdatePwdAPI} from '@/request/api';
import md5 from 'js-md5';

export default {
  name: "Header",
  data() {

    return {
      // userId: '',
      usersStnumber: '',
      data: [],
      dataList: [],
      img: "http://localhost:9090/image/",
      user: {},
      uploadData: '',
      stnumber: '',


      token: {
        token: window.sessionStorage.getItem('token')
      },

      dialogFormVisible: false,
      form: {
        id: '',
        name: '',
        sex: '',
        number: '',
        college: '',
        password: '',
        email: '',

      },

      rules: {
        password: [
          {required: true, trigger: 'blur', validator: this.passwordValidator},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],

        number: [{required: true, trigger: 'blur', validator: this.numberValidator}],

        email: [{required: true, trigger: 'blur', validator: this.emailValidator}]
      },

      formLabelWidth: '90px',
      vale: '',
    }
  },
  created() {
    // let userId = window.sessionStorage.getItem('id');
    this.getUser();

    let str = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(str)
    this.uploadData = window.sessionStorage.getItem('id')
    let stnumber = window.sessionStorage.getItem('id')

  },
  methods: {
    passwordValidator(rule, value, callback) {
      const Reg = /^[a-zA-Z0-9_]{1,30}$/
      if (!value) {
        callback(new Error('密码不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (Reg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('密码只能是数字、下划线、字母!'))
          console.log('密码只能是数字、下划线、字母!')
        }
      }
    },

    numberValidator(rule, value, callback) {
      const numberReg = /^[1][3,4,5,7,8][0-9]{9}$/
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

    emailValidator(rule, value, callback) {
      const emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!value) {
        callback(new Error('邮箱不能为空，重置密码必需'))
      } else {
        // 正则表达式判断是否符合格式
        if (emailReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('邮箱格式错误'))
          console.log('邮箱格式错误')
        }
      }
    },

    loginOut() {
      window.sessionStorage.removeItem('token')
      window.sessionStorage.clear();
      this.$message({
        message: '退出系统',
        type: 'success'
      });
      this.$router.push('/login');
    },

    //登陆用户数据渲染
    getUser() {
      UselectByIdAPI({
        params: {
          usersStnumber: window.sessionStorage.getItem('id')
        }
      }).then(res => {
        console.log("顶部用户数据====》", res)
        this.dataList = res.data.data.records
        this.form.id = res.data.data.records[0].usersStnumber
        this.form.name = res.data.data.records[0].usersName
        this.form.number = res.data.data.records[0].usersPhone
        this.form.college = res.data.data.records[0].collegeName
        this.form.email = res.data.data.records[0].usersEmail
        // this.form.password = res.data.data.records[0].usersPwd
        this.form.password = window.sessionStorage.getItem('upd')


        if (res.data.data.records[0].usersSex === 0) {
          this.form.sex = '男'
          this.vale = 'warning'
        } else {
          this.form.sex = '女'
          this.vale = 'danger'
        }

      })

    },

    //上传成功回调
    handleVideoSuccess(res, file) {
      if (res.code == 200) {
        this.$message({
          showClose: true,
          message: '更改头像成功',
          type: 'success'
        });
      } else {
        this.$message({
          showClose: true,
          message: '更改头像失败',
          type: 'error'
        });
      }
      this.getUser()
      console.log("成功回调", res)
    },

    //上传前回调
    beforeUploadVideo(file) {
      var fileSize = file.size / 1024 / 1024 < 5;
      if (['image/png', 'image/jpg', 'image/jpeg'].indexOf(file.type) == -1) {
        this.$message.error("请上传正确的图片格式");
        return false;
      }
      if (!fileSize) {
        this.$message.error("图片大小不能超过5MB");
        return false;
      }
      return true;
    },

    //用户修改信息

    updateUsers() {

      this.$refs["form"].validate(valid => {
        if (valid) {

          console.log("id", this.form.id)
          console.log("id", this.form.number)
          console.log("id", this.form.password)
          console.log("email", this.form.email)
          UpdatePwdAPI({
            params: {
              usersStnumber: this.form.id,
              usersPhone: this.form.number,
              usersPwd: md5(this.form.password),
              usersEmail: this.form.email,
            },

          }).then(res => {
            console.log("用户修改信息回调===》", res)
            if (res.data.code === "200") {
              this.dialogFormVisible = false
              this.$message({
                message: '用户信息修改成功',
                type: 'success'
              });
            } else {
              this.$message({
                message: '用户信息修改失败',
                type: 'error'
              });
            }
          })

        } else {
          console.log('信息错误')
          return false
        }
      })

    }
  }
}


</script>

<style scoped>
.img {
  width: 40px;
  height: 40px;
  max-width: 100%;
  max-height: 100%;
  background-size: 100% 100%;
  padding-top: -1px;
  border-radius: 10px;
}

.imgdiv {
  width: 49px;
  height: 49px;
  /*margin-left: 1px;*/
  padding-top: -1px;
}

.el-link {
  display: flex;
}
</style>

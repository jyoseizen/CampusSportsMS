<template>
  <div>
    <div style="background:#1da0fd;">

      <div class="tops">
        <div class="topsize">运动会报名系统</div>
        <div class="topsize2" @click="goLogin">| 返回登陆</div>
      </div>

    </div>

    <div class="bottoms">
      <div class="forms">
        <div style="width: 150px;height: 45px; "></div>
        <div style="width: 150px;height: 30px; ">| 修改密码</div>
        <el-form :model="form" label-width="153px" style="margin-top: 10px" :rules="rules" ref="form">

          <!--          学号-->
          <el-form-item label="请输入学号：" prop="id">
            <el-input v-model="form.id" style="width: 295px;margin-left :-60px"></el-input>
          </el-form-item>

          <!--          邮箱-->
          <el-form-item label="请输入预留邮箱：" prop="email">
            <el-input v-model="form.email" style="width: 295px;margin-left :-60px"></el-input>
          </el-form-item>

          <!--          新密码-->
          <el-form-item label="请输入新密码：" prop="pwd1">
            <el-input v-model="form.pwd1" style="width: 295px;margin-left :-60px"></el-input>
          </el-form-item>

          <!--          新确认密码-->
          <el-form-item label="再次输入密码：" prop="pwd2">
            <el-input v-model="form.pwd2" style="width: 295px;margin-left :-60px"></el-input>
          </el-form-item>

          <el-row>

            <el-col :span="16">
              <el-form-item label="请输入验证码:" prop="code">
                <el-input v-model="form.code" style="width: 180px;margin-left :-10px"></el-input>
              </el-form-item>
            </el-col>

            <el-col :span="6">
              <el-button type="primary" @click="buttonCode" :disabled="canClick">
                {{ content }}
              </el-button>
            </el-col>

          </el-row>

        </el-form>

        <button class="button1" @click="empty">重 置</button>
        <button class="button2" @click="updatePwd('form')">确 定</button>

      </div>
    </div>

  </div>
</template>

<script>
import {UgetEmailAPI, UgupdatePwdAPI} from '@/request/api';
import md5 from 'js-md5';

export default {
  name: "RetrievePwd",
  data() {
    return {

      content: '获取验证码',
      totalTime: 30,
      canClick: false,

      form: {
        id: '',
        email: '',
        pwd1: '',
        pwd2: '',
        code: '',
      },

      rules: {
        id: [
          {required: true, trigger: 'blur', validator: this.validateId,}
        ],
        email: [
          {required: true, trigger: 'blur', validator: this.validateEmail,}
        ],
        pwd1: [
          {required: true, trigger: 'blur', validator: this.validatePass,},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
        pwd2: [
          {required: true, trigger: 'blur', validator: this.validatePass2,}
        ],
        code: [
          {required: true, trigger: 'blur', validator: this.validateCode,}
        ],
      },
    }
  },

  methods: {

    //学号
    validateId(rule, value, callback) {
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

    //邮箱
    validateEmail(rule, value, callback) {
      const emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!value) {
        callback(new Error('邮箱不能为空'))
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

    //密码
    validatePass(rule, value, callback) {
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

    //确认密码
    validatePass2(rule, value, callback) {
      // const numberReg = /^[1][3,4,5,7,8][0-9]{9}$/
      if (!value) {
        callback(new Error('请再次输入密码'))
      } else if (value != this.form.pwd1) {
        callback(new Error('两次输入密码不一致！'))
      } else {
        callback();
      }
    },

    //验证码
    validateCode(rule, value, callback) {
      const idReg = /[0-9]\d{3}$/
      if (!value) {
        callback(new Error('验证码不能为空'))
      } else {
        // 正则表达式判断是否符合格式
        if (idReg.test(value)) {
          // 如果符合
          callback()
        } else {
          callback(new Error('验证码格式错误'))
          console.log('验证码格式错误')
        }
      }
    },

    //获取验证码
    buttonCode() {
      UgetEmailAPI({
        params: {
          usersStnumber: this.form.id,
          usersEmail: this.form.email,
        }
      }).then(res => {
        console.log("邮箱回调", res.data)
        if (res.data.code == "400") {
          this.$message({
            message: '学号或预留邮箱错误',
            type: 'error',
          });
        } else {

          if (this.canClick) return
          this.canClick = true
          this.content = this.totalTime + 's后重新发送'
          let clock = window.setInterval(() => {
            this.totalTime--
            this.content = this.totalTime + 's后重新发送'
            if (this.totalTime < 0) {
              window.clearInterval(clock)
              this.content = '重新发送验证码'
              this.totalTime = 30
              this.canClick = false
            }
          }, 1000)

          this.$message({
            message: '验证码以发送至邮箱，请注意查收',
            type: 'success',
          });
        }
      })
    },

    //重置密码
    updatePwd() {
      this.$refs["form"].validate(valid => {
        if (valid) {

          UgupdatePwdAPI({
            params: {
              usersStnumber: this.form.id,
              usersPwd: md5(this.form.pwd1),
              emailCode: this.form.code
            },
          }).then(res => {
            console.log("用户重置密码回调===》", res)
            if (res.data.code === "200") {
              this.dialogFormVisible = false
              this.$message({
                message: '重置密码成功',
                type: 'success'
              });
            } else {
              this.$message({
                message: '重置密码失败',
                type: 'error'
              });
            }
          })

        } else {
          console.log('信息错误')
          return false
        }
      })
    },

    //清空表单
    empty(){
      this.form.id = null;
      this.form.email = null;
      this.form.pwd1 = null;
      this.form.pwd2 = null;
      this.form.code = null;
    },

    goLogin() {
      window.location.hash = "#/login"
    },
  }
}
</script>

<style scoped>

.tops {
  width: 100%;
  height: 60px;
  /*border: 1px solid #d92424;*/
  margin-top: 0;
  /*float:left;*/
  /*display:inline;*/
  display: flex;
}

.bottoms {
  width: 100%;
  height: 600px;
  /*border: 1px solid #d92424;*/

}

.button1 {
  width: 388px;
  height: 40px;
  background-color: #409eff;
  border-radius: 6px;
  border: none;
  color: #FFFFFF;
}

.button2 {
  width: 388px;
  height: 40px;
  margin-top: 18px;
  background-color: #409eff;
  border-radius: 6px;
  border: none;
  color: #FFFFFF;
}

.forms {
  width: 500px;
  height: 500px;
  margin-top: 30px;
  margin-left: auto;
  margin-right: auto;
  border: 0px solid #d92424;
  box-shadow: 0 0 15px #DCDCDC
}

.topsize {
  width: 200px;
  height: 60px;
  padding-left: 30px;
  font-weight: bold;
  color: #FFFFFF;
  background: #1da0fd;
  font-size: 20px;
  text-align: center; /*字体横向居中*/
  line-height: 60px; /*字体竖直居中 跟div高度一致*/
}

.topsize2 {
  width: 120px;
  height: 60px;
  margin-left: 1150px;
  /*padding-left: 30px;*/

  font-weight: bold;
  color: #FFFFFF;
  background: #1da0fd;
  font-size: 15px;
  text-align: center; /*字体横向居中*/
  line-height: 60px; /*字体竖直居中 跟div高度一致*/
}

.codeGeting {
  background: #cdcdcd;
  border-color: #cdcdcd;
}

</style>

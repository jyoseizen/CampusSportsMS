<template>


  <div class="a">
    <div class="b"></div>
    <div class="c">
      <div class="d">
        <!--          <body id="poster">-->
        <!--  <el-button style="margin-left: 1100px;" v-on:click="switch_user()">返回主页</el-button>-->
        <el-form label-position="left" label-width="0px">
          <h1>运动会管理系统</h1>

          <el-form-item>
            <el-input type="text" v-model="usersStnumber" auto-complete="off" placeholder="账号"
                      prefix-icon="el-icon-user" clearable></el-input>
          </el-form-item>

          <el-form-item>
            <el-input type="password" v-model="usersPwd" auto-complete="off" placeholder="密码"
                      prefix-icon="el-icon-lock" show-password></el-input>
          </el-form-item>

          <el-form-item>
            <div style="display: flex">
              <el-input
                prefix-icon="el-icon-key"
                v-model="codetext"
                style="width: 50%"
                placeholder="请输入验证码"
              ></el-input>
              <el-image :src="src" class="image" @click="createCode()" style="height:30px;margin-left:30px"/>
<!--              <img :src="captchaUrl" class="image"  @click="captcha()"/>-->
            </div>
          </el-form-item>

          <el-form-item style="width: 100%">
            <el-button type="primary" class="h" @click="login()">登录
            </el-button>
          </el-form-item>

          <div>

            <el-link type="primary" @click="updatePwd">忘记密码？点击重置</el-link>

          </div>

        </el-form>
      </div>
    </div>
  </div>

</template>

<script>
import {LoginAPI} from '@/request/api';
import md5 from 'js-md5';
import axios from "axios";


export default {
  name: 'Login',
  data() {
    return {

      src:'',
      usersStnumber: '',
      usersPwd: '',
      codetext: '',
      code: "",
      responseResult: [],
      captchaUrl: '',
    }
  },

  created(){
    this.createCode()

  },

  methods: {



    //验证码校验方法
    switch(e){
      let b=""
      for(let i=0;i<e.length;i++){
        if( e[i].charCodeAt()>=65 && e[i].charCodeAt()<=91 ){
          let a=e[i].charCodeAt()+32
          b+=String.fromCharCode(a)
        }else{
          b+=e[i]
        }
      }
      return b;
    },

    //生成验证码
    createCode() {
      let code = '';
      //设置长度，4
      let codeLength = 4;
      //设置随机字符
      const randoms = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
        'R', 'S','T', 'U', 'V', 'W', 'X', 'Y', 'Z','a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
        'r', 's','t', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
      for (let i = 0; i < codeLength; i++) {
        //设置随机数范围,这设置为0 ~ 36
        let index = parseInt(Math.random()*(randoms.length),10);
        //字符串拼接 将每次随机的字符 进行拼接
        code += randoms[index];
      }
      //将拼接好的字符串赋值给展示的code
      this.code = code;
      this.src = 'http://localhost:9090/authCode/code?code='+code
    },

    //登陆
    login() {
      let inputCode = this.switch(this.codetext)
      let codeValue = this.switch(this.code)
      if (inputCode == codeValue) {
        window.sessionStorage.setItem('upd', this.usersPwd)
        LoginAPI({
          params: {
            usersStnumber: this.usersStnumber,
            usersPwd: md5(this.usersPwd),
          }
        }).then(res => {

          console.log(res)
          if (res.data.code !== "200") {
            return this.$message.error('学号或密码错误')
          } else {
            this.$message.success('登陆成功')
            window.sessionStorage.setItem('id', this.usersStnumber)
            window.sessionStorage.setItem('token', res.data.data)
            console.log("登陆返回信息", res)
            console.log("登陆id", window.sessionStorage.getItem('id'))
            this.$router.replace({
              path: '/echartsPage'
            })
          }
        })
      }else {
        this.$message({
          message: '验证码错误',
          type: 'error',
        });
      }

    },

    //重置密码
    updatePwd(){
      console.log("重置密码")
      window.location.hash="#/retrievePwd"
    }
  }
}
</script>

<style scoped>
/*@import "../../assets/bj.png";*/
/*body{*/
/*  !* 设置背景渐变 *!*/
/*  background-image: linear-gradient(to left,*/
/*  #9c88ff,#3cadeb);*/
/*  display: flex;*/
/*  justify-content: center;*/
/*}*/
.a {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  /*margin :auto 185px;*/
  /*position:relative;*/
  /*position:absolute;*/
  /*top: 40px;*/
  width: 1100px;
  height: 550px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, .8);
  display: flex;
  justify-content: center;

}

.b {
  width: 800px;
  height: 550px;
  background-image: url('../../assets/tgs.png');
  /* 让图片适应大小 */
  background-size: cover;
  justify-content: center;
}

.s {
  margin-left: auto;
  margin-right: auto
}

.c {
  width: 300px;
  height: 550px;
  background-color: white;
  display: flex;
  justify-content: center;
  align-items: center;
}

.d {
  width: 250px;
  height: 500px;
}

.d h1 {
  font: 900 30px '';
}


.e {
  width: 230px;
  margin: 20px 0;
  outline: none;
  border: 0;
  padding: 10px;
  border-bottom: 3px solid rgb(80, 80, 170);
  font: 900 16px '';
}

.f {
  float: right;
  margin: 10px 0;
}

.g {
  position: absolute;
  margin: 20px;
  bottom: 40px;
  display: block;
  width: 200px;
  height: 60px;
  font: 900 30px '';
  text-decoration: none;
  line-height: 50px;
  border-radius: 30px;
  background-image: linear-gradient(to left,
  #9c88ff, #3cadeb);
  text-align: center;
}

.h {
  width: 252px;
  height: 45px;
  background-image: linear-gradient(to left,
  #9c88ff, #3cadeb);
  text-align: center;
}


#poster {
  background-position: center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 90px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}

.image {
  margin-left: 10px;
  text-align: center;
  /* line-height: 37px; */
  height: 40px;
}
</style>


<template>
  <div>
    <el-menu
      style="width: 200px; min-height: calc(100vh - 50px)"
      :default-active="path"
      router
      class="el-menu-vertical-demo"
    >

      <el-menu-item index="/echartsPage">
        <i class="el-icon-s-opportunity"></i>
        <template #title>报名详情</template>
      </el-menu-item>


      <el-submenu index="1" v-if="this.role == 'superAdmin'">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>权限管理</span>
        </template>
        <el-menu-item index="/superAdmin">
<!--          <i class="el-icon-menu"></i>-->
          <template #title>权限管理</template>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="2" v-if="this.role == 'admin'|| this.role == 'superAdmin'">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>用户管理</span>
        </template>
        <el-menu-item index="/index">
          <template #title>用户管理</template>
        </el-menu-item>
      </el-submenu>

      <el-submenu index="3"  v-if="this.role == 'admin'|| this.role == 'superAdmin'">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>比赛管理</span>
        </template>
        <el-menu-item index="/manage">比赛项目管理</el-menu-item>
        <el-menu-item index="/enroll">比赛报名管理</el-menu-item>
        <el-menu-item index="/site">比赛场地管理</el-menu-item>
      </el-submenu>

      <el-submenu index="4" v-if="this.role == 'admin'|| this.role == 'superAdmin'">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>学院管理</span>
        </template>
        <el-menu-item index="/college">学院管理</el-menu-item>
      </el-submenu>

      <el-submenu index="5" v-if="this.role == 'admin' || this.role == 'superAdmin'">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>加油稿管理</span>
        </template>
        <el-menu-item index="/draft">加油稿管理</el-menu-item>
      </el-submenu>

      <el-menu-item index="/uManage">
        <i class="el-icon-circle-plus"></i>
        <template #title>比赛报名</template>
      </el-menu-item>

      <el-menu-item index="/college_integrate">
        <i class="el-icon-s-data"></i>
        <template #title>学院积分排行</template>
      </el-menu-item>


      <el-menu-item index="/uStudent">
        <i class="el-icon-user"></i>
        <template #title>个人参赛项目</template>
      </el-menu-item>

      <el-menu-item index="/udrafted">
        <i class="el-icon-s-promotion"></i>
        <template #title>发布加油稿</template>
      </el-menu-item>



      <el-menu-item index="/udraft">
        <i class="el-icon-share"></i>
        <!--        加油稿-->
        <template #title>加油稿展示</template>
      </el-menu-item>

    </el-menu>

  </div>
</template>
<script>
import {GetRoleAPI} from '@/request/api';
export default {
  name: "Aside",
  data() {
    return {
      user: {},
      path: this.$route.path,
      role: '',
    }
  },
  created() {
    this.getRole()
  },

  methods:{
    //获取登录用户角色（权限）
    getRole(){
      GetRoleAPI({
        params:{
          usersStnumber: window.sessionStorage.getItem('id')
        }
      }).then(res =>{

        this.role = res.data.data
        console.log("角色回调role==》",this.role)
      })
    },
  },

};
</script>
<style scoped>

</style>

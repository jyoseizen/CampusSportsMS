import Vue from 'vue'
import Router from 'vue-router'
import Login from '../views/login/Login'
import NotFound from "../views/404/NotFound";
import Layout from "../components/layout/Layout";
import Manage from "../views/manage/Manage";
import EchartsPage from "../views/echartsPage/EchartsPage";
import College from "../views/college/College";
import College_integrate from "../views/college_integrate/College_integrate";
import Draft from "../views/draft/Draft";
import UDraft from "../views/users/users_draft/UDraft"
import UDrafted from "../views/users/users_drafted/UDrafted";
import UManage from "../views/users/users_manage/UManage";
import Enroll from "../views/enroll/Enroll";
import UStudent from "../views/users/users_student/UStudent";
import Site from "../views/site/Site";
import RetrievePwd from "../views/retrievePwd/RetrievePwd";
import SuperAdmin from "../views/superAdmin/SuperAdmin";


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Layout',
      component: Layout,
      //重定向
      redirect: "/login",
      children: [
        //首页
        {
          path: 'echartsPage',
          name: 'EchartsPage',
          component: () => import("@/views/echartsPage/EchartsPage"),
        },
        //用户页
        {
          path: 'superAdmin',
          name: 'SuperAdmin',
          component: () => import("@/views/superAdmin/SuperAdmin"),
        },
        //用户页
        {
          path: 'index',
          name: 'Index',
          component: () => import("@/views/index/Index"),
        },
        //比赛项目管理
        {
          path: 'manage',
          name: 'Manage',
          component: () => import("@/views/manage/Manage"),
        },
        //比赛报名管理
        {
          path: 'enroll',
          name: 'Enroll',
          component: () => import("@/views/enroll/Enroll"),
        },
        //比赛场地管理
        {
          path: 'site',
          name: 'Site',
          component: () => import("@/views/site/Site"),
        },
        //学院管理
        {
          path: 'college',
          name: 'College',
          component: () => import("@/views/college/College"),
        },
        //学院积分排名管理
        {
          path: 'college_integrate',
          name: 'College_integrate',
          component: () => import("@/views/college_integrate/College_integrate"),
        },
        //加油稿管理
        {
          path: 'draft',
          name: 'Draft',
          component: () => import("@/views/draft/Draft"),
        },
        //(用户)加油稿管理
        {
          path: 'udraft',
          name: 'UDraft',
          component: () => import("@/views/users/users_draft/UDraft"),
        },
        //(用户)报名界面
        {
          path: 'uManage',
          name: 'UManage',
          component: () => import("@/views/users/users_manage/UManage"),
        },
        //(用户)报名名单
        {
          path: 'uStudent',
          name: 'UStudent',
          component: () => import("@/views/users/users_student/UStudent"),
        },
        //(用户)个人发布加油稿
        {
          path: 'udrafted',
          name: 'UDrafted',
          component: () => import("@/views/users/users_drafted/UDrafted"),
        },
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import("@/views/login/Login")
    },
    {
      path: '/retrievePwd',
      name: 'RetrievePwd',
      component: () => import("@/views/retrievePwd/RetrievePwd")
    },
    {
      // 404页面
      path: '*',
      component: NotFound
    }
  ]
})

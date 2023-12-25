import request from "./request";

//登陆页面
//登陆方法
export const LoginAPI = (params) => request.get('/login/doLogin', params);

//获取验证码
export const Uselect = (params) => request.get('college/selectCollegeName', params);


//菜单路由
//获取用户角色（权限）
export const GetRoleAPI = (params) => request.get('role/selectValue', params);


//重置密码页
//获取邮件验证码
export const UgetEmailAPI = (params) => request.get('/authCode/getEmail', params);

//重置密码
export const UgupdatePwdAPI = (params) => request.get('/authCode/updatePwd', params);


//修改权限
//获取列表
export const getSuperAPI = (params) => request.get('/role/search', params);

//删除
export const deleteSuperAPI = (params) => request.get('/role/deleteBy', params);

//修改
export const updateSuperAPI = (params) => request.get('/role/updateBy', params);


//顶部用户信息
//获取用户信息
export const UselectByIdAPI = (params) => request.get('/users/selectBySn', params);

//用户信息修改
export const UpdatePwdAPI = (params) => request.get('/users/updateUsersPwd', params);



//报名详情页面
//报名人数
export function EgetGuantityAPI(params) {
  return request({
    url: '/usersNum/selectSex',
    method: 'get',
    params: params
  })
}

//图表数据
export function EgetEchartsAPI(params) {
  return request({
    url: '/echarts/getList',
    method: 'get',
    params: params
  })
}

//图表数据(饼图)
export function EgetEcharts2API(params) {
  return request({
    url: '/echarts/getList2',
    method: 'get',
    params: params
  })
}


//学院积分排行数据
export function EgetRankingAPI(params) {
  return request({
    url: '/ranking/selectRanking',
    method: 'get',
    params: params
  })
}

//男女报名人数饼图数据
export function EgetSortAPI(params) {
  return request({
    url: '/usersSort/selectSum',
    method: 'get',
    params: params
  })
}


//用户页面
//图表数据
export function UgetListAllAPI(params) {
  return request({
    url: '/usersSex/All',
    method: 'get',
    params: params
  })
}

//请求列表
export const UgetListAPI = (params) => request.get('/users/search', params);

//条件查询
export const UselectAllAPI = (params) => request.get('/users/selectBySn', params);

//修改
export const UupdateAPI = (params) => request({
  url: '/users/updateUsers',
  method: 'put',
  params: params
})

//新增
export const UinsertAPI = (params) => request.post('/users/insertUsers', params);
// export const UinsertAPI = (params) => request({
//   url: '/users/insertUsers',
//   method: 'post',
//   params: params
// });

//学号删除
export const UdeleteAPI = (usersStnumber) => request.delete('/users/deleteUsers', {
  params: {
    usersStnumber
  }
});

//学号批量删除
export const UdeleteListAPI = (params) => request.delete('/users/deleteUsers', params);

//导入信息
export const UimportAPI = (params) => request.post('/users/upload', params);



//比赛页面
//请求列表
export const MgetLisetAPI = (params) => request.get('/competition/search', params);

//条件查询
export const MselectAll = (params) => request.get('/competition/selectByNb', params);

//新增
export const CinsertAPI = (params) => request.post('/competition/insertCompetition', params);

//修改
export const CupdateAPI = (params) => request({
  url: '/competition/updateScore',
  method: 'put',
  params: params
});

//删除
export const CdeleteAPI = (competitionNb) => request.delete('/competition/deleteCompetition', {
  params: {
    competitionNb
  }
});

//获取比赛地点（字典）
export const CselectSiteAPI = (params) => request.get('/site/selectSite', params);


//用户报名比赛项目页面
//用户报名
export const ApplyAPI = (params) => request.get('/competition/updateCompetition', params);



//用户报名比赛列表
//获取列表
export const seleteStudentAPI = (params) => request.get('/enroll/selectBySber', params);

//用户取消比赛报名
export const studentDeleteAPI = (params) => request.get('/applicationfrom/cancel', params);
// export const studentDeleteAPI = (usersStnumber) => request.delete('/applicationfrom/cancel', {
//   params: {
//     usersStnumber
//   }
// });



//比赛报名表
//条件分页查询
export const EselectAll = (params) => request.get('/enroll/selectEnAll', params);

//更新数据
export const EselectList = (params) => request.get('enroll/getlist', params);

//比赛录入成绩打分
export const EupdateAPI = (params) => request({
  url: '/enroll/updateCompetition',
  method: 'put',
  params: params
})



//学院管理页面
//请求列表
export const CogetLisetAPI = (params) => request.get('/college/search', params);

//新增
export const CoinsertAPI = (params) => request({
  url: '/college/insertCollege',
  method: 'post',
  params: params
});

//修改
export const CoupdateAPI = (params) => request({
  url: '/college/updateCollege',
  method: 'put',
  params: params
});

//删除
export const CodeleteAPI = (params) => request.get('/college/deleteCollege', params);
// export const CodeleteAPI = (params) => request.delete('/college/deleteCollege', {
//   params: {
//     collegeNumber
//   }
// });


//比赛场地管理页面
//请求列表
export const SgetLisetAPI = (params) => request.get('/site/search', params);

//新增
export const SinsertAPI = (params) => request({
  url: '/site/insertSite',
  method: 'post',
  params: params
});

//修改
export const SupdateAPI = (params) => request({
  url: '/site/updateSite',
  method: 'put',
  params: params
});

//删除
export const SdeleteAPI = (siteId) => request.delete('/site/deleteSite', {
  params: {
    siteId
  }
});



//加油稿管理页面
//请求列表
export const DgetLisetAPI = (params) => request.get('/draft/search', params);

//条件查询
export const DselectStateAPI = (params) => request.get('/draft/selectBySm', params);

//状态修改
export function DupdateStateAPI(draftId, draftState) {
  return request({
    url: '/draft/updateState',
    params: {
      draftId: draftId,
      draftState: draftState
    },
    method: 'get'

  })
}

//加油稿删除
export function DdeleteAPI(draftId) {
  return request({
    url: '/draft/delete',
    params: {
      draftId
    },
    method: 'delete'

  })
}

//新增加油稿
export const DinsertAPI = (params) => request.post('/draft/insertDraft', params);


//(用户)加油稿页面
//请求列表
export const UDgetLisetAPI = (params) => request.get('/draft/selectImgUrl', params);

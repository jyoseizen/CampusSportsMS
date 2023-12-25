
export const studentAvue = {

  border: true,                   //显示递增id
  index: true,
  indexLabel: '序号',
  stripe: true,
  calcHeight: 30,
  searchMenuSpan: 4,
  // menuAlign: 'center',
  searchSpan: 5,
  searchShowBtn: false,     //控制搜索折叠的按钮
  // selection:true,  //开启多选功能
  dialogDrag:true, //表单拖拽
  // emptyText: '暂无报名项目',

  // title:'表格的标题',
  page: false,
  addBtn: false,
  align: 'center',
  menuAlign: 'center',
  editBtn:false,
  delBtnText: '取消报名',
  formslot: true,
  column: [

    {
      label: '报名学号',
      prop: 'usersStnumber',
      hide: true,   //表单查询是否显示
    },
    {
      label: '比赛编号',
      prop: 'competitionNb',
      hide: true,   //表单查询是否显示
      editDisabled: true,   //编辑的时候不能修改
      rules: [{
        required: true,
        message: '比赛编号不能为空',
        trigger: 'blur'
      }, ]
    },
    {
      label: '比赛名称',
      prop: 'competitionNe',
      rules: [{
        required: true,
        message: '比赛名称不能为空',
        trigger: 'blur'
      }, ],
      // search: true,   //搜索栏

      //开启自定义搜索
      // searchslot:true,
      // search:true,
    },
    {
      label: '比赛开始时间',
      prop: 'competitionStart',
      type: "datetime",
      // search: true,
      format: "yyyy-MM-dd hh:mm:ss",
      valueFormat: "yyyy-MM-dd hh:mm:ss",
      rules: [{
        required: true,
        message: '比赛开始时间不能为空',
        trigger: 'blur'
      }]
    }, {
      label: "比赛结束时间",
      prop: "competitionEnd",
      type: "datetime",
      // search: true,
      format: "yyyy-MM-dd hh:mm:ss",
      valueFormat: "yyyy-MM-dd hh:mm:ss",
      rules: [{
        required: true,
        message: '比赛结束时间不能为空',
        trigger: 'blur'
      }]
    },

    {
      label: '状态',
      prop: 'state',
      type: '',
    },

    {
      label: '比赛地点',
      prop: 'competitionSite',
      type: 'select',
      rules: [{
        required: true,
        message: '比赛地点不能为空',
        trigger: 'blur'
      }],
    },


  ]
}

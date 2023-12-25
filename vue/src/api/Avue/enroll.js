// 设置比赛编号的验证规则
const checkNb = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入比赛编号'))
  } else {
    // const reg = /^(20)d{12}$/;
    const reg = /^glxy[0-9]\d{5}$/;
    // if (reg.test(value)) {
      callback()
    // } else {
    //   return callback(new Error('请输入正确的比赛编号'))
    // }
  }
}

// 设置比赛名字的验证规则
const checkNe = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入比赛编号'))
  } else {
    // const reg = /^(20)d{12}$/;
    const reg = /^[\u4e00-\u9fa50-9]{4,12}$/;
    // if (reg.test(value)) {
      callback()
    // } else {
    //   return callback(new Error('请输入正确的比赛编号'))
    // }
  }
}

// 设置参赛人数的验证规则
const checkPe = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入参赛人数'))
  } else {
    // const reg = /^(20)d{12}$/;
    const reg = /^40$|^([1-3]\d)$|^\d?$/;
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正确的参赛人数'))
    }
  }
}


export const enrollAvue = {

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

  // title:'表格的标题',
  page: false,
  addBtn: false,
  delBtn: false,
  editBtnText: '录入成绩',
  align: 'center',
  menuAlign: 'center',
  formslot: true,
  column: [
    {
      label: '比赛编号',
      prop: 'competitionNb',
      hide: true,   //表单查询是否显示
      editDisabled: true,   //编辑的时候不能修改
      rules: [{
        required: true,
        message: '比赛编号不能为空',
        trigger: 'blur'
      }, {
        validator: checkNb,
        trigger: 'blur'
      }]
    },
    {
      label: '比赛名称',
      prop: 'competitionNe',
      editDisabled: true,   //编辑的时候不能修改
      rules: [{
        required: true,
        message: '比赛名称不能为空',
        trigger: 'blur'
      }, {
        validator: checkNe,
        trigger: 'blur'
      }],
    },
    {
      label: '比赛开始时间',
      prop: 'competitionStart',
      editDisabled: true,   //编辑的时候不能修改
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
      editDisabled: true,   //编辑的时候不能修改
      type: "datetime",
      hide: true,   //表单查询是否显示
      // search: true,
      format: "yyyy-MM-dd hh:mm:ss",
      valueFormat: "yyyy-MM-dd hh:mm:ss",
      rules: [{
        required: true,
        message: '比赛结束时间不能为空',
        trigger: 'blur'
      }]
    }
    ,

    {
      label: '比赛地点',
      prop: 'competitionSite',
      editDisabled: true,   //编辑的时候不能修改
      type: 'select',
    },
    {
      label: '姓名',
      prop: 'usersName',
      editDisabled: true,   //编辑的时候不能修改
    },
    {
      label: '学号',
      prop: 'usersStnumber',
      hide: true,   //表单查询是否显示
      editDisabled: true,   //编辑的时候不能修改
    },
    {
      label: '性别',
      prop: 'usersSex',
      dicData: [{
        label: '男',
        value: 0
      }, {
        label: '女',
        value: 1
      }],
      editDisabled: true,   //编辑的时候不能修改
      addDisabled:false
    },
    {
      label: '号码',
      prop: 'usersPhone',
      hide: true,   //表单查询是否显示
      editDisabled: true,   //编辑的时候不能修改
      addDisabled:false
    },
    {
      label: '学院',
      prop: 'collegeName',
      editDisabled: true,   //编辑的时候不能修改
      addDisabled:false
    },
    {
      label: '成绩',
      prop: 'competitionScore',
      cell: true,
    },
    {
      label: '积分',
      prop: 'competitionIntegral',
      cell: true,
      type: 'select',
      dicData:[
        {
          label: '3',
          value: '3'
      },
        {
          label: '2',
          value: '2'
        },
        {
          label: '1',
          value: '1'
        },
      ]
    },

  ]
}

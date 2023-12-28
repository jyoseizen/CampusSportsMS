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
    callback(new Error('请输入比赛名称'))
  } else {
    // const reg = /^(20)d{12}$/;
    const reg = /^[\u4e00-\u9fa50-9]{4,12}$/;
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正确的比赛名称'))
    }
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


export const manageAvue = {

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
  addBtnText:'发布比赛',

  // title:'表格的标题',
  page: false,
  addBtn: false,
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
      rules: [{
        required: true,
        message: '比赛名称不能为空',
        trigger: 'blur'
      }, {
        validator: checkNe,
        trigger: 'blur'
      }],
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
    }
    , {
      label: "报名开始时间",
      prop: "applyStart",
      type: "datetime",
      hide: true,   //表单查询是否显示
      // search: true,
      format: "yyyy-MM-dd hh:mm:ss",
      valueFormat: "yyyy-MM-dd hh:mm:ss",
      rules: [{
        required: true,
        message: '报名开始时间不能为空',
        trigger: 'blur'
      }]
    }
    , {
      label: "报名结束时间",
      prop: "applyEnd",
      type: "datetime",
      hide: true,   //表单查询是否显示
      // search: true,
      format: "yyyy-MM-dd hh:mm:ss",
      valueFormat: "yyyy-MM-dd hh:mm:ss",
      rules: [{
        required: true,
        message: '报名结束时间不能为空',
        trigger: 'blur'
      }]
    }
    ,
    {
      label: '比赛地点',
      prop: 'competitionSite',
      type: 'select',
      rules: [{
        required: true,
        message: '比赛地点不能为空',
        trigger: 'blur'
      }],
      dicData: [{
        label: '田径场A区',
        value: '田径场A区'
      }, {
        label: '田径场B区',
        value: '田径场B区'
      },
        {
          label: '田径场C区',
          value: '田径场C区'
        },
        {
          label: '田径场D区',
          value: '田径场D区'
        },
        {
          label: '田径场E区',
          value: '田径场E区'
        },
        {
          label: '田径场F区',
          value: '田径场F区'
        },
        {
          label: '风雨棚A区',
          value: '风雨棚A区'
        },
        {
          label: '风雨棚B区',
          value: '风雨棚B区'
        },
        {
          label: '风雨棚C区',
          value: '风雨棚C区'
        },
        {
          label: '学生活动中心A区',
          value: '学生活动中心A区'
        },
        {
          label: '学生活动中心B区',
          value: '学生活动中心B区'
        },
        {
          label: '学生活动中心C区',
          value: '学生活动中心C区'
        },

      ]
    },

    {
      label: '参赛人数',
      prop: 'competitionPe',
      hide: true,//表单查询时是否显示
      rules: [{
        required: true,
        message: '参赛人数不能为空',
        trigger: 'blur'
      }, {
        validator: checkPe,
        trigger: 'blur'
      }],
    },

    {
      label: '剩余名额',
      prop: 'competitionTnor',
      editDisplay: false,  //编辑时是否显示
      addDisplay: false,   //新增时是否显示


    },
  ]
}

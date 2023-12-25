// 设置学院编号的验证规则
const checkNb = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入学院编号'))
  } else {
    const reg = /^.+$/;
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正确的学院编号'))
    }
  }
}

// 设置学院名称的验证规则
const checkNe = (rule, value, callback) => {
  if (!value) {
    callback(new Error('请输入学院名称'))
  } else {
    // const reg = /^(20)d{12}$/;
    const reg = /^[\u4e00-\u9fa5]{3,12}$/;
    if (reg.test(value)) {
      callback()
    } else {
      return callback(new Error('请输入正确的学院名称'))
    }
  }
}

export const siteAvue = {

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
  addBtn: true,
  align: 'center',
  menuAlign: 'center',
  formslot: true,
  column: [
    {
      label: '场地id',
      prop: 'siteId',
      display: false,
      hide: true,

      showColumn:false
},
    {
      label: '场地编号',
      prop: 'siteNumber',
      // hide: true,   //表单查询是否显示
      // editDisabled: true,   //编辑的时候不能修改
      rules: [{
        required: true,
        message: '学院编号不能为空',
        trigger: 'blur'
      }, {
        validator: checkNb,
        trigger: 'blur'
      }]
    },
    {
      label: '场地位置',
      prop: 'competitionSite',
      rules: [{
        required: true,
        message: '学院名称不能为空',
        trigger: 'blur'
      }, {
        validator: checkNe,
        trigger: 'blur'
      }],

    },
  ]
}

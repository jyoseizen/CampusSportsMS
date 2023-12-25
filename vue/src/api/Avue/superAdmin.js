export const superAvue = {

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
  dialogDrag: true, //表单拖拽

  // title:'表格的标题',
  page: false,
  addBtn: false,
  align: 'center',
  menuAlign: 'center',
  formslot: true,
  column: [
    {
      label: '用户学号',
      prop: 'usersStnumber',
      editDisabled: true,   //编辑的时候不能修改
    },
    {
      label: '用户姓名',
      prop: 'usersName',
      // hide: true,   //表单查询是否显示
      editDisabled: true,   //编辑的时候不能修改

    },
    {
      label: '用户角色',
      prop: 'roleValue',
      type: 'select',
      dicData: [
        {
          label: '普通用户',
          value: 'user'
        },
        {
          label: '管理员',
          value: 'admin'
        },
        {
          label: '超级管理员',
          value: 'superAdmin'
        },
      ]
    },
  ]
}

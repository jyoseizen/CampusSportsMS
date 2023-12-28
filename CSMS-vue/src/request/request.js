import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost:9090',
  timeout: 5000
})

//请求拦截器
//有些接口需要拿到token
instance.interceptors.request.use(config => {
  // let token = localStorage.getItem('token');
  config.headers.token = window.sessionStorage.getItem('token')
  return config;
}, err => {
  return Promise.reject(err)
});

//响应拦截器
instance.interceptors.response.use(response => {

  return response;
},
    err => {
  window.sessionStorage.clear()
  // this.router.push('/login')
      window.location.hash="#/login"
      this.$message.error('账号访问异常')
  // this.$router.replace({
  //   path: '/echartsPage'
  // })
  return Promise.reject(err)
});

//整体导出
export default instance;

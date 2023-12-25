import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from 'vue-router'
import axios from 'axios'
import Avue from '@smallwei/avue';
import '@smallwei/avue/lib/index.css';
// import echarts from 'echarts';
import * as echarts from 'echarts';

Vue.prototype.$echarts = echarts;
Vue.use(Avue);
Vue.prototype.axios = axios
Vue.use(VueRouter);
Vue.use(ElementUI,{size: 'small' });
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})



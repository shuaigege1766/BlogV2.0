import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
//elementui组件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
//全局css
import './assets/css/global.css';
//axios组件
import axios from 'axios'
import VueAxios from 'vue-axios'
//markdown编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
//echarts
import ECharts from "vue-echarts";
import "echarts/lib/chart/line";
import "echarts/lib/chart/pie";
import "echarts/lib/chart/bar";
import "echarts/lib/component/tooltip";
import "echarts/lib/component/legend";
import "echarts/lib/component/title";
Vue.component("v-chart", ECharts);
Vue.use(VueAxios, axios)
Vue.use(mavonEditor);
Vue.use(ElementUI)
Vue.config.productionTip = false
router.beforeEach((to, from, next) => {
  if (to.path == "/login") {
    next();
  } else {

    let username= store.state.userInfo.username;
    if(username=="" || username==null){
      next("/login");
    }
    next();
  }
})


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
// 挂载路由守卫 就像后端的拦截器  没有那么多功能 这里只有简单的路由跳转
// beforeEach 每个请求之前  afterEach 每个请求之后 一般用beforeEach


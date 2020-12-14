import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    collapse: false, //折叠按钮状态
    userRoles:[],//用户角色集合
    userInfo:{},//用户信息列表
    //菜单列表
    menuList:[],

  },
  mutations: {
    //登录保存用户信息
    login(data, user) {
      data.userInfo=user;
    },
    //保存用户的角色信息
    userRoles(data,userRole){
     data.userRoles=userRole;
    } ,
    menuList(data,menuList){
       data.menuList=menuList
    },
    logout(state) {
      state.userInfo={}
    },
  },
  actions: {
  },
  modules: {
  },
  plugins: [
    createPersistedState({
      storage: window.sessionStorage
    })
  ]
})

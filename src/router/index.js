import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: "Home" ,
    component:()=>import("../views/Home"),
  },
    {
    path: '/login',
    name: 'Login',
    component: Login
   },
   // 首页
   {
    path:"/main",
    name:"Main",
    component: ()=>import('../components/Main'),
     children:[ {
       path:"/home",
       name:"首页",
       component:()=>import("../views/Home"),
     },
       {
         path:"/userList",
         name:"用户列表",
         component:()=>import("../views/user/Users"),
       },{
         path:"/userLoginInfo",
         name:"用户登录信息",
         component:()=>import("../views/user/UserLoginInfo"),
       },
       {
         path:"/assignAPI",
         name:"api权限分配",
         component:()=>import("../views/assign/AssignAPI")
       },
       {
         path:"/assignMenu",
         name:"菜单权限分配",
         component:()=>import("../views/assign/AssignMenu")
       },
       {
         path:"/assignRole",
         name:"角色分配",
         component:()=>import("../views/assign/AssignRole")
       },
       {
         path:"/blogList",
         name:"博客列表",
         component:()=>import("../views/blog/BlogList")
       }, {
         path:"/blogAdd",
         name:"新增博客",
         component:()=>import("../views/blog/BlogAdd")
       },
       {
         path:"/category",
         name:"分类管理",
         component:()=>import("../views/blog/Category")
       },
       {
         path:"/tag",
         name:"标签管理",
         component:()=>import("../views/blog/Tag")
       }, {
         path:"/comment",
         name:"评论列表",
         component:()=>import("../views/messageInfo/Comment")
       }, {
         path:"/message",
         name:"留言列表",
         component:()=>import("../views/messageInfo/Message")
       },  {
         path:"/about",
         name:"关于我",
         component:()=>import("../views/other/About")
       },  {
         path:"/friendLink",
         name:"友链",
         component:()=>import("../views/other/FriendLink")
       }, {
         path:"/swagger",
         name:"接口文档",
         component:()=>import("../views/other/Swagger")
       }, {
         path:"/settings",
         name:"个人中心",
         component:()=>import("../views/PersonalSettings")
       },
       {
          path:"/article/*",
          name:"修改文章",
         component:()=>import("../views/blog/BlogAdd")
       } ,
       {
         path:"/swaggerApi/*",
         name:"APIManage",
         component:()=>import("../views/assign/APIManage1")
       }
     ]
   },


]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router

<template>

  <el-container style="height: 100vh">
    <el-header>
      <NavBar/>
    </el-header>

    <el-container  width="auto">

      <el-aside  :width="isHide">
        <SideBar/>
      </el-aside>

      <el-main>

        <el-breadcrumb separator="/" style=" padding-bottom: 10px ;font-size: 14px">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>活动管理</el-breadcrumb-item>
          <el-breadcrumb-item>活动列表</el-breadcrumb-item>
        </el-breadcrumb>


<!--         路由视图-->
        <router-view></router-view>
      </el-main>
    </el-container>

  </el-container>

</template>

<script>
  import NavBar from "../components/NavBar";
  import SideBar from "../components/SideBar";
  export default {
    name: "Main" ,
    components:{
     NavBar,
     SideBar,
    },
    computed:{
      isHide(){
        return this.$store.state.collapse ? "60px" : "200px";
      }
    },
    data(){
      return{
        breadcrumbList:[] ,
      }
    }
  }
</script>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
  }

  .el-aside {
    background-color: #304156;
    color: #333;
    transition:  0.45s;
  }

  .el-main {
    background-color: #E9EEF3;
    color: #333;
  }

  
  


  
</style>

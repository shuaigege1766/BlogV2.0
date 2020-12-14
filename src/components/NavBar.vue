<template>
  <div style="height: 100%">
   <div class="nav">
<!--     左侧logo盒子-->
      <div class="nav-logo">
        <span class="logo">博客后台管理系统</span>
        <span class="logo">   当前用户名：<span v-for="item in userRole">{{item}}</span></span>
      </div>
<!--     右侧头像盒子-->
     <div class="nav-avatar">
       <el-dropdown  >
       <span class="el-dropdown-link">
       <el-avatar class="el-avatar"
                  shape="square"
                  :size="55"
                  :src="avatarUrl"
       > </el-avatar>
       </span>
         <el-dropdown-menu slot="dropdown">
           <el-dropdown-item >个人中心</el-dropdown-item>
           <el-dropdown-item divided ><span @click="logout">注销登录</span></el-dropdown-item>
         </el-dropdown-menu>
       </el-dropdown>
     </div>
   </div>
  </div>
</template>

<script>
  export default {
    name: "NavBar",
    created() {
      this.getUserRole();
    },
    data() {
      return {
        avatarUrl: "http://img.zcool.cn/community/010e9557f74cbba84a0d304faa657c.jpg",
        userRole: [],
      }

    },
    methods: {
      getUserRole() {
        this.userRole = this.$store.state.userRoles;
        this.avatarUrl = this.$store.state.userInfo.avatar;
      },
      logout() {
          this.$store.commit("logout");
          this.$router.push({path: "/login"});
          this.$message.success("注销登录成功");
      },
    }
  }
</script>

<style scoped>
 .nav{
   display: flex;
   justify-content: space-between;
   align-items: center;
   padding-left: 15px;
   padding-right: 30px;
   height: 100%;
 }
 .el-dropdown-link {
   cursor: pointer;
   color: #409EFF;
 }

  .nav-avatar{
    width: 60px;
    height: 100%;
    display: flex;
    align-items: center;
    
  }
  .el-avatar{
    border-radius: 50%;
  }
  .nav-logo{
    font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
  }
  .logo{
    color: #333333; text-shadow:0 0 5px #CCCCCC, 0 0 10px #CCCCCC, 0 0 15px #CCCCCC, 0 0 20px #095816, 0 0 25px #095816, 0 0 30px #095816, 0 0 50px #095816, 0 0 80px #095816, 0 0 100px #095816, 0 0 150px #095816著作权归作者所有。


  }

</style>

<template>
    <div class="container">

      <div class="login">
        <div class="login-title">管理员后台登录</div>
        <el-form :model="loginForm"
                 status-icon :rules="rules"
                 ref="loginForm"
                 class="login-form"
                >
          <el-form-item  prop="username">
            <el-input type="text" v-model="loginForm.username"
                      placeholder="用户名"
                      prefix-icon="el-icon-user-solid"
                      autocomplete="off">
            </el-input>
          </el-form-item>
          <el-form-item  prop="password">
            <el-input type="password"
                      v-model="loginForm.password"
                      placeholder="密码"
                      show-password
                      prefix-icon="el-icon-lock"
                      autocomplete="off">
            </el-input>
          </el-form-item>


          <el-form-item>
            <el-button type="primary" @click="submitForm('loginForm')">提交</el-button>
            <el-button @click="resetForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
</template>

<script>
  import {login} from "../api/login";

  export default {
    data() {
      return {
        loginForm: {
          username: 'admin',
          password: 'admin',
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 3, max: 20, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
        }
      };
    },
    methods: {
     async submitForm(formName) {
        // login(this.loginForm);
        //const {data}=await this.axios.post("/api/login",this.loginForm) ;
       
       // console.log(data.message) ;
        this.$refs[formName].validate((valid) => {
          //表单合法
          if  ( valid) {
            // this.axios.post("/api/login",this.loginForm)
            //        .then(({data})=>{
            //          //登录返回成功的话
            //          if(data.success){
            //            console.log(data)
            //            this.$message.success(data.message);
            //            //不用session存了 换VueX来存储
            //          this.$store.commit("login", data.data.user.user);
            //          this.$store.commit("userRoles", data.data.user.authorities);
            //          //保存用户信息（）
            //          // let userRole=JSON.stringify(data.data.user.authorities);
            //          // let userAuth=data.data.user.user;
            //          // window.sessionStorage.setItem("userRole", userRole);
            //          // window.sessionStorage.setItem("userAuth", userAuth);
            //          this.$router.push("/home");
            //        }else{
            //          this.$message.error(data.message);
            //        }
            //    }).catch(()=>{
            //       this.$message.error("后台端口未开启或者接口不存在请联系管理员");
            //   });
               login(this.loginForm).then(({data})=>{
                   if(data.success){
                     this.$store.commit("login",data.data.user.user);
                     this.$store.commit("userRoles", data.data.user.authorities[0].authority);
                     this.$message.success(data.message);
                     this.$router.push("/home");
                   }else {
                     this.$message.error(data.message);
                   }
               })
          }
        });
      },
      resetForm() {
           this.loginForm.username='';
           this.loginForm.password='';
      }
    }
  }
</script>

<style>
  html,body{
    padding: 0;
    margin: 0;
  }
  .container{
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    position: absolute;
    background: url("../assets/login.jpg") no-repeat;
    background-size: cover;
  }
  .login{
    position: absolute;
    top: 0;
    bottom: 0;
    right: 0;
    background: #fff;
    padding: 170px 20px 180px;
    width: 350px;

  }
  .login-form{
    margin-top: 1.2rem;
    text-align: center;

  }
  .login-title {
    color: #303133;
    font-weight: bold;
    font-size: 1rem;
    text-align: center;

  }
  

</style>

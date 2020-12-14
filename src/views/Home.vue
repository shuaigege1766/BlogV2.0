<template>
  <div>
  <div class="main-card">

      <el-card shadow="hover">
        <div class="card-icon-container">
          <i class="el-icon-s-comment" style="color:#40C9C6" />
        </div>
        <div class="card-desc">
          <div class="card-title">访问量</div>
          <div class="card-count">99</div>
        </div>
      </el-card>


      <el-card shadow="hover">
        <div class="card-icon-container">
          <i class="el-icon-s-comment" style="color:#36A3F7" />
        </div>
        <div class="card-desc">
          <div class="card-title">留言量</div>
          <div class="card-count">99</div>
        </div>
      </el-card>


      <el-card shadow="hover">
        <div class="card-icon-container">
          <i class="el-icon-s-comment" style="color:#34BFA3" />
        </div>
        <div class="card-desc">
          <div class="card-title">用户量</div>
          <div class="card-count">99</div>
        </div>
      </el-card>


      <el-card shadow="hover">
        <div class="card-icon-container">
          <i class="el-icon-s-comment" style="color:#F4516C" />
        </div>
        <div class="card-desc">
          <div class="card-title">文章量</div>
          <div class="card-count">99</div>
        </div>
      </el-card>
  </div>


  </div>
</template>

<script>
  export default {
    name: "Home",
    data(){
      return{

      }
    } ,
    methods:{

    }
    ,
    mounted() {

    }
  }
</script>

<style scoped>
  .main-card{
    display: flex;
    justify-content: space-evenly;
    margin-top: 20px;
  }
 .el-card{
   width: 250px;
   float: left;
   align-items: center;
   display: block;
 }
 .sub-card{

 }
.card-icon-container {
  font-size: 3rem;
  display: inline-block;

  
}
.card-desc {
  font-weight: bold;
  float: right;
}
.card-title {
  margin-top: 0.3rem;
  line-height: 18px;
  color: rgba(0, 0, 0, 0.45);
  font-size: 1rem;
}
.card-count {
  margin-top: 0.75rem;
  color: #666;
  font-size: 1.25rem;
}
  ..echarts {
    width: 100%;
    height: 100%;
  }
</style>

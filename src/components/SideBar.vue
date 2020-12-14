<template>
  <div class='side-container' >
    <div class="collapse" >
      <i  @click="collapse" :class="isFold"/>
    </div>
    <el-menu

            default-active="1"
            class="el-menu-sidebar"
            background-color="#304156"
            text-color="#BFCBD9"
            active-text-color="#409EFF"
            router
            collapse-transition
            :collapse="this.$store.state.collapse"
            :unique-opened="true">
      <menu-tree :menuList="this.menuList"></menu-tree>

    </el-menu>
  </div>


</template>

<script>
  import MenuTree from "./MenuTree";
  import {getMenuList} from "../api/menu";
  export default {
    created() {
      //获取展示菜单
      //判断缓存里面有没有菜单列表 有的话就直接赋值
      // 没有的话重新请求
      const {menuList}= this.$store.state.menuList;
       if( menuList!=null){
         this.menuList=menuList;
       }else {
         this.getMenuList();
       }

    } ,
    name: "SidBar" ,
    components:{
      MenuTree,
    },
    data(){
      return{
        //菜单列表
        menuList:[],
      }
    },
    methods:{
      collapse(){
        this.$store.state.collapse=!this.$store.state.collapse;
      },
      //获取展示菜单列表
      async getMenuList(){
        const {data} = await getMenuList();
        if(data.success){
          this.menuList=data.data.menuList;
          this.$store.commit("menuList", data.data);
          //然后把菜单数据放到vuex里面 避免每次刷新都tm要去加载
          //vuex的数据持久是依赖session的 所以清除缓存 vuex的数据就没了
        }else{
          this.$message.error(data.message);
        }
      },

    },

    computed:{
      isFold(){
        return this.$store.state.collapse ? "el-icon-s-unfold" : "el-icon-s-fold";
      },
    }
  }
</script>

<style scoped>
  .el-menu-sidebar{
    top: 0;
    left: 0;
    bottom: 0;
   
  }
  .el-menu{
   border-right: 0;

  }
  .side-container{
    top: 0;
    left: 0;
    bottom: 0;
    transition: margin-left 0.45s;
  }
  .collapse{
    text-align: center;
    cursor: pointer;
    height: 40px;
    font-size: 2rem;
  }

 
</style>

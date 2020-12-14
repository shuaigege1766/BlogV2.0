<template>
  <div>
  <template v-for="item in this.menuList">
    <el-submenu  v-if="item.children.length>0"
                 :disabled="item.disabled"
                 :index="item.menuSort+''"
                 :key="item.menuId+''">
        <template slot="title">
           <i :class="item.menuIcon"></i>
          <span>{{item.menuName}}</span>
        </template>
      <menu-tree :menu-list="item.children"></menu-tree>
    </el-submenu>
    <el-menu-item v-else
                  :disabled="item.disabled"
                  :index="item.menuUrl+''"
                  :key="item.menuId+''"
                  :route="item.menuUrl+''"
    >
      <i :class="item.menuIcon"></i>
      <span>{{item.menuName}}</span>
    </el-menu-item>
  </template>
  </div>
</template>

<script>
  export default {
    name: "MenuTree",
    props:["menuList",],
  }
</script>

<style scoped>
  .el-menu--collapse span
  {
     height: 0;
    width: 0;
    overflow: hidden;
    visibility: hidden;
    display: inline-block;
  }
</style>

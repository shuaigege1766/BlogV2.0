<template>
  <el-card style="min-height: calc(100vh - 126px);">
    <el-table border :data="roleList">
      <el-table-column prop="roleName" label="角色名称" align="center">
        <template slot-scope="scope">
          <el-tag>
            {{ scope.row.roleName }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="description" label="描述" align="center" width="300px">
        <template slot-scope="scope">
          <el-tag>
            {{ scope.row.description}}
          </el-tag>
        </template>
      </el-table-column>


      <!-- 列操作 -->
      <el-table-column label="分配Api权限" align="center" >
        <template slot-scope="scope">
          <el-button type="danger" size="mini"
                     @click="editAssignApi(scope.row.roleId)"
          >
            分配API权限
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
  import {getRoleList} from "../../api/swaggerApi";

  export default {
    created() {
      this.getRoleList();
    },
    name: "AssignAPI",
    data(){
      return{
        roleList:[],
      }
    },
    methods:{
      async getRoleList(){
       const  {data}= await  getRoleList();
       if(data.success){
         this.roleList=data.data.data;
       }
      },
      editAssignApi(id){
        this.$router.push({ path: "/swaggerApi/" + id });
      }
    },
  }
</script>

<style scoped>

</style>

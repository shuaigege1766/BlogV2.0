<template>
  <el-card style="min-height: calc(100vh - 126px);">
    <el-table border :data="userList">
      <el-table-column
              prop="avatar"
              label="头像"
              width="100px"
              align="center"
      >
        <template slot-scope="scope">
          <el-avatar :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>

      <el-table-column prop="username" label="用户名" align="center">
        <template slot-scope="scope">
          <el-tag>
            {{ scope.row.username }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="nickname" label="昵称" align="center">
        <template slot-scope="scope">
          <el-tag>
            {{ scope.row.nickname }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="简介" align="center">
      </el-table-column>
      <!-- 列操作 -->
      <el-table-column label="分配角色" align="center" >
        <template slot-scope="scope">
          <el-button type="danger" size="mini"
          >
            分配用户角色
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>


  import {findUserList, getUserList} from "../../api/users";

  export default {
    created() {
      this.getUserList();
    },
    data(){
      return{
        userList:[],
        current:1,
        size:10,
        roleName:null,
        nickname:null,
      }
    },
    methods:{
       async getUserList(){
         const {data}=  await  getUserList(this.current,this.size,this.nickname);
         if(data.success){
           this.userList=data.data.data;
           this.total=data.data.total;
         }
       }
    },
  }
</script>

<style scoped>

</style>

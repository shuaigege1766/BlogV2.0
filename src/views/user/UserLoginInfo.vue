<template>
  <el-card class="box-card" style=" margin-top: 10px">
    <el-form :inline="true" :model="UserQueryVO" class="demo-form-inline">


      <el-form-item label="昵称" >
          <el-input clearable v-model="UserQueryVO.nickname" placeholder="请输入内容"></el-input>
      </el-form-item>

      <el-form-item  style="float: right">
      <el-button icon="el-icon-refresh" @click="reset">重置</el-button>
      <el-button type="primary" icon="el-icon-search" @click="getUserLoginInfoList">查询</el-button>
      <el-button type="warning" icon="el-icon-download">导出</el-button>
      </el-form-item>
    </el-form>
<!--展示用户表格    y-->
    <el-table
            :data="UserLoginInfoList"
            border
            max-height="430px"
            style="width: 100%">
      <!--      头像t-->
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
      <!--昵称      n-->
      <el-table-column
              prop="nickname"
              label="昵称"
              width="100px"
              align="center"
      >
      </el-table-column>
<!--&lt;!&ndash;      用户登录方式&ndash;&gt;-->
<!--            <el-table-column-->
<!--                    prop="loginType"-->
<!--                    label="登录方式"-->
<!--                    width="80"-->
<!--                    align="center"-->
<!--                   >-->
<!--              <template slot-scope="scope">-->
<!--                <el-tag size="medium">{{ scope.row.loginType }}</el-tag>-->
<!--              </template>-->
<!--            </el-table-column>-->

       <!--   登录ip -->
      <el-table-column
              prop="ipAddress"
              label="登录ip"
              width="120px"
              align="center"
      >
      </el-table-column>
      <!--ip来源-->
      <el-table-column
              prop="ipSources"
              label="地址来源"
              width="120px"
              align="center"
      >
      </el-table-column>

      <el-table-column
              sortable
              align="center"
              prop="loginTime"
              label="登录时间"
              width="185"
              >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{scope.row.loginTime}}</span>
        </template>
      </el-table-column>
      
      <el-table-column
              sortable
              prop="lastLoginTime"
              label="上次登录"
              width="185"
              align="center"
             >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{scope.row.lastLoginTime}}</span>
        </template>
      </el-table-column>

<!--      <el-table-column-->
<!--              prop="loginState"-->
<!--              label="登录去向"-->
<!--              width="100"-->
<!--              align="center"-->
<!--      >-->
<!--        <template slot-scope="scope">-->
<!--&lt;!&ndash;          <i class="el-icon-time"></i>&ndash;&gt;-->
<!--          <el-tag size="medium">{{ scope.row.loginState==true?"博客":"后台" }}</el-tag>-->
<!--        </template>-->
<!--</el-table-column>-->

      <el-table-column
              align="center"
              label="操作" >
        <template slot-scope="scope">
<!--          <el-button  type="primary"-->
<!--                      size="mini"-->
<!--                      icon="el-icon-edit"-->
<!--                      @click="editUserInfo(scope.row)">编辑-->
<!--          </el-button>-->
          <el-button  @click="deleteUser(scope.row.id)" type="danger" size="mini" icon="el-icon-delete">删除</el-button>

<!--          <el-button  type="warning" size="mini" icon="el-icon-s-tools"></el-button>-->
        </template>
        
      </el-table-column>
    </el-table>

    <el-pagination
            style="padding-top: 20px"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="current"
            :page-sizes="[5, 10, 20, 30]"
            :page-size="size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
    </el-pagination>





  </el-card>
</template>

<script>
  import {userLoginInfoList} from "../../api/userLoginInfo";

  export default {
    name: "UserLoginInfo",
    created() {
      this.getUserLoginInfoList();
    },
    data() {
      return {
        //根据昵称查询
        UserQueryVO: {
          nickname:'',
        },

        //用户集合
        UserLoginInfoList:[],
        //每页显示条数
        size:5,
        //总数据条数
        total:100,
        //当前第几页
        current:1,

      }

    },
    methods: {
      //当每页条数改边的时候
      handleSizeChange(val) {
        //把val复制给size 重新在后台查询数据
        this.size=val;
        this.getUserLoginInfoList();
      },
      //当页面改边的时候
      handleCurrentChange(val) {
        this.current=val;
        this.getUserLoginInfoList();
      },
      async getUserLoginInfoList(){
        const {data} = await  userLoginInfoList(this.current,this.size,this.UserQueryVO.nickname);
         this.UserLoginInfoList=data.data.data;
         this.total=data.data.total;
      },
      reset(){
        this.UserQueryVO.nickname='';
        this.UserQueryVO.userAuth='';
      },

    }
  }
</script>

<style scoped>

</style>

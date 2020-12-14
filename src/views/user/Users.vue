<template>
  <el-card class="box-card" style=" margin-top: 10px">
    <el-form :inline="true" :model="UserQueryVO" class="demo-form-inline">
     
      <el-form-item label="用户角色" >
      <el-select v-model="UserQueryVO.roleName" placeholder="请选择">
        <el-option
                clearable
                :key="1"
                label="所有用户"
                :value="null">
          <span style="float: left"> 所有用户</span>
          <span style="float: right; color: #8492a6; font-size: 13px">
            <span class="el-tag">{{ total}}</span>
          </span>
        </el-option>
        <el-option
                clearable
                v-for="item in userAuthList"
                :key="item.userAuth"
                :label="item.userAuth"
                :value="item.userAuth">
          <span >{{ item.userAuth }}</span>
          <span style="float: right; color: #8492a6; font-size: 13px">
            <span class="el-tag">{{ item.size }}</span>
          </span>
        </el-option>
      </el-select>
      </el-form-item>

      <el-form-item label="昵称" >
          <el-input clearable v-model="UserQueryVO.nickname" placeholder="请输入内容"></el-input>
      </el-form-item>

      <el-form-item  style="float: right">
      <el-button icon="el-icon-refresh" @click="reset">重置</el-button>
      <el-button type="primary" icon="el-icon-search" @click="getUserList">查询</el-button>
      <el-button type="success" icon="el-icon-plus" @click="dialogFormVisible = true">添加</el-button>
      <el-button type="warning" icon="el-icon-download">导出</el-button>
      </el-form-item>
    </el-form>
<!--展示用户表格    y-->
    <el-table

            :data="userList"
            border
            max-height="430px"
            style="width: 100%">
<!--      用户id-->
      <el-table-column
              align="center"
              prop="userId"
              label="#"
              width="40">
      </el-table-column>
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
<!--      用户身份-->
      <el-table-column
              prop="description"
              label="用户角色"
              width="120px"
              align="center"
      >
        <template slot-scope="scope">
          <el-tag size="medium">{{ scope.row.description}}</el-tag>
        </template>
      </el-table-column>
<!--      用户名-->
<!--      <el-table-column-->
<!--              prop="username"-->
<!--              label="用户名"-->
<!--              width="120px"-->
<!--              align="center"-->
<!--      >-->
<!--      </el-table-column>-->
<!--昵称      n-->
      <el-table-column
              prop="nickname"
              label="昵称"
              width="150px"
              align="center"
      >
      </el-table-column>
<!--      <el-table-column-->
<!--              prop="loginType"-->
<!--              label="登录方式"-->
<!--              width="110"-->
<!--              align="center"-->
<!--             >-->
<!--        <template slot-scope="scope">-->
<!--          <el-tag size="medium">{{ scope.row.loginType }}</el-tag>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column
              sortable
              align="center"
              prop="createTime"
              label="创建时间"
              width="180"
              >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      
      <el-table-column
              sortable
              prop="updateTime"
              label="更新时间"
              width="180"
              align="center"
             >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span style="margin-left: 10px">{{scope.row.updateTime}}</span>
        </template>
      </el-table-column>

      <el-table-column
              prop="isSilence"
              label="禁言"
              width="100"
              align="center"
      >
        <template slot-scope="scope">
          <el-switch
                  v-model="scope.row.isSilence"
                  @change="changeSilence(scope.row.isSilence,scope.row.id)"
                  active-color="#13ce66"
                  inactive-color="">
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column
              align="center"
              label="操作"
               >
        <template slot-scope="scope">
          <el-button  type="primary"
                      size="mini"
                      icon="el-icon-edit"
                      @click="editUserInfo(scope.row)">编辑
          </el-button>
          <el-button  @click="deleteUser(scope.row.id)" type="danger" size="mini" icon="el-icon-delete">删除</el-button>
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
<!--   添加对话框 t-->
    <el-dialog title="修改用户信息" :visible.sync="editUser">
      <el-form :model="editUserForm">
        <el-form-item label="头像" label-width="120px">
          <el-input clearable v-model="editUserForm.avatar" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户名" label-width="120px">
          <el-input clearable v-model="editUserForm.username " autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" label-width="120px">
          <el-input clearable v-model="editUserForm.nickname " autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户角色" label-width="120px">
          <el-select v-model="editUserForm.userAuth" placeholder="请选择">
            <el-option
                    v-for="item in userAuthList"
                    :key="item.userAuth"
                    :label="item.userAuth"
                    :value="item.userAuth">
            </el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editUser = false">取 消</el-button>
        <el-button type="primary" @click="editUserInfoSure">确 定</el-button>
      </div>
    </el-dialog>



    <el-dialog title="增加用户" :visible.sync="dialogFormVisible">
      <el-form :model="addUserForm">
        <el-form-item label="用户名" label-width="120px" >
          <el-input clearable v-model="addUserForm.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="120px">
          <el-input clearable v-model="addUserForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户头像url" label-width="120px">
          <el-input clearable v-model="addUserForm.avatar" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户昵称" label-width="120px">
          <el-input clearable v-model="addUserForm.nickname" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="用户角色" label-width="120px">
          <el-select v-model="addUserForm.userRole" placeholder="请选择用户角色">
            <el-option label="超级管理员" value="1"></el-option>
            <el-option label="管理员" value="2"></el-option>
            <el-option label="普通用户" value="3"></el-option>
            <el-option label="游客" value="4"></el-option>
            <el-option label="测试人员" value="5"></el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">确 定</el-button>
      </div>
    </el-dialog>

  </el-card>
</template>

<script>
  import {findUserList, updateSilenceById,updateUserInfo,logicDeleteUserById,  addUser} from "../../api/users";

  export default {
    name: "Users",
    created() {
      this.getUserList();
    },
    data() {
      return {
        UserQueryVO: {
          roleName:'',
          nickname:'',
        },
        nowTime:"",
        // 用户角色集合
        userAuthList:[{
          userAuth: '超级管理员',
          size:0,
        }, {
          userAuth: '管理员',
          size:0,
        }, {
          userAuth: '普通用户',
          size:0,
        }, {
          userAuth: '游客',
          size:0,
        }, {
          userAuth: '后台测试',
          size:0,
        }],
        //用户集合
        userList: [],
        //用户角色集合
        userRoleList: [],
        //每页显示条数
        size:5,
        //总数据条数
        total:100,
        //当前第几页
        current:1,
        editUserForm:{
          id:1,
          avatar:"",
          nickname:"",
          username:"",
          userAuth:"",
      },
        addUserForm:{
          username:"",
          password:"",
          avatar:"",
          nickname:"",
          loginTypeId:null,
          userRoleId:3,
          createTime:this.nowTime
        },
        editUser: false,    //修改用户数据
        dialogFormVisible:false, //增加用户显示对话框
      }

    },
    methods: {
      //当每页条数改边的时候
      handleSizeChange(val) {
        //把val复制给size 重新在后台查询数据
        this.size=val;
        this.getUserList();
      },
      //当页面改边的时候
      handleCurrentChange(val) {
        this.current=val;
        this.getUserList();
      },
      async getUserList(){
       const {data} = await  findUserList(this.current,this.size,this.UserQueryVO.roleName,this.UserQueryVO.nickname);
        this.userList=data.data.data;
        this.total=data.data.total;
      },
      reset(){
        this.UserQueryVO.nickname='';
        this.UserQueryVO.userAuth='';
      },
      async changeSilence(flag,id){
       const {data}=await updateSilenceById(flag,id);
       if(data.success){
           if(flag==true){
             this.$message.success("用户已禁言");
           }else {
             this.$message.success("用户解除禁言");
           }
       }else{
         this.$message.error(data.message);
       }
     },
      async deleteUser(id){
         console.log(id);
         const {data}=await  deleteById(id);

      },
      // 点击修改按钮出现修改信息的对话框
      editUserInfo(user){
         this.editUser=true;
         this.editUserForm=user;

      },
      // 点击确定修改按钮 交互数据
      async editUserInfoSure(){
         const {data} = await updateUserInfo(this.editUserForm);
         if(data.success){
           this.$message.success(data.message);
           this.editUser=false;
           //刷新列表
           this.getUserList();
         }else{
           this.$message.error(data.message);
         }
      },
      deleteUser(id) {
        this.$confirm('此操作删除该用户, 是否继续?', '提示', {
           confirmButtonText: '确定',
           cancelButtonText: '取消',
           type: 'warning'
        }).  then( async () => {
          const {data}= await  logicDeleteUserById(id);
          this.$message({
            type: data.success==true?'success':'error',
            message: data.message
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
     async addUser(){
          const  {data}=await addUser(this.addUserForm);
            if(data.success){
              this.$message.success(data.message);
              this.getUserList();
            }else {
              this.$message.success(data.message);
            }
       this.dialogFormVisible=false;
      },
    }
  }
</script>

<style scoped>

</style>

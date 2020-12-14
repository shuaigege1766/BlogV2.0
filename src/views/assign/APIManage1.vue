<template>
  <el-card>
    <div style="margin-bottom: 10px;padding: 10px; background-color: #ffc107;">
      选择正在给角色分配API操作权限
    </div>
    <div style="margin-bottom: 10px;">
      <el-button   type="success"  @click="save">保存配置</el-button>

    </div>
    <!-- 表格操作 -->
      <table width="100%" border="0" cellspacing="1" cellpadding="0">
        <tr class="h">
          <td>选择</td>
          <td>父级</td>
          <td>uri</td>
          <td>请求方式</td>
          <td>Api描述</td>
          <td>设置情况</td>
        </tr>
        <tr v-for="item in apiListInfo" :style="apiIdList.includes(item.id) ? {backgroundColor: '#d4edda'}:{}" >
          <td><input ref="ids" :value="item.id" type="checkbox" :checked="apiIdList.includes(item.id) ?true:false" :disabled="item.pid==null?true:false"></td>
          <td>{{item.pid==null?item.name:''}}</td>
          <td>{{item.url}}</td>
          <td>{{item.method}}</td>
          <td>{{item.pid==null?'':item.name}}</td>
          <td>{{apiIdList.includes(item.id) ? '已设置':''}}</td>
        </tr>
      </table>
    </div>

  </el-card>
</template>

<script>
  import {
    getApiInfoFromSwagger,
    getAssignedApiIdByUserRoleId,
    listApiInfoBack,
    saveOrUpdateApiFromSwagger, saveRolePermissionList
  } from "../../api/swaggerApi";
  export default {
    created() {
      const path = this.$route.path;
      //分割取得后面的id
      const arr = path.split("/");
      const roleId = arr[2];
      this.actionId=roleId;
       this.getApiListInfo();
      //获取当前路由路径
      //如果roleId不是空 就根据当前id获取文章的信息
    //   if (articleId!=null && articleId!="") {
    //
    this.getAssignedApiIdByUserRoleId();
     },
    name: "APIManage" ,
    data() {
      return {
        actionId:null,
        //从swagger文档中获取数据存储
        apiList: [{
          id:null,
          apiId: null,
          name: "",
          url: "",
          method: "",
          pid: "",
          description: "",
        },],
        //数据库获取的api列表信息
        apiListInfo:[],
        current:1,
        size:100,
        count:50,
        apiId:null,
        //选中角色所拥有的api权限id列表
        apiIdList:[],
        rolePermissionList:[]
      }
    },
    name: "APIManage",
    methods: {
      async getApiInfoFromSwagger() {
        const {data} = await getApiInfoFromSwagger();
        console.log(data);
        var x=1;
        //清空原来的数据 //重新获取
        this.apiList=[];
        for (var i in data.tags) {
          // i就是tags数组的下标//用这个下标来组成父的主键id值
          var m=  parseInt(i)+1;
          var name = data.tags[i].name;
          var parent = {
            id:x,
            apiId: m,
            name: name+"",
            description: data.tags[i].description+"",
          }
          x=x+1;
          //尿呀  这样就把父id搞定了 搞定一个父在搞定父下面的子
          this.apiList.push(parent);
          for(var j in data.paths){
            //j就是子的url
            //判断当前是不是父的子
            for(var k in data.paths[j]){
              //k就是他的请求方//在拿到他的父api名称
               var pidName= data.paths[j][k].tags
              if(pidName!=name){
                //跳出当前循环
                 break;
              } else {
                var children={
                  id:x,
                  apiId:null,
                  name: data.paths[j][k].summary+"" ,
                  url:  j,
                  method:k,
                  pid:m ,
                  description:  data.paths[j][k].summary+""
                }
                this.apiList.push(children);
                x=x+1;
              }
            }
          }
        }
        this.saveOrUpdateApiFromSwagger();
      },
      async saveOrUpdateApiFromSwagger(){
       const {data} = await saveOrUpdateApiFromSwagger(this.apiList);
         if(data.success){
           this.$message.success("更新swaggerApi信息到数据库成功") ;
         }else {
           this.$message.error("更新swaggerApi信息到数据库失败") ;
         }
      } ,
      //从数据库获取api信息
      async  getApiListInfo(){
        const{data}=  await listApiInfoBack(this.current,this.size,this.apiId);
        if(data.success){
          this.apiListInfo=data.data.data.recordList;
        }
      },
     async  getAssignedApiIdByUserRoleId(){
       const {data}=  await getAssignedApiIdByUserRoleId(this.actionId);
       if(data.success){
         this.apiIdList=data.data.data;
       }
      },
      save(){
        let ids = this.$refs.ids;
        this.rolePermissionList=[],
        ids.forEach(item=>{
          if(item.checked){
            let rolePermission={
              roleId:this.actionId,
              apiId:item.value,
            }
            this.rolePermissionList.push(rolePermission);
          }
        });
        //批量设置到数据库
        saveRolePermissionList(this.actionId,this.rolePermissionList)
            .then(({data})=>{
              if(data.success){
                this.$message.success("设置当前角色权限成功");
              }else {
                this.$message.error("设置当前角色权限失败");
              }
            }).catch(()=>{
          this.$message.error("设置失败");
        });
        this.getAssignedApiIdByUserRoleId();
      },
    },


  }
</script>

<style scoped>
  .operation-container {
    display: flex;
    align-items: center;
    margin-bottom: 1.25rem;
  }
</style>

<template>
  <el-card>
    <!-- 表格操作 -->
    <div class="operation-container">
      <el-button
              type="danger"
              size="small"
              icon="el-icon-deleteItem"
      >
        保存分配设置
      </el-button>
      <el-button
              type="primary"
              size="small"
              icon="el-icon-deleteItem"
              @click="getApiInfoFromSwagger"
      >
        从swagger2Api文档中更新Api数据
      </el-button>
      <div style="margin-left:auto">
        <el-input
                v-model="apiId"
                prefix-icon="el-icon-search"
                size="small"
                placeholder="请选中API分类"
                style="width:200px"
        />
        <el-button
                type="danger"
                size="small"
                icon="el-icon-search"
                style="margin-left:1rem"
                @click="getApiListInfo"
        >
          搜索
        </el-button>
      </div>
    </div>

  <!--展示用户表格    y-->
  <el-table
          :data="apiListInfo"
          border
          ref="multipleTable"
          max-height="430px"
          style="width: 100%"
          tooltip-effect="dark"
          @selection-change="handleSelectionChange"
          >
    <!-- 表格列 -->
    <el-table-column type="selection"  :aria-checked="true" width="55" />
    <el-table-column
            prop=""
            label="分配情况"
            width="120px"
            align="center"
    >
      <template slot-scope="scope" >
        <span v-if="apiIdList.includes(scope.row.id)">已分配</span>
      </template>
    </el-table-column>
    <!--昵称      n-->
    <el-table-column
            prop="name"
            label="API名称"
            width="200px"
            align="center"
    >
      <template slot-scope="scope" >
        <el-tag v-if="scope.row.pid==null">
          {{ scope.row.name }}
        </el-tag>
        <span v-else> {{ scope.row.name }}</span>
      </template>
    </el-table-column>
    
    <el-table-column
            prop="url"
            label="请求地址"
            width="200px"
            align="center"
    >
    </el-table-column>
    <!--ip来源-->
    <el-table-column
            prop="method"
            label="请求方式"
            width="200px"
            align="center"
    >
      <template slot-scope="scope" >
        <el-tag v-if="scope.row.method">
          {{ scope.row.method }}
        </el-tag>
      </template>
    </el-table-column>


    <el-table-column
            prop="description"
            label="描述"
            align="center"
    >
    </el-table-column>
    <!-- 列操作 -->
    <el-table-column label="操作" align="center" width="160">
      <template slot-scope="scope">
        <el-button type="danger" size="mini"
        >
         权限分配
        </el-button>
      </template>
    </el-table-column>
  </el-table>

    <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="current"
            :page-sizes="[ 6,10, 20, 30,100]"
            :page-size="size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="count"
            background
    />
    
  </el-card>
</template>

<script>
  import {getApiInfoFromSwagger, listApiInfoBack, saveOrUpdateApiFromSwagger} from "../../api/swaggerApi";
  export default {
    created() {
     this.getApiListInfo();
      //获取当前路由路径
      const path = this.$route.path;
      //分割取得后面的id
      const arr = path.split("/");
      const roleId = arr[2];
      //如果roleId不是空 就根据当前id获取文章的信息
    //   if (articleId!=null && articleId!="") {
    //

     },
    name: "APIManage" ,
    data() {
      return {
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
        apiIdList:[1,2],
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
        console.log(this.apiList);
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
          this.count=data.data.data.count ;
        }
      },
      handleSizeChange(val) {
        //把val复制给size 重新在后台查询数据
        this.size=val;
        this.getApiListInfo();
      },
      //当页面改边的时候
      handleCurrentChange(val) {
        this.current=val;
        this.getApiListInfo();
      },
      //表格选择框该变得时候
      handleSelectionChange(val){
        this.apiIdList = [];
        val.forEach(item => {
            this.apiIdList.push(item.id);
          });
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

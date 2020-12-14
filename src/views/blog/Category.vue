<template>
  <el-card style="min-height: calc(100vh - 126px);">
    <!-- 表格操作 -->
    <div class="operation-container">
      <el-button
              type="success"
              size="small"
              icon="el-icon-plus"
              @click="openModel(null)"
      >
        新增
      </el-button>
      <el-button
              type="danger"
              size="small"
              icon="el-icon-deleteItem"
              @click="isDelete = true"
      >
        批量删除
      </el-button>

      <div style="margin-left:auto">
        <el-input
                v-model="categoryName"
                prefix-icon="el-icon-search"
                size="small"
                placeholder="请输入标签名"
                style="width:200px"
        />
        <el-button
                type="primary"
                size="small"
                icon="el-icon-search"
                style="margin-left:1rem"
                @click="listTags"
        >
          搜索
        </el-button>
      </div>
    </div>

    <!-- 表格展示 -->
    <el-table border :data="categoryList" @selection-change="selectionChange">
      <!-- 表格列 -->
      <el-table-column type="selection" width="55" />
      <!-- 标签名 -->
      <el-table-column prop="tagName" label="分类名" align="center">
        <template slot-scope="scope">
          <el-tag>
            {{ scope.row.categoryName }}
          </el-tag>
        </template>
      </el-table-column>
      <!-- 标签创建时间 -->
      <el-table-column prop="createTime" label="创建时间" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right:5px" />
          {{ scope.row.createTime | date }}
        </template>
      </el-table-column>
      <!-- 标签更新时间 -->
      <el-table-column prop="updateTime" label="更新时间" align="center">
        <template slot-scope="scope">
          <i class="el-icon-time" style="margin-right:5px" />
          {{ scope.row.updateTime | date }}
        </template>
      </el-table-column>
      <!-- 列操作 -->
      <el-table-column label="操作" align="center" width="160">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="openModel(scope.row)">
            编辑
          </el-button>
          <el-popconfirm
                  title="确定删除吗？"
                  style="margin-left:1rem"
                  @onConfirm="deleteCategory(scope.row.categoryId)"
          >
            <el-button size="mini" type="danger" slot="reference">
              删除
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
            class="pagination-container"
            background
            @size-change="sizeChange"
            @current-change="currentChange"
            :current-page="current"
            :page-size="size"
            :total="total"
            :page-sizes="[5, 10,20]"
            layout="total, sizes, prev, pager, next, jumper"
    />
    <!-- 编辑对话框 -->
    <el-dialog :visible.sync="addOrEdit" width="30%">
      <div class="dialog-title-container" slot="title" ref="categoryTitle" />
      <el-form label-width="80px" size="medium" :model="tegoryForm">
        <el-form-item label="标签名">
          <el-input style="width:220px" v-model="categoryForm.categoryName" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="addOrEdit = false">取 消</el-button>
        <el-button type="primary" @click="addOrEditCategory">
          确 定
        </el-button>
      </div>
    </el-dialog>
  </el-card>
</template>

<script>


  import {addOrEditCategory, deleteCategory, listCategory} from "../../api/category";

  export default {
    created() {
      //获取分类列表
      this.getList() ;
    },
    name: "Category",
    data(){
      return{
        categoryForm:{
          categoryId:null,
          categoryName:"",
        } ,
        categoryList:[],
        categoryName:"", //模糊搜索的标签名
        current:1 ,//当前页
        size:5 ,//一页的条数
        total:100,//总条数
        addOrEdit:false
      }
    },
    methods:{
      async getList(){
        const {data} =  await listCategory(this.current,this.size,this.categoryName);
        if(data.success){
          this.categoryList=data.data.data;
          this.total=data.data.total;
        }
      },
      openModel(tag) {
        if (tag != null) {
          this.categoryForm = JSON.parse(JSON.stringify(tag));
          this.$refs.categoryTitle.innerHTML = "修改分类";

        } else {
          this.categoryForm.categoryId=null;
          this.categoryForm.categorName = "";
          this.$refs.categoryTitle.innerHTML = "添加分类";
        }
        this.addOrEdit = true;
      },
      async addOrEditCategory() {
        if (this.categoryForm.categoryName.trim() == "") {
          this.$message.error("分类名不能为空");
          return false;
        }
        const {data}= await addOrEditCategory(this.categoryForm);
        if(data.success){
          this.$message.success("操作成功");
          this.getList();
        }else {
          this.$message.error("操作失败");
        }
        this.addOrEdit = false;
      },
      async deleteCategory(categoryId){
         const {data} =await    deleteCategory(categoryId);
         if(data.success){
           this.getList()  ;
           this.$message.success("删除当前分类成功");
         } else{
           this.$message.error("删除当前分类失败");
         }
      },
      sizeChange(val) {
        //把val复制给size 重新在后台查询数据
        this.size=val;
        this.getList();
      },
      //当页面改边的时候
      currentChange(val) {
        this.current=val;
        this.getList();
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

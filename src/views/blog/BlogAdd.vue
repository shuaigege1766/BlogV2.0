<template>
  <el-card  style="margin: 0;padding: 0">
    <!-- 文章标题 -->
    <div class="article-title-container" style=" display: flex;
    align-items: center;
    margin-bottom: 1.25rem;">
      <el-input
              v-model="article.articleTitle"
              size="medium"
              placeholder="输入文章标题"
      />
      <el-button
              plain
              type="primary"
              size="medium"
              class="save-btn"
              v-if="article.isDraft==0"
      >保存草稿
      </el-button>
      <el-button
              plain
              type="primary"
              size="medium"
              @click="addOrEdit = true"
              style="margin-left:10px"
              class="save-btn">
        发布文章
      </el-button>
    </div>

    <mavon-editor
            style="min-height: 500px;padding: 0;margin: 0"
            v-model="article.articleContent"
            :ishljs="true"
            ref=md
            @change="change"
            @imgAdd="imgAdd"
    >
    </mavon-editor>

    <div>
      <!-- 添加文章对话框 -->
      <el-dialog :visible.sync="addOrEdit" width="40%" top="10vh">
        <div class="dialog-title-container" slot="title">
          上传文章
        </div>
        <!-- 文章数据 -->
        <el-form label-width="80px" size="medium" :model="article">
          <el-form-item label="文章分类">
            <el-select v-model="article.categoryId" placeholder="请选择分类">
              <el-option
                      v-for="item in categoryList"
                      :key="item.categoryId"
                      :label="item.categoryName"
                      :value="item.categoryId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="文章标签">
            <el-select
                    v-model="article.tagIdList"
                    multiple
                    placeholder="请选择标签"
            >
              <el-option
                      v-for="item in tagList"
                      :key="item.tagId"
                      :label="item.tagName"
                      :value="item.tagId"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="上传封面">
            <el-upload
                    class="upload-cover"
                    action="/api/uploadImage"
                    drag
                    multiple
                    :on-success="uploadCover"
            >
              <i class="el-icon-upload" v-if="article.articleCover == ''" />
              <div class="el-upload__text" v-if="article.articleCover == ''">
                将文件拖到此处，或<em>点击上传</em>
              </div>
              <img
                      v-else
                      :src="article.articleCover"
                      width="360px"
                      height="180px"
              />
            </el-upload>
          </el-form-item>
          <el-form-item label="置顶">
            <el-switch
                    v-model="article.isTop"
                    active-color="#13ce66"
                    inactive-color="#F4F4F5"
                    :active-value="1"
                    :inactive-value="0"
            />
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="addOrEdit = false">取 消</el-button>
          <el-button type="danger" @click="saveOrUpdateArticle">
            发 表
          </el-button>
        </div>
      </el-dialog>
    </div>
  </el-card>
  

</template>

<script>
  import {getArticleById, getCategoryList, getTagList, saveOrUpdateArticle, uploadImage} from "../../api/blogAdd";
  export default {
    name: "BlogAdd" ,
    created() {
      //获取当前路由路径
      const path = this.$route.path;
      //分割取得后面的id
      const arr = path.split("/");
      const articleId = arr[2];
      //如果文章id不是空 就根据当前id获取文章的信息
      if (articleId!=null && articleId!="") {
        getArticleById(articleId).then(({data})=>{
          if(data.success){
            this. article=data.data.data;
          }
        })
      }
      //获取标签列表和分类列表
      this.getTagList();
      this.getCategoryList();
    },
    destroyed() {
      //自动保存草稿
    },
    data(){
      return{
        autoSave:true,//自动保存草稿开关
        addOrEdit:false,//是否显示点击出来的对话框
        content:"",//编辑器的内容
        html:"",//转换的html
        images:[] ,//存储图片的数组
        //新增博客属性列表
        article:{
          articleId:null ,//博客id
          articleTitle: "" ,//博客标题
          articleContent:"",//博客内容
          articleCover:"",//博客封面
         categoryId:"",//分类id
         tagIdList:[] ,//标签id列表
         isTop:0,//是否置顶
         isDraft:0 //是否存草稿
        },
        //分类列表
        categoryList:[],
        //标签列表
        tagList:[],
      }
    },
    methods:{
      // 所有操作都会被解析重新渲染
      change(value, render){
        // render 为 markdown 解析后的结果[html]
        this.html = render;
      },
      //获取标签列表
      async getTagList(){
        const{data} =  await getTagList();
        if(data.success){
          this.tagList=data.data.data;
        } else{
          this.$message.error("获取标签列表失败");
        }
      },
      //获取分类列表
      async getCategoryList(){
        const{data} =  await getCategoryList();
        if(data.success){
          this.categoryList=data.data.data;
        } else{
          this.$message.error("获取分类列表失败");
        }
      },
      // //自动保存刚才编辑的文章
      // autoSaveArticle(){
      //   if(this.autoSave &&
      //      this.article.articleContent.trim()!=""){
      //     // 如果当前状态不是草稿就变成草稿 1为草稿状态
      //     this.article.isDraft =
      //       this.article.isDraft==0?1:this.article.isDraft;
      //      //存草稿到session
      //
      //   }
      //   },
      //更新或者保存文章
      async saveOrUpdateArticle(){
       const {data} = await saveOrUpdateArticle(this.article);
       if(data.success){
         this.$notify.success({
           title: "成功",
           message: "发布成功",
         });
       }else {
         this.$notify.error({
           title: "失败",
           message: "发布失败",
         });
       }
       this.addOrEdit=false;//是否显示点击出来的对话框
      } ,
      //吧图片上传到服务器，返回地址替换到md中
      imgAdd(pos,$file){
        let file=new FormData();
        file.append("file",$file)
        uploadImage(file).then(res=>{
          if(res.data.success){
            var url=res.data.data.url;
            console.log(url);
            this.$refs.md.$img2Url(pos,url);
          }
        }).catch(err=>{
          this.$message.error("上传图片失败");
        })
      },
      uploadCover(response) {
        this.article.articleCover = response.data.url;
      },
    }
  }
</script>

<style scoped>
  .save-btn {
    margin-left: 0.75rem;
    background: #fff;
    color: #f56c6c;
  }
</style>

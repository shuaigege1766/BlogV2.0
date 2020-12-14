import request from "../utils/request"

//上存图片
export  const uploadImage=(file)=>{
  return request({
    url: "/uploadImage",
    method: "post",
    data:file
  })
}
//获取标签列表
export  const getTagList=()=>{
  return request({
    url: "/tag/getTagList",
    method: "get",
  })
}
//获取分类列表
export  const getCategoryList=()=>{
  return request({
    url: "/category/getCategoryList",
    method: "get",
  })
}
 //添加或者更新文章
export  const saveOrUpdateArticle=(article)=>{
  return request({
    url: "/article/saveOrUpdateArticle",
    method: "post",
    data:article
  })
}
//根据文章id查询文章的信息
export  const getArticleById=(articleId)=>{
  return request({
    url: "/article/getArticleById",
    method: "get",
    params:{articleId}
  })
}

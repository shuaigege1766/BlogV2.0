import request from "../utils/request"

export  const listArticle=(current,size,articleTitle)=>{
  return request({
    url: "/article/listArticle",
    method: "get",
    params:{
      current,
      size,
      articleTitle,
    }
  })
}

export  const deleteArticleById=(articleId)=>{
  return request({
    url: "/article/deleteArticleById",
    method: "delete",
    params:{
      articleId
    }
  })
}

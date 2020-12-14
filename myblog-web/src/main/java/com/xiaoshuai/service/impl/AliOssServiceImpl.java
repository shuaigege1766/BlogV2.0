package com.xiaoshuai.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.xiaoshuai.service.AliOssService;
import com.xiaoshuai.utils.OssUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-24-上午 10:16
 **/
@Service
public class AliOssServiceImpl implements AliOssService, InitializingBean {

    @Autowired
    private OssUtil ossUtil;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    /**
     * 初始化后之后的操作这里相当于static 静态代码块 
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
       endpoint=ossUtil.getEndpoint();
       accessKeyId=ossUtil.getAccessKeyId();
       accessKeySecret=ossUtil.getAccessKeySecret();
       bucketName=ossUtil.getBucketName();
    }
    /**
     * //创建存储空间
     */
    @Override
    public void createBucket() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        if(ossClient.doesBucketExist(bucketName)){
          throw  new RuntimeException(bucketName+"对象存储已经存在了") ;
        }
        // 创建存储空间。
        ossClient.createBucket(bucketName);
// 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * //上传文件
     *
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        String uploadUrl="";
        try{
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
             if(!ossClient.doesBucketExist(bucketName)){
                 ossClient.createBucket(bucketName);
                 //设置属性
                 ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
             }
            //每次上传的名字肯定不同的呀，来弄一个不同的名字
            //uuid  redis 分布式id 雪花算法 定义一个文件格式 yyyy/MM/dd+uuid
            //获取上传文件的文件流
            InputStream fileInputStream = file.getInputStream();
             //构建日期的文件夹路径 2020/11/24/文件名
            Date date = new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
            String dataPath = simpleDateFormat.format(date);
             //获取上传文件的全名称
            String original = file.getOriginalFilename();
            //获取 UUID
            String filename = UUID.randomUUID().toString().replaceAll("-", "");
             //获取上传文件的拓展名 1.jpg
            String fileType = original.substring(original.lastIndexOf("."));
            //拼接文件名称
            String newName=filename+fileType;
            //生成文件夹  2020/11/24/UUID.png
             filename = dataPath+"/"+newName;
            //如果想要实现图片的预览效果,一定要设置一下几个点
            // 1.设置文件 ACL为反正不能为私有 要么是公共读,要么是公共读写
            // 2.一定要设置文本类型为(image/jpg)
            ObjectMetadata objectMetadata = new ObjectMetadata();
            // 设置公共读权限
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            // 设置类型
             objectMetadata.setContentType(getcontentType(fileType));
            // 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
            ossClient.putObject(bucketName, filename, fileInputStream,objectMetadata);
            //关闭oss
            ossClient.shutdown();
            //获取url地址
            //默认十年不过期
            Date expiration=new Date(System.currentTimeMillis()+3600L*1000*24*365*10);
            uploadUrl=ossClient.generatePresignedUrl(bucketName, filename, expiration).toString();

           // uploadUrl="https://"+bucketName+"."+endpoint+"/"+filename;

        }catch (Exception e){
            e.printStackTrace();
        }

        return uploadUrl;
    }

    /**
     * //下载文件
     *
     * @param filename
     * @throws IOException
     */
    @Override
    public void download(String filename) throws IOException {

    }

    /**
     * 列举文件
     */
    @Override
    public void listFile() {

    }

    /**
     * 删除文件
     *
     * @param filename
     */
    @Override
    public void deleteFile(String filename) {

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。
        ossClient.deleteObject(bucketName, filename);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    /*** Description: 判断OSS服务文件上传时文件的contentType ** @param FilenameExtension 文件后缀 * @return String */
    public static String getcontentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp"))
        { return "image/bmp"; }
        if (FilenameExtension.equalsIgnoreCase(".gif"))
        { return "image/gif"; }if
        (FilenameExtension.equalsIgnoreCase(".jpeg")
                        || FilenameExtension.equalsIgnoreCase(".jpg")
                        || FilenameExtension.equalsIgnoreCase(".png"))
        { return "image/jpg"; }
        if (FilenameExtension.equalsIgnoreCase(".html"))
        { return "text/html"; }
        if (FilenameExtension.equalsIgnoreCase(".txt"))
        { return "text/plain"; }if
        (FilenameExtension.equalsIgnoreCase(".vsd"))
        { return "application/vnd.visio"; }
        if (FilenameExtension.equalsIgnoreCase(".pptx")
                || FilenameExtension.equalsIgnoreCase(".ppt"))
        { return "application/vnd.ms-powerpoint"; }if
        (FilenameExtension.equalsIgnoreCase(".docx")
                        || FilenameExtension.equalsIgnoreCase(".doc"))
        { return "application/msword"; }
        else if
        (FilenameExtension.equalsIgnoreCase(".xml"))
        { return "text/xml"; }
        return "image/jpg";
    }
}


package com.xiaoshuai.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-24-上午 10:11
 **/
public interface AliOssService {
    /**
     *   //创建存储空间
      */

    void createBucket();

    /**
     *    //上传文件
      * @param file
     * @return
     */

    String upload(MultipartFile file);

    /**
     *     //下载文件
     * @param filename
     * @throws IOException
     */

    void download(String filename) throws IOException;

    /**
     * 列举文件
     */
    void  listFile();
    /**
     * 删除文件
     */
    void deleteFile(String filename);
}

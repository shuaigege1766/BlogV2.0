package com.xiaoshuai.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-24-上午 9:56
 **/
@ConfigurationProperties(prefix = "alioss")
@Component
@Data
public class OssUtil {
   private String endpoint;
    private String  accessKeyId;
    private String   accessKeySecret;
    private String  bucketName;
}

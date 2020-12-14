package com.xiaoshuai.dto;

import lombok.Data;

import java.util.Date;

/**
 * 文章文章归档的dto
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-30-上午 9:29
 **/

@Data
public class ArchiveDTO {

        /**
         * id
         */
        private Integer id;

        /**
         * 标题
         */
        private String articleTitle;

        /**
         * 发表时间
         */
        private Date createTime;


}

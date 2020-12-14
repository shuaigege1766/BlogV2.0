package com.xiaoshuai.dto;

import lombok.*;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-30-上午 9:49
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {

        /**
         * id
         */
        private Integer id;

        /**
         * 分类名
         */
        private String categoryName;

        /**
         * 分类下的文章数量
         */
        private Long articleCount;


}

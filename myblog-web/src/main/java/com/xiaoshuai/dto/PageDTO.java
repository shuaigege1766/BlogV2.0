package com.xiaoshuai.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-上午 11:26
 **/
@Data
public class PageDTO<T> {
    /**
     * 分页列表
     */
    private List<T> recordList;

    /**
     * 总数
     */
    private Integer count;

    public PageDTO(List<T> recordList, Integer count) {
        this.recordList = recordList;
        this.count = count;
    }

    public PageDTO() {
    }
}

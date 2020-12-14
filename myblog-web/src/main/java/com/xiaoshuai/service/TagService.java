package com.xiaoshuai.service;

import com.xiaoshuai.dto.ArticlePreviewListDTO;
import com.xiaoshuai.dto.TagDTO;
import com.xiaoshuai.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.AddOrEditTagVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
public interface TagService extends IService<Tag> {
    List<TagDTO> listTagDTO();

    ArticlePreviewListDTO listTagsByTagId(Integer tagId, Integer current);

    boolean  addOrEditTag(AddOrEditTagVO addOrEditTagVO);

    boolean deleteTag(Integer tagId);
}

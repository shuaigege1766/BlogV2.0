package com.xiaoshuai.mapper;

import com.xiaoshuai.dto.CategoryDTO;
import com.xiaoshuai.dto.TagDTO;
import com.xiaoshuai.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-24
 */
public interface TagMapper extends BaseMapper<Tag> {

    List<TagDTO> listTagDTO();
    
}

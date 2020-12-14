package com.xiaoshuai.mapper;

import com.xiaoshuai.dto.ApiBackDTO;
import com.xiaoshuai.entity.Api;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-03
 */
public interface ApiMapper extends BaseMapper<Api> {
    //分页查出所有满足条件的
  List<ApiBackDTO> listApiInfoBack(@Param("current") Integer current, @Param("size") Integer size, @Param("apiId")Integer apiId);
}

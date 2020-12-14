package com.xiaoshuai.service;

import com.xiaoshuai.dto.ApiBackDTO;
import com.xiaoshuai.dto.PageDTO;
import com.xiaoshuai.entity.Api;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.ApiVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-03
 */
public interface ApiService extends IService<Api> {


    boolean saveOrUpdateApiFromSwagger(List<Api> api) ;


    PageDTO<ApiBackDTO> listApiInfoBack(Integer current, Integer size, Integer apiId);
}

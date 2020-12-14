package com.xiaoshuai.service.impl;

import com.xiaoshuai.dto.ApiBackDTO;
import com.xiaoshuai.dto.PageDTO;
import com.xiaoshuai.entity.Api;
import com.xiaoshuai.mapper.ApiMapper;
import com.xiaoshuai.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.vo.ApiVO;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-03
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {



    @Transactional
    @Override
    public boolean saveOrUpdateApiFromSwagger(List<Api> api) {



        boolean b = this.saveOrUpdateBatch(api);

        return b;
    }

    @Override
    public PageDTO<ApiBackDTO> listApiInfoBack(Integer current, Integer size, Integer apiId) {

        //分页查出所有满足条件的api信息
        List<ApiBackDTO> apis = this.baseMapper.listApiInfoBack((current - 1) * size, size, apiId);
        //创建一个集合返回对象
        //计数
        int count = this.count();
        PageDTO<ApiBackDTO> pageDTO = new PageDTO<ApiBackDTO>(apis, count);
        return pageDTO;
    }
}

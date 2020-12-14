package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.entity.UserLogin;
import com.xiaoshuai.mapper.UserLoginMapper;
import com.xiaoshuai.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLogin> implements UserLoginService {

    @Override
    public IPage<UserLogin> getUserInfoList(Integer current, Integer size, String nickname) {
        Page<UserLogin> page = new Page<>(current, size);
        QueryWrapper<UserLogin> wrapper=null;
        if(nickname!=null && nickname!=""){
             wrapper=new QueryWrapper<>();
             wrapper.like("nickname", nickname);
        }
        Page<UserLogin> userLoginPage = this.baseMapper.selectPage(page, wrapper);
        return userLoginPage;
    }
}

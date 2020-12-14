package com.xiaoshuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.entity.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
public interface UserLoginService extends IService<UserLogin> {


     IPage<UserLogin> getUserInfoList (Integer current, Integer size , String nickname);

}

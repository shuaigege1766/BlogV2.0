package com.xiaoshuai.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.UserListPageDTO;
import com.xiaoshuai.entity.Api;
import com.xiaoshuai.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.RegisterUserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
public interface UserService extends IService<User> {

    User getUserByUsername(String username);
    List<String> listUserRolesByUsername(String username);
    boolean checkLogin(String username,String password);

    IPage<UserListPageDTO> getUserListPage(Page<UserListPageDTO> page, String roleName,
                                           String nickname);

    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱号
     */
    void sendCode(String email);


    boolean  registerUser(RegisterUserVO registerUserVO);
     List<Api> getApiUrlByUserName (String username);
}

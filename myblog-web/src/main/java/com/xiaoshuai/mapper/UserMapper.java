package com.xiaoshuai.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.dto.UserListPageDTO;
import com.xiaoshuai.entity.Api;
import com.xiaoshuai.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
public interface UserMapper extends BaseMapper<User> {
    List<String> listUserRolesByUsername(@Param("username") String username);

    IPage<UserListPageDTO> getUserListPage(Page<UserListPageDTO> page, @Param("roleName") String roleName,
                                           @Param("nickname") String nickname);
    List<Api>  getApiUrlByUserName(@Param("username") String username);
}

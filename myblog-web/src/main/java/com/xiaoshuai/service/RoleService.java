package com.xiaoshuai.service;

import com.xiaoshuai.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.RoleApiVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
public interface RoleService extends IService<Role> {


   List<Integer> getAssignedApiIdByUserRoleId(Integer roleId);
   Integer saveRolePermissionList(Integer roleId, List<RoleApiVO> roleApiVO);
}

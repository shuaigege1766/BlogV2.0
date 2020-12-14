package com.xiaoshuai.mapper;

import com.xiaoshuai.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoshuai.vo.RoleApiVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
public interface RoleMapper extends BaseMapper<Role> {
   List<Integer> getAssignedApiIdByUserRoleId(@Param("roleId") Integer roleId);
   Integer saveRolePermissionList(@Param("roleId") Integer roleId,@Param("list") List<RoleApiVO>  roleApiVO);
   Integer deleteRolePermissionList(@Param("roleId") Integer roleId);
   
}

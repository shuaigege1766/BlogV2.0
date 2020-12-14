package com.xiaoshuai.service.impl;

import com.xiaoshuai.entity.Role;
import com.xiaoshuai.mapper.RoleMapper;
import com.xiaoshuai.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.vo.RoleApiVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Integer> getAssignedApiIdByUserRoleId(Integer roleId) {


        return  this.baseMapper.getAssignedApiIdByUserRoleId(roleId);
    }

    @Transactional
    @Override
    public Integer saveRolePermissionList(Integer roleId, List<RoleApiVO>  roleApiVO) {
        if(roleId==null){
            return 0;
        }
        Integer integer=0;
        //删除当前角色拥有的权限列表 在添加进去
        this.baseMapper.deleteRolePermissionList(roleId);
        if(roleApiVO!=null){
            //给当前角色添加对应的权限列表
            integer = this.baseMapper.saveRolePermissionList(roleId, roleApiVO);
            return integer;
        }
        return integer;
    }
}

package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.entity.FriendLink;
import com.xiaoshuai.mapper.FriendLinkMapper;
import com.xiaoshuai.service.FriendLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-01
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Override
    public boolean addOrEditFriendLink(FriendLink friendLink) {

        Integer id = friendLink.getId();
//        如果是新增
        if(id==null){
            Date date=new Date();
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            simpleDateFormat.format(date);
           friendLink.setCreateTime(date);
        }

        boolean b = this.saveOrUpdate(friendLink);
        return  b;

    }

    @Override
    public IPage<FriendLink> pageLinks(Integer current,Integer size,String keywords) {

        Page<FriendLink> page = new Page<>(current, size);
        QueryWrapper<FriendLink> wrapper=null;
        if(keywords!=null & keywords!=""){
            wrapper = new QueryWrapper<>();
            wrapper.like("link_name", keywords);
        }
        Page<FriendLink> linkPage = this.page(page, wrapper);
        return  linkPage;
    }
}

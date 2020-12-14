package com.xiaoshuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.entity.FriendLink;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-01
 */
public interface FriendLinkService extends IService<FriendLink> {



    boolean  addOrEditFriendLink(FriendLink friendLink) ;

    IPage<FriendLink> pageLinks(Integer current,Integer size,String keywords);
}

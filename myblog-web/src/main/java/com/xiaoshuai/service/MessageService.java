package com.xiaoshuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaoshuai.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoshuai.vo.MessageVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-12-02
 */
public interface MessageService extends IService<Message> {
    /**
     * 添加留言弹幕
     *
     * @param messageVO 留言对象
     */
    void saveMessage(MessageVO messageVO);

    /**查看留言列表
     *
     * @param current
     * @param size
     * @param nickname
     * @return
     */
    IPage<Message> getMessageList(Integer current, Integer size, String nickname) ;
}

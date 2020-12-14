package com.xiaoshuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoshuai.constant.DefaultUser;
import com.xiaoshuai.dto.UserListPageDTO;
import com.xiaoshuai.entity.Api;
import com.xiaoshuai.entity.User;
import com.xiaoshuai.handler.BusinessException;
import com.xiaoshuai.mapper.UserMapper;
import com.xiaoshuai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshuai.vo.RegisterUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoshuai
 * @since 2020-11-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public User getUserByUsername(String username) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = this.baseMapper.selectOne(wrapper);
        return  user;
    }

    @Override
    public List<String> listUserRolesByUsername(String username) {

        List<String> roles = this.baseMapper.listUserRolesByUsername(username);
        return roles;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        //重用户里面查出用户 然得到加密的密码
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("password").eq("username", username);
        User user = this.baseMapper.selectOne(wrapper);
        if(user==null){
            return  false;
        }
        //这个是加密后的密码 在数据库里面
        String userPassword = user.getPassword();
        //用加密匹配这个 能匹配返回true 不能返回false
        return   bCryptPasswordEncoder.matches(password, userPassword);
    }

    @Override
    public IPage<UserListPageDTO> getUserListPage(Page<UserListPageDTO> page, String roleName, String nickname) {
        return this.baseMapper.getUserListPage(page, roleName, nickname);
    }

    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱号
     */
    @Override
    public void sendCode(String email) {
        if (!checkEmail(email)) {
            throw new BusinessException("邮箱格式不正确");
        }
        //生成六位随机验证码
//        StringBuilder code = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 6; i++) {
//            code.append(random.nextInt(10));
//        }
        Integer  code=666666;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1766281636@qq.com");
        message.setTo(email);
        message.setSubject("验证码");
        message.setText("您的验证码为 " + code.toString() + " 有效期15分钟，请不要告诉他人哦！");
        javaMailSender.send(message);
        //在把验证码放到redis 有效时间存为15分钟 这样就可以了
        //暂时写死验证码
    }

    @Override
    public boolean  registerUser(RegisterUserVO registerUserVO) {

        User user = new User();
        String code = registerUserVO.getCode();
        if(!code.equals("666666")){
            throw  new BusinessException("验证码错误");
        }
        //加密密码
        String encode = bCryptPasswordEncoder.encode(registerUserVO.getPassword());
        user.setPassword(encode);
        //设置默认头像
        user.setAvatar(DefaultUser.DEFAULT_AVATAR);
        //设置默认昵称
        user.setNickname(DefaultUser.NICKNAME);
        user.setCreateTime(new Date());
        user.setUsername(registerUserVO.getUsername());
        boolean b = this.save(user);
        return  b;
    }

    @Override
    public List<Api> getApiUrlByUserName(String username) {

        List<Api> list = this.baseMapper.getApiUrlByUserName(username);
        return list;
    }

    /**
     * 检测邮箱是否合法
     *
     * @param username 用户名
     * @return 合法状态
     */
    private boolean checkEmail(String username) {
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式 编译正则表达式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(username);
        //进行正则匹配
        return m.matches();
    }
}


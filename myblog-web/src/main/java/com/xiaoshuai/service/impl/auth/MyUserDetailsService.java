package com.xiaoshuai.service.impl.auth;

import com.xiaoshuai.entity.User;
import com.xiaoshuai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-17-上午 9:50
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if(username.isEmpty()){
           throw new UsernameNotFoundException("用户名为空");
       }
       //根据用户名找user
        User user = userService.getUserByUsername(username);
       if(user!=null){
           List<GrantedAuthority> authorities=new ArrayList<>();
           //根据用户名查找用户角色列表
           List<String> roles = userService.listUserRolesByUsername(username);
           //这样写减少对象指针 好一点
           SimpleGrantedAuthority simpleGrantedAuthority=null;
           for (String role :roles){
               simpleGrantedAuthority =new SimpleGrantedAuthority(role);
               authorities.add(simpleGrantedAuthority);
           }
           UserAuth userAuth=new UserAuth();
           userAuth.setUser(user);
           userAuth.setAuthorities(authorities);
           return  userAuth;
       } else {
           throw  new UsernameNotFoundException("没有该用户");
       }

    }
}

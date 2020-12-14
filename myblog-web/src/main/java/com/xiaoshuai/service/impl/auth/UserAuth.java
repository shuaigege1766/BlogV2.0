package com.xiaoshuai.service.impl.auth;

import com.xiaoshuai.entity.User;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 11-17-上午 9:47
 **/

public class UserAuth implements UserDetails {


    @Override
    public String toString() {
        return "UserAuth{" +
                "user=" + user +
                ", authorities=" + authorities +
                '}';
    }

    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuth() {

    }

    public UserAuth(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

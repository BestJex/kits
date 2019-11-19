package com.ptyt.haiguan.kits.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yq
 * @date: 2019/11/15 10:49
 * @description:
 */
@Component
public class KitsUserService implements UserDetailsService {

    @Value("${spring.username}")
    private String userName;
    @Value("${spring.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if(userName.equals(username)) {
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
            grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new User(userName, password, grantedAuthorityList);
    }
}

package com.lyontang.debtmanage.utils;


import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;


    MyPasswordEncoder passwordEncoder=new MyPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findUserByName(userName);
        if (user == null) {
            return null;
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String roleName = userService.findUserRolePhoneByName(userName).getRoleName();
        if ("admin".equals(roleName)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else  {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        org.springframework.security.core.userdetails.User userdetail = new org.springframework.security.core.userdetails.User(user.getUserName(),passwordEncoder.encode(user.getPassword()),authorities);
        System.out.println("管理员信息："+user.getUserName()+"   "+passwordEncoder.encode(user.getPassword())+"  "+userdetail.getAuthorities());
        return userdetail;

    }
}

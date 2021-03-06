package com.lyontang.debtmanage.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String role = null;
        //项目根地址
        String path = request.getContextPath();
        //主机+端口号+项目根路径
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        //权限是我手动赋予的，每一个认证成功的用户只有一个权限
        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            //所以authorities的size为1，直接赋予
            role = authority.getAuthority();
        }
        //通过不同的角色转到不同的页面
        if ("ROLE_ADMIN".equals(role)) {
        //管理员跳到管理员页面
            response.sendRedirect(basePath + "admin");
        } else {
        //用户跳到用户页面
            response.sendRedirect(basePath + "user");
        }

    }
}

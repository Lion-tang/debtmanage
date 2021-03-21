package com.lyontang.debtmanage.controller;

import com.lyontang.debtmanage.entity.Role;
import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.VO.UserRole;
import com.lyontang.debtmanage.service.RoleService;
import com.lyontang.debtmanage.service.UserService;
import com.lyontang.debtmanage.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class AdminHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //根据用户名查找用户
    @RequestMapping("/findUserByName")
    @ResponseBody
    public User findUserByName(String username) {
        System.out.println(userService.findUserByName(username));
        return userService.findUserByName(username);

    }

    //管理员查找所有用户
    @RequestMapping("/findAllUser")
    @ResponseBody
    public List<User> findAllUser() {
        System.out.println(userService.findAllUser());
        return userService.findAllUser();
    }

    @RequestMapping("/findRole")
    @ResponseBody
    public List<Role> findRole() {
        return roleService.findAllRole();
    }

    @RequestMapping("/findUserRoleByName")
    @ResponseBody
    public UserRole findUserRoleByName(String username) {
        System.out.println(userService.findUserRoleByName(username));
        return userService.findUserRoleByName(username);

    }

    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(String username,String password) {

        if (userService.findUserByName(username) != null) {
            return "用户已存在";
        }

        if (password == null) {
            return "密码不能为空";
        }
        password = Md5Utils.code(password);
        Integer resCode = userService.addUser(username,password);

        if (resCode == 0) {
            return "发生错误，用户添加失败";
        }
        return "用户添加成功";
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public String deleteUser(String username) {

        Integer resCode = userService.deleteUser(username);
         if (resCode == 0) {
            return "用户不存在";
        }
        return "用户删除成功";
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(String username, String password) {
        if (userService.findUserByName(username) == null) {
            return "用户不存在";
        }
        password = Md5Utils.code(password);
        Integer resCode = userService.updateUser(username,password);
        if (resCode == 0) {
            return "密码更新失败";
        }
        return "密码更新成功";
    }


}

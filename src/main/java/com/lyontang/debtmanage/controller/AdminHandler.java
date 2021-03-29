package com.lyontang.debtmanage.controller;

import com.lyontang.debtmanage.entity.*;
import com.lyontang.debtmanage.entity.VO.DataVO;
import com.lyontang.debtmanage.service.UserService;
import com.lyontang.debtmanage.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHandler {

    @Autowired
    private UserService userService;

    //新增用户
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity addUser(@RequestBody UserPasswordPhone userPasswordPhone) {
        RespEntity resp = new RespEntity();
        String userName = userPasswordPhone.getUserName();
        String password = userPasswordPhone.getPassword();
        String phone = userPasswordPhone.getPhone();
        if (userService.findUserByName(userName) != null) {
            resp.setCode(-1);
            resp.setMsg("用户已存在");
            return resp; }

        if (password == null) {
            resp.setCode(-1);
            resp.setMsg("密码不能为空");
            return resp; }

        password = Md5Utils.code(password);
        Integer resCode = userService.addUser(userName,password,phone);

        if (resCode == 0) {
            resp.setCode(-1);
            resp.setMsg("发生错误，用户添加失败");
            return resp; }
        //添加成功情况
        resp.setCode(0);
        resp.setMsg("用户添加成功");
        return resp;
    }

    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity deleteUser(@RequestBody String userName) {
        RespEntity resp = new RespEntity();
        Integer resCode = userService.deleteUser(userName);
         if (resCode == 0) {
             resp.setCode(-1);
             resp.setMsg("用户不存在");
            return resp;
        }
         resp.setCode(0);
         resp.setMsg("用户删除成功");
        return resp;
    }

    //更新用户信息
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity updateUser(@RequestBody UserPasswordPhone userPasswordPhone) {
        RespEntity resp = new RespEntity();
        String userName = userPasswordPhone.getUserName();
        String phone = userPasswordPhone.getPhone();
        String password = null;
        if (userService.findUserByName(userName) == null) {
            resp.setMsg("用户不存在");
            resp.setCode(-1);
            return resp;
        }
        if (userPasswordPhone.getPassword() != null && !userPasswordPhone.getPassword().equals(""))
        { password= Md5Utils.code(userPasswordPhone.getPassword());}
            Integer resCode = userService.updateUser(userName, password, phone);
            if (resCode == 0) {
                resp.setCode(-1);
                resp.setMsg("更新失败");
                return resp;
            }

//        更新成功情况
            resp.setCode(0);
            resp.setMsg("更新成功");
            return resp;
    }

    //查找所有用户的信息
    @RequestMapping(value = "findAllUserRolePhoneVO",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<UserRolePhone> findAllUserRolePhoneVO(Integer page,  Integer limit) {
        return userService.findAllUserRolePhoneVO(page,limit);
    }

    //模糊查找用户信息
    @RequestMapping(value = "findByConditionVO",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<UserRolePhone> findByConditionVO(Integer page,Integer limit,String userName, String phone) {
         return userService.findByConditionVO(page,limit,userName, phone);
    }

}

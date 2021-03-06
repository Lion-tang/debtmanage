package com.lyontang.debtmanage.controller;

import com.lyontang.debtmanage.entity.*;
import com.lyontang.debtmanage.entity.VO.BarVO;
import com.lyontang.debtmanage.entity.VO.DataVO;
import com.lyontang.debtmanage.service.StudentService;
import com.lyontang.debtmanage.service.UserService;
import com.lyontang.debtmanage.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

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
    public RespEntity updateUser(@RequestBody UserPasswordPhone userPasswordPhone,Principal principal, Authentication authentication) {
        RespEntity resp = new RespEntity();
        String userName = userPasswordPhone.getUserName();
        String phone = userPasswordPhone.getPhone();
        String password = null;
        //防止前端传来的数据不包含用户名
        if (userService.findUserByName(userName) == null) {
            resp.setMsg("用户不存在");
            resp.setCode(-1);
            return resp;
        }

        //支持不输入密码默认不修改密码
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
    @RequestMapping(value = "/findAllUserRolePhoneVO",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<UserRolePhone> findAllUserRolePhoneVO(Integer page,  Integer limit) {
        return userService.findAllUserRolePhoneVO(page,limit);
    }

    //模糊查找用户信息
    @RequestMapping(value = "/findByConditionVO",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<UserRolePhone> findByConditionVO(Integer page,Integer limit,String userName, String phone) {
         return userService.findByConditionVO(page,limit,userName, phone);
    }

    //以10000人为单位打印学员收费表
    @RequestMapping(value = "/adminGetAllStudent",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<StudentInfo> adminGetAllStudent(Integer page,Student student) {
        return userService.adminGetAllStudent(page, 10000, student);
    }

    //管理员添加学员，和代理用户添加不是一个请求
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity addStudent(@RequestBody Student student, Principal principal) {
        RespEntity resp = new RespEntity();
        if (studentService.findByIdCard(student.getIdCard()) != null) {
            resp.setMsg("学员已存在");
            resp.setCode(-1);
            return resp;
        }
        student.setUserName(principal.getName());
        student.setRId("2");
        Integer resCode = studentService.addStudent(student);
        if (resCode == 0) {
            resp.setMsg("发生异常,学员添加失败");
            resp.setCode(-1);
            return resp;
        }
        resp.setMsg("学员添加成功");
        resp.setCode(0);
        return resp;
    }

    @RequestMapping(value = "/adminGetHqStudent",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<StudentInfo> adminGetHqStudent(Integer page, Integer limit, Student student) {
        student.setRId("2");
        return userService.adminGetAllStudent(page, limit, student);
    }

    @RequestMapping(value = "/adminGetDivisionStudent")
    @ResponseBody
    public  DataVO<StudentInfo> adminGetDivisionStudent(Integer page, Integer limit, Student student) {
        student.setRId("1");
        student.setWantDelete("Y");
        return userService.adminGetAllStudent(page, limit, student);
    }

    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity deleteStudent(@RequestBody List<String> idCardList) {
        RespEntity resp = new RespEntity();
        Integer resCode = userService.deleteStudent(idCardList);
        if (resCode == 0) {
            resp.setCode(-1);
            resp.setMsg("发生异常,删除失败");
            return resp;
        }
        resp.setCode(0);
        resp.setMsg("删除学员成功");
        return resp;
    }

    @RequestMapping(value = "/recoverStudent",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity recoverStudent(@RequestBody String idCard) {
        RespEntity resp = new RespEntity();
        if (studentService.recoverStudent(idCard) == 0) {
            resp.setMsg("发生异常,撤销失败");
            resp.setCode(-1);
            return resp;
        }
        resp.setMsg("撤销成功");
        resp.setCode(0);
        return resp;
    }

    @RequestMapping(value = "/getBarData",method = RequestMethod.POST)
    @ResponseBody
    public BarVO getBarData(@RequestBody  DateRange dateRange) {
        String startDate = dateRange.getStartDate();
        String endDate = dateRange.getEndDate();
        return userService.getBarData(startDate, endDate);
    }

    @RequestMapping(value = "/getPieData",method = RequestMethod.POST)
    @ResponseBody
    public List<Pie> getPieData(@RequestBody DateRange dateRange) {
        String startDate = dateRange.getStartDate();
        String endDate = dateRange.getEndDate();
        return userService.getPieData(startDate, endDate);
    }

    @RequestMapping(value = "/getLineData")
    @ResponseBody
    public BarVO getLineData(@RequestBody DateRangeAndName dateRangeAndName) {
        String startDate = dateRangeAndName.getStartDate();
        String endDate = dateRangeAndName.getEndDate();
        String userName = dateRangeAndName.getUserName();
        return userService.getLineData(startDate, endDate, userName);
    }
}

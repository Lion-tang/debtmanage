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
        String userName = userPasswordPhone.getUserName();
        String password = userPasswordPhone.getPassword();
        String phone = userPasswordPhone.getPhone();
        if (userService.findUserByName(userName) != null) {
            return RespEntity.fail("用户已存在");
        }

        if (password == null) {
            return RespEntity.fail("密码不能为空");
        }

        password = Md5Utils.code(password);
        Integer resCode = userService.addUser(userName,password,phone);

        if (resCode == 0) {
            return RespEntity.fail("发生错误，用户添加失败");
        }
        //添加成功情况
        return RespEntity.success("用户添加成功");
    }

    //删除用户
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity deleteUser(@RequestBody String userName) {
        Integer resCode = userService.deleteUser(userName);
         if (resCode == 0) {
            return RespEntity.fail("用户不存在");
        }
        return RespEntity.success("用户删除成功");
    }

    //更新用户信息
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity updateUser(@RequestBody UserPasswordPhone userPasswordPhone,Principal principal, Authentication authentication) {
        String userName = userPasswordPhone.getUserName();
        String phone = userPasswordPhone.getPhone();
        String password = null;
        //防止前端传来的数据不包含用户名
        if (userService.findUserByName(userName) == null) {
            return RespEntity.fail("用户不存在");
        }

        //支持不输入密码默认不修改密码
        if (userPasswordPhone.getPassword() != null && !userPasswordPhone.getPassword().equals(""))
        { password= Md5Utils.code(userPasswordPhone.getPassword());}
            Integer resCode = userService.updateUser(userName, password, phone);
            if (resCode == 0) {
                return RespEntity.fail("更新失败");
            }

//        更新成功情况
        return RespEntity.success("更新成功");
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
    public DataVO<StudentInfo> adminGetAllStudent(Integer page,Integer limit,Student student) {
        return userService.adminGetAllStudent(page, limit, student);
    }

    //管理员添加学员，和代理用户添加不是一个请求
    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity addStudent(@RequestBody Student student, Principal principal) {
        if (studentService.findByIdCard(student.getIdCard()) != null) {
            return RespEntity.fail("学员已存在");
        }
        student.setUserName(principal.getName());
        student.setRId("2");
        Integer resCode = studentService.addStudent(student);
        if (resCode == 0) {
            return RespEntity.fail("发生异常,学员添加失败");
        }
        return RespEntity.success("学员添加成功");
    }

    //管理员获得总校学员
    @RequestMapping(value = "/adminGetHqStudent",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<StudentInfo> adminGetHqStudent(Integer page, Integer limit, Student student) {
        student.setRId("2");
        return userService.adminGetAllStudent(page, limit, student);
    }

    //管理员获得分校学员
    @RequestMapping(value = "/adminGetDivisionStudent",method = RequestMethod.POST)
    @ResponseBody
    public  DataVO<StudentInfo> adminGetDivisionStudent(Integer page, Integer limit, Student student) {
        student.setRId("1");
        student.setWantDelete("Y");
        return userService.adminGetAllStudent(page, limit, student);
    }

    //管理员真正删除学员
    @RequestMapping(value = "/deleteStudent",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity deleteStudent(@RequestBody List<String> idCardList) {
        Integer resCode = userService.deleteStudent(idCardList);
        if (resCode == 0) {
            return RespEntity.fail("发生异常,删除失败");
        }
        return RespEntity.success("删除学员成功");
    }

    //管理员拒绝分校人员删除学员
    @RequestMapping(value = "/recoverStudent",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity recoverStudent(@RequestBody String idCard) {
        if (studentService.recoverStudent(idCard) == 0) {
            return RespEntity.fail("发生异常,撤销失败");
        }
        return RespEntity.success("撤销成功");
    }

    //获得柱状图数据
    @RequestMapping(value = "/getBarData",method = RequestMethod.POST)
    @ResponseBody
    public BarVO getBarData(@RequestBody  DateRange dateRange) {
        String startDate = dateRange.getStartDate();
        String endDate = dateRange.getEndDate();
        return userService.getBarData(startDate, endDate);
    }

    //获得饼图数据
    @RequestMapping(value = "/getPieData",method = RequestMethod.POST)
    @ResponseBody
    public List<Pie> getPieData(@RequestBody DateRange dateRange) {
        String startDate = dateRange.getStartDate();
        String endDate = dateRange.getEndDate();
        return userService.getPieData(startDate, endDate);
    }

    //获得密集条形图数据
    @RequestMapping(value = "/getLineData",method = RequestMethod.POST)
    @ResponseBody
    public BarVO getLineData(@RequestBody DateRangeAndName dateRangeAndName) {
        String startDate = dateRangeAndName.getStartDate();
        String endDate = dateRangeAndName.getEndDate();
        String userName = dateRangeAndName.getUserName();
        return userService.getLineData(startDate, endDate, userName);
    }


}

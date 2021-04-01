package com.lyontang.debtmanage.controller;

import com.lyontang.debtmanage.entity.RespEntity;
import com.lyontang.debtmanage.entity.Student;
import com.lyontang.debtmanage.entity.StudentInfo;
import com.lyontang.debtmanage.entity.VO.DataVO;
import com.lyontang.debtmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/findAllStudentVO")
    @ResponseBody
    public DataVO<StudentInfo> findAllStudentVO(Integer page, Integer limit, Principal principal) {
        String userName = principal.getName();
        //找出不退档的记录
        return studentService.findAllStudentVO(page, limit, userName,"N");
    }

    @RequestMapping("/addStudent")
    @ResponseBody
    public RespEntity addStudent(@RequestBody Student student, Principal principal) {
        RespEntity resp = new RespEntity();
        if (studentService.findByIdCard(student.getIdCard()) != null) {
            resp.setMsg("学员已存在");
            resp.setCode(-1);
            return resp;
        }
        student.setUserName(principal.getName());
        student.setRId("1");
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

    @RequestMapping(value = "/applyDeleteStudent",method = RequestMethod.POST)
    @ResponseBody
    public RespEntity applyDeleteStudent(@RequestBody  List<String> idCardList) {
        RespEntity resp = new RespEntity();
        Integer resCode = studentService.applyDeleteStudent(idCardList);
        if (resCode == 0) {
            resp.setCode(-1);
            resp.setMsg("发生异常,申请失败");
            return resp;
        }
        resp.setCode(0);
        resp.setMsg("申请删除学员成功");
        return resp;
    }

    //这里的分页按照1页，10000条记录分，可以借助分页统计记录总数
    @RequestMapping(value = "/wantDeleteVO", method = RequestMethod.POST)
    @ResponseBody
    //page在这里表示若记录超过10000，客户端可以通过前端传来的page分页
    public DataVO<StudentInfo> wantDeleteVO(Integer page ,Principal principal) {
        DataVO<StudentInfo> dataVO = new DataVO<>();
        String userName = principal.getName();
        Integer limit = 10000;
        //找出想退档的记录
        return studentService.findAllStudentVO(page, limit, userName, "Y");
    }

    //该findByCondition和管理员的模糊查找不一样，不是同一个请求
    @RequestMapping(value = "/findByConditionVO",method = RequestMethod.POST)
    @ResponseBody
    public DataVO<StudentInfo> findByConditionVO( Integer page, Integer limit, StudentInfo studentInfo,Principal principal) {
        String userName = principal.getName();
        studentInfo.setUserName(userName);
        return studentService.findByConditionVO(page, limit, studentInfo);
    }

    @RequestMapping(value = "/recoverStudent", method = RequestMethod.POST)
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

}

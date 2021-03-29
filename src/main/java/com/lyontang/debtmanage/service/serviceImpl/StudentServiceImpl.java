package com.lyontang.debtmanage.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyontang.debtmanage.entity.Student;
import com.lyontang.debtmanage.entity.StudentInfo;
import com.lyontang.debtmanage.entity.VO.DataVO;
import com.lyontang.debtmanage.mapper.StudentMapper;
import com.lyontang.debtmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService  {

    @Autowired
    public StudentMapper studentMapper;

    @Override
    public Student findByIdCard(String idCard) {
        return studentMapper.findByIdCard(idCard);
    }

    @Override
    public Integer addStudent(Student student) {
        return studentMapper.addStudent(student);
    }

    @Override
    public Integer applyDeleteStudent(List<String> idCardList) {
        return studentMapper.applyDeleteStudent(idCardList);
    }

    @Override
    public Integer recoverStudent(String idCard) {
        return studentMapper.recoverStudent(idCard);
    }

    @Override
    public DataVO<StudentInfo> findAllStudentVO(Integer page, Integer limit,String userName,String wantDelete) {
        DataVO<StudentInfo> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        PageHelper.startPage(page, limit);
        List<StudentInfo> list = studentMapper.findAllStudent(userName,wantDelete);
        dataVO.setCount((int)((Page)list).getTotal());
        dataVO.setData(list);
        return dataVO;
    }

    @Override
    public DataVO<StudentInfo> findByConditionVO(Integer page, Integer limit, StudentInfo studentInfo) {
        DataVO<StudentInfo> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        PageHelper.startPage(page, limit);
        List<StudentInfo> list = studentMapper.findByCondition(studentInfo);
        dataVO.setCount((int)((Page)list).getTotal());
        dataVO.setData(list);
        return dataVO;
    }


}

package com.lyontang.debtmanage.service;

import com.lyontang.debtmanage.entity.Student;
import com.lyontang.debtmanage.entity.StudentInfo;
import com.lyontang.debtmanage.entity.VO.DataVO;

import java.util.List;

public interface StudentService {
    Student findByIdCard(String idCard);

    Integer addStudent(Student student);

    Integer applyDeleteStudent(List<String> idCardList);

    Integer recoverStudent(String idCard);

    //根据wantDelete查看是否是想退档的学员
    DataVO<StudentInfo> findAllStudentVO(Integer page, Integer limit, String userName, String wantDelete);

    DataVO<StudentInfo> findByConditionVO(Integer page, Integer limit, StudentInfo studentInfo);


}

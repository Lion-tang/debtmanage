package com.lyontang.debtmanage.mapper;

import com.lyontang.debtmanage.entity.Student;
import com.lyontang.debtmanage.entity.StudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    Student findByIdCard(String idCard);

    Integer addStudent(Student student);

    Integer applyDeleteStudent(List<String> idCardList);

    Integer recoverStudent(String idCard);

    List<StudentInfo> findAllStudent(String userName,String wantDelete);

    List<StudentInfo> findByCondition(StudentInfo studentInfo);
}

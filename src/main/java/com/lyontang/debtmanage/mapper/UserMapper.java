package com.lyontang.debtmanage.mapper;

import com.lyontang.debtmanage.entity.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserMapper {

     User findUserByName(String userName);

     Integer addUser(String userName, String password, String phone);

     Integer deleteUser(String userName);

     Integer updateUser(String userName,String password, String phone);

     UserRolePhone findUserRolePhoneByName(String userName);

     List<UserRolePhone> findAllUserRolePhone();

     List<UserRolePhone> findByCondition(String userName,String phone);

     List<StudentInfo> adminGetAllStudent(Student student);

     Integer deleteStudent(List<String> idCardList);

     List<Bar> getBarData(String startDate, String endDate);

     List<Pie> getPieData(String startDate, String endDate);

     List<Bar> getLineData(String startDate, String endDate, String userName);
}

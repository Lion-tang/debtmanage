package com.lyontang.debtmanage.service;

import com.lyontang.debtmanage.entity.*;
import com.lyontang.debtmanage.entity.VO.BarVO;
import com.lyontang.debtmanage.entity.VO.DataVO;

import java.util.List;


public interface UserService {

    User findUserByName(String userName);//添加用户需要用到，验证是否存在相同用户名

    Integer addUser(String userName,String password,String phone);

    Integer deleteUser(String userName);

    Integer updateUser(String userName,String password, String phone);

    UserRolePhone findUserRolePhoneByName(String userName);//角色验证时需要用到，根据用户名查询用户的角色名

    DataVO<UserRolePhone> findAllUserRolePhoneVO(Integer page, Integer limit);

    DataVO<UserRolePhone> findByConditionVO(Integer page,Integer limit,String userName, String phone);

    DataVO<StudentInfo> adminGetAllStudent(Integer page, Integer limit, Student student);

    Integer deleteStudent(List<String> idCardList);

    BarVO getBarData(String startDate, String endDate);

    List<Pie> getPieData(String startDate, String endDate);

    BarVO getLineData(String startDate, String endDate, String userName);
}

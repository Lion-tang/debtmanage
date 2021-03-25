package com.lyontang.debtmanage.service;

import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.UserRolePhone;
import com.lyontang.debtmanage.entity.VO.DataVO;

import java.util.List;


public interface UserService {
    List<User> findAllUser();

    User findUserByName(String userName);


    Integer addUser(String userName,String password,String phone);

    Integer deleteUser(String userName);

    Integer updateUser(String userName,String password, String phone);

    UserRolePhone findUserRolePhoneByName(String userName);

    DataVO<UserRolePhone> findAllUserRolePhoneVO(Integer page, Integer limit);

    DataVO<UserRolePhone> findByConditionVO(Integer page,Integer limit,String userName, String phone);




}

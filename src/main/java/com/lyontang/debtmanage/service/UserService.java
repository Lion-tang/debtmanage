package com.lyontang.debtmanage.service;

import com.lyontang.debtmanage.entity.Role;
import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.VO.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<User> findAllUser();

    User findUserByName(String userName);

    UserRole findUserRoleByName(String userName);

    Integer addUser(String userName,String password);

    Integer deleteUser(String userName);

    Integer updateUser(String userName, String password);

}

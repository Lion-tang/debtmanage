package com.lyontang.debtmanage.mapper;

import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.VO.UserRole;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
     List<User> findAllUser();

     User findUserByName(String userName);

     UserRole findUserRoleByName(String userName);

     Integer addUser(String userName,String password);

     Integer deleteUser(String userName);

     Integer updateUser(String userName, String password);
}

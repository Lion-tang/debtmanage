package com.lyontang.debtmanage.service.serviceImpl;

import com.lyontang.debtmanage.entity.Role;
import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.VO.UserRole;
import com.lyontang.debtmanage.mapper.UserMapper;
import com.lyontang.debtmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User findUserByName(String userName) {
        return  userMapper.findUserByName(userName);
    }

    @Override
    public UserRole findUserRoleByName(String userName) {
        return userMapper.findUserRoleByName(userName);
    }

    @Override
    public Integer addUser(String userName,String password) {
        return userMapper.addUser(userName,password);
    }

    @Override
    public Integer deleteUser(String userName) {
        return userMapper.deleteUser(userName);
    }

    @Override
    public Integer updateUser(String userName, String password) {
        return userMapper.updateUser(userName,password);
    }


}

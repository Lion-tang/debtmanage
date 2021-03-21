package com.lyontang.debtmanage.service.serviceImpl;

import com.lyontang.debtmanage.entity.Role;
import com.lyontang.debtmanage.mapper.RoleMapper;
import com.lyontang.debtmanage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }


}

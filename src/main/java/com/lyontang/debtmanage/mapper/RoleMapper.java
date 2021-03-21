package com.lyontang.debtmanage.mapper;

import com.lyontang.debtmanage.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    List<Role> findAllRole();

}

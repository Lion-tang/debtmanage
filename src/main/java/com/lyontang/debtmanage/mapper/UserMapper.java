package com.lyontang.debtmanage.mapper;

import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.UserRolePhone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
     List<User> findAllUser();

     User findUserByName(String userName);

     Integer addUser(String userName, String password, String phone);

     Integer deleteUser(String userName);

     Integer updateUser(String userName,String password, String phone);

     UserRolePhone findUserRolePhoneByName(String userName);

     List<UserRolePhone> findAllUserRolePhone();

     List<UserRolePhone> findByCondition(String userName,String phone);

     Integer findByConditionCount(String userName, String phone);


}

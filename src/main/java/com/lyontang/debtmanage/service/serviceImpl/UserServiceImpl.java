package com.lyontang.debtmanage.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyontang.debtmanage.entity.User;
import com.lyontang.debtmanage.entity.UserRolePhone;
import com.lyontang.debtmanage.entity.VO.DataVO;
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
    public List<User> findAllUser() { return userMapper.findAllUser(); }

    @Override
    public User findUserByName(String userName) {
        return  userMapper.findUserByName(userName);
    }

    @Override
    public Integer addUser(String userName,String password,String phone) {
        return userMapper.addUser(userName,password,phone);
    }

    @Override
    public Integer deleteUser(String userName) {
        return userMapper.deleteUser(userName);
    }

    @Override
    public Integer updateUser(String userName,String password, String phone) {
        return userMapper.updateUser(userName,password,phone);
    }

    @Override
    public UserRolePhone findUserRolePhoneByName(String userName) {
        return userMapper.findUserRolePhoneByName(userName);
    }

    @Override
    public DataVO<UserRolePhone> findAllUserRolePhoneVO(Integer page, Integer limit) {
        DataVO<UserRolePhone> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        //使用PageHelper插件分页，后面紧跟一个查询语句
        PageHelper.startPage(page, limit);
        List<UserRolePhone> list = userMapper.findAllUserRolePhone();
        dataVO.setCount((int)((Page)list).getTotal());
        dataVO.setData(list);
        return dataVO;
    }


    @Override
    public DataVO<UserRolePhone> findByConditionVO(Integer page,Integer limit,String userName, String phone) {
        DataVO<UserRolePhone> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        PageHelper.startPage(page, limit);
        List<UserRolePhone> list = userMapper.findByCondition(userName, phone);
        dataVO.setCount((int)((Page)list).getTotal());
        dataVO.setData(list);
        return dataVO;
    }


    //查询所有用户数量，使用PageHelper之后就不需要在查一次sql了
    // PageHelper会把相关分页信息(包括所有记录数)封装到Page中，强转为Page就可以使用
//    @Override
//    public Integer findUserNum() {
//        return userMapper.findUserNum();
//    }


}

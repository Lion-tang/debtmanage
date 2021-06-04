package com.lyontang.debtmanage.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lyontang.debtmanage.entity.*;
import com.lyontang.debtmanage.entity.VO.BarVO;
import com.lyontang.debtmanage.entity.VO.DataVO;
import com.lyontang.debtmanage.mapper.UserMapper;
import com.lyontang.debtmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


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

    ////角色验证时需要用到，根据用户名查询用户的角色名
    @Override
    public UserRolePhone findUserRolePhoneByName(String userName) {
        return userMapper.findUserRolePhoneByName(userName);
    }



    @Override
    public DataVO<UserRolePhone> findAllUserRolePhoneVO(Integer page, Integer limit) {

        //使用PageHelper插件分页，后面紧跟一个查询语句
        PageHelper.startPage(page, limit);
        List<UserRolePhone> list = userMapper.findAllUserRolePhone();
        int count =(int)((Page)list).getTotal();
        return DataVO.getDataVO(count, list);
    }


    @Override
    public DataVO<UserRolePhone> findByConditionVO(Integer page,Integer limit,String userName, String phone) {
        PageHelper.startPage(page, limit);
        List<UserRolePhone> list = userMapper.findByCondition(userName, phone);
        int count = (int)((Page)list).getTotal();
        return DataVO.getDataVO(count,list);
    }

    @Override
    public DataVO<StudentInfo> adminGetAllStudent(Integer page, Integer limit, Student student) {

        //使用PageHelper插件分页，后面紧跟一个查询语句
        PageHelper.startPage(page, limit);
        List<StudentInfo> list = userMapper.adminGetAllStudent(student);
        int count = (int)((Page)list).getTotal();
        return DataVO.getDataVO(count,list);
    }

    @Override
    public Integer deleteStudent(List<String> idCardList) {
        return userMapper.deleteStudent(idCardList);
    }



    @Override
    public BarVO getBarData(String startDate, String endDate) {
        List<Bar> barList =  userMapper.getBarData(startDate, endDate);
        return BarVO.getBarVO(barList);
}

    @Override
    public List<Pie> getPieData(String startDate, String endDate) {
        return userMapper.getPieData(startDate,endDate);
    }

//    Line的数据结构和Bar类似，这里直接借用
    @Override
    public BarVO getLineData(String startDate, String endDate, String userName) {
        List<Bar> lineList = userMapper.getLineData(startDate, endDate, userName);
        return BarVO.getBarVO(lineList);
    }


}

package com.lyontang.debtmanage.controller;

import com.lyontang.debtmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class GeneralHandler {


    @Autowired
    private  StudentService studentService;
    //产生逻辑视图对应的物理页面
    @RequestMapping("/{url}")
    public String getProperty(@PathVariable("url") String url) {
        return url;
    }



}

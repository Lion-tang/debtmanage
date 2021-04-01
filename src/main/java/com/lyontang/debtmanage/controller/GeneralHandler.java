package com.lyontang.debtmanage.controller;

import com.lyontang.debtmanage.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

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

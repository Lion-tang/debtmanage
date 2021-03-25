package com.lyontang.debtmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralHandler {

    @RequestMapping("/{url}")
    public String getProperty(@PathVariable("url") String url) {
        return url;
    }



}

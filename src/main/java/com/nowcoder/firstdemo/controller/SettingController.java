package com.nowcoder.firstdemo.controller;

import com.nowcoder.firstdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ae on 2018/8/11.
 */

@Controller
public class SettingController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/setting", method = {RequestMethod.GET})
    @ResponseBody
    public String setting() {
        return "1";
    }
}

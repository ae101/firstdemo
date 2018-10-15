package com.nowcoder.firstdemo.controller;

import com.nowcoder.firstdemo.service.WendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by ae on 2018/8/11.
 */

@Controller
public class SettingController {
    @Autowired
    WendaService wendaService;

    @RequestMapping(path = "/setting", method = {RequestMethod.GET})
    @ResponseBody
    public String setting(HttpSession httpSession) {
        return "Setting Ok." + wendaService.getMessage(1);
    }
}

package com.nowcoder.firstdemo.controller;

import com.nowcoder.firstdemo.aspect.LoggerAspect;
import com.nowcoder.firstdemo.model.User;
import com.nowcoder.firstdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by ae on 2018/8/10.
 */

//@Controller
public class IndexController {
    @Autowired
    UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @RequestMapping(path = {"/","/index"})
    @ResponseBody
    public String index(HttpSession httpSession) {
        logger.info("ING");
        return "Hello!" + httpSession.getAttribute("msg");
    }


    @RequestMapping(path = {"/profine/{groupId}/{userId}"}, method = {RequestMethod.GET})
    @ResponseBody
    public String profine(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type",defaultValue = "1") int type,
                          @RequestParam(value = "key",defaultValue = "zz",required = false) String key) {
        return String.format("This is %s / %d, %s : %d", groupId, userId, key, type);
    }

    @RequestMapping(path = {"profine1/{gId}/{uId}"}, method = {RequestMethod.GET})
    @ResponseBody
    public String profine1(@PathVariable("gId") String gId,
                           @PathVariable("uId") int uId,
                           @RequestParam(value = "type", defaultValue = "1") int type,
                           @RequestParam(value = "key", defaultValue = "zcl") String key) {
        return String.format("This is %s / %d, %s:%d",gId,uId,key,type);
    }

    @RequestMapping(path = {"/vm"},method = {RequestMethod.GET})
    public String template(Model model) {
        model.addAttribute("value1","vvvvv");

        List<String> colors = Arrays.asList(new String[] {"RED","BLUE","YELLOW"});
        model.addAttribute("colors",colors);

        Map<String, String> map = new HashMap<>();
        for (int i = 0;i<5;i++) {
            map.put(String.valueOf(i),String.valueOf(i*i));
        }
        model.addAttribute("map",map);
        model.addAttribute("user", new User("Lee"));
        return "hello";
    }

    @RequestMapping(path = "/vm1", method = RequestMethod.GET)
    public String template1(Model model) {
        model.addAttribute("value1","vvv");

        List<String> colors = Arrays.asList(new String[]{"RED","YELLOW","BLACK"});
        model.addAttribute("colors",colors);
        return "hello1";
    }

    @RequestMapping(path = {"/request"})
    @ResponseBody
    public String request(Model model, HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session,
                          @CookieValue("JSESSIONID") String sessionId) {
        StringBuffer sb = new StringBuffer();
        sb.append("COOKIEVALUE:" + sessionId + "<br>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }

        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                sb.append("Cookie:" + cookie.getName() + "value:" + cookie.getValue() + "<br>");
            }
        }
        sb.append(request.getMethod() + "<br>");
        sb.append(request.getQueryString() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        sb.append(request.getRequestURI() + "<br>");//URI?

        response.addHeader("zcl","nowcoder");
        response.addCookie(new Cookie("username","nowcoder"));

        return sb.toString();
    }

    @RequestMapping(path = "/request1/{rId}",method = RequestMethod.GET)
    @ResponseBody
    public String request1(@PathVariable("rId") int rId,
                           HttpServletRequest request,
                           HttpServletResponse response,
                           HttpSession session) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getRequestURI() + "<br>");
        sb.append(request.getPathInfo() + "<br>");
        return sb.toString();
    }

    @RequestMapping(path = {"/redirect/{code}"}, method = {RequestMethod.GET})
    public RedirectView redirct(@PathVariable("code")int code,
                                HttpSession httpSession) {
        httpSession.setAttribute("msg","jump from rediect");
        RedirectView redirectView = new RedirectView("/",true);
        if (code == 301) {
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        return redirectView;
    }


    @RequestMapping(path = {"/redirects/{code}"},method = {RequestMethod.GET})
    public String redirects(@PathVariable("code") int code,
                           HttpSession session) {
        session.setAttribute("msg","I had redirct");
        return "redirect:/";
    }


    @RequestMapping(path = {"/admin"}, method = {RequestMethod.GET})
    @ResponseBody
    public String admin(@RequestParam("key") String key) {
        if ("admin".equals(key)) {
            return "hello admin";
        }
        throw new IllegalArgumentException("参数不对");
    }

    @ExceptionHandler()//统一的异常处理
    @ResponseBody
    public String error(Exception e) {
        return "error:" + e.getMessage();
    }
}

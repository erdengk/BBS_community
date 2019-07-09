package com.dk.bbs.controller;

import com.dk.bbs.mapper.UserMapper;
import com.dk.bbs.model.User;
import com.dk.bbs.pro.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : dk
 * @date : 2019/7/7 11:27
 * @description :
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.equals("token"))
            {
                String token = cookie.getName();
                User user = JDBC.findByToken(token);
                if (user!=null)
                {
                    request.getSession().setAttribute("user",user);
                }

            }
        }
        return "index";
    }
}

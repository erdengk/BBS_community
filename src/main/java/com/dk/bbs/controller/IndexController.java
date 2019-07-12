package com.dk.bbs.controller;

import com.dk.bbs.dto.QusetionDTO;
import com.dk.bbs.mapper.UserMapper;
import com.dk.bbs.model.User;
import com.dk.bbs.service.QusetionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : dk
 * @date : 2019/7/7 11:27
 * @description :
 */
@Controller
public class IndexController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QusetionService qusetionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model)
    {

        Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.equals("token"))
                {
                    String token = cookie.getName();
                    User user = userMapper.findByToken(token);
                    System.out.println("index:"+user.toString());
                    if (user!=null)
                    {
                        request.getSession().setAttribute("user",user);
                        System.out.println("index:"+user.toString());
                    }
                }
            }


       List<QusetionDTO> qusetionList = qusetionService.list();

        model.addAttribute("qusetionList",qusetionList);

        System.out.println("indexController j结束");


        return "index";
    }
}

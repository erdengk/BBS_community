package com.dk.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : dk
 * @date : 2019/7/12 16:13
 * @description :
 */
@Controller
public class Hello {
    @GetMapping("hello")
    public String hello(Model model)
    {
        model.addAttribute("s","s");
        return  "index";
    }

}

package com.dk.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : dk
 * @date : 2019/7/7 11:27
 * @description :
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}

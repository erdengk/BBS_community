package com.dk.bbs.controller;

import com.dk.bbs.model.Qusetion;
import com.dk.bbs.model.User;
import com.dk.bbs.pro.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : dk
 * @date : 2019/7/9 14:59
 * @description :
 */

@Controller
public class PublicController {
    @Autowired
  //  private QuesstionMapper quesstionMapper;


    @GetMapping("/publish")
    public String publish()
    {
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
            )
    {
        User user = (User) request.getSession().getAttribute("user");
        if (user==null)
        {
            System.out.println("ssssssss");
            model.addAttribute("error","用户未登录");
        }
        System.out.println(user.toString());
        JDBC jdbc = new JDBC();
        Qusetion qusetion = new Qusetion();
        qusetion.setTitle(title);
        qusetion.setDescription(description);
        qusetion.setTag(tag);
        qusetion.setCreator(user.getId());
        qusetion.setGmt_create(System.currentTimeMillis());
        qusetion.setGmt_modified(qusetion.getGmt_create());
        jdbc.Create(qusetion);
        return "redirect:/";
    }


}

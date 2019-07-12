package com.dk.bbs.controller;

import com.dk.bbs.mapper.QusetionMapper;
import com.dk.bbs.mapper.UserMapper;
import com.dk.bbs.model.Qusetion;
import com.dk.bbs.model.User;
import com.dk.bbs.pro.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : dk
 * @date : 2019/7/9 14:59
 * @description :
 */

@Controller
public class PublicController {
    @Autowired
    private QusetionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;

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
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
     //   model.addAttribute("tags", TagCache.get());
        if (title == null || title == "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == "") {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (tag == null || tag == "") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

//        String invalid = TagCache.filterInvalid(tag);
//        if (StringUtils.isNotBlank(invalid)) {
//            model.addAttribute("error", "输入非法标签:" + invalid);
//            return "publish";
//        }
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0)
        {
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
        }
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
        int num =userMapper.findeByAccountId(user.getGmt_create());

        user.setId(num);
        System.out.println("sss"+user.getId());
        qusetion.setCreator(user.getId());
        qusetion.setGmt_create(System.currentTimeMillis());
        qusetion.setGmt_modified(qusetion.getGmt_create());
        quesstionMapper.createQusetion(qusetion);
        //jdbc.Create(qusetion);
        return "redirect:/";
    }


}

package com.dk.bbs.controller;

import com.dk.bbs.dto.AccessTokenDTO;
import com.dk.bbs.dto.GithubProvider;
import com.dk.bbs.dto.GithubUser;
import com.dk.bbs.mapper.UserMapper;
import com.dk.bbs.model.User;
import com.dk.bbs.pro.JDBC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;

/**
 * @author : dk
 * @date : 2019/7/7 15:04
 * @description :授权控制器
 */

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private   UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response, HttpSession session,
                           Map<String, Object> paramMap, Model model) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri(redirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            User user1 = new User();
            String token = UUID.randomUUID().toString();
            user1.setToken(token);
            user1.setName(githubUser.getName());
            user1.setAccount_Id(String.valueOf(githubUser.getId()));
            user1.setGmt_create(System.currentTimeMillis());
            user1.setGmt_modeified(user1.getGmt_create());
            user1.setAvatar_url(githubUser.getAvatar_url());
            JDBC jdbc = new JDBC();
            jdbc.Insert(user1);
            model.addAttribute("user",user1);
            session.setAttribute("user",user1);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
    }
//    @GetMapping
//    public String callback(@RequestParam(name = "code",required=false) String code,
//                           @RequestParam(name = "state", required=false ) String state,
//                           HttpServletRequest request, HttpServletResponse response
//                           ) {
//        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
//        //获得一个授权对象
//        accessTokenDTO.setClient_id(clientId);
//        accessTokenDTO.setClient_secret(clientSecret);
//        accessTokenDTO.setCode(code);
//        accessTokenDTO.setState(state);
//        accessTokenDTO.setRedirectUri(redirectUri);
//        //赋值各项属性
//        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        //得到touken
//        GithubUser user = githubProvider.getUser(accessToken);
//        //user对象携带token去拿到用户信息  赋值给   githubUser
//        if (user != null) {
//            //登录成功
//            User user1 = new User();
//            String token = UUID.randomUUID().toString();
//            user1.setToken(token);
//            user1.setName(user.getName());
//            user1.setAccount_Id(String.valueOf(user.getId()));
//            user1.setGmt_create(System.currentTimeMillis());
//            user1.setGmt_modeified(user1.getGmt_create());
//
//            response.addCookie(new Cookie("token", token));
//            request.getSession().setAttribute("user", user);
//            return "index";
//        }
//        return "index";
//    }

}

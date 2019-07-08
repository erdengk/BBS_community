package com.dk.bbs.pro;

import com.dk.bbs.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * @author : dk
 * @date : 2019/7/8 18:33
 * @description :
 */
@Component(value="jdbcTemplateDemo")
public class JDBC {

    public void Insert(User user){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=yes&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        String sql="insert into user(name,account_id,token,gmt_create,gmt_modeified) values(?,?,?,?,?)";
        int count=jdbcTemplate.update(sql,user.getName(),user.getAccount_Id(),user.getToken(),user.getGmt_create(),user.getGmt_modeified());
        if(count==1) {
            System.out.println("success");
        }
    }
    public static User findByToken(String token)
    {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        String sql="select * from user where token = ?";
        return jdbcTemplate.queryForObject(sql,User.class);
    }


}

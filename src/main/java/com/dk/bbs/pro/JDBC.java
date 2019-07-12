package com.dk.bbs.pro;

import com.dk.bbs.model.Qusetion;
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
        String sql="insert into user(name,account_id,token,gmt_create,gmt_modeified,avatar_url) values(?,?,?,?,?,?)";
        int count=jdbcTemplate.update(sql,user.getName(),user.getAccount_Id(),user.getToken(),user.getGmt_create(),user.getGmt_modeified(),user.getAvatar_url());
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

    public void Create(Qusetion qusetion){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=yes&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        String sql="insert into qusetion" +
                "(title,description,tag,creator,gmt_create,gmt_modified) values(?,?,?,?,?,?)";
        int count=jdbcTemplate.update(sql,
                qusetion.getTitle(),qusetion.getDescription()
        ,qusetion.getTag(),qusetion.getCreator(),qusetion.getGmt_create(),qusetion.getGmt_modified());
        if(count==1) {
            System.out.println("success");

        }
    }
//    public List<Qusetion> qusetionList() {
//        DriverManagerDataSource dataSource=new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=yes&characterEncoding=utf8");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
//        String sql = "select * from qusetion";
//        List<Qusetion> qusetionList=jdbcTemplate.update(sql,new MyRowMapper());
//        if(count==1) {
//            System.out.println("查询所有问题success");
//        }
//        return
//    }

//    class MyRowMapper implements RowMapper<Qusetion> {
//
//        @Override
//        public Qusetion mapRow(ResultSet rs, int num) throws SQLException {
//            //从结果集里把数据得到
//            String id=rs.getString("id");
//            String title=rs.getString("title");
//            String description=rs.getString("description");
//            String gmt_create=rs.getString("gmt_create");
//            String gmt_modified=rs.getString("gmt_modified");
//            String title=rs.getString("title");
//            //把数据封装到对象里
//            Qusetion qusetion =new Qusetion();
//            qusetion.setId(id);
//            return qusetion;
//        }
//    }
}

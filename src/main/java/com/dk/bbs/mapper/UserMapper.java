package com.dk.bbs.mapper;

import com.dk.bbs.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author : dk
 * @date : 2019/7/8 16:00
 * @description :
 */
@Mapper
public interface UserMapper {

   // public static User findByToken(String token) ;

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);

    @Select("select * from user where id=#{id}")
    User findId(Integer id);

    @Select("select * from user where token =#{token}")
    User findByToken(String token);

    @Select(("select id from user where gmt_create=#{gmt_create}"))
    int findeByAccountId(Long gmt_create);
}

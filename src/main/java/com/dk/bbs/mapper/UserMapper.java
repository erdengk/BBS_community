package com.dk.bbs.mapper;

import com.dk.bbs.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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
}

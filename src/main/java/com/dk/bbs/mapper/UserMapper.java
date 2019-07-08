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
    @Insert("insert into user (name,account_id,token,gmt,create) values (#{name},#{account_id},#{token},#{gmt},#{create})")
    void insert(User user);
}

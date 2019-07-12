package com.dk.bbs.mapper;

import com.dk.bbs.model.Qusetion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : dk
 * @date : 2019/7/10 20:40
 * @description :
 */

@Mapper
public interface QusetionMapper {

    @Insert("insert into qusetion " +
            "(title,description,tag,creator,gmt_create,gmt_modified)" +
            " values ( #{title},#{description},#{tag},#{creator},#{gmt_create},#{gmt_modified})")
    public void createQusetion(Qusetion qusetion);

    @Select("select * from qusetion")
    List<Qusetion> list();
}

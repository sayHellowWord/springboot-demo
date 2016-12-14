package com.yd.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by wubo on 2016/12/14.
 */
@Mapper
public interface DemoMapper {

    @Select("SELECT name FROM test WHERE id = #{id}")
    String getNameById(@Param("id") String id);

}

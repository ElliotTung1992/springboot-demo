package com.github.dge1992.mybatis.mapper;


import com.github.dge1992.mybatis.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
public interface UserMapper {

    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age")
    })
    @Select("SELECT * FROM TEST_USER WHERE NAME = #{name}")
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO TEST_USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("INSERT INTO TEST_USER(NAME, AGE) VALUES(#{name,jdbcType=VARCHAR}, #{age,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO TEST_USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insertByUser(User user);

    @Update("UPDATE TEST_USER SET age=#{age} WHERE name=#{name}")
    void update(User user);

    @Delete("DELETE FROM TEST_USER WHERE id =#{id}")
    void delete(Long id);
}

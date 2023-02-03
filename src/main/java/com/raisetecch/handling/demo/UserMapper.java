package com.raisetecch.handling.demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(int id);

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findUserByID(int id);

    @Insert("INSERT INTO users (id, name) VALUES ( #{id}, #{name})")
    int insertUser(@Param("id") int id, @Param("name") String name);

    @Select("SELECT * FROM users WHERE id = #{id} AND name = #{name}")
    Optional<User> findByIdAndName(int id, String name);
}

package com.raisetecch.handling.demo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(int id);

    @Insert("INSERT INTO users (name) VALUES (#{name})")
    void registrationUserByName(String name);

    @Select("SELECT * FROM users WHERE id = #{id} AND name = #{name}")
    Optional<User> findByIdAndName(int id, String name);
}

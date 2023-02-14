package com.raisetecch.handling.demo.mapper;

import com.raisetecch.handling.demo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<User> findById(int id);

    @Insert("INSERT INTO users (name, age) VALUES (#{name}, #{age})")
    void registrationUserByName(String name, int age);

    @Delete("DELETE FROM users WHERE id = #{id}")
    boolean deleteById(int id);

    @Update("UPDATE users SET name = #{name} WHERE id = #{id}")
    void updateNameById(int id, String name);

    @Select("SELECT * FROM users WHERE id = #{id} AND name = #{name}")
    Optional<User> findByIdAndName(int id, String name);
}

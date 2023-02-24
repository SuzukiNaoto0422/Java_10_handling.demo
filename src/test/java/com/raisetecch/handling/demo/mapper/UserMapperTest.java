package com.raisetecch.handling.demo.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetecch.handling.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

@DBRider
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidでユーザーのすべての要素を取得できていること() {
        int id = 1;

        Optional<User> user = userMapper.findById(id);
        Assertions.assertTrue(user.isPresent());
        assertThat(user.get()).isEqualTo(new User(1, "suzuki", 30));
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 入力したnameとageで新規ユーザーの登録ができていること() {
        String name = "yamauchi";
        int age = 28;

        userMapper.registryUser(name, age);
        Optional<User> newUser = userMapper.findById(5);
        assertTrue(newUser.isPresent());
        assertThat(newUser.get()).isEqualTo(new User(5,"yamauchi", 28));
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 入力されたidで対象のユーザーの削除ができていること() {
        String name = "sasaki";
        int age = 25;

        userMapper.deleteById(4);
        Optional<User> deletedUser = userMapper.findById(4);
        assertFalse(deletedUser.isPresent());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 入力されたidで対象のユーザーの名前の更新ができていること() {
        String name = "sasaki";
        int age = 25;

        userMapper.updateNameById(4, "isibashi");
        Optional<User> updatedUser = userMapper.findById(4);
        assertThat(updatedUser.get()).isEqualTo(new User(4, "isibashi", 25));
    }

}

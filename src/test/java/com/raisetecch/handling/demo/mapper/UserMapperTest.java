package com.raisetecch.handling.demo.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetecch.handling.demo.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@DBRider
@MybatisTest
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
    @ExpectedDataSet(value = "datasets/updatedUsers.yml", ignoreCols = "id")
    void 入力したnameとageで新規ユーザーの登録ができていること() {
        String name = "yamauchi";
        int age = 28;

        userMapper.registryUser(name, age);
    }

}

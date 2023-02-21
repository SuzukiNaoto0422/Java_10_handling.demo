package com.raisetecch.handling.demo.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetecch.handling.demo.entity.User;
import com.raisetecch.handling.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Optional;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidでユーザーのすべての要素を取得できていること(int id) {
        Optional<User> user = userMapper.findById(id);
        assertThat(user)
                .hasSize(3)
                .contains(
                        new User(1, "suzuki", 30),
                        new User(2, "satou", 25),
                        new User(3, "takahashi", 18)
                );
    }
}

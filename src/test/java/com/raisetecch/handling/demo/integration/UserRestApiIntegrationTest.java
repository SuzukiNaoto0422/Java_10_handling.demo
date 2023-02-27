package com.raisetecch.handling.demo.integration;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetecch.handling.demo.entity.User;
import com.raisetecch.handling.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserMapper userMapper;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーの全要素が取得できること() throws Exception {
        List<User> users = userMapper.findAllUsers();

        for (User user : users) {
            mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", user.getId()))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                              {
                                "id": %d,
                                "name": "%s",
                                "age": %d
                              }
                            """.formatted(user.getId(), user.getName(), user.getAge())));
        }
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void nameとidを入力したユーザーの登録ができること() throws Exception {
        userMapper.registryUser("tainaka", 22);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 5))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                              {
                                "id": 5,
                                "name": "tainaka",
                                "age": 22
                              }
                            """));
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーの削除ができること() throws Exception {
        userMapper.deleteById(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーのnameが更新できること() throws Exception {
        userMapper.updateNameById(1, "yamamoto");

        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().json("""
                              {
                                "id": 1,
                                "name": "yamamoto",
                                "age": 30
                              }
                            """));
    }

}

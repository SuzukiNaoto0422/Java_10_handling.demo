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
import org.springframework.http.MediaType;
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
        /*
        ステータスコード: 200 OK
        レスポンスボディ:
            {
                "id": ユーザーのid,
                "name": ユーザーの名前,
                "age": ユーザーの年齢
            }
         */
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
    void 存在しないユーザーを取得しようとしたこと() throws Exception {
        /*
        ステータスコード: 404 Not Found
        レスポンスボディ:
            {
                "message": ユーザーが見つからない場合のメッセージ
            }
        */
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 6)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "message": "user not found"
                        }
                        """)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーの削除ができること() throws Exception {
        /*
        ステータスコード: 200 OK
        */
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 2))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないユーザーを削除しようとしたこと() throws Exception {
        /*
        ステータスコード: 404 Not Found
        レスポンスボディ:
            {
                "message": ユーザーが見つからない場合のメッセージ
            }
         */
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 6)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "message": "user not found"
                        }
                        """)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーのnameが更新できること() throws Exception {
        /*
        ステータスコード: 200 OK
        レスポンスボディ:
            {
                "name": 変更後のユーザーの名前,
            }
         */
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "name": "yamamoto"
                        }
                        """)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 存在しないユーザーのnameを更新しようとしたこと() throws Exception {
        /*
        ステータスコード: 404 Not Found
        レスポンスボディ:
            {
                "message": ユーザーが見つからない場合のメッセージ
            }
         */
        mockMvc.perform(MockMvcRequestBuilders.patch("/users/{id}", 6)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "message": "user not found"
                        }
                        """)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}

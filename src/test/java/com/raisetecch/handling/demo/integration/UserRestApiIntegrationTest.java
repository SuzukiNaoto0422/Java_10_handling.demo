package com.raisetecch.handling.demo.integration;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raisetecch.handling.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidのユーザーの全要素が取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        JSONAssert.assertEquals(
                " {" +
                " \"id\": 1," +
                " \"name\": \"suzuki\"," +
                " \"age\": 30" +
                " }" , response, JSONCompareMode.STRICT);
    }
}

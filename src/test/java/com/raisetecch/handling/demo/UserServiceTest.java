package com.raisetecch.handling.demo;

import com.raisetecch.handling.demo.entity.User;
import com.raisetecch.handling.demo.mapper.UserMapper;
import com.raisetecch.handling.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserMapper userMapper;

    @Test
    public void 存在するユーザーのIDを指定されたときに正常にユーザーを返すこと() throws Exception {
        doReturn(Optional.of(new User(1, "suzuki", 30))).when(userMapper).findById(1);

        User actual = userService.findUser(1);
        assertThat(actual, equalTo(new User(1, "suzuki", 30)));
        verify(userMapper, times(1)).findById(1);
    }
}

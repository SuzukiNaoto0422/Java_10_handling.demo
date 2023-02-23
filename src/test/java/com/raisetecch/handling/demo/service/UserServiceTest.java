package com.raisetecch.handling.demo.service;

import com.raisetecch.handling.demo.entity.User;
import com.raisetecch.handling.demo.entity.UserForm;
import com.raisetecch.handling.demo.exception.ResourceNotFoundException;
import com.raisetecch.handling.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserMapper userMapper;

    @Test
    public void 存在するユーザーのidを指定された時に正常にユーザーを返すこと() throws Exception {
        doReturn(Optional.of(new User(1, "suzuki", 30))).when(userMapper).findById(1);

        User actual = userService.findUser(1);
        assertThat(actual, equalTo(new User(1, "suzuki", 30)));
        verify(userMapper).findById(1);
    }

    @Test
    public void ユーザーのnameとageが入力された時に正常にユーザーの登録をすること() throws Exception {
        doNothing().when(userMapper).registryUser("suzuki", 30);

        UserForm actual = userService.entryUser("suzuki", 30);
        assertThat(actual,equalTo(new UserForm("suzuki", 30)));
        verify(userMapper).registryUser("suzuki", 30);
    }

    @Test
    public void 存在するユーザーのidを指定された時に正常にユーザーを削除すること() throws Exception{

        User user = new User();
        user.setId(1);
        when(userMapper.findById(1)).thenReturn(Optional.of(user));
        when(userMapper.deleteById(1)).thenReturn(true);

        boolean result = userService.deleteUser(1);

        assertTrue(result);
        verify(userMapper).findById(1);
        verify(userMapper).deleteById(1);
    }

    @Test
    public void 存在しないユーザーのidを指定された時にResourceNotFoundExceptionがスローされること() throws Exception {

        when(userMapper.findById(2)).thenReturn(Optional.empty());

        userService.deleteUser(2);

        verify(userMapper).findById(2);
        verify(userMapper, never()).deleteById(anyInt());
    }



}

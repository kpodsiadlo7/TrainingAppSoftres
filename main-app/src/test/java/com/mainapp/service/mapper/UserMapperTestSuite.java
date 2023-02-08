package com.mainapp.service.mapper;

import com.mainapp.MainAppApplication;
import com.mainapp.security.AuthorityEntity;
import com.mainapp.service.data.User;
import com.mainapp.web.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    void mapToUserFromUserDto(){
        //given
        UserDto userDto = new UserDto(
                7L,
                "username",
                "password",
                "confirmpassword",
                Set.of(new AuthorityEntity())
        );
        //when
        User userAfterMapper = userMapper.mapToUserFromUserDto(userDto);
        //then
        Assertions.assertEquals(7,userAfterMapper.getId());
        Assertions.assertEquals("username",userAfterMapper.getUsername());
        Assertions.assertEquals("confirmpassword",userAfterMapper.getConfirmPassword());
        Assertions.assertEquals(1,userAfterMapper.getAuthorities().size());
    }

    @Test
    void mapToUserDtoFromUser(){
        //given
        User user = new User(
                7L,
                "username",
                "password",
                "confirmpassword",
                Set.of(new AuthorityEntity())
        );
        //when
        UserDto userAfterMapper = userMapper.mapToUserDtoFromUser(user);
        //then
        Assertions.assertEquals(7,userAfterMapper.getId());
        Assertions.assertEquals("username",userAfterMapper.getUsername());
        Assertions.assertEquals("confirmpassword",userAfterMapper.getConfirmPassword());
        Assertions.assertEquals(1,userAfterMapper.getAuthorities().size());
    }
}

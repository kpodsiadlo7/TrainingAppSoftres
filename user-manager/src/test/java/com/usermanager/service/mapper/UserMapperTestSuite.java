package com.usermanager.service.mapper;

import com.usermanager.UserManagerApplication;
import com.usermanager.domain.UserEntity;
import com.usermanager.service.data.User;
import com.usermanager.web.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserMapperTestSuite {
    @Autowired
    private UserMapper userMapper;


    @Test
    void mapToUserDtoFromUser(){
        //given
        User user = prepareUser();
        //when
        UserDto userAfterMapper = userMapper.mapToUserDtoFromUser(user);
        //then
        Assertions.assertEquals(7L,userAfterMapper.getId());
        Assertions.assertEquals("username",userAfterMapper.getUsername());
        Assertions.assertEquals("password",userAfterMapper.getPassword());

    }
    @Test
    void mapToUserFromUserDto(){
        //given
        UserDto userDto = prepareUserDto();
        //when
        User userAfterMapper = userMapper.mapToUserFromUserDto(userDto);
        //then
        Assertions.assertEquals(7L,userAfterMapper.getId());
        Assertions.assertEquals("username",userAfterMapper.getUsername());
        Assertions.assertEquals("password",userAfterMapper.getPassword());

    }
    @Test
    void mapToUserEntityFromUser(){
        //given
        User user = prepareUser();
        //when
        UserEntity userAfterMapper = userMapper.mapToUserEntityFromUser(user);
        //then
        Assertions.assertNull(userAfterMapper.getId());
        Assertions.assertEquals("username",userAfterMapper.getUsername());
        Assertions.assertEquals("password",userAfterMapper.getPassword());
    }
    @Test
    void mapToUserFromUserEntity(){
        //given
        UserEntity userEntity = new UserEntity("username","password");
        //when
        User userAfterMapper = userMapper.mapToUserFromUserEntity(userEntity);
        //then
        Assertions.assertNull(userAfterMapper.getId());
        Assertions.assertEquals("username",userAfterMapper.getUsername());
        Assertions.assertEquals("password",userAfterMapper.getPassword());
    }
    private User prepareUser(){
        return new User(
                7L,
                "username",
                "password"
        );
    }
    private UserDto prepareUserDto(){
        return new UserDto(
                7L,
                "username",
                "password"
        );
    }
}

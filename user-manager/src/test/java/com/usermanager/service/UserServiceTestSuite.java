package com.usermanager.service;

import com.usermanager.UserManagerApplication;
import com.usermanager.repository.adapter.AdapterUserEntityRepository;
import com.usermanager.service.data.User;
import com.usermanager.service.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserManagerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTestSuite {
    @Autowired
    private AdapterUserEntityRepository adapterUserEntityRepository;
    @Autowired
    private UserMapper userMapper;


    @Test
    @DisplayName("when we pass User without username, should be set name to 'enter login'")
    void testingValidateDataWithoutUsername() {
        //given
        User userWithoutUsername = new User();
        //system under test
        var toTest = new UserService(null, null);
        //when
        String shouldBeEnterLogin = toTest.validateData(userWithoutUsername).getUsername();
        //then
        Assertions.assertEquals("Enter login", shouldBeEnterLogin);
    }

    @Test
    @DisplayName("when we pass user with username but user exist, should be set username to 'User already exist!'")
    void testingValidateDataWithUsernameAndUserExist() {
        //given
        User userWithName = new User();
        userWithName.setUsername("username");
        //and
        var mockRepo = mock(AdapterUserEntityRepository.class);
        when(mockRepo.existsByUsername(anyString())).thenReturn(true);
        //and
        //system under test
        var toTest = new UserService(mockRepo, null);
        //when
        String shouldBeUserAlreadyExist = toTest.validateData(userWithName).getUsername();
        //then
        Assertions.assertEquals("User already exist!", shouldBeUserAlreadyExist);
    }

    @Test
    @DisplayName("should save user in db, and we check it using another method in service, getUserById")
    void testingRegisterUserWithAllCorrectData() {
        //given
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        //and
        //system under test
        var toTest = new UserService(adapterUserEntityRepository, userMapper);
        //when
        toTest.validateData(user);
        boolean shouldBeTrue = adapterUserEntityRepository.existsByUsername(user.getUsername());
        //then
        Assertions.assertTrue(shouldBeTrue);
    }
}

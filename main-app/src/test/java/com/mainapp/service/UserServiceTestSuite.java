package com.mainapp.service;

import com.mainapp.MainAppApplication;
import com.mainapp.service.data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.ModelMap;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MainAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTestSuite {

    @Test
    @DisplayName("should return false when we pass not matching passwords")
    void validateBeforeRegisterNotMatchingPasswords() {
        //given
        User user = new User();
        user.setPassword("baolaola");
        user.setConfirmPassword("baolaolanot");
        //system under test
        var toTest = new UserService(null, null, null, null);
        //when
        boolean shouldBeFalse = toTest.validateBeforeRegister(user, new ModelMap());
        //then
        Assertions.assertFalse(shouldBeFalse);
    }

    @Test
    @DisplayName("should return false when we pass empty password")
    void validateBeforeRegisterEmptyPasswords() {
        //given
        User user = new User();
        user.setPassword("");
        user.setConfirmPassword("");
        //system under test
        var toTest = new UserService(null, null, null, null);
        //when
        boolean shouldBeFalse = toTest.validateBeforeRegister(user, new ModelMap());
        //then
        Assertions.assertFalse(shouldBeFalse);
    }

    @Test
    @DisplayName("should return false when we pass empty username")
    void validateBeforeRegisterEmptyUsername() {
        //given
        User user = new User();
        user.setPassword("password");
        user.setConfirmPassword("password");
        //system under test
        var toTest = new UserService(null, null, null, null);
        //when
        boolean shouldBeFalse = toTest.validateBeforeRegister(user, new ModelMap());
        //then
        Assertions.assertFalse(shouldBeFalse);
    }
}

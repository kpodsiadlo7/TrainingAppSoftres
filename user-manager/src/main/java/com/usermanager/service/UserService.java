package com.usermanager.service;

import com.usermanager.domain.UserEntity;
import com.usermanager.repository.adapter.AdapterUserEntityRepository;
import com.usermanager.service.data.User;
import com.usermanager.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AdapterUserEntityRepository adapterUserEntityRepository;
    private final UserMapper userMapper;
    public User validateData(final User user){
        User errorUser = new User();
        if (user.getUsername() == null){
            log.warn("error, enter login");
            errorUser.setUsername("Enter login");
            return errorUser;
        }
        if (checkIfUserExistInDbWithThisUsername(user.getUsername())) {
            log.warn("user exist");
            errorUser.setUsername("User already exist!");
            return errorUser;
        }
        return registerUser(user);
    }

    public boolean checkIfUserExistInDbWithThisUsername(final String username) {
        log.warn("check if user exist in db with this username");
        return adapterUserEntityRepository.existsByUsername(username);
    }

    public User loginUser(final String username) {
        log.warn("login user");
        UserEntity userFromDbByUsername = adapterUserEntityRepository.findByUsername(username);
        return userMapper.mapToUserFromUserEntity(userFromDbByUsername);
    }

    private User registerUser(final User user) {
        log.info("register method start");
        UserEntity userEntity = userMapper.mapToUserEntityFromUser(user);
        adapterUserEntityRepository.save(userEntity);
        return userMapper.mapToUserFromUserEntity(userEntity);
    }

    public User getUserById(final Long userId) {
        log.warn("get user by id");
        return userMapper.mapToUserFromUserEntity(adapterUserEntityRepository.findById(userId));
    }
}

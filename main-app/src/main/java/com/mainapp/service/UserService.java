package com.mainapp.service;

import com.mainapp.repository.adapter.AdapterAuthorityRepository;
import com.mainapp.security.AuthorityEntity;
import com.mainapp.service.data.User;
import com.mainapp.service.mapper.UserMapper;
import com.mainapp.web.feign.FeignServiceUserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AdapterAuthorityRepository adapterAuthorityRepository;
    private final FeignServiceUserManager feignServiceUserManager;

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public String registerUser(User user, final ModelMap modelMap) {
        log.info("register user");
        if (!validateBeforeRegister(user, modelMap))
            return "register";
        return createUser(user, modelMap);
    }

    public User createUserAndReturnHimBack(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.mapToUserFromUserDto
                (feignServiceUserManager.createUser(userMapper.mapToUserDtoFromUser(user)));
    }

    public boolean validateBeforeRegister(User user, ModelMap modelMap) {
        log.info("validate before register");
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            modelMap.put("error", "Password doesn't match!");
            log.warn("password doesnt match");
            return false;
        }
        if (Strings.isEmpty(user.getPassword()) || (Strings.isEmpty(user.getConfirmPassword()))) {
            modelMap.put("error", "You must choose a password");
            log.warn("you must choose a password");
            return false;
        }
        if (Strings.isEmpty(user.getUsername())) {
            modelMap.put("error", "Enter your username");
            log.warn("enter username");
            return false;
        }
        return true;
    }

    private String createUser(User user, ModelMap modelMap) {
        log.info("create user");
        try {
            user = createUserAndReturnHimBack(user);
            log.info("successful connecting to user manager");
        } catch (Exception e) {
            modelMap.put("error", "Problem with connecting to user manager");
            log.warn("failed with connecting to user manager");
            return "register";
        }
        if (user.getId() == null) {
            modelMap.put("error", user.getUsername());
            log.warn("failed with reason from user manager " + user.getUsername());
            return "register";
        }
        return setAuthorityForUser(user);
    }

    public String setAuthorityForUser(User user) {
        log.info("set authority for user");
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority("ROLE_USER");
        authorityEntity.setUserId(user.getId());
        adapterAuthorityRepository.save(authorityEntity);
        user.getAuthorities().add(authorityEntity);
        return setSecurityContext(user);
    }

    private String setSecurityContext(final User user) {
        log.info("set security context");
        Authentication authentication = new UsernamePasswordAuthenticationToken
                (user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/main";
    }

}

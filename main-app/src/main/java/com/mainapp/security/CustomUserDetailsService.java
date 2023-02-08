package com.mainapp.security;

import com.mainapp.repository.adapter.AdapterAuthorityRepository;
import com.mainapp.service.data.User;
import com.mainapp.service.mapper.UserMapper;
import com.mainapp.web.feign.FeignServiceUserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final FeignServiceUserManager feignServiceUserManager;
    private final UserMapper userMapper;
    private final AdapterAuthorityRepository adapterAuthorityRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userMapper.mapToUserFromUserDto(feignServiceUserManager.loginUser(username));

        AuthorityEntity authority = adapterAuthorityRepository.findByUserId(user.getId());
        user.getAuthorities().add(authority);
        return new SecurityUser(user);
    }
}

package com.mainapp.service.mapper;

import com.mainapp.service.data.User;
import com.mainapp.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {


    public User mapToUserFromUserDto(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getConfirmPassword(),
                userDto.getAuthorities()
        );
    }

    public UserDto mapToUserDtoFromUser(final User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getConfirmPassword(),
                user.getAuthorities()
        );
    }
}

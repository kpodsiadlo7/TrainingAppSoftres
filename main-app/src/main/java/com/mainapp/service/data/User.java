package com.mainapp.service.data;

import com.mainapp.security.AuthorityEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    //confirmPassword only for validation with register process
    private String confirmPassword;
    private Set<AuthorityEntity> authorities = new HashSet<>();
}

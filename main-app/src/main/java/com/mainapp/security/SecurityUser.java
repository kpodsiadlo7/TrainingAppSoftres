package com.mainapp.security;

import com.mainapp.service.data.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@NoArgsConstructor
public class SecurityUser extends User implements UserDetails {


    public SecurityUser(final User userEntity) {
        this.setAuthorities(userEntity.getAuthorities());
        this.setId(userEntity.getId());
        this.setUsername(userEntity.getUsername());
        this.setPassword(userEntity.getPassword());
    }

    @Override
    public Set<AuthorityEntity> getAuthorities() {
        return super.getAuthorities();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.usermanager.repository.adapter;

import com.usermanager.domain.UserEntity;
import org.springframework.stereotype.Component;

public interface AdapterUserEntityRepository {
    UserEntity save(UserEntity userEntity);

    UserEntity findByUsername(String username);

    boolean existsByUsername(String username);
    UserEntity findById(long userId);
}

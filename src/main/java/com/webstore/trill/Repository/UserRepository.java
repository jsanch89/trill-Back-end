package com.webstore.trill.Repository;

import com.webstore.trill.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByName(String name);
}

package com.webstore.trill.Repository;

import com.webstore.trill.Entity.SellEntity;
import com.webstore.trill.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SellRepository extends JpaRepository<SellEntity, Long> {
    List<SellEntity> findAllByUser(final UserEntity user);
}

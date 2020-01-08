package com.webstore.trill.Repository;

import com.webstore.trill.Entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
    Optional<ItemEntity> findByNameIgnoreCase(String name);
    List<ItemEntity> findByPriceLessThanEqual(double price);
}

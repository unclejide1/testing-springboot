package com.jide.testing.repository;

import com.jide.testing.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}

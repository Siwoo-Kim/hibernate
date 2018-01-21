package com.learning.jpa.repository;

import com.learning.jpa.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long>{
}

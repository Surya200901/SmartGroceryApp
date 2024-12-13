package com.smartgrocery.repository;

import com.smartgrocery.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {
}

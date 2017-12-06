package com.example.data.combine.mongo.repository;

import com.example.data.combine.mongo.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Created by khan on 11/20/17.
 */

public interface InventoryRepository
    extends MongoRepository<Inventory, String> {
  Inventory findByProductName(String productName);
}

package com.example.data.combine.mongo.repository;

import com.example.data.combine.mongo.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by khan on 10/20/17.
 */

public interface MongoUserRepository extends MongoRepository<MongoUser,String>{
  MongoUser findByName(String name);
}

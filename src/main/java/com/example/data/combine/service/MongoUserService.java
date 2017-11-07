package com.example.data.combine.service;

import com.example.data.combine.mastermodel.Response;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.postgres.model.PostgresUser;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Created by khan on 10/20/17.
 */
public interface MongoUserService {
  MongoUser findUserByName(String name);
  boolean saveMongoUser(MongoUser mongoUser);
  Boolean convertAndSaveUser(PostgresUser postgresUser);
}

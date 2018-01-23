package com.example.data.combine.service;

import com.example.data.combine.mastermodel.PostgresRequest;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.postgres.model.PostgresUser;
import com.example.data.combine.restwebmodel.MandatoryParameter;

import java.util.List;

/**
 * Created by khan on 10/20/17.
 */
public interface MongoUserService {
  MongoUser findUserByName(String name);

  boolean saveMongoUser(MongoUser mongoUser);

  Boolean convertAndSaveUser(PostgresUser postgresUser);

  Boolean sendUser(PostgresRequest request, MandatoryParameter parameter);

  void processUser(MongoUser mongoUser);

  List<MongoUser> findByStoreIdAndCurrentDate(String storeId);
}

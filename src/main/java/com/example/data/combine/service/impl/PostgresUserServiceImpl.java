package com.example.data.combine.service.impl;

import com.example.data.combine.mastermodel.Response;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.postgres.model.PostgresUser;
import com.example.data.combine.postgres.repository.PostgresUserRepository;
import com.example.data.combine.service.PostgresUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by khan on 10/20/17.
 */
@Service
public class PostgresUserServiceImpl implements PostgresUserService {

  private static final Logger LOG = LoggerFactory.getLogger(PostgresUserServiceImpl.class);

  @Autowired
  private PostgresUserRepository postgresUserRepository;

  @Override
  public PostgresUser findUserByName(String name) {
    PostgresUser postgresUser = null;
    try {
      postgresUser = this.postgresUserRepository.findByName(name);
    } catch (Exception e) {
      LOG.error("Fialed to find user name : {}, error", name, e);
    }
    return postgresUser;
  }

  @Override
  public void savePostgresUser(PostgresUser postgresUser) {
    try {
      LOG.info("saving postgresUser : {}", postgresUser);
      this.postgresUserRepository.save(postgresUser);
      LOG.info("SucessFully saved postgresUser : {}", postgresUser);
    } catch (Exception e) {
      LOG.error("Fialed to save postgresUser : {}", postgresUser, e);
    }
  }

  @Override
  public String converter(PostgresUser postgresUser) throws JsonProcessingException {
    Response response = new Response();
    response.setAddress(postgresUser.getAddress());
    response.setAge(postgresUser.getAge());
    response.setId(postgresUser.getId());
    response.setName(postgresUser.getName());
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(response);
  }

  @Override
  public Boolean convertAndSaveUser(MongoUser mongoUser) {
    LOG.info("convertAndSaveUser with data: {}", mongoUser);
    PostgresUser postgresUser = new PostgresUser();
    try {
      postgresUser.setId(mongoUser.getId());
      postgresUser.setAddress(mongoUser.getAddress());
      postgresUser.setAge(mongoUser.getAge());
      postgresUser.setName(mongoUser.getName());
      LOG.info("start saving data into MongoCollection mongoUser : {}", mongoUser);
      this.postgresUserRepository.save(postgresUser);
      LOG.info("successfully  data save into  postgresUser postgresUser : {}", postgresUser);
      return true;
    } catch (Exception ex) {
      LOG.error("data not save in postgresUser: {}, with error : {}", postgresUser,ex);
    }
    return false;
  }
}

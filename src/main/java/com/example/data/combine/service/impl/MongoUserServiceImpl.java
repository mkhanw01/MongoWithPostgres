package com.example.data.combine.service.impl;

import com.example.data.combine.mastermodel.Response;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.mongo.repository.MongoUserRepository;
import com.example.data.combine.postgres.model.PostgresUser;
import com.example.data.combine.service.MongoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by khan on 10/20/17.
 */
@Service
public class MongoUserServiceImpl implements MongoUserService {

  private static final Logger LOG = LoggerFactory.getLogger(MongoUserServiceImpl.class);

  @Autowired
  private MongoUserRepository mongoUserRepository;

  @Override
  public MongoUser findUserByName(String name) {
    MongoUser mongoUser = null;
    try {
      LOG.info("Fiend username with name : {}", name);
      mongoUser = this.mongoUserRepository.findByName(name);
    } catch (Exception e) {
      LOG.error("failed to find username with name : {}, error : {}", name, e);
    }
    return mongoUser;
  }

  @Override
  public void saveMongoUser(MongoUser mongoUser) {
    LOG.info("call mongo save service mongoUser : {}", mongoUser.toString());
    try {
      this.mongoUserRepository.save(mongoUser);
      LOG.info("save SuccessFull mongoUser : {}", mongoUser);
    } catch (Exception e) {
      LOG.error("failed to save mongoUser : {}", mongoUser, e);
    }
  }

  @Override
  public String converter(MongoUser mongoUser) throws JsonProcessingException {
    Response response = new Response();
    response.setName(mongoUser.getName());
    response.setId(mongoUser.getId());
    response.setAge(mongoUser.getAge());
    response.setAddress(mongoUser.getAddress());
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = ow.writeValueAsString(response);
    LOG.info("return  value  json : {}",json);
    return json;
  }

  @Override
  public Boolean convertAndSaveUser(PostgresUser postgresUser) {
    LOG.info("convertAndSaveUser with data: {}",postgresUser);
    Response response = new Response();
    MongoUser mongoUser = new MongoUser();
    try {
      BeanUtils.copyProperties(postgresUser,response);
      mongoUser.setId(response.getId());
      mongoUser.setAddress(response.getAddress());
      mongoUser.setAge(response.getAge());
      mongoUser.setName(response.getName());
    }catch (Exception e){
      LOG.error("data is not converted in BeanUtils data : {}",postgresUser);
    }
    try {
      LOG.info("start saving data into MOngoCollection mongoUser : {}",mongoUser);
      this.mongoUserRepository.save(mongoUser);
      LOG.info("successfully  data save into  MOngoCollection mongoUser : {}",mongoUser);
      return true;
    }catch (Exception ex){
      LOG.error("data not save in mongoCollection mongoUser : {}",mongoUser);
    }
    return false;
  }
}

package com.example.data.combine.controller;

import com.example.data.combine.mastermodel.Request;
import com.example.data.combine.mastermodel.Response;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.postgres.model.PostgresUser;
import com.example.data.combine.service.MongoUserService;
import com.example.data.combine.service.PostgresUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khan on 10/20/17.
 */
@RestController
public class MongoController {
  private static final Logger LOG = LoggerFactory.getLogger(MongoController.class);

  public static final String MONGO_USER = "/mongoUser";
  public static final String FIND_USER = "/findUser";
  public static final String FIND_AND_SAVE_IN_POSTGRES ="/findAndSaveInPostgres";
  @Autowired
  private MongoUserService mongoUserService;
  @Autowired
  PostgresUserService postgresUserService;

  @RequestMapping(value = MONGO_USER,
      method = {RequestMethod.POST}, consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public void saveMongoUser(@RequestBody Request  request)throws Exception{
    LOG.info("saveMongoController with request : {}",request.toString());
    try {
      MongoUser  mongoUser = new MongoUser();
      BeanUtils.copyProperties(request,mongoUser);
      this.mongoUserService.saveMongoUser(mongoUser);
    }catch (Exception e){
      LOG.error("failed in saveMongoController with request : {}, error : {}",request,e);
    }
  }

  @RequestMapping(value = FIND_USER,
      method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public String findUserName(@RequestParam String name) throws Exception{
    LOG.info("findName with name : {}", name);
    String response = null;
    try {
       MongoUser mongoUser=this.mongoUserService.findUserByName(name);
      response = this.mongoUserService.converter(mongoUser);
    }catch (Exception e){
      LOG.error("Faild to findUserName request : {}, error: {}",name,e);
    }
    return response;
  }

  @RequestMapping(value = FIND_AND_SAVE_IN_POSTGRES, method = {RequestMethod.POST},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean findAndSaveUserInPostgres(@RequestParam String name) throws Exception {
    LOG.info("findUserName with name : {}", name);
    Boolean responce = false;
    try {
      MongoUser mongoUser = this.mongoUserService.findUserByName(name);
      if (mongoUser != null) {
        responce = this.postgresUserService.convertAndSaveUser(mongoUser);
      }
    } catch (Exception e) {
      LOG.error("Failed in findUserName request : {}, error: {}", name, e);
    }
    return responce;
  }

}

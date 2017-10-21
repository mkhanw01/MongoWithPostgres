package com.example.data.combine.controller;

import com.example.data.combine.mastermodel.Request;
import com.example.data.combine.postgres.model.PostgresUser;
import com.example.data.combine.service.MongoUserService;
import com.example.data.combine.service.PostgresUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by khan on 10/20/17.
 */
@RestController
public class PostgresController {
  private static final Logger LOG = LoggerFactory.getLogger(PostgresController.class);
  public static final String POSTGRES_USER = "/postgresUser";
  public static final String FIND = "/find";
  public static final String FIND_AND_SAVE_FROM_POSTGRES = "findAndSaveFromPostgres";
  @Autowired
  private PostgresUserService postgresUserService;
  @Autowired
  private MongoUserService mongoUserService;

  @RequestMapping(value = POSTGRES_USER,
      method = {RequestMethod.POST},
      consumes = {MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public void savePostgresUser(@RequestBody Request request) throws Exception {
    LOG.info("postgresController with request : {}", request);
    try {
      PostgresUser postgresUser = new PostgresUser();
      BeanUtils.copyProperties(request, postgresUser);
      this.postgresUserService.savePostgresUser(postgresUser);
    } catch (Exception e) {
      LOG.error("Faild in postgresCOntroller request : {}, error: {}", request, e);
    }
  }


  @RequestMapping(value = FIND,
      method = {RequestMethod.GET},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public String findUserName(@RequestParam String name) throws Exception {
    LOG.info("findUserName with name : {}", name);
    String responce = null;
    try {
      PostgresUser postgresUser = this.postgresUserService.findUserByName(name);
      responce = this.postgresUserService.converter(postgresUser);
    } catch (Exception e) {
      LOG.error("Failed in findUserName request : {}, error: {}", name, e);
    }
    return responce;
  }

  @RequestMapping(value = FIND_AND_SAVE_FROM_POSTGRES, method = {RequestMethod.POST},
      produces = {MediaType.APPLICATION_JSON_VALUE})
  public boolean findAndSaveUserInMongo(@RequestParam String name) throws Exception {
    LOG.info("findUserName with name : {}", name);
    Boolean responce = false;
    try {
      PostgresUser postgresUser = this.postgresUserService.findUserByName(name);
      if (postgresUser != null) {
        responce = this.mongoUserService.convertAndSaveUser(postgresUser);
      }
    } catch (Exception e) {
      LOG.error("Failed in findUserName request : {}, error: {}", name, e);
    }
    return responce;
  }
}

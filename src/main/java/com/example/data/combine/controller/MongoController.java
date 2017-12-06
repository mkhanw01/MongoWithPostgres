package com.example.data.combine.controller;

import com.example.data.combine.mastermodel.BeanMapper;
import com.example.data.combine.mastermodel.PostgresRequest;
import com.example.data.combine.mastermodel.Response;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.restwebmodel.ApiPath;
import com.example.data.combine.restwebmodel.BaseResponse;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import com.example.data.combine.service.MongoUserService;
import com.example.data.combine.service.PostgresUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

/**
 * Created by khan on 10/20/17.
 */
@RestController
@RequestMapping(value = ApiPath.MONGO)
@Api(value = "MongoController ",
    description = "Mongo Service Api")
public class MongoController extends BaseController {
  private static final Logger LOG = LoggerFactory.getLogger(MongoController.class);

  @Autowired
  private MongoUserService mongoUserService;
  @Autowired
  PostgresUserService postgresUserService;

  @PostMapping(value = ApiPath.MONGO_USER)
  @ApiOperation(value = "crate new user")
  public BaseResponse<Boolean> saveMongoUser(
      @ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter,
      @RequestBody PostgresRequest postgresRequest) throws Exception {
    LOG.info("saveMongoController with parameter : {}", parameter);
    MongoUser mongoUser = BeanMapper.map(postgresRequest, MongoUser.class);
    boolean success = this.mongoUserService.saveMongoUser(mongoUser);
    return toCombineResponse(success);
  }

  @GetMapping(value = ApiPath.FIND_USER_BY_NAME)
  @ApiOperation(value = "find user by name")
  public BaseResponse<Response> findUserName(
      @ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter, @RequestParam String name)
      throws Exception {
    LOG.info("findName with parameter : {}", parameter);
    MongoUser mongoUser = this.mongoUserService.findUserByName(name);
    Response response = BeanMapper.map(mongoUser, Response.class);
    return toCombineResponse(response);
  }

  @PostMapping(value = ApiPath.MIGRATE_MONGO)
  @ApiOperation(value = "fetch from mongo and save in postgres")
  public BaseResponse<Boolean> findAndSaveUserInPostgres(@RequestParam String name)
      throws Exception {
    LOG.info("findUserName with name : {}", name);
    Boolean responce = false;
    MongoUser mongoUser = this.mongoUserService.findUserByName(name);
    if (mongoUser != null) {
      responce = this.postgresUserService.convertAndSaveUser(mongoUser);
    }
    return toCombineResponse(responce);
  }

  @PostMapping(value = ApiPath.MONGO_SEND_USER)
  @ApiOperation(value = "send a user to save")
  public BaseResponse<Boolean> mongoSendUser(
      @ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter,
      @RequestBody PostgresRequest postgresRequest) throws Exception {
    LOG.info("send user with parameter : {}", parameter);
    return toCombineResponse(this.mongoUserService.sendUser(postgresRequest,parameter));
  }
}

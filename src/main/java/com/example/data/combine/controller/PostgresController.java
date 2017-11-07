package com.example.data.combine.controller;

import com.example.data.combine.mastermodel.BeanMapper;
import com.example.data.combine.mastermodel.PostgresRequest;
import com.example.data.combine.mastermodel.Response;
import com.example.data.combine.postgres.model.PostgresUser;
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
@RequestMapping(ApiPath.POSTGRES)
@Api(value = "PostgresController",
    description = "postgres service API")
public class PostgresController extends BaseController {
  private static final Logger LOG = LoggerFactory.getLogger(PostgresController.class);

  @Autowired
  private PostgresUserService postgresUserService;
  @Autowired
  private MongoUserService mongoUserService;


  @PostMapping(value = ApiPath.CREATE)
  @ApiOperation(value = "crate new user")
  public BaseResponse<Boolean> savePostgresUser(
      @ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter,
      @RequestBody PostgresRequest postgresRequest) throws Exception {
    LOG.info("postgresController with parameter : {}", parameter);
    PostgresUser postgresUser = BeanMapper.map(postgresRequest, PostgresUser.class);
    boolean success = this.postgresUserService.savePostgresUser(postgresUser);
    return toCombineResponse(success);
  }

  @GetMapping(value = ApiPath.FIND)
  @ApiOperation(value = "find user by name")
  public BaseResponse<Response> findUserName(
      @ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter ,@RequestParam String name) throws
      Exception {
    LOG.info("findUserName with parameter : {}", parameter);
    PostgresUser postgresUser = this.postgresUserService.findUserByName(name);
    Response response = BeanMapper.map(postgresUser, Response.class);
    return toCombineResponse(response);
  }

  @PostMapping(value = ApiPath.MIGRATE_POSTGRES)
  @ApiOperation(value = "fetch from postgres and save in mongo")
  public BaseResponse<Boolean> findAndSaveUserInMongo(
      @ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter, @RequestParam String name)
      throws Exception {
    LOG.info("findUserName with parameter : {}", parameter);
     Boolean response = false;
     PostgresUser  postgresUser = this.postgresUserService.findUserByName(name);
    if(postgresUser!=null){
      response = this.mongoUserService.convertAndSaveUser(postgresUser);
    }
    return toCombineResponse(response);
  }
}

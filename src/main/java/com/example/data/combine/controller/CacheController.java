package com.example.data.combine.controller;

import com.example.data.combine.restwebmodel.ApiPath;
import com.example.data.combine.restwebmodel.BaseResponse;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import com.example.data.combine.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import org.slf4j.Logger;

/**
 * Created by khan on 11/10/17.
 */
@RestController
@RequestMapping(value = ApiPath.CACHE)
public class CacheController extends BaseController{

  @Autowired
  private CacheService cacheService;

  public static final Logger LOGGER = LoggerFactory.getLogger(CacheController.class);
  @DeleteMapping(value = ApiPath.FLUSH_DB)
  @ApiOperation("Flush all redis key")
  public BaseResponse<Boolean> flushDb(@ApiIgnore @Valid @ModelAttribute MandatoryParameter parameter){
    LOGGER.info("CacheController flushdb with parameter : {}",parameter);
    this.cacheService.flushDB();
    return toCombineResponse(true);
  }
}

package com.example.data.combine.controller;

import com.example.data.combine.mastermodel.BeanMapper;
import com.example.data.combine.mastermodel.InventoryRequest;
import com.example.data.combine.mastermodel.InventoryResponse;
import com.example.data.combine.mongo.model.Inventory;
import com.example.data.combine.restwebmodel.ApiPath;
import com.example.data.combine.restwebmodel.BaseResponse;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import com.example.data.combine.service.InventoryService;
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
import java.util.List;

/**
 * Created by khan on 11/18/17.
 */
@RestController
@RequestMapping(value = ApiPath.INVENTORY)
@Api(value = "Inventory Controller",
    description = "Inventory Service Api")
public class InventoryController extends BaseController {
  private static final Logger LOGGER = LoggerFactory.getLogger(InventoryController.class);

  @Autowired
  private InventoryService inventoryService;

  @PostMapping(value = ApiPath.ADD_INVENTORY)
  @ApiOperation(value = "create new inventory")
  public BaseResponse<Boolean> addInventory(
      @Valid @ApiIgnore @ModelAttribute MandatoryParameter parameter,
      @RequestBody InventoryRequest request) {
    LOGGER.info("#addInventory with parameter: {}, and request : {}", parameter, request);
    return toCombineResponse(this.inventoryService.createInventory(parameter, request));
  }

  @GetMapping(value = ApiPath.FIND_ALL)
  @ApiOperation(value = "get all inventory")
  public BaseResponse<List<InventoryResponse>> findAll(
      @Valid @ApiIgnore @ModelAttribute MandatoryParameter parameter) {
    LOGGER.info("#getAllInventory with parameter : {}", parameter);
    return toCombineResponse(
        BeanMapper.mapAsList(this.inventoryService.findAllInventory(), InventoryResponse.class));
  }

  @GetMapping(value = ApiPath.FIND_BY_PRODUCT_NAME)
  @ApiOperation(value = "find inventory by productName")
  public BaseResponse<InventoryResponse> findByProductName(@Valid @ApiIgnore @ModelAttribute
      MandatoryParameter parameter, @RequestParam (required = true)String productname){
    LOGGER.info("#getInventory with productName : {}",productname);
    Inventory inventory = this.inventoryService.findByProductName(productname);
    InventoryResponse  inventoryResponse = null;
    if(inventory!=null){
      inventoryResponse = BeanMapper.map(inventory,InventoryResponse.class);
    }
    return toCombineResponse(inventoryResponse);
  }
}

package com.example.data.combine.service;

import com.example.data.combine.mastermodel.InventoryRequest;
import com.example.data.combine.mongo.model.Inventory;
import com.example.data.combine.restwebmodel.MandatoryParameter;

import java.util.List;

/**
 * Created by khan on 11/21/17.
 */
public interface InventoryService {
  Boolean createInventory(MandatoryParameter parameter, InventoryRequest request);

  List<Inventory> findAllInventory();

  Inventory findByProductName(String productName);
}

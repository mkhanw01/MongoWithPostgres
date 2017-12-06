package com.example.data.combine.service.impl;

import com.example.data.combine.mastermodel.BeanMapper;
import com.example.data.combine.mastermodel.InventoryRequest;
import com.example.data.combine.mongo.model.Inventory;
import com.example.data.combine.mongo.repository.InventoryRepository;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import com.example.data.combine.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khan on 11/21/17.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

  @Autowired
  private InventoryRepository inventoryRepository;

  @Override
  public Boolean createInventory(MandatoryParameter parameter, InventoryRequest request) {
    Inventory inventory = BeanMapper.map(request,Inventory.class);
    inventoryRepository.save(inventory);
    return true;
  }

  @Override
  public List<Inventory> findAllInventory() {
    return inventoryRepository.findAll();
  }

  @Override
  public Inventory findByProductName(String productName) {
    return this.inventoryRepository.findByProductName(productName);
  }
}

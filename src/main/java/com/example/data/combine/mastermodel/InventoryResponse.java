package com.example.data.combine.mastermodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by khan on 11/21/17.
 */
@GeneratePojoBuilder
@JsonIgnoreProperties
public class InventoryResponse implements Serializable {

  private static final long serialVersionUID = -1905716114871857890L;

  private String id;
  private String productName;
  private Double price;
  private int stock;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("productName", productName)
        .append("price", price).append("stock", stock).toString();
  }
}

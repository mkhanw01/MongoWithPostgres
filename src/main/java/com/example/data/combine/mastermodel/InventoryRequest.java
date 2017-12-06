package com.example.data.combine.mastermodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by khan on 11/20/17.
 */
@GeneratePojoBuilder
@JsonIgnoreProperties
public class InventoryRequest implements Serializable{

  private static final long serialVersionUID = 6509230232063278004L;

  private int stock;
  private Double price;
  private String productName;

  public InventoryRequest() {
    // do nothing
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("stock", stock).append("price", price)
        .append("productName", productName).toString();
  }
}

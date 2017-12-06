package com.example.data.combine.mongo.model;

import com.example.data.combine.mastermodel.FieldsName;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;

/**
 * Created by khan on 11/18/17.
 */
@GeneratePojoBuilder
@Document(collection = FieldsName.INVENTORY_COLLECTION)
public class Inventory {

  @Id
  @Field(value = FieldsName.ID)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;
  @Field(value = FieldsName.PRIDUCT_NAME)
  private String productName;
  @Field(value = FieldsName.PRICE)
  private Double price;
  @Field(value = FieldsName.STOCK)
  private int stock;

  public Inventory() {
    // do nothing
  }

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

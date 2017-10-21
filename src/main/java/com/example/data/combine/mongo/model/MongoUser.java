package com.example.data.combine.mongo.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * Created by khan on 10/20/17.
 */
@Document(collection = MongoUser.USER)
public class MongoUser implements Serializable{

  private static final long serialVersionUID = 8945071908947549380L;
  protected static final String USER = "User";
  private static final String NAME = "name";
  private static final String ADDRESS = "address";
  private static final String AGE = "age";
  private static final String ID = "id";

  @Id
  @Field(value = ID)
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  private String id;
  @Field(value = NAME)
  private String name;
  @Field(value = ADDRESS)
  private String address;
  @Field(value = AGE)
  private String age;

  public MongoUser() {
    // do nothing
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("MongoUser{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", age='").append(age).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

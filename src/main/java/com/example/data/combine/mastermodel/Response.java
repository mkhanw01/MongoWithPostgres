package com.example.data.combine.mastermodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by khan on 10/20/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable{

  private static final long serialVersionUID = 3656817382453002947L;
  private String id;
  private String name;
  private String address;
  private String age;

  public Response() {
    // do nothing
  }

  public Response(String id, String name, String address, String age) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.age = age;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getAge() {
    return age;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Response{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", age='").append(age).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

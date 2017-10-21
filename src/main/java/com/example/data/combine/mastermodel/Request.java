package com.example.data.combine.mastermodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by khan on 10/20/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Request implements Serializable{

  private static final long serialVersionUID = 342152180148459236L;

  private String name;
  private String address;
  private String age;

  public Request() {
    // do nothing
  }

  public Request(String name, String address, String age) {

    this.name = name;
    this.address = address;
    this.age = age;
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
    final StringBuilder sb = new StringBuilder("Request{");
    sb.append(", name='").append(name).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", age='").append(age).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

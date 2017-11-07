package com.example.data.combine.mastermodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by khan on 10/20/17.
 */
@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostgresRequest implements Serializable{

  private static final long serialVersionUID = 342152180148459236L;

  @NotNull
  private String name;
  @NotNull
  private String address;
  @NotNull
  private String age;

  public PostgresRequest() {
    // do nothing
  }

  public PostgresRequest(String name, String address, String age) {

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
    final StringBuilder sb = new StringBuilder("PostgresRequest{");
    sb.append(", name='").append(name).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", age='").append(age).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

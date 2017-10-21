package com.example.data.combine.postgres.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by khan on 10/20/17.
 */
@Entity
@Table(name = PostgresUser.POSTGRES_USER)
public class PostgresUser implements Serializable{

  private static final long serialVersionUID = 744436252052211880L;


  public static final String POSTGRES_USER = "PostgresUser";
  public static final String ID = "id";
  public static final String NAME = "name";
  public static final String ADDRESS = "address";
  public static final String AGE = "age";

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid2")
  @Column(name = ID)
  private String id;
  @Column(name = NAME)
  private String name;
  @Column(name = ADDRESS)
  private String address;
  @Column(name = AGE)
  private String age;

  public PostgresUser() {
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
    final StringBuilder sb = new StringBuilder("PostgresUser{");
    sb.append("id='").append(id).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", age='").append(age).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

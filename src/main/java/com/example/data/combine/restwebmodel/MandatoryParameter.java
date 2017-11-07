package com.example.data.combine.restwebmodel;

import com.example.data.combine.mastermodel.ObjectHelper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by khan on 10/31/17.
 */

@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MandatoryParameter implements Serializable {

  private static final long serialVersionUID = 9041895550429392942L;

  @NotEmpty
  private String storeId;
  @NotEmpty
  private String channelId;
  @NotEmpty
  private String clientId;
  @NotEmpty
  private String requestId;
  private String username;

  @Override
  public boolean equals(Object object) {
    return ObjectHelper.equals(this, object);
  }

  @Override
  public int hashCode() {
    return ObjectHelper.hashCode(this);
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("storeId", storeId).append("channelId", channelId)
        .append("clientId", clientId).append("requestId", requestId).append("username", username)
        .toString();
  }
}

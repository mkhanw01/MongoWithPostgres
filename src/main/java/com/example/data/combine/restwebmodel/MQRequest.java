package com.example.data.combine.restwebmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by khan on 12/6/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MQRequest<T> {
  private String requestId;
  private String clientId;
  private String channelId;
  private String storeId;
  private String username;
  private T object;

  public MQRequest() {
    // nothing to do
  }

  public MQRequest(String requestId, String clientId, String channelId, String storeId,
      String username, T object) {
    super();
    this.requestId = requestId;
    this.clientId = clientId;
    this.channelId = channelId;
    this.storeId = storeId;
    this.username = username;
    this.object = object;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public String getStoreId() {
    return storeId;
  }

  public void setStoreId(String storeId) {
    this.storeId = storeId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public T getObject() {
    return object;
  }

  public void setObject(T object) {
    this.object = object;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("requestId", requestId).append("clientId", clientId)
        .append("channelId", channelId).append("storeId", storeId).append("username", username)
        .append("object", object).toString();
  }
}

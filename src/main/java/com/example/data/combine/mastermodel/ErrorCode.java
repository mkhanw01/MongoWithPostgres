package com.example.data.combine.mastermodel;

/**
 * Created by khan on 10/31/17.
 */
public enum ErrorCode {

  STORE_ID_MUST_NOT_BE_BLANK_OR_NULL(
      "StoreId must not be blank or null"),
  REQUEST_ID_MUST_NOT_BE_BLANK_OR_NULL(
      "RequestId must not be blank or null"),
  CHANNEL_ID_MUST_NOT_BE_BLANK_OR_NULL(
      "ChannelId must not be blank or null"),
  CLIENT_ID_MUST_NOT_BE_BLANK_OR_NULL(
      "ClientId must not be blank or null");

  private String message;

  ErrorCode(String message) {
    this.message = message;
  }

  public String getCode() {
    return this.name();
  }

  public String getMessage() {
    return message;
  }
}

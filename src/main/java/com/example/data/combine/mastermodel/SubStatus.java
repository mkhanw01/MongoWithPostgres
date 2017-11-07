package com.example.data.combine.mastermodel;

/**
 * Created by khan on 10/31/17.
 */
public enum SubStatus {

  OK("200.000", "OK"),
  BAD_REQUEST("400.000", "Bad PostgresRequest"),
  MANDATORY_HEADER_REQUIRED("401.001", "Mandatory Parameter Required"),
  UNKNOWN_ERROR("500.001", "Unknown Error"),
  SYSTEM_ERROR("500.002", "System Error"),
  DATA_NOT_FOUND("500.003", "Data Not Found");

  private String subCode;
  private String reason;

  SubStatus(String subCode, String reason) {
    this.subCode = subCode;
    this.reason = reason;
  }

  public String getSubCode() {
    return subCode;
  }

  public String getReason() {
    return reason;
  }
}

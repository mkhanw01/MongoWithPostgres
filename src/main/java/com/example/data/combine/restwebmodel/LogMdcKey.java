package com.example.data.combine.restwebmodel;

/**
 * Created by khan on 12/6/17.
 */
public enum LogMdcKey {
  KEY("key");

  private String key;

  private LogMdcKey(String key) {
    this.key = key;
  }

  public String getKey() {
    return this.key;
  }
}

package com.example.data.combine.controller.config.rabbit.modelprocess;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by khan on 12/6/17.
 */
@Component
@ConfigurationProperties(prefix = "combine.rabbit.process-model")
public class ProcessModelRabbitProperties {
  private String queue;
  private String exchange;
  private String key;
  private Integer listenerConcurrencyMax;

  public String getQueue() {
    return queue;
  }

  public void setQueue(String queue) {
    this.queue = queue;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Integer getListenerConcurrencyMax() {
    return listenerConcurrencyMax;
  }

  public void setListenerConcurrencyMax(Integer listenerConcurrencyMax) {
    this.listenerConcurrencyMax = listenerConcurrencyMax;
  }

}

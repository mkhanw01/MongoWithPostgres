package com.example.data.combine.mastermodel;

/**
 * Created by khan on 12/6/17.
 */
public enum RabbitQueue {

  PROCESS_ORDER_QUEUE("com.example.data.combine", "com.example.data.topic", "topic", "data.combine");

  private String name;

  private String exchange;

  private String exchangeType;

  private String routingKey;

  RabbitQueue(String name, String exchange, String exchangeType, String routingKey) {
    this.name = name;
    this.exchange = exchange;
    this.exchangeType = exchangeType;
    this.routingKey = routingKey;
  }

  public String getName() {
    return name;
  }

  public String getExchange() {
    return exchange;
  }

  public String getExchangeType() {
    return exchangeType;
  }

  public String getRoutingKey() {
    return routingKey;
  }
}

package com.example.data.combine.service.listner.impl;

import com.example.data.combine.restwebmodel.LogMdcKey;
import com.example.data.combine.restwebmodel.MQRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by khan on 12/6/17.
 */
public abstract class BaseQueueListener implements MessageListener{

  private static final Logger LOG = LoggerFactory.getLogger(BaseQueueListener.class);
  private static final String MDC_KEY_FORMAT = "%s|%s|%s|%s|%s";

  @Autowired
  protected ObjectMapper objectMapper;

  private String createMdcValue(MQRequest request) {
    return String.format(MDC_KEY_FORMAT, UUID.randomUUID().toString(),
        Strings.nullToEmpty(request.getStoreId()), Strings.nullToEmpty(request.getChannelId()),
        Strings.nullToEmpty(request.getClientId()), Strings.nullToEmpty(request.getRequestId()));
  }


  public ObjectMapper getObjectMapper() {
    return this.objectMapper;
  }

  /**
   * Convert received message from queue to {@link String}
   *
   * @param message received from MQ
   * @throws Exception if there's a problem processing the message
   */
  protected void handleMessage(byte[] message) throws Exception {
    processMessage(new String(message));
  }

  protected void logError(final Logger logger, String queueName, String message,
      Exception exception) {
    logger.error("An error occurred when consuming message from {}. Message: {}",
        new Object[] {queueName, message, exception});
  }

  protected void logInfoConsumingQueue(final Logger logger, String queueName, String message) {
    logger.info("Consuming {} Queue. Message: {}", new Object[] {queueName, message});
  }

  @Override
  public void onMessage(Message message) {
    try {
      handleMessage(message.getBody());
    } catch (Exception e) {
      LOG.error("Cannot receive message from MQ!", e);
    }
  }

  public abstract void processMessage(String message) throws Exception;

  protected void setMDC(MQRequest request) {
    if (request != null) {
      MDC.put(LogMdcKey.KEY.getKey(), createMdcValue(request));
    }
  }

}

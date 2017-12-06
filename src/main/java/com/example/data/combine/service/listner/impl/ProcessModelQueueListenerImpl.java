package com.example.data.combine.service.listner.impl;

import com.example.data.combine.mastermodel.BeanMapper;
import com.example.data.combine.mastermodel.PostgresRequest;
import com.example.data.combine.mastermodel.RabbitQueue;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.service.MongoUserService;
import com.example.data.combine.service.listner.ProcessModelQueueListener;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by khan on 12/6/17.
 */
@Service
public class ProcessModelQueueListenerImpl extends BaseQueueListener
    implements ProcessModelQueueListener {
  private static final Logger LOG = LoggerFactory.getLogger(ProcessModelQueueListenerImpl.class);

  @Autowired
  private MongoUserService mongoUserService;

  @Override
  public void processMessage(String message) throws Exception {
    try {
      logInfoConsumingQueue(LOG, RabbitQueue.PROCESS_ORDER_QUEUE.getName(), message);
      PostgresRequest mqRequest =
          getObjectMapper().readValue(message, new TypeReference<PostgresRequest>() {
          });
      LOG.info("# mqRequest object : {}", mqRequest);
      mongoUserService.processUser(BeanMapper.map(mqRequest, MongoUser.class));
    } catch (Exception ex) {
      logError(LOG, RabbitQueue.PROCESS_ORDER_QUEUE.getName(), message, ex);
    }
  }
}

package com.example.data.combine.consumer;

import com.example.data.combine.eventmodel.AddUserEventModel;
import com.example.data.combine.eventmodel.DomainEventNames;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.mongo.repository.MongoUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by khan on 1/19/18.
 */
@Component
@KafkaListener(topics = DomainEventNames.COM_COMBINE_DOMAIN_ADD_USER, containerFactory =
    "containerFactory")
@Slf4j
public class AddUserConsumer {

  @Autowired
  private MongoUserRepository mongoUserRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaHandler
  public void addUserConsumer(AddUserEventModel addUserEventModel) {
    log.info("#AdduserConsumer consuming addUserEventModel : {} ", addUserEventModel);
    try {
      MongoUser mongoUser = new MongoUser();
      BeanUtils.copyProperties(addUserEventModel, mongoUser);
      this.mongoUserRepository.save(mongoUser);
      log.info("#SuccessFully saved  consumed object : {}", mongoUser);
    } catch (Exception e) {
      log.error("#AddUserConsumer exception during consume addUserEventModel : {}, with error : {}", addUserEventModel,
          e);
    }
  }
}

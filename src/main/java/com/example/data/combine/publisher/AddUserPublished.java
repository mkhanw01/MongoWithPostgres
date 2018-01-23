package com.example.data.combine.publisher;

import com.example.data.combine.eventmodel.AddUserEventModel;
import com.example.data.combine.eventmodel.DomainEventNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by khan on 1/19/18.
 */

@Slf4j
@Component
public class AddUserPublished  {

  @Autowired
  private KafkaTemplate<String, AddUserEventModel> kafkaTemplate;

  public void publish(AddUserEventModel addUserEventModel) {
    log.info("#sending addUserEventModel");
    kafkaTemplate.send(DomainEventNames.COM_COMBINE_DOMAIN_ADD_USER, addUserEventModel);
    try {
      TimeUnit.MILLISECONDS.sleep(2000);
    } catch (InterruptedException e) {
      log.error("exception at addUserEventModel while thread sleep", e);
    }
  }
}

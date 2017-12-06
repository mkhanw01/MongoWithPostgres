package com.example.data.combine.service.impl;

import com.example.data.combine.controller.config.rabbit.modelprocess.ProcessModelRabbitProperties;
import com.example.data.combine.mastermodel.CacheName;
import com.example.data.combine.mastermodel.PostgresRequest;
import com.example.data.combine.mongo.model.MongoUser;
import com.example.data.combine.mongo.repository.MongoUserRepository;
import com.example.data.combine.postgres.model.PostgresUser;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import com.example.data.combine.service.MongoUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by khan on 10/20/17.
 */
@Service
public class MongoUserServiceImpl implements MongoUserService {

  private static final Logger LOG = LoggerFactory.getLogger(MongoUserServiceImpl.class);

  @Autowired
  private MongoUserRepository mongoUserRepository;

  @Autowired
  private ProcessModelRabbitProperties processModelRabbitProperties;

  @Autowired
  private  ObjectMapper objectMapper;

 @Autowired
  RabbitTemplate rabbitTemplate;


  @Override
  @Cacheable(cacheNames = CacheName.NAME)
  public MongoUser findUserByName(String name) {
    MongoUser mongoUser = null;
    try {
      LOG.info("Fiend username with name : {}", name);
      mongoUser = this.mongoUserRepository.findByName(name);
    } catch (Exception e) {
      LOG.error("failed to find username with name : {}, error : {}", name, e);
    }
    return mongoUser;
  }

  @Override
  public boolean saveMongoUser(MongoUser mongoUser) {
    boolean success = false;
    LOG.info("call mongo save service mongoUser : {}", mongoUser);
    try {
        this.mongoUserRepository.save(mongoUser);
      success = true;
      LOG.info("save SuccessFull mongoUser : {}", mongoUser);
    } catch (Exception e) {
      LOG.error("failed to save mongoUser : {}", mongoUser, e);
    }
    return success;
  }

  @Override
  public Boolean convertAndSaveUser(PostgresUser postgresUser) {
    LOG.info("convertAndSaveUser with data: {}",postgresUser);
    MongoUser mongoUser = new MongoUser();
    try {
      mongoUser.setId(postgresUser.getId());
      mongoUser.setAddress(postgresUser.getAddress());
      mongoUser.setAge(postgresUser.getAge());
      mongoUser.setName(postgresUser.getName());
    }catch (Exception e){
      LOG.error("data is not converted in BeanUtils data : {}",postgresUser);
    }
    try {
      LOG.info("start saving data into MOngoCollection mongoUser : {}",mongoUser);
      this.mongoUserRepository.save(mongoUser);
      LOG.info("successfully  data save into  MOngoCollection mongoUser : {}",mongoUser);
      return true;
    }catch (Exception ex){
      LOG.error("data not save in mongoCollection mongoUser : {}",mongoUser);
    }
    return false;
  }

  @Override
  public Boolean sendUser(PostgresRequest request, MandatoryParameter parameter) {
    try {
      LOG.info("# sending in rabbit queue with parameter : {} , and request : {}", parameter,
          request);
      byte[] message = this.objectMapper.writeValueAsBytes(request);
      if (processModelRabbitProperties.getQueue() == null) {
        processModelRabbitProperties.setQueue("com.example.data.combine");
      }
      this.rabbitTemplate.convertAndSend(processModelRabbitProperties.getQueue(), message);
      LOG.info("# successfully send in rabbit queue with parameter : {} , and request : {}",
          parameter, request);
    } catch (Exception ex) {
      LOG.error("# failed to send in queue with parameter : {}, and error : {}", parameter, ex);
      return false;
    }
    return true;
  }

  @Override
  public void processUser(MongoUser mongoUser) {
    try {
      LOG.info("#receving user form queue with mongoUser : {}", mongoUser);
      if (mongoUser != null) {
        this.mongoUserRepository.save(mongoUser);
        LOG.info("#Sucessfully recived user with mongoUser : {}", mongoUser);
      }
    } catch (Exception ex) {
      LOG.error("# failed to recived message form queue with mongoUser : {}, and  error : {}",
          mongoUser, ex);
    }
  }
}

package com.example.data.combine.controller.config;

import com.example.data.combine.controller.handler.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mongodb.ReadPreference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

/**
 * Created by khan on 10/31/17.
 */
@Configuration
@ComponentScan(basePackages = "com.example.data.combine")
public class BeanConfiguration {

  @Bean
  DeserializationProblemHandler deserializationProblemHandler() {
    return new DeserializationProblemHandler();
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter){
    return new MongoTemplate(mongoDbFactory, mongoConverter);
  }

  @Bean
  public ObjectMapper createObjectMapper() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
    objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
    objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
    objectMapper.addHandler(this.deserializationProblemHandler());
    return objectMapper;
  }

}

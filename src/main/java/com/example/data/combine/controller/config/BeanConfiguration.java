package com.example.data.combine.controller.config;

import com.example.data.combine.controller.handler.DeserializationProblemHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
}

package com.example.data.combine.controller.config.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by khan on 11/23/17.
 */
@Configuration
@ComponentScan(basePackages = "com.example.data.combine")
public class BaseRabbitConfiguration {

  private static BaseRabbitProperties baseRabbitProperties;

  @Autowired
  BaseRabbitConfiguration(BaseRabbitProperties baseRabbitProperties) {
    BaseRabbitConfiguration.baseRabbitProperties = baseRabbitProperties;
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
        baseRabbitProperties.getHost(), baseRabbitProperties.getPort());
    connectionFactory.setUsername(baseRabbitProperties.getUsername());
    connectionFactory.setPassword(baseRabbitProperties.getPassword());
    return connectionFactory;
  }

  @Bean
  public AmqpAdmin amqpAdmin() {
    return new RabbitAdmin(connectionFactory());
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    return new RabbitTemplate(connectionFactory());
  }
}

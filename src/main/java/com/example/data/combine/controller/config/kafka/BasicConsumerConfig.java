package com.example.data.combine.controller.config.kafka;

import com.example.data.combine.eventmodel.AddUserEventModel;
import com.example.data.combine.eventmodel.DomainEventNames;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khan on 1/19/18.
 */
@EnableKafka
@Configuration
public class BasicConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String servers;



  public ConsumerFactory<String, AddUserEventModel> kafkaConsumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, DomainEventNames.COM_COMBINE_DOMAIN_ADD_USER);
    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(AddUserEventModel.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, AddUserEventModel> containerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, AddUserEventModel> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(kafkaConsumerFactory());
    return factory;
  }

}

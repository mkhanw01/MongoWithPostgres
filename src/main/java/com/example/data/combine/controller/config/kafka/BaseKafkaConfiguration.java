package com.example.data.combine.controller.config.kafka;

import com.example.data.combine.eventmodel.AddUserEventModel;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by khan on 1/18/18.
 */
@Configuration
public class BaseKafkaConfiguration {

  @Value("${spring.kafka.bootstrap-servers}")
  private String servers;

  @Bean
  public ProducerFactory<String, AddUserEventModel> producerFactory() {
    Map<String, Object> property = new HashMap<>();
    property.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
    property.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    property.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return new DefaultKafkaProducerFactory<>(property);
  }

  @Bean
  public KafkaTemplate<String, AddUserEventModel> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }
}

package com.example.data.combine.controller.config.rabbit.modelprocess;

import com.example.data.combine.controller.config.rabbit.BaseRabbitConfiguration;
import com.example.data.combine.mastermodel.Helper;
import com.example.data.combine.service.listner.ProcessModelQueueListener;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by khan on 12/6/17.
 */
@Configuration
@Import(BaseRabbitConfiguration.class)
public class ProcessModelRabbitConfiguration {

  private static ProcessModelRabbitProperties ProcessModelRabbitProperties;


  @Autowired
  public ProcessModelRabbitConfiguration(
      ProcessModelRabbitProperties ProcessModelRabbitProperties) {
    this.ProcessModelRabbitProperties = ProcessModelRabbitProperties;
  }

  @Bean
  public MessageListenerAdapter processModelListener(ProcessModelQueueListener messageListener) {
    return new MessageListenerAdapter(messageListener, Helper.MQ_DEFAULT_METHOD);
  }

  @Bean
  public Queue queue() {
    return new Queue(ProcessModelRabbitProperties.getQueue(), false);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(ProcessModelRabbitProperties.getExchange());
  }

  @Bean
  public Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(queue.getName());
  }

  @Bean
  public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter processModelListener) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(ProcessModelRabbitProperties.getQueue());
    container.setMessageListener(processModelListener);
    container.setMaxConcurrentConsumers(ProcessModelRabbitProperties.getListenerConcurrencyMax());
    return container;
  }
}

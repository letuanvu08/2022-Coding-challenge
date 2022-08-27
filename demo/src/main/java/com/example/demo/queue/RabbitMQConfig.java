//package com.example.demo.queue;
//
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Getter
//@RequiredArgsConstructor
//public class RabbitMQConfig {
//  private final RabbitMQProperties properties;
//
//  @Bean
//  Queue userProfileQueue() {
//    return new Queue("events", false);
//  }
//
//  @Bean
//  TopicExchange userProfileTopicExchange() {
//    return new TopicExchange("");
//  }
//
//  @Bean
//  Binding userProfileBinding(Queue userProfileQueue, TopicExchange userProfileTopicExchange) {
//    return BindingBuilder.bind(userProfileQueue).to(userProfileTopicExchange).with("");
//  }
//
//  @Bean
//  public MessageConverter jsonMessageConverter() {
//    return new Jackson2JsonMessageConverter();
//  }
//  @Bean
//  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//    rabbitTemplate.setMessageConverter(jsonMessageConverter());
//    return rabbitTemplate;
//  }
//
//}

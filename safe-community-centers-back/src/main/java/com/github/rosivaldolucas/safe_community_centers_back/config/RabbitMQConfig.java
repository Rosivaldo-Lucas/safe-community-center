package com.github.rosivaldolucas.safe_community_centers_back.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String EXCHANGE_NOTIFICATION_DIRECT = "safe_community_centers_back";
  public static final String EXCHANGE_NOTIFICATION = "safe_community_centers_back.notification";
  public static final String RK_NOTIFICATION = "safe_community_centers_back";

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange(EXCHANGE_NOTIFICATION_DIRECT, false, false);
  }

  @Bean
  public Queue queue() {
    return new Queue(EXCHANGE_NOTIFICATION, false, false, false);
  }

  @Bean
  public Binding binding() {
    return BindingBuilder
            .bind(this.queue())
            .to(this.directExchange())
            .with(RK_NOTIFICATION);
  }

}

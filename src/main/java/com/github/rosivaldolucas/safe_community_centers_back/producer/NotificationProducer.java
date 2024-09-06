package com.github.rosivaldolucas.safe_community_centers_back.producer;

import com.github.rosivaldolucas.safe_community_centers_back.config.RabbitMQConfig;
import com.github.rosivaldolucas.safe_community_centers_back.dto.NotificationFullyOccupiedDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

  private final RabbitTemplate rabbitTemplate;

  public NotificationProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendNotification(NotificationFullyOccupiedDTO notificationFullyOccupiedDTO) {
    String message =
            notificationFullyOccupiedDTO.id() + "\n" +
            notificationFullyOccupiedDTO.name() + "\n" +
            notificationFullyOccupiedDTO.maxCapacity();

    this.rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NOTIFICATION, RabbitMQConfig.RK_NOTIFICATION, message);
  }

}

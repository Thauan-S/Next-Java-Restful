package com.tropical.ms.email.consumers;

import com.tropical.ms.email.dtos.EmailRecordDto;
import com.tropical.ms.email.models.Email;
import com.tropical.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public  void listenEmailQueue(@Payload EmailRecordDto emailRecordDto){
       var email = new Email();
        BeanUtils.copyProperties(emailRecordDto,email);
        emailService.sendEmail(email);
    }
}

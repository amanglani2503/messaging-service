package com.example.messaging_service.consumers;

import com.example.messaging_service.entity.MessagingDetails;
import com.example.messaging_service.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingNotificationConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "booking_notification_queue")
    public void receiveMessage(MessagingDetails details) {
        if ("CONFIRMED".equals(details.getStatus())) {
            emailService.sendBookingEmail(details);
        }
        else{
            emailService.sendCancelEmail(details);
        }
    }
}

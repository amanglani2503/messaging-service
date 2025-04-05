package com.example.messaging_service.service;

import com.example.messaging_service.entity.MessagingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingEmail(MessagingDetails details) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(details.getRecipientEmail());
        message.setSubject("Booking Confirmation - " + details.getBookingId());
        message.setText("Dear " + details.getPassengerName() + ",\n\n"
                + "Your booking (ID: " + details.getBookingId() + ") for Flight " + details.getFlightId()
                + " from " + details.getDepartureAirport() + " to " + details.getArrivalAirport()
                + " has been confirmed.\n\n"
                + "Seat Number: " + details.getSeatNumber() + "\n"
                + "Departure: " + details.getDepartureTime() + "\n"
                + "Arrival: " + details.getArrivalTime() + "\n"
                + "Total Amount Paid: $" + details.getTotalAmountPaid() + "\n\n"
                + "Thank you for choosing our service!");

        mailSender.send(message);
        System.out.println("Email sent to: " + details.getRecipientEmail());
    }

    public void sendCancelEmail(MessagingDetails details) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(details.getRecipientEmail());
        message.setSubject("Booking Cancellation - " + details.getBookingId());
        message.setText("Dear " + details.getPassengerName() + ",\n\n"
                + "We regret to inform you that your booking (ID: " + details.getBookingId() + ") for Flight " + details.getFlightId()
                + " from " + details.getDepartureAirport() + " to " + details.getArrivalAirport()
                + " has been canceled.\n\n"
                + "Seat Number: " + details.getSeatNumber() + "\n"
                + "Originally scheduled Departure: " + details.getDepartureTime() + "\n"
                + "Originally scheduled Arrival: " + details.getArrivalTime() + "\n"
                + "Refund Amount (if applicable): $" + details.getTotalAmountPaid() + "\n\n"
                + "If you have any questions or need further assistance, feel free to contact us.\n\n"
                + "We hope to serve you again soon!");

        mailSender.send(message);
        System.out.println("Cancellation email sent to: " + details.getRecipientEmail());
    }

}

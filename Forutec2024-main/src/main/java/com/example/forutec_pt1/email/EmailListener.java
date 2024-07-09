package com.example.forutec_pt1.email;

import com.example.forutec_pt1.events.CommentEmailEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @Autowired
    private final EmailService emailService;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @EventListener
    @Async
    public void handleCommentEmailEvent(CommentEmailEvent event) {
        emailService.sendEmail(event.getEmail(), "Nuevo comentario en tu publicación", "Se ha realizado un nuevo comentario: " + event.getComment());
    }
}

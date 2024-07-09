package com.example.forutec_pt1.events;

import org.springframework.context.ApplicationEvent;

public class CommentEmailEvent extends ApplicationEvent  {
    private final String email;
    private final String comment;

    public CommentEmailEvent(String email, String comment) {
        super(email);
        this.email = email;
        this.comment = comment;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }
}

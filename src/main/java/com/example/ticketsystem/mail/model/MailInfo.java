package com.example.ticketsystem.mail.model;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class MailInfo {
    private String from;
    private String to;
    private String subject;
    private Map<String, Object> pros;
    private String template;
}

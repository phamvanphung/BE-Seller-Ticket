package com.example.ticketsystem.mail.service;


import com.example.ticketsystem.mail.model.MailInfo;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class GmailService implements IServiceMail {


    private final JavaMailSender sender;
    private final SpringTemplateEngine templateEngine;

    @Override
    @SneakyThrows
    public void sendEmail(MailInfo mailInfo) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(mailInfo.getPros());
        String html = templateEngine.process(mailInfo.getTemplate(), context);
        helper.setTo(mailInfo.getTo());
        helper.setText(html, true);
        helper.setSubject(mailInfo.getSubject());
        helper.setFrom(mailInfo.getFrom());
        sender.send(message);

    }
}

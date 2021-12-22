package org.gteperu.erp.everest.mail;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


public class MailConfig {
	@Bean
	public JavaMailSender javaMailService(ParametrosMail m) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", true);
        mailProperties.put("mail.smtp.starttls.enable", true);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);
        mailSender.setProtocol("smtp");
        mailSender.setUsername(m.getMailUsername());
        mailSender.setPassword(m.getMailPassword());
        return mailSender;
				
	}

}

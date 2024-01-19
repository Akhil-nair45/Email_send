package com.example.demo;

import java.io.File;
import java.util.Objects;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	private final JavaMailSender javaMailSender;

	public EmailService(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}
	
	
	@PostConstruct
	public void sendEmailWithAttachment() throws MessagingException
	{
		MimeMessage createMimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper mm= new MimeMessageHelper(createMimeMessage,true);//this will throw exception
		
		mm.setFrom("nairakh5@gmail.com");
		mm.setTo("nair56@gmail.com");
		mm.setText("Hey this is a test email as i have implemented the email from spring boot, Thanks for ur understanding and support!");
		mm.setSubject("This is a dummy email");
		
		FileSystemResource fr= new FileSystemResource(new File("C:\\Users\\158250\\EmailSpring\\captur.png"));
		mm.addAttachment(Objects.requireNonNull(fr.getFilename()),fr);
		
		javaMailSender.send(createMimeMessage);
		System.out.println("Mail send to user successfully!!!!");
	}
}

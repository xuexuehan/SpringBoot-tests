package com.xx.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot12MailApplicationTests {

	@Autowired
	JavaMailSenderImpl javaMailSender;

	@Test
	public void testSimpleMail() {
		//封装简单的邮件内容
		SimpleMailMessage message = new SimpleMailMessage();
		//标题
		message.setSubject("五一放假通知");
		message.setText("放假七天");
		//发件人
		message.setFrom("1778313247@qq.com");
		//收件人
		message.setTo("xuexuehan_101@163.com");
		javaMailSender.send(message);
	}


	@Test
	public void testMimeMail() throws MessagingException {
		//发送复杂邮件
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		//通过消息帮助对象来设置发送的内容，第二个参数为true表示可以发送附件
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setSubject("国庆放假通知");
		//第二个参数为true表示可以发送html代码
		messageHelper.setText("<h2 style='color:green'>放假七天</h2>", true);
		//发送附件
		messageHelper.addAttachment("1.jpg", new File("E:\\MyPhoto\\生活照.jpg"));
		messageHelper.addAttachment("2.jpg", new File("E:\\MyPhoto\\证件照.jpg"));
		//发件人
		messageHelper.setFrom("1778313247@qq.com");
		//收件人
		messageHelper.setTo("xuexuehan_101@163.com");
		javaMailSender.send(mimeMessage);
	}

}

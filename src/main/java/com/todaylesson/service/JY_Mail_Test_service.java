package com.todaylesson.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.todaylesson.Mapper.JY_Admin_LessonMapper;

@Service
public class JY_Mail_Test_service {

	@Autowired 
	private JY_Admin_LessonMapper mapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public int mailSendWithPassword(String member_id, String senioir_email, String lesson_title, HttpServletRequest request) {
				
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>�ȳ��ϼ��� '"+ member_id +"' ��</h2><br><br>" 
				+ "<p>��û���ֽ� ����" +lesson_title +"�� �ɻ簡 �������ϴ�.</p>"
				+ "<p>" + lesson_title +"ū �������� ������ ���� �� �� ���� �� �����ϴ�</p>"
				+ "<p> ������ ��û�Ͻô� �е鲲 �����帮�� ŰƮ ������ ����</p>"
				+ "<p> �����(������ : 010-7144-3797)�� ������ �帱 �����Դϴ�.</p>"
				+ "<p>������ ���� �÷��� �� ���� '������ ����'�� �������ּż� ����帮��,</p>"
				+ "<p>������ ������ �� ���� �ִ� ��� �̵��� �ϳ��� ������ ������ �׳�����! ������ ����ϰڽ��ϴ�!</p>";
		try {
			mail.setSubject("[TodayLesson] ��û�Ͻ� ���� �ɻ簡 �Ϸ� �Ǿ����ϴ�.", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(senioir_email));
			mailSender.send(mail);
		} catch (MessagingException e) { 
			e.printStackTrace();
		}
		return mapper.lessonResult(member_id, senioir_email,lesson_title);	
	
	}

	
	
}

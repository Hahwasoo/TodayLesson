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
public class JY_SN_Mail_service {

	@Autowired 
	private JY_Admin_LessonMapper mapper;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void mailSendLessonApprove(String member_id, String senior_email, String lesson_title, HttpServletRequest request) {
				
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2> �ȳ��ϼ��� '"+ member_id +"' ��</h2><br><br>" 
				+ "<p> ��û���ֽ� ���� '" +lesson_title + "' �� �ɻ簡 �������ϴ�.</p>"
				+ "<p><b> �ɻ� ��� ū �������� ������ ���� �� �� ���� �� �����ϴ�.</b></p><br>"
				+ "<p> ������ �����Ͻô� �ִϾ�в� �����帮�� ŰƮ ������ ����</p>"
				+ "<p> �����(������ : 010-7144-3797)�� ������ �帱 �����Դϴ�.</p><br>"
				+ "<p> ������ ���� �÷��� �� ���� '������ ����'�� �������ּż� ����帮��,</p>"
				+ "<p> ������ ������ �� ���� �ִ� ��� �̵��� �ϳ��� ��̸� ������ �׳�����! ������ ����ϰڽ��ϴ�!</p>";
		try {
			mail.setSubject("[TodayLesson] ��û�Ͻ� ���� �ɻ簡 �Ϸ� �Ǿ����ϴ�.", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(senior_email));
			mailSender.send(mail);
		} catch (MessagingException e) { 
			e.printStackTrace();
		}	
	
	}

	public void mailSendLessonReject(int lesson_no, String member_id, String senior_email, String lesson_title,HttpServletRequest request) {
		
		MimeMessage mail = mailSender.createMimeMessage();
		String htmlStr = "<h2>�ȳ��ϼ��� '"+ member_id +"' ��</h2><br><br>" 
				+ "<p> ��û���ֽ� ���� '" +lesson_title + "'  �� �ɻ簡 �������ϴ�.</p><br>"
				+ "<p> �ƽ��Ե� �̹����� ���� ������ ������ ������ �����ϱ� ����� �� �����ϴ�.</p>"
				+ "<p> ��� ���� ���� �ʾ����� ������ ���� �÷��� �� ���� '������ ����'�� �������ּż� �����մϴ�.</p>"
				+ "<p> ������ ������ �ñ��Ͻ� ���� �ִٸ� �����(������ : 010-7144-3797)�� ���� ��Ź�帳�ϴ�.</p>";
		try {
			mail.setSubject("[TodayLesson] ��û�Ͻ� ���� �ɻ簡 �Ϸ� �Ǿ����ϴ�.", "utf-8");
			mail.setText(htmlStr, "utf-8", "html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(senior_email));
			mailSender.send(mail);
		} catch (MessagingException e) { 
			e.printStackTrace();
		}	
		
	}



	
	
}

package com.todaylesson.service;

import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.todaylesson.Mapper.TodayLessonMapper;

@Service
public class Hm_Us_MailSendService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TodayLessonMapper mapper;
	

	// �̸��� ���� ����� �޼���
		private String init() {
			Random ran = new Random();
			StringBuffer sb = new StringBuffer();
			int num = 0;

			do {
				num = ran.nextInt(75) + 48;
				if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
					sb.append((char) num);
				} else {
					continue;
				}

			} while (sb.length() < size);
			if (lowerCheck) {
				return sb.toString().toLowerCase();
			}
			return sb.toString();
		}

		// ������ �̿��� Ű ����
		private boolean lowerCheck;
		private int size;

		public String getKey(boolean lowerCheck, int size) {
			this.lowerCheck = lowerCheck;
			this.size = size;
			return init();
		}

		//��ȣȭ
		@Resource(name="passwordEncoder")
		private BCryptPasswordEncoder encoder;
		
		
		public int mailSendWithPassword(String member_id, String member_email, HttpServletRequest request) {
			// TODO Auto-generated method stub
			String key = getKey(false, 6);
					
			MimeMessage mail = mailSender.createMimeMessage();
			String htmlStr = "<h2 style='text-align : center'>�ȳ��ϼ��� '"+ member_id +"' ��</h2><br><br>" 
					+ "<p style='text-align : center'>��й�ȣ ã�⸦ ��û���ּż� �ӽ� ��й�ȣ�� �߱��ص�Ƚ��ϴ�.</p>"
					+ "<p style='text-align : center'>�ӽ÷� �߱� �帰 ��й�ȣ�� <h2 style='text-align : center'>'" + key +"'</h2></p>" 
					+ "<p style='text-align : center'>�̸� �α��� �� �������������� ��й�ȣ�� �������ֽø� �˴ϴ�.</p><br>"
					+ "<h3 style='text-align : center'><a href='http://localhost:8080/todaylessonlogin'>TodayLesson :p Ȩ������ ���� ^0^</a></h3><br><br>"
					+ "<p style='text-align : center'>Ȥ�� �߸� ���޵� �����̶�� �� �̸����� �����ϼŵ� �˴ϴ�</p>";
			try {
				mail.setSubject("[TodayLesson] �ӽ� ��й�ȣ�� �߱޵Ǿ����ϴ�", "utf-8");
				mail.setText(htmlStr, "utf-8", "html");
				mail.addRecipient(RecipientType.TO, new InternetAddress(member_email));
				mailSender.send(mail);
			} catch (MessagingException e) { 
				e.printStackTrace();
			}
			
			key=encoder.encode(key);
			// ��й�ȣ ��ȣȭ
			/*key = UserSha256.encrypt(key);*/
			// ������ ���̽� ���� ��ȣ�� ������ �����Ų��.
			return mapper.searchPassword(member_id, member_email, key);
	
			
		}

	
}

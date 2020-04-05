package com.todaylesson.oreo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.todaylesson.service.TodaylessonService;
import com.todaylesson.DTO.Member_AuthDTO;
import com.todaylesson.DTO.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml"
	                ,"file:src\\main\\resources\\spring-security.xml"
})
public class MemberInsertTest {

// ����� �����Ҷ� ��ȣȭ�� ��й�ȣ�� ����. 
// ������ ������ ���ÿ� �ο��Ҷ� ( �� : admin�� user�� ���ÿ� �ٶ�) ����Ŭ�� insert all �������� �̿�������
// mysql���� insert all�� ���⶧���� values("a1","ROLE_ADMIN"), ("a1", "ROLE_USER"), ..... �̷� ���°� �Ǿ����.
// �������� mapper.xml���� Ȯ�� ����
		   
		  @Resource(name="todaylessonService")
		  private TodaylessonService service;
		  @Test
		  public void t1()
		  {
			  
			  // a1�� ������ admin�� user. auth�� 2���̹Ƿ� service.insert(vo) == 2 �� �Ǹ� true.
			  
			  MemberDTO dto=new MemberDTO(); 
			  dto.setMember_no(1);
			  dto.setMember_id("todaylesson");
			  dto.setMember_pwd("todaylesson");
			  dto.setMember_name("������ ����");
			  dto.setMember_birth("1993-12-26");
			  dto.setMember_phone("010-5109-7881");
			  dto.setMember_addr("��Ʈķ��");
			  dto.setMember_zipcode(12345);
			  dto.setMember_email("todaylesson144@gmail.com");
			  dto.setMember_nick("������ ����");
			  dto.setMember_img("������ ����");
			  dto.setMember_level(0);
			  dto.setEnabled(true);
			  ArrayList<Member_AuthDTO> arr=new ArrayList<>();
			  arr.add(new Member_AuthDTO("ROLE_ADMIN","todaylesson"));
			  dto.setAuthList(arr);
			  assertEquals(1, service.insert(dto));
			  
			  //a2�� ������ user �ϳ��� �����Ƿ� 1.
		  }
		
		

	}

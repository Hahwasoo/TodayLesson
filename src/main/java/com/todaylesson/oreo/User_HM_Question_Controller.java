package com.todaylesson.oreo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.service.User_HM_Question_Service;

@Controller
public class User_HM_Question_Controller {

	@Resource(name="user_hm_question_service")
	private User_HM_Question_Service service;
	
	
	//���� ��������� �̵�
	@RequestMapping("/hm_question_insert")
	private String hm_question_insert()
	{
		
		return "/TodayLesson_UserPage/hm_question_insert";
	}
}

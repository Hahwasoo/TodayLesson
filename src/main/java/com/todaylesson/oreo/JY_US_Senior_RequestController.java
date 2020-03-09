package com.todaylesson.oreo;

import javax.annotation.Resource;

import org.apache.maven.plugin.descriptor.MojoDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.DTO.SeniorDTO;
import com.todaylesson.service.JY_US_SeniorService;

@Controller
public class JY_US_Senior_RequestController {

	@Resource(name="senior_service")
	private JY_US_SeniorService seniorservice;
	
	// �ôϾ� ���� ��ư
	@RequestMapping("senior_request")
	public String senior_Request_PopUp() {
		return "TodayLesson_UserPage/jy_us_senior_request";
	}
	
	// �ôϾ� ���� ��ư �˾� > �� ������ �ôϾ�� ��ȯ��
	@RequestMapping("senior_request_form/{member_id}")
	public String senior_Request_Button(@PathVariable String member_id, Model model) {
		int mem_level=seniorservice.check_senior(member_id);
		
		if (mem_level == 1) {
			// �ôϾ�� ��������
			seniorservice.new_senior(member_id);
			model.addAttribute("mem_level",mem_level);

		} else {
			model.addAttribute("mem_level",mem_level);
		}


		return "TodayLesson_UserPage/jy_us_senior_form";

	}
	
	// �ôϾ� ���� ��ư �˾����� �� ������ ������ ��
	@RequestMapping("senior_switch/{member_id}")
	public String senior_Switch(@PathVariable String member_id) {
			return "TodayLesson_UserPage/jy_us_senior_switch";
	}
	
	// ����� �̹� �ôϾ� �Դϴ�.
	@RequestMapping("you_are_senior")
	public String you_are_senior() {
		return "TodayLesson_SeniorPage/jy_sn_you_are_senior";
	}
	
	// ���� ���� �Է��ϸ� �ôϾ� ���� ������Ʈ
	@RequestMapping("plus_senior")
	public String plus_senior(SeniorDTO dto, Model model) {
		int result = seniorservice.plus_senior(dto);
		model.addAttribute("result",result);
		return "TodayLesson_UserPage/jy_us_insertresult";
	}
	
	
}

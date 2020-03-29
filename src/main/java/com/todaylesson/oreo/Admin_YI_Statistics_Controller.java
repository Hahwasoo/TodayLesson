package com.todaylesson.oreo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.DTO.Stat_LogDTO;
import com.todaylesson.service.Admin_YI_Statistics_Service;

@Controller
@RequestMapping("/todaylessonadmin")
public class Admin_YI_Statistics_Controller {

	@Resource(name="admin_YI_Statistics_Service")
	private Admin_YI_Statistics_Service service;
	
	@RequestMapping("/member_statistics")
	public String user_statistics(Model model)
	{
		
		//���� ���
		List<Stat_LogDTO> list=service.allLog();
		
		//���� ��� ���
		List<Stat_LogDTO> distinctMember=service.distinct_member();
		
		//���� ���� ���
		List<Integer> cumlist=new ArrayList<>();
		
		//���� ��� ���� ���
		List<Integer> distinctList=new ArrayList<>();
		
		int cum=0; //���� ��
		int cum2=0; // ��� ������
		for(int i=0; i<list.size();i++)
		{
			
			cum+=list.get(i).getTotal();
			cum2+=distinctMember.get(i).getMember_total();
			cumlist.add(cum);
			distinctList.add(cum2);
			
		}
		
		model.addAttribute("list",list);
		model.addAttribute("distinctMember",distinctMember);
		model.addAttribute("cumlist",cumlist);
		model.addAttribute("distinctList",distinctList);
		
		return "/TodayLesson_AdminPage/yi_ad_member_statistics";
	}
	
}

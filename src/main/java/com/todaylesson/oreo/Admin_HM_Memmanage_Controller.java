package com.todaylesson.oreo;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todaylesson.DTO.MemberDTO;
import com.todaylesson.DTO.PageMaker;
import com.todaylesson.service.Admin_HM_MemmanageService;

@Controller
@RequestMapping("/todaylessonadmin/")
public class Admin_HM_Memmanage_Controller {

	@Resource(name="admin_hm_memmanage")
	private Admin_HM_MemmanageService service;
	
	@RequestMapping("/admin_hm_memmanage")
	public String memberlist(
			@RequestParam(required=false, defaultValue="") String search
			,@RequestParam(required=false, defaultValue="") String searchtxt
			,@RequestParam(required=false, defaultValue="1") int currPage
			,Model model ) 
	{
		  
		
		
		int totalCount= service.totalCount(search, searchtxt);
		int pageSize=15;
		int blockSize=5;
		
		
		PageMaker page = new PageMaker(currPage, totalCount, pageSize, blockSize);
		
		
		List<MemberDTO> list =service.adminmemberlist(search,searchtxt,page.getStartRow()
													,page.getEndRow());
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("search",search);
		model.addAttribute("searchtxt",searchtxt);
		
		System.out.println(search);
		System.out.println(searchtxt);
		System.out.println(totalCount);
		
		return "/TodayLesson_AdminPage/hm_ad_user_memmanage.hs_ad_main_section";
		
	}
	
	@RequestMapping("/adminmember_levelupdate")
	public String member_levelupdate(@RequestParam("member_id") String member_id
									,@RequestParam("member_level") int member_level
									,Model model)
	{
		
		if(member_level==1) {
		//������ �ִϾ�� �ٲ� �� 
			MemberDTO dto = service.oldlevel(member_id);
			//���� ������ �޾ƿ´�
			int oldlevel = dto.getMember_level();
				if(oldlevel == 2) {
				//���� ������ 2(�ôϾ� �� ��)
									HashMap<String, Object> map = new HashMap<>();
									map.put("member_id", member_id);
									map.put("member_level", member_level);
									int result = service.adminlevelupdate(map);
									//���� ������Ʈ�� �����ְ�
									int result2 = service.adminlevelup(map);
									//�ôϾ� ���̺� ����
				
										if(result>0 && result2>0) {
									    //���� ������Ʈ�� �ôϾ� ���̺��� ���� ���� �� ->  
										int authresult = service.adminmemberauthupdate(map);
										//���ѵ� ������Ʈ ������
										model.addAttribute("result",result);
										model.addAttribute("authresult",authresult);
										return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
			
						 				}else{
					
										return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";	
										}
				

			    }else{
				//�õ巹���� 2�� �ƴҋ�
				HashMap<String, Object> map = new HashMap<>();
				map.put("member_id", member_id);
				map.put("member_level", member_level);
				int result = service.adminlevelupdate(map);
				

								if(result>0)
								{
								int authresult = service.adminmemberauthupdate(map);
								model.addAttribute("authresult",authresult);
								model.addAttribute("result",result);
								return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
								}else{
					
								return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";	
								}
				
				
			    	}
			
		}else {
			//������ �ִϾ�� �ٲٴ°� �ƴ� ��
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("member_id", member_id);
			map.put("member_level", member_level);
			int result = service.adminlevelupdate(map);
								if(result>0) {
									int authresult = service.adminmemberauthupdate(map);
									model.addAttribute("authresult",authresult);
									model.addAttribute("result",result);
									return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
								}else {
									return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
								}
			
					
		}
		

		
		
		
		
		
		
		}//�޼���
	
	
}//��ü
	


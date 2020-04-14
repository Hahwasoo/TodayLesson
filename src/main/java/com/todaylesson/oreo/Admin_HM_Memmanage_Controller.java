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
		
		//(search);
		//(searchtxt);
		//(totalCount);
		
		return "/TodayLesson_AdminPage/hm_ad_user_memmanage.hs_ad_main_section";
		
	}
	
	
	
	@RequestMapping("/adminmember_levelupdate")
	public String member_levelupdate(@RequestParam("member_id") String member_id
									,@RequestParam("member_level") int member_level
									,Model model) {
		
		

		HashMap<String, Object> map = new HashMap<>();
		map.put("member_id", member_id);
		map.put("member_level", member_level);
		int succes = 1;
		int error = 0;
		int cancel = -1;
		
		if(member_level==1) {
			//��� ������ �ִϾ�(1)�� �ٲ� ��
			
			MemberDTO dto = service.oldlevel(member_id);
			int oldlevel = dto.getMember_level();
				//������ ������ �޾ƿ´�
				
				if(oldlevel ==2) {
					//������ �ôϾ�(2)���ٸ�
					int result = service.adminlevelupdate(map);
					//member���� ������Ʈ�� �����ְ� enabled �������ش�
					int result2 = service.adminlevelup(map);
					//������ �ִ� �ôϾ� ���̺� �������ش�
					int authresult = service.adminmemberauthupdate(map);
					//update�� member_auth�� 'ROLE_USER'(�ִϾ� �������߰����ش�)
					int authdelresult = service.adminmemberauthdelete(map);
					// delete�� ������ member_id�� member_auth�� 'ROLE_SENIOR'�� ������ �������ش�

					if(result>0 && result2>0 && authresult>0 && authdelresult>0) {
						
						model.addAttribute("result",succes);
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					}else {
						
						model.addAttribute("result",error);
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					}
			
				}else if(oldlevel ==3) {
					//������ ���(3)�̾��ٸ�(->�ôϾ� ���ٸ� �ôϾ�� �ִϾ� ���ٸ� �ִϾ�� ���� ���� ������ٰ�)
					int blockresult = service.adminlevelupdate(map);
					//member���� ������Ʈ�� �����ְ� enabled �������ش�
					int blockresult2 = service.delblockauth(map);
					//���� ROLE_ERROR���� ��������
					int authresult = service.adminmemberauthupdate(map);
					//update�� member_auth�� 'ROLE_USER'(�ִϾ� �������߰����ش�)
					if(blockresult>0 && blockresult2>0&&authresult>0) {
						//������ ������ �ִ»��·� ����� �Ǿ��⶧���� ������ ������Ʈ �����ش�
						
						
						model.addAttribute("result",succes);//1
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					}else {
						model.addAttribute("result",error);//0
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
	
					}
				}else{
					//������ �ִϾ�(1)���� ����� �ٽ� �ִϾ �ǰ� �;��� ���� ���� 
					//�����ھ��̵�� �ִϾ �� �� ����(��Ģ��)
					
					model.addAttribute("result",cancel);
					return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";

				}

		}else if(member_level ==2) {
			//��� ������ �ôϾ�� �ٲ� �� 
			MemberDTO dto = service.oldlevel(member_id);
			int oldlevel = dto.getMember_level();
			//������ ������ �޾ƿ´�
				if(oldlevel==1) {
					//�ִϾ �ôϾ �ǰ� �;� �� ��
					int result = service.adminlevelupdate(map);
					//member���� ������Ʈ�� �����ְ� enabled �������ش�
					int result2 = service.leveluptojr(map);
					//ROLE_SENIOR������ �߰� �����ش�
					int result3 = service.deljrauth(map);
					//������ �ִ� ROLE_JINOR������ ���������ش�
					int result4 = service.insertsenior(member_id);
					if(result>0 && result2>0 && result3>0 && result4>0) {
						model.addAttribute("result",succes);
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					}else {
						
						model.addAttribute("result",error);
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					}
					
				}if(oldlevel==3) {
					//����ƴٰ� �ٽ� �ôϾ�� �ٲ���
					int blockresult = service.adminlevelupdate(map);
					//member���� ������Ʈ�� �����ְ� enabled �������ش�
					int blockresult2 = service.delblockauth(map);
					//���� ROLE_ERROR���� ��������
					int result2 = service.leveluptojr(map);
					//ROLE_SENIOR������ �߰� �����ش�
					
					if(blockresult>0 && blockresult2>0&&result2>0) {
						
						model.addAttribute("result",succes);
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
						
					}else {
						model.addAttribute("result",error);
						return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
						
					}

				}else {
					//������ �ôϾ�(2)���� ����� �ٽ� �ôϾ �ɸ� ���� 
					//�����ھ��̵�� �ôϾ �� �� ����(��Ģ��)
					
					model.addAttribute("result",cancel);
					return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					
				}
			

		}else if(member_level==3) {
			//������� ��
			
			int result = service.adminlevelupdate(map);
			//member���� ������Ʈ�� �����ְ� enabled �������ش�
			int result2 =service.insertblock(map);
			//ROLE_ERROR�� �ش�
			int result3 =service.delnotblock(map);
			//���� ������ �����Ѵ�
			if(result > 0 && result2>0 && result3>0) {
				model.addAttribute("result",succes);
				return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
				
			}else {
				model.addAttribute("result",error);
				return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
			}
			
		}else {
			//������ ������ ������ ��
			MemberDTO dto = service.oldlevel(member_id);
			int oldlevel = dto.getMember_level();
			
			if(oldlevel != 3) {
				//���������� ����� �ƴ� ��= �ִϾ��̰ų� �ôϾ��� ��

				int result = service.adminlevelupdate(map);
				//member���� ������Ʈ�� �����ְ� enabled �������ش�
				int result2 = service.plusadmin(map);
				//ROLE_ADMIN�� �߰����ش�
				int result3 = service.delauth(map);
				//������ ROLE�� �������ش�(�ִϾ�� �ôϾ��)
				if(result>0 && result2>0 && result3>0) {
					model.addAttribute("result",succes);
					return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
					
				}else {
					model.addAttribute("result",error);
					return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
				}
			}else {
				//���� ������ ����� �� �����ڰ� �Ǵ°��� ���� �ȵ� �켱 �ִϾ �ôϾ�� ����� Ǯ�� �ٲ� ��
				model.addAttribute("result",cancel);
				return "TodayLesson_AdminPage/hm_ad_levelupdateresult.hs_ad_main_section";
				
			}

		}

	}

	}
	
	
	
	
	
	

	


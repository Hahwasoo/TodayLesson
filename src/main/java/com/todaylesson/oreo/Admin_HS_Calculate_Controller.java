package com.todaylesson.oreo;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todaylesson.DTO.CalculateDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.service.Admin_HS_Calculate_Service;

@Controller
@RequestMapping("/todaylessonadmin/")
public class Admin_HS_Calculate_Controller {

	@Resource(name="admin_HS_Calculate_Service")
	private Admin_HS_Calculate_Service adminCalculateService;
	
	@RequestMapping("/admin_calculate_list")
	public String adminCalculteList(Authentication authentication
                                  , HttpServletRequest request,HttpServletResponse response
			                      , Model model) {
		//��ť��Ƽ ������̵�
		UserDetails userDetails = (UserDetails) authentication.getPrincipal(); 
		String member_id = userDetails.getUsername();
		
		//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_requestlist
				            =adminCalculateService.calculateRequsetList();
		model.addAttribute("cal_requestlist", cal_requestlist);
		
		//�����û ����Ʈ �����Ǽ�
		/*List<Integer> cal_paycount=adminCalculateService.calPayCount();
		model.addAttribute("cal_paycount", cal_paycount);*/
				
		//�����û ����Ʈ �������ͱݾ�
		List<Integer> cal_lessonrevenuecost=adminCalculateService.calRevenueCost();
		model.addAttribute("cal_lessonrevenuecost", cal_lessonrevenuecost);

		//�����û ����Ʈ ����Ʈ���
		List<Integer> cal_usepointsum=adminCalculateService.calUsePointSum();
		model.addAttribute("cal_usepointsum", cal_usepointsum);
				
		//�����û ��������� ���ݰ�꼭 �ΰ���
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_CommSurtax
						    =adminCalculateService.cal_CommSurtax();
		model.addAttribute("cal_CommSurtax", cal_CommSurtax);

		//�ôϾ� ���ް��� �� ��û����
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_SeniorInfo
	    =adminCalculateService.cal_SeniorInfo();
		model.addAttribute("cal_SeniorInfo", cal_SeniorInfo);
		return "/TodayLesson_AdminPage/hs_ad_calculate_list.hs_ad_main_section";
	}
	
	@RequestMapping("/admin_calculate_give")
	public String admin_Calculate_Give(OrderListDTO dto
			                         , Authentication authentication
                                     , HttpServletRequest request,HttpServletResponse response
                                     , Model model) {
		
		int admin_Calculate_Give=adminCalculateService.admin_Calculate_Give(dto);
		model.addAttribute("admin_Calculate_Give", admin_Calculate_Give);
				
		return"/TodayLesson_AdminPage/hs_ad_calculte_give.hs_ad_main_section";
	}
	
	
	@RequestMapping("admin_calculte_datail")
	public String adminCalculteDetail(Authentication authentication
                                     , HttpServletRequest request,HttpServletResponse response
                                     , Model model) {
		
		/*SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO calculte_datail
		              =adminCalculateService.calculte_datail();*/
				
		return"/TodayLesson_AdminPage/hs_ad_calculte_detail.hs_ad_main_section";
	}
}

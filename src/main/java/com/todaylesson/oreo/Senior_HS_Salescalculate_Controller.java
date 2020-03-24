package com.todaylesson.oreo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO;
import com.todaylesson.service.Senior_HS_Salescalculate_Service;

@Controller
@RequestMapping("/todaylessonsenior/")
public class Senior_HS_Salescalculate_Controller {
	
	@Resource(name="senior_HS_Salescalculate_Service")
	private Senior_HS_Salescalculate_Service salescalculateService;
	
	@RequestMapping("/senior_sales_list/{senior_no}")
	public String salesList(@PathVariable int senior_no 
	            		   ,@RequestParam(required=false, defaultValue="") String sales_search_startdate
			               ,@RequestParam(required=false, defaultValue="") String sales_search_enddate
			               ,@RequestParam(required=false, defaultValue="") String search
			               ,@RequestParam(required=false, defaultValue="") String searchtxt
			               , Model model) {
		//������Ȳ��ü����Ʈ
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> salesList=
	         salescalculateService.salesList(senior_no, sales_search_startdate, sales_search_enddate, search, searchtxt); 
		
		model.addAttribute("salesList", salesList);
		//model.addAttribute("start_date",start_date);
		//model.addAttribute("end_date",end_date);
		model.addAttribute("search",search);
		model.addAttribute("searchtxt",searchtxt);
		
		return "/TodayLesson_SeniorPage/hs_sn_sales_list.sn_main_section";
	}
	
	@RequestMapping("/senior_calculate_requestlist/{senior_no}")
	public String calculateRequestList( @PathVariable int senior_no ,Model model) {
		//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
		List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> cal_requestlist=salescalculateService.calculateRequsetList(senior_no);
		model.addAttribute("cal_requestlist", cal_requestlist);
		
		//�����û ����Ʈ �����Ǽ�
		List<Integer> cal_paycount=salescalculateService.calPayCount(senior_no);
		model.addAttribute("cal_paycount", cal_paycount);
		
		//�����û ����Ʈ �������ͱݾ�
		List<Integer> cal_lessonrevenuecost=salescalculateService.calRevenueCost(senior_no);
		model.addAttribute("cal_lessonrevenuecost", cal_lessonrevenuecost);

		//�����û ����Ʈ ����Ʈ���
		List<Integer> cal_usepointsum=salescalculateService.calUsePointSum(senior_no);
		model.addAttribute("cal_usepointsum", cal_usepointsum);

		//�����û ����Ʈ ������ұݾ�
		//List<Integer> cal_lseeoncancelcost=salescalculateService.calCancelCost(senior_no);
		//model.addAttribute("cal_lseeoncancelcost", cal_lseeoncancelcost);
	
		//�����û ����Ʈ ����Ʈ���
		//List<Integer> cal_cancelpointsum=salescalculateService.calCancelPointSum(senior_no);
		//model.addAttribute("cal_cancelpointsum", cal_cancelpointsum);
		
		return "/TodayLesson_SeniorPage/hs_sn_calculate_requestlist.sn_main_section";
	}

}

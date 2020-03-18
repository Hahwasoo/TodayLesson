package com.todaylesson.oreo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.PageMaker;
import com.todaylesson.DTO.SQLjoin_OrderList_Order_detail_MemberDTO;
import com.todaylesson.service.Admin_YI_OrderListService;

@Controller
public class Admin_YI_Orderlist_Controller {

	@Resource(name="admin_YI_OrderListService")
	private Admin_YI_OrderListService service;
	
	@RequestMapping("/todaylessonadmin/admin_orderlist")
	public String orderlist(
			@RequestParam(required=false, defaultValue="0") int orderlist_orderstatus
			,@RequestParam(required=false, defaultValue="1") int orderlist_category
			,@RequestParam(required=false, defaultValue="0") int orderlist_search
			,@RequestParam(required=false, defaultValue="") String start_date
			,@RequestParam(required=false, defaultValue="") String end_date
			,@RequestParam(required=false, defaultValue="1") int currPage
			,Model model)
	{
		
		//�� �Խñ� ��
		int totalCount= service.totalCount(orderlist_category,orderlist_search);
		int pageSize=15;
		int blockSize=5;
		
		
		PageMaker page=new PageMaker(currPage,totalCount,pageSize,blockSize);
		
		
		List<SQLjoin_OrderList_Order_detail_MemberDTO> list=service.orderlist(orderlist_orderstatus,orderlist_category,orderlist_search,start_date,end_date
				,page.getStartRow()
				,page.getEndRow());
		System.out.println("list:"+list.get(0).getOrderlist_no());
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("search",orderlist_search);
		
		return "/TodayLesson_AdminPage/yi_ad_orderlist";
	}
}

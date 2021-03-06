package com.todaylesson.oreo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.service.Admin_HS_Statsales_Service;

@Controller
@RequestMapping("/todaylessonadmin")
public class Admin_HS_Statsales_Controller {

	@Resource(name="admin_HS_Statsales_Service")
	private Admin_HS_Statsales_Service adminStatSalesService;
	
	@RequestMapping("/sales_statistics")
	public String salesStatistics(@RequestParam(required=false,defaultValue="date") String ymd
			                    , @RequestParam(required=false,defaultValue="") String start_date
			                    , @RequestParam(required=false,defaultValue="") String end_date
			                    , Model model) {
		
		//매출통계(일별, 주별, 월별, 년별)
		List<OrderListDTO> StatSalesAllChart=adminStatSalesService.StatSalesAllChart(ymd,start_date,end_date);
		model.addAttribute("StatSalesAllChart", StatSalesAllChart);
		
		//매출목록
		/*List<OrderListDTO> StatSalesList=adminStatSalesService.StatSalesList(ymd,start_date,end_date);*/

		return "TodayLesson_AdminPage/hs_ad_sales_statistices.hs_ad_main_section";
	}
	
	//AmChart
	@RequestMapping(value = "/sales_statistics", method = RequestMethod.POST)
	@ResponseBody
	public List chartOutput()throws Exception {
		
		List<OrderListDTO> chartOutput = adminStatSalesService.chartOutput();
		ArrayList response = new ArrayList();
		  for(int i =0; i < chartOutput.size(); i ++) {
		    HashMap<String , Object> map = new HashMap<String, Object>();
		    map.put("date", chartOutput.get(i).getRegYear()+"-"+chartOutput.get(i).getRegMonth()+"-"+chartOutput.get(i).getRegDay());
		    map.put("output", chartOutput.get(i).getCostTotal());
		    response.add(map);
		    //(map);
		    //(response);
		  }
		  //(response);
		  return response;

		}
}

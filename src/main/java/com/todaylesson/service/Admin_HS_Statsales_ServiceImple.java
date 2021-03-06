package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.Mapper.Admin_HS_Statsales_Mapper;

@Service(value="admin_HS_Statsales_Service")
public class Admin_HS_Statsales_ServiceImple implements Admin_HS_Statsales_Service {

	@Resource(name="admin_HS_Statsales_Mapper")
	private Admin_HS_Statsales_Mapper adminStatSalesMapper;

	//매출통계(일별, 주별, 월별, 년별)
	@Override
	public List<OrderListDTO> StatSalesAllChart(String ymd, String start_date, String end_date) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<>();
		hm.put("ymd", ymd);
		hm.put("start_date", start_date);
		hm.put("end_date", end_date);
		return adminStatSalesMapper.StatSalesAllChart(hm);
	}

	//AmChart
	@Override
	public List<OrderListDTO> chartOutput() {
		// TODO Auto-generated method stub
		return adminStatSalesMapper.chartOutPut();
	}

	//매출목록
//	@Override
//	public List<OrderListDTO> StatSalesList(String ymd, String start_date, String end_date) {
//		// TODO Auto-generated method stub
//		HashMap<String, Object> hm=new HashMap<>();
//		hm.put("ymd", ymd);
//		hm.put("start_date", start_date);
//		hm.put("end_date", end_date);
//		return adminStatSalesMapper.StatSalesList(hm);
//	}

	

}

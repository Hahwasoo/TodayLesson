package com.todaylesson.service;

import java.util.List;

import com.todaylesson.DTO.OrderListDTO;

public interface Admin_HS_Statsales_Service {

	//�������(�Ϻ�, �ֺ�, ����, �⺰)
	public List<OrderListDTO> StatSalesAllChart(String ymd, String start_date, String end_date);

	//AmChart
	public List<OrderListDTO> chartOutput();

	//������
	//public List<OrderListDTO> StatSalesList(String ymd, String start_date, String end_date);

}

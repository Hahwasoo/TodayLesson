package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.OrderListDTO;

@Mapper
public interface Admin_HS_Statsales_Mapper {

	//�������(�Ϻ�, �ֺ�, ����, �⺰)
	public List<OrderListDTO> StatSalesAllChart(HashMap<String, Object> hm);

	public List<OrderListDTO> chartOutPut();

	

}

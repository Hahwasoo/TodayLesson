package com.todaylesson.service;

import java.util.List;

import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_Sales_CalculateDTO;

public interface Senior_HS_Salescalculate_Service {
	
	//������Ȳ��ü����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_Sales_CalculateDTO> 
	       salesList(int senior_no, String start_date, String end_date, String search, String searchtxt);

	//�����û ����Ʈ �����ȣ / ������� / �����û��
	public List<SQLjoin_Member_Senior_Lesson_OrderList_Sales_CalculateDTO> calculateRequsetList(int senior_no);

}

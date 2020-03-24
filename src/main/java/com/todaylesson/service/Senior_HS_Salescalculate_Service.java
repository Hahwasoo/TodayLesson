package com.todaylesson.service;

import java.util.List;

import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO;

public interface Senior_HS_Salescalculate_Service {
	
	//������Ȳ��ü����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> 
	       salesList(int senior_no, String sales_search_startdate, String sales_search_enddate, String search, String searchtxt);

	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> calculateRequsetList(int senior_no);

	//�����û ����Ʈ �����Ǽ�
	public List<Integer> calPayCount(int senior_no);

	//�����û ����Ʈ �������ͱݾ�
	public List<Integer> calRevenueCost(int senior_no);

	//�����û ����Ʈ ����Ʈ���
	public List<Integer> calUsePointSum(int senior_no);

	//�����û ����Ʈ ������ұݾ�
	//public List<Integer> calCancelCost(int senior_no);

	//�����û ����Ʈ ����Ʈ���
	//public List<Integer> calCancelPointSum(int senior_no);

}

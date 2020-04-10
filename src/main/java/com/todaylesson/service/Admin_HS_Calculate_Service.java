package com.todaylesson.service;

import java.util.List;

import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.DTO.CalculateDTO;

public interface Admin_HS_Calculate_Service {
	
	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calculateRequsetList();
	
	//�����û ����Ʈ �������ͱݾ�
	public List<Integer> calRevenueCost();

	//�����û ����Ʈ ����Ʈ���
	public List<Integer> calUsePointSum();

	//�����û ��������� ���ݰ�꼭 �ΰ���
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_CommSurtax();

	//�ôϾ� ���ް��� �� ��û����
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_SeniorInfo();

	public int admin_Calculate_Give(OrderListDTO dto);

	//SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO calculte_datail();

}

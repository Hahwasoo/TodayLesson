package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO;

@Mapper
public interface Senior_HS_Salescalculate_Mapper {
	//������Ȳ��ü����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> SalesList(HashMap<String, Object> hm);

	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> cal_RequestList(int senior_no);

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

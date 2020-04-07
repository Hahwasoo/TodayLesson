package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO;
import com.todaylesson.DTO.SeniorDTO;

@Mapper
public interface Senior_HS_Salescalculate_Mapper {
	
	//�ôϾ� ������̵�� �ôϾ��ȣ �޾ƿ���
	//public int get_Seniorno(String member_id);
	
	//������Ȳ��ü����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> SalesList(HashMap<String, Object> hm);

	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> cal_RequestList(String member_id);

	//�����û ����Ʈ �����Ǽ�
	public List<Integer> calPayCount(String member_id);

	//�����û ����Ʈ �������ͱݾ�
	public List<Integer> calRevenueCost(String member_id);

	//�����û ����Ʈ ����Ʈ���
	public List<Integer> calUsePointSum(String member_id);

	//�����û �ôϾ������
	public SeniorDTO accountDetailDTO(String member_id);
	
	//�����û �ôϾ������������
	public int accountUpdateDTO(SeniorDTO dto);

	//�����û���ɱݾ�
	//public int calculate_PossibilityCost(String member_id);

	//������ݾ�
	//public int calculate_WaitingCost(String member_id);
 
	//���곻�� ��� ����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> calStatementList(String member_id);

	//���곻�� ����Ʈ �����Ǽ�
	public List<Integer> calStatementPayCount(String member_id);

	//���곻�� ����Ʈ �������ͱݾ�
	public List<Integer> calStatementRevenueCost(String member_id);

	//���곻�� ����Ʈ ����Ʈ���
	public List<Integer> calStatementUsePointSum(String member_id);

	


}

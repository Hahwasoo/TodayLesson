package com.todaylesson.service;

import java.util.List;

import com.todaylesson.DTO.CalculateDTO;
import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.DTO.SeniorDTO;

public interface Senior_HS_Salescalculate_Service {

	//�ôϾ� ������̵�� �ôϾ��ȣ �޾ƿ���
	//public int get_Seniorno(String member_id);
	
	//������Ȳ ��Żī��Ʈ
	public int seniorSales_TotalCount(int senior_no, String search, String searchtxt);
	
	//������Ȳ��ü����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> 
	       salesList(int senior_no, String start_date, String end_date, String search, String searchtxt, int startRow, int endRow);
	       
	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ 
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calculateRequsetList(int senior_no);

	//�����û  �������
	//public List<SeniorDTO> cal_SeniorAccount(String member_id);

	
	//�����û ����Ʈ �����Ǽ�
	public List<Integer> calPayCount(int senior_no);

	//�����û ����Ʈ �������ͱݾ�
	public List<Integer> calRevenueCost(int senior_no);

	//�����û ����Ʈ ����Ʈ���
	public List<Integer> calUsePointSum(int senior_no);
	
	//�����û ��������� ���ݰ�꼭 �ΰ���
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_CommSurtax(int senior_no);

	//�����û �ôϾ������
	public SeniorDTO accountDetailDTO(String member_id);
	
	//�����û �ôϾ������������
	public int accountUpdateDTO(SeniorDTO dto);

	//�����û���ɱݾ�
	public Integer calculate_PossibilityCost(int senior_no);

	//������ݾ�
	public Integer calculate_WaitingCost(int senior_no);

	//�����û
	public int calculateRequestResult(CalculateDTO dto);
	
	//���곻�� ��� ����Ʈ
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calStatementList(String member_id);

	//���곻�� ����Ʈ �����Ǽ�
	public List<Integer> calStatementPayCount(String member_id);

	//���곻�� ����Ʈ �������ͱݾ�
	public List<Integer> calStatementRevenueCost(String member_id);

	//���곻�� ����Ʈ ����Ʈ���
	public List<Integer> calStatementUsePointSum(String member_id);

	//������¾�����Ʈ(��������)
	public int updateOrderCalculateStatus(OrderListDTO orderdto);


	

}

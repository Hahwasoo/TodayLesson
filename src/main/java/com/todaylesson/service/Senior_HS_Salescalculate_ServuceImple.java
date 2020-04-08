package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.LessonDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.DTO.SeniorDTO;
import com.todaylesson.Mapper.Senior_HS_Salescalculate_Mapper;

@Service(value="senior_HS_Salescalculate_Service")
public class Senior_HS_Salescalculate_ServuceImple implements Senior_HS_Salescalculate_Service {
	
	@Resource(name="senior_HS_Salescalculate_Mapper")
	private Senior_HS_Salescalculate_Mapper salescalculateMapper;

	//�ôϾ� ������̵�� �ôϾ��ȣ �޾ƿ���
	/*@Override
	public int get_Seniorno(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.get_Seniorno(member_id);
	}*/
	
	//������Ȳ��ü����Ʈ
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> 
	       salesList(String member_id, String sales_search_startdate, String sales_search_enddate, String search, String searchtxt) {  //String start_date, String end_date, 
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<String, Object>();
		
		hm.put("member_id", member_id);
		hm.put("sales_search_startdate", sales_search_startdate);
		hm.put("sales_search_enddate", sales_search_enddate);
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		
		return salescalculateMapper.SalesList(hm);
	}
	       
	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calculateRequsetList(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.cal_RequestList(member_id);
	}

	//�����û ����Ʈ �����Ǽ�
	@Override
	public List<Integer> calPayCount(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calPayCount(member_id);
	}

	//�����û ����Ʈ �������ͱݾ�
	@Override
	public List<Integer> calRevenueCost(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calRevenueCost(member_id);
	}

	//�����û ����Ʈ ����Ʈ���
	@Override
	public List<Integer> calUsePointSum(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calUsePointSum(member_id);
	}

	//�����û �ôϾ������
	@Override
	public SeniorDTO accountDetailDTO(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.accountDetailDTO(member_id);
	}
	
	//�����û �ôϾ������������
	@Override
	public int accountUpdateDTO(SeniorDTO dto) {
		// TODO Auto-generated method stub
		return salescalculateMapper.accountUpdateDTO(dto);
	}

	//�����û���ɱݾ�
	/*@Override
	public int calculate_PossibilityCost(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calculate_PossibilityCost(member_id);
	}*/

	//������ݾ�
	/*@Override
	public int calculate_WaitingCost(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calculate_WaitingCost(member_id);
	}*/
	
	//���곻�� ��� ����Ʈ
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calStatementList(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calStatementList(member_id);
	}

	//���곻�� ����Ʈ �����Ǽ�
	@Override
	public List<Integer> calStatementPayCount(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calStatementPayCount(member_id);
	}

	//���곻�� ����Ʈ �������ͱݾ�
	@Override
	public List<Integer> calStatementRevenueCost(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calStatementRevenueCost(member_id);
	}

	//���곻�� ����Ʈ ����Ʈ���
	@Override
	public List<Integer> calStatementUsePointSum(String member_id) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calStatementUsePointSum(member_id);
	}

	



}

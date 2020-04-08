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
	
	//������Ȳ ��Żī��Ʈ
	@Override
	public int seniorSales_TotalCount(int senior_no, String search, String searchtxt) {
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("senior_no", senior_no);
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		return salescalculateMapper.seniorSales_TotalCount(hm);
	}
	
	//������Ȳ��ü����Ʈ
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> 
	       salesList(int senior_no, String start_date, String end_date, String search, String searchtxt, int startRow, int endRow) {  
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<String, Object>();
		
		hm.put("senior_no", senior_no);
		hm.put("start_date", start_date);
		hm.put("end_date", end_date);
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		hm.put("startRow", startRow);
		hm.put("endRow", endRow);
		
		return salescalculateMapper.SalesList(hm);
	}
	       
	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calculateRequsetList(int senior_no) {
		// TODO Auto-generated method stub
		return salescalculateMapper.cal_RequestList(senior_no);
	}

	//�����û ����Ʈ �����Ǽ�
	@Override
	public List<Integer> calPayCount(int senior_no) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calPayCount(senior_no);
	}

	//�����û ����Ʈ �������ͱݾ�
	@Override
	public List<Integer> calRevenueCost(int senior_no) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calRevenueCost(senior_no);
	}

	//�����û ����Ʈ ����Ʈ���
	@Override
	public List<Integer> calUsePointSum(int senior_no) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calUsePointSum(senior_no);
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

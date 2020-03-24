package com.todaylesson.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO;
import com.todaylesson.Mapper.Senior_HS_Salescalculate_Mapper;

@Service(value="senior_HS_Salescalculate_Service")
public class Senior_HS_Salescalculate_ServuceImple implements Senior_HS_Salescalculate_Service {
	
	@Resource(name="senior_HS_Salescalculate_Mapper")
	private Senior_HS_Salescalculate_Mapper salescalculateMapper;

	//������Ȳ��ü����Ʈ
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> 
	       salesList(int senior_no, String sales_search_startdate, String sales_search_enddate, String search, String searchtxt) {  //String start_date, String end_date, 
		// TODO Auto-generated method stub
		HashMap<String, Object> hm=new HashMap<String, Object>();
		hm.put("senior_no", senior_no);
		hm.put("sales_search_startdate", sales_search_startdate);
		hm.put("sales_search_enddate", sales_search_enddate);
		hm.put("search", search);
		hm.put("searchtxt", searchtxt);
		return salescalculateMapper.SalesList(hm);
	}

	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ / �������
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_Sales_CalculateDTO> calculateRequsetList(int senior_no) {
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

	//�����û ����Ʈ ������ұݾ�
	/*@Override
	public List<Integer> calCancelCost(int senior_no) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calCancelCost(senior_no);
	}*/

	//�����û ����Ʈ ����Ʈ���
	/*@Override
	public List<Integer> calCancelPointSum(int senior_no) {
		// TODO Auto-generated method stub
		return salescalculateMapper.calCancelPointSum(senior_no);
	}*/

	
	

}

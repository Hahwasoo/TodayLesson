package com.todaylesson.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.DTO.OrderListDTO;
import com.todaylesson.DTO.SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO;
import com.todaylesson.Mapper.Admin_HS_Calculate_Mapper;
import com.todaylesson.DTO.CalculateDTO;;

@Service(value="admin_HS_Calculate_Service")
public class Admin_HS_Calculate_ServiceImple implements Admin_HS_Calculate_Service {

	@Resource(name="admin_HS_Calculate_Mapper")
	private Admin_HS_Calculate_Mapper adminCalculateMapper;

	//�����û ����Ʈ �����ȣ / ������� / �����û�� / ����Ⱓ
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> calculateRequsetList() {
		// TODO Auto-generated method stub
		return adminCalculateMapper.calculateRequsetList();
	}
	//�����û ����Ʈ �������ͱݾ�
	@Override
	public List<Integer> calRevenueCost() {
		// TODO Auto-generated method stub
		return adminCalculateMapper.calRevenueCost();
	}
	
	//�����û ����Ʈ ����Ʈ���
	@Override
	public List<Integer> calUsePointSum() {
		// TODO Auto-generated method stub
		return adminCalculateMapper.calUsePointSum();
	}

	//�����û ��������� ���ݰ�꼭 �ΰ���
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_CommSurtax() {
		// TODO Auto-generated method stub
		return adminCalculateMapper.cal_CommSurtax();
	}
	
	//�ôϾ� ���ް��� �� ��û����
	@Override
	public List<SQLjoin_Member_Senior_Lesson_OrderList_OrderDetail_CalculateDTO> cal_SeniorInfo() {
		// TODO Auto-generated method stub
		return adminCalculateMapper.cal_SeniorInfo();
	}
	@Override
	public int admin_Calculate_Give(OrderListDTO dto) {
		// TODO Auto-generated method stub
		return adminCalculateMapper.admin_Calculate_Give(dto);
	}
}

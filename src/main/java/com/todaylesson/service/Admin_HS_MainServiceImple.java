package com.todaylesson.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.todaylesson.Mapper.Admin_HS_MainMapper;

@Service(value="admin_HS_MainService")
public class Admin_HS_MainServiceImple implements Admin_HS_MainService {

	@Resource(name="admin_HS_MainMapper")
	private Admin_HS_MainMapper adminmainMapper;

	//����ī�װ���
	@Override
	public int lessonITCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonITCount();
	}

	@Override
	public int lessonCookCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonCookCount();
	}

	@Override
	public int lessonHandmadeCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonHandmadeCount();
	}

	@Override
	public int lessonSportCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonSportCount();
	}

	@Override
	public int lessonEducationCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonEducationCount();
	}

	@Override
	public int lessonOtherCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.lessonOtherCount();
	}

	//��ǰī�װ���
	@Override
	public int productITCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productITCount();
	}

	@Override
	public int productCookCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productCookCount();
	}

	@Override
	public int productHandmadeCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productHandmadeCount();
	}

	@Override
	public int productSportCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productSportCount();
	}

	@Override
	public int productEducationCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productEducationCount();
	}

	@Override
	public int productOtherCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.productOtherCount();
	}

	//���ϰԽñۼ� ����
	@Override
	public int freeboardWriteCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.freeboardWriteCount();
	}

	//���� �Ǹűݾ� ����
	@Override
	public int orderlistCostSum() {
		// TODO Auto-generated method stub
		return adminmainMapper.orderlistCostSum();
	}

	//���ϰ����ڼ� ����
	@Override
	public int memberJoinCount() {
		// TODO Auto-generated method stub
		return adminmainMapper.memberJoinCount();
	}
	
}
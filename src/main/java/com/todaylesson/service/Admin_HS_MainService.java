package com.todaylesson.service;

public interface Admin_HS_MainService {

	//���ϰԽñۼ� ����
	public int freeboardWriteCount();

	//���� �Ǹűݾ� ����
	public int orderlistCostSum();

	//���ϰ����ڼ� ����
	public int memberJoinCount();
	
	//����ī�װ�
	public int lessonITCount();

	public int lessonCookCount();

	public int lessonHandmadeCount();

	public int lessonSportCount();

	public int lessonEducationCount();

	public int lessonOtherCount();

	//��ǰī�װ�
	public int productITCount();

	public int productCookCount();

	public int productHandmadeCount();

	public int productSportCount();

	public int productEducationCount();

	public int productOtherCount();

	//���ɴ뺰 ȸ����Ȳ
	public int memberAge10Sum();

	public int memberAge20Sum();

	public int memberAge30Sum();

	public int memberAge40Sum();

	public int memberAge50Sum();

	public int memberAge60Sum();

	public int memberAge70PlusSum();

}

package com.todaylesson.service;

public interface Admin_HS_MainService {

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

	//���ϰԽñۼ� ����
	public int freeboardWriteCount();

	//���� �Ǹűݾ� ����
	public int orderlistCostSum();

	//���ϰ����ڼ� ����
	public int memberJoinCount();

}

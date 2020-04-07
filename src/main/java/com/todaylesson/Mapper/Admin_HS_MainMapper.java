package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.OrderListDTO;

@Mapper
public interface Admin_HS_MainMapper {

	//���ϰ����ڼ� ����
	public int memberJoinCount();

	//���� �ôϾ���ȯ�� ����
	public int seniorChangeCount();
	
	//���ϰԽñۼ� ����
	public int freeboardWriteCount();

	//���� �Ǹűݾ� ����
	public int orderlistCostSum();

	//�������Ȳ
	  //��ϵȻ�ǰ
	  public int registrationProductCount();
	  //�ǸŰ��ɻ�ǰ
	  public int possibilityProductCount();
	  //ǰ����ǰ
	  public int soldOutProductCount();
	
	//������Ȳ
	  //��ϵ� ���� 
	  public int registrationLessonCount();
	  //���·���
	  public int openLessonCount();
	  //��������
	  public int closeLessonCount();
	  //ǰ������
	  public int soldOutLessonCount();
	  
	//�ֹ���Ȳ
	  //�ֹ��Ϸ�
	  public int orderCompleteCount();
	  //�����
	  public int orderDuringShippingCount();
	  //��ۿϷ�
	  public int orderShippingCompleteCount();
	  //�ֹ����
	  public int orderCancelCount();
	  
	//������Ȳ
	  //�����Ϸ�
	  public int paymentCompleteCount();
	  //ȯ������
	  public int refundAcceptCount();
	  //ȯ�ҿϷ�
	  public int refundCompleteCount();
	  
	//����������Ȳ
	  //�����ű�����  
	  public int newLessonAcceptCount();
	  //�����ɻ���
	  public int newLessonEvaluationCount();
	  //��������
	  public int newLessonAcceptanceCount();	
	  //��������
	  public int newLessonRefuseCount();
	
	//�ôϾ�������Ȳ
	  //������
	  public int seniorCalculateWaitCount();
	  //���갡��
	  public int seniorCalculatePossibleCount();
	  //����Ϸ�
	  public int seniorCalculateCompleteCount();

	//1:1������Ȳ
	  //��������
	  public int questionLessonCount();
	  //�ôϾ��
	  public int questionSeniorCount();
	  //������
	  public int questionStoreCount();
	  //�ֹ�����
	  public int questionOrderCount();
	  //��Ÿ����
	  public int questionOtherCount();
	  //�亯���
	  public int questionAnswerWaitCount();
	  //�亯�Ϸ�
	  public int questionAnswerCompleteCount();
	  
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

	//�������(�Ϻ�, �ֺ�, ����, �⺰)
	public List<OrderListDTO> adMainStatSalesAllChart(HashMap<String, Object> hm);

	//AmChart
	public List<OrderListDTO> chartOutput();


	
}

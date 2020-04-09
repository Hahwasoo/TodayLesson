package com.todaylesson.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalculateDTO {

	private int orderlist_no;
	private int calculate_no;
	private String calculate_date; //�����û��
	//private int calculate_cost; //����ݾ�
	private String calculate_bank_name; //������� �����
	private String calculate_account_name; //������� ������
	private String calculate_account_num; // ������� ���¹�ȣ
	private String calculate_crno; //���� ����ڹ�ȣ
	private String calculate_crno_name; // ���� ����ڸ�
	private String calculate_name; //�����û��
	private String calculate_phone; // �����û������ȣ
	
	//�ôϾ� ����
	private int senior_no;
	
}

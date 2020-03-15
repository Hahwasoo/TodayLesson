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
	private int lessondate_count; //���� ������ or ���� ������ +5 �����Ǽ�
}

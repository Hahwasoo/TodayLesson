package com.todaylesson.Mapper;

import org.apache.ibatis.annotations.Mapper;

import com.todaylesson.DTO.SeniorDTO;

@Mapper
public interface JY_US_SeniorMapper {

	// �ôϾ �߰�
	public void new_senior(String member_id);

	// ��� ��� ������
	public void member_level_up(String member_id);

	// �ôϾ� ���� ���� �Է�
	public int plus_senior(SeniorDTO dto);

	// �ôϾ� ���� ��� ���� Ȯ��
	public int check_senior(String member_id);
	

}

package com.todaylesson.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


import com.todaylesson.DTO.SQLjoin_Member_FreeBoardDTO;

@Mapper
public interface User_YI_FreeBoard_Mapper {

//����¡ ī��Ʈ
public int getCount(HashMap<String, Object> hm);	

//�Խù� ��ü����
public List<SQLjoin_Member_FreeBoardDTO> list(HashMap<String, Object> hm);

//�Խù� �󼼺���
public SQLjoin_Member_FreeBoardDTO freeboard_detail(int freeboard_no);

//�Խù� ��ȸ�� ����
public void freeboard_readnoUp(int freeboard_no);





}
